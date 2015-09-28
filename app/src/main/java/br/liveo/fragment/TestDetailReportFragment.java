package br.liveo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import br.liveo.activity.MainActivity;
import br.liveo.adapter.ReportsAdapter;
import br.liveo.model.TestReport;
import br.liveo.navigationviewpagerliveo.R;
/**
 * Author       :   Mohsin Khan
 * Designation  :   Android Developer
 * E-mail       :   khan.square@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 12 , 2015
 * Purpose      :   To display reports of the test
 * Description  :   Detailed Description...
 */
public class TestDetailReportFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test_report, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        generateAllReports(rootView);
        rootView.findViewById(R.id.btnAnalytics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new TestAnalyticsFragment())
                        .addToBackStack(MainActivity.MAIN_FRAGMENT_STACK).commit();
            }
        });
        rootView.findViewById(R.id.btnReview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ReviewPagerFragment())
                        .addToBackStack(MainActivity.MAIN_FRAGMENT_STACK).commit();
            }
        });
        return rootView;
    }

    private void generateAllReports(View rootView) {
        GridView gridViewReports = (GridView) rootView.findViewById(R.id.gridViewReports);
        ArrayList<TestReport> testReports = new ArrayList<>();
        testReports.add(new TestReport("Score",50,25,R.drawable.circular_progress_purple));
        testReports.add(new TestReport("Percentage",50,25,R.drawable.circular_progress_purple));
        testReports.add(new TestReport("Attempted",20,12,R.drawable.circular_progress_purple));
        testReports.add(new TestReport("Correct",12,10,R.drawable.circular_progress_purple));
        gridViewReports.setAdapter(new ReportsAdapter(getActivity(),R.layout.layout_circular_chart,testReports));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

}
