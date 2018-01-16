package agh.oop;

import org.junit.Test;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;


public class JsonQueryParserTest {

    private void compareLists(ArrayList<TableRecord> a, ArrayList<TableRecord> b) {
        for (int i = 0; i < a.size(); i++) {
            assertEquals(a.get(i).toString(), b.get(i).toString());
        }
    }

    @Test
    public void parseTest() throws Exception {

        URL gold1 = new URL("http://api.nbp.pl/api/cenyzlota/2013-01-02/?format=json");
        URL gold2 = new URL("http://api.nbp.pl/api/cenyzlota/2013-01-02/2013-01-05/?format=json");
        URL gold3 = new URL("http://api.nbp.pl/api/cenyzlota/2013-01-06/2013-01-10/?format=json");
        JsonQueryParser jsonQueryParser = new JsonQueryParser();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        // Test gold1 url
        System.out.print("Test gold1...");
        List<URL> goldQuery1 = new ArrayList<>();
        goldQuery1.add(gold1);
        ArrayList<TableRecord> result1 = jsonQueryParser.parse(goldQuery1);
        ArrayList<TableRecord> trueResult1 = new ArrayList<>();
        trueResult1.add(new TableRecord(format.parse("2013-01-02"), 165.83));
        compareLists(result1, trueResult1);
        System.out.println(" good.");


        // Test gold2 url
        System.out.print("Test gold2...");
        ArrayList<URL> goldQuery2 = new ArrayList<>();
        goldQuery2.add(gold2);
        ArrayList<TableRecord> result2 = jsonQueryParser.parse(goldQuery2);
        ArrayList<TableRecord> trueResult2 = new ArrayList<>();
        trueResult2.add(new TableRecord(format.parse("2013-01-02"), 165.83));
        trueResult2.add(new TableRecord(format.parse("2013-01-03"), 166.97));
        trueResult2.add(new TableRecord(format.parse("2013-01-04"), 167.43));
        compareLists(result2, trueResult2);
        System.out.println(" good.");

        // Test gold2 and gold3 url
        System.out.print("Test gold 2 and gold3...");
        ArrayList<URL> goldQuery3 = new ArrayList<>();
        goldQuery3.add(gold2);
        goldQuery3.add(gold3);
        ArrayList<TableRecord> result3 = jsonQueryParser.parse(goldQuery3);
        ArrayList<TableRecord> trueResult3 = new ArrayList<>();
        trueResult3.add(new TableRecord(format.parse("2013-01-02"), 165.83));
        trueResult3.add(new TableRecord(format.parse("2013-01-03"), 166.97));
        trueResult3.add(new TableRecord(format.parse("2013-01-04"), 167.43));
        trueResult3.add(new TableRecord(format.parse("2013-01-07"), 167.98));
        trueResult3.add(new TableRecord(format.parse("2013-01-08"), 167.26));
        trueResult3.add(new TableRecord(format.parse("2013-01-09"), 167.48));
        trueResult3.add(new TableRecord(format.parse("2013-01-10"), 167.98));
        compareLists(result3, trueResult3);
        System.out.println(" good");
    }
}