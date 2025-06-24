package org.Astatine.r10.command.UserCommand.Announce;

import org.Astatine.r10.Data.User.UserData.User;
import org.Astatine.r10.Data.User.UserData.UserBuilder;
import org.Astatine.r10.Data.User.UserData.UserHandler;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Util.Function.Emoji;
import org.Astatine.r10.command.CommandRegister;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ToggleAnnouncing extends CommandRegister {

    public ToggleAnnouncing() {
        super(GlobalCommandHandler.ANNOUNCING);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        User targetUser = new UserHandler().readUser(((Player) commandSender).getUniqueId());

        String comment = targetUser.announcingSkip() ? "비활성화" : "활성화";
        commandSender.sendMessage(
            emojiMessage(
                Emoji.CHECK, 
                String.format("공지가 %s됐어요!",comment),
                ColorType.YELLOW
            )
        );

        new UserBuilder(targetUser)
                .isAnnouncingSkip(!targetUser.announcingSkip())
                .buildAndUpdate();
        return true;
    }
}