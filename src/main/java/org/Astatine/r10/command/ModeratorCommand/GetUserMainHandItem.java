package org.Astatine.r10.command.ModeratorCommand;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;

import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.command.CommandRegister;
import org.Astatine.r10.command.GlobalCommandHandler;


public class GetUserMainHandItem extends CommandRegister {
    public GetUserMainHandItem() {
        super(GlobalCommandHandler.LOOK_USER_MAIN_HAND_ITEM);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        ItemStack mainHandItem = ((Player) commandSender).getInventory().getItemInMainHand();
        Bukkit.getLogger().info("Item -> " + mainHandItem.toString());
        commandSender.sendMessage(
            Component.text()
                        .append(Component.text(mainHandItem.toString())
                                                .color(ColorType.YELLOW.getTextColor())    
                        )
                        .build()
                    );
        return true;
    }
}
