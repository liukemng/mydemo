package com.example.android.refreshandload;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.example.example_android_demo.R;
import com.example.android.extensions.DensityUtil;

/**
 * 下拉刷新控件
 * @author MissWhen
 *
 */
public class RefreshAndLoadListView extends LinearLayout {
			
	private static final int WHAT_BEFORE_REFRESH = 1;// Handler what 刷新之前
	private static final int WHAT_ON_REFRESHING = 2;// Handler what 刷新中
	private static final int WHAT_AFTER_REFRESH = 3;// Handler what 刷新后
	private static final int WHAT_ON_LOADING = 4;// Handler what 加载中
	private static final int WHAT_BEFOREORAFTER_LOAD = 5;// Handler what 加载之前或之后
	private static final int WHAT_SET_HEADER_HEIGHT = 6;// Handler what 设置高度
	private static final int WHAT_SHOW_FOOTER_VIEW = 7;// Handler what 显示FOOTER
	private static final int WHAT_HIDE_FOOTER_VIEW = 8;// Handler what 隐藏FOOTER
	private static final int AUTO_INCREMENTAL = 10;// 自增量，用于回弹
	// 头部文件的状态
	private static final int HEADER_VIEW_STATE_IDLE = 0;// 空闲
	private static final int HEADER_VIEW_STATE_NOT_OVER_HEIGHT = 1;// 没有超过默认高度
	private static final int HEADER_VIEW_STATE_OVER_HEIGHT = 2;// 超过默认高度
	// 刷新或者加载操作
	private static final int PULL_UP_FOR_REFERSH=1;// 刷新操作
	private static final int PULL_DOWN_FOR_LOAD=2;// 加载操作
	
	@SuppressLint("SimpleDateFormat")
	private final SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd hh:mm:ss");	
	private Date mLastRefreshTime;// 列表信息最后更新时间
	private int mDefaultHeaderHeight;// 头部文件原本的高度
	private View mHeaderView;
	private LayoutParams mHeaderViewParams;	
	private TextView mHeaderDateView;
	private TextView mHeaderTextView;
	private ImageView mHeaderArrowView;
	private View mHeaderProgressView;
	private View mFooterView;
	private TextView mFooterTextView;
	private View mFooterProgressView;
	private ListView mListView;	
	private IRefreshAndLoadListener mRefreshAndLoadListener;
	private RotateAnimation mRotateOTo180Animation;
	private RotateAnimation mRotate180To0Animation;
	private int mHeaderIncremental;// 增量
	private int mMotionDownLastY;// 按下时候的Y轴坐标	
	private boolean mIsRefreshing;// 是否下拉刷新中
	private boolean mIsLoading;	// 是否获取更多中
	private int mMoveAction;// 操作类型
	private int mHeaderViewState = HEADER_VIEW_STATE_IDLE;
	
	private MyHandler myHandler = new MyHandler(this);

	public RefreshAndLoadListView(Context context) {
		super(context);
		initHeaderViewAndFooterViewAndListView(context);
	}
	
