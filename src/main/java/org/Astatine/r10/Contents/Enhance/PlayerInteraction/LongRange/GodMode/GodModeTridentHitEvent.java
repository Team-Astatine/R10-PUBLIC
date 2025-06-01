package org.Astatine.r10.Contents.Enhance.PlayerInteraction.LongRange.GodMode;

import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Data.User.UserData.UserHandler;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.entity.ProjectileHitEvent;


public class GodModeTridentHitEvent implements EventRegister {
    private final ProjectileHitEvent event;

    public GodModeTridentHitEvent(ProjectileHitEvent event) {
        this.event = event;
        execute();
    }

    @Override
    public void init() {
    }

    @Override
    public void execute() {
        if (!(this.event.getEntity() instanceof Trident trident))
            return;

        if (!(this.event.getEntity().getShooter() instanceof Player shooter))
            return;

        if (!new UserHandler().readUser(shooter.getUniqueId()).godMode())
            return;

        Runnable tridentTask = () -> {
            trident.setLoyaltyLevel(3);
            trident.setGlowing(true);

            Location loc = trident.getLocation();
            loc.getWorld().strikeLightning(loc);
            loc.getWorld().createExplosion(loc, 4F, true);
            trident.getWorld().playSound(loc, Sound.ITEM_TRIDENT_THUNDER, 5F, 5F);
        };

        tridentTask.run();
//        if (this.event.getHitBlock() != null && this.event.getHitBlock().getType() != Material.BEDROCK)
//            tridentTask.run();
    }
}
