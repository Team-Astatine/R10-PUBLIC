package org.Astatine.r10.Contents.UserInterface.Core.UIGenerator;

import org.bukkit.inventory.ItemStack;

public record SlotItemMapping (
        int slot,
        ItemStack itemStack
){};