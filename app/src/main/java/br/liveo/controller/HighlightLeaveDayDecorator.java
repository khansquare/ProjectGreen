package br.liveo.controller;
import android.content.Context;
import android.graphics.Color;
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
 * Description  :   Highlight Sundays with a background
 */
public class HighlightLeaveDayDecorator implements DayViewDecorator {

    private final Calendar calendar = Calendar.getInstance();
    private int weekDay;
    private final Drawable highlightDrawable;

    public HighlightLeaveDayDecorator(Context context) {
        highlightDrawable = context.getResources().getDrawable(R.drawable.circle_gray_filled);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        int a=day.getDay();
      //  String s1 = String.valueOf(a);
       // Log.e("daayyyyyy", s1);
        day.copyTo(calendar);
       // weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        return  a==16;
    }
    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(highlightDrawable);
    }
}
