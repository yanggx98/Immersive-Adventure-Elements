package io.github.yanggx98.immersive.aelements.gemslot;

import io.github.yanggx98.immersive.aelements.gemslot.item.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements.identifier;

public class GemSlotItems {

    public static Item EMPTY_GEM_EMBED_TEMPLATE;
    public static Item ROUND_GEM_EMBED_TEMPLATE;
    public static Item TRIANGLE_GEM_EMBED_TEMPLATE;
    public static Item SQUARE_GEM_EMBED_TEMPLATE;
    public static Item OCTAGON_GEM_EMBED_TEMPLATE;

    public static Item EMPTY_TRIANGLE_GEM;
    public static Item CRITICAL_DAMAGE_GEM_LEVEL_1;
    public static Item CRITICAL_DAMAGE_GEM_LEVEL_2;
    public static Item CRITICAL_DAMAGE_GEM_LEVEL_3;

    public static Item HEALTH_UPTAKE_GEM_LEVEL_1;
    public static Item HEALTH_UPTAKE_GEM_LEVEL_2;
    public static Item HEALTH_UPTAKE_GEM_LEVEL_3;

    public static Item EXTRA_DAMAGE_GEM_LEVEL_1;
    public static Item EXTRA_DAMAGE_GEM_LEVEL_2;
    public static Item EXTRA_DAMAGE_GEM_LEVEL_3;

    public static void onInitialize(){
        EMPTY_GEM_EMBED_TEMPLATE = register("empty_gem_embed_template", new Item(new FabricItemSettings()));
        ROUND_GEM_EMBED_TEMPLATE = register("round_gem_embed_template", RoundGemEmbedTemplateItem.createRoundGemSlotTemplate());
        TRIANGLE_GEM_EMBED_TEMPLATE = register("triangle_gem_embed_template", TriangleGemEmbedTemplateItem.createTriangleGemSlotTemplate());
        SQUARE_GEM_EMBED_TEMPLATE = register("square_gem_embed_template", SquareGemEmbedTemplateItem.createSquareGemSlotTemplate());
        OCTAGON_GEM_EMBED_TEMPLATE = register("octagon_gem_embed_template", OctagonGemEmbedTemplateItem.createOctagonGemSlotTemplate());

        EMPTY_TRIANGLE_GEM = register("empty_triangle_gem", new Item(new FabricItemSettings()));
        CRITICAL_DAMAGE_GEM_LEVEL_1 = register("critical_damage_gem_level_1", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_CRITICAL_DAMAGE_LEVEL_1));
        CRITICAL_DAMAGE_GEM_LEVEL_2 = register("critical_damage_gem_level_2", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_CRITICAL_DAMAGE_LEVEL_2));
        CRITICAL_DAMAGE_GEM_LEVEL_3 = register("critical_damage_gem_level_3", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_CRITICAL_DAMAGE_LEVEL_3));

        HEALTH_UPTAKE_GEM_LEVEL_1 = register("health_uptake_gem_level_1", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_HEALTH_UPTAKE_LEVEL_1));
        HEALTH_UPTAKE_GEM_LEVEL_2 = register("health_uptake_gem_level_2", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_HEALTH_UPTAKE_LEVEL_2));
        HEALTH_UPTAKE_GEM_LEVEL_3 = register("health_uptake_gem_level_3", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_HEALTH_UPTAKE_LEVEL_3));

        EXTRA_DAMAGE_GEM_LEVEL_1 = register("extra_damage_gem_level_1", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_EXTRA_DAMAGE_LEVEL_1));
        EXTRA_DAMAGE_GEM_LEVEL_2 = register("extra_damage_gem_level_2", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_EXTRA_DAMAGE_LEVEL_2));
        EXTRA_DAMAGE_GEM_LEVEL_3 = register("extra_damage_gem_level_3", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_EXTRA_DAMAGE_LEVEL_3));
    }
    private static Item register(String id,Item entry){
        return Registry.register(Registries.ITEM,identifier(id),entry);
    }
}
