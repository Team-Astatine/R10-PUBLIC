package org.Astatine.r10.Contents.UserInterface.GSit;

import org.Astatine.r10.Contents.UserInterface.Core.UIUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Contents.UserInterface.Core.Interface.UIHolder;

public class GSitUIClickEvent extends UIUtils implements EventRegister {
    private UIHolder uiHolder;
    private Player clickPlayer;

    private final InventoryClickEvent event;

    public GSitUIClickEvent(InventoryClickEvent event) {
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
    }

    @Override
    public void execute() {
        if (ObjectUtils.isEmpty(this.uiHolder))
            return;

        if (ObjectUtils.notEqual(this.clickPlayer, this.uiHolder.getOwner()))
            return;

        this.event.setCancelled(true);
        switch (this.event.getSlot()) {
            case 1 -> executeCommand("sit");
            case 3 -> executeCommand("lay");
            case 5 -> executeCommand("spin");
            case 7 -> executeCommand("crawl");
        }
    }

    private void executeCommand(String sit) {
        this.clickPlayer.performCommand(sit);
        this.event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);
    }
}
