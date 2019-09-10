package com.javacountries.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;


@RestController
@RequestMapping("/countries")
public class CountryController {

    // return the names of all the countries alphabetically
    // /names/all
    @GetMapping(value = "/names/all", produces = {"application/json"})
    public ResponseEntity<?> getAllCountries() {
        ArrayList<Country> rtnCountries = CountriesApplication.ourCountryList.countryList;
        rtnCountries.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    //return the countries alphabetically that begin with the given letter
    // /names/start/{letter}
    @GetMapping(value = "/names/start/{letter}", produces = {"application/json"})
    public ResponseEntity<?> getCountryByFirstLetter(@PathVariable char letter) {
        ArrayList<Country> rtnCountries = CountriesApplication.ourCountryList
                .findCountries(c -> c.getName().toUpperCase().charAt(0) == Character.toUpperCase(letter));
        rtnCountries.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    //return the countries alphabetically that have a name equal to or longer than the given length
    // /names/size/{number}
    @GetMapping(value = "/names/size/{number}", produces = {"application/json"})
    public ResponseEntity<?> getNameEqualOrLonger(@PathVariable long number) {
        ArrayList<Country> rtnCountries = CountriesApplication.ourCountryList
                .findCountries(c -> c.getName().length() >= number);
        rtnCountries.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    //return the countries that have a population equal to or greater than the given population
    // /population/size/{people}
    @GetMapping(value = "/population/size/{people}", produces = {"application/json"})
    public ResponseEntity<?> getPopEqualOrGreater(@PathVariable long people) {
        ArrayList<Country> rtnCountries = CountriesApplication.ourCountryList
                .findCountries(c -> c.getPopulation() >= people);
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    //return the country with the smallest population
    ///population/min
    @GetMapping(value = "/population/min", produces = {"application/json"})
    public ResponseEntity<?> getSmallestPop() {
        ArrayList<Country> rtnCountries = CountriesApplication.ourCountryList.countryList;
        rtnCountries.sort((c1, c2) -> ((int)(c1.getPopulation() - c2.getPopulation())));
        return new ResponseEntity<>(rtnCountries.subList(0,1), HttpStatus.OK);
    }

    //return the country with the largest population
    ///population/max
    @GetMapping(value = "/population/max", produces = {"application/json"})
    public ResponseEntity<?> getLargestPop() {
        ArrayList<Country> rtnCountries = CountriesApplication.ourCountryList.countryList;
        rtnCountries.sort((c1, c2) -> ((int)(c2.getPopulation() - c1.getPopulation())));
        return new ResponseEntity<>(rtnCountries.subList(0,1), HttpStatus.OK);
    }
    //Stretch Goal

    ///population/median
    //return the country with the median population
    ///age/age/{age}

    //return the countries that have a median age equal to or greater than the given age
    ///age/min

    //return the country with the smallest median age
    ///age/max

    //return the country the the greatest median age
    //Stretch Goal

    ///age/median
    //return the country with the median median age

}
