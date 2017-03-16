package com.changyi.song.retrofitcache;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.changyi.song.retrofitcache.adapter.NewsAdapter;
import com.changyi.song.retrofitcache.domain.News;
import com.changyi.song.retrofitcache.http.Network;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mRecyclerView)
    RecyclerView mMRecyclerView;
    @BindView(R.id.activity_main)
    RelativeLayout mActivityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        show();
    }

    private void show() {
       final NewsAdapter adapter = new NewsAdapter(this);
        mMRecyclerView.setAdapter(adapter);
        mMRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Network.getDemo().getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<News>() {
                               @Override
                               public void onCompleted() {
                                   Toast.makeText(MainActivity.this,"onCompleted", Toast.LENGTH_SHORT).show();

                               }

                               @Override
                               public void onError(Throwable e) {
                                   Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                               }

                               @Override
                               public void onNext(News news) {
                                   adapter.setDatas(news.getTop_stories());
                               }
                           }
                );

    }
}
