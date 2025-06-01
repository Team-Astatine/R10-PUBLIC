package org.Astatine.r10.Contents.Enhance.Processor;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.Astatine.r10.Contents.Enhance.Interface.EnhanceUtil;

/**
 * {@link EnhanceItem} 강화용 아이템을 만듭니다.
 * 강화 성공 및 실패 확률은 {@link EnhanceItemExecutor} 를 참고해주세요.
 */
public class EnhanceItemBuilder {

    private Player enhancePlayer;
    private ItemStack enhanceItem;
    private ItemStack enhanceScroll;
    private ItemStack protectScroll;

    public EnhanceItemBuilder() {
    }

    public EnhanceItemBuilder enhancePlayer(Player player) {
        this.enhancePlayer = player;
        return this;
    }

    public EnhanceItemBuilder enhanceItem(ItemStack enhanceItem) {
        this.enhanceItem = EnhanceUtil.initItemCustomModelData(enhanceItem); //CheckUp hasCustomModelData
        return this;
    }

    public EnhanceItemBuilder enhanceScroll(ItemStack enhanceScroll) {
        this.enhanceScroll = enhanceScroll;
        return this;
    }

    public EnhanceItemBuilder protectScroll(ItemStack protectScroll) {
        this.protectScroll = protectScroll;
        return this;
    }

    public EnhanceItem generating() {
        return new EnhanceItem(
                this.enhancePlayer,
                this.enhanceItem,
                this.enhanceScroll,
                this.protectScroll
        );
    }
}
