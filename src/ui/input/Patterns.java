package ui.input;

/**
 * This enum contains all input patterns, which used in this project.
 * Enum contain link, and string data of pattern.
 * */
public enum Patterns {
    DIGITS15("^[12345]$"),
    DIGITS16("^[123456]$"),
    EMAIL("^(.+)@(.+).(.+)$"),
    YN("^[YyNn]$"),
    DIGITS("^(\\d+)$"),
    DIGITS12("^[12]$"),
    /**
     * This pattern found on <a href="https://www.baeldung.com/java-date-regular-expressions">baeldung</a>
     */
    DATE("^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
            + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$");
    private final String pattern;

    Patterns(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

}
