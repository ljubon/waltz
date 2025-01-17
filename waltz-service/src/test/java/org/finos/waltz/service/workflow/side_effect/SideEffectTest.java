package org.finos.waltz.service.workflow.side_effect;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.junit.Test;

import static org.finos.waltz.common.CollectionUtilities.find;
import static org.finos.waltz.common.MapUtilities.newHashMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SideEffectTest {


    @Test
    public void canMakeAssessmentRefs() {

        JexlBuilder builder = new JexlBuilder();
        TestSideEffectNamespace testNamespace = new TestSideEffectNamespace();

        JexlEngine jexl = builder
                .namespaces(newHashMap(
                        "assessment", new AssessmentSideEffectNamespace(),
                        "test", testNamespace))
                .create();

        MapContext ctx = new MapContext();
        String messageString = "I have updated something!";
        ctx.set("message", messageString);

        JexlExpression sideEffectExpression = jexl.createExpression( "assessment:update('ARCH_REVIEW', 'YES')");
        JexlExpression messageExpression = jexl.createExpression( "test:sendMessage(message)");
        messageExpression.evaluate(ctx);

        assertEquals("Should be able to use context as part of expression",
                messageString,
                testNamespace.lastMessage);

        assertEquals("Should be able to parse survey templateExtId and ratingExtId from update()",
                AssessmentSideEffectNamespace.update("ARCH_REVIEW", "YES"),
                sideEffectExpression.evaluate(ctx));
    }

}
