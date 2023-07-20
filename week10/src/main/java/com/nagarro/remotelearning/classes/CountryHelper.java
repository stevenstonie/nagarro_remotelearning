package com.nagarro.remotelearning.classes;

import java.util.Collections;
import java.util.List;

public class CountryHelper {
	public static void sortCountriesByCapital(List<Country> countries) {
		Collections.sort(countries, Country::compare);
	}

	public static int binarySearchForCapital(Country country, List<Country> countries, int low, int high) {
		if (low > high) {
			return -1;
		} else {
			int mid = (low + high) / 2;

			if (Country.compare(country, countries.get(mid)) == 0) {
				return mid;
			} else if (Country.compare(country, countries.get(mid)) > 0) {
				return binarySearchForCapital(country, countries, mid + 1, high);
			} else {
				return binarySearchForCapital(country, countries, low, mid - 1);
			}
		}
	}
}
