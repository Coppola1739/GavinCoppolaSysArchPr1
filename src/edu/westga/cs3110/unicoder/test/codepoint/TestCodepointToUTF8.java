package edu.westga.cs3110.unicoder.test.codepoint;

import edu.westga.cs3110.unicoder.model.Codepoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCodepointToUTF8 {


    @Test
    public void testValid1ByteToUTF8() {
        Codepoint codepoint = new Codepoint("0015");
        assertEquals("15", codepoint.toUTF8());
    }

    @Test
    public void testValid2ByteToUTF8() {
        Codepoint codepoint = new Codepoint("01A0");
        assertEquals("C6A0", codepoint.toUTF8());
    }

    @Test
    public void testValid3ByteToUTF8(){
        Codepoint codepoint = new Codepoint("4CE3");
        assertEquals("E4B3A3", codepoint.toUTF8());
    }

    @Test
    public void testValid4ByteToUTF8(){
        Codepoint codepoint = new Codepoint("10B341");
        assertEquals("F48B8D81", codepoint.toUTF8());
    }

}
