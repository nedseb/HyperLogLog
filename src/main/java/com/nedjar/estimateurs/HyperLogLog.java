package com.nedjar.estimateurs;

import com.nedjar.algo.hachage.Hacheur;
import com.nedjar.dataStructure.Tuple;

public class HyperLogLog<T extends Iterable<Tuple>> implements Estimateur<T> {
	
	private Hacheur hacheur;
	private int b;
	private int m;
	private long[] registers;
	
	public HyperLogLog(Hacheur hacheur, int b) {
		super();
		this.hacheur = hacheur;
		this.b = b;
		this.m = 2^b;
		this.registers = new long[m];
	}



	public int estimerCardinalite(T dataSet) {	
		int res = 0;
		for (Tuple tupleCour : dataSet) {
			long hashCour = hacheur.hache(tupleCour);
			long index = hashCour >> (Long.SIZE - b);
		}
		return res;
	}
}
