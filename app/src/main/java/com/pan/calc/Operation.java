package com.pan.calc;

/**
 * Created by ricky.ye on 5/22/16.
 */
public enum Operation {
	PLUS("+") {
		long apply(long x, long y) { return x + y; }
	},
	MINUS("-") {
		long apply(long x, long y) { return x - y; }
	},
	TIMES("*") {
		long apply(long x, long y) { return x * y; }
	},
	DIVIDE("/") {
		long apply(long x, long y) { return x / y; }
	};
	private final String symbol;
	Operation(String symbol) { this.symbol = symbol; }
	@Override public String toString() { return symbol; }
	abstract long apply(long x, long y);
}
