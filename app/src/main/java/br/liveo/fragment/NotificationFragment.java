package br.liveo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.liveo.custom.AnimatedExpandableListView;
import br.liveo.model.Notification;
import br.liveo.navigationviewpagerliveo.R;

/**
 * This is an example usage of the AnimatedExpandableListView class.
 *
 * It is an activity that holds a listview which is populated with 100 groups
 * where each group has from 1 to 100 children (so the first group will have one
 * child, the second will have two children and so on...).
 */
public class NotificationFragment extends Fragment {


	private AnimatedExpandableListView listViewForNotification;
	private ExampleAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_notification, container, false);
		ArrayList<Notification> notifications = new ArrayList<>();
		notifications.add(new Notification(1,"1 Jan 2015", "Exam Scedule","Your upcoming exam dates and time table has been released."));
		notifications.add(new Notification(2,"1 Jan 2015", "Result","Your anual Function held on."));
		notifications.add(new Notification(3,"8 Mar 2015", "Upcoming Event","Semester exam held on"));
		notifications.add(new Notification(4,"12 Dec 2015", "Mid-Term Exam","Your upcoming exam dates and time table has been released."));
		notifications.add(new Notification(5,"12 Dec 2015", "Syllabus","Your upcoming exam dates and time table has been released."));
		notifications.add(new Notification(6,"12 Dec 2015", "Class Test","Your upcoming exam dates and time table has been released."));
		notifications.add(new Notification(3,"8 Mar 2015", "Upcoming Event","Your upcoming exam dates and time table has been released."));
		notifications.add(new Notification(4,"10 Dec 2015", "Mid-Term Exam","Your upcoming exam dates and time table has been released."));
		notifications.add(new Notification(5,"11 Dec 2015", "Syllabus","Your upcoming exam dates and time table has been released."));
		notifications.add(new Notification(6,"12 Dec 2015", "Class Test","Your upcoming exam dates and time table has been released."));
		notifications.add(new Notification(2,"1 Jan 2015", "Result","Your upcoming exam dates and time table has been released."));
		notifications.add(new Notification(3,"9 Mar 2015", "Upcoming Event","Your upcoming exam dates and time table has been released."));
		notifications.add(new Notification(4,"12 Dec 2015", "Mid-Term Exam","Your upcoming exam dates and time table has been released."));
		notifications.add(new Notification(5,"11 Feb 2015", "Syllabus","Your upcoming exam dates and time table has been released."));
		notifications.add(new Notification(6,"12 Dec 2015", "Class Test","Your upcoming exam dates and time table has been released."));


		ArrayList<String> parents = getParentTitles(notifications);

		List<GroupItem> items = new ArrayList<GroupItem>();

		// Populate our list with groups and it's children
		for(int i = 0; i < parents.size(); i++) {
			GroupItem item = new GroupItem();
			item.title = parents.get(i);
			ArrayList<Notification> children = getChildren(notifications, parents.get(i));
			for(int j = 0; j < children.size(); j++) {

				ChildItem child = new ChildItem();
				child.title = children.get(j).getTitle();
				child.message= children.get(j).getMessage();
				item.items.add(child);
			}
			items.add(item);
		}

		adapter = new ExampleAdapter(getActivity());
		adapter.setData(items);

		listViewForNotification = (AnimatedExpandableListView) rootView.findViewById(R.id.listViewForNotification);
		listViewForNotification.setAdapter(adapter);
		listViewForNotification.expandGroup(0);
		listViewForNotification.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
				if (listViewForNotification.isGroupExpanded(groupPosition)) {
					listViewForNotification.collapseGroupWithAnimation(groupPosition);
				} else {
					listViewForNotification.expandGroupWithAnimation(groupPosition);
				}
				return true;
			}
		});
		listViewForNotification.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
			int previousItem = -1;
			@Override
			public void onGroupExpand(int groupPosition) {
				if (groupPosition != previousItem)
					listViewForNotification.collapseGroup(previousItem);
				previousItem = groupPosition;
			}
		});
		return  rootView;
	}

	private ArrayList<String> getParentTitles(ArrayList<Notification> notifications) {
		Set<String> uniqueSet = new HashSet<>();
		for (int i = 0; i < notifications.size(); i++) {
			uniqueSet.add(notifications.get(i).getDate());
		}
		return new ArrayList<>(uniqueSet);
	}

	private ArrayList<Notification> getChildren(ArrayList<Notification> notifications, String parent) {
		ArrayList<Notification> children = new ArrayList<>();
		for (int i = 0; i < notifications.size(); i++) {
			if (notifications.get(i).getDate().equals(parent))
				children.add(notifications.get(i));
		}
		return children;
	}

	private static class GroupItem {
		String title;
		List<ChildItem> items = new ArrayList<ChildItem>();


	}

	private static class ChildItem {
		String title;
		String message;
	}

	private static class ChildHolder {
		TextView title;
		TextView message;
	}

	private static class GroupHolder {
		TextView title;
		TextView message;
	}

	/**
	 * Adapter for our list of {@link GroupItem}s.
	 */
	private class ExampleAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
		private LayoutInflater inflater;

		private List<GroupItem> items;

		public ExampleAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		public void setData(List<GroupItem> items) {
			this.items = items;

		}

		@Override
		public ChildItem getChild(int groupPosition, int childPosition) {
			return items.get(groupPosition).items.get(childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
			ChildHolder holder;
			ChildItem item = getChild(groupPosition, childPosition);
			if (convertView == null) {
				holder = new ChildHolder();
				convertView = inflater.inflate(R.layout.layout_notification_exp_child, parent, false);
				holder.title = (TextView) convertView.findViewById(R.id.txtTitle);
				holder.message = (TextView) convertView.findViewById(R.id.txtMessage);
				convertView.setTag(holder);
			} else {
				holder = (ChildHolder) convertView.getTag();
			}

			holder.title.setText(item.title);
			holder.message.setText(item.message);
			return convertView;
		}

		@Override
		public int getRealChildrenCount(int groupPosition) {
			return items.get(groupPosition).items.size();
		}

		@Override
		public GroupItem getGroup(int groupPosition) {
			return items.get(groupPosition);
		}

		@Override
		public int getGroupCount() {
			return items.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
			GroupHolder holder;
			GroupItem item = getGroup(groupPosition);
			if (convertView == null) {
				holder = new GroupHolder();
				convertView = inflater.inflate(R.layout.layout_notification_exp_parent, parent, false);
				holder.title = (TextView) convertView.findViewById(R.id.textTitleforMessage);
				convertView.setTag(holder);
			} else {
				holder = (GroupHolder) convertView.getTag();
			}

			holder.title.setText(item.title);
			return convertView;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		public boolean isChildSelectable(int arg0, int arg1) {
			return true;
		}

	}

}