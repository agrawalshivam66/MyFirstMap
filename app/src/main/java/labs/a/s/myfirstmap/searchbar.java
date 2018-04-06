package labs.a.s.myfirstmap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;

public class searchbar extends Activity {

    EditText source,destination;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edittext);
        source = (EditText)findViewById(R.id.Source);
        destination = (EditText)findViewById(R.id.Destination) ;


        PlaceAutocompleteFragment SourceautocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.Source_autocomplete_fragment);


        SourceautocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
               LatLng source = place.getLatLng();
            }

            @Override
            public void onError(Status status) {

            }
        });


        PlaceAutocompleteFragment DestinationautocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.Destination_autocomplete_fragment);

        DestinationautocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

               LatLng dest = place.getLatLng();

            }

            @Override
            public void onError(Status status) {

            }
        });
    }
}
