/***************************************************************************
 * COPYRIGHT (C) 2015, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/
package com.rpowell.mymidwife;

import retrofit.http.Query;

public class MotherDetailsResponseMapper implements java.io.Serializable {

    private static final long serialVersionUID = -8966655440381065205L;

    private int height;
    private String waist;
    private String weight;
    private int weeks;
    private String address;
    private String postcode;
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

}
