
package com.vk59.coronastat.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.vk59.coronastat.model.CountryData;

public class ResponseData {

    @SerializedName("countrydata")
    private List<CountryData> countryData = null;
    @SerializedName("stat")
    private String stat;

    public List<CountryData> getCountryData() {
        return countryData;
    }

    public void setCountryData(List<CountryData> countryData) {
        this.countryData = countryData;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

}