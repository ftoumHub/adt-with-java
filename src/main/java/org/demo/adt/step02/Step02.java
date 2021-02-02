package org.demo.adt.step02;

import org.demo.adt.step02.Resultat.Droit;
import org.demo.adt.step02.Resultat.Gauche;

import static java.lang.Float.parseFloat;
import static org.demo.adt.step02.Resultat.div;

abstract class Resultat<A, B> {

    private Resultat() { }

    static final class Gauche<A, B> extends Resultat<A, B> {
        A a;

        Gauche(A a) {
            super();
            this.a = a;
        }
    }

    static final class Droit<A, B> extends Resultat<A, B> {
        B b;

        Droit(B b) {
            super();
            this.b = b;
        }
    }

    static Resultat<Float, String> div(Float a, Float b) {
        if (b == 0.) {
            return new Droit<>("Impossible de diviser par zero");
        } else {
            return new Gauche<>(a / b);
        }
    }
}

public class Step02 {
    public static void main(String[] args){
        Resultat<Float,String> resultat = div(parseFloat("2"), parseFloat("0"));
        if (resultat instanceof Gauche){
            Gauche<Float,String> gauche = (Gauche<Float, String>) resultat;
            System.out.println("resultat : " + gauche.a);
        } else {
            Droit<Float,String> droit = (Droit<Float, String>) resultat;
            System.out.println(droit.b);
        }
    }
}
