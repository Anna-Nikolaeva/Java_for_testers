package jft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by Anna on 13.06.16.
 */
public class GeoIPServiceTests {

    @Test
    public void testMyIP(){

        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("194.28.29.152");
        assertEquals(geoIP.getCountryCode(), "RUS" );
    }

    @Test
    public void testInvalidIP(){

        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("194.df.29.152");
        assertEquals(geoIP.getCountryCode(), "RUS" );
    }
}
