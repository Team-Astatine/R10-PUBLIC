package org.Astatine.r10.Contents.RandomTeleport;

import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Enumeration.Type.WorldType;
import org.Astatine.r10.Util.Function.StringComponentExchanger;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;


public class RespawnRandomTeleportService extends StringComponentExchanger implements EventRegister {
    private Player player;

    private final PlayerRespawnEvent event;

    public RespawnRandomTeleportService(PlayerRespawnEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.player = this.event.getPlayer();
    }

    @Override
    public void execute() {
        if (this.event.isAnchorSpawn())
            return;

        if (ObjectUtils.allNotNull(this.player.getPotentialBedLocation()))
            return;

        World world = Bukkit.getWorld(WorldType.WORLD.getExchangeEnglish());
        int[] position = new RandomPositionGenerator().getRandomPosition(world);

        int x = position[0];
        int y = position[1];
        int z = position[2];

        this.player.teleportAsync(new Location(world, x, y, z));
    }
}
