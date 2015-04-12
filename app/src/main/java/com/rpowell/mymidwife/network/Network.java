/***************************************************************************
 * COPYRIGHT (C) 2015, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/
package com.rpowell.mymidwife.network;

import com.rpowell.mymidwife.responseMappers.BookingResponseMapper;
import com.rpowell.mymidwife.responseMappers.LoginResponseMapper;
import com.rpowell.mymidwife.responseMappers.MotherDetailsResponseMapper;

import retrofit.Callback;

public class Network {

    public static void loginUser(String username, String password,
                                 Callback<LoginResponseMapper> callback) {
        INetwork loginService = NetworkGlobals.getNonAuthenticatedRestAdapater().create(INetwork.class);
        loginService.loginUser(username, password, callback);
    }

    public static void makeBooking(String date,String session, String user, String note,
                                   Callback<BookingResponseMapper> callback) {
        INetwork bookingService = NetworkGlobals.getNonAuthenticatedRestAdapater().create(INetwork.class);
        bookingService.makeBooking(date, session, user, note, callback);
    }

    public static void addMotherDetails(int height, String waist, String weight,
                                        int weeks, String postcode, String ailement,
                                        Callback<MotherDetailsResponseMapper> callback) {
        INetwork motherDetailsService = NetworkGlobals.getNonAuthenticatedRestAdapater().create(INetwork.class);
        motherDetailsService.addMotherDetails(height, waist, weight, weeks, postcode, ailement, callback);
    }
}
