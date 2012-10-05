package com.nedjar.algo.hachage;

import com.nedjar.dataStructure.Tuple;

public class HacheurXOR implements Hacheur {

	@Override
	public int hache(Tuple key) {
		int h = 0;
		for (byte i = 0; i < key.size(); i++) {
			h ^= key.get(i);
		}
		return h;
	}

}
