package com.nagarro.remotelearning.Exercise2.classes;

import java.time.LocalDate;

import com.nagarro.remotelearning.Exercise2.enum_classes.Days;
import com.nagarro.remotelearning.Exercise2.enum_classes.Months;

public class Ex2Solutions {
	public static String computeDescriptionFromDate(LocalDate date) {
		String dateBeautified = "the date is ";

		dateBeautified += getDayOfWeek(date) + ", ";

		dateBeautified += getDayOfMonth(date) + " of ";

		dateBeautified += getMonth(date) + " ";

		dateBeautified += date.getYear();

		return dateBeautified;
	}

	private static String getDayOfWeek(LocalDate date) {
		String dayOfWeek = Days.valueOf(date.getDayOfWeek().toString()).toString();

		return formatString(dayOfWeek);
	}

	private static String getDayOfMonth(LocalDate date) {
		String dayOfMonth = Integer.toString(date.getDayOfMonth());

		return dayOfMonth + getSuffixForDayOfMonth(date);
	}

	private static String getSuffixForDayOfMonth(LocalDate date) {
		if (date.getDayOfMonth() == 1 || date.getDayOfMonth() == 21 || date.getDayOfMonth() == 31) {
			return "st";
		} else if (date.getDayOfMonth() == 2 || date.getDayOfMonth() == 22) {
			return "nd";
		} else if (date.getDayOfMonth() == 3 || date.getDayOfMonth() == 23) {
			return "rd";
		} else {
			return "th";
		}
	}

	private static String getMonth(LocalDate date) {
		String month = Months.valueOf(date.getMonth().toString()).toString();

		return formatString(month);
	}

	private static String formatString(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
	}
}
