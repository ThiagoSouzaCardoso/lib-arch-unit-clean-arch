package archunit.core;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

import static archunit.CleanArchitectureConstants.EVENTS_PACKAGE;

public class EventRules {

    @ArchTest
    public static final ArchRule eventsShouldHaveNameEndingWithEvent = ArchRuleDefinition.classes()
            .that().
            resideInAPackage(EVENTS_PACKAGE).should().
            haveSimpleNameEndingWith("Event")
            .because("Events are need to ending with Event")
            .allowEmptyShould(true);


}
