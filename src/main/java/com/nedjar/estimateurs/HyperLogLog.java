package com.nedjar.estimateurs;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

import com.nedjar.algo.hachage.Hacheur;
import com.nedjar.algo.hachage.HacheurBernstein;
import com.nedjar.algo.hachage.HacheurFNV;
import com.nedjar.algo.hachage.HacheurJSW;
import com.nedjar.algo.hachage.HacheurJava;
import com.nedjar.algo.hachage.HacheurOAT;
import com.nedjar.algo.hachage.HacheurRot;
import com.nedjar.algo.hachage.HacheurSDBM;
import com.nedjar.algo.hachage.HacheurSax;
import com.nedjar.algo.hachage.HacheurSedgewick;
import com.nedjar.algo.hachage.HacheurSedgewick2;
import com.nedjar.dataStructure.Tuple;

public class HyperLogLog<T extends Iterable<Tuple>> implements Estimateur<T> {

    private double  alpha;
    private Hacheur hacheur;
    private int     b;
    private int     m;
    private long[]  registers;

    public HyperLogLog(Hacheur hacheur, int b) {
        super();
        assert(b >=4);
        this.hacheur = hacheur;
        this.b = b;
        this.m = 1 << b;
        if(b == 4)
            this.alpha = 0.673;
        else if(b==5)
            this.alpha = 0.697;
        else if(b==6)
            this.alpha = 0.709;
        else
            this.alpha = 0.7213 / (1 + 1.079 / m);
        this.registers = new long[m];
    }

    public double estimerCardinalite(T dataSet) {
        for (Tuple tupleCour : dataSet) {
            long hashCour = hacheur.hache(tupleCour);
            if(hashCour==0) continue;
            BitSet bits = BitSet.valueOf(new long[] { hashCour });
            int index = recupererIndex(bits);
            registers[index] = Math.max(registers[index], positionPremierUn(bits));
        }
        double Z = indicator(registers);
        double E = alpha * Math.pow(m, 2) * Z;
        
        if (E <= 2.5 * m) {
            int nbRegistreDifferentDeZero = NbRegistreDifferentDeZero();
            if (nbRegistreDifferentDeZero != 0) E = m * Math.log(m / nbRegistreDifferentDeZero);
        }
        else if (E > 1 / 30d * Math.pow(2, 32)) {
            E = -Math.pow(2, 32) * Math.log(1 - E / Math.pow(2, 32));
        }
        return E;
    }

    private int NbRegistreDifferentDeZero() {
        int nbRegistreDifferentDeZero = 0;
        for (int i = 0; i < registers.length; i++) {
            if (registers[i] == 0) nbRegistreDifferentDeZero++;
        }
        return nbRegistreDifferentDeZero;
    }

    private int positionPremierUn(BitSet bits) {
        BitSet valueBits = bits.get(b, bits.size());
        return valueBits.nextSetBit(0) + 1;
    }

    private int recupererIndex(BitSet bits) {
        BitSet indexBits = bits.get(0, b);
        int index = (int) (indexBits.isEmpty()? 0 : indexBits.toLongArray()[0]);
        return index;
    }

    private double indicator(long[] registers) {
        double res = 0;
        for (int i = 0; i < registers.length; i++) {
            res += Math.pow(2, -registers[i]);
        }
        return 1 / res;
    }

    public static void main(String[] args) {
        ArrayList<Tuple> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            Tuple t = new Tuple(10);
            for (int j = 0; j < t.size(); j++) {
                t.set(j, (byte) (Byte.MAX_VALUE * Math.random()));
            }
            list.add(t);
        }
        Set<Tuple> set = new HashSet<>(100000);
        set.addAll(list);
        System.out.println("Cardinalité réélle :" + set.size());
        
        Hacheur[] hacheurs = new Hacheur[] { 
                new HacheurJava(), 
                //new HacheurAdd(), 
                //new HacheurXOR(), 
                new HacheurRot(),
                new HacheurSax(), 
                new HacheurSDBM(), 
                new HacheurBernstein(), 
                new HacheurSedgewick(),
                new HacheurSedgewick2(), 
                new HacheurFNV(), 
                new HacheurOAT(), 
                new HacheurJSW(),
                };
        
        for (int i = 4; i < 16; i++) {
            for (Hacheur hacheur : hacheurs) {
                HyperLogLog<ArrayList<Tuple>> hyp = new HyperLogLog<>(hacheur, i);
                System.out.println("Cardinalité estimée avec b= " +i +" et " + hacheur.getClass().getName() + " :"
                        + (int)hyp.estimerCardinalite(list));
            }
        }
    }
}
