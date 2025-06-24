package org.Astatine.r10.Contents.PlayerInteraction.Announce.Raid;

import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Enumeration.Type.WorldType;
import org.Astatine.r10.Util.Function.Emoji;
import org.Astatine.r10.Util.Function.StringComponentExchanger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.raid.RaidTriggerEvent;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;


public class RaidAnnouncementService extends StringComponentExchanger implements EventRegister {
    private Location location;
    private String activeWorld;
    private final RaidTriggerEvent event;

    public RaidAnnouncementService(RaidTriggerEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.location = this.event.getRaid().getLocation();
        this.activeWorld = this.event.getWorld().getName();
    }

    @Override
    public void execute() {
        WorldType activeWorld = WorldType.findByWorldName(this.activeWorld);

        double x = this.location.getX();
        double z = this.location.getZ();

        Bukkit.broadcast(
            emojiMessage(
                Emoji.LOUDER, 
                String.format(" %s 월드의 X : %1.0f | Z : %1.0f 에서 레이드가 시작됐어요!",
                    activeWorld.getKoreanWorldName(), x, z),
                ColorType.ORANGE
            )
        );
    }
}
