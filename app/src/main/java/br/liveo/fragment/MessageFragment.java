package br.liveo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

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
		messages.add(new Message(6,"24:05:2015","Harshel Gibs", "Want to know result declaration date."));
		listViewMessage.setAdapter(new MessageListAdapter(getActivity(),R.layout.layout_message, messages));
		return rootView;
	}
}