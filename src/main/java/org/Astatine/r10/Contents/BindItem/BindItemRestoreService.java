package org.Astatine.r10.Contents.BindItem;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import org.Astatine.r10.Contents.EventRegister;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class BindItemRestoreService implements EventRegister {
    
    private Player player;
    private BindItemCacheManager handler;
    
    private final PlayerRespawnEvent event;

    public BindItemRestoreService(PlayerRespawnEvent event) {
        this.event = event;

        init();
        execute();
    }

    @Override
    public void init() {
        this.player = this.event.getPlayer();
        this.handler = BindItemCacheManager.getInstance();
    }

    @Override
    public void execute() {
        UUID eventPlayerUUID = this.player.getUniqueId();
        List<ItemStack> items = this.handler.getItemStacks(this.player.getUniqueId());

        if (ObjectUtils.isEmpty(items))
            return;

        this.handler.removeItems(eventPlayerUUID);

        IntStream.range(0, items.size())
            .forEach(index -> {
                this.player.getInventory().setItem(index, items.get(index));
                }
            );
    }
}