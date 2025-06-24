package org.Astatine.r10.command.ModeratorCommand;

import java.util.Optional;

import org.Astatine.r10.Data.User.UserData.User;
import org.Astatine.r10.Data.User.UserData.UserHandler;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatus;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusHandler;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.command.CommandRegister;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;

public class GetUserValue extends CommandRegister {

    public GetUserValue() {
        super(GlobalCommandHandler.LOOK_USER_VALUE);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String @NotNull [] args) {

        User user = null;
        try {
            user = new UserHandler().readUser(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional.ofNullable(user)
                .ifPresentOrElse(
                        existUser -> {
                            UserKillStatus userKillStatus = new UserKillStatusHandler().readUser(existUser.uuid());
                            sendComment(sender, existUser + "\n\n" + userKillStatus);
                        },

                        () -> {
                            sendComment(sender, "존재하지 않는 유저");
                        }
                );

        return true;
    }

    private void sendComment(CommandSender sender, String comment) {
        if (sender instanceof Player)
            sender.sendMessage(
                Component.text()
                .append(Component.text(comment))
                .color(ColorType.YELLOW.getTextColor())
                .build()
            );
        else Bukkit.getLogger().info(comment);
    }
}
