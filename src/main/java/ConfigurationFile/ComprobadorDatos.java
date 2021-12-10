package ConfigurationFile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ComprobadorDatos {
    static final String REGEX = ".*[a-zA-Z0-9_].*";

    public void validarPropiedades(JSONObject cuarto, int posicion) throws MissingContentError {
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
    }

    public void validarValores(JSONObject cuarto, int posicion) throws MissingContentError {
        final int NUMERO_SALIDAS = 4;
        String name = (String) cuarto.get("Name");
        if (validarRegex(name, REGEX)) {
            String error = "Empty name in object number " + posicion;
            throw new MissingContentError(error);
        }
        String description = (String) cuarto.get("Description");
        if (validarRegex(description, REGEX)) {
            String error = "Empty description in object number " + posicion;
            throw new MissingContentError(error);
        }
        JSONArray exits = (JSONArray) cuarto.get("Exits");
        if (exits.size() != NUMERO_SALIDAS) {
            String error = "Must be four exits in object number " + posicion;
            throw new MissingContentError(error);
        }
        for (int j = 0; j < exits.size(); j++) {
            if (validarRegex((String) exits.get(j), REGEX)) {
                String error = "Empty exit in object number " + posicion + ", exit " + (j + 1);
                throw new MissingContentError(error);
            }
        }
    }

    public void validarInicial(JSONObject inicial) throws MissingContentError {
        if (inicial.get("Inicial") == null) {
            String error = "Initial room not found in object number 1";
            throw new MissingContentError(error);
        }
        if (validarRegex((String) inicial.get("Inicial"), REGEX)) {
            String error = "Empty Inicial value ";
            throw new MissingContentError(error);
        }
    }

     static boolean validarRegex(String valor, String regex) {
        return !valor.matches(regex);
    }
}
