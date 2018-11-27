package com.example.douglassmith.lab7;

public class Brewery {
    private String brewery;
    private String breweryURL;
    private void setBreweryInfo(Integer breweryCrowd){

        switch (breweryCrowd){
            case 0:
                brewery="Avery";
                breweryURL="http://www.averybrewing.com";
                break;
            case 1:
                brewery="Twisted Pine";
                breweryURL="http://www.twistedpinebrewing.com";
                break;
            case 2:
                brewery="Boulder Beer";
                breweryURL="http://www.averybrewing.com";
                break;
            case 3:
                brewery="Odells Brewing";
                breweryURL="http://www.odellsbrewing.com";
                break;
            case 4:
                brewery="Mountain Sun";
                breweryURL="http://www.mountainsunbrewing.com";
                break;
            case 5:
                brewery="Gross Brewing";
                breweryURL="https://gross.beer/";
                break;

            default:
                brewery = "none";
                breweryURL = "http://www.google.com";
        }
    }
    public void setBrewery(Integer breweryCrowd){
        setBreweryInfo(breweryCrowd);

    }
    public void setBreweryURL(Integer breweryCrowd){
        setBreweryInfo(breweryCrowd);

    }

    public String getBrewery(){
        return brewery;
    }


    public String getBreweryUrl(){
        return breweryURL;
    }
}
