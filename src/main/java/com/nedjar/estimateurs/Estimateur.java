package com.nedjar.estimateurs;

public interface Estimateur<T  extends Iterable<?>> {
	int estimerCardinalite( T dataSet);
}
