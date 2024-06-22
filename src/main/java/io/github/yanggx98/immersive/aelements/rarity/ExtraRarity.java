package io.github.yanggx98.immersive.aelements.rarity;

import net.minecraft.util.Formatting;

public enum ExtraRarity {
    COMMON(Formatting.WHITE),
    SENIOR(Formatting.BLUE),
    RARE(Formatting.DARK_PURPLE),
    ARTIFACT(Formatting.LIGHT_PURPLE),
    LEGEND(Formatting.RED),
    EPIC(Formatting.GOLD);
    public final Formatting formatting;

    ExtraRarity(Formatting formatting) {
        this.formatting = formatting;
    }
}



