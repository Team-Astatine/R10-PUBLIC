package org.Astatine.r10.Contents.Restricted.Function.TotemStack;

import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Util.Function.StringComponentExchanger;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class RestrictedStackingTotemClickHandler extends StringComponentExchanger implements EventRegister {

    private Player clicker;
    private ItemStack getCurrentCursorHoldItem;
    public final InventoryClickEvent event;

    public RestrictedStackingTotemClickHandler(InventoryClickEvent event) {
        this.event = event;

        init();
        execute();
    }

    @Override
    public void init() {
        this.clicker = (Player) this.event.getWhoClicked();
        this.getCurrentCursorHoldItem = this.event.getCurrentItem();
    }

    @Override
    public void execute() {
        if (this.clicker.getGameMode() == GameMode.CREATIVE)
            return;

        if (this.event.isShiftClick())
            return;

        if (ObjectUtils.isEmpty(this.getCurrentCursorHoldItem))
            return;

        if (this.getCurrentCursorHoldItem.getType().equals(Material.AIR))
            return;

        if (ObjectUtils.notEqual(this.getCurrentCursorHoldItem.getType(), Material.TOTEM_OF_UNDYING))
            return;

        if (this.getCurrentCursorHoldItem.getAmount() < 2)
            return;

        playerSendMsgComponentExchanger(
                clicker,
                "겹쳐진 토템은 [스왑키, Shift + 클릭] 으로만 옮길 수 있습니다.",
                ColorType.RED
        );
        this.event.setCancelled(true);
    }
}
