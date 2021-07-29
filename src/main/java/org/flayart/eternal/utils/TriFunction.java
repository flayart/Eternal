package org.flayart.eternal.utils;

import java.util.function.Function;

@FunctionalInterface
public interface TriFunction<T, U, S, R> {
    
    R apply(T t, U u, S s);
    
    default <V> TriFunction<T, U, S, V> andThen(Function<? super R, ? extends V> then) {
        return (t, u, s) -> then.apply(apply(t, u, s));
    }
}
