package labs.a.s.myfirstmap;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class PollutionLoader extends AsyncTaskLoader<List<pollution>> {

        /** Query URL */
        private String mUrl;

        /**

         */
        public PollutionLoader(Context context, String url) {
            super(context);
            mUrl = url;
        }

        @Override
        protected void onStartLoading() {
            forceLoad();
        }

        /**
         * This is on a background thread.
         */
        @Override
        public List<pollution> loadInBackground() {
            if (mUrl == null) {
                return null;
            }

            // Perform the network request, parse the response, and extract a list of pollution
            List<pollution> pollutionlist = QueryUtils.fetchpollutionData(mUrl);
            return pollutionlist;
        }
    }



