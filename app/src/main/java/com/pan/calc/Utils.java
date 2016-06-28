package com.pan.calc;

import java.util.List;
import java.util.Random;

/**
 * Created by ricky.ye on 5/22/16.
 */
public class Utils {
	public static List<Calc> generateCalcList(int count, List<Calc> calcList,CALC_LEVEL calcLevel) {
		int maxNumber = 0;
		Random random =new Random();
		switch (calcLevel) {
			case EASY:
				maxNumber = 20;
				break;
			case MIDDLE:
				maxNumber = 60;
				break;
			case HARD:
				maxNumber = 100;
				break;
			default:
				maxNumber = 20;
				break;
		}

		for (int i = 0; i < count; ++i) {
			Calc calc =new Calc();
			Operation operation = Operation.values()[random.nextInt(4)];
			int firstNumber = random.nextInt(maxNumber) + 1 ;
			int secondNumber = random.nextInt(maxNumber) + 1 ;
			switch (operation) {
				case MINUS:
					if(firstNumber < secondNumber) {
						calc.setFirstNumber(secondNumber);
						calc.setSecondNumber(firstNumber);
					} else {
						calc.setFirstNumber(firstNumber);
						calc.setSecondNumber(secondNumber);
					}
					break;
				case DIVIDE:
					long timeResult = firstNumber * secondNumber ;
					calc.setFirstNumber(timeResult);
					calc.setSecondNumber(firstNumber);
					break;
				default:
					calc.setFirstNumber(firstNumber);
					calc.setSecondNumber(secondNumber);
					break;
			}
			calc.setCalcChar(operation);
			calc.setAnswer(-1);
			calc.setResult(calc.getCalcChar().apply(calc.getFirstNumber(),calc.getSecondNumber()));
			calcList.add(calc);
		}
		return calcList;
	}
}
