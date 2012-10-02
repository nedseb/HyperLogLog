package com.nedjar.dataStructure;

public class Tuple{
	/**
	 * 
	 * Valeur du tuple. les attributs sont codé par des entier court. 
	 * La valeur 0 corespond aux ALL
	 * 
	 */
	private byte[]	valeurs;

	public Tuple(byte taille) {
		valeurs = new byte[taille];
	}
	
	public int size() {
		return valeurs.length;
	}

	public void set(byte i, byte valeur) {
		assert (i >= 0 && i < valeurs.length);
		assert (valeur >= 0);
		valeurs[i] = valeur;
	}

	public byte get(byte i) {
		assert (i >= 0 && i < valeurs.length);
		return valeurs[i];
	}

	public int hashCode() {
		int h = 0;
		for (int i = 0; i < valeurs.length; i++) {
			h = 31 * h + valeurs[i];
		}
		return h;
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
