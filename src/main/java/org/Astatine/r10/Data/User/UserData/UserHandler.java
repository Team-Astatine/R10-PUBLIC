package org.Astatine.r10.Data.User.UserData;

import java.util.ArrayList;
import java.util.UUID;

import org.Astatine.r10.Exception.NullReadUserException;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class UserHandler {
    private final UserAccessManager userDataBase = UserAccessManager.getInstance();

    public boolean createUser(@NotNull Player player) {
        return createUser(
                new UserBuilder(player)
                        .buildAndUpdate()
        );
    }

    public boolean createUser(@NotNull User user) {
        return this.userDataBase.insert(user);
    }

    public User readUser(UUID uuid) {
        return this.userDataBase.select(uuid);
    }

    public User readUser(String userName) throws NullReadUserException {
        Player player = Bukkit.getPlayer(userName);
        if (ObjectUtils.isEmpty(player))
            throw new NullReadUserException("UserController->readUser() Can't Exchanging Player");

        return readUser(player.getUniqueId());
    }

    public void updateUser(@NotNull User user) {
        this.userDataBase.update(user);
    }

    public void updateAllUserData(ArrayList<User> newUserData) {
        this.userDataBase.clear();

        if (ObjectUtils.isEmpty(newUserData))
            return;

        newUserData.forEach(this::createUser);
    }

    public ArrayList<User> getAllUserTable() {
        Bukkit.getOnlinePlayers().forEach(player -> readUser(player.getUniqueId()));
        return new ArrayList<>(this.userDataBase.getAllUserTable().values());
    }

}
