package CoronaPandemic;

import java.util.List;

public interface DataManipulationInterface {

    List<Country> sortByCFR(List<Country> countries);

    List<Country> sortByTotalCases(List<Country> countries);

    List<Country> sortByHeatlhEx(List<Country> countries);

    List<Country> sortByCountry(List<Country> countries);

    List<Country> sortByTotalDeaths(List<Country> countries);

    List<Country> sortByTotalRecovered(List<Country> countries);
}
