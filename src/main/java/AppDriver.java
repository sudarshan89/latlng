import com.google.maps.GeoApiContext;
import com.google.maps.TimeZoneApi;
import com.google.maps.model.LatLng;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.nio.charset.Charset.*;

/**
 * Created by s.sreenivasan on 2/12/2016.
 */
public class AppDriver {


    public static void main(String[] args) throws Exception {
        System.out.println("Start here");
        AppDriver appDriver = new AppDriver();
        Path filePath = Paths.get(AppDriver.class.getResource("input.txt").toURI());
        appDriver.processFile(filePath);
    }

    public void processFile(Path filePath) throws IOException {
        List<String> inputData = Files.readAllLines(filePath);
        Function<LatLng,Optional<TimeZone>> latLngToTimezone = new LatLngToTimezone();
        List<String> output = convert(inputData,latLngToTimezone);
        output.forEach(s -> System.out.println(s));
    }

    List<String> convert(List<String> inputData, Function<LatLng, Optional<TimeZone>> latLngToTimezone) {
        return inputData.stream().map(input -> {
            final String[] split = input.split(",");
            String dateTime = split[0];
            Double lat = Double.valueOf(split[1]);
            Double lng = Double.valueOf(split[2]);
            LatLng latLng = new LatLng(lat,lng);
            Optional<TimeZone> timeZone = latLngToTimezone.apply(latLng);
            if(!timeZone.isPresent()){
                return "Invalid input";
            }
            LocalDateTime localDateTime = getLocalDateFromUtc(dateTime);
            String formattedLocalDateTime = localDateTime.toString().replace("T"," ");
            final String output = input.concat(",").concat(timeZone.get().getID()).concat(",").concat(formattedLocalDateTime);
            return output;
        }).collect(Collectors.toList());
    }

    private DateTimeFormatter getUTCDateTimeFormat(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC);
    }

    private LocalDateTime getLocalDateFromUtc(String dateTime){
        final DateTimeFormatter UTCTimeFormat = getUTCDateTimeFormat();
        final ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTime, UTCTimeFormat);
        return LocalDateTime.ofInstant(zonedDateTime.toInstant(), ZoneId.of("Pacific/Auckland"));
    }
}
