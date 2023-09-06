package edu.westga.cs3110.unicoder.model;

public class Codepoint {

    public static String VALID_HEXADECIMAL_STRING = "^[A-Fa-f0-9]+$";
    private String codepoint;

    public Codepoint(String hexadecimalString) {
        if (hexadecimalString == null) {
            throw new NullPointerException("Hexadecimal value cannot be null");
        }
        if (hexadecimalString.isBlank()) {
            throw new IllegalArgumentException("Hexadecimal value cannot be blank");
        }
        if (!hexadecimalString.matches(VALID_HEXADECIMAL_STRING)) {
            throw new IllegalArgumentException("Hexadecimal value must be valid format");
        }
        this.codepoint = hexadecimalString;
    }

    public String getCodepoint() {
        return this.codepoint;
    }

}
