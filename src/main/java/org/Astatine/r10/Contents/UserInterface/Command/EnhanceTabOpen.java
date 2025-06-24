package org.Astatine.r10.Contents.UserInterface.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.Astatine.r10.Contents.UserInterface.Enhance.EnhanceUI;
import org.Astatine.r10.command.CommandRegister;
import org.Astatine.r10.command.GlobalCommandHandler;

public class EnhanceTabOpen extends CommandRegister {

    public EnhanceTabOpen() {
        super(GlobalCommandHandler.ENHANCE_TAB_OPEN);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        new EnhanceUI((Player) commandSender);
        return true;
    }
}
