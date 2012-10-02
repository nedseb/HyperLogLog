package com.nedjar.algo.hachage;

import com.nedjar.dataStructure.Tuple;

public class HacheurSDBM implements Hacheur {

	@Override
	public long hache(Tuple key) {
		int h = 0;
		for (byte i = 0; i < key.size(); i++) {
			h=key.get(i) + (h<<6) + (h<<16) -h;
		}
		return h;
	}

}