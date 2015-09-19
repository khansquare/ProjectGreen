package br.liveo.controller;

import android.content.Context;
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
 * Date         :   September 18 , 2015
 * Purpose      :   Home screen of the application
 * Description  :   Detailed Description...
 */
public class HighlightAbsentDecorator implements DayViewDecorator {

    private final Calendar calendar = Calendar.getInstance();
    private int weekDay;
    private final Drawable highlightDrawable;

    public HighlightAbsentDecorator(Context context) {
        highlightDrawable = context.getResources().getDrawable(R.drawable.circle_red_filled);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        int a=day.getDay();
        day.copyTo(calendar);
        return a==14|| a==9 ||a==4;
    }
    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(highlightDrawable);
    }
}