package io.github.yanggx98.immersive.aelements.gemslot;

import io.github.yanggx98.immersive.aelements.gemslot.item.GemItem;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DamageAbsorptionGemEffectEntry extends GemEffectEntry {
    public DamageAbsorptionGemEffectEntry(Identifier identifier) {
        super(identifier);
    }


    public Text getDescText(GemItem.Level level) {
        return Text.translatable(identifier.withSuffixedPath(".desc").toTranslationKey(), (int) (getAmount(level) * 100));
    }

    public float getAmount(GemItem.Level level) {
        return switch (level) {
            case LEVEL_1 -> 0.05F;
            case LEVEL_2 -> 0.08F;
            case LEVEL_3 -> 0.15F;
            case LEVEL_4 -> 0.18F;
            case LEVEL_5 -> 0.22F;
            case LEVEL_6 -> 0.30F;
        };
    }
}
