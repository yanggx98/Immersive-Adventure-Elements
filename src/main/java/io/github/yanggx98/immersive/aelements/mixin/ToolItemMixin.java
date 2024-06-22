package io.github.yanggx98.immersive.aelements.mixin;


import io.github.yanggx98.immersive.aelements.gemslot.GemEffectEntry;
import io.github.yanggx98.immersive.aelements.gemslot.GemSlotHelper;
import io.github.yanggx98.immersive.aelements.gemslot.IEmbeddable;
import io.github.yanggx98.immersive.aelements.gemslot.item.GemItem;
import io.github.yanggx98.immersive.aelements.rarity.ExtraRarity;
import io.github.yanggx98.immersive.aelements.rarity.ExtraRarityHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import org.spongepowered.asm.mixin.Mixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mixin(ToolItem.class)
public class ToolItemMixin implements IEmbeddable {

    @Override
    public List<GemItem.GemType> immersive_Adventure_Elements$canOpenSlot(ItemStack stack) {
        List<GemItem.GemType> slotList = new ArrayList<>();
        ExtraRarity extraRarity = ExtraRarityHelper.convert(stack.getRarity());
        switch (extraRarity) {
            case LEGEND, EPIC:
                slotList.add(GemItem.GemType.TRIANGLE);
            case ARTIFACT:
                slotList.add(GemItem.GemType.ROUND);
            case RARE:
                slotList.add(GemItem.GemType.ROUND);
            case SENIOR:
                slotList.add(GemItem.GemType.TRIANGLE);
            case COMMON:
                slotList.add(GemItem.GemType.ROUND);
        }
        return slotList;
    }

    @Override
    public Map<GemEffectEntry, GemItem.Level> immersive_Adventure_Elements$getGemEmbededList(ItemStack stack) {
        return GemSlotHelper.getGemSlotEntryMap(stack);
    }

    @Override
    public List<GemItem.GemType> immersive_Adventure_Elements$getEmptyGemSlotList(ItemStack stack) {
        return GemSlotHelper.getEmptyGemSlotList(stack);
    }
}
