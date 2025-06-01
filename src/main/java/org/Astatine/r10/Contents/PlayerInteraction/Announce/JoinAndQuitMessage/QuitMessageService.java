package org.Astatine.r10.Contents.PlayerInteraction.Announce.JoinAndQuitMessage;

import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatus;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusHandler;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Util.Function.StringComponentExchanger;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitMessageService extends StringComponentExchanger implements EventRegister {
    private Player player;
    private UserKillStatus userKillStatus;

    private final PlayerQuitEvent event;

    public QuitMessageService(PlayerQuitEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.player = this.event.getPlayer();
        this.userKillStatus = new UserKillStatusHandler().readUser(this.player.getUniqueId());
    }

    @Override
    public void execute() {
        if (this.userKillStatus.killCount() == 0)
            this.event.quitMessage(
                    componentExchanger(" - " + this.player.getName(), ColorType.RED)
            );

        else
            this.event.quitMessage(
                    componentExchanger(" - [ " + this.userKillStatus.killCount() + "KILL ] " + this.player.getName(), ColorType.RED)
            );
    }
}