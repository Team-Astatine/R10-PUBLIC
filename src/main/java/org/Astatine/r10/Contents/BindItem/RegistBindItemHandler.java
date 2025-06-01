package org.Astatine.r10.Contents.BindItem;

import java.util.List;
import java.util.stream.Collectors;

import org.Astatine.r10.R10;
import org.Astatine.r10.Contents.EventRegister;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class RegistBindItemHandler implements EventRegister {

    private Player player;
    private List<ItemStack> dropList;
    private BindItemCacheManager handler;
    
    private final PlayerDeathEvent event;

    public RegistBindItemHandler(PlayerDeathEvent event) {
        this.event = event;

        init();
        execute();
    }

    @Override
    public void init() {
        this.player = this.event.getPlayer();
        this.dropList = this.event.getDrops();
        this.handler = BindItemCacheManager.getInstance();
    }
    
    @Override
    public void execute() {
        List<ItemStack> bindItemList = this.dropList.stream()
            .filter(item -> {
                NamespacedKey keepkey = new NamespacedKey(R10.getPlugin(R10.class), "keepOnDeath");
                Boolean flag = item.getPersistentDataContainer().get(keepkey, PersistentDataType.BOOLEAN);
                return flag != null && flag;
            })
            .collect(Collectors.toList());

        this.event.getDrops().removeAll(bindItemList);
        this.handler.addItems(this.player.getUniqueId(), bindItemList);     
    }
}
