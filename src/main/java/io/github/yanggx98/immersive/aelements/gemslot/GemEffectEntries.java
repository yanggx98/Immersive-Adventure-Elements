package io.github.yanggx98.immersive.aelements.gemslot;

import java.util.HashMap;
import java.util.Map;

import static io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements.identifier;

public class GemEffectEntries {
    static final Map<String, GemEffectEntry> GEM_EFFECT_ENTRY_MAP = new HashMap<>();
    public static final GemEffectEntry GEM_EFFECT_ENTRY_CRITICAL_DAMAGE;
    public static final GemEffectEntry GEM_EFFECT_ENTRY_HEALTH_UPTAKE;
    public static final GemEffectEntry GEM_EFFECT_ENTRY_EXTRA_DAMAGE;
    public static final GemEffectEntry GEM_EFFECT_ENTRY_DAMAGE_ABSORPTION;

    static {
        GEM_EFFECT_ENTRY_CRITICAL_DAMAGE = register(new GemEffectEntry(identifier("gem_effect.critical_damage")));
        GEM_EFFECT_ENTRY_HEALTH_UPTAKE = register(new HealthUptakeGemEffectEntry(identifier("gem_effect.health_uptake")));
        GEM_EFFECT_ENTRY_EXTRA_DAMAGE = register(new ExtraDamageGemEffectEntry(identifier("gem_effect.extra_damage")));

        GEM_EFFECT_ENTRY_DAMAGE_ABSORPTION = register(new GemEffectEntry(identifier("gem_effect.damage_absorption")));
    }

    private static GemEffectEntry register(GemEffectEntry entry) {
        GEM_EFFECT_ENTRY_MAP.put(entry.getTranslateKey(), entry);
        return entry;
    }
}
