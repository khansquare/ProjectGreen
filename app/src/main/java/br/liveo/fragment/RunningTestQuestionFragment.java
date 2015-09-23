package br.liveo.fragment;


import android.os.Bundle;
import android.os.CountDownTimer;
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
        // rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        TextView txtQuestion = (TextView)rootView.findViewById(R.id.txtQuestion);
        RadioButton txtAnswerA =  (RadioButton)rootView.findViewById(R.id.txtAnswerA);
        RadioButton txtAnswerB =  (RadioButton)rootView.findViewById(R.id.txtAnswerB);
        RadioButton txtAnswerC =  (RadioButton)rootView.findViewById(R.id.txtAnswerC);
        RadioButton txtAnswerD =  (RadioButton)rootView.findViewById(R.id.txtAnswerD);
        ArrayList<Question> questionsTest = getAllQuestions();
        for (Question question : questionsTest) {
            txtQuestion.setText(question.getQuestion());
            txtAnswerA.setText(question.getOptionA());
            txtAnswerB.setText(question.getOptionB());
            txtAnswerC.setText(question.getOptionC());
            txtAnswerD.setText(question.getOptionD());
        }
        return rootView;
    }

    private ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("Which is a reserved word in the Java programming language?","method","native","subclasses","reference"));
        questions.add(new Question("Which one of these lists contains only Java programming language keywords?","class, if, void, long, Int, continue","goto, instanceof, native, finally, default, throws","try, virtual, throw, final, volatile, transient","byte, break, assert, switch, include"));
        questions.add(new Question("Which is a valid keyword in java?","interface","string","unsigned","Float"));
        questions.add(new Question("Which is the valid declarations within an interface definition?","public double methoda();","ublic final double methoda();","static void methoda(double d1);","protected void methoda(double d1);"));
        return questions;
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


