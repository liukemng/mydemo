package com.example.android.models;

import java.util.Date;

public class TestMessageModel{
	
	public TestMessageModel(int id,String title,String detail,String userName,Date createTime){
		this.id=id;
		this.title=title;
		this.detail=detail;
		this.userName=userName;
		this.createTime=createTime;
	}
	
	private int id;
	private String title;
	private String detail;
	private String userName;
	private Date createTime;
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id=id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title=title;
	}
	
	public String getDetail()
	{
		return detail;
	}
	
	public void setDetail(String detail)
	{
		this.detail=detail;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public void setUserName(String userName)
	{
		this.userName=userName;
	}
	
	public Date getCreateTime()
	{
		return createTime;
	}
	
	public void setCreateTime(Date createTime)
	{
		this.createTime=createTime;
	}
	
}