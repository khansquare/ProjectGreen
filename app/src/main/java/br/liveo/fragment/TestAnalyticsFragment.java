package br.liveo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.liveo.navigationviewpagerliveo.R;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Author       :   Mohsin Khan
 * Designation  :   Android Developer
 * E-mail       :   khan.square@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 14 , 2015
 * Purpose      :   Per test analysis
 * Description  :   Detailed Description...
 */
public class TestAnalyticsFragment extends Fragment {
    private PieChartView chart;
    private PieChartData data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test_analytics, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        chart = (PieChartView) rootView.findViewById(R.id.pieChartAnalytics);
        int numValues = 3;

        List<SliceValue> values = new ArrayList<>();
        SliceValue sliceValueCorrect = new SliceValue(10, Color.parseColor("#48be81"));
        values.add(sliceValueCorrect);
        SliceValue sliceValueIncorrect = new SliceValue(2, Color.parseColor("#F44336"));
        values.add(sliceValueIncorrect);
        SliceValue sliceValueUnattempted = new SliceValue(8, Color.parseColor("#40000000"));
        values.add(sliceValueUnattempted);

        data = new PieChartData(values);
        data.setHasLabels(false);
        data.setHasLabelsOnlyForSelected(true);
        data.setHasLabelsOutside(false);
        data.setHasCenterCircle(true);
        data.setCenterText1("50%");
        data.setCenterText1Color(Color.parseColor("#808080"));
        data.setCenterText1FontSize(70);
        chart.setPieChartData(data);
        chart.animate();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }


}