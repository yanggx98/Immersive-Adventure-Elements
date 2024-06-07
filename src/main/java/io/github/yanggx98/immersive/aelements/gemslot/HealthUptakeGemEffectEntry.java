package io.github.yanggx98.immersive.aelements.gemslot;

import io.github.yanggx98.immersive.aelements.gemslot.item.GemItem;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class HealthUptakeGemEffectEntry extends GemEffectEntry {
    public HealthUptakeGemEffectEntry(Identifier identifier) {
        super(identifier);
    }

    @Override
    public Text getDescText(GemItem.Level level) {
        return Text.translatable(identifier.withSuffixedPath(".desc").toTranslationKey(), (int) (getAmount(level) * 100));
    }

    public float getAmount(GemItem.Level level) {
        return switch (level) {
            case LEVEL_1 -> 0.1F;
            case LEVEL_2 -> 0.2F;
            case LEVEL_3 -> 0.35F;
            case LEVEL_4 -> 0.45F;
            case LEVEL_5 -> 0.60F;
            case LEVEL_6 -> 0.80F;
        };
    }
}
