package org.Astatine.r10.command.UserCommand.Announce;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

import org.Astatine.r10.Data.DataIO.Config.ConfigIOHandler;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Util.Function.Emoji;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public record Community() implements CommandExecutor {

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        ConfigIOHandler configIOHandler = ConfigIOHandler.getConfigIOHandler();

        String notionLink = configIOHandler.getNotionLink();
        String discordLink = configIOHandler.getDiscordInviteLink();
        String mineListLink = configIOHandler.getMinelistLink();

        TextColor notionColor = ColorType.NOTION_COLOR.getTextColor();
        TextColor discordColor = ColorType.DISCORD_COLOR.getTextColor();
        TextColor mineListColor = ColorType.GREEN.getTextColor();

        commandSender.sendMessage(
        Component.text()
                        .append(Emoji.MINELIST.getComponentTypeEmoji()
                                .color(ColorType.WHITE.getTextColor())
                                )
                        .append(Component.text(configIOHandler.getMinelistLinkComment())
                                .color(mineListColor)
                                .clickEvent(ClickEvent.openUrl(mineListLink))
                                .decorate(TextDecoration.UNDERLINED)
                                )
                        .build()
        );

        commandSender.sendMessage(
        Component.text()
                        .append(Emoji.DISCORD.getComponentTypeEmoji()
                                .color(ColorType.WHITE.getTextColor())
                                )
                        .append(Component.text(configIOHandler.getDiscordInviteComment())
                                .color(discordColor)
                                .clickEvent(ClickEvent.openUrl(discordLink))
                                .decorate(TextDecoration.UNDERLINED)
                                )
                        .build()
        );

        commandSender.sendMessage(
        Component.text()
                        .append(Emoji.NOTION.getComponentTypeEmoji()
                                .color(ColorType.WHITE.getTextColor())
                                )
                        .append(Component.text(configIOHandler.getNotionLinkComment())
                                .color(notionColor)
                                .clickEvent(ClickEvent.openUrl(notionLink))
                                .decorate(TextDecoration.UNDERLINED)
                                )
                        .build()
        );

        return true;
    }
}
