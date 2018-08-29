package com.shinybunny.mcfapi.scoreboard;

public enum Operator {
    ADD("+="), SUBTRACT("-="), MULTIPLY("*="), DIVIDE("/="), MODULO("%="), ASSIGN("="), MINIMUM("<"), MAXIMUM(">"), SWAP("><");

    private final String sign;

    Operator(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
