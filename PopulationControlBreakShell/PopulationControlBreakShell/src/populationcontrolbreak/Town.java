package populationcontrolbreak;

public class Town {

    /**
     * @return the townNumber
     */
    public int getTownNumber() {
        return townNumber;
    }

    /**
     * @param townNumber the townNumber to set
     */
    public void setTownNumber(int townNumber) {
        this.townNumber = townNumber;
    }

    /**
     * @return the townName
     */
    public String getTownName() {
        return townName;
    }

    /**
     * @param townName the townName to set
     */
    public void setTownName(String townName) {
        this.townName = townName;
    }

    /**
     * @return the countyNumber
     */
    public int getCountyNumber() {
        return countyNumber;
    }

    /**
     * @param countyNumber the countyNumber to set
     */
    public void setCountyNumber(int countyNumber) {
        this.countyNumber = countyNumber;
    }

    /**
     * @return the regionNumber
     */
    public int getRegionNumber() {
        return regionNumber;
    }

    /**
     * @param regionNumber the regionNumber to set
     */
    public void setRegionNumber(int regionNumber) {
        this.regionNumber = regionNumber;
    }

    /**
     * @return the population
     */
    public int getPopulation() {
        return population;
    }

    /**
     * @param population the population to set
     */
    public void setPopulation(int population) {
        this.population = population;
    }
    private int townNumber;
    private String townName;
    private int countyNumber;
    private int regionNumber;
    private int population;


}