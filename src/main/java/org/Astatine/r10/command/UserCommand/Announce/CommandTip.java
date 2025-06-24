package org.Astatine.r10.command.UserCommand.Announce;


import org.Astatine.r10.Data.DataIO.Config.ConfigIOHandler;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public record CommandTip() implements CommandExecutor {

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        ConfigIOHandler configIOHandler = ConfigIOHandler.getConfigIOHandler();
        TextColor commandColor = ColorType.COMMAND_COLOR.getTextColor();
        TextColor enhanceColor = ColorType.PINK.getTextColor();

        Component text = Component.text()
            .append(Component.text(configIOHandler.getCommandEnhance(), enhanceColor))
            .append(Component.text(configIOHandler.getCommandFly(), commandColor))
            .append(Component.text(configIOHandler.getCommandHat(), commandColor))
            .append(Component.text(configIOHandler.getCommandSwap(), commandColor))
            .append(Component.text(configIOHandler.getCommandTotem(), commandColor))
            .append(Component.text(configIOHandler.getCommandAnnouncing(), commandColor))
            .append(Component.text(configIOHandler.getCommandTpa(), commandColor))
            .append(Component.text(configIOHandler.getCommandShop(), commandColor))
            .append(Component.text(configIOHandler.getCommandBalance(), commandColor))
            .append(Component.text(configIOHandler.getCommandParty(), commandColor))
            .append(Component.text(configIOHandler.getCommandSkill(), commandColor))
            .append(Component.text(configIOHandler.getCommandPlayTime(), commandColor))
            .build();

        commandSender.sendMessage(text);

        return true;
    }
}
