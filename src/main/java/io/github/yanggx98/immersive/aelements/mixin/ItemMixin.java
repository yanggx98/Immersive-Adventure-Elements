package io.github.yanggx98.immersive.aelements.mixin;


import io.github.yanggx98.immersive.aelements.gemslot.GemEffectEntry;
import io.github.yanggx98.immersive.aelements.gemslot.GemSlotHelper;
import io.github.yanggx98.immersive.aelements.gemslot.IEmbeddable;
import io.github.yanggx98.immersive.aelements.gemslot.item.GemItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Map;

@Mixin(Item.class)
public class ItemMixin implements IEmbeddable {

    @Override
    public Map<GemEffectEntry, GemItem.Level> immersive_Adventure_Elements$getGemEmbededList(ItemStack stack) {
        return GemSlotHelper.getGemSlotEntryMap(stack);
    }
}
