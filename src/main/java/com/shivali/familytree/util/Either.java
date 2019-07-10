package com.shivali.familytree.util;

import java.util.Objects;
import java.util.Optional;

public final class Either<L, R> {

    private final Optional<L> left;
    private final Optional<R> right;

    public static <L, R> Either<L, R> left(L value) {
        return new Either<>(Optional.of(value), Optional.empty());
    }

    public static <L, R> Either<L, R> right(R value) {
        return new Either<>(Optional.empty(), Optional.of(value));
    }

    private Either(Optional<L> l, Optional<R> r) {
        left = l;
        right = r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Either<?, ?> either = (Either<?, ?>) o;
        return left.equals(either.left) &&
                right.equals(either.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    public R getRight() {
        return right.orElseGet(null);
    }

    public L getLeft() {
        return left.orElseGet(null);
    }

    public boolean isLeft() {
        return left.isPresent();
    }

    public boolean isRight() {
        return right.isPresent();
    }

}