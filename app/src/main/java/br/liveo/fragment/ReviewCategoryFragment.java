package br.liveo.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import br.liveo.activity.MainActivity;
import br.liveo.adapter.QuestionBulletAdapter;
import br.liveo.model.Bullet;
import br.liveo.navigationviewpagerliveo.R;
import static br.liveo.model.BulletColor.*;

/**
 * Author       :   Rakesh Kumawat
 * Designation  :   Android Developer
 * E-mail       :   er.r.kumawat@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 16 , 2015
 * Purpose      :   Review category
 * Description  :   Adding question bullets according to the category...
 */
public class ReviewCategoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_review_category, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        GridView gridViewQuestionBullets = (GridView) rootView.findViewById(R.id.gridViewQuestionBullets);
        final ArrayList<Bullet> bullets = new ArrayList<>();
        if (getArguments().getString("FROM").equals("CORRECT")) {
            bullets.add(new Bullet("1", GREEN));
            bullets.add(new Bullet("2", GREEN));
            bullets.add(new Bullet("5", GREEN));
            bullets.add(new Bullet("6", GREEN));
            bullets.add(new Bullet("9", GREEN));
            bullets.add(new Bullet("10", GREEN));
            bullets.add(new Bullet("13", GREEN));
            bullets.add(new Bullet("14", GREEN));
            bullets.add(new Bullet("15", GREEN));
            bullets.add(new Bullet("17", GREEN));
        } else if (getArguments().getString("FROM").equals("INCORRECT")) {
            bullets.add(new Bullet("3", RED));
            bullets.add(new Bullet("4", RED));
            bullets.add(new Bullet("7", RED));
            bullets.add(new Bullet("11", RED));
            bullets.add(new Bullet("12", RED));
            bullets.add(new Bullet("16", RED));
            bullets.add(new Bullet("18", RED));
            bullets.add(new Bullet("20", RED));
        } else if (getArguments().getString("FROM").equals("UNANSWERED")) {
            bullets.add(new Bullet("8", GRAY));
            bullets.add(new Bullet("19", GRAY));
        }
        gridViewQuestionBullets.setAdapter(new QuestionBulletAdapter(getActivity(), R.layout.layout_bullet, bullets));
        gridViewQuestionBullets.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Bundle bundle  = new Bundle();
                bundle.putInt("SIZE", bullets.size());
                bundle.putInt("POSITION",position);
                bundle.putParcelableArrayList("DATA", bullets);
                ReviewCategoryPagerFragment reviewCategoryPagerFragment = new ReviewCategoryPagerFragment();
                reviewCategoryPagerFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, reviewCategoryPagerFragment)
                        .addToBackStack(MainActivity.MAIN_FRAGMENT_STACK).commit();
            }
        });
        return rootView;
    }
}