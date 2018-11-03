package de.wolc.test;

import junit.framework.TestCase;

/**
 * Beispiel unit test (Wir verwenden jUnit 3.8.1, siehe pom.xml für Begründung).
 * 
 * Siehe hier für alle möglichen asserts:
 * http://junit.sourceforge.net/junit3.8.1/javadoc/junit/framework/Assert.html
 * 
 * !! Alle Testfälle müssen mit "test..." beginnen.
 */
public class AppTest extends TestCase
{
    public void testBeispielUnitTest1() {
        boolean res = true;
        assertTrue("true ist true", res);
    }

    public void testBeispielUnitTest2() {
        boolean res = "hello".equalsIgnoreCase("HEllO");
        assertTrue("Vergleichen von zwei Strings unterschiedlicher Groß- und Kleinschreibung", res);
    }
}