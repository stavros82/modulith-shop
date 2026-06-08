package com.example;

import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class ModulithDocGenerator {

    public static void main(String[] args) {
        // Build module model starting from the main Application class
        ApplicationModules modules = ApplicationModules.of(Application.class);

        // Generate documentation and PlantUML files
        Documenter documenter = new Documenter(modules);
        documenter.writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml()
                .writeDocumentation();

        System.out.println("Modulith documentation generated (look for .puml and docs files in the working directory).");
    }
}

