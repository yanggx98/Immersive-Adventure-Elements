package io.github.yanggx98.immersive.aelements.gemslot;

import io.github.yanggx98.immersive.aelements.gemslot.item.GemItem;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Map;

public interface IEmbeddable {
    List<GemItem.GemType> immersive_Adventure_Elements$canOpenSlot(ItemStack stack);
    List<GemItem> immersive_Adventure_Elements$getGemEmbededList(ItemStack stack);
    List<GemItem.GemType> immersive_Adventure_Elements$getEmptyGemSlotList(ItemStack stack);
}
