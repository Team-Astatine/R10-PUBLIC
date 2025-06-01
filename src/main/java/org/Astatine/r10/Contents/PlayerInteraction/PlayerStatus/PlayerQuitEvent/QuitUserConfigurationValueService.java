package org.Astatine.r10.Contents.PlayerInteraction.PlayerStatus.PlayerQuitEvent;

import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Data.User.UserData.User;
import org.Astatine.r10.Data.User.UserData.UserBuilder;
import org.Astatine.r10.Data.User.UserData.UserHandler;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitUserConfigurationValueService implements EventRegister {

    private Player quitPlayer;
    private UserHandler userController;

    private final PlayerQuitEvent event;

    public QuitUserConfigurationValueService(PlayerQuitEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.quitPlayer = this.event.getPlayer();
        this.userController = new UserHandler();
    }

    @Override
    public void execute() {
        User user = this.userController.readUser(this.quitPlayer.getUniqueId());

        new UserBuilder(user)
                .playTime(this.quitPlayer.getStatistic(Statistic.PLAY_ONE_MINUTE))
                .buildAndUpdate();
    }
}
