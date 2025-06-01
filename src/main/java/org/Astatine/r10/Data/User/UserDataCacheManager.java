package org.Astatine.r10.Data.User;


import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.Astatine.r10.Data.User.UserData.User;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatus;

public class UserDataCacheManager {
    private static class UserDataHolder {
        private final static ConcurrentHashMap<UUID, User> INSTANCE = new ConcurrentHashMap<>();
    }

    public static ConcurrentHashMap<UUID, User> getUserDataInstance() {
        return UserDataHolder.INSTANCE;
    }


    private static class UserKillStatusHolder {
        private final static ConcurrentHashMap<UUID, UserKillStatus> INSTANCE = new ConcurrentHashMap<>();
    }

    public static ConcurrentHashMap<UUID, UserKillStatus> getUserKillStatusInstance() {
        return UserKillStatusHolder.INSTANCE;
    }
}
