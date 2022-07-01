package com.learning.core.serviceimpl;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learning.core.service.DamImageAssetInfo;


@Component(service = DamImageAssetInfoImpl.class,immediate=true, enabled=false)
public class DamImageAssetInfoImpl implements DamImageAssetInfo {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(DamImageAssetInfoImpl.class);
	private static final String DAM_PATH = "/content/dam/learning";

	@Override
	public String getImageData() {
		// TODO Auto-generated method stub
		return null;
	}

}
