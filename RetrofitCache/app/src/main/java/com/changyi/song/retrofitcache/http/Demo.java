package com.changyi.song.retrofitcache.http;


import com.changyi.song.retrofitcache.domain.News;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;


public interface Demo {
    @Headers("Cache-Control: public, max-age=3600)")
    @GET("latest")
    Observable<News> getNews();
}
