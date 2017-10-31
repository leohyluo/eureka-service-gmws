package com.ebm.gmws.fw.common.core.aspectj;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class InjectBeanSelfProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof BeanSelfAware) {
			((BeanSelfAware)bean).setProxyObj(bean);
		}
		return bean;
	}

}
