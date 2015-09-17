package br.liveo.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.liveo.model.Test;
import br.liveo.model.TestCategory;
import br.liveo.navigationviewpagerliveo.R;

/**
 * Author       :   Mohsin Khan
 * Designation  :   Android Developer
 * E-mail       :   khan.square@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 11 , 2015
 * Purpose      :   List Adapter for test lists
 * Description  :   Detailed Description...
 */

public class TestListAdapter extends ArrayAdapter<Test> {

    private Activity context;
    private int layoutId;
    private ArrayList<Test> tests;

    private String[] months;

    public TestListAdapter(Activity context, int layoutId, ArrayList<Test> tests) {
        super(context, layoutId, tests);
        this.context = context;
        this.layoutId = layoutId;
        this.tests = tests;
        this.months = context.getResources().getStringArray(R.array.custom_months);
    }
    @Override
    public int getCount() {
        return tests.size();
    }
    @Override
    public Test getItem(int position) {
        return tests.get(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layoutId, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtTitle.setText(tests.get(position).getTitle());
        viewHolder.txtMonth.setText(months[((int) tests.get(position).getDate().getMonth())-1]);
        viewHolder.txtDate.setText(String.valueOf(tests.get(position).getDate().getDate()));
        viewHolder.txtDuration.setText(tests.get(position).getDuration());
        return convertView;
    }

    private class ViewHolder {
        private TextView txtTitle;
        private TextView txtMonth;
        private TextView txtDate;
        private TextView txtDuration;

        public ViewHolder(View convertView) {
            txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            txtMonth = (TextView) convertView.findViewById(R.id.txtMonth);
            txtDate = (TextView) convertView.findViewById(R.id.txtDate);
            txtDuration = (TextView) convertView.findViewById(R.id.txtDuration);
            if (tests.get(0).getCategory() == TestCategory.ATTEMPTED)
            txtMonth.setBackgroundResource(R.drawable.shape_month_green);
        }
    }
}