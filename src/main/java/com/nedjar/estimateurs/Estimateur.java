package com.nedjar.estimateurs;

public interface Estimateur<T  extends Iterable<?>> {
	double estimerCardinalite( T dataSet);
}
