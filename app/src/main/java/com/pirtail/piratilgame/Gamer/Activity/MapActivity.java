package com.pirtail.piratilgame.Gamer.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.maps.MapView;
import com.pirtail.piratilgame.Gamer.Fragment.FragmentUserFrame;
import com.pirtail.piratilgame.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MapActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoiZGFsaXJlemEiLCJhIjoiY2p3eDlub3B2MTJvYzQxbW9jMTdyaGNjcCJ9.oH-jhRGdnAgbJbSw7rEp-Q");
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        defineObjects();

        mapView.onCreate(savedInstanceState);
        mapView.setStyleUrl(Style.MAPBOX_STREETS);

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

        String username = getResources().getString(R.string.username);
        String rank = getResources().getString(R.string.user_rank);
        String dimound_count = getResources().getString(R.string.txt_dimound_count);
        int image = R.drawable.green_bg_empty;
        int charracter = R.drawable.ic_user_charracter;

        FragmentUserFrame fragmentUserFrame = FragmentUserFrame.newInstance(username, rank, dimound_count, image, charracter);
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
}
