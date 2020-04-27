package CoronaPandemic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ReadCountryDataFile {

    static List<String[]> countriesData = readingData("CountryData.txt");

    static List<String[]> readingData(String filename){
        Path path = Paths.get("src", "main", "resources",filename).toAbsolutePath();
        Stream<String> lines = null;
        try {
            lines = Files.lines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stream<String[]> CountryDetails = lines.map(x -> x.split(", "));

        return CountryDetails.collect(Collectors.toList());
    }
}
