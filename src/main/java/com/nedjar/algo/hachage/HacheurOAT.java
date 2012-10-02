package com.nedjar.algo.hachage;

import com.nedjar.dataStructure.Tuple;

public class HacheurOAT  implements Hacheur {

	@Override
	public long hache(Tuple key) {
		long h = 0l;
		for (byte i = 0; i < key.size(); i++) {
			h += key.get(i);
			h += ( h << 10 );
			h ^= ( h >> 6 );
		} 
		  
		h += ( h << 3 );
		h ^= ( h >> 11 );
		h += ( h << 15 );
				
		return h;
	}
}