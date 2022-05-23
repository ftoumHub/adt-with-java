package org.demo.adt.step02;

import org.demo.adt.step02.Resultat.Droit;
import org.demo.adt.step02.Resultat.Gauche;

import static io.vavr.API.println;
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

    static Resultat<String, Float> div(Float a, Float b) {
        if (b == 0.)    return new Gauche<>("Impossible de diviser par zero");
        else            return new Droit<>(a / b);
    }
}

public class Step02 {

    public static void main(String[] args) {
        Resultat<String, Float> quatreDivParDeux = div(4f, 2f);
        Resultat<String, Float> divParZero = div(2f, 0f);

        printResult(quatreDivParDeux);
        printResult(divParZero);
    }

    private static void printResult(Resultat<String, Float> resultat) {
        if (resultat instanceof Gauche){
            Gauche<String, Float> gauche = (Gauche<String, Float>) resultat;
            println("resultat : " + gauche.a);
        } else {
            Droit<String, Float> droit = (Droit<String, Float>) resultat;
            println(droit.b);
        }
    }
}
