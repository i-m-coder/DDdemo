package com.dddemo.sumanthmadala.androidapp.dddemo.net;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dddemo.sumanthmadala.androidapp.dddemo.model.Restaurant;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by sumanthmadala on 3/26/17.
 */

public class NetUtils {
    private ServiceUtils serviceUtils;
    private  Retrofit retrofit;

    private  NetUtils() {
          retrofit = new Retrofit.Builder().baseUrl(ServiceConstants.ROOT_URI)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()))
                .build();
        serviceUtils = retrofit.create(ServiceUtils.class);
    }

    private static NetUtils netUtils;

    public  static  NetUtils getInstance(){
        if(netUtils == null) {
            netUtils = new NetUtils();
         }
         return netUtils;
    }

    public Observable<List<Restaurant>> fetchRestaurantsList(String lat, String lng) {
        Observable<List<Restaurant>> observable = serviceUtils.fetchRestaurantsList(lat,lng);
        return observable;
    }


    //URLS   /v2/restaurant/
    static String FETCH_RESTAURANTS = "https://api.doordash.com/v2/restaurant/?lat=37.422740&lng=-122.139956";
    static String FETCH_RESTAURANT_DETAIL = "http://api.demo.doordash.com/v2/restaurant/%@/";


}
