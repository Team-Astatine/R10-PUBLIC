package org.Astatine.r10.command.ModeratorCommand;

import java.util.Optional;

import org.Astatine.r10.Data.User.UserData.User;
import org.Astatine.r10.Data.User.UserData.UserBuilder;
import org.Astatine.r10.Data.User.UserData.UserHandler;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.command.CommandRegister;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;


public class SetGodMode extends CommandRegister {
    private User senderUser;
    private Player senderPlayer;
    private boolean consoleSend = false;

    public SetGodMode() {
        super(GlobalCommandHandler.GOD_MODE_SET);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String @NotNull [] args) {

        try {
            this.senderUser = new UserHandler().readUser(sender.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional.ofNullable(this.senderUser).ifPresentOrElse(
                existUser -> this.senderPlayer = Bukkit.getPlayer(existUser.uuid()),
                () -> this.consoleSend = true
        );

        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (ObjectUtils.isEmpty(targetPlayer)) {
            String comment = "해당 유저는 존재하지 않습니다.";

            if (this.consoleSend)
                Bukkit.getLogger().info("[R10] " + comment);
            else playerSendMsgComponentExchanger(senderPlayer, comment, ColorType.RED);
            return false;
        }

        changeUserStatus(targetPlayer);
        consoleSend = false;
        return true;
    }


    private void changeUserStatus(Player targetPlayer) {
        User targetUser = new UserHandler().readUser(targetPlayer.getUniqueId());
        targetUser = new UserBuilder(targetUser)
                .isGodMode(!targetUser.godMode())
                .buildAndUpdate();
        setPotionEffect(targetPlayer, targetUser);


        String targetStatus = targetUser.godMode() ? "신" : "인간";
        String comment = "은(는) 이제 " + targetStatus + " 입니다.";

        if (consoleSend)
            Bukkit.getLogger().info("[R10] " + targetUser.nameList().getLast() + comment);

        else if (ObjectUtils.notEqual(this.senderPlayer, targetPlayer))
            playerSendMsgComponentExchanger(this.senderPlayer, targetUser.nameList().getLast() + comment, ColorType.ORANGE);

        playerSendMsgComponentExchanger(targetPlayer, "당신" + comment, ColorType.ORANGE);

    }

    public void setPotionEffect(Player targetPlayer, User targetUser) {
        if (targetUser.godMode()) {
            targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 100000000, 0));
            targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100000000, 0));
        } else {
            targetPlayer.removePotionEffect(PotionEffectType.SATURATION);
            targetPlayer.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
        }
    }
}
