package org.Astatine.r10.Util.Function;

import net.kyori.adventure.text.Component;

public enum Emoji {
    MINECRAFT_HEART('\ue000'),
    SKULL('\ue001'),
    LOUDER('\ue002'),
    WARNING('\ue003'),
    EXPLODING_PARTY('\ue004'),
    SAD_FACE('\ue005'),
    FIRE('\ue006'),
    NOTION('\ue007'),
    DISCORD('\ue008'),
    MINELIST('\ue009'),
    CROSSED_SWORDS('\ue010'),
    CHECK('\ue011');

    private final char emojiChar;
    
    Emoji(Character c) {
        this.emojiChar = c;
    }

    public String getStringTypeEmoji() {
        return String.format("%c", emojiChar);
    }

    public Component getComponentTypeEmoji() {
        return Component.text(
            String.format("%c", emojiChar)
            );
    }
}
