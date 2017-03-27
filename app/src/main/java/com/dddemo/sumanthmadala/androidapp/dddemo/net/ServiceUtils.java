package com.dddemo.sumanthmadala.androidapp.dddemo.net;

import com.dddemo.sumanthmadala.androidapp.dddemo.model.Restaurant;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sumanthmadala on 3/26/17.
 */

public interface ServiceUtils {

    @GET("/v2/restaurant/")
    Observable<List<Restaurant>> fetchRestaurantsList(@Query("lat") String lat, @Query("lng") String lng);

    @GET("/v2/restaurant/{restaurant_id}/")
    Observable<Restaurant> fetchRestaurantDetails(@Path("restaurant_id") String id);

}
