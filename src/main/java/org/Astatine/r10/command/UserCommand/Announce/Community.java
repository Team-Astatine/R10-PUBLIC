package org.Astatine.r10.command.UserCommand.Announce;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
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

                sendLinkMessage(commandSender, Emoji.MINELIST,
                configIOHandler.getMinelistLinkComment(), mineListLink, mineListColor);
                sendLinkMessage(commandSender, Emoji.DISCORD,
                configIOHandler.getDiscordInviteComment(), discordLink, discordColor);
                sendLinkMessage(commandSender, Emoji.NOTION,
                configIOHandler.getNotionLinkComment(), notionLink, notionColor);

                return true;
        }

        private void sendLinkMessage(CommandSender sender, Emoji emoji, String comment, String url, TextColor color) {
        sender.sendMessage(
                Component.text()
                .append(emoji.getComponentTypeEmoji()
                        .color(ColorType.WHITE.getTextColor())
                )
                .append(Component.text(comment)
                        .color(color)
                        .clickEvent(ClickEvent.openUrl(url))
                        .hoverEvent(HoverEvent.showText(
                                Component.text(url + " 열기"))
                        )
                        .decorate(TextDecoration.UNDERLINED)
                )
                .build()
        );
    }
}
