package br.liveo.fragment;


import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.liveo.navigationviewpagerliveo.R;

/**
 * Author       :   Rakesh Kumawat
 * Designation  :   Android Developer
 * E-mail       :   er.r.kumawat@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 16 , 2015
 * Purpose      :   Home screen of the application
 * Description  :   Detailed Description...
 */
public class ReviewCategoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_review_category, container, false);
        TextView t1=(TextView)rootView.findViewById(R.id.txtOne);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (getArguments().getString("FROM").equals("INCORRECT")) {
            t1.setBackgroundResource(R.drawable.shape_circle_red);
        } else if (getArguments().getString("FROM").equals("UNANSWERED")) {
            t1.setBackgroundResource(R.drawable.shape_circle_green);
        }
        return rootView;
    }
}