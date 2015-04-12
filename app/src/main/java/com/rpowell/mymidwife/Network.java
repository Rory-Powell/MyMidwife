/***************************************************************************
 * COPYRIGHT (C) 2015, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/
package com.rpowell.mymidwife;

import retrofit.Callback;

public class Network {

    public static void loginUser(String username, String password,
                                 Callback<LoginResponseMapper> callback) {
        INetwork loginService = NetworkGlobals.getNonAuthenticatedRestAdapater().create(INetwork.class);
        loginService.loginUser(username, password, callback);
    }

    public static void makeBooking(String date,String session, String user,
                                   Callback<BookingResponseMapper> callback) {
        INetwork bookingService = NetworkGlobals.getNonAuthenticatedRestAdapater().create(INetwork.class);
        bookingService.makeBooking(date, session, user, callback);
    }

    public static void addMotherDetails(String username, int height, String waist, String weight, int weeks,
                                        String address, String postcode, String number,
                                        Callback<MotherDetailsResponseMapper> callback) {
        INetwork bookingService = NetworkGlobals.getNonAuthenticatedRestAdapater().create(INetwork.class);
        bookingService.addMotherDetails(username, height, waist, weight, weeks,
                address, postcode, number, callback);
    }
}
