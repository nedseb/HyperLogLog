package com.nedjar.algo.hachage;

import com.nedjar.dataStructure.Tuple;

public class HacheurJava implements Hacheur {

	@Override
	public long hache(Tuple key) {
		return key.hashCode();
	}
}
