package CoronaPandemic;

public class Country implements CoronaVirusFatalityRate{

    private String name;
    private int total_cases;
    private int recovered;
    private int deaths;
    private double GDP;
    private double healthExpenditurePercentage;
    private double healthExpenditure;

    public Country(String name, int total_cases, int recovered, int deaths, double GDP, double healthExpenditurePercentage) {
        this.name = name;
        this.total_cases = total_cases;
        this.recovered = recovered;
        this.deaths = deaths;
        this.GDP = GDP;
        this.healthExpenditurePercentage = healthExpenditurePercentage;
    }

    public String getName() {
        return name;
    }

    public int getTotal_cases() {
        return total_cases;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getDeaths() {
        return deaths;
    }

    public double getGDP() {
        return GDP;
    }

    public double getHealthExpenditure() {
        return healthExpenditure;
    }

    public double getHealthExpenditurePercentage() {
        return healthExpenditurePercentage;
    }

    public void setHealthExpenditure(double healthExpenditure) {
        this.healthExpenditure = healthExpenditure;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", total_cases=" + total_cases +
                ", recovered=" + recovered +
                ", deaths=" + deaths +
                ", GDP=" + GDP +
                ", healthExpenditurePercentage=" + healthExpenditurePercentage +
                ", healthExpenditure=" + healthExpenditure +
                '}';
    }

    @Override
    public double getCfr() {
        return (getDeaths()*1.0) / (getDeaths() + getRecovered());
    }

    public int getActiveCases() {
        return getTotal_cases() - getRecovered() - getDeaths();
    }
}
