package org.Astatine.r10.Contents.UserInterface.Enhance;

import org.Astatine.r10.Contents.UserInterface.Core.UIUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Util.Function.Emoji;
import org.Astatine.r10.Contents.Enhance.Enumeration.Armour.ArmourList;
import org.Astatine.r10.Contents.Enhance.Enumeration.Scroll.ProtectScrollList;
import org.Astatine.r10.Contents.Enhance.Enumeration.Scroll.ScrollList;
import org.Astatine.r10.Contents.Enhance.Enumeration.Weapon.LongRange;
import org.Astatine.r10.Contents.Enhance.Enumeration.Weapon.ShortRange;
import org.Astatine.r10.Contents.Enhance.Processor.EnhanceItem;
import org.Astatine.r10.Contents.Enhance.Processor.EnhanceItemBuilder;
import org.Astatine.r10.Contents.Enhance.Processor.EnhanceItemExecutor;
import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Contents.UserInterface.Core.Interface.UIHolder;
import org.Astatine.r10.Contents.UserInterface.Core.UIGenerator.InventoryUIGenerator;

import java.util.Arrays;
import java.util.HashSet;


public class EnhanceUIClickEvent extends UIUtils implements EventRegister {
    private UIHolder uiHolder;
    private Player clickPlayer;

    private ItemStack enhanceItem;
    private ItemStack scrollStuff;
    private ItemStack protectScroll;

    private HashSet<Material> allowedItem;
    private HashSet<Material> allowedScroll;

    private final InventoryClickEvent event;

    public EnhanceUIClickEvent(InventoryClickEvent event) {
        this.event = event;

        if (ObjectUtils.isEmpty(this.event.getClickedInventory()))
            return;

        init();
        execute();
    }

    @Override
    public void init() {
        this.uiHolder = this.event.getClickedInventory().getHolder() instanceof UIHolder holder ? holder : null;
        this.clickPlayer = this.event.getWhoClicked() instanceof Player player ? player : null;

        this.enhanceItem = this.event.getView().getItem(3);
        this.scrollStuff = this.event.getView().getItem(4);
        this.protectScroll = this.event.getView().getItem(5);

        this.allowedItem = new HashSet<>();
        this.allowedScroll = new HashSet<>();
    }

    /**
     * 인벤토리를 열고 닫음에 있어서 인벤토리 주인을 찾습니다.
     * Click Inventory 생성은 {@link InventoryUIGenerator}를 참고해주세요.
     * 인벤토리 생성, 등록이 완료되면 플레이어가 강화 이벤트를 발생시키는지 확인합니다.
     * 강화는 {@link EnhanceItemExecutor} 를 참조하세요.
     */
    @Override
    public void execute() {
        if (ObjectUtils.isEmpty(this.uiHolder))
            return;

        if (ObjectUtils.notEqual(this.clickPlayer, this.uiHolder.getOwner()))
            return;

//        Add Allowed Item
        if (ObjectUtils.allNotNull(this.enhanceItem, this.scrollStuff)) {
            Arrays.stream(ShortRange.values()).forEach(i -> this.allowedItem.add(i.getMaterial()));
            Arrays.stream(LongRange.values()).forEach(i -> this.allowedItem.add(i.getMaterial()));
            Arrays.stream(ArmourList.values()).forEach(i -> this.allowedItem.add(i.getMaterial()));

            Arrays.stream(ScrollList.values()).forEach(i -> this.allowedScroll.add(i.getMaterial()));
            Arrays.stream(ProtectScrollList.values()).forEach(i -> this.allowedScroll.add(i.getMaterial()));
        }

        switch (this.event.getSlot()) {
            case 0, 1, 2 -> {
                this.event.setCancelled(true);
            }

            case 6, 7, 8 -> {
                this.event.setCancelled(true);
                if (isAllowedEnhanceItem()) {
                    EnhanceItem enhanceItemObj = new EnhanceItemBuilder()
                            .enhancePlayer((Player) this.event.getWhoClicked())
                            .enhanceItem(this.enhanceItem)
                            .enhanceScroll(this.scrollStuff)
                            .protectScroll(this.protectScroll)
                            .generating();

                    new EnhanceItemExecutor(enhanceItemObj);
                }
            }
        }
    }

    private boolean isAllowedEnhanceItem() {
        String comment = "";

        if (ObjectUtils.isEmpty(this.enhanceItem) || this.enhanceItem.isEmpty()) 
            comment = "무기를 올려주세요.";

        else if (ObjectUtils.isEmpty(this.scrollStuff) || this.scrollStuff.isEmpty()) 
            comment = "강화 주문서가 부족합니다.";

        else if (BooleanUtils.isFalse(this.allowedItem.contains(this.enhanceItem.getType())))
            comment = "허용된 아이템을 넣어주세요.";

        else if (BooleanUtils.isFalse(this.allowedScroll.contains(this.scrollStuff.getType())))
            comment = "허용된 주문서를 넣어주세요";

        else if (ObjectUtils.allNotNull(this.protectScroll) && BooleanUtils.isFalse(this.allowedScroll.contains(this.protectScroll.getType())))
            comment = "허용된 주문서를 넣어주세요";

        if (BooleanUtils.isFalse(comment.isBlank()))
            playerSendMsgComponentExchanger(
                this.clickPlayer, 
                Emoji.WARNING.getStringTypeEmoji() + comment, 
                ColorType.RED
                );

        return comment.isBlank();
    }
}
