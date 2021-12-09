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
        JSONArray cuartosArray = new JSONArray();
        HashMap<String, String[]> cuartos = new HashMap<String, String[]>();
        String ubicacionArchivo = "src\\ConfigurationFile\\Config.txt";
        try {
            FileReader fileReader = new FileReader(ubicacionArchivo);
            Object datosArchivo = parser.parse(fileReader);
            cuartosArray = (JSONArray) datosArchivo;
        } catch (FileNotFoundException e) {
            System.out.println("src/ConfigurationFile/Config.txt not found.");
        } catch (Exception e) {
            System.out.println("Unexpected content in configuration file.");
        }
        Iterator iterator = cuartosArray.iterator();
        int posicion = 1;
        while (iterator.hasNext()) {
            JSONObject cuarto = (JSONObject) iterator.next();
            if (cuarto.get("Name") == null) {
                String error = "Name not found in object number " + posicion;
                throw new MissingContentError(error);
            }
            if (cuarto.get("Description") == null) {
                String error = "Description not found in object number " + posicion;
                throw new MissingContentError(error);
            }
            if (cuarto.get("Exits") == null) {
                String error = "Exit not found in object number " + posicion;
                throw new MissingContentError(error);
            }
            String numeros = ".*[0-9].*";
            String minusculas = ".*[a-z].*";
            String mayusculas = ".*[A-Z].*";
            String name = (String) cuarto.get("Name");
            if (!name.matches(minusculas) && !name.matches(mayusculas) && !name.matches(numeros)) {
                String error = "Empty name in object number " + posicion;
                throw new MissingContentError(error);
            }
            String description = (String) cuarto.get("Description");
            if (!description.matches(minusculas) && !description.matches(mayusculas) && !description.matches(numeros)) {
                String error = "Empty description in object number " + posicion;
                throw new MissingContentError(error);
            }
            JSONArray exits = (JSONArray) cuarto.get("Exits");
            if (exits.size() != 4) {
                String error = "Must be four exits in object number " + posicion;
                throw new MissingContentError(error);
            }
            for (int j = 0; j < exits.size(); j++) {
                if (!exits.get(j).toString().matches(minusculas) && !exits.get(j).toString().matches(mayusculas) && !exits.get(j).toString().matches(numeros)) {
                    String error = "Empty exit in object number " + posicion + ", exit " + (j + 1);
                    throw new MissingContentError(error);
                }
            }
            cuartos.put(name, new String[]{description, (String) exits.get(0), (String) exits.get(1), (String) exits.get(2), (String) exits.get(3)});
            posicion++;
        }
        return cuartos;
    }
}
