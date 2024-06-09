package io.github.yanggx98.immersive.aelements;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public interface IExtraSmithingRecipesProvider {
    boolean isSupportBaseItem(ItemStack stack);
    boolean isSupportAdditionItem(ItemStack stack);
    ItemStack resultItemStack(ItemStack templateItemStack, ItemStack baseItem, ItemStack additionItem);
}