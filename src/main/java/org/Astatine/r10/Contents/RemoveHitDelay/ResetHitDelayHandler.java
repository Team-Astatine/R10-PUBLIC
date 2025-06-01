package org.Astatine.r10.Contents.RemoveHitDelay;

import org.Astatine.r10.Contents.EventRegister;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public class ResetHitDelayHandler implements EventRegister {

    private Player player;
    private final PlayerInteractEvent event;

    public ResetHitDelayHandler(PlayerInteractEvent event) {
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
//        default == 20
        if (this.player.getMaximumNoDamageTicks() == 20)
            return;

        this.player.setMaximumNoDamageTicks(20);
    }
}
