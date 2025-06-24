package org.Astatine.r10.Contents.LifeSteel;

import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatus;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusBuilder;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusHandler;
import org.Astatine.r10.Enumeration.Type.BossType;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Util.Function.Emoji;
import org.Astatine.r10.Util.Function.StringComponentExchanger;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;

public class BossRewardService extends StringComponentExchanger implements EventRegister {
    private final UserKillStatusHandler userKillStatusController = new UserKillStatusHandler();

    private BossType bossType;

    private Player bossSlayerPlayer;
    private UserKillStatus bossSlayerUser;

    private final EntityDeathEvent event;

    public BossRewardService(EntityDeathEvent event) {
        this.event = event;

        this.bossType = BossType.findByEntityType(this.event.getEntityType());
        if (bossType == null)
            return;

        init();
        execute();
    }

    @Override
    public void init() {
        this.bossSlayerPlayer = this.event.getEntity().getKiller();
        this.bossSlayerUser = this.userKillStatusController.readUser(this.bossSlayerPlayer.getUniqueId());
    }

    @Override
    public void execute() {
        double MAX_HEALTH_SCALE = 60.0;
        double newHealthScale = this.bossSlayerPlayer.getHealthScale() + this.bossType.getRewardHealth();

        if (bossSlayerPlayer.getHealthScale() >= 60)
            return;

        if (newHealthScale >= MAX_HEALTH_SCALE)
            newHealthScale = MAX_HEALTH_SCALE;

        this.userKillStatusController.healthUpdate(
                new UserKillStatusBuilder(this.bossSlayerUser)
                        .healthScale(newHealthScale)
                        .build()
        );

        String playerName = this.bossSlayerPlayer.getName();
        String bossName = this.bossType.getBossName();

        Component comment;
        if (newHealthScale < MAX_HEALTH_SCALE)
            comment = Component.text()
                            .append(Component.text(
                                String.format("%s님이 %s를 처치해서 ",playerName, bossName))
                                .color(ColorType.WHITE_TO_RED7.getTextColor())
                                .decorate(TextDecoration.BOLD)
                                )
                            .append(Component.text(this.bossType.getRewardHeartEmoji())
                                .color(ColorType.WHITE.getTextColor())
                                )
                            .append(Component.text("를 보상을 받았어요!")
                                .color(ColorType.WHITE_TO_RED7.getTextColor())
                                .decorate(TextDecoration.BOLD)
                                )
                            .build();

        else
            comment = Component.text()
                            .append(Component.text(
                                String.format("%s님이 %s를 처치하여 최대 체력이 되었어요!!",playerName, bossName))
                                )
                            .color(ColorType.YELLOW.getTextColor())
                            .decorate(TextDecoration.BOLD)
                            .build();

        this.bossSlayerPlayer.sendMessage(
            Component.text()
                        .append(
                            Emoji.EXPLODING_PARTY.getComponentTypeEmoji()
                                .color(ColorType.WHITE.getTextColor())                
                        )
                        .append(comment)
                        .build()
        );        
    }
}
