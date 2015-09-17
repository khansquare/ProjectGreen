package br.liveo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import br.liveo.controller.HighlightWeekendsDecorator;
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
public class CalenderFragment extends Fragment {
    MaterialCalendarView widget;

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_calender, container, false);
        widget=(MaterialCalendarView)rootView.findViewById(R.id.calendarView);
        widget.addDecorators(
                new HighlightWeekendsDecorator(getActivity()));
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return rootView;


    }
}