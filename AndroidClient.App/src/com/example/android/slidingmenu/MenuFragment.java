package com.example.android.slidingmenu;

import com.example.example_android_demo.R;
import com.example.example_android_demo.TestSMActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MenuFragment extends ListFragment {
	
	MenuAdapter adapter;
	private int[] menuIcon;
	private String[] menuText;
	private int selectedPosition = 0;//选中 的位置 
	
	public MenuFragment(int[] menuIcon, String[] menuText) {	
		this.menuIcon = menuIcon;
		this.menuText = menuText;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.test_menu_list, null);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		adapter = new MenuAdapter(getActivity());		
		for(int i=0; i<menuText.length; i++)
		{
			adapter.add(new SampleItem(menuIcon[i], menuText[i]));
		}		
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {		
		/**
		 * 改变选中的背景色，并去除之前选中的背景色
		 */
		selectedPosition=position;
		adapter.notifyDataSetInvalidated();
		

		if (getActivity() == null)
			return;		
		else
		{
			//Fragment newContent = null;
			
			TestSMActivity tsm = (TestSMActivity) getActivity();
			tsm.switchContent(position);
			
		}
		
	}
	
	private class SampleItem {
		public int menuImg;
		public String menuText;
		public SampleItem(int menuImg, String menuText) {
			this.menuImg = menuImg; 
			this.menuText = menuText;
		}
	}
	
	private class MenuAdapter extends ArrayAdapter<SampleItem> {		
		public MenuAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View contentView, ViewGroup parentView) {
			if (contentView == null) {
				contentView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, null);
			}
			
			TextView text = (TextView) contentView.findViewById(R.id.menu_item_text);
			text.setText(getItem(position).menuText);
			ImageView icon = (ImageView) contentView.findViewById(R.id.menu_item_icon);
			icon.setImageResource(getItem(position).menuImg);
			
			if (selectedPosition == position)
				contentView.setBackgroundColor(getResources().getColor(R.color.sm_menulist_selected_bg));
			else
				contentView.setBackgroundColor(getResources().getColor(R.color.sm_menulist_unselect_bg));	
			
			return contentView;
		}	
	}
	
}