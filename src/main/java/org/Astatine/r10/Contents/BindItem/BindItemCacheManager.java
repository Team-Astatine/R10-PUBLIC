package org.Astatine.r10.Contents.BindItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.inventory.ItemStack;

public class BindItemCacheManager {

    private static class BindItemHandlerHolder {
        private static BindItemCacheManager INSTANCE = new BindItemCacheManager();
    }

    public static BindItemCacheManager getInstance() {
        return BindItemHandlerHolder.INSTANCE;
    }

    private final Map<UUID, List<ItemStack>> CACHED_ITEM;

    public BindItemCacheManager() {
        this.CACHED_ITEM = new HashMap<>();
    }

    public void addItems(UUID uuid, List<ItemStack> items) {
        this.CACHED_ITEM.put(uuid, items);
    }

    public List<ItemStack> getItemStacks(UUID uuid) {
        return this.CACHED_ITEM.get(uuid);
    }

    public void removeItems(UUID uuid) {
        this.CACHED_ITEM.remove(uuid);
    }
}
