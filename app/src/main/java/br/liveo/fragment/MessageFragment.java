package br.liveo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import br.liveo.activity.MainActivity;
import br.liveo.adapter.MessageListAdapter;
import br.liveo.model.Message;
import br.liveo.navigationviewpagerliveo.R;

/**
 * This is an example usage of the AnimatedExpandableListView class.
 *
 * It is an activity that holds a listview which is populated with 100 groups
 * where each group has from 1 to 100 children (so the first group will have one
 * child, the second will have two children and so on...).
 */
public class MessageFragment extends Fragment {

    private ListView listViewMessage;
	FloatingActionButton fabMailSend;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_message, container, false);
	    listViewMessage = (ListView)rootView.findViewById(R.id.listViewMessage);
		ArrayList<Message> messages = new ArrayList<>();
		messages.add(new Message(0,"20:01:2015","Michael Clark", "Want to know result declaration date."));
		messages.add(new Message(1,"20:01:2015","Adam Smith", "Attenddence report."));
		messages.add(new Message(2,"21:02:2015","Lisa Hyden ", "Exam Fees for next semester."));
		messages.add(new Message(3,"22:02:2015","Amelia Silverstone", "Last date of fee submission."));
		messages.add(new Message(4,"23:05:2015","Chris Walkner", "Want to know result declaration date."));
		messages.add(new Message(5,"24:05:2015","David Guetta", "Want to know result declaration date."));
		messages.add(new Message(6, "24:05:2015", "Harshel Gibs", "Want to know result declaration date."));
		listViewMessage.setAdapter(new MessageListAdapter(getActivity(), R.layout.layout_message, messages));
		fabMailSend = (FloatingActionButton)rootView.findViewById(R.id.fabMailSend);
		fabMailSend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				getFragmentManager().beginTransaction().replace(R.id.container, new ComposeFragment())
						.addToBackStack(MainActivity.MAIN_FRAGMENT_STACK).commit();
				/*Intent intent=new Intent(Intent.ACTION_SEND);
				String[] recipients={"edtechguy@gmail.com"};
				intent.putExtra(Intent.EXTRA_EMAIL, recipients);
				intent.putExtra(Intent.EXTRA_SUBJECT,"abc");
				intent.putExtra(Intent.EXTRA_TEXT,"def");
				intent.putExtra(Intent.EXTRA_CC,"ghi");
				intent.setType("text/html");
				startActivity(Intent.createChooser(intent, "Send mail"));*/
				Log.e("Click on send mail","Button");
			}
		});

		return rootView;
	}
}