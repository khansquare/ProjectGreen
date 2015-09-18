package br.liveo.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import br.liveo.model.TestReport;
import br.liveo.navigationviewpagerliveo.R;
import br.liveo.util.GeneralUtils;

@SuppressWarnings("deprecation")
public class ReportsAdapter extends BaseAdapter {
	private int layoutId;
    private int cellWidth;
	private Context context;
	private ArrayList<TestReport> testReports;
    public ReportsAdapter(Context context, int layoutId, ArrayList<TestReport> testReports){
        this.context = context;
        this.layoutId = layoutId;
        this.testReports = testReports;
        this.cellWidth = (int)(new GeneralUtils(context).getScreenWidth()/2.4);
    }
    
	@Override
    public int getCount() {
        return testReports.size();
    }
 
    @Override
    public Object getItem(int position) {
        return testReports.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 	@Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
		if(convertView == null) {
			convertView = (((Activity)context).getLayoutInflater()).inflate(layoutId, parent, false);
            convertView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, cellWidth));
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

        viewHolder.txtTitle.setText(testReports.get(position).getTitle());
        Rect bounds = viewHolder.progressBar.getProgressDrawable().getBounds();
        if (position == 0) {
            viewHolder.txtProgress.setText(testReports.get(position).getProgress() + " / " + testReports.get(position).getMax());
            viewHolder.progressBar.setProgressDrawable(context.getResources().getDrawable(R.drawable.circular_progress_purple));
            viewHolder.progressBar.getProgressDrawable().setBounds(bounds);
        } else if (position == 1) {
            viewHolder.txtProgress.setText((testReports.get(position).getProgress() * 100) / testReports.get(position).getMax() + " %");
            viewHolder.progressBar.setProgressDrawable(context.getResources().getDrawable(R.drawable.circular_progress_cyan));
            viewHolder.progressBar.getProgressDrawable().setBounds(bounds);
        } else if (position == 2) {
            viewHolder.txtProgress.setText(testReports.get(position).getProgress() + " / " + testReports.get(position).getMax());
            viewHolder.progressBar.setProgressDrawable(context.getResources().getDrawable(R.drawable.circular_progress_green));
            viewHolder.progressBar.getProgressDrawable().setBounds(bounds);
        } else if (position == 3){
            viewHolder.txtProgress.setText(testReports.get(position).getProgress() + " / " + testReports.get(position).getMax());
            viewHolder.progressBar.setProgressDrawable(context.getResources().getDrawable(R.drawable.circular_progress_magenta));
            viewHolder.progressBar.getProgressDrawable().setBounds(bounds);
        }
        viewHolder.progressBar.setMax(testReports.get(position).getMax());
        viewHolder.progressBar.setSecondaryProgress(testReports.get(position).getMax());
        final ProgressBar pb = viewHolder.progressBar;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < testReports.get(position).getProgress(); i++) {
                    pb.setProgress(i);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    	return convertView;
    }
	private class ViewHolder {
		private TextView txtTitle;
		private TextView txtProgress;
		private ProgressBar progressBar;

		public ViewHolder(View convertView) {
			txtTitle = (TextView) convertView.findViewById(R.id.txtTitleOfChart);
			txtProgress = (TextView) convertView.findViewById(R.id.txtProgress);
			progressBar = (ProgressBar) convertView.findViewById(R.id.progressChart);
		}
	}
}