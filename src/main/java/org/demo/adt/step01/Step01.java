package org.demo.adt.step01;

import static java.lang.Float.parseFloat;
import static org.demo.adt.step01.Cas.Droit;
import static org.demo.adt.step01.Cas.Gauche;
import static org.demo.adt.step01.Resultat.div;

enum Cas { Gauche, Droit }

class Resultat<A, B> {
    Cas cas;
    A a;
    B b;

    Resultat(Cas cas, A a, B b) {
        this.cas = cas;
        this.a = a;
        this.b = b;
    }

    static Resultat<Float, String> div(Float a, Float b) {
        if (b == 0.) {
            return new Resultat<>(Droit, null, "Impossible de diviser par zero");
        } else {
            return new Resultat<>(Gauche, a / b, null);
        }
    }
}

public class Step01 {
    public static void main(String[] args){
        Resultat<Float,String> resultat = div(parseFloat("2"), parseFloat("0"));
        switch (resultat.cas){
            case Gauche:
                System.out.println("resultat : " + resultat.a);
                break;
            case Droit:
                System.out.println(resultat.b);
                break;
            default:
                assert false;
        }
    }
}
