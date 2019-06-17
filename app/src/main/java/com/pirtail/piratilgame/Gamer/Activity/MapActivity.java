package com.pirtail.piratilgame.Gamer.Activity;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.maps.MapView;
import com.pirtail.piratilgame.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MapActivity extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoiZGFsaXJlemEiLCJhIjoiY2p3eDlub3B2MTJvYzQxbW9jMTdyaGNjcCJ9.oH-jhRGdnAgbJbSw7rEp-Q");
        setContentView(R.layout.activity_map);
        DefineObjects();

        mapView.onCreate(savedInstanceState);
        mapView.setStyleUrl(Style.MAPBOX_STREETS);

//        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
//        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE);
    }

    private void DefineObjects() {
        mapView= (MapView)findViewById(R.id.mapView);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
