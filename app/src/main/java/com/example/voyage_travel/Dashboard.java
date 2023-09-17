package com.example.voyage_travel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voyage_travel.Adapters.PopularAdapter;
import com.example.voyage_travel.Domains.PopularDomain;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Dashboard extends AppCompatActivity {

    FusedLocationProviderClient fusedLocationProviderClient;
    TextView city;
    private final static int REQUEST_CODE=100;

    private RecyclerView.Adapter adapterPopular;
    private RecyclerView recyclerViewPopular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initRecyclerView();
        city = findViewById(R.id.editTextText);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();

    }

    private void getLastLocation() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null){
                        Geocoder geocoder = new Geocoder(Dashboard.this, Locale.getDefault());
                        List<Address> addresses = null;
                        try {
                            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            city.setText(addresses.get(0).getAddressLine(0));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
            });
        }else{
            askPermissions();
        }
    }

    private void askPermissions() {
        ActivityCompat.requestPermissions(Dashboard.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }else {
                Toast.makeText(this, "Permission required", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("Hohenzollern Castle, Mount Hohenzollern","Germany","Hamburg","Hamburg","castle1", "12"));
        items.add(new PopularDomain("castle2","Germany","","","castle2","12"));
        items.add(new PopularDomain("castle1","Ireland","","","castle3", "12"));
        recyclerViewPopular=findViewById(R.id.view_pop);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterPopular = new PopularAdapter(items);
        recyclerViewPopular.setAdapter(adapterPopular);

        ArrayList<PopularDomain> items2 = new ArrayList<>();
        items2.add(new PopularDomain("castle1","Church1","","","church1", "12"));
        items2.add(new PopularDomain("castle2","Church2","","","church2", "12"));
        items2.add(new PopularDomain("castle1","Church3","","","church3", "12"));
        recyclerViewPopular=findViewById(R.id.view_church);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterPopular = new PopularAdapter(items2);
        recyclerViewPopular.setAdapter(adapterPopular);

        ArrayList<PopularDomain> items3 = new ArrayList<>();
        items3.add(new PopularDomain("Cala Granadella, Javea, Costa Blanca","Spain","","","beach1", "12"));
        items3.add(new PopularDomain("Sykia Beach, Milos Island","Greece","","","beach2", "12"));
        items3.add(new PopularDomain("Monterosso al Mare, Liguria","Italy","","","beach3", "12"));
        recyclerViewPopular=findViewById(R.id.view_beach);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterPopular = new PopularAdapter(items3);
        recyclerViewPopular.setAdapter(adapterPopular);

        ArrayList<PopularDomain> items4 = new ArrayList<>();
        items4.add(new PopularDomain("castle1","Church1","","","tourist1", "12"));
        items4.add(new PopularDomain("castle2","Church2","","","tourist2", "12"));
        items4.add(new PopularDomain("castle1","Church3","","","tourist3", "12"));
        recyclerViewPopular=findViewById(R.id.view_tourist);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterPopular = new PopularAdapter(items4);
        recyclerViewPopular.setAdapter(adapterPopular);

    }
}
