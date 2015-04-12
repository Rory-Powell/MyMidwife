package com.rpowell.mymidwife;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rpowell.mymidwife.R;

/**
 * Created by rpowell on 23/03/15.
 */
public class BookingFragment extends Fragment {
    private View containerView;
    protected int res;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        this.containerView = view.findViewById(R.id.bookingDetailsMain);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.booking_details, container, false);
//        return rootView;
        return null;
    }

}
