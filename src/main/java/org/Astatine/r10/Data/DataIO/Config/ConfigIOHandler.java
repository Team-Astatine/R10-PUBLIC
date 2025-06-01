package org.Astatine.r10.Data.DataIO.Config;

import org.Astatine.r10.Data.DataIO.User.DataFile;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Util.Function.StringComponentExchanger;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigIOHandler extends StringComponentExchanger {
    private static class ConfigIOHandlerHolder {
        private static final ConfigIOHandler INSTANCE = new ConfigIOHandler();
    }

    public static ConfigIOHandler getConfigIOHandler() {
        return ConfigIOHandlerHolder.INSTANCE;
    }

    private File file;
    private YamlConfiguration config;

    public void fileLoader() {
        this.file = DataFile.CONFIG.getFileInstance();
        this.config = YamlConfiguration.loadConfiguration(DataFile.CONFIG.getFileInstance());
    }

    public String getString(ConfigMenu msgPath) {
        return this.config.getString(msgPath.getConfigMessage());
    }

    public void allConfigLoad() {
        setWorldSettingMotd();
        getHelp();
        getCommunityLink();
        getMinelistLink();
        getDiscordInviteLink();
        getNotionLink();
        getMinelistLinkComment();
        getDiscordInviteComment();
        getNotionLinkComment();
        getTip();
        getHeartTip();
        getExtraHeartTip();
        getSteelLifeTip();
        getRaidTip();
        getWeaponTip();
        getExplosiveTip();
        getCommand();
        getCommandFly();
        getCommandHat();
        getCommandSwap();
        getCommandTotem();
        getCommandEnhance();
        getCommandAnnouncing();
        getCommandTpa();
        getCommandShop();
        getCommandBalance();
        getCommandParty();
        getCommandSkill();
        getCommandPlayTime();
    }

    public void setWorldSettingMotd() {
        String motd = getString(ConfigMenu.WORLD_SETTING_MOTD);
        Bukkit.motd(componentExchanger(motd, ColorType.VOTE_COLOR));
    }

    public String getHelp() {
        return getString(ConfigMenu.MESSAGE_COMMAND_HELP);
    }

    public String getWorldMotdConfig() {
        return getString(ConfigMenu.WORLD_SETTING_MOTD);
    }

    public String getCommunityLink() {
        return getString(ConfigMenu.MESSAGE_COMMAND_COMMUNITY);
    }

    public String getMinelistLink() {
        return getString(ConfigMenu.LINK_SETTING_MINELIST_VOTE_SITE_LINK);
    }

    public String getDiscordInviteLink() {
        return getString(ConfigMenu.LINK_SETTING_DISCORD_INVITE_LINK);
    }

    public String getNotionLink() {
        return getString(ConfigMenu.LINK_SETTING_NOTION_SITE_LINK);
    }

    public String getMinelistLinkComment() {
        return getString(ConfigMenu.MESSAGE_MINELIST);
    }

    public String getDiscordInviteComment() {
        return getString(ConfigMenu.MESSAGE_DISCORD);
    }

    public String getNotionLinkComment() {
        return getString(ConfigMenu.MESSAGE_SERVER_NOTION);
    }

    public String getTip() {
        return getString(ConfigMenu.MESSAGE_COMMAND_SERVER_TIP);
    }

    public String getHeartTip() {
        return getString(ConfigMenu.HEART_TIP);
    }

    public String getExtraHeartTip() {
        return getString(ConfigMenu.MESSAGE_EXTRA_HEART_TIP);
    }

    public String getSteelLifeTip() {
        return getString(ConfigMenu.MESSAGE_STEEL_LIFE_TIP);
    }

    public String getRaidTip() {
        return getString(ConfigMenu.MESSAGE_RAID_TIP);
    }

    public String getWeaponTip() {
        return getString(ConfigMenu.MESSAGE_WEAPON_TIP);
    }

    public String getExplosiveTip() {
        return getString(ConfigMenu.MESSAGE_EXPLOSIVE_TIP);
    }

    public String getCommand() {
        return getString(ConfigMenu.MESSAGE_COMMAND_TIP);
    }

    public String getCommandFly() {
        return getString(ConfigMenu.MESSAGE_COMMAND_FLY);
    }

    public String getCommandHat() {
        return getString(ConfigMenu.MESSAGE_COMMAND_HAT);
    }

    public String getCommandSwap() {
        return getString(ConfigMenu.MESSAGE_COMMAND_SWAP);
    }

    public String getCommandTotem() {
        return getString(ConfigMenu.MESSAGE_COMMAND_TOTEM);
    }

    public String getCommandEnhance() {
        return getString(ConfigMenu.MESSAGE_COMMAND_ENHANCE);
    }

    public String getCommandAnnouncing() {
        return getString(ConfigMenu.MESSAGE_COMMAND_ANNOUNCING);
    }

    public String getCommandTpa() {
        return getString(ConfigMenu.MESSAGE_COMMAND_TPA);
    }

    public String getCommandShop() {
        return getString(ConfigMenu.MESSAGE_COMMAND_SHOP);
    }

    public String getCommandBalance() {
        return getString(ConfigMenu.MESSAGE_COMMAND_BALANCE);
    }

    public String getCommandParty() {
        return getString(ConfigMenu.MESSAGE_COMMAND_PARTY);
    }

    public String getCommandSkill() {
        return getString(ConfigMenu.MESSAGE_COMMAND_SKILL);
    }

    public String getCommandPlayTime() {
        return getString(ConfigMenu.MESSAGE_COMMAND_PLAY_TIME);
    }

    public void worldConfigSave(String motd) {
        this.config.set(ConfigMenu.WORLD_SETTING_MOTD.getConfigMessage(), motd);
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            System.err.println("Saving World Config Fail!");
        }
    }
}
