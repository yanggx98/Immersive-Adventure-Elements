package io.github.yanggx98.immersive.aelements.mixin;


import com.google.common.collect.Lists;
import io.github.yanggx98.immersive.aelements.gemslot.GemEffectEntries;
import io.github.yanggx98.immersive.aelements.gemslot.GemEffectEntry;
import io.github.yanggx98.immersive.aelements.gemslot.GemSlotHelper;
import io.github.yanggx98.immersive.aelements.gemslot.IEmbeddable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(Item.class)
public class ItemMixin implements IEmbeddable {

    @Override
    public List<GemEffectEntry> immersive_Adventure_Elements$getGemEmbededList(ItemStack stack) {
        return GemSlotHelper.getGemSlotEntryList(stack);
//        return Lists.newArrayList(GemEffectEntries.GEM_EFFECT_ENTRY_CRITICAL_DAMAGE_LEVEL_1);
    }
}
