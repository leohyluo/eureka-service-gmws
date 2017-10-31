/*package com.ebm.gmws.rpc.disease;

import org.springframework.stereotype.Component;

import com.ebm.gmws.pojo.domain.Drug;

@Component
public class DiseaseFeignFallback implements DiseaseFeign {

	@Override
	public String getDetail() {
		return "invoke gmws-knowleadge-disease failed, return by hystrix";
	}

	@Override
	public Drug postDrugDetail(Drug drug) {
		Drug d = new Drug();
		d.setDrugId("0");
		return d;
	}

	@Override
	public Drug getDrugDetail(Drug drug) {
		Drug d = new Drug();
		d.setDrugId("0");
		return d;
	}

}
*/