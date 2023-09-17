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
        items.add(new PopularDomain("Neuschwanstein Castle","Germany","Neuschwanstein Castle is an iconic fairytale-like castle built by King Ludwig II of Bavaria in the 19th century. It is nestled in the Bavarian Alps and is famous for its stunning architecture and picturesque surroundings.The castle's design was inspired by romanticized medieval themes and it served as the inspiration for Disneyland's Sleeping Beauty Castle.","","castle2","13"));
        items.add(new PopularDomain("Pena Palace, Sintra","Portugal","Pena Palace is a colorful and eclectic Romanticist castle situated on a hilltop in the Sintra Mountains. It's known for its vibrant yellow and red façade, blending various architectural styles, including Moorish and Gothic elements. The palace offers stunning panoramic views of the surrounding landscape and is surrounded by lush gardens.","","castle3", "13.9"));
        items.add(new PopularDomain("Hohenzollern Castle, Mount Hohenzollern","Germany","Hohenzollern Castle is a stunning hilltop fortress with a rich history, dating back to the 11th century. It offers breathtaking views of the surrounding landscape and is an excellent example of German castle architecture. The castle has been partially rebuilt and restored over the years and houses a museum showcasing its history and artifacts.","Hamburg","castle1", "15"));

        recyclerViewPopular=findViewById(R.id.view_pop);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterPopular = new PopularAdapter(items);
        recyclerViewPopular.setAdapter(adapterPopular);

        ArrayList<PopularDomain> items2 = new ArrayList<>();
        items2.add(new PopularDomain("Duomo di Milano, Milan","Italy","The Milan Cathedral, often referred to as the Duomo, is a magnificent example of Gothic architecture. It is one of the largest cathedrals in the world and took nearly six centuries to complete. The cathedral is adorned with intricate spires, statues, and stunning stained glass windows. Visitors can climb to the rooftop for panoramic views of Milan.","","church1", "10"));
        items2.add(new PopularDomain("Cologne Cathedral, Cologne","Germany","The Cologne Cathedral is a masterpiece of High Gothic architecture and is a UNESCO World Heritage site. It is known for its immense size and the intricacy of its design. The cathedral houses many art treasures and religious relics. Visitors can climb the south tower for panoramic views of the city and the Rhine River.","","church2", "9"));
        items2.add(new PopularDomain("St. Peter's Basilica, Vatican City","Italy","St. Peter's Basilica is one of the most iconic churches in the world and the centerpiece of Vatican City. It is renowned for its Renaissance and Baroque architecture, designed by architects like Michelangelo and Gian Lorenzo Bernini. The basilica houses numerous important works of art, including Michelangelo's famous sculpture, the Pietà.","","church3", "49"));
        recyclerViewPopular=findViewById(R.id.view_church);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterPopular = new PopularAdapter(items2);
        recyclerViewPopular.setAdapter(adapterPopular);

        ArrayList<PopularDomain> items3 = new ArrayList<>();
        items3.add(new PopularDomain("Cala Granadella, Javea, Costa Blanca","Spain","Cala Granadella is known for its crystal-clear waters and picturesque surroundings. It's a popular spot for snorkeling, swimming, and sunbathing. The cove is surrounded by rugged cliffs covered in lush vegetation, making it a beautiful natural setting.","","beach1", "free"));
        items3.add(new PopularDomain("Sykia Beach, Milos Island","Greece","Sykia Beach is renowned for its unique geological formations, including volcanic rock formations and colorful cliffs. The beach is known for its clear waters and tranquil atmosphere, making it a great place for relaxation and swimming. Milos Island is also famous for its beautiful beaches, each with its own distinct charm.","","beach2", "free"));
        items3.add(new PopularDomain("Monterosso al Mare, Liguria","Italy","Monterosso al Mare is known for its stunning coastal views and its beautiful old town with colorful houses. It's the largest of the Cinque Terre villages and offers a mix of historical charm and seaside beauty. Visitors can explore the narrow streets, relax on the beach, and savor delicious Italian cuisine in local restaurants.","","beach3", "free"));
        recyclerViewPopular=findViewById(R.id.view_beach);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterPopular = new PopularAdapter(items3);
        recyclerViewPopular.setAdapter(adapterPopular);

        ArrayList<PopularDomain> items4 = new ArrayList<>();
        items4.add(new PopularDomain("Eiffel Tower, Paris","France","The Eiffel Tower is one of the most iconic landmarks in the world. Built for the 1889 World's Fair, it stands at 324 meters (1,063 feet) tall and offers breathtaking panoramic views of Paris from its observation decks. The iron lattice structure is an engineering marvel and an enduring symbol of France.","Mona Lisa (La Gioconda),Venus de Milo","tourist1", "17"));
        items4.add(new PopularDomain("Leaning Tower of Pisa","Italy","The Leaning Tower of Pisa is renowned for its unintended tilt, which is the result of unstable foundation soil. Despite its famous lean, the tower is a beautiful example of Romanesque architecture. Visitors can climb to the top for panoramic views and marvel at the tower's unique design.","","tourist2", "25"));
        items4.add(new PopularDomain("Louvre Museum, Paris,","France","The Louvre is one of the world's largest and most famous art museums. It's housed in a historic palace and is known for its incredible art collection, including the Mona Lisa and the Venus de Milo. The museum's architecture itself is a work of art, with the glass pyramid entrance being an iconic feature.","","tourist3", "17"));
        recyclerViewPopular=findViewById(R.id.view_tourist);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterPopular = new PopularAdapter(items4);
        recyclerViewPopular.setAdapter(adapterPopular);

    }
}
