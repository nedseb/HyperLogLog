package com.nedjar.algo.hachage;

import com.nedjar.dataStructure.Tuple;

public class HacheurSedgewick2 implements Hacheur {

	@Override
	public long hache(Tuple key) {
		int h = 0;
		int a = 31415;
		final int b = 27183;
		for (byte i = 0; i < key.size(); i++,a=a*b) {
			h = (a * h + key.get(i));
		}
		return h;
	}
}
