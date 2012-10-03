package com.nedjar.algo;

import java.util.BitSet;
import java.util.Enumeration;

public class BitSetEnumeration implements Enumeration<BitSet> {
	private BitSet	b;
	private int nbits;

	public BitSetEnumeration(int nbits) {
		super();
		this.b = new BitSet(nbits);
		this.nbits = nbits;
	}

	@Override
	public boolean hasMoreElements() {
		return b.cardinality() < nbits;
	}

	@Override
	public BitSet nextElement() {
		int i;
		for (i = 0; i < b.size(); i++) {
			b.set(i, !b.get(i));
			if (b.get(i)) {
				return b;
			}
		}
		b.set(i);
		return b;
	}

	public static void next(BitSet b) {
		int i;
		for (i = 0; i < b.size(); i++) {
			b.set(i, !b.get(i));
			if (b.get(i)) {
				return;
			}
		}
		b.set(i);
	}

	public static void main(String[] args) {
		BitSetEnumeration bitSetEnum = new BitSetEnumeration(5);
		for (Enumeration<BitSet> e = bitSetEnum; e.hasMoreElements();)
			System.out.println(e.nextElement());

	}
}
