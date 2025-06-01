package org.Astatine.r10.Contents.UserInterface.Command;

import net.ess3.api.IEssentials;
import org.Astatine.r10.Contents.UserInterface.Home.HomeUI;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.command.CommandRegisterSection;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HomeTabOpen extends CommandRegisterSection {

    public HomeTabOpen() {
        super(GlobalCommandHandler.HOME_TAB_OPEN);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        IEssentials essAPI = (IEssentials) Bukkit.getServer()
                .getPluginManager()
                .getPlugin("Essentials");

        int senderHomesCount = essAPI.getUser(((Player) commandSender).getUniqueId())
                .getHomes()
                .size();

        if (senderHomesCount == 0) {
            commandSender.sendMessage(componentExchanger("설정된 홈이 없습니다.", ColorType.RED));
            return false;
        }

        new HomeUI((Player) commandSender);
        return true;
    }
}