	public RefreshAndLoadListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initHeaderViewAndFooterViewAndListView(context);
	}
	
	/**
	 * 首次加载时初始化数据
	 */
	public void InitializeData() {
		if(!mIsLoading && mRefreshAndLoadListener != null){
			myHandler.sendEmptyMessage(WHAT_ON_LOADING);
			mRefreshAndLoadListener.onLoad();
		}
	}
	
	/**
	 * 通知已经刷新完了，要放在Adapter.notifyDataSetChanged后面
	 * 当你执行完刷新任务之后，调用这个notifyAfterRefresh()
	 * 才会隐藏掉头部文件等操作
	 */
	public void notifyAfterRefresh() {
		myHandler.sendEmptyMessage(WHAT_AFTER_REFRESH);
	}
	
	/**
	 * 通知加载完了数据，要放在Adapter.notifyDataSetChanged后面
	 * 当你加载完数据的时候，调用这个notifyAfterLoad()
	 * 才会初始化底部状态
	 */
	public void notifyAfterLoad() {
		myHandler.sendEmptyMessage(WHAT_BEFOREORAFTER_LOAD);
	}
	
	/**
	 * 通知所有数据已加载，要放在Adapter.notifyDataSetChanged后面
	 * 当所有数据已加载的时候，调用这个notifyAllLoaded()
	 * 才会隐藏底部
	 */
	public void notifyAllLoaded() {
		myHandler.sendEmptyMessage(WHAT_HIDE_FOOTER_VIEW);
	}
	
	/**
	 * 获取内嵌的listview
	 * @return ScrollOverListView
	 */
	public ListView getListView(){
		return mListView;
	}
	
	/**
	 * 设置最后刷新时间
	 * @param lastRefreshTime
	 */
	public Date setLastRefreshTime(Date lastRefreshTime){
		mLastRefreshTime=lastRefreshTime;
		return mLastRefreshTime;
	}

	/**
	 * 设置监听器
	 * @param listener
	 */
	public void setRefreshAndLoadListener(IRefreshAndLoadListener listener){
		mRefreshAndLoadListener = listener;
	}

	/**
	 * 初始化界面
	 * @param context
	 */
	private void initHeaderViewAndFooterViewAndListView(Context context){	
		setOrientation(LinearLayout.VERTICAL);
		mDefaultHeaderHeight=DensityUtil.dip2px(context,65);//设置HEADER的高度
		//setDrawingCacheEnabled(false);
		mHeaderView = LayoutInflater.from(context).inflate(R.layout.refreshandload_header, null);
		mHeaderTextView = (TextView) mHeaderView.findViewById(R.id.refreshandload_header_text);
		mHeaderDateView = (TextView) mHeaderView.findViewById(R.id.refreshandload_header_date);
		mHeaderArrowView = (ImageView) mHeaderView.findViewById(R.id.refreshandload_header_arrow);
		mHeaderProgressView = mHeaderView.findViewById(R.id.refreshandload_header_progress);
		mHeaderViewParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		addView(mHeaderView, 0, mHeaderViewParams);
		
		// 注意，图片旋转之后，再执行旋转，坐标会重新开始计算
		mRotateOTo180Animation = new RotateAnimation(0, 180, 
				Animation.RELATIVE_TO_SELF, 0.5f, 
				Animation.RELATIVE_TO_SELF, 0.5f);
		mRotateOTo180Animation.setDuration(250);
		mRotateOTo180Animation.setFillAfter(true);
		
		mRotate180To0Animation = new RotateAnimation(180, 0, 
				Animation.RELATIVE_TO_SELF, 0.5f, 
				Animation.RELATIVE_TO_SELF, 0.5f);
		mRotate180To0Animation.setDuration(250);
		mRotate180To0Animation.setFillAfter(true);
		
		/**
		 * 自定义脚部文件
		 */
		mFooterView = LayoutInflater.from(context).inflate(R.layout.refreshandload_footer, null);
		mFooterTextView = (TextView) mFooterView.findViewById(R.id.refreshandload_footer_text);
		mFooterProgressView = mFooterView.findViewById(R.id.refreshandload_footer_progress);
		mFooterView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!mIsLoading && mRefreshAndLoadListener != null){
					myHandler.sendEmptyMessage(WHAT_ON_LOADING);
					mRefreshAndLoadListener.onLoad();
				}
			}
		});
		
		/**
		 * ScrollOverListView 同样是考虑到都是使用，所以放在这里
		 * 同时因为，需要它的监听事件
		 */
		mListView = new ListView(context);
		mListView.setCacheColorHint(0);
		addView(mListView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		myHandler.sendEmptyMessage(WHAT_BEFORE_REFRESH);
		myHandler.sendEmptyMessage(WHAT_BEFOREORAFTER_LOAD);
		myHandler.sendEmptyMessage(WHAT_SHOW_FOOTER_VIEW);
	}
	
	/**
	 * 在下拉和回推的时候检查头部文件的状态
	 * 如果超过了默认高度，就显示松开可以刷新，
	 * 否则显示下拉可以刷新
	 */
	private void checkHeaderViewState(){
		if(mHeaderViewParams.height >= mDefaultHeaderHeight){
			if(mHeaderViewState == HEADER_VIEW_STATE_OVER_HEIGHT) 
				return;
			mHeaderViewState = HEADER_VIEW_STATE_OVER_HEIGHT;
			mHeaderTextView.setText(R.string.refreshandload_header_releasetorefresh);
			mHeaderArrowView.startAnimation(mRotateOTo180Animation);
		}else{
			if(mHeaderViewState == HEADER_VIEW_STATE_NOT_OVER_HEIGHT || mHeaderViewState == HEADER_VIEW_STATE_IDLE) 
				return;
			mHeaderViewState = HEADER_VIEW_STATE_NOT_OVER_HEIGHT;
			mHeaderTextView.setText(R.string.refreshandload_header_pulltorefresh);
			mHeaderArrowView.startAnimation(mRotate180To0Animation);
		}
	}
		
	/**
	 * 设置头部的高度
	 * @param height
	 */
	private void setHeaderHeight(final int height){
		mHeaderIncremental = height;
		mHeaderViewParams.height = height;
		mHeaderView.setLayoutParams(mHeaderViewParams);
	}

	/**
	 * 显示脚步脚部文件
	 */
	private void showFooterView(){
		if(mListView.getFooterViewsCount() == 0){
			mListView.addFooterView(mFooterView);
			mListView.setAdapter(mListView.getAdapter());
		}
	}
	
	/**
	 * 隐藏脚步脚部文件
	 */
	private void hideFooterView(){
		if(mListView.getFooterViewsCount() != 0)
			mListView.removeFooterView(mFooterView);
	}
	
