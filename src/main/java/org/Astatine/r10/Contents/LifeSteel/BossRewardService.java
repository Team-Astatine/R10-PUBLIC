package org.Astatine.r10.Contents.LifeSteel;

import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatus;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusBuilder;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusHandler;
import org.Astatine.r10.Enumeration.Type.BossType;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Util.Function.StringComponentExchanger;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;

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

        String comment;
        if (newHealthScale < MAX_HEALTH_SCALE)
            comment = String.format("%s님이 %s를 처치하여 %s를 보상받았습니다.",
                    playerName, bossName, this.bossType.getRewardHeartEmoji());

        else
            comment = String.format("%s님이 %s를 처치하여 최대 체력이 되었습니다.",
                    playerName, bossName);


        playerSendMsgComponentExchanger(this.bossSlayerPlayer, comment, ColorType.WHITE_TO_RED7);
    }
}
