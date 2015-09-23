package br.liveo.adapter;

import android.app.Activity;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.liveo.model.Message;
import br.liveo.navigationviewpagerliveo.R;

/**
 * Author       :   Mohsin Khan
 * Designation  :   Android Developer
 * E-mail       :   khan.square@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 11 , 2015
 * Purpose      :   List Adapter for messages
 * Description  :   Detailed Description...
 */

public class CustomListAdapter extends ArrayAdapter<String> {

    private Activity context;
    private int layoutId;
    private ArrayList<String> titles;
    private ArrayList<String> values;
    private TypedArray icons;

    public CustomListAdapter(Activity context, int layoutId, ArrayList<String> titles, int titleResourceId) {
        super(context, layoutId, titles);
        this.context = context;
        this.layoutId = layoutId;
        this.titles = titles;
    }

    public CustomListAdapter(Activity context, int layoutId, ArrayList<String> titles, int titleResourceId, ArrayList<String> values, int valueResourceId) {
        super(context, layoutId, titles);
        this.context = context;
        this.layoutId = layoutId;
        this.titles = titles;
        this.values = values;
    }

    public CustomListAdapter(Activity context, int layoutId, ArrayList<String> titles, ArrayList<String> values, TypedArray icons) {
        super(context, layoutId, titles);
        this.context = context;
        this.layoutId = layoutId;
        this.titles = titles;
        this.values = values;
        this.icons = icons;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public String getItem(int position) {
        return titles.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layoutId, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (icons != null) {
            viewHolder.txtTitle.setText(titles.get(position));
            viewHolder.txtValue.setText(values.get(position));
            viewHolder.imgIcon.setImageResource(icons.getResourceId(position, 0));
        } else if (values != null) {
            Log.e("GETVIEW", titles.get(position));
            Log.e("GETVIEW", values.get(position));

            viewHolder.txtTitle.setText(titles.get(position));
            viewHolder.txtValue.setText(values.get(position));
        } else {
            viewHolder.txtTitle.setText(titles.get(position));
        }


        return convertView;
    }

    private class ViewHolder {
        private TextView txtTitle;
        private TextView txtValue;
        private ImageView imgIcon;

        public ViewHolder(View convertView) {


            txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            txtValue = (TextView) convertView.findViewById(R.id.txtValue);
            imgIcon = (ImageView) convertView.findViewById(R.id.imgPicture);


        }
    }
}