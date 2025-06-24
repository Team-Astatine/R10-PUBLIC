package org.Astatine.r10.command.ModeratorCommand;

import java.util.Optional;

import org.Astatine.r10.R10;
import org.Astatine.r10.Data.User.UserData.User;
import org.Astatine.r10.Data.User.UserData.UserHandler;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.command.CommandRegister;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class ReloadConfigData extends CommandRegister {

    private Player player;
    private boolean consoleSend = false;

    public ReloadConfigData() {
        super(GlobalCommandHandler.CONFIG_RELOAD);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        User user = null;
        try {
            user = new UserHandler().readUser(sender.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional.ofNullable(user).ifPresentOrElse(
                existUser -> this.player = Bukkit.getPlayer(existUser.uuid()),
                () -> this.consoleSend = true
        );

        sendComment();
        R10.getPlugin(R10.class).configFileLoader();
        return true;
    }

    private void sendComment() {
        String comment = "Reload Done";
        if (this.consoleSend)
            Bukkit.getLogger().info("[R10] " + comment);
        else playerSendMsgComponentExchanger(this.player, comment, ColorType.YELLOW);
    }
}
