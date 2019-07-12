package com.pirtail.piratilgame.Gamer.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonArray;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineListener;
import com.mapbox.android.core.location.LocationEnginePriority;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerPlugin;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.CameraMode;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.RenderMode;
import com.pirtail.piratilgame.Class.vollayRequest;
import com.pirtail.piratilgame.Gamer.Fragment.FragmentUserFrame;
import com.pirtail.piratilgame.R;
import com.pirtail.piratilgame.ServerCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, LocationEngineListener, PermissionsListener {

    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.search_bar)
    EditText searchBar;
    AlertDialog.Builder alertDialog;
    LayoutInflater layoutInflater;
    View view;
    AlertDialog dialog;
    @BindView(R.id.ic_menu)
    ImageView icMenu;
    @BindView(R.id.ic_back)
    ImageView icBack;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.ic_cup)
    ImageView icCup;
    Intent intent;
    HashMap<String, String> stringStringHashMap;
    String mobile, token;

    private MapboxMap map;
    private PermissionsManager permissionsManager;
    private LocationEngine locationEngine;
    private LocationLayerPlugin locationLayerPlugin;
    private Location originLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoiZGFsaXJlemEiLCJhIjoiY2p3eDlub3B2MTJvYzQxbW9jMTdyaGNjcCJ9.oH-jhRGdnAgbJbSw7rEp-Q");
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        defineObjects();

        mapView.onCreate(savedInstanceState);
        mapView.setStyleUrl(Style.MAPBOX_STREETS);

        mapView.getMapAsync(this);

        });

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                alertDialog = new AlertDialog.Builder(MapActivity.this);
                layoutInflater = getLayoutInflater();
                view = layoutInflater.inflate(R.layout.search_layout, null);
                alertDialog.setView(view);
                dialog = alertDialog.create();
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void defineObjects() {

        String rank = getResources().getString(R.string.user_rank);
        int image = R.drawable.green_bg_empty;
//        int charracter = R.drawable.ic_user_charracter;
        intent = getIntent();
        int charracter;

        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);

        //get jender:
        String gender = sharedPreferences.getString("gender", "1");
//        gender = intent.getExtras().getString("gender", "1");
        if (gender.equals("0")){
            charracter = R.drawable.ic_girl_charracter;
        } else {
            charracter = R.drawable.ic_user_charracter;
        }

        //get username:
        String username = sharedPreferences.getString("name", "gust");

        //get dimound count:
        String dimound_count = sharedPreferences.getString("dimound_count", "0");

        FragmentUserFrame fragmentUserFrame = FragmentUserFrame.newInstance(username/*, rank*/, dimound_count, image, charracter);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragmentUserFrame)
                .commit();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        map = mapboxMap;
        enableLocation();
    }

    private void enableLocation (){
        if (PermissionsManager.areLocationPermissionsGranted(this)){
            initializeLocationEngine();
            initializeLocationLayer();
        }else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

    @SuppressWarnings("MissingPermission")
    private void initializeLocationEngine(){
        locationEngine = new LocationEngineProvider(this).obtainBestLocationEngineAvailable();
        locationEngine.setPriority(LocationEnginePriority.HIGH_ACCURACY);
        locationEngine.activate();

        Location lastLocation = locationEngine.getLastLocation();
        if (lastLocation != null){
            originLocation = lastLocation;
            setCameraPostion(lastLocation);
        }else {
            locationEngine.addLocationEngineListener(this);
        }
    }

    @SuppressWarnings("MissingPermission")
    private void initializeLocationLayer(){
        locationLayerPlugin = new LocationLayerPlugin(mapView, map, locationEngine);
        locationLayerPlugin.setLocationLayerEnabled(true);
        locationLayerPlugin.setCameraMode(CameraMode.TRACKING);
        locationLayerPlugin.setRenderMode(RenderMode.NORMAL);
    }

    private void setCameraPostion(Location location) {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),
                location.getLongitude()), 13.0));
    }

    @Override
    @SuppressWarnings("MissingPermission")
    public void onConnected() {
        locationEngine.requestLocationUpdates();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null){
            originLocation = location;
            setCameraPostion(location);
        }
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        //Present toast or dialog
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted){
            enableLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    @SuppressWarnings("MissingPermission")
    protected void onStart() {
        super.onStart();
        if (locationEngine != null){
            locationEngine.requestLocationUpdates();
        }
        if (locationLayerPlugin != null){
            locationLayerPlugin.onStart();
        }
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (locationLayerPlugin != null){
            locationLayerPlugin.onStop();
        }
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
        if (locationEngine != null){
            locationEngine.deactivate();
        }
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @OnClick({R.id.ic_menu, R.id.ic_back, R.id.ic_cup, R.id.fragment_container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_menu:
                intent = new Intent(MapActivity.this, MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.ic_back:
                onBackPressed();
                break;
            case R.id.ic_cup:
                intent = new Intent(MapActivity.this, RankingActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_container:
                intent = new Intent(MapActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;
        }
    }

//    @OnClick(R.id.ic_back)
//    public void onViewClicked() {
//        onBackPressed();
//    }

//    @OnClick(R.id.search_bar)
//    public void onViewClicked() {
//        alertDialog=new AlertDialog.Builder(MapActivity.this);
//        layoutInflater= getLayoutInflater();
//        view=layoutInflater.inflate(R.layout.search_layout, null);
//        alertDialog.setView(view);
//        dialog=alertDialog.create();
//        dialog.show();
//    }

    //encrypting token
    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}
