package org.demo.adt.step01;

import static io.vavr.API.println;
import static org.demo.adt.step01.Cas.Droit;
import static org.demo.adt.step01.Cas.Gauche;

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
}

public class Step01 {

    public static void main(String[] args){
        Resultat<Float, String> quatreDivParDeux = div(4f, 2f);
        Resultat<Float, String> divParZero = div(2f, 0f);

        printResult(quatreDivParDeux);
        printResult(divParZero);
    }

    private static Resultat<Float, String> div(Float a, Float b) {
        if (b == 0.)    return new Resultat<>(Droit, null, "Impossible de diviser par zero");
        else            return new Resultat<>(Gauche, a / b, null);
    }

    private static void printResult(Resultat<Float, String> resultat) {
        switch (resultat.cas){
            case Gauche: println("resultat : " + resultat.a);
                break;
            case Droit: println(resultat.b);
                break;
            default:
                assert false;
        }
    }
}
