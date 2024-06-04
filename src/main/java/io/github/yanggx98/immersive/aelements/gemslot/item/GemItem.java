package io.github.yanggx98.immersive.aelements.gemslot.item;

import com.google.common.collect.Lists;
import io.github.yanggx98.immersive.aelements.gemslot.GemEffectEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

import java.util.List;

public class GemItem extends Item {
    private final GemEffectEntry effectEntry;

    public GemItem(GemEffectEntry effectEntry) {
        super(new Settings());
        this.effectEntry = effectEntry;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return switch (effectEntry.getLevel()) {
            case LEVEL_1, LEVEL_2 -> Rarity.COMMON;
            case LEVEL_3 -> Rarity.UNCOMMON;
            case LEVEL_4 -> Rarity.RARE;
            case LEVEL_5 -> Rarity.EPIC;
        };
    }

    public GemEffectEntry getGemEffectEntry() {
        return effectEntry;
    }
}
