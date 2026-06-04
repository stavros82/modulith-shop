package com.example;


import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ArchitectureTest {

    // Automatically inspects the package structure starting from your main Application class
//    ApplicationModules modules = ApplicationModules.of(Application.class);
    ApplicationModules modules = ApplicationModules.of(Application.class,
            type -> !type.getClass().getName().endsWith("EventListener")
    );
    @Test
    void verifyModularStructure() {
        // Enforces module isolation and detects illegal cyclic dependencies
        modules.verify();
    }

    @Test
    void createModuleDocumentation() {
        // Generates architectural documentation and component diagrams automatically
        new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml()
                .writeDocumentation();
    }
}