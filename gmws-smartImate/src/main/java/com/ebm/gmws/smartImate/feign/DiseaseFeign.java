package com.ebm.gmws.smartImate.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ebm.gmws.smartImate.feign.hystrix.MyHystrixFallbackFactory;
import com.ebm.gmws.smartImate.pojo.disease.Drug;

/**
 * Spring Cloud应用在启动时，Feign会扫描标有@FeignClient注解的接口，生成代理，并注册到Spring容器中。
 * @author leohyluo
 */
//@FeignClient(name = "gmws-knowleadge-disease", fallback = DiseaseFeignFallback.class)
@FeignClient(name = "gmws-knowleadge-disease", configuration = FeignConfiguration.class, fallbackFactory = MyHystrixFallbackFactory.class)
public interface DiseaseFeign {

	/**
	 * 生成代理时Feign会为每个接口方法创建一个RequetTemplate对象，该对象封装了HTTP请求需要的全部信息，请求参数名、请求方法等信息
	 * @return
	 */
	@RequestMapping(value = "/knowleadge/disease/getDetail", method = RequestMethod.GET)
	public String getDetail();
	
	/**
	 * 默认情况下，Feign会将标有@RequestParam注解的参数转换成字符串添加到URL中，将没有注解的参数通过Jackson转换成json放到请求体中。
	 * 注意，如果在@RequetMapping中的method将请求方式指定为POST，那么所有未标注解的参数将会被忽略
	 * @param drug
	 * @return
	 */
	@RequestMapping(value = "/knowleadge/disease/postDrugDetail", method = RequestMethod.POST)
	public Drug postDrugDetail(Drug drug);
	
	@RequestMapping(value = "/knowleadge/disease/getDrugDetail", method = RequestMethod.GET)
	public Drug getDrugDetail(Drug drug);
}
