package br.liveo.fragment;


import android.os.Bundle;
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
        ArrayList<Bullet> bullets = new ArrayList<>();
        if (getArguments().getString("FROM").equals("INCORRECT")) {
            bullets.add(new Bullet("2", RED));
            bullets.add(new Bullet("4", RED));
            bullets.add(new Bullet("5", RED));
            bullets.add(new Bullet("8", RED));
            bullets.add(new Bullet("99", RED));
            bullets.add(new Bullet("2", RED));
            bullets.add(new Bullet("4", RED));
            bullets.add(new Bullet("5", RED));
            bullets.add(new Bullet("8", RED));
            bullets.add(new Bullet("99", RED));
            bullets.add(new Bullet("2", RED));
            bullets.add(new Bullet("4", RED));
            bullets.add(new Bullet("5", RED));
            bullets.add(new Bullet("8", RED));
            bullets.add(new Bullet("99", RED));

        } else if (getArguments().getString("FROM").equals("UNANSWERED")) {
            bullets.add(new Bullet("3", GRAY));
            bullets.add(new Bullet("6", GRAY));
            bullets.add(new Bullet("7", GRAY));
        }
        gridViewQuestionBullets.setAdapter(new QuestionBulletAdapter(getActivity(), R.layout.layout_bullet, bullets));
        gridViewQuestionBullets.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ReviewQuestionFragment())
                        .addToBackStack(MainActivity.MAIN_FRAGMENT_STACK).commit();
            }
        });
        return rootView;
    }
}