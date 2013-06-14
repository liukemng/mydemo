package com.example.android.slidingmenu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.android.extensions.MyBaseAdapter;
import com.example.android.models.TestMessageModel;
import com.example.android.pageindicator.IconPagerAdapter;
import com.example.android.pageindicator.TabPageIndicatorView;
import com.example.android.refreshandload.RefreshAndLoadListView;
import com.example.example_android_demo.MessageDetailActivity;
import com.example.example_android_demo.R;
import com.example.example_android_demo.SMBaseActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;

public class ContentFragmentB extends Fragment {
	
	private final String[] TitleB = new String[] { "BÀ¸Ä¿1", "BÀ¸Ä¿2", "BÀ¸Ä¿3", "BÀ¸Ä¿4" };
    private final int[] TitleIconsB = new int[] { R.drawable.perm_group_calendar, R.drawable.perm_group_camera, R.drawable.perm_group_device_alarms, R.drawable.perm_group_location };
    
    private List<TestMessageModel> tmmlB1 = new ArrayList<TestMessageModel>();
	private List<TestMessageModel> tmmlB2 = new ArrayList<TestMessageModel>();
	private List<TestMessageModel> tmmlB3 = new ArrayList<TestMessageModel>();
	private List<TestMessageModel> tmmlB4 = new ArrayList<TestMessageModel>();	
	private Date lrtB1,lrtB2,lrtB3,lrtB4;
	private MyBaseAdapter mbaB1,mbaB2,mbaB3,mbaB4;
    private RefreshAndLoadListView rllvB1,rllvB2,rllvB3,rllvB4;
    
    public SMBaseActivity sfContext;
    
