/***************************************************************************
 * COPYRIGHT (C) 2015, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/
package com.rpowell.mymidwife;

import android.app.Application;

import com.rpowell.mymidwife.userObjects.User;

public class ApplicationData extends Application {

    private User user;

    private static ApplicationData instance;

    public static ApplicationData getInstance()
    {
        if(instance == null){
            instance = new ApplicationData();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void voidSession() {
        instance = null;
    }

    public User getCurrentUser() {
        return user;
    }

    public void setCurrentUser(User currentUser) {
        user = currentUser;
    }
}