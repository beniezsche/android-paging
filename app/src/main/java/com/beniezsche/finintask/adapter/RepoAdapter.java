package com.beniezsche.finintask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.beniezsche.finintask.R;
import com.beniezsche.finintask.model.Repo;
import com.bumptech.glide.Glide;

public class RepoAdapter extends PagedListAdapter<Repo,RepoAdapter.RepoVH> {

    private Context context;

    public RepoAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }


    @NonNull
    @Override
    public RepoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_item,parent,false);
        return new RepoVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoVH holder, int position) {

        Repo repo = getItem(position);

        holder.firstName.setText(repo.getFirst_name());
        holder.lastName.setText(repo.getLast_name());
        holder.email.setText(repo.getEmail());

        Glide.with(holder.itemView.getContext())
                .load(repo.getAvatar())
                .circleCrop()
                .into(holder.avatar);

    }

    private static DiffUtil.ItemCallback<Repo> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Repo>() {
                @Override
                public boolean areItemsTheSame(Repo oldItem, Repo newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(Repo oldItem, Repo newItem) {
                    return oldItem.equals(newItem);
                }
            };



    class RepoVH extends RecyclerView.ViewHolder{

        TextView firstName,lastName,email;
        ImageView avatar;

        public RepoVH(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.firstName);
            lastName = itemView.findViewById(R.id.lastName);
            email = itemView.findViewById(R.id.email);

            avatar = itemView.findViewById(R.id.avatar);
        }
    }
}
