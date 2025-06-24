package org.Astatine.r10.Contents.PlayerInteraction.PlayerStatus.PlayerRespawnEvent;

import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Util.Function.Emoji;
import org.Astatine.r10.Util.Function.StringComponentExchanger;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnMessageService extends StringComponentExchanger implements EventRegister {
    private Player player;
    private final PlayerRespawnEvent event;

    public RespawnMessageService(PlayerRespawnEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.player = this.event.getPlayer();
    }

    @Override
    public void execute() {
        deathRandomTeleport();
    }

    public void deathRandomTeleport() {
        if (ObjectUtils.isEmpty(this.player.getPotentialBedLocation()))
            return;

        String comment = this.event.isAnchorSpawn() ? "정박기로 텔레포트 합니다" : "침대로 텔레포트 합니다";

        this.player.sendMessage(
            emojiMessage(
                Emoji.CHECK, 
                comment,
                ColorType.WHITE_TO_RED7
            )
        );
    }
}
