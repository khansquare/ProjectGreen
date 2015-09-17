package br.liveo.controller;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;

import br.liveo.navigationviewpagerliveo.R;

/**
 * Author       :   Rakesh Kumawat
 * Designation  :   Android Developer
 * E-mail       :   er.r.kumawat@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 15 , 2015
 * Purpose      :   Home screen of the application
 * Description  :  Highlight Sundays with a background
 */
public class HighlightWeekendsDecorator implements DayViewDecorator {

    private final Calendar calendar = Calendar.getInstance();
    private int weekDay,monthDay;
    private final Drawable highlightDrawable;
    private static final int color = Color.parseColor("#e0e0e0");

    public HighlightWeekendsDecorator(Context context) {
        highlightDrawable = context.getResources().getDrawable(R.drawable.shape_circle_gray);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);
        weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        monthDay= calendar.get(Calendar.DAY_OF_MONTH);
        return  weekDay == Calendar.SUNDAY;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(highlightDrawable);

    }
}
