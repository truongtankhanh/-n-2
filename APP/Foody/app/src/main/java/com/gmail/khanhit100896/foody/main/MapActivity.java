package com.gmail.khanhit100896.foody.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.gmail.khanhit100896.foody.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    /*
     * Khai báo biến cần thiết
     */
    protected Toolbar app_bar;
    protected ImageView img_back;
    protected Intent intent;
    protected MapFragment mapFragment;
    //protected Button btnChiDuong;

    protected String context;
    protected String name;
    protected String image;
    protected String address;
    protected double latitude;
    protected double longitude;

    protected LocationManager locationManager;
    protected double latitudeCurrent;
    protected double longitudeCurrent;
    protected String country;
    /*
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        /*
         * Giải thuật lấy kinh độ vĩ độ hiện tại
         */
        locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(),"Vui lòng bật GPS.",Toast.LENGTH_SHORT).show();
        }

        final Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        // Gọi hàm lấy kinh độ vĩ độ tại nơi bạn đang đứng
        onLocationChanged(location);

        // Gọi hàm lấy thông tin nơi bạn đang đứng
        getLocationInfo();
        /*
         */

        // Gọi hàm ánh xạ
        init();

//        this.btnChiDuong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri gmmIntentUri = Uri.parse("google.navigation:q="+ address + ","+ country);
////                Uri gmmIntentUri = Uri.parse("geo:"+latitudeCurrent+","+longitudeCurrent+"" +
////                        "?q="+latitude+","+longitude+"("+country+"+"+address+")");
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                mapIntent.setPackage("com.google.android.apps.maps");
//                if (mapIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(mapIntent);
//                }
//            }
//        });
    }

    /*
     * Hàm ánh xạ
     */
    private void init() {
        this.mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFrame);
        this.mapFragment.getMapAsync(this);
        this.app_bar     = findViewById(R.id.app_bar);
        this.img_back    = findViewById(R.id.img_back);
        //this.btnChiDuong = findViewById(R.id.btnChiDuong);

        intent = getIntent();
        assert intent != null;
        context = intent.getExtras().getString("context");
        name = Objects.requireNonNull(intent.getExtras()).getString("name");
        image = intent.getExtras().getString("image");
        address = intent.getExtras().getString("address");
        latitude = Double.parseDouble(intent.getExtras().getString("latitude"));
        longitude = Double.parseDouble(intent.getExtras().getString("longitude"));

        setSupportActionBar(this.app_bar);
        this.app_bar.setTitle(name);

        this.img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(getApplicationContext(),Class.forName(context).getClass()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
    }

    /*
     * Hàm lấy kinh độ vĩ độ tại nơi bạn đang đứng
     */
    private void onLocationChanged(Location location) {
        latitudeCurrent = location.getLatitude();
        longitudeCurrent = location.getLongitude();
        //Toast.makeText(getApplicationContext(),latitudeCurrent + " - " + longitudeCurrent,Toast.LENGTH_SHORT).show();
    }

    /*
     * Hàm lấy thông tin nơi bạn đang đứng
     */
    private void getLocationInfo(){
        Geocoder geocoder = new Geocoder(getApplicationContext());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitudeCurrent,longitudeCurrent,1);
            country = addresses.get(0).getAddressLine(0);
            Toast.makeText(getApplicationContext(),country,Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Hàm đánh dấu vị trí trên map
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(latitude, longitude);
//        MarkerOptions option = new MarkerOptions();
//        option.position(sydney);
//        option.title(name);
//        option.snippet(address);
//
//        Marker marker = googleMap.addMarker(option);
//        marker.showInfoWindow();
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title(name)
                .snippet(address)).showInfoWindow();
        googleMap.setBuildingsEnabled(true);
        googleMap.setIndoorEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,18));
    }
}
