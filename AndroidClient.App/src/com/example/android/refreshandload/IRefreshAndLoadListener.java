package com.example.android.refreshandload;

/**
 * 刷新或加载监听接口
 * @author MissWhen
 *
 */
public interface IRefreshAndLoadListener {
	
	void onLoad();// 监听加载
	void onRefresh();// 监听刷新
	
}