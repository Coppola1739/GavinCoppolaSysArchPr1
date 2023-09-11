package edu.westga.cs3110.unicoder.test.codepoint;

import edu.westga.cs3110.unicoder.model.Codepoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCodepointToUTF16 {

    @Test
    public void testValid5CharToUTF16() {
        Codepoint codepoint = new Codepoint("1682F");
        assertEquals("D81ADC2F", codepoint.toUTF16());
    }

    @Test
    public void testValid4CharToUTF16(){
        Codepoint codepoint = new Codepoint("FFFF");
        assertEquals("FFFF", codepoint.toUTF16());
    }
}
