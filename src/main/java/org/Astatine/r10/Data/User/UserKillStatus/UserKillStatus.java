package org.Astatine.r10.Data.User.UserKillStatus;

import org.Astatine.r10.Data.Interface.RObject;

import java.util.UUID;

public record UserKillStatus(
        UUID uuid,
        double healthScale,
        int killCount
) implements RObject {
}
