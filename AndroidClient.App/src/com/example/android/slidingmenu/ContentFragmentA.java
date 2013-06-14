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

public class ContentFragmentA extends Fragment {
	
	private final String[] TitleA = new String[] { "AÀ¸Ä¿1", "AÀ¸Ä¿2", "AÀ¸Ä¿3", "AÀ¸Ä¿4" };
    private final int[] TitleIconsA = new int[] { R.drawable.perm_group_calendar, R.drawable.perm_group_camera, R.drawable.perm_group_device_alarms, R.drawable.perm_group_location };
    
    private List<TestMessageModel> tmmlA1 = new ArrayList<TestMessageModel>();
	private List<TestMessageModel> tmmlA2 = new ArrayList<TestMessageModel>();
	private List<TestMessageModel> tmmlA3 = new ArrayList<TestMessageModel>();
	private List<TestMessageModel> tmmlA4 = new ArrayList<TestMessageModel>();		
	private Date lrtA1,lrtA2,lrtA3,lrtA4;
	private MyBaseAdapter mbaA1,mbaA2,mbaA3,mbaA4;
    private RefreshAndLoadListView rllvA1,rllvA2,rllvA3,rllvA4;
    
    public SMBaseActivity sfContext;
    
    public ContentFragmentA() { }
    public ContentFragmentA(SMBaseActivity parentContext) {
    	sfContext = parentContext;
    	
    	rllvA1=new RefreshAndLoadListView(sfContext);
		rllvA1.setLastRefreshTime(lrtA1);
		mbaA1=new MyBaseAdapter(sfContext, tmmlA1); 
		rllvA1.getListView().setAdapter(mbaA1);
		rllvA1.setRefreshAndLoadListener(IRALListenerHelperA.getIRALL1(ContentFragmentA.this));
		rllvA1.getListView().setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Intent intent = new Intent(sfContext, MessageDetailActivity.class);
				intent.putExtra("position", position);
				startActivity(intent);
			}		
		});
		
		rllvA2=new RefreshAndLoadListView(sfContext);
		rllvA2.setLastRefreshTime(lrtA2);
		mbaA2=new MyBaseAdapter(sfContext, tmmlA2);
		rllvA2.getListView().setAdapter(mbaA2);
		rllvA2.setRefreshAndLoadListener(IRALListenerHelperA.getIRALL2(ContentFragmentA.this));
		rllvA2.getListView().setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Intent intent = new Intent(sfContext, MessageDetailActivity.class);
				intent.putExtra("position", position);
				startActivity(intent);
			}		
		});
		
		rllvA3=new RefreshAndLoadListView(sfContext);
		rllvA3.setLastRefreshTime(lrtA3);
		mbaA3=new MyBaseAdapter(sfContext, tmmlA3);
		rllvA3.getListView().setAdapter(mbaA3);
		rllvA3.setRefreshAndLoadListener(IRALListenerHelperA.getIRALL3(ContentFragmentA.this));
		rllvA3.getListView().setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Intent intent = new Intent(sfContext, MessageDetailActivity.class);
				intent.putExtra("position", position);
				startActivity(intent);
			}		
		});
		
		rllvA4=new RefreshAndLoadListView(sfContext);
		rllvA4.setLastRefreshTime(lrtA4);
		mbaA4=new MyBaseAdapter(sfContext, tmmlA4);
		rllvA4.getListView().setAdapter(mbaA4);
		rllvA4.setRefreshAndLoadListener(IRALListenerHelperA.getIRALL4(ContentFragmentA.this));
		rllvA4.getListView().setOnItemClickListener(new OnItemClickListener(){
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
		ViewGroup p1 = (ViewGroup) rllvA1.getParent(); 
        if (p1 != null) { 
            p1.removeAllViewsInLayout(); 
        } 
        ViewGroup p2 = (ViewGroup) rllvA2.getParent(); 
        if (p2 != null) { 
            p2.removeAllViewsInLayout(); 
        } 
        ViewGroup p3 = (ViewGroup) rllvA3.getParent(); 
        if (p3 != null) { 
            p3.removeAllViewsInLayout(); 
        } 
        ViewGroup p4 = (ViewGroup) rllvA4.getParent(); 
        if (p4 != null) { 
            p4.removeAllViewsInLayout(); 
        } 
        
		super.onDestroy();
	}
	
	public void rllv1LoadData(List<TestMessageModel> tmm){
		if(tmm!=null){
			tmmlA1.add(new TestMessageModel(0,"¹Å½£ÆæÌ·1Load","¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load","11",new Date(System.currentTimeMillis())));
			tmmlA1.add(new TestMessageModel(0,"¹Å½£ÆæÌ·1Load","¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load","11",new Date(System.currentTimeMillis())));
			tmmlA1.add(new TestMessageModel(0,"¹Å½£ÆæÌ·1Load","¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load¹Å½£ÆæÌ·1Load","11",new Date(System.currentTimeMillis())));
			mbaA1.notifyDataSetChanged();
		}
		rllvA1.notifyAfterLoad();
	}
	
	public void rllv1RefreshData(List<TestMessageModel> tmm){
		if(tmm!=null){
			tmmlA1.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·1Refresh","¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh","11",new Date(System.currentTimeMillis())));
			tmmlA1.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·1Refresh","¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh","11",new Date(System.currentTimeMillis())));
			tmmlA1.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·1Refresh","¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh¹Å½£ÆæÌ·1Refresh","11",new Date(System.currentTimeMillis())));
			mbaA1.notifyDataSetChanged();
			lrtA1=rllvA1.setLastRefreshTime(new Date(System.currentTimeMillis()));
		}
		rllvA1.notifyAfterRefresh();
	}
	
	public void rllv2LoadData(List<TestMessageModel> tmm){
		if(tmm!=null){
			tmmlA2.add(new TestMessageModel(0,"¹Å½£ÆæÌ·2Load","¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load","22",new Date(System.currentTimeMillis())));
			tmmlA2.add(new TestMessageModel(0,"¹Å½£ÆæÌ·2Load","¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load","22",new Date(System.currentTimeMillis())));
			tmmlA2.add(new TestMessageModel(0,"¹Å½£ÆæÌ·2Load","¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load¹Å½£ÆæÌ·2Load","22",new Date(System.currentTimeMillis())));
			mbaA2.notifyDataSetChanged();
		}
		rllvA2.notifyAfterLoad();
	}
	
	public void rllv2RefreshData(List<TestMessageModel> tmm){
		if(tmm!=null){
			tmmlA2.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·2Refresh","¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh","22",new Date(System.currentTimeMillis())));
			tmmlA2.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·2Refresh","¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh","22",new Date(System.currentTimeMillis())));
			tmmlA2.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·2Refresh","¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh¹Å½£ÆæÌ·2Refresh","22",new Date(System.currentTimeMillis())));
			mbaA2.notifyDataSetChanged();
			lrtA2=rllvA2.setLastRefreshTime(new Date(System.currentTimeMillis()));
		}
		rllvA2.notifyAfterRefresh();
	}
		
	public void rllv3LoadData(List<TestMessageModel> tmm){
		if(tmm!=null){
			tmmlA3.add(new TestMessageModel(0,"¹Å½£ÆæÌ·3Load","¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load","33",new Date(System.currentTimeMillis())));
			tmmlA3.add(new TestMessageModel(0,"¹Å½£ÆæÌ·3Load","¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load","33",new Date(System.currentTimeMillis())));
			tmmlA3.add(new TestMessageModel(0,"¹Å½£ÆæÌ·3Load","¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load¹Å½£ÆæÌ·3Load","33",new Date(System.currentTimeMillis())));
			mbaA3.notifyDataSetChanged();
		}
		rllvA3.notifyAfterLoad();
	}
	
	public void rllv3RefreshData(List<TestMessageModel> tmm){
		if(tmm!=null){
			tmmlA3.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·3Refresh","¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh","33",new Date(System.currentTimeMillis())));
			tmmlA3.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·3Refresh","¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh","33",new Date(System.currentTimeMillis())));
			tmmlA3.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·3Refresh","¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh¹Å½£ÆæÌ·3Refresh","33",new Date(System.currentTimeMillis())));
			mbaA3.notifyDataSetChanged();
			lrtA3=rllvA3.setLastRefreshTime(new Date(System.currentTimeMillis()));
		}
		rllvA3.notifyAfterRefresh();
	}
	
	public void rllv4LoadData(List<TestMessageModel> tmm){
		if(tmm!=null){
			tmmlA4.add(new TestMessageModel(0,"¹Å½£ÆæÌ·4Load","¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load","44",new Date(System.currentTimeMillis())));
			tmmlA4.add(new TestMessageModel(0,"¹Å½£ÆæÌ·4Load","¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load","44",new Date(System.currentTimeMillis())));
			tmmlA4.add(new TestMessageModel(0,"¹Å½£ÆæÌ·4Load","¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load¹Å½£ÆæÌ·4Load","44",new Date(System.currentTimeMillis())));
			mbaA4.notifyDataSetChanged();
		}
		rllvA4.notifyAfterLoad();
	}
	
	public void rllv4RefreshData(List<TestMessageModel> tmm){
		if(tmm!=null){
			tmmlA4.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·4Refresh","¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh","44",new Date(System.currentTimeMillis())));
			tmmlA4.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·4Refresh","¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh","44",new Date(System.currentTimeMillis())));
			tmmlA4.add(0,new TestMessageModel(0,"¹Å½£ÆæÌ·4Refresh","¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh¹Å½£ÆæÌ·4Refresh","44",new Date(System.currentTimeMillis())));
			mbaA4.notifyDataSetChanged();
			lrtA4=rllvA4.setLastRefreshTime(new Date(System.currentTimeMillis()));
		}
		rllvA4.notifyAfterRefresh();
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
	    		ret= rllvA1;
	    	else if (arg1==1)
	    		ret= rllvA2;
	    	else if (arg1==2)
	    		ret= rllvA3;
	    	else
	    		ret= rllvA4;

	    	arg0.addView(ret, 0);
	    	return ret;
	    }
		
	    @Override
	    public void destroyItem(ViewGroup arg0, int arg1, Object arg2) {
	    	if(arg1==0)
	        	arg0.removeView(rllvA1);
	    	else if (arg1==1)
	    		arg0.removeView(rllvA2);
	    	else if (arg1==2)
	    		arg0.removeView(rllvA3);
	    	else
	    		arg0.removeView(rllvA4);
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
	    	return TitleA[position % TitleA.length];
        }
	    
		@Override
		public int getIconResId(int index) {
			return TitleIconsA[index];
		}
		
		@Override
	    public int getCount() {
	    	return TitleA.length;
	    }
		
	}
	
}