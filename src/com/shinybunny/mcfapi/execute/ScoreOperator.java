package com.shinybunny.mcfapi.execute;

public enum ScoreOperator {
    LESS("<"), LESS_OR_EQUAL("<="), EQUALS("="), GREATER_OR_EQUAL(">="), GREATER(">");

    private final String sign;

    ScoreOperator(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
