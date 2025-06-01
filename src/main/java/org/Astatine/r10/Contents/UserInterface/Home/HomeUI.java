package org.Astatine.r10.Contents.UserInterface.Home;

import net.ess3.api.IUser;
import org.Astatine.r10.Contents.UserInterface.Core.Interface.Type;
import org.Astatine.r10.Contents.UserInterface.Core.Interface.UIHolder;
import org.Astatine.r10.Contents.UserInterface.Core.Interface.UIType;
import org.Astatine.r10.Contents.UserInterface.Core.UIGenerator.InventoryUIGenerator;
import org.Astatine.r10.Contents.UserInterface.Core.UIGenerator.SlotItemMapping;
import org.Astatine.r10.Contents.UserInterface.Core.UIUtils;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UIType(Type.HOME)
public class HomeUI extends UIUtils implements UIHolder {
    private List<String> playerHomes;

    private Player chestOwner;
    private Inventory inventory;
    private int slotCount;

    public HomeUI(Player player) {
        this.chestOwner = player;
        init();
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

    private void init() {
        IUser essPlayer = getEssentialPluginUserObject(getOwner());
        this.playerHomes = essPlayer.getHomes();
        this.slotCount = getOwner().hasPermission("essentials.sethome.multiple.support")
                    ? MINIUM_TAB_CNT * 2 // 타겟 플레이어 권한이 support일 경우
                    : MINIUM_TAB_CNT;    // 타겟 플레이어 권한이 default일 경우
    }

    @Override
    public void UIExecutor() {
        this.inventory = new InventoryUIGenerator()
                .bindHolder(this)
                .inventoryGenerator(
                        this.slotCount,
                        componentExchanger("홈 정보", ColorType.YELLOW)
                )
                .setEnhanceUIItem(itemPanelList())
                .executeUI();
    }

    private ArrayList<SlotItemMapping> itemPanelList() {
        ArrayList<SlotItemMapping> result = new ArrayList<>(defaultPanelItems(this.slotCount));
        int homeIndex = 0;
        for (int i = 0; i < this.slotCount; i++) {
            if (homeIndex == this.playerHomes.size())
                break;

            // 첫 번째 줄(0~8)에서는 1,3,5,7을, 두 번째 줄(9~)에서는 10,12,14,16을 건너뛰도록 조건 분기
            if ((i < MINIUM_TAB_CNT && i % 2 == 1) || (i >= MINIUM_TAB_CNT && i % 2 == 0))
                continue;

            result.add(new SlotItemMapping(
                i,
                createItem(
                    Material.RED_BED,
                    this.playerHomes.get(homeIndex++), ColorType.RED,
                    true,
                    new ArrayList<>(Arrays.asList(
                            componentExchanger("왼쪽 클릭 : 해당 지점으로 이동", ColorType.GREEN),
                            componentExchanger("쉬프트 + 마우스 좌클릭 : 해당 지점 삭제", ColorType.WHITE_TO_RED5),
                            componentExchanger("! 해당 지점 삭제 시 복구되지 않습니다.", ColorType.WHITE_TO_RED5),
                            componentExchanger("! 신중히 삭제해주세요.", ColorType.WHITE_TO_RED5)
                    ))
                ))
            );

        }
        return result;
    }
}
