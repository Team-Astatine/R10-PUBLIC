package org.Astatine.r10.Contents.CustomizationExploded;

import org.Astatine.r10.R10;
import org.Astatine.r10.Contents.EventRegister;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplosiveService implements EventRegister {
    private Location location;
    private final EntityExplodeEvent event;

    public ExplosiveService(EntityExplodeEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.location = this.event.getLocation();
    }

    @Override
    public void execute() {
//        TNT: 4 blocks
//        Creeper:
//        isNormal -> 3 blocks
//        isCharged -< 6 blocks
//        Ghast Fireball: 1 block
//        Wither Skull:
//        isBlueSkull -> 1 block
//        isBlackSkull -> Varies depending on difficulty
        switch (this.event.getEntityType()) {
            case CREEPER -> creeperBoom();
            case FIREBALL -> ghastBoom();
            case WITHER_SKULL -> witherBoom();
            case TNT -> boomBer();
            case TNT_MINECART -> cartBoom();
            case END_CRYSTAL -> endCrystal();
            default -> this.event.setCancelled(true);
        }
    }

    private void creeperBoom() {
        Creeper creeper = (Creeper) this.event.getEntity();
        int explosiveRadius = creeper.isPowered() ? 100 : 5;
        taskExecute(explosiveRadius);
    }

    private void ghastBoom() {
        taskExecute(3);
    }

    private void witherBoom() {
        WitherSkull witherSkull = (WitherSkull) this.event.getEntity();
        int explosiveRadius = witherSkull.isCharged() ? 130 : 40;
        taskExecute(explosiveRadius);
    }

    private void boomBer() {
        taskExecute(15);
    }

    private void cartBoom() {
        taskExecute(25);
    }

    private void endCrystal() {
        taskExecute(15);
    }

    private void taskExecute(int explosiveRadius) {
        Bukkit.getScheduler().runTask(R10.getPlugin(R10.class), () -> {
            this.location.createExplosion(explosiveRadius, true);
        });
    }
}
