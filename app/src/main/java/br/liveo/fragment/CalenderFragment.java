package br.liveo.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateChangedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.liveo.controller.HighlightAbsentDecorator;
import br.liveo.controller.HighlightLeaveDayDecorator;
import br.liveo.navigationviewpagerliveo.R;

/**
 * Author       :   Rakesh Kumawat
 * Designation  :   Android Developer
 * E-mail       :   er.r.kumawat@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 14 , 2015
 * Purpose      :   To mark weekend
 * Description  :   Detailed Description...
 */
public class CalenderFragment extends Fragment implements OnDateChangedListener {
    MaterialCalendarView widget;

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_calender, container, false);
        widget=(MaterialCalendarView)rootView.findViewById(R.id.calendarView);
        Calendar calendar = Calendar.getInstance();
        widget.setSelectedDate(calendar.getTime());
        Log.e("hi calendar", calendar.getTime().toString());
        Log.e("hi date", new Date().toString());
         {
            widget.addDecorators(
                    new HighlightLeaveDayDecorator(getActivity()),
                    new HighlightAbsentDecorator(getActivity())
            );}
        widget.setOnDateChangedListener(this);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return rootView;
    }
    @Override
    public void onDateChanged(@NonNull MaterialCalendarView widget, CalendarDay date) {
        //Toast.makeText(getActivity(),"click on date",Toast.LENGTH_SHORT).show();
    }

}