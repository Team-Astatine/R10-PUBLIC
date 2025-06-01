package org.Astatine.r10.Contents.Enhance.PlayerInteraction.LongRange.Shot;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.Astatine.r10.Contents.Enhance.Interface.EnhanceUtil;
import org.Astatine.r10.Contents.EventRegister;

public class EnhanceTridentShotEvent implements EventRegister {
    private Trident trident;
    private ItemStack shooterTrident;
    private final ProjectileLaunchEvent event;

    public EnhanceTridentShotEvent(ProjectileLaunchEvent event) {
        this.event = event;
        init();
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

        this.trident = trident;
        this.shooterTrident = shooter.getInventory().getItemInMainHand();

        if (ObjectUtils.notEqual(this.shooterTrident.getType(), Material.TRIDENT))
            this.shooterTrident = shooter.getInventory().getItemInOffHand();

        if (BooleanUtils.isFalse(this.shooterTrident.hasItemMeta()))
            return;

        if (BooleanUtils.isFalse(this.shooterTrident.getItemMeta().hasCustomModelData()))
            return;

        Vector vector = this.event.getEntity().getVelocity();
        switch (EnhanceUtil.getItemCustomModelData(this.shooterTrident)) {
            case 1, 2, 3 -> executeEnhanceState(1, 1, 1, Sound.ENTITY_GHAST_DEATH, vector);
            case 4, 5, 6 -> executeEnhanceState(1, 2, 3, Sound.ENTITY_ENDER_DRAGON_HURT, vector);
            case 7, 8, 9 -> executeEnhanceState(2, 3, 6, Sound.ENTITY_WITHER_SPAWN, vector);
            case 10 -> executeEnhanceState(3, 5, 10, Sound.BLOCK_CONDUIT_ACTIVATE, vector);
            default -> {
            }
        }
    }

    private void executeEnhanceState(int shootingSpeed, int loyaltyLevel, int pierceLevel, Sound sound, Vector vector) {
        Runnable tridentThrowingTask = () -> {
            this.trident.setGlowing(true);
            this.trident.setPierceLevel(pierceLevel);
            this.trident.setLoyaltyLevel(loyaltyLevel);
            this.event.getEntity().setVelocity(vector.multiply(shootingSpeed));
            this.trident.getWorld().playSound(this.trident.getLocation(), sound, 5F, 5F);
        };

        tridentThrowingTask.run();
    }
}
