package br.liveo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import br.liveo.navigationviewpagerliveo.R;

/**
 * Author       :   Mohsin Khan
 * Designation  :   Android Developer
 * E-mail       :   khan.square@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 5 , 2015
 * Purpose      :   Application Settings
 * Description  :   Detailed Description...
 */
public class SettingsFragment extends Fragment {
    private LinearLayout layoutChangePassword;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		((ImageView) rootView.findViewById(R.id.imgNotification)).setColorFilter(getContext().getResources().getColor(R.color.nliveo_green_colorPrimary));
		((ImageView) rootView.findViewById(R.id.imgPassword)).setColorFilter(getContext().getResources().getColor(R.color.nliveo_green_colorPrimary));
		((ImageView) rootView.findViewById(R.id.imgOrganization)).setColorFilter(getContext().getResources().getColor(R.color.nliveo_green_colorPrimary));
		((ImageView) rootView.findViewById(R.id.imgTerms)).setColorFilter(getContext().getResources().getColor(R.color.nliveo_green_colorPrimary));
        layoutChangePassword = (LinearLayout) rootView.findViewById(R.id.layoutChangePassword);
        layoutChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogLayout = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_change_password, null);
                final PopupWindow popupWindow = new PopupWindow(dialogLayout, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);

                Button btnDialogClose = (Button) dialogLayout.findViewById(R.id.btnChangePassword);
                btnDialogClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                popupWindow.showAtLocation(dialogLayout, Gravity.CENTER, 0, 0);
            }
        });
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(false);
	}
}