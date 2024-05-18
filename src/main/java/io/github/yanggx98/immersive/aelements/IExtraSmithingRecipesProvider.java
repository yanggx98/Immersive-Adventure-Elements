package io.github.yanggx98.immersive.aelements;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SmithingTemplateItem;

import java.util.List;

public interface IExtraSmithingRecipesProvider {
    List<Item> getBaseList(ItemStack templateItemStack);
    List<Item> getAdditionList(ItemStack templateItemStack);

    ItemStack resultItemStack(ItemStack templateItemStack,ItemStack bastItem,ItemStack additionItem);
}