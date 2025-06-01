package org.Astatine.r10.Contents.UserInterface.Core;

import java.util.Optional;

import org.Astatine.r10.Contents.UserInterface.Core.Interface.UIHolder;
import org.Astatine.r10.Contents.UserInterface.Core.Interface.UIType;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryEvent;

/**
 * {@link UIEventSwitcherHandler} 클래스는 {@link InventoryEvent}를 받아 {@link UIType} 어노테이션에 매핑된 핸들러로 분기 처리합니다.
 * CLICK_HANDLERS: 각 UIType의 클릭 이벤트 처리 핸들러 맵 (Type → Consumer InventoryClickEvent)
 * CLOSE_HANDLERS: 각 UIType의 닫기 이벤트 처리 핸들러 맵 (Type → Consumer InventoryCloseEvent)
 */
public class UIEventSwitcherHandler {
    /**
     * 주어진 {@link InventoryEvent}를 분석하여 {@link UIType} 값에 맞는 핸들러를 실행합니다.
     *
     * @param event 처리할 인벤토리 이벤트 (클릭 또는 닫기)
     */
    public UIEventSwitcherHandler(InventoryEvent event) {

        if (!(event.getView().getTopInventory().getHolder() instanceof UIHolder holder))
            return;

        UIType typeAnnotation = holder.getClass().getAnnotation(UIType.class);
        if (ObjectUtils.isEmpty(typeAnnotation))
            return;

        switch (event) {
            case InventoryClickEvent clickEvent ->
                typeAnnotation.value().getOpenConsumer().accept(clickEvent);

            case InventoryCloseEvent closeEvent ->
                    Optional.ofNullable(typeAnnotation.value()
                            .getCloseEventConsumer())
                            .ifPresent(
                    handler -> handler.accept(closeEvent)
                );

            default -> {}
        }

    }
}
