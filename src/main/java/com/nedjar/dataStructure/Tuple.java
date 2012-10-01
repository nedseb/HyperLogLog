package com.nedjar.dataStructure;

public class Tuple{
	/**
	 * 
	 * Valeur du tuple. les attributs sont codé par des entier court la valeur 0
	 * corespond aux ALL
	 * 
	 */
	private byte[]	val;
	private byte	len;

	public Tuple(byte taille) {
		val = new byte[taille];
		len = taille;
	}
	
	public byte size() {
		return len;
	}

	public void set(byte i, byte valeur) {
		assert (i >= 0 && i < val.length);
		assert (valeur >= 0);
		val[i] = valeur;
	}

	public byte get(byte i) {
		assert (i >= 0 && i < val.length);
		return val[i];
	}

	public int hashCode() {
		int h = 0;
		for (int i = 0; i < len; i++) {
			h = 31 * h + val[i];
		}
		return h;
	}
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer(val.length*3);
		for (int i = 0; i < val.length; i++) {
			str.append(val[i]+" ");	
		}
		return str.toString();
	}
}
