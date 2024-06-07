package io.github.yanggx98.immersive.aelements.gemslot;

import io.github.yanggx98.immersive.aelements.gemslot.item.GemItem;
import net.minecraft.item.ItemStack;

import java.util.Map;

public interface IEmbeddable {

    Map<GemEffectEntry, GemItem.Level> immersive_Adventure_Elements$getGemEmbededList(ItemStack stack);
}
