package test_data;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.*;

public class DataObjectBuilder {
    public static <T> T buildDataObject(String filePath, Class<T> dataType) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader;
        InputStream is = FileUtils.class.getResourceAsStream(filePath);
        String line;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(is));
            while (null != (line = bufferedReader.readLine())) {
                stringBuilder.append(line);
            }
            // Create Gson instance
            Gson gson = new Gson();

            // Convert json array data into loginCreds []
            return gson.fromJson(stringBuilder.toString(), dataType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
