/***************************************************************************
 * COPYRIGHT (C) 2015, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/
package com.rpowell.mymidwife.userObjects;

import java.io.Serializable;

/**
 * Used to map login response data to a Java object.
 */
public class MotherDetails implements Serializable {

    private static final long serialVersionUID = -6119382989755072206L;

    private String address;
    private String postcode;
    private String number;
    private int height;
    private String waist;
    private String weight;
    private int weeks;

    public MotherDetails(String address, String postcode, String number,
                         int height, String waist, String weight, int weeks) {
        this.address = address;
        this.postcode = postcode;
        this.number = number;
        this.height = height;
        this.waist = waist;
        this.weight = weight;
        this.weeks = weeks;
    }
}
