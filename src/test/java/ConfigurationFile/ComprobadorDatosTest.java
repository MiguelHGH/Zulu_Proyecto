package ConfigurationFile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class ComprobadorDatosTest {
    ArrayList palabras = new ArrayList();

    @Before
    public void setUp() throws Exception {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        for (int j = 0; j < 50; j++){
            Random random = new Random();
            String generatedString = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            palabras.add(generatedString);
        }
    }

    @Test
    public void validarRegex() {
        for (int j = 0; j < 50; j++){
            String palabra =(String) palabras.get(j);
            System.out.println(palabra);
            assertEquals(false, ComprobadorDatos.validarRegex(palabra,ComprobadorDatos.REGEX));
        }
    }
}