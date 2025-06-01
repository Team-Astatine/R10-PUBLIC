package org.Astatine.r10.Contents.UserInterface.TPA;

import org.Astatine.r10.Contents.UserInterface.Core.UIUtils;
import org.bukkit.Bukkit;
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
import java.util.Arrays;
import java.util.List;

@UIType(Type.TPA)
public class TpaUI extends UIUtils implements UIHolder {
    private Player chestOwner;
    private Inventory inventory;

    private List<Player> onlinePlayers;
    private int slotCount;

    public TpaUI(Player player) {
        this.chestOwner = player;
        setOnlinePlayer();
        calculatingSlotCount();
        UIExecutor();
    }

    private void setOnlinePlayer() {
        this.onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
//        Exclude the Chest Owner from the oneline player list
        this.onlinePlayers.removeIf(
                targetPlayer -> targetPlayer.getName().equals(getOwner().getName())
        );
    }

    @Override
    public Player getOwner() {
        return this.chestOwner;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    private void calculatingSlotCount() {
        int playerCount = this.onlinePlayers.size();

        int fullPages = playerCount / MINIUM_TAB_CNT;
        boolean hasRemainder = (playerCount % MINIUM_TAB_CNT) > 0;
        int line = fullPages + (hasRemainder ? 1 : 0);

        if (line == 0)
            line = 1;

        this.slotCount = line * MINIUM_TAB_CNT;
    }

    @Override
    public void UIExecutor() {
        this.inventory = new InventoryUIGenerator()
                .bindHolder(this)
                .inventoryGenerator(
                        this.slotCount,
                        componentExchanger("TPA 요청", ColorType.GREEN)
                )
                .setEnhanceUIItem(itemPanelList())
                .executeUI();
    }

    private ArrayList<SlotItemMapping> itemPanelList() {
        ArrayList<SlotItemMapping> result = new ArrayList<>(defaultPanelItems(this.slotCount));

        for (int i = 0; i < this.onlinePlayers.size(); i++) {
            Player player = this.onlinePlayers.get(i).getPlayer();
            result.add(new SlotItemMapping(
                    i,
                    createItem(
                            getHeadItemStack(player),
                            player.getName(), ColorType.COMMAND_COLOR,
                            false,
                            new ArrayList<>(Arrays.asList(
                                    componentExchanger("왼쪽 클릭 : TPA 요청", ColorType.YELLOW),
                                    componentExchanger("쉬프트 + 마우스 우클릭 : TPA HERE 요청", ColorType.WHITE_TO_RED5),
                                    componentExchanger("! 상대방을 나에게 오게 만듭니다.", ColorType.WHITE_TO_RED5),
                                    componentExchanger("! 상대방이 나에게 올 경우 위치가 노출될 수 있습니다.", ColorType.WHITE_TO_RED5)
                            ))
                    )
            ));
        }

        return result;
    }
}
