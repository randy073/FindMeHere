package com.randy073.findmehere;

/**
 * Created by randyyang on 7/29/16.
 */
public class UserLocation {
    private String uId;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    private double lat;
    private double lng;

    public UserLocation(){

    }

    public UserLocation(String uid, double lat, double lng){
        this.uId = uid;
        this.lat = lat;
        this.lng = lng;
    }
}
