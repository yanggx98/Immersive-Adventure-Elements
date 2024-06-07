package io.github.yanggx98.immersive.aelements.gemslot.item;

import com.google.common.collect.Lists;
import io.github.yanggx98.immersive.aelements.gemslot.GemEffectEntry;
import io.github.yanggx98.immersive.aelements.rarity.ExtraRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

import java.util.List;

public class GemItem extends Item {
    private final GemEffectEntry effectEntry;
    private final Level level;

    public GemItem(GemEffectEntry effectEntry, Level level) {
        super(new Settings());
        this.effectEntry = effectEntry;
        this.level = level;
    }

    public GemEffectEntry getGemEffectEntry() {
        return effectEntry;
    }


    @Override
    public Rarity getRarity(ItemStack stack) {
        return switch (level){
            case LEVEL_1 -> Rarity.COMMON;
            case LEVEL_2 -> Rarity.UNCOMMON;
            case LEVEL_3 -> Rarity.RARE;
            case LEVEL_4,LEVEL_5,LEVEL_6 -> Rarity.EPIC;
        };
    }

    public Level getLevel() {
        return level;
    }


    public enum Level {
        LEVEL_1(1), LEVEL_2(2), LEVEL_3(3), LEVEL_4(4), LEVEL_5(5), LEVEL_6(6);

        public final int value;

        Level(int value) {
            this.value = value;
        }

    }
}
