package org.Astatine.r10.Enumeration.Type;

import java.util.Arrays;
import java.util.List;

import net.kyori.adventure.text.format.TextColor;

public enum ColorType {
    WHITE(TextColor.color(255, 255, 255)),
    WHITE_TO_RED1(TextColor.color(255, 227, 242)),
    WHITE_TO_RED2(TextColor.color(255, 204, 226)),
    WHITE_TO_RED3(TextColor.color(255, 181, 210)),
    PINK(TextColor.color(255, 182, 193)),
    WHITE_TO_RED4(TextColor.color(255, 158, 194)),
    WHITE_TO_RED5(TextColor.color(255, 135, 178)),
    WHITE_TO_RED6(TextColor.color(255, 112, 162)),
    WHITE_TO_RED7(TextColor.color(255, 89, 146)),
    WHITE_TO_RED8(TextColor.color(255, 66, 130)),
    RED(TextColor.color(233, 30, 100)),
    ORANGE(TextColor.color(255, 142, 0)),
    YELLOW(TextColor.color(255, 228, 0)),
    GREEN(TextColor.color(47, 255, 0)),
    COMMAND_COLOR(TextColor.color(139, 195, 74)),
    SKY_BLUE(TextColor.color(173, 216, 230)),
    DISCORD_COLOR(TextColor.color(114, 137, 218)),
    VOTE_COLOR(TextColor.color(167, 123, 202)),
    NOTION_COLOR(TextColor.color(129, 90, 157)),
    PURPLE(TextColor.color(139, 0, 255)),
    GRAY(TextColor.color(86, 86, 86));

    private final TextColor textColor;

    ColorType(TextColor colorName) {
        this.textColor = colorName;
    }

    public TextColor getTextColor() {
        return this.textColor;
    }

    public static List<ColorType> getWhiteToRedArrays() {
        return Arrays.asList(
            WHITE,
            WHITE_TO_RED1,
            WHITE_TO_RED2,
            WHITE_TO_RED3,
            WHITE_TO_RED4,
            WHITE_TO_RED5,
            WHITE_TO_RED6,
            WHITE_TO_RED7,
            WHITE_TO_RED8,
            RED
        );
    }
}