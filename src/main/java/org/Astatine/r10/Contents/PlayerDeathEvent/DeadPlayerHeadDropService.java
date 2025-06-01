package org.Astatine.r10.Contents.PlayerDeathEvent;

import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Contents.UserInterface.Core.UIUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeadPlayerHeadDropService extends UIUtils implements EventRegister {

    private Player player;
    private final PlayerDeathEvent event;

    public DeadPlayerHeadDropService(PlayerDeathEvent event) {
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
        this.event.getDrops().add(getHeadItemStack(this.player));
    }
}
