package archunit.configuration;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

import static archunit.CleanArchitectureConstants.CONFIGURATION_PACKAGE;
import static archunit.CleanArchitectureConstants.CORE_PACKAGE;
import static archunit.CleanArchitectureConstants.ENTRYPOINT_PACKAGE;
import static archunit.CleanArchitectureConstants.GATEWAY_PACKAGE;
import static archunit.CleanArchitectureConstants.PORTS_PACKAGE;
import static archunit.CleanArchitectureConstants.USE_CASES_DEEP_PACKAGE;

public class ConfigurationRules {

    @ArchTest
    public static final ArchRule configurationCantBeAcessedByAnyLayer = Architectures.layeredArchitecture()
            .layer("UseCases")
            .definedBy(USE_CASES_DEEP_PACKAGE)
            .layer("Ports")
            .definedBy(PORTS_PACKAGE)
            .layer("Gateway")
            .definedBy(GATEWAY_PACKAGE)
            .layer("Configuration")
            .definedBy(CONFIGURATION_PACKAGE)
            .layer("Entrypoints")
            .definedBy(ENTRYPOINT_PACKAGE)
            .layer("Core")
            .definedBy(CORE_PACKAGE)
            .whereLayer("Configuration")
            .mayNotBeAccessedByAnyLayer()
            .because("Configuration is our last external layer and should not be accessed by other layers.")
            .allowEmptyShould(true);



}
