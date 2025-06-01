package org.Astatine.r10.Contents;

import org.Astatine.r10.Contents.BindItem.BindItemRestoreService;
import org.Astatine.r10.Contents.BindItem.RegistBindItemHandler;
import org.Astatine.r10.Contents.Company.CompanyPrefix;
import org.Astatine.r10.Contents.CustomizationExploded.ExplosiveService;
import org.Astatine.r10.Contents.Enhance.PlayerInteraction.Armour.EnhanceArmourResistanceHandler;
import org.Astatine.r10.Contents.Enhance.PlayerInteraction.LongRange.GodMode.GodModeTridentHitEvent;
import org.Astatine.r10.Contents.Enhance.PlayerInteraction.LongRange.GodMode.GodModeTridentShotEvent;
import org.Astatine.r10.Contents.Enhance.PlayerInteraction.LongRange.Hit.EnhanceBowHitEvent;
import org.Astatine.r10.Contents.Enhance.PlayerInteraction.LongRange.Hit.EnhanceCrossBowHitEvent;
import org.Astatine.r10.Contents.Enhance.PlayerInteraction.LongRange.Hit.EnhanceTridentHitEvent;
import org.Astatine.r10.Contents.Enhance.PlayerInteraction.LongRange.Shot.EnhanceBowShotEvent;
import org.Astatine.r10.Contents.Enhance.PlayerInteraction.LongRange.Shot.EnhanceCrossBowShotEvent;
import org.Astatine.r10.Contents.Enhance.PlayerInteraction.LongRange.Shot.EnhanceTridentShotEvent;
import org.Astatine.r10.Contents.Enhance.PlayerInteraction.ShortRange.EnhanceShortRangeHitEvent;
import org.Astatine.r10.Contents.Enhance.PlayerInteraction.UpdateItemLore.UpdateEnhanceItemLoreFromEnchantment;
import org.Astatine.r10.Contents.Enhance.PlayerInteraction.UpdateItemLore.UpdateEnhanceItemPrepareAnvil;
import org.Astatine.r10.Contents.Enhance.PlayerInteraction.UpdateItemLore.UpdateEnhanceItemPrepareGrindstone;
import org.Astatine.r10.Contents.Enhance.PlayerInteraction.UpdateItemLore.UpdateEnhanceResultItemLoreFromAnvil;
import org.Astatine.r10.Contents.Enhance.PlayerInteraction.UpdateItemLore.UpdateEnhanceResultItemLoreFromGrindStone;
import org.Astatine.r10.Contents.LifeSteel.BossRewardService;
import org.Astatine.r10.Contents.LifeSteel.DualWieldSwingMotionService;
import org.Astatine.r10.Contents.LifeSteel.LifeSteelEvent;
import org.Astatine.r10.Contents.PlayerDeathEvent.DeadPlayerHeadDropService;
import org.Astatine.r10.Contents.PlayerInteraction.Announce.JoinAndQuitMessage.JoinMessageService;
import org.Astatine.r10.Contents.PlayerInteraction.Announce.JoinAndQuitMessage.QuitMessageService;
import org.Astatine.r10.Contents.PlayerInteraction.Announce.Raid.RaidAnnouncementService;
import org.Astatine.r10.Contents.PlayerInteraction.PlayerStatus.PlayerDeathEvent.IncrementKillerKillCountService;
import org.Astatine.r10.Contents.PlayerInteraction.PlayerStatus.PlayerJoinEvent.JoiningPlayerConfigurationStatusService;
import org.Astatine.r10.Contents.PlayerInteraction.PlayerStatus.PlayerJoinEvent.JoiningUserConfigurationValueService;
import org.Astatine.r10.Contents.PlayerInteraction.PlayerStatus.PlayerJoinEvent.PlayerFlightService;
import org.Astatine.r10.Contents.PlayerInteraction.PlayerStatus.PlayerQuitEvent.QuitUserConfigurationValueService;
import org.Astatine.r10.Contents.PlayerInteraction.PlayerStatus.PlayerRespawnEvent.RespawnMessageService;
import org.Astatine.r10.Contents.RandomTeleport.RespawnRandomTeleportService;
import org.Astatine.r10.Contents.RemoveHitDelay.EntityHitDealyHandler;
import org.Astatine.r10.Contents.RemoveHitDelay.ResetHitDelayHandler;
import org.Astatine.r10.Contents.Restricted.AntiExploit.ChatFlood.RestrictedChatHandler;
import org.Astatine.r10.Contents.Restricted.AntiExploit.ChunkRenderer.RestrictedPortalEntryHandler;
import org.Astatine.r10.Contents.Restricted.AntiExploit.Gravity.RestrictedPistonInteractionHandler;
import org.Astatine.r10.Contents.Restricted.AntiExploit.Interaction.InventoryInteraction.Dispenser.RestrictedDispenserInventoryMoveItemHandler;
import org.Astatine.r10.Contents.Restricted.AntiExploit.Interaction.InventoryInteraction.Dispenser.RestrictedDispenserItemClickHandler;
import org.Astatine.r10.Contents.Restricted.AntiExploit.Interaction.LeverInteraction.LeverInteractionHandler;
import org.Astatine.r10.Contents.Restricted.AntiExploit.Interaction.PlayerInteraction.RestrictedCommandHandler;
import org.Astatine.r10.Contents.Restricted.AntiExploit.Interaction.PlayerInteraction.RestrictedCommandInInventoryHandler;
import org.Astatine.r10.Contents.Restricted.AntiExploit.Interaction.PlayerInteraction.RestrictedItemInteractHandler;
import org.Astatine.r10.Contents.Restricted.AntiExploit.Interaction.SignInteraction.RestrictedSignChangeHandler;
import org.Astatine.r10.Contents.Restricted.AntiExploit.Interaction.SignInteraction.RestrictedSignHandler;
import org.Astatine.r10.Contents.Restricted.Function.Event.DefendsDurabilityWithMace.PreventMaceDamageHandler;
import org.Astatine.r10.Contents.Restricted.Function.Event.EntityExplode.RestrictedExplosiveDamageHandler;
import org.Astatine.r10.Contents.Restricted.Function.TotemStack.RestrictedStackingTotemClickHandler;
import org.Astatine.r10.Contents.Restricted.Function.TotemStack.RestrictedStackingTotemInteractionShulkerChestHandler;
import org.Astatine.r10.Contents.UserInterface.Command.MainMenu.MainMenuTabKeyHandler;
import org.Astatine.r10.Contents.UserInterface.Core.UIEventSwitcherHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareGrindstoneEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.raid.RaidTriggerEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.event.world.ChunkLoadEvent;

