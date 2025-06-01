package org.Astatine.r10.command.UserCommand.Function;

import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.command.CommandRegisterSection;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class ToggleFly extends CommandRegisterSection {

    public ToggleFly() {
        super(GlobalCommandHandler.FLY);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        Player targetPlayer = (Player) sender;

        targetPlayer.setAllowFlight(!targetPlayer.getAllowFlight());

        String comment = targetPlayer.getAllowFlight() ? "활성화" : "비활성화";
        playerSendMsgComponentExchanger(targetPlayer, "플라이 " + comment, ColorType.YELLOW);
        return true;
    }
}