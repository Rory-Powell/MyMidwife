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

    public void makeBooking(String date, String session)
    {
        String user = ApplicationData.getInstance().getCurrentUser().getUsername();
        Network.makeBooking(date, session, user, new Callback<BookingResponseMapper>() {
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

    public void addMotherDetails(int height, String waist, String weight, int weeks, String postcode)
    {
        Network.addMotherDetails(height, waist, weight, weeks, postcode,
                new Callback<MotherDetailsResponseMapper>() {
                    @Override
                    public void success(MotherDetailsResponseMapper s, Response response) {
                        MotherDetails motherDetails =
                                new MotherDetails(s.getHeight(), s.getWaist(), s.getWeight(), s.getWeeks(), s.getPostcode());

                        ApplicationData.getInstance()
                                .getCurrentUser()
                                .setMotherDetails(motherDetails);
                    }
                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
    }
}