import io.papermc.paper.event.player.AsyncChatEvent;
import io.papermc.paper.event.player.PlayerArmSwingEvent;
import io.papermc.paper.event.player.PlayerOpenSignEvent;

/**
 * {@linkplain Listener}의 구현체를 관리합니다.
 *
 * @performance RunTime시 해당 클래스는 하나의 Instance만 생성됩니다.
 * 이벤트를 추가하고 싶다면, Event를 선택하여 해당 함수에 객체를 생성 후, {@link EventRegister}를 상속받아 구현합니다.
 * 사용하지 않지만, 개발된 함수는 일단 적어둔 후 todo 태그로 관리힙니다.
 */
public class GlobalEventHandler implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void BlockRedstoneEvent(BlockRedstoneEvent event) {
//        methodImplement
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void BlockPhysicsEvent(BlockPhysicsEvent event) {
//        methodImplement
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void ChunkLoadEvent(ChunkLoadEvent event) {
//        methodImplement
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        new JoiningUserConfigurationValueService(event);
        new JoiningPlayerConfigurationStatusService(event);
        new JoinMessageService(event);
        new PlayerFlightService(event);

    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerQuitEvent(PlayerQuitEvent event) {
        new QuitUserConfigurationValueService(event);
        new QuitMessageService(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void RaidTriggerEvent(RaidTriggerEvent event) {
//        todo 반야생변경에 따른 콘텐츠 수정
        new RaidAnnouncementService(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerDeathEvent(PlayerDeathEvent event) {
        new LifeSteelEvent(event);
        new RegistBindItemHandler(event);
        new IncrementKillerKillCountService(event);
        new DeadPlayerHeadDropService(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void EntityDeathEvent(EntityDeathEvent event) {
        new BossRewardService(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void EnchantItemEvent(EnchantItemEvent event) {
        new UpdateEnhanceItemLoreFromEnchantment(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PrepareAnvilEvent(PrepareAnvilEvent event) {
        new UpdateEnhanceItemPrepareAnvil(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PrepareGrindstoneEvent(PrepareGrindstoneEvent event) {
        new UpdateEnhanceItemPrepareGrindstone(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void ProjectileHitEvent(ProjectileHitEvent event) {
        new EnhanceBowHitEvent(event);
        new EnhanceCrossBowHitEvent(event);
        new EnhanceTridentHitEvent(event);
        new GodModeTridentHitEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void ProjectileLaunchEvent(ProjectileLaunchEvent event) {
        new EnhanceBowShotEvent(event);
        new EnhanceCrossBowShotEvent(event);
        new EnhanceTridentShotEvent(event);
        new GodModeTridentShotEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerRespawnEvent(PlayerRespawnEvent event) {
        new BindItemRestoreService(event);
        new RespawnMessageService(event);
        new PlayerFlightService(event);
        new RespawnRandomTeleportService(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void InventoryClickEvent(InventoryClickEvent event) {

//        Restriction
        new RestrictedDispenserItemClickHandler(event);
        new RestrictedStackingTotemClickHandler(event);
        new RestrictedStackingTotemInteractionShulkerChestHandler(event);

//        Enhance Prepare
        new UpdateEnhanceResultItemLoreFromAnvil(event);
        new UpdateEnhanceResultItemLoreFromGrindStone(event);

//        User Interface
        new UIEventSwitcherHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void InventoryCloseEvent(InventoryCloseEvent event) {
//        User Interface
        new UIEventSwitcherHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerEditSign(PlayerOpenSignEvent event) {
        new RestrictedSignHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void SignChangeEvent(SignChangeEvent event) {
        new RestrictedSignChangeHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void InventoryMoveItemEvent(InventoryMoveItemEvent event) {
        new RestrictedDispenserInventoryMoveItemHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerItemDamageEvent(PlayerItemDamageEvent event) {
        new PreventMaceDamageHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void EntityExplodeEvent(EntityExplodeEvent event) {
        new ExplosiveService(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerArmSwingEvent(PlayerArmSwingEvent event) {
        new DualWieldSwingMotionService(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void EntityDamageEvent(EntityDamageEvent event) {
//        Event Cancelled 하면 해당 Event 자체가 캔슬됌.
        new EnhanceArmourResistanceHandler(event);
        new RestrictedExplosiveDamageHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerInteractEvent(PlayerInteractEvent event) {
        new ResetHitDelayHandler(event);
        new LeverInteractionHandler(event);
        new RestrictedItemInteractHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void EntityPortalEvent(EntityPortalEvent event) {
        new RestrictedPortalEntryHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void BlockPistonExtendEvent(BlockPistonExtendEvent event) {
        new RestrictedPistonInteractionHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void TabCompleteEvent(TabCompleteEvent event) {
//        todo permission 세팅으로 해결함.
//        new RestrictedCommandTabCompleteEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        new RestrictedCommandInInventoryHandler(event);
        new RestrictedCommandHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void AsyncChatEvent(AsyncChatEvent event) {
        new RestrictedChatHandler(event);
        new CompanyPrefix(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerSwapHandItemsEvent(PlayerSwapHandItemsEvent event) {
        new MainMenuTabKeyHandler(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerChangedWorldEvent(PlayerChangedWorldEvent event) {
        new PlayerFlightService(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        new EntityHitDealyHandler(event);
        new EnhanceShortRangeHitEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void CraftItemEvent(CraftItemEvent event) {
//        todo permission 세팅으로 해결함.
//        new RestrictedItemCraftHandler(event);
    }
}
