package com.example.android.commons;

import com.example.android.exceptions.ExampleException;

public interface IRequestListener<T> {
  
	/**
	 * 监听请求完成
	 * @param bean
	 */
    public void onComplete(T bean);
    
    /**
     * 监听错误
     * @param fault
     */
    public void onFault(Throwable fault);
 
    /**
     * 监听自定义异常
     * @param exampleException
     */
    public void onExampleException(ExampleException exampleException);
 
}