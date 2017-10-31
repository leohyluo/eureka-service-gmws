package com.ebm.gmws.rpc.disease;

import org.springframework.stereotype.Component;

import com.ebm.gmws.pojo.domain.Drug;

import feign.hystrix.FallbackFactory;

@Component
public class DiseaseFallbackFactory implements FallbackFactory<DiseaseFeign> {

	@Override
	public DiseaseFeign create(Throwable e) {
		// TODO Auto-generated method stub
		System.out.println("fall back reason is " + e.getMessage());
		return new DiseaseFeignWithFactory() {
			
			@Override
			public Drug postDrugDetail(Drug drug) {
				// TODO Auto-generated method stub
				return new Drug("-1");
			}
			
			@Override
			public Drug getDrugDetail(Drug drug) {
				// TODO Auto-generated method stub
				return new Drug("-1");
			}
			
			@Override
			public String getDetail() {
				// TODO Auto-generated method stub
				System.out.println("执行fallback方法");
				return "drug not found, pls try again";
			}
		};
	}

}
