package br.com.wmw.comprastc.util;

import totalcross.util.Date;
import totalcross.util.InvalidDateException;

public class DateUtils {
	
	private DateUtils() {
	}

	public static Date convert(String stringDate, Byte formatDate) throws InvalidDateException {
		Date date = new Date();
		try {
			date = new Date(stringDate, formatDate);
		} catch (InvalidDateException e) {
			throw new InvalidDateException();
		}
		return date;
	}
	
	public static boolean isDataAtualOuFutura(String stringDate, Byte formatDate) throws InvalidDateException {
		Date date = DateUtils.convert(stringDate, formatDate);
		return date.isAfter(new Date()) || date.equals(new Date());
	}
}
