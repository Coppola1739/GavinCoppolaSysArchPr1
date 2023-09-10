package edu.westga.cs3110.unicoder.model;

public class Codepoint {
    private static final String VALID_HEXADECIMAL_STRING = "^[A-Fa-f0-9]+$";

    private static final int START_RANGE_1_BYTE_UTF8 = Integer.parseUnsignedInt("0000", 16);
    private static final int END_RANGE_1_BYTE_UTF8 = Integer.parseUnsignedInt("007F", 16);

    private static final int START_RANGE_2_BYTE_UTF8 = Integer.parseUnsignedInt("0080", 16);
    private static final int END_RANGE_2_BYTE_UTF8 = Integer.parseUnsignedInt("07FF", 16);

    private static final int START_RANGE_3_BYTE_UTF8 = Integer.parseUnsignedInt("0800", 16);
    private static final int END_RANGE_3_BYTE_UTF8 = Integer.parseUnsignedInt("FFFF", 16);

    private static final int START_RANGE_4_BYTE_UTF8 = Integer.parseUnsignedInt("10000", 16);
    private static final int END_RANGE_4_BYTE_UTF8 = Integer.parseUnsignedInt("10FFFF", 16);

    private static final int START_RANGE_2_BYTE_UTF16_FIRST_GAP = Integer.parseUnsignedInt("0000", 16);
    private static final int END_RANGE_2_BYTE_UTF16_FIRST_GAP = Integer.parseUnsignedInt("D7FF", 16);
    private static final int START_RANGE_2_BYTE_UTF16_SECOND_GAP = Integer.parseUnsignedInt("E000", 16);
    private static final int END_RANGE_2_BYTE_UTF16_SECOND_GAP = Integer.parseUnsignedInt("FFFF", 16);
    private static final int START_RANGE_4_BYTE_UTF16 = Integer.parseUnsignedInt("10000", 16);
    private static final int END_RANGE_4_BYTE_UTF16 = Integer.parseUnsignedInt("10FFFF", 16);

    private final String codepoint;

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


    public String toUTF8() {
        int parsedCodePointAsInt = Integer.parseUnsignedInt(this.codepoint, 16);

        if (parsedCodePointAsInt > END_RANGE_4_BYTE_UTF8) {
            throw new IllegalArgumentException("Cannot encode this codepoint to UTF8");
        }

        if (parsedCodePointAsInt >= START_RANGE_1_BYTE_UTF8
                && parsedCodePointAsInt <= END_RANGE_1_BYTE_UTF8) {

            return this.to1ByteUTF8();
        }
        if (parsedCodePointAsInt >= START_RANGE_2_BYTE_UTF8
                && parsedCodePointAsInt <= END_RANGE_2_BYTE_UTF8) {

            return this.to2ByteUTF8();
        }

        if (parsedCodePointAsInt >= START_RANGE_3_BYTE_UTF8
                && parsedCodePointAsInt <= END_RANGE_3_BYTE_UTF8) {

            return this.to3ByteUTF8();
        } else return this.to4ByteUTF8();
    }

    public String toUTF16() {
        int parsedCodePointAsInt = Integer.parseUnsignedInt(this.codepoint, 16);
        if ((parsedCodePointAsInt >= START_RANGE_2_BYTE_UTF16_FIRST_GAP && parsedCodePointAsInt <= END_RANGE_2_BYTE_UTF16_FIRST_GAP)
                || (parsedCodePointAsInt >= START_RANGE_2_BYTE_UTF16_SECOND_GAP && parsedCodePointAsInt <= END_RANGE_2_BYTE_UTF16_SECOND_GAP)) {
            return this.to2ByteUTF16();
        }
        if (parsedCodePointAsInt >= START_RANGE_4_BYTE_UTF16 && parsedCodePointAsInt <= END_RANGE_4_BYTE_UTF16) {
            return this.to4ByteUTF16();
        }
        throw new IllegalArgumentException(
                "Cannot convert this hexstring to UTF16"
        );
    }

    public String toUTF32() {
        int paddedZeros = 8 - this.codepoint.length();
        StringBuilder output = new StringBuilder(this.codepoint);
        for (int i = 0; i < paddedZeros; i++) {
            output.insert(0, "0");
        }
        return output.toString();
    }

    private String getBinaryStringOfCodepoint() {
        StringBuilder binaryString = new StringBuilder();
        for (char c : this.codepoint.toCharArray()) {
            binaryString.append(String.format("%4s", Integer.toBinaryString((byte) Character.digit(c, 16))).replace(" ", "0"));
        }
        return binaryString.toString();
    }

    private String convert8BytesTo2HexDigits(String byteInString) {
        String output = "";
        String byteQuarter1 = byteInString.substring(0, 4);
        String byteQuarter2 = byteInString.substring(4);
        int digit1 = Integer.parseInt(byteQuarter1, 2);
        int digit2 = Integer.parseInt(byteQuarter2, 2);

        output += Integer.toHexString(digit1).toUpperCase() + Integer.toHexString(digit2).toUpperCase();
        return output;
    }

    private String to1ByteUTF8() {
        return String.valueOf(Integer.parseUnsignedInt(this.codepoint));
    }

    private String to2ByteUTF8() {
        String byte1 = "110" + this.getBinaryStringOfCodepoint().substring(5, this.getBinaryStringOfCodepoint().length() - 6);
        String byte2 = "10" + this.getBinaryStringOfCodepoint().substring(this.getBinaryStringOfCodepoint().length() - 6);
        return this.convert8BytesTo2HexDigits(byte1)
                + convert8BytesTo2HexDigits(byte2);
    }

    private String to3ByteUTF8() {
        String byte1 = "1110" + (this.getBinaryStringOfCodepoint().substring(0, this.getBinaryStringOfCodepoint().length() - 12));
        String byte2 = "10" + this.getBinaryStringOfCodepoint().substring(4, this.getBinaryStringOfCodepoint().length() - 6);
        String byte3 = "10" + this.getBinaryStringOfCodepoint().substring(this.getBinaryStringOfCodepoint().length() - 6);

        return this.convert8BytesTo2HexDigits(byte1) + this.convert8BytesTo2HexDigits(byte2) + this.convert8BytesTo2HexDigits(byte3);
    }

    private String to4ByteUTF8() {
        String byte1 = "11110" + this.getBinaryStringOfCodepoint().substring(3, this.getBinaryStringOfCodepoint().length() - 18);
        String byte2 = "10" + this.getBinaryStringOfCodepoint().substring(6, this.getBinaryStringOfCodepoint().length() - 12);
        String byte3 = "10" + this.getBinaryStringOfCodepoint().substring(12, this.getBinaryStringOfCodepoint().length() - 6);
        String byte4 = "10" + this.getBinaryStringOfCodepoint().substring(this.getBinaryStringOfCodepoint().length() - 6);

        return this.convert8BytesTo2HexDigits(byte1) + this.convert8BytesTo2HexDigits(byte2) + this.convert8BytesTo2HexDigits(byte3) + this.convert8BytesTo2HexDigits(byte4);
    }

    private String to2ByteUTF16() {
        return String.valueOf(Integer.parseUnsignedInt(this.codepoint, 16));
    }

    private String to4ByteUTF16(){
        return "";
    }
}
