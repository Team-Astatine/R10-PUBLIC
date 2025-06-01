package org.Astatine.r10.Data.DataIO.User;

import org.Astatine.r10.R10;

import java.io.File;

public enum DataFile {
    CONFIG(new File(R10.getPlugin(R10.class).getDataFolder(), "config.yml"), "Config "),
    USER_DATA(new File(R10.getPlugin(R10.class).getDataFolder(), "DataResource/UserData.json"), "DataResource/User Data "),
    KILL_STATUS(new File(R10.getPlugin(R10.class).getDataFolder(), "DataResource/KillStatus.json"), "DataResource/Kill Status "),
    COMPANY(new File(R10.getPlugin(R10.class).getDataFolder(), "DataResource/Company/Companys.json"), "DataResource/Company/Companys "),
    USER_INVENTORY(new File(R10.getPlugin(R10.class).getDataFolder(), "DataResource/UserInventory.json"), "DataResource/User Inventory "),
    ABSOLUTE_PATH(new File(R10.getPlugin(R10.class).getDataFolder().getParentFile().getAbsolutePath()), "Plugin Folder");

    private final File fileInstance;
    private final String fileTypeName;

    DataFile(File fileInstance, String fileTypeName) {
        this.fileInstance = fileInstance;
        this.fileTypeName = fileTypeName;
    }

    public File getFileInstance() {
        return fileInstance;
    }

    public String getFileName() {
        return fileInstance.getName();
    }

    public String getFileTypeName() {
        return fileTypeName;
    }
}
