package CoronaPandemic;

public enum CountriesList implements ReadCountryDataFile{
    USA,
    ESP,
    ITA,
    FRA,
    DEU,
    IRN,
    CHN,
    RUS;

    final String name;
    final double gdp;
    final double healthExpenditurePercentage;

    CountriesList() {
        String[] country = null;
        for(String arr[] : countriesData){
            if(this.toString().equals(arr[0])){
                country = arr;
            }
        }
        this.name = country[1];
        this.gdp = Double.parseDouble(country[2]);
        this.healthExpenditurePercentage = Double.parseDouble(country[3]);
    }

    public double getGdp() {
        return gdp;
    }

    public double getHealthExpenditurePercentage() {
        return healthExpenditurePercentage;
    }

    public String getName() {
        return name;
    }
}
