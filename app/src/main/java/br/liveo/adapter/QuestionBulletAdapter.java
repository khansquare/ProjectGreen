package br.liveo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import br.liveo.navigationviewpagerliveo.R;
import java.util.ArrayList;
import br.liveo.model.Bullet;
import static br.liveo.model.BulletColor.*;

public class QuestionBulletAdapter extends BaseAdapter {
	private Context context;	
	private int layoutId;
    private TypedArray menuIcons;
	private ArrayList<Bullet> bullets;

    public QuestionBulletAdapter(Context context, int layoutId, ArrayList<Bullet> bullets){
        this.context = context;
        this.layoutId = layoutId;
        this.bullets = bullets;
    }
    
	@Override
    public int getCount() {
        return bullets.size();
    }
 
    @Override
    public Object getItem(int position) {
        return bullets.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }

 	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	if(convertView == null) {
    		convertView = (((Activity)context).getLayoutInflater()).inflate(layoutId, parent,false);
    	}
        TextView bullet = (TextView) convertView.findViewById(R.id.txtQuestionBullet);
        bullet.setText(bullets.get(position).getText());
        if (bullets.get(position).getColor() == GRAY) {
            bullet.setBackgroundResource(R.drawable.circle_gray_filled);
        } else if (bullets.get(position).getColor() == GREEN) {
            bullet.setBackgroundResource(R.drawable.circle_green_filled);
        } else if (bullets.get(position).getColor() == RED) {
            bullet.setBackgroundResource(R.drawable.circle_red_filled);
        }
    	return convertView;
    }
}