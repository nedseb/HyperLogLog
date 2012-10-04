package com.nedjar.estimateurs;

import java.util.ArrayList;
import java.util.BitSet;

import com.nedjar.algo.hachage.Hacheur;
import com.nedjar.algo.hachage.HacheurFNV;
import com.nedjar.dataStructure.Tuple;

public class HyperLogLog<T extends Iterable<Tuple>> implements Estimateur<T> {

	private double alpha;
	private Hacheur	hacheur;
	private int		b;
	private int		m;
	private long[]	registers;

	public HyperLogLog(Hacheur hacheur, int b) {
		super();
		this.hacheur = hacheur;
		this.b = b;
		this.m = 1<<b;
		this.alpha	= 0.7213/(1 + 1.079/m);
		this.registers = new long[m];
	}

	public double estimerCardinalite(T dataSet) {
		int res = 0;
		for (Tuple tupleCour : dataSet) {
			long hashCour = hacheur.hache(tupleCour);
			BitSet bits = BitSet.valueOf(new long[]{hashCour});
			BitSet indexBits = bits.get(0, b);
			BitSet valueBits = bits.get(b, bits.size());
			int index = (int)indexBits.toLongArray()[0];
			registers[index] = Math.max(registers[index], valueBits.nextClearBit(0));
		}
		double Z = indicator(registers);
		return alpha*Math.pow(m, 2)*Z;
	}

	private double indicator(long[] registers) {
		double res = 0;
		for (int i = 0; i < registers.length; i++) {
			res += Math.pow(2, -registers[i]);
		}
		return 1/res;
	}

	public static void main(String[] args) {
		Tuple t = new Tuple((byte) 5);
		for (byte i = 0; i < 5; i++) {
			t.set(i, (byte)(Byte.MAX_VALUE*Math.random()));
		}
		System.out.println(t);
		ArrayList<Tuple> list = new ArrayList<>();
		list.add(t);
		HyperLogLog<ArrayList<Tuple>> hyp = new HyperLogLog<>(new HacheurFNV(), 10);
		System.out.println(hyp.estimerCardinalite(list));
	}
}
