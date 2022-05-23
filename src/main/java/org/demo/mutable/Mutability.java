package org.demo.mutable;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

/**
 * D'après:
 * https://medium.com/@hashan.mahesh01/what-is-shared-mutability-in-java-3914e8e3d789#
 */
public class Mutability {

    public static void main(String[] args) {
        Sample sample = new Sample();

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        IntStream.range(0, 100).forEach(__ -> executor.execute(sample::setNumber));

        while(!executor.isTerminated()) {
            executor.shutdown();
        }

        System.out.println(sample.getNumber());
    }
}
