package CoronaPandemic;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public interface ReadData {

    static List<Country> readData() throws ParseException, UnirestException {

        List<Country> countries = new ArrayList<>();
        Unirest.setTimeouts(0, 0);
        for(CountriesList c : CountriesList.values()){

            String countryName = c.toString();
            HttpResponse<String> response = Unirest.get("https://covidapi.info/api/v1/country/"+countryName+"/latest").asString();

            JSONParser jsonParser = new JSONParser();

            JSONObject jsonObject = (JSONObject)jsonParser.parse(response.getBody());

            String result = jsonObject.get("result").toString();
            jsonObject = (JSONObject)jsonParser.parse(result);

            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);

            String date = simpleDateFormat.format(cal.getTime());
            String latestDate = jsonObject.get(date).toString();

            jsonObject = (JSONObject)jsonParser.parse(latestDate);

            Country country = new Country(c.getName(), Integer.parseInt(jsonObject.get("confirmed").toString()),
                                          Integer.parseInt(jsonObject.get("recovered").toString()),
                                          Integer.parseInt(jsonObject.get("deaths").toString()),
                                          c.getGdp(), c.getHealthExpenditurePercentage());

            country.setHealthExpenditure((country.getGDP() * country.getHealthExpenditurePercentage()) / 100);
            countries.add(country);
        }

        return countries;
    }
}
