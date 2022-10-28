package br.com.wmw.comprastc.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleUtils {

	private DoubleUtils() {
	}
	
	public static Double round(Double valorDouble, RoundingMode roundingMode){
		Double result = Double.valueOf(0);
		result = BigDecimal.valueOf(valorDouble).setScale(2, roundingMode).doubleValue();
		return result;
	}
	
}