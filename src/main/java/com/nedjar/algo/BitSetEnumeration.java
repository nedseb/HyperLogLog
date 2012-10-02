package com.nedjar.algo;
import java.util.BitSet;



public class BitSetEnumeration {
	public static void next(BitSet b)
	{
		int i; 
		for(i=0; i< b.size();i++){
			b.set(i,!b.get(i));
			if(b.get(i)){
				return;
			}
		}
		b.set(i);
	}
	
	public static void main(String[] args) 
	{
		for (BitSet b = new BitSet(10); b.cardinality() <= 10;next(b)) {
			System.out.println(b);
		}
	}
}
