package com.dddemo.sumanthmadala.androidapp.dddemo.model;

import com.dddemo.sumanthmadala.androidapp.dddemo.net.ServiceConstants;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sumanthmadala on 3/26/17.
 */

public class RestaurantMenu {

    @SerializedName(ServiceConstants.TAG_ID)
    public String menuId;

    @SerializedName(ServiceConstants.TAG_NAME)
    public String menuName;



}
