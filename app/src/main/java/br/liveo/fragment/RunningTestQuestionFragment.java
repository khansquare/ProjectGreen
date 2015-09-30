package br.liveo.fragment;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import br.liveo.model.Question;
import br.liveo.navigationviewpagerliveo.R;


public class RunningTestQuestionFragment extends Fragment {
    static TextView timerText;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_running_test_question, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        TextView txtQuestion = (TextView) rootView.findViewById(R.id.txtQuestion);
        RadioButton txtAnswerA = (RadioButton) rootView.findViewById(R.id.radioAnswerA);
        RadioButton txtAnswerB = (RadioButton) rootView.findViewById(R.id.radioAnswerB);
        RadioButton txtAnswerC = (RadioButton) rootView.findViewById(R.id.radioAnswerC);
        RadioButton txtAnswerD = (RadioButton) rootView.findViewById(R.id.radioAnswerD);

        Question question  = getArguments().getParcelable("QUESTION");
        txtQuestion.setText(question.getQuestion());
        txtAnswerA.setText(question.getOptionA());
        txtAnswerB.setText(question.getOptionB());
        txtAnswerC.setText(question.getOptionC());
        txtAnswerD.setText(question.getOptionD());

        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_timer, menu);
        MenuItem timerItem = menu.findItem(R.id.action_timer);
        timerText  = (TextView) MenuItemCompat.getActionView(timerItem);
        timerText.setPadding(10, 0, 10, 0);

        CountDownTimer timer = new CountDownTimer(60000, 1000) {
            boolean flag = true;
            @Override
            public void onFinish() {
            }

            @Override
            public void onTick(long millisecondsLeft) {

                timerText.setText(String.valueOf((int) Math.round(millisecondsLeft / (double) 1000)));
            }
        };
        timer.start();
    }
}