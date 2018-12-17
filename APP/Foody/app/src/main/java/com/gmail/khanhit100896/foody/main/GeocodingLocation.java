package com.gmail.khanhit100896.foody.main;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeocodingLocation {
    private static final String TAG = "GeocodingLocation";

    /*public static void getAddressFromLocation(final String locationAddress,
                                              final Context context, final Handler handler) {
        final List<String> res = new ArrayList<>();
        Thread thread = new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String result = null;
                try {
                    List addressList = geocoder.getFromLocationName(locationAddress, 1);
                    if (addressList != null && addressList.size() > 0) {
                        Address address = (Address) addressList.get(0);
                        StringBuilder sb = new StringBuilder();
                        sb.append(address.getLatitude()).append("\n");
                        res.add( String.valueOf(address.getLatitude()));
                        sb.append(address.getLongitude()).append("\n");
                        res.add( String.valueOf(address.getLongitude()));
                        result = sb.toString();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Unable to connect to Geocoder", e);
                } finally {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    if (result != null) {
                        message.what = 1;
                        Bundle bundle = new Bundle();
//                        result = "Address: " + locationAddress +
//                                "\n\nLatitude and Longitude :\n" + result;
                        bundle.putStringArrayList("address", (ArrayList<String>) res);
                        message.setData(bundle);
                    } else {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        result = "Address: " + locationAddress +
                                "\n Unable to get Latitude and Longitude for this address location.";
                        bundle.putString("address", result);
                        message.setData(bundle);
                    }
                    message.sendToTarget();
                }
            }
        };
        thread.start();
    }*/

    /*
     *Hàm lấy vĩ độ theo địa chỉ
     */
    public static Double getLatitude(final String locationAddress,
                                     final Context context){
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List addressList = geocoder.getFromLocationName(locationAddress, 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = (Address) addressList.get(0);
                return address.getLatitude();
            }
        } catch (IOException e) {
            Log.e(TAG, "Unable to connect to Geocoder", e);
        }
        return 0.0;
    }

    /*
     *Hàm lấy kinh độ theo địa chỉ
     */
    public static Double getLongitude(final String locationAddress,
                                     final Context context){
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List addressList = geocoder.getFromLocationName(locationAddress, 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = (Address) addressList.get(0);
                return address.getLongitude();
            }
        } catch (IOException e) {
            Log.e(TAG, "Unable to connect to Geocoder", e);
        }
        return 0.0;
    }

    /*
     *Hàm tính khoảng cách giữa 2 điễm
     */
    public static double distance(Point point1, Point point2){

        double dLongitude  = point2.getLongitude() - point1.getLongitude();
        double dLatitude = point2.getLatitude() - point1.getLatitude();

        return Math.sqrt(Math.pow(dLatitude,2) + Math.pow(dLongitude,2));
    }
}
