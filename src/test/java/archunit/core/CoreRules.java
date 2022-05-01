package archunit.core;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

import static archunit.CleanArchitectureConstants.CORE_PACKAGE;
import static archunit.CleanArchitectureConstants.JAVA_PACKAGE;
import static archunit.CleanArchitectureConstants.JETBRAINS_PACKAGE;
import static archunit.CleanArchitectureConstants.KOTLIN_PACKAGE;
import static archunit.CleanArchitectureConstants.LOG_PACKAGE;

public class CoreRules {

    @ArchTest
    public static final ArchRule oreLayerCannotDependOnFrameworks = ArchRuleDefinition.classes()
            .that().resideInAPackage(CORE_PACKAGE)
            .should().onlyDependOnClassesThat().resideInAnyPackage(
                    JAVA_PACKAGE,
                    KOTLIN_PACKAGE,
                    LOG_PACKAGE,
                    CORE_PACKAGE,
                    JETBRAINS_PACKAGE
            )
            .allowEmptyShould(true);


}