//	/**
//	 * 条目是否填满整个屏幕
//	 * 需要在已绑定数据后才能调用，否则不准确
//	 */
//	private boolean isFillScreenItem(){
//		final int firstVisiblePosition = mListView.getFirstVisiblePosition();		
//		final int lastVisiblePostion = mListView.getLastVisiblePosition() - mListView.getFooterViewsCount();
//		final int visibleItemCount = lastVisiblePostion - firstVisiblePosition + 1;
//		final int totalItemCount = mListView.getCount() - mListView.getFooterViewsCount();
//		
//		if(visibleItemCount < totalItemCount) 
//			return true;
//		return false;
//	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		int y = (int) event.getRawY();
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mMotionDownLastY = y;
				break;
			case MotionEvent.ACTION_MOVE:
				if(mIsRefreshing || mIsLoading)
					return false;
				final int childCount = mListView.getChildCount();
				if(childCount == 0) 
					return false;
				final int itemCount = mListView.getAdapter().getCount();
				final int firstTop = mListView.getChildAt(0).getTop();
				final int listPadding = mListView.getListPaddingTop();
				final int lastBottom = mListView.getChildAt(childCount - 1).getBottom();
				final int end = mListView.getHeight() - mListView.getPaddingBottom();
				final int firstVisiblePosition = mListView.getFirstVisiblePosition();
				int deltaY = y - mMotionDownLastY;
				if (firstTop >= listPadding && deltaY > 0) {
					mMoveAction = PULL_UP_FOR_REFERSH;
		            return true;
		        }
				else if (firstVisiblePosition + childCount >= itemCount && lastBottom <= end && deltaY < 0) {
					mMoveAction = PULL_DOWN_FOR_LOAD;
		        	return true;
		        }
				break;
			case MotionEvent.ACTION_UP:			
			case MotionEvent.ACTION_CANCEL:
				break;
		}
		return false;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int y = (int) event.getRawY();
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				break;
			case MotionEvent.ACTION_MOVE:
				int deltaY = y - mMotionDownLastY;
				if (mMoveAction == PULL_UP_FOR_REFERSH) {
		            if(ListViewTopAndPullDown(deltaY))
		            	mMotionDownLastY = y;
				} else if (mMoveAction == PULL_DOWN_FOR_LOAD) {
		        	if(ListViewBottomAndPullUp(deltaY))
		        		mMotionDownLastY = y;
				}
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				if (MotionUp(event))
					mMotionDownLastY = y;
				break;
		}
		return true;
	}
	
	public boolean ListViewTopAndPullDown(int delta) {
		if(!mIsRefreshing && mRefreshAndLoadListener != null) {
			mHeaderIncremental += (int) Math.ceil((double)delta / 2);
			if(mHeaderIncremental >= 0){
				setHeaderHeight(mHeaderIncremental);
				checkHeaderViewState();
			}
			return true;
		}
		return false;
	}

	public boolean ListViewBottomAndPullUp(int delta) {
		if (!mIsLoading && mRefreshAndLoadListener != null) {
			myHandler.sendEmptyMessage(WHAT_ON_LOADING);
			return true;
		}
		return false;
	}

	public boolean MotionUp(MotionEvent ev) {
		// 避免和点击事件冲突
		if(mHeaderViewParams.height > 0){
			// 判断头文件拉动的距离与设定的高度，小了就隐藏，多了就固定高度
			int x = mHeaderIncremental - mDefaultHeaderHeight;
			Timer timer = new Timer(true);
			if(x < 0)
				timer.scheduleAtFixedRate(new HideHeaderViewTask(), 0, 10);
			else
				timer.scheduleAtFixedRate(new ShowHeaderViewTask(), 0, 10);
			
			return true;
		}
		return false;
	}
	
	/**
	 * 自动隐藏动画
	 * @author MissWhen
	 *
	 */
	class HideHeaderViewTask extends TimerTask{
		@Override
		public void run() {
			mHeaderIncremental -= AUTO_INCREMENTAL;
			if(mHeaderIncremental > 0)
				myHandler.sendEmptyMessage(WHAT_SET_HEADER_HEIGHT);
			else{
				mHeaderIncremental = 0;
				myHandler.sendEmptyMessage(WHAT_SET_HEADER_HEIGHT);
				cancel();
			}
		}
	}
	
	/**
	 * 自动显示动画
	 * @author MissWhen
	 *
	 */
	class ShowHeaderViewTask extends TimerTask{
		@Override
		public void run() {
			mHeaderIncremental -= AUTO_INCREMENTAL;
			if(mHeaderIncremental > mDefaultHeaderHeight)
				myHandler.sendEmptyMessage(WHAT_SET_HEADER_HEIGHT);
			else{
				mHeaderIncremental = mDefaultHeaderHeight;
				myHandler.sendEmptyMessage(WHAT_SET_HEADER_HEIGHT);
				if(!mIsRefreshing){
					mIsRefreshing = true;
					myHandler.sendEmptyMessage(WHAT_ON_REFRESHING);
				}
				cancel();
			}
		}
	}
	
	/**
	 * 处理各种状态
	 * @author MissWhen
	 *
	 */
	static class MyHandler extends Handler {
		WeakReference<RefreshAndLoadListView> thisListView;

        MyHandler(RefreshAndLoadListView listView) {
        	thisListView = new WeakReference<RefreshAndLoadListView>(listView);
        }
        
        @Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			
				case WHAT_BEFORE_REFRESH:
					thisListView.get().mIsRefreshing= false;
					thisListView.get().mHeaderArrowView.setVisibility(View.VISIBLE);
					thisListView.get().mHeaderProgressView.setVisibility(View.INVISIBLE);
					thisListView.get().mHeaderTextView.setVisibility(View.VISIBLE);
					thisListView.get().mHeaderTextView.setText(R.string.refreshandload_header_pulltorefresh);
					if(thisListView.get().mLastRefreshTime!=null) {
						thisListView.get().mHeaderDateView.setVisibility(View.VISIBLE);
						thisListView.get().mHeaderDateView.setText(thisListView.get().getResources().getString(R.string.refreshandload_header_date) + thisListView.get().mDateFormat.format(thisListView.get().mLastRefreshTime));
					}
					thisListView.get().setHeaderHeight(0);
					break;
				case WHAT_ON_REFRESHING:
					if(thisListView.get().mRefreshAndLoadListener != null){
						thisListView.get().mIsRefreshing = true;
						thisListView.get().mHeaderTextView.setText(R.string.refreshandload_header_refershing);
						thisListView.get().mHeaderArrowView.clearAnimation();// 要清除掉动画，否则无法隐藏
						thisListView.get().mHeaderArrowView.setVisibility(View.INVISIBLE);
						thisListView.get().mHeaderProgressView.setVisibility(View.VISIBLE);
						thisListView.get().mRefreshAndLoadListener.onRefresh();
					}
					break;			
				case WHAT_AFTER_REFRESH :
					thisListView.get().mIsRefreshing = false;
					thisListView.get().mHeaderViewState = HEADER_VIEW_STATE_IDLE;
					thisListView.get().mHeaderArrowView.setVisibility(View.VISIBLE);
					thisListView.get().mHeaderProgressView.setVisibility(View.INVISIBLE);
					thisListView.get().mHeaderTextView.setText(R.string.refreshandload_header_pulltorefresh);
					if(thisListView.get().mLastRefreshTime!=null) {
						thisListView.get().mHeaderDateView.setVisibility(View.VISIBLE);
						thisListView.get().mHeaderDateView.setText(thisListView.get().getResources().getString(R.string.refreshandload_header_date) + thisListView.get().mDateFormat.format(thisListView.get().mLastRefreshTime));
					}
					thisListView.get().setHeaderHeight(0);
					break;
				case WHAT_BEFOREORAFTER_LOAD :
					thisListView.get().mIsLoading = false;
					thisListView.get().mFooterTextView.setText(R.string.refreshandload_footer_readyload);
					thisListView.get().mFooterProgressView.setVisibility(View.INVISIBLE);
					break;
				case WHAT_ON_LOADING :
					if(thisListView.get().mRefreshAndLoadListener != null){
						thisListView.get().mIsLoading = true;
						thisListView.get().mFooterTextView.setText(R.string.refreshandload_footer_loading);
						thisListView.get().mFooterProgressView.setVisibility(View.VISIBLE);
						thisListView.get().mRefreshAndLoadListener.onLoad();
					}
					break;
				case WHAT_SET_HEADER_HEIGHT :
					thisListView.get().setHeaderHeight(thisListView.get().mHeaderIncremental);
					break;
				case WHAT_SHOW_FOOTER_VIEW:
					thisListView.get().showFooterView();
					break;
				case WHAT_HIDE_FOOTER_VIEW:
					thisListView.get().hideFooterView();
					break;			
				default:
					break;
					
			}
		}
	}

}
