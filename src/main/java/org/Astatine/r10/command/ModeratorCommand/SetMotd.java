package org.Astatine.r10.command.ModeratorCommand;

import org.Astatine.r10.Data.DataIO.Config.ConfigIOHandler;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.command.CommandRegisterSection;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class SetMotd extends CommandRegisterSection {

    private static final String DEFAULT_SERVER_MOTD = "Astatine Online";
    private String newMotd;

    public SetMotd() {
        super(GlobalCommandHandler.MOTD_SET);
        this.newMotd = DEFAULT_SERVER_MOTD;
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        getteringNewMotd(args);
        sendComment(sender);
        configDataUpdate();
        return false;
    }

    private void getteringNewMotd(String @NotNull [] newMotd) {
        if (newMotd.length == 0) {
            this.newMotd = DEFAULT_SERVER_MOTD;
            return;
        }

        StringBuilder customMotd = new StringBuilder();
        for (String motd : newMotd)
            customMotd.append(motd).append(" ");

        this.newMotd = customMotd.toString().trim();
    }

    private void sendComment(CommandSender sender) {
        String comment = "New Motd Set -> " + this.newMotd;
        if (sender instanceof Player player) {
            playerSendMsgComponentExchanger(player, comment, ColorType.YELLOW);
        } else Bukkit.getLogger().info(comment);
    }

    private void configDataUpdate() {
        Bukkit.motd(componentExchanger(this.newMotd, ColorType.VOTE_COLOR));
        ConfigIOHandler.getConfigIOHandler().worldConfigSave(this.newMotd);
    }
}
