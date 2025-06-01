package org.Astatine.r10.Contents.Restricted.Function.Event.EntityExplode;

import org.Astatine.r10.Contents.EventRegister;
import org.bukkit.damage.DamageType;
import org.bukkit.event.entity.EntityDamageEvent;

public class RestrictedExplosiveDamageHandler implements EventRegister {
    private final EntityDamageEvent event;

    public RestrictedExplosiveDamageHandler(EntityDamageEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
    }

    @Override
    public void execute() {
        if (this.event.getDamageSource().getDamageType() != DamageType.EXPLOSION)
            return;

        switch (this.event.getEntity().getType()) {
            case GHAST, ENDER_DRAGON, WITHER -> this.event.setCancelled(true);
            default -> {}
        }
    }
}
