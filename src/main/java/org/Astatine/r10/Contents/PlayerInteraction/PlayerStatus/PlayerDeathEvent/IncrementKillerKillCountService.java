package org.Astatine.r10.Contents.PlayerInteraction.PlayerStatus.PlayerDeathEvent;

import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Data.User.UserData.UserHandler;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatus;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusBuilder;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusHandler;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

public class IncrementKillerKillCountService implements EventRegister {
    private UserKillStatusHandler controller = new UserKillStatusHandler();
    private UserHandler userController = new UserHandler();

    private UserKillStatus userKillStatus;
    private final PlayerDeathEvent event;

    public IncrementKillerKillCountService(PlayerDeathEvent event) {
        this.event = event;

        init();
        execute();
    }

    @Override
    public void init() {
    }

    @Override
    public void execute() {
        Player player = this.event.getPlayer().getKiller();

        if (ObjectUtils.isEmpty(player))
            return;

        if (this.userController.readUser(player.getUniqueId()).godMode())
            return;

        this.userKillStatus = this.controller.readUser(player.getUniqueId());
        new UserKillStatusBuilder(this.userKillStatus)
                .killCount(this.userKillStatus.killCount() + 1)
                .buildAndUpdate();
    }
}
