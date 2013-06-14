package com.example.android.commons;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import com.example.android.exceptions.ExampleException;

public abstract class RequestListener<T extends ResponseBean> implements IRequestListener<T> {
	
	@SuppressWarnings("unchecked")
    public T parse(String response) {
	    Class<?> c = this.getGenericType();
        try {
            Constructor<T> constructor = (Constructor<T>) c.getDeclaredConstructor(String.class);
            T result = constructor.newInstance(response);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	private Class<?> getGenericType() {
        Type genType = getClass().getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (params.length < 1) {
            throw new RuntimeException("Index outof bounds");
        }
        if (!(params[0] instanceof Class)) {
            return Object.class;
        }
        return (Class<?>) params[0];
    }
	
    public abstract void onComplete(T bean);
    
    public abstract void onFault(Throwable fault);

    public abstract void onExampleException(ExampleException exampleException);

}