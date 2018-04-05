package labs.a.s.myfirstmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap m_map;
    Boolean mapReady=false;
    Button map,hybrid,satelite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialling buttons
        map=(Button)findViewById(R.id.map);
        satelite=(Button)findViewById(R.id.satellite);
        hybrid=(Button)findViewById(R.id.hybrid);
        //Setting map type in OnClickListiners
        //setting map onclick listiner
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady)
                {
                    m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        });

        //setting satelite onclick listiner
        satelite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady)
                {
                    m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            }
        });

    //setting hybrid onclick listiner
    hybrid.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(mapReady)
            {
                m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        }
    });
        //updating fragment
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady=true;
        m_map=googleMap;
        LatLng place = new LatLng(40.7484,-73.9857);
        CameraPosition target = CameraPosition.builder().target(place).zoom(14).build();
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
    }
}
