package org.Astatine.r10.command.ModeratorCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.Astatine.r10.R10;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.command.CommandRegister;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;

public class RegistBindItem extends CommandRegister {

    public RegistBindItem() {
        super(GlobalCommandHandler.BINDING_ITEM);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {
        
        Player player = (Player) sender;
        Player targetPlayer = Bukkit.getPlayer(args[0]);

        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = mainHandItem.getItemMeta();
        List<Component> mainItemLore = itemMeta.lore() == null
                            ? mainItemLore = new ArrayList<>()
                            : itemMeta.lore();
        

        NamespacedKey keepkey = new NamespacedKey(R10.getPlugin(R10.class), "keepOnDeath");
        itemMeta.getPersistentDataContainer().set(keepkey, PersistentDataType.BOOLEAN, true);

        mainItemLore.addAll(Arrays.asList(
            Component.text(String.format("%s님의 영구 소유물", args[0]))
            .color(ColorType.WHITE_TO_RED8.getTextColor())
            .decorate(TextDecoration.BOLD)
        ));

        itemMeta.lore(mainItemLore);
        mainHandItem.setItemMeta(itemMeta);
        
        player.sendMessage(componentExchanger(
            String.format(
                "%s님에게 아이템이 Binding 되었습니다.", targetPlayer.getName()), 
                ColorType.RED)
            );

        return true;
    }
}