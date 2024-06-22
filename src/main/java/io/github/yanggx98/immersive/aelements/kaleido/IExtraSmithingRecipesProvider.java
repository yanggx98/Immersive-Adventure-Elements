package io.github.yanggx98.immersive.aelements.kaleido;

import net.minecraft.item.ItemStack;

public interface IExtraSmithingRecipesProvider {
    boolean isSupportBaseItem(ItemStack stack);
    boolean isSupportAdditionItem(ItemStack stack);
    ItemStack resultItemStack(ItemStack templateItemStack, ItemStack baseItem, ItemStack additionItem);
}