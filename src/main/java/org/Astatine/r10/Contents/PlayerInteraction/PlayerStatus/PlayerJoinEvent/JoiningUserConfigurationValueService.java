package org.Astatine.r10.Contents.PlayerInteraction.PlayerStatus.PlayerJoinEvent;

import java.util.Optional;

import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Data.User.UserData.User;
import org.Astatine.r10.Data.User.UserData.UserBuilder;
import org.Astatine.r10.Data.User.UserData.UserHandler;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusHandler;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Util.Function.StringComponentExchanger;
import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * 
 */
public class JoiningUserConfigurationValueService extends StringComponentExchanger implements EventRegister {
    private User joinUser;
    private Player joinPlayer;
    private UserHandler userController;
    private UserKillStatusHandler userKillStatus;
    private final PlayerJoinEvent joinEvent;

    public JoiningUserConfigurationValueService(PlayerJoinEvent event) {
        this.joinEvent = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.userController = new UserHandler();
        this.userKillStatus = new UserKillStatusHandler();
        this.joinPlayer = this.joinEvent.getPlayer();
        User user = this.userController.readUser(this.joinPlayer.getUniqueId());

        Optional.ofNullable(user).ifPresentOrElse(
                existUser -> {
                    this.joinUser = new UserBuilder(existUser)
                            .playTime(this.joinPlayer.getStatistic(Statistic.PLAY_ONE_MINUTE))
                            .level(this.joinPlayer.getLevel())
                            .buildAndUpdate();
                },

                () -> {
                    this.userController.createUser(this.joinPlayer);
                    this.userKillStatus.createUserKillStatus(this.joinPlayer);
                    this.joinUser = this.userController.readUser(this.joinPlayer.getUniqueId());
                    Bukkit.getLogger().info(this.joinPlayer.getName() + "님이 신규유저 등록됐습니다.");
                });
    }

    @Override
    public void execute() {
        String name = this.joinPlayer.getName();
        String ip = this.joinPlayer.getAddress().getHostName();

        updateUserInfo(name, ip, this.joinPlayer.hasPlayedBefore());
    }

    private void updateUserInfo(String name, String ip, boolean playedBefore) {
        boolean equalsLastName = this.joinUser.nameList().getLast().equals(name);
        boolean existsIP = this.joinUser.connectionIPList().contains(ip);

        if (BooleanUtils.isFalse(equalsLastName)) {
            updateNameList(name);
            sendMessageToPlayer(this.joinPlayer, "새로운 이름으로 접속하셨습니다.", "신규 이름을 등록합니다.", playedBefore);
        }

        if (BooleanUtils.isFalse(existsIP)) {
            updateIPList(ip);
            sendMessageToPlayer(this.joinPlayer, "새로운 IP로 접속하셨습니다.", "신규 IP를 등록합니다.", playedBefore);
        }
    }

    private void updateNameList(String name) {
        this.joinUser = new UserBuilder(this.joinUser)
                .nameList(name)
                .buildAndUpdate();
    }

    private void updateIPList(String ip) {
        this.joinUser = new UserBuilder(this.joinUser)
                .ipList(ip)
                .buildAndUpdate();
    }

    private void sendMessageToPlayer(Player player, String existingMessage, String newMessage, boolean playedBefore) {
        String message = playedBefore ? existingMessage : newMessage;
        playerSendMsgComponentExchanger(player, message, ColorType.YELLOW);
    }

}