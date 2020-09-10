package com.example.bffa_rahmatsaputra.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.MyViewHolder> {

    private List<UsersItem> _userItem;
    private OnItemClickListener onItemClickListener;
    private Context _context;

    public GithubAdapter(List<UsersItem> usersItems, Context context) {
        this._userItem = usersItems;
        this._context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(_context).inflate(R.layout.main_item, parent, false);

        return new MyViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder _holder, int position) {
        final MyViewHolder holder = _holder;
        UsersItem model = _userItem.get(position);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();
//        Log.d("tamvan", "onBindViewHolder: "+model.getAvatar().replace("res/drawable/","R.drawable.").replace(".png","").replace("//","").trim());

        Glide.with(_context)
                .load(getImage(model.getAvatar().replace("res/drawable/", "").replace(".png", "").replace("//", "").trim()))
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.gambar);
        holder.name.setText(model.getName());
        holder.company.setText(model.getCompany());
        holder.location.setText(model.getLocation());
    }

    @Override
    public int getItemCount() {
        return _userItem.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public int getImage(String imageName) {
        return _context.getResources().getIdentifier(imageName, "drawable", _context.getPackageName());
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClickListener onItemClickListener;

        @BindView(R.id.ivGambar)
        ImageView gambar;

        @BindView(R.id.pbProsesGambar)
        ProgressBar progressBar;

        @BindView(R.id.tvName)
        TextView name;

        @BindView(R.id.tvCompany)
        TextView company;

        @BindView(R.id.tvLocation)
        TextView location;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            itemView.setOnClickListener(this);


            gambar = itemView.findViewById(R.id.ivGambar);
            name = itemView.findViewById(R.id.tvName);
            company = itemView.findViewById(R.id.tvCompany);
            location = itemView.findViewById(R.id.tvLocation);
            progressBar = itemView.findViewById(R.id.pbProsesGambar);

            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

}
