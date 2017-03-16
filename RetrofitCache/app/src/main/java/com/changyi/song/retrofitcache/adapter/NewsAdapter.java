package com.changyi.song.retrofitcache.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.changyi.song.retrofitcache.R;
import com.changyi.song.retrofitcache.domain.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Song on 2017/3/15.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private List<News.TopStoriesBean> datas = new ArrayList<News.TopStoriesBean>();

    private Context mContext;

    private LayoutInflater mLayoutInflater;

    public NewsAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.base_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.mTvWho.setText(datas.get(position).getTitle());
        Picasso.with(mContext).load(datas.get(position).getImage())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.timg)
                .into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setDatas(List<News.TopStoriesBean>  datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }
}

class NewsViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.img)
    ImageView mImg;
    @BindView(R.id.tv_who)
    TextView mTvWho;
    public NewsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
