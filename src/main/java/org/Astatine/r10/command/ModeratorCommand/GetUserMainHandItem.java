package org.Astatine.r10.command.ModeratorCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.command.CommandRegisterSection;
import org.Astatine.r10.command.GlobalCommandHandler;


public class GetUserMainHandItem extends CommandRegisterSection {
    public GetUserMainHandItem() {
        super(GlobalCommandHandler.LOOK_USER_MAIN_HAND_ITEM);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        ItemStack mainHandItem = ((Player) commandSender).getInventory().getItemInMainHand();
        System.out.println("mainHandItem > " + mainHandItem.toString());
        playerSendMsgComponentExchanger(commandSender, mainHandItem.toString(), ColorType.YELLOW);
        return true;
    }
}
