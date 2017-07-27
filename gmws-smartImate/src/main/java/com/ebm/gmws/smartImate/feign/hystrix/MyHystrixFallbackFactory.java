package com.ebm.gmws.smartImate.feign.hystrix;

import org.springframework.stereotype.Component;

import com.ebm.gmws.smartImate.feign.DiseaseFeign;
import com.ebm.gmws.smartImate.feign.DiseaseFeignWithFactory;
import com.ebm.gmws.smartImate.pojo.disease.Drug;

import feign.hystrix.FallbackFactory;

@Component
public class MyHystrixFallbackFactory implements FallbackFactory<DiseaseFeign> {

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
				return "drug not found, pls try again";
			}
		};
	}

}
