package Utils.jsonHelpers;

import Utils.JsonDataTypes.RegisterData;
import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class RegisterUserHelper {

    private static final String TestPrjRoot = "src/test/java/";
    private static final String TestDataFolder = "TestingData/";

    // WE DELETED ReadUsers() because the 'User' class no longer exists.

    // We use this method for EVERYTHING now (Positive tests, Negative tests, etc.)
    public static RegisterData[] ReadRegisterData(String fileName) throws FileNotFoundException {
        String filePath = System.getProperty("user.dir") + "/" + TestPrjRoot + TestDataFolder + fileName;
        FileReader reader = new FileReader(filePath);
        Gson gson = new Gson();
        return gson.fromJson(reader, RegisterData[].class);
    }
}