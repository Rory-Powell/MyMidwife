package com.rpowell.mymidwife.network;

import com.rpowell.mymidwife.ApplicationData;
import com.rpowell.mymidwife.responseMappers.BookingResponseMapper;
import com.rpowell.mymidwife.responseMappers.MotherDetailsResponseMapper;
import com.rpowell.mymidwife.userObjects.Booking;
import com.rpowell.mymidwife.userObjects.MotherDetails;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by rpowell on 12/04/15.
 */
public class NetworkUtils {

    public static void makeBooking(String date, String session, String note)
    {
        String user = ApplicationData.getInstance().getCurrentUser().getUsername();
        Network.makeBooking(date, session, user, note, new Callback<BookingResponseMapper>() {
            @Override
            public void success(BookingResponseMapper s, Response response) {
                ApplicationData.getInstance()
                        .getCurrentUser()
                        .setBooking(new Booking(s.getDate(), s.getSession()));

                ApplicationData.getInstance()
                        .getCurrentUser()
                        .setMidwife(s.getMidwife());
            }
            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
