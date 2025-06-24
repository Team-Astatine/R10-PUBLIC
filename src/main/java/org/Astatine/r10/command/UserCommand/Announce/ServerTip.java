package org.Astatine.r10.command.UserCommand.Announce;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.Astatine.r10.Data.DataIO.Config.ConfigIOHandler;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;


public record ServerTip() implements CommandExecutor {
    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        ConfigIOHandler configIOHandler = ConfigIOHandler.getConfigIOHandler();
        TextColor commonColor = ColorType.WHITE_TO_RED3.getTextColor();

        commandSender.sendMessage(
        Component.text()
                        .append(Component.text(configIOHandler.getHeartTip(), commonColor))
                        .append(Component.text(configIOHandler.getExtraHeartTip(), commonColor))
                        .append(Component.text(configIOHandler.getSteelLifeTip(), commonColor))
                        .append(Component.text(configIOHandler.getRaidTip(), commonColor))
                        .append(Component.text(configIOHandler.getWeaponTip(), commonColor))
                        .append(Component.text(configIOHandler.getExplosiveTip(), commonColor))
                        .build()
        );

        return true;
    }
}
