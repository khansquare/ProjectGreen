package br.liveo.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.liveo.activity.MainActivity;
import br.liveo.navigationviewpagerliveo.R;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;

import static br.liveo.model.TestCategory.ACCESS_KEY;
import static br.liveo.model.TestCategory.LIVE;

public class HomeFragment extends Fragment {


	public final static String[] days = new String[]{"Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun",};
	private LineChartView chartTop;
	private ColumnChartView chartBottom;
	private TextView txtTimer;
	private LineChartData lineData;
	private ColumnChartData columnData;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT ));
		chartTop = (LineChartView) rootView.findViewById(R.id.chart_top);
		txtTimer = (TextView) rootView.findViewById(R.id.txtTimer);

		new CountDownTimer(60000,1000) {

			@Override
			public void onTick(long millisUntilFinished) {

				txtTimer.setText(String.valueOf(
						(Long.parseLong(txtTimer.getText().toString()) - 1)
				));
			}

			@Override
			public void onFinish() {

			}
		}.start();

		generateInitialLineData();
        rootView.findViewById(R.id.btnLiveAtHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(ACCESS_KEY, LIVE);
				bundle.putString("TITLE", ((TextView) rootView.findViewById(R.id.txtTestNameAtHome)).getText().toString());
                Fragment fragment = new TestDetailPagerFragment();
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment)
                        .addToBackStack(MainActivity.MAIN_FRAGMENT_STACK).commit();
            }
        });
		return rootView;		
	}

	private void generateInitialLineData() {
		int numValues = 7;
		List<AxisValue> axisValues = new ArrayList<AxisValue>();
		List<PointValue> values = new ArrayList<PointValue>();
		for (int i = 0; i < numValues; ++i) {
			values.add(new PointValue(i, 0));
			axisValues.add(new AxisValue(i).setLabel(days[i]));
		}
		Line line = new Line(values);
		line.setColor(ChartUtils.COLOR_GREEN).setCubic(true);
		List<Line> lines = new ArrayList<Line>();
		lines.add(line);
		lineData = new LineChartData(lines);
		lineData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
		lineData.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(3));
		chartTop.setLineChartData(lineData);
		// For build-up animation you have to disable viewport recalculation.
		chartTop.setViewportCalculationEnabled(false);

		// And set initial max viewport and current viewport- remember to set viewports after data.
		Viewport v = new Viewport(0, 110, 6, 0);
		chartTop.setMaximumViewport(v);
		chartTop.setCurrentViewport(v);

		chartTop.setZoomType(ZoomType.HORIZONTAL);
		generateLineData(ChartUtils.COLOR_GREEN, 100);
	}

	private void generateLineData(int color, float range) {
		// Cancel last animation if not finished.
		chartTop.cancelDataAnimation();

		// Modify data targets
		Line line = lineData.getLines().get(0);// For this example there is always only one line.
		line.setColor(color);
		for (PointValue value : line.getValues()) {
			// Change target only for Y value.
			value.setTarget(value.getX(), (float) Math.random() * range);
		}
		// Start new data animation with 300ms duration;
		chartTop.startDataAnimation(300);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);		
		inflater.inflate(R.menu.menu_basic, menu);


		menu.findItem(R.id.menu_notify).setVisible(true);
		menu.findItem(R.id.menu_sync).setVisible(true);
  	    

	}	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menu_notify:

			break;				
		
		case R.id.menu_sync:


			break;
		}		
		return true;
	}	


}
