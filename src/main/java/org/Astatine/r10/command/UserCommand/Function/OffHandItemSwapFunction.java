package org.Astatine.r10.command.UserCommand.Function;

import org.Astatine.r10.command.CommandRegisterSection;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class OffHandItemSwapFunction extends CommandRegisterSection {

    public OffHandItemSwapFunction() {
        super(GlobalCommandHandler.SWAP_HAND_TO_OFFHAND);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        Player player = (Player) commandSender;

        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        ItemStack offHandItem = player.getInventory().getItemInOffHand();

        player.getInventory().setItemInMainHand(offHandItem);
        player.getInventory().setItemInOffHand(mainHandItem);

        return true;
    }
}
