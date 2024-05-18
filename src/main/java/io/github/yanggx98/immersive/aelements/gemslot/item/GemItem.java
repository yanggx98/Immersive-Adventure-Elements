package io.github.yanggx98.immersive.aelements.gemslot.item;

import com.google.common.collect.Lists;
import io.github.yanggx98.immersive.aelements.gemslot.GemEffectEntry;
import net.minecraft.item.Item;

import java.util.List;

public class GemItem extends Item{
    private final GemEffectEntry effectEntry;

    public GemItem(GemEffectEntry effectEntry) {
        super(new Item.Settings());
        this.effectEntry = effectEntry;
    }

    public GemEffectEntry getGemEffectEntry(){
        return effectEntry;
    }
}
