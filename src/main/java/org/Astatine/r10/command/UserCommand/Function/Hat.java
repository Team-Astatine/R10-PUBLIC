package org.Astatine.r10.command.UserCommand.Function;

import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Util.Function.Emoji;
import org.Astatine.r10.command.CommandRegister;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

public class Hat extends CommandRegister {
    private Player player;
    private PlayerInventory playerInventory;

    public Hat() {
        super(GlobalCommandHandler.SWAP_HAT_TO_HAND);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        this.player = (Player) sender;
        this.playerInventory = this.player.getInventory();

        ItemStack tmpItemInHand = this.playerInventory.getItemInMainHand();
        if (tmpItemInHand.isEmpty()) {
            player.sendMessage(
                waringMessage("손에 아이템을 들어야 머리에 올릴 수 있어요!")
            );
            return false;
        }

        ItemStack armourHead = this.playerInventory.getHelmet();
        this.playerInventory.setHelmet(tmpItemInHand);
        this.playerInventory.setItemInMainHand(armourHead);
        this.player.sendMessage(
            emojiMessage(
                Emoji.EXPLODING_PARTY, 
                "머리에 썼어요!", 
                ColorType.YELLOW
            )
        );

        return true;
    }
}