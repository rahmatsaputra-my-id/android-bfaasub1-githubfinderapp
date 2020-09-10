package com.example.bffa_rahmatsaputra.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.bffa_rahmatsaputra.R;
import com.example.bffa_rahmatsaputra.models.UsersItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_GITHUB_USERS = "extra_github_users";
    private static Context _context;
    private TextView tv_name_detail, tv_following_detail, tv_follower_detail, tv_repository_detail, tv_company_detail, tv_location_detail;
    private ImageView iv_gambar_detail;
    private ProgressBar pbProgressBar;


//    @BindView(R.id.gambarDetail)
//    ImageView iv_gambar_detail;
//
//    @BindView(R.id.tvNameDetail)
//    TextView tv_name_detail;
//
//    @BindView(R.id.tvFollowingDetail)
//    TextView tv_following_detail;
//
//    @BindView(R.id.tvFollowerDetail)
//    TextView tv_follower_detail;
//
//    @BindView(R.id.tvRepositoryDetail)
//    TextView tv_repository_detail;
//
//    @BindView(R.id.tvCompanyDetail)
//    TextView tv_company_detail;
//
//    @BindView(R.id.tvLocationDetail)
//    TextView tv_location_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        _context = getApplicationContext();

        iv_gambar_detail = findViewById(R.id.gambarDetail);
        tv_name_detail = findViewById(R.id.tvNameDetail);
        tv_following_detail = findViewById(R.id.tvFollowingDetail);
        tv_follower_detail = findViewById(R.id.tvFollowerDetail);
        tv_repository_detail = findViewById(R.id.tvRepositoryDetail);
        tv_company_detail = findViewById(R.id.tvCompanyDetail);
        tv_location_detail = findViewById(R.id.tvLocationDetail);
        pbProgressBar = findViewById(R.id.proses_gmbr);

        UsersItem _userItem = getIntent().getParcelableExtra(EXTRA_GITHUB_USERS);

//        Log.d("tamvan", "onCreate: "+_userItem.getName());
        String getAvatar = _userItem.getAvatar();
        String getName = _userItem.getName();
        String getUserName = _userItem.getUsername();
        String getCompany = _userItem.getCompany();
        String getLocation = _userItem.getLocation();
        String getFollower = _userItem.getFollower();
        String getFollowing = _userItem.getFollowing();
        String getRepository = _userItem.getRepository();

        getSupportActionBar().setTitle(getUserName);


//        Log.d("tamvan", "onCreate: "+" "+getName+" "+getCompany+" "+getLocation+" "+getFollower+" "+getFollowing+" "+getRepository);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.override(400, 400);
//        Log.d("tamvan", "onBindViewHolder: "+getAvatar.replace("res/drawable/","R.drawable.").replace(".png","").replace("//","").trim());

        Glide.with(_context)
                .load(getImage(getAvatar.replace("res/drawable/", "").replace(".png", "").replace("//", "").trim()))
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        pbProgressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        pbProgressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(iv_gambar_detail);

        tv_name_detail.setText(getName);
        tv_following_detail.setText(getFollowing);
        tv_follower_detail.setText(getFollower);
        tv_repository_detail.setText(getRepository);
        tv_company_detail.setText(getCompany);
        tv_location_detail.setText(getLocation);

    }

    public int getImage(String imageName) {
        return _context.getResources().getIdentifier(imageName, "drawable", _context.getPackageName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
