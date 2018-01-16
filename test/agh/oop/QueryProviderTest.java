package agh.oop;

import javafx.scene.control.Tab;
import org.junit.Test;

import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import static org.junit.Assert.*;

public class QueryProviderTest {

    private static SimpleDateFormat iso8601 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private String URLPart = "http://api.nbp.pl/api/exchangerates/rates/a/";

    @Test
    public void giveQueriesTest() throws Exception {
        QueryProvider queryProvider = new QueryProvider(QueryProviderFormat.JSON);
        ArrayList<URL> queries = queryProvider.giveQueries(URLPart,
                new GregorianCalendar(2015, 9, 20).getTime(),
                new GregorianCalendar(2015, 9,29).getTime(), TableType.CURRENCY);
        ArrayList<String> trueResult = new ArrayList<>();
        trueResult.add(URLPart + "2015-10-20/2015-10-29/" + QueryProviderFormat.JSON);
        int i = 0;
        for (URL url : queries) {
            assertEquals(trueResult.get(i), url.toString());
            i++;
        }
        System.out.println("First test passed.");

        queries = queryProvider.giveQueries(URLPart,
                new GregorianCalendar(2015, 5, 20).getTime(),
                new GregorianCalendar(2015, 7,29).getTime(), TableType.CURRENCY);
        trueResult = new ArrayList<>();
        trueResult.add(URLPart + "2015-06-20/2015-08-29/" + QueryProviderFormat.JSON);
        i = 0;
        for (URL url : queries) {
            assertEquals(trueResult.get(i), url.toString());
            i++;
        }
        System.out.println("Second test passed.");

        queries = queryProvider.giveQueries(URLPart,
                new GregorianCalendar(2015, 5, 20).getTime(),
                new GregorianCalendar(2017, 7,29).getTime(), TableType.CURRENCY);
        trueResult = new ArrayList<>();
        trueResult.add(URLPart + "2015-06-20/2015-09-18/" + QueryProviderFormat.JSON);
        trueResult.add(URLPart + "2015-09-19/2015-12-17/" + QueryProviderFormat.JSON);
        trueResult.add(URLPart + "2015-12-18/2016-03-16/" + QueryProviderFormat.JSON);
        trueResult.add(URLPart + "2016-03-17/2016-06-14/" + QueryProviderFormat.JSON);
        trueResult.add(URLPart + "2016-06-15/2016-09-12/" + QueryProviderFormat.JSON);
        trueResult.add(URLPart + "2016-09-13/2016-12-11/" + QueryProviderFormat.JSON);
        trueResult.add(URLPart + "2016-12-12/2017-03-11/" + QueryProviderFormat.JSON);
        trueResult.add(URLPart + "2017-03-12/2017-06-09/" + QueryProviderFormat.JSON);
        trueResult.add(URLPart + "2017-06-10/2017-08-29/" + QueryProviderFormat.JSON);
        i = 0;
        for (URL url : queries) {
            assertEquals(trueResult.get(i), url.toString());
            i++;
        }
        System.out.println("Third test passed.");
    }

}