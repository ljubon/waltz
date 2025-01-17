package org.finos.waltz.service.workflow;

import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.finos.waltz.common.CollectionUtilities.find;
import static org.finos.waltz.common.ListUtilities.asList;
import static org.junit.Assert.*;

public class TransitionDefinitionCompilerTest {

    @Test
    public void compile() {

        ImmutableTransitionDefinition transitionDefn = ImmutableTransitionDefinition
                .builder()
                .name("transition1")
                .predicate("1 == 1")
                .initialState("IN_PROGRESS")
                .targetState("COMPLETED")
                .build();

        ImmutableTransitionDefinition transitionDefn2 = ImmutableTransitionDefinition
                .builder()
                .name("transition2")
                .predicate("1 == 0")
                .initialState("IN_PROGRESS")
                .targetState("COMPLETED")
                .build();

        ImmutableTransitionDefinition transitionDefn3 = ImmutableTransitionDefinition
                .builder()
                .name("transition3")
                .predicate("1 == ")
                .initialState("IN_PROGRESS")
                .targetState("COMPLETED")
                .build();

        List<TransitionDefinition> defns = asList(transitionDefn, transitionDefn2);
        List<TransitionDefinition> defnsWithError = asList(transitionDefn3);

        List<CompiledTransitionDefinition> compiledDefns = TransitionDefinitionCompiler.compile(defns);

        assertThrows("Definitions with invalid predicates should throw IllegalArgumentException",
                IllegalArgumentException.class,
                () -> TransitionDefinitionCompiler.compile(defnsWithError));

        Optional<CompiledTransitionDefinition> compTrans1 = find(compiledDefns, d -> d.name().equalsIgnoreCase("transition1"));
        Optional<CompiledTransitionDefinition> compTrans2 = find(compiledDefns, d -> d.name().equalsIgnoreCase("transition2"));

        compTrans1
                .ifPresent(d -> assertTrue(
                        "Predicate for transition1 should compile and evaluate as true",
                        (boolean) d.test(null)));

        compTrans2
                .ifPresent(d -> assertFalse(
                        "Predicate for transition2 should compile and evaluate as false",
                        (boolean) d.test(null)));

    }

}
