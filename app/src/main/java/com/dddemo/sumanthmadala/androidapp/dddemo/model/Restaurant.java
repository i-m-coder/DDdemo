package com.dddemo.sumanthmadala.androidapp.dddemo.model;

import com.dddemo.sumanthmadala.androidapp.dddemo.net.ServiceConstants;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by sumanthmadala on 3/26/17.
 */

public class Restaurant {


    @SerializedName(ServiceConstants.TAG_ID)
    public long id;

    @SerializedName(ServiceConstants.TAG_NAME)
    public String name;

    @SerializedName(ServiceConstants.TAG_TRAVELTIME)
    public String travelTime;

    @SerializedName(ServiceConstants.TAG_ADDRESS)
    public String address;

    @SerializedName(ServiceConstants.TAG_MENUS)
    public List<RestaurantMenu> menus;

    @SerializedName(ServiceConstants.TAG_IMAGE_URL)
    public String logo_url;

    @SerializedName(ServiceConstants.TAG_DESCRIPTION)
    public String description;



    public Restaurant(long id, String name, String time,String address, String logo_url, String description, List<RestaurantMenu> menus){
        this.id = id;
        this.name = name;
        this.travelTime = time;
        this.address = address;
        this.logo_url = logo_url;
        this.description = description;
        this.menus = menus;
    }
}
