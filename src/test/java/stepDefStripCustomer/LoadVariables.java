package stepDefStripCustomer;
import java.io.FileInputStream; // This MUST be here
import java.io.IOException;
import java.util.Properties;

import io.cucumber.java.Before;

public class LoadVariables {
	public static Properties prop;

    @Before
    public void setup() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/propertyFiles/object.properties");
        prop.load(fis);
        System.out.println("Global Setup: Properties loaded.");
    }
}
