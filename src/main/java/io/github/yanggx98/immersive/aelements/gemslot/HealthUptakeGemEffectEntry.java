package io.github.yanggx98.immersive.aelements.gemslot;

import net.minecraft.util.Identifier;

public class HealthUptakeGemEffectEntry extends GemEffectEntry {
    public HealthUptakeGemEffectEntry(Identifier identifier, LEVEL level) {
        super(identifier, level);
    }

    public float getAmount() {
        return switch (getLevel()) {
            case LEVEL_1 -> 0.1f;
            case LEVEL_2 -> 0.2f;
            case LEVEL_3 -> 0.3f;
            case LEVEL_4 -> 0.4f;
            case LEVEL_5 -> 0.5f;
        };
    }
}
