package io.github.yanggx98.immersive.aelements.gemslot;

import java.util.HashMap;
import java.util.Map;

import static io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements.identifier;

public class GemEffectEntries {
    static final Map<String, GemEffectEntry> GEM_EFFECT_ENTRY_MAP = new HashMap<>();
    public static final GemEffectEntry GEM_EFFECT_ENTRY_CRITICAL_DAMAGE_LEVEL_1 = new GemEffectEntry(identifier("gem_effect.critical_damage_level_1"), GemEffectEntry.LEVEL.LEVEL_1);
    public static final GemEffectEntry GEM_EFFECT_ENTRY_CRITICAL_DAMAGE_LEVEL_2 = new GemEffectEntry(identifier("gem_effect.critical_damage_level_2"), GemEffectEntry.LEVEL.LEVEL_2);
    public static final GemEffectEntry GEM_EFFECT_ENTRY_CRITICAL_DAMAGE_LEVEL_3 = new GemEffectEntry(identifier("gem_effect.critical_damage_level_3"), GemEffectEntry.LEVEL.LEVEL_3);

    public static void onInitialize() {
        register(GEM_EFFECT_ENTRY_CRITICAL_DAMAGE_LEVEL_1.getTranslateKey(), GEM_EFFECT_ENTRY_CRITICAL_DAMAGE_LEVEL_1);
    }

    public static void register(String id, GemEffectEntry entry) {
        GEM_EFFECT_ENTRY_MAP.put(id, entry);
    }
}
