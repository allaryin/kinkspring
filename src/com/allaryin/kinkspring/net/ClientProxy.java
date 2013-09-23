package com.allaryin.kinkspring.net;

import com.allaryin.kinkspring.Kinkspring;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit() {
		Kinkspring.log.info("ClientProxy.preInit...");
	}

}
