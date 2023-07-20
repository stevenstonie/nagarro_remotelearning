package com.nagarro.remotelearning;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nagarro.remotelearning.classes.Country;
import com.nagarro.remotelearning.classes.CountryHelper;

public class AppTest {
    List<Country> countries;

    @BeforeEach
    public void setup() {
        countries = new ArrayList<>();
        countries.add(new Country("France", "Paris"));
        countries.add(new Country("Italy", "Rome"));
        countries.add(new Country("Spain", "Madrid"));
    }

    @Test
    public void test1stBulletpoint() {
        Collections.sort(countries);

        assertEquals("France", countries.get(0).getName());
        assertEquals("Italy", countries.get(1).getName());
        assertEquals("Spain", countries.get(2).getName());
    }

    @Test
    public void test2ndBulletpoint() {
        CountryHelper.sortCountriesByCapital(countries);

        assertEquals("Madrid", countries.get(0).getCapital());
        assertEquals("Paris", countries.get(1).getCapital());
        assertEquals("Rome", countries.get(2).getCapital());
    }

    @Test
    public void testBinarySearchForCapital() {
        CountryHelper.sortCountriesByCapital(countries);

        Country countryWithCapitalToSearchFor = new Country("France", "Paris");
        int indexOfFoundCapital = CountryHelper.binarySearchForCapital(countryWithCapitalToSearchFor,
                countries, 0, countries.size() - 1);
        String foundCapital = countries.get(indexOfFoundCapital).getCapital();

        assertEquals(countryWithCapitalToSearchFor.getCapital(), foundCapital);
    }
}