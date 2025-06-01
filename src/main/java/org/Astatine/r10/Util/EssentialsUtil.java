package org.Astatine.r10.Util;

import net.ess3.api.IEssentials;
import net.ess3.api.IUser;

import org.Astatine.r10.Util.Function.StringComponentExchanger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

public class EssentialsUtil extends StringComponentExchanger {
    private IEssentials getEssentialsAPI() {
        return (IEssentials) Bukkit.getServer()
                .getPluginManager()
                .getPlugin("Essentials");
    }

    public IUser getEssentialPluginUserObject(Player player) {
        return getEssentialsAPI().getUser(player.getUniqueId());
    }

    public BigDecimal getEssentialPluginUserMoney(Player player) {
        return getEssentialPluginUserObject(player).getMoney();
    }
}
