package br.liveo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;

import br.liveo.navigationviewpagerliveo.R;
/**
 * Author       :   Mohsin Khan
 * Designation  :   Android Developer
 * E-mail       :   khan.square@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 5 , 2015
 * Purpose      :   Student's overall record
 * Description  :   Detailed Description...
 */
public class ReportsFragment extends Fragment {

    private boolean mSearchCheck;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_reports, container, false);
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

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
		inflater.inflate(R.menu.menu, menu);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.menu_search));
        searchView.setQueryHint(this.getString(R.string.search));

        ((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text))
                .setHintTextColor(getResources().getColor(R.color.nliveo_white));
        searchView.setOnQueryTextListener(onQuerySearchView);

		menu.findItem(R.id.menu_add).setVisible(true);
		menu.findItem(R.id.menu_search).setVisible(true);
  	    
		mSearchCheck = false;	
	}	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menu_add:

			break;				
		
		case R.id.menu_search:
			mSearchCheck = true;

			break;
		}		
		return true;
	}	

   private SearchView.OnQueryTextListener onQuerySearchView = new SearchView.OnQueryTextListener() {
       @Override
       public boolean onQueryTextSubmit(String s) {
           return false;
       }

       @Override
       public boolean onQueryTextChange(String s) {
           if (mSearchCheck){
               // implement your search here
           }
           return false;
       }
   };
}