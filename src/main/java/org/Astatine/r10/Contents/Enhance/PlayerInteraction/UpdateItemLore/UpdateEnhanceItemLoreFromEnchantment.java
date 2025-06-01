package org.Astatine.r10.Contents.Enhance.PlayerInteraction.UpdateItemLore;

import java.util.Map;
import java.util.Optional;

import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Contents.Enhance.Interface.EnhanceUtil;
import org.Astatine.r10.Util.Function.StringComponentExchanger;
import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class UpdateEnhanceItemLoreFromEnchantment extends StringComponentExchanger implements EventRegister {
    private Map<Enchantment, Integer> enchantment;
    private ItemStack enchantItem;
    private int sharpnessLevel = 0;
    private int enhanceLevel = 0;

    private final EnchantItemEvent event;

    public UpdateEnhanceItemLoreFromEnchantment(EnchantItemEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.enchantItem = this.event.getItem();
        this.enchantment = this.event.getEnchantsToAdd();
    }

    @Override
    public void execute() {
        Optional.ofNullable(this.enchantment.get(Enchantment.SHARPNESS)).ifPresent(
                level -> this.sharpnessLevel = level
        );

        if (this.sharpnessLevel < 1)
            return;

        if (BooleanUtils.isFalse(this.enchantItem.hasItemMeta()))
            return;

        if (BooleanUtils.isFalse(this.enchantItem.getItemMeta().hasCustomModelData()))
            return;

        this.enchantItem.addEnchantments(this.enchantment);
        this.enhanceLevel = this.enchantItem.getItemMeta().getCustomModelData();

        ItemMeta targetItemMeta = this.enchantItem.getItemMeta();
        targetItemMeta.setCustomModelData(0);
        this.enchantItem.setItemMeta(targetItemMeta);

        try {
            EnhanceUtil.increaseEnhanceItemLevel(this.enchantItem, enhanceLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
