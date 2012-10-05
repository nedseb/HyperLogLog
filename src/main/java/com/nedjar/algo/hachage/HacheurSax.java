package com.nedjar.algo.hachage;

import com.nedjar.dataStructure.Tuple;

public class HacheurSax implements Hacheur {

	@Override
	public int hache(Tuple key) {
		int h = 0;
		for (byte i = 0; i < key.size(); i++) {
			h^=(h<<5) + (h>>2) +  key.get(i);
		}
		return h;
	}

}
