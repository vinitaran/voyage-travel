package com.example.voyage_travel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.voyage_travel.Adapters.PopularAdapter;
import com.example.voyage_travel.Domains.PopularDomain;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    private RecyclerView.Adapter adapterPopular;
    private RecyclerView recyclerViewPopular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initRecyclerView();
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