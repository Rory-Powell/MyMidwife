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
public class User implements Serializable {

    private static final long serialVersionUID = -6119382989755072206L;

    private String username;
    private String password;
    private String privileges;
    private String firstname;
    private Booking booking;
    private MotherDetails motherDetails;
    private Midwife midwife;

    public Midwife getMidwife() {
        return midwife;
    }

    public void setMidwife(Midwife midwife) {
        this.midwife = midwife;
    }

    public User(String username, String password, String privileges, String firstname)
    {
        this.username = username;
        this.password = password;
        this.privileges = privileges;
        this.firstname = firstname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public MotherDetails getMotherDetails() {
        return motherDetails;
    }

    public void setMotherDetails(MotherDetails motherDetails) {
        this.motherDetails = motherDetails;
    }
}
