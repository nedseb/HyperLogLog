package com.nedjar.algo.hachage;

import com.nedjar.dataStructure.Tuple;

public class HacheurFNV implements Hacheur {

	@Override
	public long hache(Tuple key) {
		long h = 2166136261l;
		for (byte i = 0; i < key.size(); i++) {
			h = ( h * 16777619l ) ^ key.get(i);
		}
		return h;
	}
}
