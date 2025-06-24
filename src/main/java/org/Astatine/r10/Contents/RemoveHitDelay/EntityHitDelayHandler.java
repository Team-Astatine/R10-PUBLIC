package org.Astatine.r10.Contents.RemoveHitDelay;

import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Contents.Enhance.Enumeration.Weapon.ShortRange;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class EntityHitDelayHandler implements EventRegister {
    private Entity damagerEntity;
    private LivingEntity targetEntity;
    private final EntityDamageByEntityEvent event;

    public EntityHitDelayHandler(EntityDamageByEntityEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.damagerEntity = this.event.getDamager();
        this.targetEntity = this.event.getEntity() instanceof LivingEntity entity ? entity : null;
    }

    @Override
    public void execute() {
        if (ObjectUtils.isEmpty(this.targetEntity))
            return;
            
        if (this.damagerEntity.getType() == EntityType.ARROW || this.damagerEntity.getType() == EntityType.SPECTRAL_ARROW) {
            this.targetEntity.setMaximumNoDamageTicks(1);
            return;
        }

        if (ObjectUtils.notEqual(this.damagerEntity.getType(), EntityType.PLAYER))
            return;

        Player damager = (Player) this.damagerEntity;

        int hurtTick = 10; //default 20

        boolean stuffCheck = isDualWeaponChecker(
                damager.getInventory().getItemInMainHand(),
                damager.getInventory().getItemInOffHand()
        );

        if (stuffCheck) //Two Hand Sword
            hurtTick = 1;

//        Bukkit.getScheduler().runTaskLater(R10.getPlugin(R10.class),
//                () -> targetEntity.setVelocity(targetEntity.getVelocity().multiply(hitDelay)), 1);

//        final int tick = hurtTick;
//        Bukkit.getScheduler().runTaskLater(
//                R10.getPlugin(R10.class),
//                () -> this.targetEntity.setMaximumNoDamageTicks(tick),
//                0
//        );

        this.targetEntity.setMaximumNoDamageTicks(hurtTick);
    }

    private Boolean isDualWeaponChecker(ItemStack mainStuff, ItemStack offStuff) {
        ShortRange mainHand = ShortRange.findByItemStack(mainStuff);
        ShortRange offHand = ShortRange.findByItemStack(offStuff);

        if (mainHand.getMaterial().equals(Material.AIR))
            return false;

        if (offHand.getMaterial().equals(Material.AIR))
            return false;

        return true;
    }
}