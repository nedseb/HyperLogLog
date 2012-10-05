package com.nedjar.algo.hachage;

import com.nedjar.dataStructure.Tuple;

public class HacheurSedgewick implements Hacheur {

	@Override
	public int hache(Tuple key) {
		int h = 0;
		int a = 127;
		for (byte i = 0; i < key.size(); i++) {
			h=(a*h + key.get(i));
		}
		return h;
	}
}