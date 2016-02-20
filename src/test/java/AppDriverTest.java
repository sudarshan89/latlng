import com.google.maps.GeoApiContext;
import com.google.maps.model.LatLng;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import static java.util.TimeZone.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;


/**
 * Created by s.sreenivasan on 2/12/2016.
 */
public class AppDriverTest {

    private LatLngToTimezone latLngToTimezoneConverter;
    private AppDriver appDriver;

    @Before
    public void setUp() {
        latLngToTimezoneConverter = mock(LatLngToTimezone.class);
        appDriver = new AppDriver();
    }

    @Test
    public void testConvert() {
        stub(latLngToTimezoneConverter.apply(any(LatLng.class))).toReturn(Optional.of(getTimeZone( ZoneId.of("Pacific/Auckland"))));
        final List<String> input = Lists.newArrayList("2013-07-10 02:52:49,-44.490947,171.220966");
        final List<String> actualOutput = appDriver.convert(input, latLngToTimezoneConverter);
        assertThat(actualOutput).isEqualTo(Lists.newArrayList("2013-07-10 02:52:49,-44.490947,171.220966,Pacific/Auckland,2013-07-10 14:52:49"));
    }
}
