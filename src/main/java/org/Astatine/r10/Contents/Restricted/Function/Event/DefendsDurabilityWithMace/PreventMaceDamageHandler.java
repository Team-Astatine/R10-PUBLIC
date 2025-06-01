package org.Astatine.r10.Contents.Restricted.Function.Event.DefendsDurabilityWithMace;

import org.Astatine.r10.Contents.EventRegister;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class PreventMaceDamageHandler implements EventRegister {
    private Player player;
    private Entity damager;

    private EntityDamageByEntityEvent entityDamageByEntityEvent;

    private final PlayerItemDamageEvent event;

    public PreventMaceDamageHandler(PlayerItemDamageEvent event) {
        this.event = event;

        init();
        execute();
    }

    @Override
    public void init() {
        this.player = this.event.getPlayer();
        this.entityDamageByEntityEvent =
                this.player.getLastDamageCause() instanceof EntityDamageByEntityEvent event
                ? event
                : null;
    }

    @Override
    public void execute() {
        if (ObjectUtils.isEmpty(this.entityDamageByEntityEvent))
            return;

        this.entityDamageByEntityEvent = (EntityDamageByEntityEvent) this.player.getLastDamageCause();

        Entity damager = this.entityDamageByEntityEvent.getDamager();

        this.damager = damager instanceof Player player ? player : null;
        if (ObjectUtils.isEmpty(this.damager))
            return;

        ItemStack weapon = ((Player) this.damager).getInventory().getItemInMainHand();
        if (BooleanUtils.isFalse(isMace(weapon)))
            return;

        this.event.setCancelled(true);
    }

    private boolean isMace(ItemStack item) {
        return item != null && item.getType() == Material.MACE;
    }
}
