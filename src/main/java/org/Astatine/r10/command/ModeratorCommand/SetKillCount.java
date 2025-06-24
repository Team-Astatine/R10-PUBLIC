package org.Astatine.r10.command.ModeratorCommand;

import java.util.Optional;

import org.Astatine.r10.Data.User.UserData.User;
import org.Astatine.r10.Data.User.UserData.UserHandler;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusBuilder;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.command.CommandRegister;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetKillCount extends CommandRegister {
    private Player senderPlayer;
    private Player targetPlayer;
    private boolean consoleSend = false;

    public SetKillCount() {
        super(GlobalCommandHandler.KILL_COUNT_SET);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        User senderUser = null;
        try {
            senderUser = new UserHandler().readUser(commandSender.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional.ofNullable(senderUser).ifPresentOrElse(
                existUser -> this.senderPlayer = Bukkit.getPlayer(existUser.uuid()),
                () -> this.consoleSend = true
        );

        User targetUser = null;
        try {
            targetUser = new UserHandler().readUser(strings[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int setKillCount = Integer.parseInt(strings[1]);
        Optional.ofNullable(Bukkit.getPlayer(targetUser.uuid())).ifPresent(
                player -> {
                    this.targetPlayer = player;
                    new UserKillStatusBuilder(this.targetPlayer)
                            .killCount(setKillCount)
                            .buildAndUpdate();
                }
        );

        sendComment(setKillCount);
        return true;
    }

    private void sendComment(int setHealthValue) {
        String comment = this.targetPlayer.getName() + "님의 킬 카운트가 " + setHealthValue + "으로 설정됐습니다.";
        if (consoleSend) {
            Bukkit.getLogger().info("[R10] " + comment);
            return;
        }

        if (ObjectUtils.notEqual(this.senderPlayer, this.targetPlayer))
            playerSendMsgComponentExchanger(this.senderPlayer, comment, ColorType.YELLOW);
        playerSendMsgComponentExchanger(this.targetPlayer, comment, ColorType.YELLOW);
    }
}
