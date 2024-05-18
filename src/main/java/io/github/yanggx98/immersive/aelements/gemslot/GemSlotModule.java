package io.github.yanggx98.immersive.aelements.gemslot;

import io.github.yanggx98.immersive.aelements.ExtraSmithingRecipesHelper;
import io.github.yanggx98.immersive.aelements.IAEModule;
import io.github.yanggx98.immersive.aelements.IExtraSmithingRecipesProvider;
import io.github.yanggx98.immersive.aelements.gemslot.item.GemItem;
import io.github.yanggx98.immersive.aelements.gemslot.item.RoundGemEmbedTemplateItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SmithingTemplateItem;

import java.util.List;

import static io.github.yanggx98.immersive.aelements.gemslot.GemSlotItems.CRITICAL_DAMAGE_GEM_LEVEL_1;

public class GemSlotModule implements IAEModule {

    @Override
    public void onInitialize() {
        GemEffectEntries.onInitialize();
        GemSlotItems.onInitialize();
        ExtraSmithingRecipesHelper.registerRecipes((SmithingTemplateItem) GemSlotItems.ROUND_GEM_EMBED_TEMPLATE,new IExtraSmithingRecipesProvider() {

            @Override
            public List<Item> getBaseList(ItemStack templateItemStack) {
                return List.of(Items.STONE_SWORD);
            }

            @Override
            public List<Item> getAdditionList(ItemStack templateItemStack) {
                return List.of(CRITICAL_DAMAGE_GEM_LEVEL_1);
            }

            @Override
            public ItemStack resultItemStack(ItemStack templateItemStack, ItemStack baseItem, ItemStack additionItem) {
                if (additionItem.getItem() instanceof GemItem gemItem){
                    return GemSlotHelper.embedGem(baseItem,gemItem);
                }
                return null;
            }
        });

    }
}
