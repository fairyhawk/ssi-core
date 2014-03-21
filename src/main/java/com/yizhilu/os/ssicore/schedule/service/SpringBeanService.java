package com.yizhilu.os.ssicore.schedule.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;


@SuppressWarnings("unchecked")
@Service("springBeanService")
public class SpringBeanService implements Serializable {

	private static final long serialVersionUID = -2228376078979553838L;
	
	private ApplicationContext applicationContext;
	
	
	public <T> T getWebBean(Class<T> clazz, String beanName) {
		ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();


		return (T) context.getBean(beanName);
	}

	public <T> T getBean(Class<T> clazz, String beanName) {


		return (T) applicationContext.getBean(beanName);
	}

	
	/**
	 * @return the application
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @param application the application to set
	 */
	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}