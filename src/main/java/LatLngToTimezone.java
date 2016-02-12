import com.google.maps.GeoApiContext;
import com.google.maps.TimeZoneApi;
import com.google.maps.model.LatLng;

import java.sql.Time;
import java.util.Optional;
import java.util.TimeZone;
import java.util.function.Function;

/**
 * Created by s.sreenivasan on 2/12/2016.
 */
public class LatLngToTimezone implements Function<LatLng,Optional<TimeZone>> {

    private static final String API_KEY = "AIzaSyApVcklJpPOvIP5pa8IaUcWG6AtEaSnMNI";
    private static GeoApiContext context = new GeoApiContext().setApiKey(API_KEY);

    @Override
    public Optional<TimeZone> apply(LatLng latLng) {
        Optional<TimeZone> timezone = Optional.empty();
        try {
            timezone = Optional.of(TimeZoneApi.getTimeZone(context,latLng).await());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timezone;
    }
}
