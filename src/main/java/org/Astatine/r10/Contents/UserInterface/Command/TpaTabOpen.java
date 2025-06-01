package org.Astatine.r10.Contents.UserInterface.Command;

import org.Astatine.r10.Contents.UserInterface.TPA.TpaUI;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.command.CommandRegisterSection;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TpaTabOpen extends CommandRegisterSection {

    public TpaTabOpen() {
        super(GlobalCommandHandler.TPA_TAB_OPEN);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        if (Bukkit.getOnlinePlayers().size() == 1) {
            commandSender.sendMessage(componentExchanger("온라인 플레이어가 없습니다.", ColorType.RED));
            return false;
        }

        new TpaUI((Player) commandSender);
        return true;
    }
}
