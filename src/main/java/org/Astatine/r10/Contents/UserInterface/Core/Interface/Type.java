package org.Astatine.r10.Contents.UserInterface.Core.Interface;

import java.util.function.Consumer;

import org.Astatine.r10.Contents.UserInterface.Enhance.EnhanceUIClickEvent;
import org.Astatine.r10.Contents.UserInterface.Enhance.EnhanceUICloseEvent;
import org.Astatine.r10.Contents.UserInterface.GSit.GSitUIClickEvent;
import org.Astatine.r10.Contents.UserInterface.Home.HomeUIClickEvent;
import org.Astatine.r10.Contents.UserInterface.Menu.MainMenuUIClickEvent;
import org.Astatine.r10.Contents.UserInterface.TPA.TpaUIClickEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public enum Type {
    MAIN_MENU(MainMenuUIClickEvent::new, null),
    ENHANCE(EnhanceUIClickEvent::new, EnhanceUICloseEvent::new),
    GSIT(GSitUIClickEvent::new, null),
    TPA(TpaUIClickEvent::new, null),
    HOME(HomeUIClickEvent::new, null);

    private Consumer<InventoryClickEvent> openConsumer;
    private Consumer<InventoryCloseEvent> closeConsumer;

    Type(Consumer<InventoryClickEvent> eventConsumer, Consumer<InventoryCloseEvent> closeConsumer) {
        this.openConsumer = eventConsumer;
        this.closeConsumer = closeConsumer;
    }

    public Consumer<InventoryClickEvent> getOpenConsumer() {
        return openConsumer;
    }

    public Consumer<InventoryCloseEvent> getCloseEventConsumer() {
        return closeConsumer;
    }
}
