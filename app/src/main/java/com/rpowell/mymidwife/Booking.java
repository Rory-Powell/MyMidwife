/***************************************************************************
 * COPYRIGHT (C) 2015, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/
package com.rpowell.mymidwife;

import java.io.Serializable;

/**
 * Used to map login response data to a Java object.
 */
public class Booking implements Serializable {

    private static final long serialVersionUID = -6119382989755072206L;

    public Booking(String date, String session)
    {
        this.date = date;
        this.session = session;
    }

    private String date;
    private String session;
    private String user;


}
