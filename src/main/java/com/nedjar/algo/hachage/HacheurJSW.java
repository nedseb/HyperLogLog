package com.nedjar.algo.hachage;

import java.util.Random;

import com.nedjar.dataStructure.Tuple;

public class HacheurJSW implements Hacheur {
	static long tab[];
	static byte tab2[];
	static
	{
		tab = new long[256];
		tab2 = new byte[256];
		Random r = new Random();
		r.nextBytes(tab2);
		for(int i=0;i<tab.length;i++)
			tab[i] = r.nextLong();
	}
	
	public int hache(Tuple key) {
		long h = 16777551;

		for (byte i = 0; i < key.size(); i++) {
			h = ( h << 1 | h >> 31 ) + tab[tab2[key.get(i)]< 0 ?-tab2[key.get(i)] :tab2[key.get(i)]];
		}
		return (int)h;
	}
}
