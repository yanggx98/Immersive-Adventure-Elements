package io.github.yanggx98.immersive.aelements.rarity;

import net.minecraft.util.Rarity;

public class ExtraRarityHelper {
    public static ExtraRarity convert(Rarity rarity){
        return switch (rarity)
        {
            case COMMON -> ExtraRarity.COMMON;
            case UNCOMMON -> ExtraRarity.SENIOR;
            case RARE -> ExtraRarity.RARE;
            case EPIC -> ExtraRarity.ARTIFACT;
        };
    }
}
