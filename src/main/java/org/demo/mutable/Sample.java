package org.demo.mutable;

public class Sample {

    double number = 0;

    public synchronized void setNumber() {
        for(int i = 0; i < 10 ; i++) {
            number += i;
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();;
            }
        }
    }

    public double getNumber() {
        return number;
    }
}
