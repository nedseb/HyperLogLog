package com.nedjar.algo.hachage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.nedjar.dataStructure.Tuple;

public class TestHacheur {

	public static void main(String[] args) throws IOException,
			InterruptedException {

		// Hacheur h = new HacheurJava();
		// Hacheur h = new HacheurAdd();
		// Hacheur h = new HacheurXOR();
		// Hacheur h = new HacheurRot();
		// Hacheur h = new HacheurSax();
		// Hacheur h = new HacheurSDBM();
		// Hacheur h = new HacheurBernstein();
		// Hacheur h = new HacheurSedgewick();
		// Hacheur h = new HacheurSedgewick2();
		Hacheur h = new HacheurFNV();
		// Hacheur h = new HacheurOAT();
		// Hacheur h = new HacheurJSW();
		long[] tableOcc = new long[64];
		long debut = System.nanoTime();

		try (BufferedReader is = new BufferedReader(new FileReader(args[0]),
				200000)) {
			String lineCou = is.readLine();// saut de la ligne d'entete
			String[] strTuple = lineCou.split(" ");
			Tuple tuple = new Tuple((byte) (strTuple.length - 1));

			while ((lineCou = is.readLine()) != null) {
				strTuple = lineCou.split(" ");
				assert (strTuple.length < Byte.MAX_VALUE);
				for (byte i = 0; i < tuple.size(); i++)
					tuple.set(i, Byte.parseByte(strTuple[i]));
				tableOcc[Long.numberOfLeadingZeros(h.hache(tuple))
						% tableOcc.length]++;

				lineCou = is.readLine();
			}
		}
		for (int i = 0; i < tableOcc.length; i++) {
			System.out.println(i + " " + tableOcc[i]);
		}
		long fin = System.nanoTime();
		System.err.println("durée totale d'exécution : " + (fin - debut)
				/ 1000000000. + "s");
	}

}
