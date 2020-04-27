package CoronaPandemic;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DataManipulation implements DataManipulationInterface{

    @Override
    public List<Country> sortByCFR(List<Country> countries) {
        countries = countries.stream().sorted((o1, o2) -> {
            if(o1.getCfr() < o2.getCfr()){
                return -1;
            }
            else if(o1.getCfr() > o2.getCfr()){
                return 1;
            }
            else {
                return 0;
            }
        }).collect(Collectors.toList());

        return countries;
    }

    @Override
    public List<Country> sortByTotalCases(List<Country> countries) {
        countries = countries.stream().sorted((o1, o2) -> {
            if(o1.getTotal_cases() < o2.getTotal_cases()){
                return -1;
            }
            else if(o1.getTotal_cases() > o2.getTotal_cases()){
                return 1;
            }
            else {
                return 0;
            }
        }).collect(Collectors.toList());

        return countries;
    }

    public List<Country> sortByHeatlhEx(List<Country> countries) {
        countries = countries.stream().sorted((o1, o2) -> {
            if(o1.getHealthExpenditure() < o2.getHealthExpenditure()){
                return -1;
            }
            else if(o1.getHealthExpenditure() > o2.getHealthExpenditure()){
                return 1;
            }
            else {
                return 0;
            }
        }).collect(Collectors.toList());

        return countries;
    }

    @Override
    public List<Country> sortByCountry(List<Country> countries) {
        countries = countries.stream().sorted(Comparator.comparing(Country::getName)).collect(Collectors.toList());
        return countries;
    }

    @Override
    public List<Country> sortByTotalDeaths(List<Country> countries) {
        countries = countries.stream().sorted((o1, o2) -> {
            if(o1.getDeaths() < o2.getDeaths()){
                return -1;
            }
            else if(o1.getDeaths() > o2.getDeaths()){
                return 1;
            }
            else {
                return 0;
            }
        }).collect(Collectors.toList());
        return countries;
    }

    @Override
    public List<Country> sortByTotalRecovered(List<Country> countries) {
        countries = countries.stream().sorted((o1, o2) -> {
            if(o1.getRecovered() < o2.getRecovered()){
                return -1;
            }
            else if(o1.getRecovered() > o2.getRecovered()){
                return 1;
            }
            else {
                return 0;
            }
        }).collect(Collectors.toList());
        return countries;
    }
}
