package edu.westga.cs3110.unicoder.test.codepoint;

import edu.westga.cs3110.unicoder.model.Codepoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCodepointToUTF32 {
    @Test
    public void testValidToUTF32() {
        Codepoint codepoint = new Codepoint("10FFFF");
        assertEquals("0010FFFF", codepoint.toUTF32());
    }
}
