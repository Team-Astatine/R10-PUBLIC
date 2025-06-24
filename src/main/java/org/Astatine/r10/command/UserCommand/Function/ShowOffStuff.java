package org.Astatine.r10.command.UserCommand.Function;

import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Util.Function.Emoji;
import org.Astatine.r10.command.CommandRegister;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextDecoration;

public class ShowOffStuff extends CommandRegister {

    public ShowOffStuff() {
        super(GlobalCommandHandler.SHOW_OFF_STUFF);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();

        // 2. 손에 아이템이 없거나 AIR인 경우
        if (item.isEmpty() || item.getType() == Material.AIR) {
            player.sendMessage(
                waringMessage("손에 아이템을 들어야 자랑할 수 있어요..!")
            );
            return true;
        }

        ItemStack single = item.asOne();
        HoverEvent<HoverEvent.ShowItem> hover = single.asHoverEvent(show -> show);

        Component nameComp = Bukkit.getItemFactory().displayName(single);

        Bukkit.broadcast(
        Component.text()
                        .append(Emoji.LOUDER.getComponentTypeEmoji()
                                .color(ColorType.WHITE.getTextColor())
                                )
                        .append(player.displayName())
                        .append(Component.text("님의 자랑 : "))
                        .append(nameComp.hoverEvent(hover))
                        .color(ColorType.WHITE.getTextColor())
                        .decorate(TextDecoration.BOLD)
                        .build()
            );

        return true;
    }
}
