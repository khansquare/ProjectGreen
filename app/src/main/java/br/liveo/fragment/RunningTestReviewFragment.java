package br.liveo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

import br.liveo.adapter.QuestionBulletAdapter;
import br.liveo.model.Bullet;
import br.liveo.navigationviewpagerliveo.R;

import static br.liveo.model.BulletColor.GRAY;
import static br.liveo.model.BulletColor.GREEN;
import static br.liveo.model.BulletColor.RED;

/**
 * Author       :   Rakesh Kumawat
 * Designation  :   Android Developer
 * E-mail       :   er.r.kumawat@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 22 , 2015
 * Purpose      :   Home screen of the application
 * Description  :   Detailed Description...
 */
public class RunningTestReviewFragment extends Fragment {

    private GridView gridViewQuestionBulletsAtReviewTest;
    private Button btnSubmit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_running_test_review, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        gridViewQuestionBulletsAtReviewTest = (GridView) rootView.findViewById(R.id.gridViewQuestionBulletsAtReviewTest);
        btnSubmit = (Button) rootView.findViewById(R.id.btnSubmit);
        ArrayList<Bullet> bullets = new ArrayList<>();

        bullets.add(new Bullet("2", GREEN));
        bullets.add(new Bullet("3", RED));
        bullets.add(new Bullet("4", RED));
        bullets.add(new Bullet("6", GREEN));
        bullets.add(new Bullet("5", GREEN));
        bullets.add(new Bullet("7", RED));
        bullets.add(new Bullet("21", GRAY));
        bullets.add(new Bullet("11", RED));
        bullets.add(new Bullet("1", GREEN));
        bullets.add(new Bullet("12", RED));
        bullets.add(new Bullet("21", GRAY));
        bullets.add(new Bullet("11", RED));
        bullets.add(new Bullet("1", GREEN));
        bullets.add(new Bullet("12", RED));
        bullets.add(new Bullet("8", GRAY));
        bullets.add(new Bullet("9", GREEN));
        bullets.add(new Bullet("19", GRAY));
        gridViewQuestionBulletsAtReviewTest.setAdapter(new QuestionBulletAdapter(getActivity(), R.layout.layout_bullet, bullets));
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new TestDetailReportFragment()).commit();
            }
        });
        return rootView;
    }
}