package org.Astatine.r10.command.ModeratorCommand;

import java.util.Optional;

import org.Astatine.r10.Data.User.UserData.User;
import org.Astatine.r10.Data.User.UserData.UserHandler;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusBuilder;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusHandler;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.command.CommandRegisterSection;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class SetHealth extends CommandRegisterSection {
    private Player senderPlayer;
    private Player targetPlayer;
    private boolean consoleSend = false;

    public SetHealth() {
        super(GlobalCommandHandler.HEALTH_SCALE_SET);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String [] args) {

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

        if (ObjectUtils.allNotNull(senderUser) && BooleanUtils.isFalse(this.senderPlayer.isOp())) {
            playerSendMsgComponentExchanger(this.senderPlayer, "해당 명령어는 플레이어가 사용할 수 없습니다.", ColorType.RED);
            return false;
        }

        User targetUser = null;
        try {
            targetUser = new UserHandler().readUser(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional.ofNullable(Bukkit.getPlayer(targetUser.uuid())).ifPresent(
                player -> {
                    this.targetPlayer = player;
                    setPlayerHealth(Double.parseDouble(args[1]));
                }
        );
        return true;
    }

    private void setPlayerHealth(double setHealthValue) {
        UserKillStatusHandler userKillStatusController = new UserKillStatusHandler();
        userKillStatusController.healthUpdate(
                new UserKillStatusBuilder(userKillStatusController.readUser(this.targetPlayer.getUniqueId()))
                        .healthScale(setHealthValue)
                        .buildAndUpdate()
        );

        sendComment(setHealthValue);
    }

    private void sendComment(double setHealthValue) {
        String comment = this.targetPlayer.getName() + "님의 체력이" + setHealthValue + "으로 설정됐습니다.";
        if (consoleSend) {
            Bukkit.getLogger().info("[R10] " + comment);
            return;
        }

        if (ObjectUtils.notEqual(this.senderPlayer, this.targetPlayer))
            playerSendMsgComponentExchanger(this.senderPlayer, comment, ColorType.YELLOW);
        playerSendMsgComponentExchanger(this.targetPlayer, comment, ColorType.YELLOW);
    }
}
