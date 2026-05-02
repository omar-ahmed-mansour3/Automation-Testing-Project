package Utils.jsonHelpers;

import Utils.JsonDataTypes.RegisterAndCheckoutData;
import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class RegisterAndCheckoutDataHelper {
    public static RegisterAndCheckoutData[] readData(String jsonFileName) throws FileNotFoundException {
        Gson gson = new Gson();

        // FIX: Point to "src/test/java/TestingData/" where your files actually live
        FileReader reader = new FileReader("src/test/java/TestingData/" + jsonFileName);

        return gson.fromJson(reader, RegisterAndCheckoutData[].class);
    }
}