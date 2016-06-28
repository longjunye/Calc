package com.pan.calc;

/**
 * Created by ricky.ye on 5/22/16.
 */
public class Calc {
	private long firstNumber;
	private long secondNumber;
	private Operation calcChar;
	private long result;
	private long answer;

	public long getFirstNumber() {
		return firstNumber;
	}

	public void setFirstNumber(long firstNumber) {
		this.firstNumber = firstNumber;
	}

	public long getSecondNumber() {
		return secondNumber;
	}

	public void setSecondNumber(long secondNumber) {
		this.secondNumber = secondNumber;
	}

	public Operation getCalcChar() {
		return calcChar;
	}

	public void setCalcChar(Operation calcChar) {
		this.calcChar = calcChar;
	}

	public long getResult() {
		return result;
	}

	public void setResult(long result) {
		this.result = result;
	}

	public long getAnswer() {
		return answer;
	}

	public void setAnswer(long answer) {
		this.answer = answer;
	}
}
