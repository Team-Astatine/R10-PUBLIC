package org.Astatine.r10.Contents.UserInterface.Menu;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import org.Astatine.r10.Contents.UserInterface.Core.UIUtils;
import org.Astatine.r10.Contents.UserInterface.Core.Interface.Type;
import org.Astatine.r10.Contents.UserInterface.Core.Interface.UIHolder;
import org.Astatine.r10.Contents.UserInterface.Core.Interface.UIType;
import org.Astatine.r10.Contents.UserInterface.Core.UIGenerator.InventoryUIGenerator;
import org.Astatine.r10.Contents.UserInterface.Core.UIGenerator.SlotItemMapping;
import org.Astatine.r10.Data.User.UserData.User;
import org.Astatine.r10.Data.User.UserData.UserHandler;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;

@UIType(Type.MAIN_MENU)
public class MainMenuUI extends UIUtils implements UIHolder {
    private Player chestOwner;
    private User chestOwnerUserObj;

    private Inventory inventory;
    private int slotCount;

    public MainMenuUI(Player player) {
        this.chestOwner = player;
        this.chestOwnerUserObj = new UserHandler().readUser(this.chestOwner.getUniqueId());
        this.slotCount = MINIUM_TAB_CNT * 6;

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
        this.inventory = new InventoryUIGenerator()
                .bindHolder(this)
                .inventoryGenerator(
                        this.slotCount,
                        componentExchanger("서버 메뉴", ColorType.NOTION_COLOR)
                )
                .setEnhanceUIItem(itemPanelList())
                .executeUI();
    }

    private ArrayList<SlotItemMapping> itemPanelList() {
        ArrayList<SlotItemMapping> result = new ArrayList<>(defaultPanelItems(this.slotCount));

//        result.add(new SlotItemMapping(0, item));
//        result.add(new SlotItemMapping(1, item));
//        result.add(new SlotItemMapping(9, item));
        result.add(new SlotItemMapping(
            10,
            createItem(
                    Material.ENDER_PEARL,
                    "TPA 요청", ColorType.GREEN,
                    true)
            ));

//        result.add(new SlotItemMapping(3, item));
//        result.add(new SlotItemMapping(4, item));
//        result.add(new SlotItemMapping(5, item));
//        result.add(new SlotItemMapping(12, item));
        result.add(new SlotItemMapping(
                13,
                createItem(
                        Material.CHEST_MINECART,
                        "상점", ColorType.GRAY,
                        true)
        ));
//        result.add(new SlotItemMapping(14, item));

//        result.add(new SlotItemMapping(7, item));
//        result.add(new SlotItemMapping(8, item));

        result.add(new SlotItemMapping(
                16,
                createItem(
                        Material.ANVIL,
                        "강화", ColorType.RED,
                        true)
        ));
//        result.add(new SlotItemMapping(17, item));


//        result.add(new SlotItemMapping(18, item));
//        result.add(new SlotItemMapping(19, item));
//        result.add(new SlotItemMapping(27, item));
        result.add(new SlotItemMapping(
                28,
                createItem(
                        Material.ACACIA_DOOR,
                        "홈 세팅", ColorType.YELLOW,
                        true)
        ));

//        result.add(new SlotItemMapping(21, item));
//        result.add(new SlotItemMapping(22, item));
//        result.add(new SlotItemMapping(23, item));
//        result.add(new SlotItemMapping(30, item));
        result.add(new SlotItemMapping(
                31,
                createItem(
                        Material.BARRIER,
                        "준비중", ColorType.GRAY,
                        true)
        ));
//        result.add(new SlotItemMapping(32, item));

//        result.add(new SlotItemMapping(25, item));
//        result.add(new SlotItemMapping(26, item));
        result.add(new SlotItemMapping(
                34,
                createItem(
                        Material.COMMAND_BLOCK,
                        "행동 상호작용", ColorType.PINK,
                        true)
        ));
//        result.add(new SlotItemMapping(35, item));

        result.add(new SlotItemMapping(
                45,
                createItem(
                        Material.EMERALD,
                        getPlayerBalance(),
                        true)
        ));

//        result.add(new SlotItemMapping(46, item));
        result.add(new SlotItemMapping(
                47,
                createItem(
                        Material.PURPLE_STAINED_GLASS_PANE,
                        "커뮤니티 링크", ColorType.VOTE_COLOR,
                        false)
        ));

//        result.add(new SlotItemMapping(48, item));
        result.add(new SlotItemMapping(
                48,
                createItem(
                        Material.PURPLE_STAINED_GLASS_PANE,
                        "커뮤니티 링크", ColorType.VOTE_COLOR,
                        false)
        ));
//        result.add(new SlotItemMapping(50, item));

        result.add(new SlotItemMapping(
                50,
                createItem(
                        Material.RED_STAINED_GLASS_PANE,
                        "공지 활성화", ColorType.WHITE_TO_RED4,
                        chestOwnerUserObj.announcingSkip())
        ));
        result.add(new SlotItemMapping(
                51,
                createItem(
                        Material.YELLOW_STAINED_GLASS_PANE,
                        "플라이 활성화", ColorType.WHITE_TO_RED4,
                        chestOwnerUserObj.flight())
        ));
//        result.add(new SlotItemMapping(52, item));

        result.add(new SlotItemMapping(
                53,
                createItem(
                        getHeadItemStack(getOwner()),
                        getPlayerStatusInformation(),
                        true)
        ));

        return result;
    }

    private @NotNull Component getPlayerBalance() {
        BigDecimal rawMoney = getEssentialPluginUserMoney(getOwner());

        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
        formatSymbols.setGroupingSeparator('.');

        return componentExchanger(String.format("%s 달러", new DecimalFormat("#,###", formatSymbols)
                .format(rawMoney)), ColorType.COMMAND_COLOR);
    }

    private @NotNull Component getPlayerStatusInformation() {
        int ticks = getOwner().getStatistic(Statistic.PLAY_ONE_MINUTE);
        long totalSeconds = ticks / 20L;
        long hrs = totalSeconds / 3600;
        long mins = (totalSeconds % 3600) / 60;
        long secs = totalSeconds % 60;
        String formatted = String.format("%02d시간 %02d분 %02d초", hrs, mins, secs);

        return Component.text()
                .append(componentExchanger(formatted, ColorType.PINK))
                .append(componentExchanger(" 째 플레이 중!", ColorType.SKY_BLUE))
                .build();
    }
}