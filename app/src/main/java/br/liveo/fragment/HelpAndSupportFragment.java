package br.liveo.fragment;

        import java.util.ArrayList;
        import java.util.List;
        import android.os.Bundle;
        import android.content.Context;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ExpandableListView;
        import android.widget.ExpandableListView.OnGroupClickListener;
        import android.widget.TextView;

        import br.liveo.custom.AnimatedExpandableListView;
        import br.liveo.navigationviewpagerliveo.R;

/**
 * This is an example usage of the AnimatedExpandableListView class.
 *
 * It is an activity that holds a listview which is populated with 100 groups
 * where each group has from 1 to 100 children (so the first group will have one
 * child, the second will have two children and so on...).
 */
public class HelpAndSupportFragment extends Fragment {
    private AnimatedExpandableListView listView;
    private ExampleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_helpandsupport, container, false);
        String [] questions = {"How can I start test?",
                "How to get results?",
                "Can I get detailed syllabus attached with the test schedule?",
                "How to ..."};
        String [] answer = {"It is easy to start test but keep it mind that you can only attempt those tests displayed on your home screen with the tag LIVE NOW. Just click on the live test and you will get test overview having the button 'Take Test'. By clicking on this key, you will be redirected to the test screen.",
                "If you successfully attempt the test then your test will be displayed in Menu > Tests > Attempted. Here you can see test overview, syllabus and reports. In the reports, you can see your score, percentage, total attempted questions and correct answers. To get more analytical information about your test hit ANALYTICS button and see a pie chart that implies your performance in the test. At review screen, you can get final score and class average also.",
                "It is easy to start test but keep it mind that you can only attempt those tests displayed on your home screen with the tag LIVE NOW. Just click on the live test and you will get test overview having the button 'Take Test'. By clicking on this key, you will be redirected to the test screen.",
                "..."};
        List<GroupItem> items = new ArrayList<GroupItem>();

        // Populate our list with groups and it's children
        for(int i = 0; i < questions.length; i++) {
            GroupItem item = new GroupItem();
            item.title = questions[i];
            for(int j = 0; j < 1; j++) {
                ChildItem child = new ChildItem();
                child.title = answer[i];
               item.items.add(child);
            }
            items.add(item);
        }

        adapter = new ExampleAdapter(getActivity());
        adapter.setData(items);

        listView = (AnimatedExpandableListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (listView.isGroupExpanded(groupPosition)) {
                    listView.collapseGroupWithAnimation(groupPosition);
                } else {
                    listView.expandGroupWithAnimation(groupPosition);
                }
                return true;
            }
        });
        return  rootView;
    }

    private static class GroupItem {
        String title;
        List<ChildItem> items = new ArrayList<ChildItem>();
    }

    private static class ChildItem {
        String title;
    }

    private static class ChildHolder {
        TextView title;
    }

    private static class GroupHolder {
        TextView title;
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
                convertView = inflater.inflate(R.layout.layout_exp_child, parent, false);
                holder.title = (TextView) convertView.findViewById(R.id.textTitle);
                convertView.setTag(holder);
            } else {
                holder = (ChildHolder) convertView.getTag();
            }

            holder.title.setText(item.title);
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
                convertView = inflater.inflate(R.layout.layout_exp_parent, parent, false);
                holder.title = (TextView) convertView.findViewById(R.id.textTitle);
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