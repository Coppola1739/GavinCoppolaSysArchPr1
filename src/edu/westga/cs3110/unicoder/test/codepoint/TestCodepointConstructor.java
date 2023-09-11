package edu.westga.cs3110.unicoder.test.codepoint;

import edu.westga.cs3110.unicoder.model.Codepoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCodepointConstructor {

    @Test
    public void testValidCodepointConstructor() {
        Codepoint codepoint = new Codepoint("1F682");
        assertEquals(codepoint.getCodepoint(), "1F682");
    }

    @Test
    public void testNullCodepointConstructor() {
        assertThrows(NullPointerException.class, () -> {
            new Codepoint(null);
        });
    }

    @Test
    public void testBlankCodepointConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Codepoint("");
        });
    }

    @Test
    public void testInvalidHexaCodepointConstructor(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Codepoint("Q12!");
        });
    }

}
