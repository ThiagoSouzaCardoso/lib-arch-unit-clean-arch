package archunit.core;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;

import static archunit.CleanArchitectureConstants.CONFIGURATION_PACKAGE;
import static archunit.CleanArchitectureConstants.CORE_PACKAGE;
import static archunit.CleanArchitectureConstants.ENTRYPOINT_PACKAGE;
import static archunit.CleanArchitectureConstants.GATEWAY_PACKAGE;
import static archunit.CleanArchitectureConstants.PORTS_PACKAGE;
import static archunit.CleanArchitectureConstants.USE_CASES_DEEP_PACKAGE;

public class PortRules {

    @ArchTest
    public static final ArchRule portsBelongToUsecasesAndGateway = Architectures.layeredArchitecture()
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
            .whereLayer("Ports")
            .mayOnlyBeAccessedByLayers("UseCases", "Gateway","Configuration")
            .allowEmptyShould(true)
            .because("Ports interfaces should not leak.");
    @ArchTest
    public static final ArchRule portsShouldHaveNameEndingWithPort = ArchRuleDefinition.classes()
            .that().
            resideInAPackage(PORTS_PACKAGE).should().
            haveSimpleNameEndingWith("Port").andShould().beInterfaces()
            .because("Ports are need to ending with Port and be an interface.")
            .allowEmptyShould(true);



}
