package br.liveo.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.liveo.model.Message;
import br.liveo.model.Test;
import br.liveo.model.TestCategory;
import br.liveo.navigationviewpagerliveo.R;

/**
 * Author       :   Mohsin Khan
 * Designation  :   Android Developer
 * E-mail       :   khan.square@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 11 , 2015
 * Purpose      :   List Adapter for messages
 * Description  :   Detailed Description...
 */

public class MessageListAdapter extends ArrayAdapter<Message> {

    private Activity context;
    private int layoutId;
    private ArrayList<Message> messages;

    public MessageListAdapter(Activity context, int layoutId, ArrayList<Message> messages) {
        super(context, layoutId, messages);
        this.context = context;
        this.layoutId = layoutId;
        this.messages = messages;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Message getItem(int position) {
        return messages.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layoutId, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imgPicture.setImageResource(R.drawable.ic_profile_background);
        viewHolder.txtSender.setText(messages.get(position).getSender());
        viewHolder.txtMessage.setText(messages.get(position).getMessage());
        return convertView;
    }

    private class ViewHolder {
        private TextView txtDate;
        private TextView txtSender;
        private TextView txtMessage;
        private ImageView imgPicture;
        public ViewHolder(View convertView) {
            txtSender = (TextView) convertView.findViewById(R.id.txtSender);
            //txtDate = (TextView) convertView.findViewById(R.id.txtDate);
            txtMessage = (TextView) convertView.findViewById(R.id.txtMessage);
            imgPicture = (ImageView) convertView.findViewById(R.id.imgPicture);
        }
    }
}