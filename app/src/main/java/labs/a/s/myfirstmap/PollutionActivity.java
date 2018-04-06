package labs.a.s.myfirstmap;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PollutionActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<pollution>> {



    private  String  pollutionurl="http://api.airpollutionapi.com/1.0/aqi?lat=28.6364&lon=77.2928&APPID=vrlrhcapur72htaf5v1ioh8sno";

    private PollutionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pollution);


        ListView pollution_list_view = (ListView) findViewById(R.id.list);

        mAdapter = new PollutionAdapter(this, new ArrayList<pollution>());

        pollution_list_view.setAdapter(mAdapter);


        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);


        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected())

        {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(1, null, this);
        }
    }


    @Override
    public Loader<List<pollution>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new PollutionLoader(this, pollutionurl);
    }

    @Override
    public void onLoadFinished(Loader<List<pollution>> loader, List<pollution> pollutionList) {
        // Clear the adapter of previous pollution data
        mAdapter.clear();

        if (pollutionList != null && !pollutionList.isEmpty()) {
            mAdapter.addAll(pollutionList);
            pollution p = pollutionList.get(0);
            String text = p.gettext();
            String des = p.getdescription();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<pollution>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}
