package com.ebm.gmws.fw.common.core.aspectj;

/**
 * 在目标对象注入代理对象标识接口
 * @author Administrator
 *
 */
public interface BeanSelfAware {

	void setProxyObj(Object obj);
}
