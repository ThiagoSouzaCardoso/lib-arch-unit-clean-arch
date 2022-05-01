package archunit;

import archunit.configuration.ConfigurationRules;
import archunit.core.CommandRules;
import archunit.core.CoreRules;
import archunit.gateways.DataProviderRules;
import archunit.gateways.EntrypointRules;
import archunit.core.EventRules;
import archunit.core.PortRules;
import archunit.core.UseCaseRules;
import archunit.gateways.GatewayRules;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchTests;


@AnalyzeClasses(packages = CleanArchitectureConstants.PACKAGE_NAME,importOptions = {ImportOption.DoNotIncludeTests.class,ImportOption.DoNotIncludeJars.class})
public class ArchitetureTest {

    @ArchTest
    public static final ArchTests entrypointRules = ArchTests.in(EntrypointRules.class);
    @ArchTest
    public static final ArchTests commandRules = ArchTests.in(CommandRules.class);
    @ArchTest
    public static final ArchTests eventRules = ArchTests.in(EventRules.class);
    @ArchTest
    public static final ArchTests portRules = ArchTests.in(PortRules.class);
    @ArchTest
    public static final ArchTests useCaseRules = ArchTests.in(UseCaseRules.class);
    @ArchTest
    public static final ArchTests coreRules = ArchTests.in(CoreRules.class);

    @ArchTest
    public static final ArchTests configurationRules = ArchTests.in(ConfigurationRules.class);

    @ArchTest
    public static final ArchTests dataProviderRules = ArchTests.in(DataProviderRules.class);

    @ArchTest
    public static final ArchTests gatewayRules = ArchTests.in(GatewayRules.class);

}
