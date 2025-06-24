package org.Astatine.r10.command.UserCommand.Announce;

import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.command.CommandRegister;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.title.Title;

public class TitleAstatine extends CommandRegister {

    public TitleAstatine() {
        super(GlobalCommandHandler.ASTN);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        Player player = (Player) commandSender;
        Title title = Title.title(
                componentExchanger("최신버전 무정부 플라이 반야생", ColorType.PURPLE),
                componentExchanger("Shift + F 를 눌러 메뉴를 열 수 있습니다.", ColorType.ORANGE)
        );
        player.showTitle(title);
        return true;
    }
}