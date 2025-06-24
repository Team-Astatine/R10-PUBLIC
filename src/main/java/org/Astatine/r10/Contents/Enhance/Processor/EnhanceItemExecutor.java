package org.Astatine.r10.Contents.Enhance.Processor;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.Material;

import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Exception.Enhance.EnhanceItemSearchException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.Astatine.r10.Contents.Enhance.Enumeration.Armour.ArmourList;
import org.Astatine.r10.Contents.Enhance.Enumeration.Scroll.ProtectScrollList;
import org.Astatine.r10.Contents.Enhance.Enumeration.Scroll.ScrollList;
import org.Astatine.r10.Contents.Enhance.Enumeration.Weapon.LongRange;
import org.Astatine.r10.Contents.Enhance.Enumeration.Weapon.ShortRange;
import org.Astatine.r10.Util.Function.Emoji;
import org.Astatine.r10.Util.Function.StringComponentExchanger;

public class EnhanceItemExecutor extends StringComponentExchanger {
    private static final int LOW_LEVEL = 0;
    private static final int MAX_LEVEL = 10;
    
    private final int MSG_NO_WEAPON = 0;
    private final int MSG_NO_SCROLL = 1;
    private final int MSG_MAX_LEVEL = 2;
    private final int MSG_ITEM_DESTROYED = 3;
    private final int MSG_PROTECT_SUCCESS = 4;
    private final int MSG_ENHANCE_FAIL = 5;
    private final int MSG_ENHANCE_SUCCESS = 6;
    private final int MSG_PROTECT_INSUFFICIENT = 7;
    private final int MSG_SCROLL_INSUFFICIENT = 8;
    private final int MSG_INVALID_ITEM = 9;
    private final int MSG_INVALID_SCROLL = 10;
    private final int MSG_INVALID_PROTECT_SCROLL = 11;

    private final EnhanceItem item;
    private final Set<Material> allowedItems;
    private final Set<Material> allowedScrolls;
    
    private boolean hasProtectScroll;
    private boolean isScrollSufficient;
    private boolean isProtectScrollSufficient;

    public EnhanceItemExecutor(EnhanceItem item) {
        this.item = item;
        this.allowedItems = createAllowedItemsSet();
        this.allowedScrolls = createAllowedScrollsSet();
        
        initializeFlags();
        executeEnhancement();
    }

    private void executeEnhancement() {
        if (BooleanUtils.isFalse(isValidEnhanceItem())) 
            return;
        
        checkScrollAvailability();
        
        if (isMaxLevelReached()) 
            return;

        if (BooleanUtils.isFalse(areScrollsSufficient())) 
            return;
        
        processEnhancement();
    }

    private Set<Material> createAllowedItemsSet() {
        Set<Material> items = new HashSet<>();
        Arrays.stream(ShortRange.values()).forEach(i -> items.add(i.getMaterial()));
        Arrays.stream(LongRange.values()).forEach(i -> items.add(i.getMaterial()));
        Arrays.stream(ArmourList.values()).forEach(i -> items.add(i.getMaterial()));
        return items;
    }

    private Set<Material> createAllowedScrollsSet() {
        Set<Material> scrolls = new HashSet<>();
        Arrays.stream(ScrollList.values()).forEach(i -> scrolls.add(i.getMaterial()));
        Arrays.stream(ProtectScrollList.values()).forEach(i -> scrolls.add(i.getMaterial()));
        return scrolls;
    }

    private void initializeFlags() {
        this.hasProtectScroll = ObjectUtils.isNotEmpty(item.protectScroll());
        this.isScrollSufficient = false;
        this.isProtectScrollSufficient = false;
    }

    private boolean isValidEnhanceItem() {
        if (ObjectUtils.isEmpty(item.enhanceItem())) {
            sendMessage(MSG_NO_WEAPON);
            return false;
        }

        if (ObjectUtils.isEmpty(item.enhanceScroll())) {
            sendMessage(MSG_NO_SCROLL);
            return false;
        }

        if (!allowedItems.contains(item.enhanceItem().getType())) {
            sendMessage(MSG_INVALID_ITEM);
            return false;
        }

        if (!allowedScrolls.contains(item.enhanceScroll().getType())) {
            sendMessage(MSG_INVALID_SCROLL);
            return false;
        }

        if (hasProtectScroll && !allowedScrolls.contains(item.protectScroll().getType())) {
            sendMessage(MSG_INVALID_PROTECT_SCROLL);
            return false;
        }

        return true;
    }

    private void checkScrollAvailability() {
        try {
            int scrollRequirement = ScrollList.findByItemStack(item.enhanceScroll()).getDiscountValue();
            isScrollSufficient = item.enhanceScroll().getAmount() >= scrollRequirement;
            
            if (hasProtectScroll) {
                int protectRequirement = ProtectScrollList.findByItemStack(item.protectScroll()).getDiscountValue();
                isProtectScrollSufficient = item.protectScroll().getAmount() >= protectRequirement;
            }
        } catch (EnhanceItemSearchException e) {
            e.printStackTrace();
        }
    }

