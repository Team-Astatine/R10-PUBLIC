package org.Astatine.r10.command.UserCommand.Announce;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.Astatine.r10.Data.DataIO.Config.ConfigIOHandler;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public record ServerTip() implements CommandExecutor {
    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        ConfigIOHandler configIOHandler = ConfigIOHandler.getConfigIOHandler();
        TextColor commonColor = ColorType.RED.getTextColor();

        ArrayList<Component> serverTip = new ArrayList<>();
        serverTip.add(Component.text(configIOHandler.getHeartTip(), commonColor));
        serverTip.add(Component.text(configIOHandler.getExtraHeartTip(), commonColor));
        serverTip.add(Component.text(configIOHandler.getSteelLifeTip(), commonColor));
        serverTip.add(Component.text(configIOHandler.getRaidTip(), commonColor));
        serverTip.add(Component.text(configIOHandler.getWeaponTip(), commonColor));
        serverTip.add(Component.text(configIOHandler.getExplosiveTip(), commonColor));

        for (Component comment : serverTip)
            commandSender.sendMessage(comment);

        return true;
    }
}
