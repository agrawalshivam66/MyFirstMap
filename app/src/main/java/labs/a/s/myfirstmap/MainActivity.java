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
    static final CameraPosition newyork = CameraPosition.builder()
            .target(new LatLng(40.7484,-73.9857)).bearing(0).tilt(45).zoom(21).build();

    static final CameraPosition random1 = CameraPosition.builder()
            .target(new LatLng(47.6204,-122.3491)).bearing(0).tilt(45).zoom(21).build();

    static final CameraPosition random2 = CameraPosition.builder()
            .target(new LatLng(53.3478,-6.2597)).bearing(0).tilt(45).zoom(21).build();

    static final CameraPosition random3 = CameraPosition.builder()
            .target(new LatLng(35.6895,139.6917)).bearing(0).tilt(45).zoom(21).build();

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
                    flyto(random3);
                }
            }
        });

        //setting satelite onclick listiner
        satelite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady)
                {
                  flyto(random2);
                }
            }
        });

    //setting hybrid onclick listiner
    hybrid.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(mapReady)
            {
               flyto(random1);
            }
        }
    });

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    private void flyto(CameraPosition target) {
        //to fly animation for 10 seconds to target place
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target),10000,null);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady=true;
        m_map=googleMap;
        LatLng place = new LatLng(40.7484,-73.9857);
        CameraPosition target = CameraPosition.builder().target(place).zoom(14).build();
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
            m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    }
}
