package org.Astatine.r10.Contents.UserInterface.Core;

import net.kyori.adventure.text.Component;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Util.EssentialsUtil;
import org.Astatine.r10.Contents.UserInterface.Core.UIGenerator.CreatePanelItem;
import org.Astatine.r10.Contents.UserInterface.Core.UIGenerator.SlotItemMapping;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public abstract class UIUtils extends EssentialsUtil {
    private final List<Component> EMPTY_LORE = Collections.emptyList();
    public final int MINIUM_TAB_CNT = 9;

    public void performCommand(Player player, String command, String targetName) {
        player.performCommand(String.format("%s %s", command, targetName));
    }

    public ItemStack getHeadItemStack(Player player) {
        ItemStack headItemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta data = (SkullMeta) headItemStack.getItemMeta();
        data.setOwningPlayer(player);
        headItemStack.setItemMeta(data);
        return headItemStack;
    }

    public List<SlotItemMapping> defaultPanelItems(int size) {
        return IntStream.range(0, size)
            .mapToObj(i -> new SlotItemMapping(
                i,
                createItem(Material.WHITE_STAINED_GLASS_PANE, "", ColorType.WHITE, false)
            ))
            .collect(Collectors.toList());
    }

    public ItemStack createItem(Material material, String comment, ColorType color, boolean isItemGlowing) {
        return createItem(material, comment, color, isItemGlowing, EMPTY_LORE);
    }

    public ItemStack createItem(Material material, Component component, boolean isItemGlowing) {
        return createItem(material, component, isItemGlowing, EMPTY_LORE);
    }

    public ItemStack createItem(ItemStack itemStack, Component component, boolean isItemGlowing) {
        return createItem(itemStack, component, isItemGlowing, EMPTY_LORE);
    }

    public ItemStack createItem(Material material, String comment, ColorType color, boolean isItemGlowing, List<Component> lore) {
        return buildItem(
            new CreatePanelItem()
                .setPanelItem(material)
                .setDisplayName(comment, color),
                isItemGlowing,
            lore
        );
    }

    public ItemStack createItem(ItemStack targetItem, String comment, ColorType color, boolean isItemGlowing, List<Component> lore) {
        return buildItem(
            new CreatePanelItem()
                .setPanelItem(targetItem)
                .setDisplayName(comment, color),
                isItemGlowing,
            lore
        );
    }

    public ItemStack createItem(Material material, Component component, boolean isItemGlowing, List<Component> lore) {
        return buildItem(
                new CreatePanelItem()
                        .setPanelItem(material)
                        .setDisplayName(component),
                isItemGlowing,
                lore
        );
    }

    public ItemStack createItem(ItemStack itemStack, Component component, boolean isItemGlowing, List<Component> lore) {
        return buildItem(
                new CreatePanelItem()
                        .setPanelItem(itemStack)
                        .setDisplayName(component),
                isItemGlowing,
                lore
        );
    }

    private ItemStack buildItem(CreatePanelItem builder, boolean isItemGlowing, List<Component> lore) {
        return builder
            .setLore(new ArrayList<>(lore))
            .isEnchantGlowing(isItemGlowing)
            .createItem();
    }
}
