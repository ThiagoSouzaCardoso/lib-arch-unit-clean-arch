package archunit.core;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;

import java.util.Set;

import static archunit.CleanArchitectureConstants.CONFIGURATION_PACKAGE;
import static archunit.CleanArchitectureConstants.CORE_PACKAGE;
import static archunit.CleanArchitectureConstants.ENTRYPOINT_PACKAGE;
import static archunit.CleanArchitectureConstants.GATEWAY_PACKAGE;
import static archunit.CleanArchitectureConstants.PORTS_PACKAGE;
import static archunit.CleanArchitectureConstants.USE_CASES_DEEP_PACKAGE;
import static archunit.CleanArchitectureConstants.USE_CASES_PACKAGE;
import static archunit.CleanArchitectureConstants.USE_CASES_PUBLIC_METHODS_LIMIT;

public class UseCaseRules {

    @ArchTest
    public static final ArchRule usecasesShouldHaveOnlyOnePublicMethod =  ArchRuleDefinition.classes()
            .that()
            .haveSimpleNameEndingWith("UseCase")
            .should(containOnlyOnePublicMethod())
            .because("Use Cases should have only one business responsibility.")
            .allowEmptyShould(true);

    @ArchTest
    public static final ArchRule useCasesShouldResideInsideCore = ArchRuleDefinition.classes()
            .that()
            .resideInAPackage(USE_CASES_PACKAGE)
            .should()
            .haveSimpleNameEndingWith("UseCase")
            .because("UseCases are the core of our business, hence they should stay inside core.")
            .allowEmptyShould(true);

    @ArchTest
    public static final ArchRule useCasesCanBeAccessedByExternalWorld = Architectures.layeredArchitecture()
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
            .as("Use Case External World Access.")
            .whereLayer("UseCases")
        .mayOnlyBeAccessedByLayers("UseCases", "Entrypoints", "Configuration")
        .because("It's ok for Use Cases to be accessed by external world.")
        .allowEmptyShould(true);

    private static ArchCondition<JavaClass> containOnlyOnePublicMethod() {

        return new ArchCondition<JavaClass>("Only one public method") {

            @Override
            public void check(final JavaClass clazz, final ConditionEvents events) {

                final String name = clazz.getName();
                final Set<JavaMethod> methodsSet = clazz.getMethods();
                int PublicMethodsCount = 0;

                for (final JavaMethod javaMethod : methodsSet) {
                    if (javaMethod.getModifiers()
                            .contains(JavaModifier.PUBLIC)) {
                        PublicMethodsCount = PublicMethodsCount + 1;
                    }
                }

                if (PublicMethodsCount > USE_CASES_PUBLIC_METHODS_LIMIT) {
                    throw new AssertionError(String.format("Class %s contains %d public methods, when limit is %d",
                            name, PublicMethodsCount, USE_CASES_PUBLIC_METHODS_LIMIT));
                }
            }
        };
    }


}
