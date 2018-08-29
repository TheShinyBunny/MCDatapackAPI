package com.shinybunny.mcfapi;

public enum ChatColor {
    BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE, RESET, BOLD, ITALIC, UNDERLINE, STRIKETHROUGH, OBFUSCATED;

    public boolean isFormat() {
        return this == BOLD || this == ITALIC || this == UNDERLINE || this == OBFUSCATED || this == STRIKETHROUGH;
    }

    public boolean isColor() {
        return this != RESET && !isFormat();
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
