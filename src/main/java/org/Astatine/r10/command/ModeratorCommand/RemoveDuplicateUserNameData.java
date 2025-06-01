package org.Astatine.r10.command.ModeratorCommand;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.Astatine.r10.Data.DataIO.User.DataFile;
import org.Astatine.r10.Data.DataIO.User.RObjectIOHandler;
import org.Astatine.r10.Data.User.UserData.User;
import org.Astatine.r10.Data.User.UserData.UserHandler;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.command.CommandRegisterSection;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RemoveDuplicateUserNameData extends CommandRegisterSection {
    private User senderUser;
    private Player senderPlayer;
    private boolean consoleSend = false;

    public RemoveDuplicateUserNameData() {
        super(GlobalCommandHandler.REMOVE_DUPLICATE_USER_DATA);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        try {
            this.senderUser = new UserHandler().readUser(sender.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional.ofNullable(this.senderUser).ifPresentOrElse(
                existUser -> this.senderPlayer = Bukkit.getPlayer(existUser.uuid()),
                () -> this.consoleSend = true
        );

        if (ObjectUtils.allNotNull(this.senderUser) && !this.senderPlayer.isOp() || !this.consoleSend) {
            playerSendMsgComponentExchanger(this.senderPlayer, "해당 명령어는 플레이어가 사용할 수 없습니다.", ColorType.RED);
            return false;
        }

        ArrayList<User> newUsers = new ArrayList<>();
        UserHandler userController = new UserHandler();

        ArrayList<User> allUserTable = userController.getAllUserTable();
        for (User user : allUserTable) {
            Set<String> userName = new HashSet<>(user.nameList());
            ArrayList<String> nonDupeUserName = new ArrayList<>(userName);

            newUsers.add(new User(
                    user.uuid(),
                    nonDupeUserName,
                    user.connectionIPList(),
                    user.playTime(),
                    user.joinCount(),
                    user.level(),
                    user.godMode(),
                    user.announcingSkip()
            ));
        }

        userController.updateAllUserData(newUsers);
        playerSendMsgComponentExchanger(this.senderPlayer, "성공적으로 중복이 제거되었습니다.", ColorType.YELLOW);

        new RObjectIOHandler().exportData(
                DataFile.USER_DATA, new UserHandler().getAllUserTable(), getClass().getName());
        playerSendMsgComponentExchanger(this.senderPlayer, "성공적으로 데이터가 저장되었습니다.", ColorType.YELLOW);
        return true;
    }
}
