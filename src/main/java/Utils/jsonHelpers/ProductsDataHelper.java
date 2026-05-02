package Utils.jsonHelpers;

import Utils.JsonDataTypes.ProductsData;
import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ProductsDataHelper {

    private static final String TestPrjRoot = "src/test/java/";
    private static final String TestDataFolder = "TestingData/";

    public static ProductsData[] ReadProductsData(String fileName) throws FileNotFoundException {
        // Construct the full path
        String filePath = System.getProperty("user.dir") + "/" + TestPrjRoot + TestDataFolder + fileName;

        // Parse the JSON file into an array of ProductsData objects
        FileReader reader = new FileReader(filePath);
        return new Gson().fromJson(reader, ProductsData[].class);
    }
}