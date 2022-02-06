
import com.opencsv.CSVReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.util.List;
import java.util.Objects;

public class Currency {

    @Test(dataProvider = "getData")
    public void CountryData(String code, String symbol, String name){
        System.out.println("code = " + code);
        System.out.println("symbol = " + symbol);
        System.out.println("name = " + name);

    }

    @DataProvider(name = "getData")
    public Object[][] getData() throws Exception {
        CSVReader reader = new CSVReader(new FileReader("data/currency.csv"));

        List<String[]> records = reader.readAll();

        Object[][] array = null;

        for (int i = 0; i < records.size(); i++) {

            Object[] row = records.get(i);
            if (Objects.isNull(array)) {
                array = new Object[records.size()][row.length];
            }
            array[i][0] = row[0];
            // System.out.println(array[i][column]);
            array[i][1] = row[1];
            // System.out.println(array[i][column]);
            array[i][1] = row[2];

        }
        return array;

    }

}
