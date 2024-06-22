package io.github.yanggx98.immersive.aelements.gemslot.item;

import io.github.yanggx98.immersive.aelements.gemslot.IEmbeddable;
import io.github.yanggx98.immersive.aelements.kaleido.ExtraAnvilRecipesHelper;
import io.github.yanggx98.immersive.aelements.kaleido.IExtraSmithingRecipesProvider;
import io.github.yanggx98.immersive.aelements.gemslot.GemSlotHelper;
import net.minecraft.item.*;
import net.minecraft.screen.Property;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.Util;

import java.util.List;

import static io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements.identifier;

public abstract class GemEmbedTemplateItem extends Item implements ExtraAnvilRecipesHelper.IExtraAnvilRecipeProvider {

    public GemEmbedTemplateItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack get(ItemStack stack, ItemStack stack2, Property levelCount) {
        ItemStack result = stack.copy();
        levelCount.set(levelCount.get()+1);
        if (stack2.getItem() instanceof GemEmbedTemplateItem templateItem) {
            if (stack.getItem() instanceof IEmbeddable) {
                GemSlotHelper.openSlot(result, templateItem);
                return result;
            }
        }
        return ItemStack.EMPTY;
    }

    public abstract GemItem.GemType getGemSlotType();
}
