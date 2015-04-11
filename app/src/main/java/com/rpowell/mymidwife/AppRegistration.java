/***************************************************************************
 * COPYRIGHT (C) 2015, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/
package com.rpowell.mymidwife;

import retrofit.Callback;

public class AppRegistration {

    public static void loginUser(String username, String password, Callback<LoginResponseMapper> callback) {

        IAppRegistration loginService = NetworkGlobals.getNonAuthenticatedRestAdapater().create(IAppRegistration.class);
        loginService.loginUser(username, password, callback);
    }

    public static boolean registerUser(String username, String password, String first, String last, String confirm)
    {

        return true;
    }
}
