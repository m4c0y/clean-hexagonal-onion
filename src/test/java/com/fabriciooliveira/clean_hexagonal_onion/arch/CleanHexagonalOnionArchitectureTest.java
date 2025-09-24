package com.fabriciooliveira.clean_hexagonal_onion.arch;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.fabriciooliveira.clean_hexagonal_onion",
        importOptions = {ImportOption.DoNotIncludeTests.class})
public class CleanHexagonalOnionArchitectureTest {

    @ArchTest
    static final ArchRule layer_dependencies_are_respected =
            layeredArchitecture().consideringAllDependencies()
                    .layer("command").definedBy("com.fabriciooliveira.clean_hexagonal_onion.command..")
                    .layer("query").definedBy("com.fabriciooliveira.clean_hexagonal_onion.query..")
                    .layer("data").definedBy("com.fabriciooliveira.clean_hexagonal_onion.data..")
                    .layer("acl").definedBy("com.fabriciooliveira.clean_hexagonal_onion.acl..")
                    .layer("domain interaction").definedBy("com.fabriciooliveira.clean_hexagonal_onion.domaininteraction..")
                    .layer("domain").definedBy("com.fabriciooliveira.clean_hexagonal_onion.domain..")

                    .whereLayer("command").mayNotBeAccessedByAnyLayer()
                    .whereLayer("query").mayNotBeAccessedByAnyLayer()
                    .whereLayer("data").mayNotBeAccessedByAnyLayer()
                    .whereLayer("domain interaction").mayOnlyBeAccessedByLayers("command", "query", "data", "acl")
                    .whereLayer("domain").mayOnlyBeAccessedByLayers("domain interaction");
}