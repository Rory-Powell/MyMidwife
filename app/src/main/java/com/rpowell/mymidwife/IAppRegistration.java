/***************************************************************************
 * COPYRIGHT (C) 2015, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/
package com.rpowell.mymidwife;

import retrofit.Callback;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

public interface IAppRegistration {

    @POST("/api/login")
    @Headers({ "Content-Type:application/json" })
    public void loginUser(@Query(value="username", encodeValue = true) String username,
                          @Query(value="password", encodeValue = true) String password,
                          Callback<LoginResponseMapper> cb);

    @Headers({ "Accept: application/json", "Content-Type:application/json" })
    @POST("/api/1/registration/createUser")
    public void registerUser(String username, String password, String first, String last, String confirm);
}
