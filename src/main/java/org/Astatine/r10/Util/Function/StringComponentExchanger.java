package org.Astatine.r10.Util.Function;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.Astatine.r10.Data.DataIO.Config.ConfigMenu;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public abstract class StringComponentExchanger {
    private Component exchangerStringToComponentAndColor(String comment, ColorType color) {
        return Component.text()
                .content(comment)
                .color(color.getTextColor())
                .build();
    }

    public @NotNull Component createLinkComponentExchanger(String comment, String url, @NotNull ColorType color) {
        return Component.text()
                .content(comment)
                .color(color.getTextColor())
                .clickEvent(ClickEvent.openUrl(url))
                .build();
    }

    public void playerSendMsgComponentExchanger(@NotNull Player player, String comment, @NotNull ColorType color) {
        player.sendMessage(exchangerStringToComponentAndColor(comment, color));
    }

    public void playerSendMsgComponentExchanger(@NotNull CommandSender sender, @NotNull String comment, @NotNull ColorType color) {
        playerSendMsgComponentExchanger((Player) sender, comment, color);
    }

    public Component componentExchanger(String comment, ColorType color) {
        return exchangerStringToComponentAndColor(comment, color);
    }

    public String componentExchanger(Component comment) {
        String result = PlainTextComponentSerializer.plainText().serialize(comment);

        /*
        String input = "Name: John, Age: 30";
        Pattern pattern = Pattern.compile("Name: (.*?), Age: (\\d+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            System.out.println("group(0): " + matcher.group(0)); // Name: John, Age: 30
            System.out.println("group(1): " + matcher.group(1)); // John
            System.out.println("group(2): " + matcher.group(2)); // 30
        }
         */

        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(result);

        if (matcher.find()) {
            result = matcher.group(1); // 대괄호 안의 내용만 추출
        }

        return result;
    }
}