    private boolean isMaxLevelReached() {
        int currentLevel = getCurrentEnhanceLevel();
        if (currentLevel >= MAX_LEVEL) {
            sendMessage(MSG_MAX_LEVEL);
            return true;
        }
        return false;
    }

    private boolean areScrollsSufficient() {
        if (!isScrollSufficient) {
            sendMessage(MSG_SCROLL_INSUFFICIENT);
            return false;
        }

        if (hasProtectScroll && !isProtectScrollSufficient) {
            sendMessage(MSG_PROTECT_INSUFFICIENT);
            return false;
        }

        return true;
    }

    private void processEnhancement() {
        int currentLevel = getCurrentEnhanceLevel();
        boolean isSuccessful = EnhanceUtil.isMeetsJudgementCriteria(MAX_LEVEL - currentLevel);
        boolean willDestroy = EnhanceUtil.isMeetsJudgementCriteria(currentLevel - LOW_LEVEL);

        if (isSuccessful) {
            handleSuccess();
        } else if (!willDestroy) {
            handleFailure();
        } else if (!hasProtectScroll) {
            handleDestruction();
        } else {
            handleProtectedFailure();
        }
        
        EnhanceUtil.scrollDiscount(item.enhanceScroll(), item.protectScroll());
    }

    private void handleSuccess() {
        sendMessage(MSG_ENHANCE_SUCCESS);
        try {
            EnhanceUtil.increaseEnhanceItemLevel(item.enhancePlayer(), item.enhanceItem(), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleFailure() {
        sendMessage(MSG_ENHANCE_FAIL);
        try {
            EnhanceUtil.increaseEnhanceItemLevel(item.enhanceItem(), -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleDestruction() {
        item.enhanceItem().setAmount(0);
        sendMessage(MSG_ITEM_DESTROYED);
    }

    private void handleProtectedFailure() {
        handleFailure();
        sendMessage(MSG_PROTECT_SUCCESS);
    }

    private int getCurrentEnhanceLevel() {
        if (ObjectUtils.isEmpty(item.enhanceItem()) || item.enhanceItem().getType() == Material.AIR) {
            return 0;
        }
        return item.enhanceItem().getItemMeta().getCustomModelData();
    }

    private void sendMessage(int messageCode) {
        int currentLevel = getCurrentEnhanceLevel();
        PlayerComment comment = createPlayerComment(messageCode, currentLevel);
        
        item.enhancePlayer().sendMessage(
            emojiMessage(comment.emoji(), comment.comment(), comment.colorType())
        );
    }

    private PlayerComment createPlayerComment(int messageCode, int currentLevel) {
        return switch (messageCode) {
            case MSG_NO_WEAPON -> new PlayerComment(Emoji.WARNING, "무기를 올려주세요..!", ColorType.WHITE_TO_RED7);
            case MSG_NO_SCROLL -> new PlayerComment(Emoji.WARNING, "강화 주문서가 부족해요..", ColorType.WHITE_TO_RED7);
            case MSG_MAX_LEVEL -> new PlayerComment(Emoji.WARNING, "이미 최고 레벨입니다..!", ColorType.WHITE_TO_RED7);
            case MSG_ITEM_DESTROYED -> new PlayerComment(Emoji.SKULL, "강화에 실패하여 무기가 파괴 되었어요...ㅠㅠㅠㅠ", ColorType.WHITE_TO_RED7);
            case MSG_PROTECT_SUCCESS -> new PlayerComment(Emoji.EXPLODING_PARTY, "파괴방어 스크롤을 사용하여 파괴방지에 성공했어요!!", ColorType.DISCORD_COLOR);
            case MSG_ENHANCE_FAIL -> new PlayerComment(Emoji.SAD_FACE, currentLevel + "강 -> " + (currentLevel - 1) + "강 강화실패..", ColorType.PINK);
            case MSG_ENHANCE_SUCCESS -> new PlayerComment(Emoji.EXPLODING_PARTY, currentLevel + "강 -> " + (currentLevel + 1) + "강 강화 성공했어요!!", ColorType.YELLOW);
            case MSG_PROTECT_INSUFFICIENT -> new PlayerComment(Emoji.WARNING, "파괴방지 주문서가 부족하여 강화가 실행되지 않았어용..!", ColorType.WHITE_TO_RED7);
            case MSG_SCROLL_INSUFFICIENT -> new PlayerComment(Emoji.WARNING, "강화 주문서가 부족하여 실행되지 않았어용..!", ColorType.WHITE_TO_RED7);
            case MSG_INVALID_ITEM -> new PlayerComment(Emoji.WARNING, "허용된 아이템을 넣어주세요!", ColorType.WHITE_TO_RED7);
            case MSG_INVALID_SCROLL -> new PlayerComment(Emoji.WARNING, "허용된 주문서를 넣어주세요!", ColorType.WHITE_TO_RED7);
            case MSG_INVALID_PROTECT_SCROLL -> new PlayerComment(Emoji.WARNING, "허용된 보호 주문서를 넣어주세요!", ColorType.WHITE_TO_RED7);
            default -> throw new IllegalStateException("Unknown message code: " + messageCode);
        };
    }

    private record PlayerComment(Emoji emoji, String comment, ColorType colorType) {}
}