    public ContentFragmentB() { }
    public ContentFragmentB(SMBaseActivity parentContext) {
    	sfContext = parentContext;
    	
    	rllvB1=new RefreshAndLoadListView(sfContext);
		rllvB1.setLastRefreshTime(lrtB1);
		mbaB1=new MyBaseAdapter(sfContext, tmmlB1); 
		rllvB1.getListView().setAdapter(mbaB1);
		rllvB1.setRefreshAndLoadListener(IRALListenerHelperB.getIRALL1(ContentFragmentB.this));
		rllvB1.getListView().setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Intent intent = new Intent(sfContext, MessageDetailActivity.class);
				intent.putExtra("position", position);
				startActivity(intent);
			}		
		});
		
		rllvB2=new RefreshAndLoadListView(sfContext);
		rllvB2.setLastRefreshTime(lrtB2);
		mbaB2=new MyBaseAdapter(sfContext, tmmlB2);
		rllvB2.getListView().setAdapter(mbaB2);
		rllvB2.setRefreshAndLoadListener(IRALListenerHelperB.getIRALL2(ContentFragmentB.this));
		rllvB2.getListView().setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Intent intent = new Intent(sfContext, MessageDetailActivity.class);
				intent.putExtra("position", position);
				startActivity(intent);
			}		
		});
		
		rllvB3=new RefreshAndLoadListView(sfContext);
		rllvB3.setLastRefreshTime(lrtB3);
		mbaB3=new MyBaseAdapter(sfContext, tmmlB3); 
		rllvB3.getListView().setAdapter(mbaB3);
		rllvB3.setRefreshAndLoadListener(IRALListenerHelperB.getIRALL3(ContentFragmentB.this));
		rllvB3.getListView().setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Intent intent = new Intent(sfContext, MessageDetailActivity.class);
				intent.putExtra("position", position);
				startActivity(intent);
			}		
		});
		
		rllvB4=new RefreshAndLoadListView(sfContext);
		rllvB4.setLastRefreshTime(lrtB4);
		mbaB4=new MyBaseAdapter(sfContext, tmmlB4);
		rllvB4.getListView().setAdapter(mbaB4);
		rllvB4.setRefreshAndLoadListener(IRALListenerHelperB.getIRALL4(ContentFragmentB.this));
		rllvB4.getListView().setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Intent intent = new Intent(sfContext, MessageDetailActivity.class);
				intent.putExtra("position", position);
				startActivity(intent);
			}		
		});	
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.activity_messagelist, null);
		ViewPager pager = (ViewPager) ll.findViewById(R.id.message_view_pager);
		pager.setAdapter(new RefreshAndLoadPagerAdapter());
		
		TabPageIndicatorView tabPageIndicator = (TabPageIndicatorView)ll.findViewById(R.id.message_view_indicator);
        tabPageIndicator.setViewPager(pager);
        tabPageIndicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
	        @Override
	        public void onPageSelected(int position) {
	            switch (position) {
	                case 0:
	                	sfContext.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	                    break;
	                default:
	                	sfContext.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
	                    break;
	            }
	        }

	    });

		return ll;
	}
	
	@Override
	public void onDestroy(){
		ViewGroup p1 = (ViewGroup) rllvB1.getParent(); 
        if (p1 != null) { 
            p1.removeAllViewsInLayout(); 
        } 
        ViewGroup p2 = (ViewGroup) rllvB2.getParent(); 
        if (p2 != null) { 
            p2.removeAllViewsInLayout(); 
        } 
        ViewGroup p3 = (ViewGroup) rllvB3.getParent(); 
        if (p3 != null) { 
            p3.removeAllViewsInLayout(); 
        } 
        ViewGroup p4 = (ViewGroup) rllvB4.getParent(); 
        if (p4 != null) { 
            p4.removeAllViewsInLayout(); 
        } 
        
		super.onDestroy();
	}
	
	public void rllv1LoadData(List<TestMessageModel> tmm){
		if(tmm!=null){
			tmmlB1.add(new TestMessageModel(0,"¹Å½£ÆæÌ·1Load","¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load","11",new Date(System.currentTimeMillis())));
			tmmlB1.add(new TestMessageModel(0,"¹Å½£ÆæÌ·1Load","¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load","11",new Date(System.currentTimeMillis())));
			tmmlB1.add(new TestMessageModel(0,"¹Å½£ÆæÌ·1Load","¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load","11",new Date(System.currentTimeMillis())));
			mbaB1.notifyDataSetChanged();
		}
		rllvB1.notifyAfterLoad();
	}
	
	public void rllv1RefreshData(List<TestMessageModel> tmm){
		if(tmm!=null){
			tmmlB1.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·1Refresh","¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh","11",new Date(System.currentTimeMillis())));
			tmmlB1.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·1Refresh","¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh","11",new Date(System.currentTimeMillis())));
			tmmlB1.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·1Refresh","¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh","11",new Date(System.currentTimeMillis())));
			mbaB1.notifyDataSetChanged();
			lrtB1=rllvB1.setLastRefreshTime(new Date(System.currentTimeMillis()));
		}
		rllvB1.notifyAfterRefresh();
	}
	
	public void rllv2LoadData(List<TestMessageModel> tmm){
		if(tmm!=null){
			tmmlB2.add(new TestMessageModel(0,"¹Å½£ÆæÌ·2Load","¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load","22",new Date(System.currentTimeMillis())));
			tmmlB2.add(new TestMessageModel(0,"¹Å½£ÆæÌ·2Load","¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load","22",new Date(System.currentTimeMillis())));
			tmmlB2.add(new TestMessageModel(0,"¹Å½£ÆæÌ·2Load","¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load","22",new Date(System.currentTimeMillis())));
			mbaB2.notifyDataSetChanged();
		}
		rllvB2.notifyAfterLoad();
	}
	
	public void rllv2RefreshData(List<TestMessageModel> tmm){
		if(tmm!=null){
			tmmlB2.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·2Refresh","¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh","22",new Date(System.currentTimeMillis())));
			tmmlB2.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·2Refresh","¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh","22",new Date(System.currentTimeMillis())));
			tmmlB2.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·2Refresh","¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh","22",new Date(System.currentTimeMillis())));
			mbaB2.notifyDataSetChanged();
			lrtB2=rllvB2.setLastRefreshTime(new Date(System.currentTimeMillis()));
		}
		rllvB2.notifyAfterRefresh();
	}
		
	public void rllv3LoadData(List<TestMessageModel> tmm){
		if(tmm!=null){
			tmmlB3.add(new TestMessageModel(0,"¹Å½£ÆæÌ·3Load","¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load","33",new Date(System.currentTimeMillis())));
			tmmlB3.add(new TestMessageModel(0,"¹Å½£ÆæÌ·3Load","¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load","33",new Date(System.currentTimeMillis())));
			tmmlB3.add(new TestMessageModel(0,"¹Å½£ÆæÌ·3Load","¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load","33",new Date(System.currentTimeMillis())));
			mbaB3.notifyDataSetChanged();
		}
		rllvB3.notifyAfterLoad();
	}
	
	public void rllv3RefreshData(List<TestMessageModel> tmm){
		if(tmm!=null){
			tmmlB3.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·3Refresh","¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh","33",new Date(System.currentTimeMillis())));
			tmmlB3.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·3Refresh","¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh","33",new Date(System.currentTimeMillis())));
			tmmlB3.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·3Refresh","¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh","33",new Date(System.currentTimeMillis())));
			mbaB3.notifyDataSetChanged();
			lrtB3=rllvB3.setLastRefreshTime(new Date(System.currentTimeMillis()));
		}
		rllvB3.notifyAfterRefresh();
	}
	
	public void rllv4LoadData(List<TestMessageModel> tmm){
		if(tmm!=null){
			tmmlB4.add(new TestMessageModel(0,"¹Å½£ÆæÌ·4Load","¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load","44",new Date(System.currentTimeMillis())));
			tmmlB4.add(new TestMessageModel(0,"¹Å½£ÆæÌ·4Load","¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load","44",new Date(System.currentTimeMillis())));
			tmmlB4.add(new TestMessageModel(0,"¹Å½£ÆæÌ·4Load","¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load","44",new Date(System.currentTimeMillis())));
			mbaB4.notifyDataSetChanged();
		}
		rllvB4.notifyAfterLoad();
	}
	
	public void rllv4RefreshData(List<TestMessageModel> tmm){
		if(tmm!=null){
			tmmlB4.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·4Refresh","¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh","44",new Date(System.currentTimeMillis())));
			tmmlB4.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·4Refresh","¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh","44",new Date(System.currentTimeMillis())));
			tmmlB4.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·4Refresh","¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh","44",new Date(System.currentTimeMillis())));
			mbaB4.notifyDataSetChanged();
			lrtB4=rllvB4.setLastRefreshTime(new Date(System.currentTimeMillis()));
		}
		rllvB4.notifyAfterRefresh();
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		//outState.putInt("mPos", mPos);
	}
	
	class RefreshAndLoadPagerAdapter extends PagerAdapter implements IconPagerAdapter {
		
		@Override
	    public Object instantiateItem(ViewGroup arg0, int arg1) {
			RefreshAndLoadListView ret;
	    	
	    	if(arg1==0)
	    		ret= rllvB1;
	    	else if (arg1==1)
	    		ret= rllvB2;
	    	else if (arg1==2)
	    		ret= rllvB3;
	    	else
	    		ret= rllvB4;

	    	arg0.addView(ret, 0);
	    	return ret;
	    }
		
	    @Override
	    public void destroyItem(ViewGroup arg0, int arg1, Object arg2) {
	    	if(arg1==0)
	        	arg0.removeView(rllvB1);
	    	else if (arg1==1)
	    		arg0.removeView(rllvB2);
	    	else if (arg1==2)
	    		arg0.removeView(rllvB3);
	    	else
	    		arg0.removeView(rllvB4);
	    }

	    @Override
	    public boolean isViewFromObject(View arg0, Object arg1) {
	        return arg0 == arg1;
	    }

	    @Override
    	public int getItemPosition(Object object) {
	    	return super.getItemPosition(object);
    	}
	    
	    @Override
        public CharSequence getPageTitle(int position) {
	    	return TitleB[position % TitleB.length];
        }
	    
		@Override
		public int getIconResId(int index) {
			return TitleIconsB[index];
		}
		
		@Override
	    public int getCount() {
	    	return TitleB.length;
	    }
		
	}
	
}