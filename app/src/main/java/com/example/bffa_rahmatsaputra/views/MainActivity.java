package com.example.bffa_rahmatsaputra.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.bffa_rahmatsaputra.R;
import com.example.bffa_rahmatsaputra.adapter.GithubAdapter;
import com.example.bffa_rahmatsaputra.models.UsersItem;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView _recyclerView;
    private List<UsersItem> _users = new ArrayList<>();

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.openabout, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.openBrowser:
                Intent browserIntent = new Intent(MainActivity.this, AboutActivity.class);
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(browserIntent);
                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _recyclerView = findViewById(R.id.rv);
        _recyclerView.setHasFixedSize(true);

        _users.addAll(getListGithubUser());
        showRecyclerList();

    }

    public ArrayList<UsersItem> getListGithubUser() {
        String[] dataName = getResources().getStringArray(R.array.name);
        String[] dataUsername = getResources().getStringArray(R.array.username);
        String[] dataCompany = getResources().getStringArray(R.array.company);
        String[] dataLocation = getResources().getStringArray(R.array.location);
        String[] dataFollowing = getResources().getStringArray(R.array.following);
        String[] dataFollowers = getResources().getStringArray(R.array.followers);
        String[] dataRepository = getResources().getStringArray(R.array.repository);
        String[] dataAvatar = getResources().getStringArray(R.array.avatar);

        ArrayList<UsersItem> listUser = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            UsersItem _usersItem = new UsersItem();
            _usersItem.setAvatar(dataAvatar[i]);
            _usersItem.setName(dataName[i]);
            _usersItem.setUsername(dataUsername[i]);
            _usersItem.setCompany(dataCompany[i]);
            _usersItem.setLocation(dataLocation[i]);
            _usersItem.setFollowing(dataFollowing[i]);
            _usersItem.setFollower(dataFollowers[i]);
            _usersItem.setRepository(dataRepository[i]);

            listUser.add(_usersItem);
        }
        return listUser;
    }


    private void showRecyclerList() {
        _recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GithubAdapter varGithubAdapter = new GithubAdapter(_users, this);
        _recyclerView.setAdapter(varGithubAdapter);

        varGithubAdapter.setOnItemClickListener(new GithubAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                UsersItem _userItem = new UsersItem();
                UsersItem varuser = _users.get(position);

                _userItem.setUsername(varuser.getUsername());
                _userItem.setName(varuser.getName());
                _userItem.setAvatar(varuser.getAvatar());
                _userItem.setCompany(varuser.getCompany());
                _userItem.setLocation(varuser.getLocation());
                _userItem.setRepository(varuser.getRepository());
                _userItem.setFollower(varuser.getFollower());
                _userItem.setFollowing(varuser.getFollowing());


                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_GITHUB_USERS, varuser);
                startActivity(intent);


            }
        });

    }

}
