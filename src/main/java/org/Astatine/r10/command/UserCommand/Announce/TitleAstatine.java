package org.Astatine.r10.command.UserCommand.Announce;

import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.command.CommandRegisterSection;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.title.Title;

public class TitleAstatine extends CommandRegisterSection {

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
                componentExchanger("최신버전 무정부 플라이 생야생", ColorType.DISCORD_COLOR),
                componentExchanger("Astn.kr", ColorType.ORANGE)
        );
        player.showTitle(title);
        return true;
    }
}