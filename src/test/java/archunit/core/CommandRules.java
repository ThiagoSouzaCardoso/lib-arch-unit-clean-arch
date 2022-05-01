package archunit.core;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import static archunit.CleanArchitectureConstants.COMMANDS_PACKAGE;


public class CommandRules {

    @ArchTest
    public static final ArchRule commandsShouldHaveNameEndingWithCommand = ArchRuleDefinition.classes()
            .that().
            resideInAPackage(COMMANDS_PACKAGE).should().
            haveSimpleNameEndingWith("Command")
            .because("Commands are need to ending with Command")
            .allowEmptyShould(true);



}
