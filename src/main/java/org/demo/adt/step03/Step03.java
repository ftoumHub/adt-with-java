package org.demo.adt.step03;

import io.vavr.Tuple;
import io.vavr.Tuple0;
import io.vavr.Tuple1;
import org.demo.adt.step03.Resultat.Cases;

import static io.vavr.API.println;
import static org.demo.adt.step03.Resultat.div;

abstract class Resultat<A, B> {

    private Resultat() {}

    public interface Cases<R, A, B> {
        R casGauche(A gauche);
        R casDroit(B droit);
    }

    abstract <R> R match(Cases<R, A, B> cases);

    static final class Gauche<A, B> extends Resultat<A, B> {
        A a;

        Gauche(A a) {
            super();
            this.a = a;
        }

        <R> R match(Cases<R, A, B> cases) {
            return cases.casGauche(a);
        }
    }

    static final class Droit<A, B> extends Resultat<A, B> {
        B b;

        Droit(B b) {
            super();
            this.b = b;
        }

        <R> R match(Cases<R, A, B> cases) {
            return cases.casDroit(b);
        }
    }

    static Resultat<Float, String> div(Float a, Float b) {
        if (b == 0.)    return new Droit<>("Impossible de diviser par zero");
        else            return new Gauche<>(a / b);
    }
}

public class Step03 {

    public static void main(String[] args) {

        final Resultat<Float, String> div = div(2f, 0f);

        div.match(new Cases<Tuple, Float, String>() {
            public Tuple1 casGauche(Float gauche) {
                println("res : " + gauche);
                return Tuple.of(gauche);
            }

            public Tuple0 casDroit(String droit) {
                println(droit);
                return Tuple.empty();
            }
        });
    }
}
