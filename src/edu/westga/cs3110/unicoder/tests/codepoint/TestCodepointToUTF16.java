package edu.westga.cs3110.unicoder.tests.codepoint;

import edu.westga.cs3110.unicoder.model.Codepoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCodepointToUTF16 {

    @Test
    public void testValid5CharToUTF16() {
        Codepoint codepoint = new Codepoint("0183A5");
        assertEquals("DB1ADC2F", codepoint.toUTF16());
    }

    @Test
    public void testValid4CharToUTF16(){
        Codepoint codepoint = new Codepoint("FFFF");
        assertEquals("FFFF", codepoint.toUTF16());
    }
}
