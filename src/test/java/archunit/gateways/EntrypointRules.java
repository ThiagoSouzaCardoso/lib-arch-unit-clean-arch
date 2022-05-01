package archunit.gateways;

import archunit.CleanArchitectureConstants;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

public class EntrypointRules {

    @ArchTest
    public static final ArchRule controllerShouldHaveAnnotationRestControllerAndRequestMapping  = ArchRuleDefinition.classes()
            .that().
            resideInAPackage(CleanArchitectureConstants.ENTRYPOINT_REST_PACKAGE).should().
            beAnnotatedWith("org.springframework.web.bind.annotation.RestController").andShould().
            beAnnotatedWith("org.springframework.web.bind.annotation.RequestMapping")
            .allowEmptyShould(true)
            .because("Controllers are need to be annotated with @RestController and @RequestMapping");


    @ArchTest
    public static final ArchRule controllerShouldHaveNameEndingWithController = ArchRuleDefinition.classes()
            .that().
            resideInAPackage(CleanArchitectureConstants.ENTRYPOINT_REST_PACKAGE).should().
            haveSimpleNameEndingWith("Controller")
            .allowEmptyShould(true)
            .because("Controllers are need to ending with Controller");

    @ArchTest
    public static final ArchRule inputsRestShouldHaveNameEndingWithRequest =   ArchRuleDefinition.classes()
            .that().
            resideInAPackage(CleanArchitectureConstants.ENTRYPOINT_REST_INPUTS_PACKAGE).should().
            haveSimpleNameEndingWith("Request")
            .allowEmptyShould(true)
            .because("Rest inputs are need to ending with Request");

    @ArchTest
    public static final ArchRule outputsRestShouldHaveNameEndingWithResponse =   ArchRuleDefinition.classes()
            .that().
            resideInAPackage(CleanArchitectureConstants.ENTRYPOINT_REST_OUTPUTS_PACKAGE).should().
            haveSimpleNameEndingWith("Response")
            .allowEmptyShould(true)
            .because("Integration outputs are need to ending with Response");

    @ArchTest
    public static final ArchRule consumerkafkaShouldHaveNameEndingWithConsumer = ArchRuleDefinition.classes()
            .that().
            resideInAPackage(CleanArchitectureConstants.ENTRYPOINT_KAFKA_PACKAGE).should().
            haveSimpleNameEndingWith("Consumer")
            .allowEmptyShould(true)
            .because("Consumers Kafka are need to ending with Consumer");

    @ArchTest
    public static final ArchRule onsumerKafkaShouldHaveAnnotationController = ArchRuleDefinition.classes()
            .that().
            resideInAPackage(CleanArchitectureConstants.ENTRYPOINT_KAFKA_PACKAGE).should().
            beAnnotatedWith("org.springframework.stereotype.Controller")
            .allowEmptyShould(true)
            .because("Consumers Kafka are need to be annotaded with @Controller");

}
