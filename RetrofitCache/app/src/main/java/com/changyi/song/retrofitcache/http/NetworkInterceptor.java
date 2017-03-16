package com.changyi.song.retrofitcache.http;




import android.util.Log;


import com.changyi.song.retrofitcache.utill.ToolsUtil;
import com.changyi.song.retrofitcache.utill.UfunApplication;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (ToolsUtil.isNetworkAvailable(UfunApplication.getContext())){
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();
            Log.d("MM","from net");
        }else{
            request=request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            Log.d("MM","from cache");

        }

        Response response =   chain.proceed(request);
        if (ToolsUtil.isNetworkAvailable(UfunApplication.getContext())){
            int maxAge = 60 * 60*5;
            // 有网络时 设置缓存超时时间5分钟

            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .build();
        }else{
            // 无网络时，设置超时为一小时
            int maxStale = 60 * 60 * 60 ;
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
            return response;

    }
}
