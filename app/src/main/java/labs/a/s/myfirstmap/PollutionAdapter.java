package labs.a.s.myfirstmap;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PollutionAdapter extends ArrayAdapter<pollution> {

    public PollutionAdapter(Context context, List<pollution> pollution_list){
        super(context,0,pollution_list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate
                    (R.layout.list_item, parent, false);
        }

        pollution currentpollution = getItem(position);

        TextView text = (TextView) listItemView.findViewById(R.id.text);
        String txt = currentpollution.gettext();
        text.setText(txt);

        TextView description1 = (TextView) listItemView.findViewById(R.id.description);
        String des = currentpollution.getdescription();
        description1.setText(des);

        return listItemView;
    }

}

