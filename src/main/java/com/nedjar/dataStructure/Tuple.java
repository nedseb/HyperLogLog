package com.nedjar.dataStructure;

import java.util.Arrays;

public class Tuple{
	/**
	 * 
	 * Valeur du tuple. les attributs sont codé par des entier court. 
	 * La valeur 0 corespond aux ALL
	 * 
	 */
	private byte[]	valeurs;

	public Tuple(int taille) {
		valeurs = new byte[taille];
	}
	
	public int size() {
		return valeurs.length;
	}

	public void set(int i, byte valeur) {
		assert (i >= 0 && i < valeurs.length);
		assert (valeur >= 0);
		valeurs[i] = valeur;
	}

	public byte get(int i) {
		assert (i >= 0 && i < valeurs.length);
		return valeurs[i];
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(valeurs);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Tuple other = (Tuple) obj;
		if (!Arrays.equals(valeurs, other.valeurs)) return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer(valeurs.length*3);
		for (int i = 0; i < valeurs.length; i++) {
			str.append(valeurs[i]+" ");	
		}
		return str.toString();
	}
}
