package org.Astatine.r10.Contents.UserInterface.GSit;

import org.Astatine.r10.Contents.UserInterface.Core.UIUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Contents.UserInterface.Core.Interface.Type;
import org.Astatine.r10.Contents.UserInterface.Core.Interface.UIHolder;
import org.Astatine.r10.Contents.UserInterface.Core.Interface.UIType;
import org.Astatine.r10.Contents.UserInterface.Core.UIGenerator.InventoryUIGenerator;
import org.Astatine.r10.Contents.UserInterface.Core.UIGenerator.SlotItemMapping;

import java.util.ArrayList;

@UIType(Type.GSIT)
public class GSitUI extends UIUtils implements UIHolder {
    private Player chestOwner;
    private Inventory inventory;
    private int slotCount;

    public GSitUI(Player player) {
        this.chestOwner = player;
        this.slotCount = MINIUM_TAB_CNT;
        UIExecutor();
    }

    @Override
    public Player getOwner() {
        return this.chestOwner;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public void UIExecutor() {
        /**
         * 총 4개의 매뉴, 각 메뉴별로 아이템으로 표기예정
         * ⬜️🟧⬜️🟧⬜️🟧⬜️🟧⬜️
         * 0 1  2 3 4 5  6 7 8
         */

        this.inventory = new InventoryUIGenerator()
                .bindHolder(this)
                .inventoryGenerator(
                        this.slotCount,
                        componentExchanger("행동 상호작용", ColorType.PINK)
                )
                .setEnhanceUIItem(itemPanelList())
                .executeUI();
    }

    private ArrayList<SlotItemMapping> itemPanelList() {
        ArrayList<SlotItemMapping> result = new ArrayList<>(defaultPanelItems(this.slotCount));

        result.add(new SlotItemMapping(
                1,
                createItem(
                Material.BRICK_STAIRS,
                "앉기", ColorType.ORANGE,
                true)
        ));

        result.add(new SlotItemMapping(
                3,
                createItem(
                Material.RED_BED,
                "눕기",
                ColorType.ORANGE,
                true)
        ));

        result.add(new SlotItemMapping(
                5,
                createItem(
                Material.TRIDENT,
                "돌기",
                ColorType.ORANGE,
                true)
        ));

        result.add(new SlotItemMapping(
                7,
                createItem(
                Material.IRON_TRAPDOOR,
                "기어가기",
                ColorType.ORANGE,
                true)
        ));

        return result;
    }
}
