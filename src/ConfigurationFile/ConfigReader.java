package ConfigurationFile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class ConfigReader {
    public HashMap<String, String[]> leerArchivo() throws MissingContentError {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = new JSONArray();
        HashMap<String, String[]> rooms = new HashMap<String, String[]>();
        String fileLocation = "src\\ConfigurationFile\\Config.txt";
        try {
            FileReader fileReader = new FileReader(fileLocation);
            Object obj = parser.parse(fileReader);
            jsonArray = (JSONArray) obj;
        } catch (FileNotFoundException e) {
            System.out.println("src/ConfigurationFile/Config.txt not found.");
        } catch (Exception e) {
            System.out.println("Unexpected content in configuration file.");
        }
        Iterator iterator = jsonArray.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            JSONObject jsonObject = (JSONObject) iterator.next();
            if (jsonObject.get("Name") == null) {
                String error = "Name not found in object number " + i;
                throw new MissingContentError(error);
            }
            if (jsonObject.get("Description") == null) {
                String error = "Description not found in object number " + i;
                throw new MissingContentError(error);
            }
            if (jsonObject.get("Exits") == null) {
                String error = "Exit not found in object number " + i;
                throw new MissingContentError(error);
            }
            String n = ".*[0-9].*";
            String a = ".*[a-z].*";
            String b = ".*[A-Z].*";
            String name = (String) jsonObject.get("Name");
            if (!name.matches(a) && !name.matches(b) && !name.matches(n)) {
                String error = "Empty name in object number " + i;
                throw new MissingContentError(error);
            }
            String description = (String) jsonObject.get("Description");
            if (!description.matches(a) && !description.matches(b) && !description.matches(n)) {
                String error = "Empty description in object number " + i;
                throw new MissingContentError(error);
            }
            JSONArray exits = (JSONArray) jsonObject.get("Exits");
            if (exits.size() != 4) {
                String error = "Must be four exits in object number " + i;
                throw new MissingContentError(error);
            }
            for (int j = 0; j < exits.size(); j++) {
                if (!exits.get(j).toString().matches(a) && !exits.get(j).toString().matches(b) && !exits.get(j).toString().matches(n)) {
                    String error = "Empty exit in object number " + i + ", exit " + (j + 1);
                    throw new MissingContentError(error);
                }
            }
            rooms.put(name, new String[]{description, (String) exits.get(0), (String) exits.get(1), (String) exits.get(2), (String) exits.get(3)});
            i++;
        }
        return rooms;
    }
}
