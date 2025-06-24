package org.Astatine.r10.Contents.LifeSteel;

import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Data.User.UserData.User;
import org.Astatine.r10.Data.User.UserData.UserHandler;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatus;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusBuilder;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusHandler;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Util.Function.Emoji;
import org.Astatine.r10.Util.Function.StringComponentExchanger;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.kyori.adventure.text.Component;


public class LifeSteelEvent extends StringComponentExchanger implements EventRegister {
    private final UserHandler userController = new UserHandler();
    private final UserKillStatusHandler userKillStatusController = new UserKillStatusHandler();

    private Player deathPlayer;
    private UserKillStatus deathUserKillStatusObj;

    private Player killer;
    private User killerUserObj;
    private UserKillStatus killerKillStatusObj;

    private final PlayerDeathEvent event;

    public LifeSteelEvent(PlayerDeathEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.deathPlayer = this.event.getPlayer();
        this.killer = deathPlayer.getKiller();
        this.killerUserObj = this.userController.readUser(this.deathPlayer.getUniqueId());
        this.deathUserKillStatusObj = this.userKillStatusController.readUser(this.deathPlayer.getUniqueId());
    }

    @Override
    public void execute() {
        if (isDeathUserGodMode())
            return;
        if (isNotAllowedThisEvent())
            return;
        lifeSteel();
    }

    private boolean isDeathUserGodMode() {
        if (BooleanUtils.isFalse(this.killerUserObj.godMode()))
            return false;

        Location playerLocation = this.deathPlayer.getLocation();
        playerLocation.setY(playerLocation.getY() + 2.0);
        Runnable undyingEventTask = () -> {
            playerLocation.getWorld().playSound(playerLocation, Sound.ENTITY_WITHER_SPAWN, 1.0f, 1.0f);
            playerLocation.getWorld().spawnParticle(Particle.TOTEM_OF_UNDYING, playerLocation, 200);
//                playerLocation.createExplosion(60);
        };

        undyingEventTask.run();
        this.event.setCancelled(true);
        return true;
    }

    private boolean isNotAllowedThisEvent() {
        //무조건 플레이어가 죽여야함
        if (ObjectUtils.isEmpty(this.killer))
            return true;

        //스스로가 스스로를 죽이면 무시함
        if (this.deathPlayer.equals(this.killer)) {
            this.event.deathMessage(componentExchanger(this.killerUserObj.nameList().getLast() + " 님이 자살했습니다.", ColorType.RED));
            return true;
        }

        return false;
    }

    private void lifeSteel() {
        this.killerKillStatusObj = this.userKillStatusController.readUser(this.killer.getUniqueId());

        double MAX_HEALTH_SCALE = 60.0;
        double MIN_HEALTH_SCALE = 4.0;
        double STEP_SIZE = 2.0;

        //valid Logic
        if (this.deathPlayer.getHealthScale() <= MIN_HEALTH_SCALE ||
                this.killer.getHealthScale() >= MAX_HEALTH_SCALE ||
                this.deathPlayer == killer)
            return;

        //execute Logic
        this.userKillStatusController.healthUpdate(
                new UserKillStatusBuilder(this.deathUserKillStatusObj)
                        .healthScale(this.deathPlayer.getHealthScale() - STEP_SIZE)
                        .build());

        this.userKillStatusController.healthUpdate(
                new UserKillStatusBuilder(this.killerKillStatusObj)
                        .healthScale(this.killer.getHealthScale() + STEP_SIZE)
                        .build());

        this.deathPlayer.sendMessage(
            emojiMessage(
                Emoji.CROSSED_SWORDS, 
                Component.text()
                    .append(this.killer.displayName())
                    .append(Component.text("님이 체력을 약탈했어요 ㅠㅠ"))
                    .build(),
                ColorType.WHITE_TO_RED7
            )
        );

        this.killer.sendMessage(
            emojiMessage(
                Emoji.CROSSED_SWORDS, 
                Component.text()
                    .append(this.deathPlayer.displayName())
                    .append(Component.text("님의 체력을 약탈했어요 흐흐.."))
                    .build(),
                ColorType.WHITE_TO_RED7
            )
        );
    }
}
