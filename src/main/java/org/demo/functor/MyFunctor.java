package org.demo.functor;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;


public class MyFunctor<T> implements Functor<T, MyFunctor> {

    private final T value;

    private MyFunctor(T value) {
        this.value = requireNonNull(value);
    }

    public static <T> MyFunctor<T> of(T value) {
        return new MyFunctor<>(value);
    }

    public T get() {
        return value;
    }

    @Override
    public <B> MyFunctor<B> map(Function<? super T, ? extends B> fn) {
        B apply = fn.apply(value);
        return MyFunctor.of(apply);
    }

}
