package org.Astatine.r10.command.UserCommand.Function;

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

public class ToggleFly extends CommandRegister {

    public ToggleFly() {
        super(GlobalCommandHandler.FLIGHT);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        Player targetPlayer = (Player) sender;

        User user = new UserHandler().readUser(targetPlayer.getUniqueId());
        new UserBuilder(user)
            .isFlight(!user.flight())
            .buildAndUpdate();
        
        targetPlayer.setAllowFlight(!targetPlayer.getAllowFlight());

        String comment = targetPlayer.getAllowFlight() ? "활성화" : "비활성화";
        targetPlayer.sendMessage(
        emojiMessage(
                Emoji.CHECK, 
                String.format("플라이가 %s됐어요!",comment),
                ColorType.YELLOW
            )
        );
        return true;
    }
}