package org.Astatine.r10.command.UserCommand.Announce;

import org.Astatine.r10.Data.DataIO.Config.ConfigIOHandler;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Util.Function.Emoji;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;

public record Help() implements CommandExecutor {

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        ConfigIOHandler configIOHandler = ConfigIOHandler.getConfigIOHandler();
        
        commandSender.sendMessage(
            Component.text()
                            .append(Emoji.LOUDER.getComponentTypeEmoji()
                                    .color(ColorType.WHITE.getTextColor())
                                    )
                            .append(Component.text(configIOHandler.getHelp())
                                    .color(ColorType.WHITE_TO_RED7.getTextColor())
                                    )
                            .decorate(TextDecoration.BOLD)
                            .build()
                );
        return true;
    }
}
