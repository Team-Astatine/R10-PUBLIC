package org.Astatine.r10.command.ModeratorCommand;

import java.util.Optional;

import org.Astatine.r10.Data.Company.CompanyData.Company.CompanyController;
import org.Astatine.r10.Data.DataIO.User.DataFile;
import org.Astatine.r10.Data.DataIO.User.RObjectIOHandler;
import org.Astatine.r10.Data.User.UserData.User;
import org.Astatine.r10.Data.User.UserData.UserHandler;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusHandler;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.command.CommandRegister;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class ExportAllData extends CommandRegister {
    private Player senderPlayer;
    private boolean consoleSend = false;

    public ExportAllData() {
        super(GlobalCommandHandler.EXPORT_DATA_FILE);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        User senderUser = null;
        try {
            senderUser = new UserHandler().readUser(sender.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional.ofNullable(senderUser).ifPresentOrElse(
                existUser -> this.senderPlayer = Bukkit.getPlayer(existUser.uuid()),
                () -> this.consoleSend = true
        );

        switch (args[0].toLowerCase()) {
            case "userData" -> 
                new RObjectIOHandler().exportData(
                    DataFile.USER_DATA, new UserHandler().getAllUserTable(), getClass().getName()
                    );

            case "userKillStatus" -> 
                new RObjectIOHandler().exportData(
                    DataFile.KILL_STATUS, new UserKillStatusHandler().getAllUserTable(), getClass().getName()
                    );

            // case "company" -> 
            //     new RObjectIOHandler().exportData(
            //         DataFile.COMPANY, new CompanyController().getAllCompanies(), getClass().getName()
            //         );

            default -> {
                new RObjectIOHandler().exportData(DataFile.USER_DATA, new UserHandler().getAllUserTable(), getClass().getName());
                new RObjectIOHandler().exportData(DataFile.KILL_STATUS, new UserKillStatusHandler().getAllUserTable(), getClass().getName());
                // new RObjectIOHandler().exportData(DataFile.COMPANY, new CompanyController().getAllCompanies(), getClass().getName());
            }
        }

        sendComment("Success to exporting " + args[0]);
        return true;
    }

    private void sendComment(String comment) {
        if (consoleSend && ObjectUtils.isEmpty(this.senderPlayer))
            Bukkit.getLogger().info("[R10] " + comment);
        else playerSendMsgComponentExchanger(this.senderPlayer, comment, ColorType.YELLOW);
    }
}
