package TestClasses;

import Configuration.Models.Configuration;
import Configuration.Services.ConfigurationPersistenceService;

public class ConfigurationTest {
    public static void main(String[] args) {
        ConfigurationPersistenceService configPersS = ConfigurationPersistenceService.getInstance();
        Configuration config = configPersS.getConfiguration();
        System.out.println(config);
    }
}
