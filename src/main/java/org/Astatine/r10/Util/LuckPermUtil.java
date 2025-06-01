package org.Astatine.r10.Util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;

public class LuckPermUtil {

    private LuckPerms getLuckPerms() {
        return (LuckPerms)Bukkit.getServer()
                .getPluginManager()
                .getPlugin("LuckPerm");
    }

    public User getUser(Player player) {
        return getLuckPerms()
                .getPlayerAdapter(Player.class)
                .getUser(player);
    }
}
