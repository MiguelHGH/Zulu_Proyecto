package ConfigurationFile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.*;

public class ConfigReaderTest {

    @ParameterizedTest
    @ValueSource(strings = {"Files\\Config.txt", ""})
    public void leerArchivo(String direccionArchivo){
        assertNotNull(ConfigReader.leerArchivo(direccionArchivo));
    }

    ConfigReader configReader;
    @Before
    public void setUp() throws Exception {
        configReader = new ConfigReader();
    }

    @Test
    public void leerConfiguracion() {
        configReader.leerConfiguracion();
    }

    @After
    public void tearDown() throws Exception {;
        assertNotNull(configReader.getCuartoInicial());
        System.out.println(configReader.getCuartoInicial());
    }

}