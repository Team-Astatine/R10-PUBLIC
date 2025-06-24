package org.Astatine.r10.Contents.UserInterface.Menu;

import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Contents.UserInterface.Core.UIUtils;
import org.Astatine.r10.Contents.UserInterface.Core.Interface.UIHolder;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class MainMenuUIClickEvent extends UIUtils implements EventRegister {
    private UIHolder uiHolder;
    private Player clickPlayer;

    private final InventoryClickEvent event;

    public MainMenuUIClickEvent(InventoryClickEvent event) {
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
            case /*0,1,9,*/10 -> this.clickPlayer.performCommand(GlobalCommandHandler.TPA_TAB_OPEN.getCommand());

            case /*3,4,5,12,*/13/*,14*/ -> this.clickPlayer.performCommand("shop");

            case /*7,8,*/16/*,17*/ -> this.clickPlayer.performCommand(GlobalCommandHandler.ENHANCE_TAB_OPEN.getCommand());

            case /*18,19,27,*/28 -> this.clickPlayer.performCommand(GlobalCommandHandler.HOME_TAB_OPEN.getCommand());

//            case /*21,22,23,30,*/31/*,32*/ -> this.event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);

            case /*25,26,*/34/*,35*/ -> this.clickPlayer.performCommand(GlobalCommandHandler.GSIT_TAB_OPEN.getCommand());

            case 47, 48 -> {
                this.event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);
                this.clickPlayer.performCommand(GlobalCommandHandler.COMMUNITY.getCommand());
            }

            case 50 -> {
                this.clickPlayer.performCommand(GlobalCommandHandler.MAIN_MENU_TAB_OPEN.getCommand());
                this.clickPlayer.performCommand(GlobalCommandHandler.ANNOUNCING.getCommand());
            }

            case 51 -> {
                this.clickPlayer.performCommand(GlobalCommandHandler.MAIN_MENU_TAB_OPEN.getCommand());
                this.clickPlayer.performCommand(GlobalCommandHandler.FLIGHT.getCommand());
            }
        }
    }
}
