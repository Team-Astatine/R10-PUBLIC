package org.Astatine.r10.Contents.PlayerInteraction.PlayerStatus.PlayerJoinEvent;

import java.util.Arrays;
import java.util.Optional;

import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Contents.RandomTeleport.RandomPositionGenerator;
import org.Astatine.r10.Data.User.UserData.User;
import org.Astatine.r10.Data.User.UserData.UserBuilder;
import org.Astatine.r10.Data.User.UserData.UserHandler;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatus;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusHandler;
import org.Astatine.r10.Enumeration.Kit.FoodKit;
import org.Astatine.r10.Enumeration.Kit.ToolKit;
import org.Astatine.r10.Enumeration.Type.WorldType;
import org.Astatine.r10.Util.Function.StringComponentExchanger;
import org.Astatine.r10.command.ModeratorCommand.SetGodMode;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class JoiningPlayerConfigurationStatusService extends StringComponentExchanger implements EventRegister {

    private User user;
    private UserKillStatus userKillStatus;
    private Player player;
    private final PlayerJoinEvent event;

    public JoiningPlayerConfigurationStatusService(PlayerJoinEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.player = this.event.getPlayer();
        this.user = new UserHandler().readUser(this.player.getUniqueId());
        this.userKillStatus = new UserKillStatusHandler().readUser(this.player.getUniqueId());
    }

    @Override
    public void execute() {
        supplyUserKit();
        checkingUserStatusGod();
        checkingUserStatusAttackSpeed();
        checkingUserStatusHealth();
        increaseUserJoinCnt();
        randomTeleportFirstJoinPlayer();
    }

    private void randomTeleportFirstJoinPlayer() {
        if (this.player.hasPlayedBefore())
            return;

        World world = Bukkit.getWorld(WorldType.WORLD.getExchangeEnglish());
        int[] position = new RandomPositionGenerator().getRandomPosition(world);

        int x = position[0];
        int y = position[1];
        int z = position[2];

        this.player.teleportAsync(new Location(world, x, y, z));
        Bukkit.getLogger().info(String.format("%s Player Teleport Position is > %d %d %d", player.getName(), x, y, z));
    }

    private void checkingUserStatusGod() {
        new SetGodMode().setPotionEffect(this.player, this.user);
    }

    private void checkingUserStatusAttackSpeed() {
        Optional.ofNullable(this.player.getAttribute(Attribute.ATTACK_SPEED)).ifPresent(
                attackSpeed -> attackSpeed.setBaseValue(40.0)
        );
    }

    private void checkingUserStatusHealth() {
        this.player.setHealthScale(this.userKillStatus.healthScale());
        this.player.getAttribute(Attribute.MAX_HEALTH).setBaseValue(this.userKillStatus.healthScale());
    }

    private void supplyUserKit() {
        if (this.player.hasPlayedBefore())
            return;

        Arrays.stream(FoodKit.values()).forEach(i ->
                this.player.getInventory().addItem(i.getFood())
        );

        /* Non Using Netherite Kit
        for (ArmourKit kit : ArmourKit.values()) {
            ItemStack armour = kit.getArmour();
            armour.addEnchantment(Enchantment.PROTECTION, 2);
            armour.addEnchantment(Enchantment.UNBREAKING, 2);
            this.player.getInventory().addItem(kit.getArmour());
        }
        */

        Arrays.stream(ToolKit.values()).forEach(i -> {
            ItemStack tool = i.getToolKit();
            tool.setAmount(i.getSupplyAmount());
            this.player.getInventory().addItem(tool);
        });
    }

    private void increaseUserJoinCnt() {
        this.user = new UserBuilder(this.user)
                .joinCount(this.user.joinCount() + 1)
                .buildAndUpdate();
    }
}
