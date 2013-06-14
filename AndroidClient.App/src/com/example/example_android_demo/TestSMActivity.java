package com.example.example_android_demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.actionbarsherlock.view.MenuItem;
import com.example.android.slidingmenu.ContentFragmentA;
import com.example.android.slidingmenu.ContentFragmentB;
import com.example.android.slidingmenu.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class TestSMActivity extends SMBaseActivity {
	
	private Fragment mContent,contentA,contentB;
	private final String[] menuText = new String[] { "板块A", "板块B"};
    private final int[] menuIcon = new int[] { R.drawable.menu_item_news, R.drawable.menu_item_music };
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(menuText[0]);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		// set the Behind View
		setBehindContentView(R.layout.test_smmenu_frame);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.menu_frame, new MenuFragment(menuIcon, menuText))
		.commit();
		
		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		//sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);//设置距右边边距
		sm.setBehindWidthRes(R.dimen.slidingmenu_width);//设置固定宽度
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.slidingmenu_shadow);
		sm.setBehindScrollScale(0.25f);
		sm.setFadeDegree(0.25f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);	
		
		if (savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
		if (mContent == null)
			mContent = contentA = new ContentFragmentA(TestSMActivity.this);	
		
		// set the Above View
		setContentView(R.layout.test_smcontent_frame);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, mContent)
		.commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}
	
	/**
	 * 选中板块菜单后更改内容
	 */
	public void switchContent(int position) {
		setTitle(menuText[position]);
		
		if(position==0)
		{
			if(contentA==null)
				contentA=new ContentFragmentA(TestSMActivity.this);
			mContent=contentA;
		}
		else if(position==1)
		{
			if(contentB==null)
				contentB=new ContentFragmentB(TestSMActivity.this);
			mContent=contentB;
		}

		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, mContent)
		.commit();
		
		/*Handler h = new Handler();
		h.postDelayed(new Runnable() {
			public void run() {
				getSlidingMenu().showContent();
			}
		}, 500);*/
		getSlidingMenu().showContent();
	}
	
}