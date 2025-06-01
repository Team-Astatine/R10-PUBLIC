package org.Astatine.r10.Contents.Enhance.PlayerInteraction.LongRange.Shot;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.Astatine.r10.Contents.Enhance.Interface.EnhanceUtil;
import org.Astatine.r10.Contents.EventRegister;

public class EnhanceBowShotEvent implements EventRegister {
    private Arrow arrow;
    private final ProjectileLaunchEvent event;

    public EnhanceBowShotEvent(ProjectileLaunchEvent event) {
        this.event = event;

        init();
        execute();
    }

    @Override
    public void init() {
    }

    @Override
    public void execute() {
        if (!(this.event.getEntity() instanceof Arrow arrow))
            return;

        if (arrow.isShotFromCrossbow())
            return;

        if (!(this.event.getEntity().getShooter() instanceof Player shooter))
            return;

        this.arrow = arrow;
        ItemStack mainHandBow = shooter.getInventory().getItemInMainHand();

        if (ObjectUtils.notEqual(mainHandBow.getType(), Material.BOW))
            mainHandBow = shooter.getInventory().getItemInOffHand();

        if (BooleanUtils.isFalse(mainHandBow.hasItemMeta()))
            return;

        if (BooleanUtils.isFalse(mainHandBow.getItemMeta().hasCustomModelData()))
            return;

        Vector vector = this.event.getEntity().getVelocity();
        switch (EnhanceUtil.getItemCustomModelData(mainHandBow)) {
            case 1, 2, 3 -> executeEnhanceState(1, 1, Sound.ENTITY_GHAST_DEATH, vector);
            case 4, 5, 6 -> executeEnhanceState(1, 3, Sound.ENTITY_ENDER_DRAGON_HURT, vector);
            case 7, 8, 9 -> executeEnhanceState(2, 6, Sound.ENTITY_ENDER_DRAGON_SHOOT, vector);
            case 10 -> executeEnhanceState(3, 10, Sound.BLOCK_CONDUIT_ACTIVATE, vector);
            default -> {
            }
        }
    }

    private void executeEnhanceState(int shootingSpeed, int pierceLevel, Sound sound, Vector vector) {
        Runnable tridentThrowingTask = () -> {
            this.arrow.setPierceLevel(pierceLevel);
            this.event.getEntity().setVelocity(vector.multiply(shootingSpeed));
            this.arrow.getWorld().playSound(arrow.getLocation(), sound, 5F, 5F);

            this.arrow.setCritical(false);
        };

        tridentThrowingTask.run();
    }
}
