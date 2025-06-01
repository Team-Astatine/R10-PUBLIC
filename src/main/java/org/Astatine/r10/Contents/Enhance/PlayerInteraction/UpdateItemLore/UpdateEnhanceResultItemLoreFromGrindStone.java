package org.Astatine.r10.Contents.Enhance.PlayerInteraction.UpdateItemLore;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.Astatine.r10.Contents.Enhance.Interface.EnhanceUtil;
import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Util.Function.StringComponentExchanger;

public class UpdateEnhanceResultItemLoreFromGrindStone extends StringComponentExchanger implements EventRegister {
    private ItemStack resultItem;

    private final InventoryClickEvent event;

    public UpdateEnhanceResultItemLoreFromGrindStone(InventoryClickEvent event) {
        this.event = event;

        if (valid())
            return;

        init();
        execute();
    }

    public boolean valid() {
        if (ObjectUtils.isEmpty(this.event.getClickedInventory()))
            return true;

        if (BooleanUtils.isFalse(this.event.getClickedInventory().getType().equals(InventoryType.GRINDSTONE)))
            return true;

        return BooleanUtils.isFalse(this.event.getSlotType().equals(InventoryType.SlotType.RESULT));
    }

    @Override
    public void init() {
        this.resultItem = this.event.getClickedInventory().getItem(2);
    }

    @Override
    public void execute() {
        if (ObjectUtils.isEmpty(this.resultItem)) //아이템에 아무것도 옵션이 없을때 null 뜸
            return;

        if (BooleanUtils.isFalse(this.resultItem.hasItemMeta()))
            return;

        if (BooleanUtils.isFalse(this.resultItem.getItemMeta().hasCustomModelData()))
            return;

        int enhanceLevel = this.resultItem.getItemMeta().getCustomModelData();
        ItemMeta targetItemMeta = this.resultItem.getItemMeta();
        targetItemMeta.setCustomModelData(0);
        this.resultItem.setItemMeta(targetItemMeta);

        try {
            EnhanceUtil.increaseEnhanceItemLevel(this.resultItem, enhanceLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
