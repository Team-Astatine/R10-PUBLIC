package org.Astatine.r10.Contents.UserInterface.Home;

import net.ess3.api.IUser;
import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Contents.UserInterface.Core.Interface.UIHolder;
import org.Astatine.r10.Contents.UserInterface.Core.UIUtils;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class HomeUIClickEvent extends UIUtils implements EventRegister {
    private UIHolder uiHolder;
    private Player clickPlayer;

    private final InventoryClickEvent event;

    public HomeUIClickEvent(InventoryClickEvent event) {
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
        IUser essPlayer = getEssentialPluginUserObject(this.clickPlayer);
        String homeName = componentExchanger(
                this.event.getInventory().getItem(this.event.getSlot()).displayName()
        );

        switch (event.getClick()) {
            case ClickType.LEFT -> performCommand(this.clickPlayer, "home", homeName);
            case ClickType.SHIFT_LEFT -> {
                performCommand(this.clickPlayer, "delhome", homeName);

//                홈 삭제 후 화면 새로고침
                if (essPlayer.getHomes().isEmpty())
                    this.clickPlayer.closeInventory(InventoryCloseEvent.Reason.PLUGIN);
                else
                    this.clickPlayer.performCommand(GlobalCommandHandler.HOME_TAB_OPEN.getCommand());
                }

            default -> {}
        }

    }
}
