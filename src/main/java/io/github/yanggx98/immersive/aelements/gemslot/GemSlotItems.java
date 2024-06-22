package io.github.yanggx98.immersive.aelements.gemslot;

import io.github.yanggx98.immersive.aelements.gemslot.item.*;
import io.github.yanggx98.immersive.aelements.rarity.ExtraRarity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
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
    public static Item CRITICAL_DAMAGE_GEM_COMMON;
    public static Item CRITICAL_DAMAGE_GEM_SENIOR;
    public static Item CRITICAL_DAMAGE_GEM_RARE;
    public static Item CRITICAL_DAMAGE_GEM_ARTIFACT;

    public static Item HEALTH_UPTAKE_GEM_COMMON;
    public static Item HEALTH_UPTAKE_GEM_SENIOR;
    public static Item HEALTH_UPTAKE_GEM_RARE;
    public static Item HEALTH_UPTAKE_GEM_ARTIFACT;

    public static Item EXTRA_DAMAGE_GEM_COMMON;
    public static Item EXTRA_DAMAGE_GEM_SENIOR;
    public static Item EXTRA_DAMAGE_GEM_RARE;
    public static Item EXTRA_DAMAGE_GEM_ARTIFACT;

    public static Item DAMAGE_ABSORPTION_GEM_COMMON;
    public static Item DAMAGE_ABSORPTION_GEM_SENIOR;
    public static Item DAMAGE_ABSORPTION_GEM_RARE;
    public static Item DAMAGE_ABSORPTION_GEM_ARTIFACT;
    public static Item DAMAGE_ABSORPTION_GEM_LEGEND;
    public static Item DAMAGE_ABSORPTION_GEM_EPIC;
    public static Item EMBED_GEM_TABLE;

    public static void onInitialize() {
        EMPTY_GEM_EMBED_TEMPLATE = register("empty_gem_embed_template", new Item(new FabricItemSettings()));
        ROUND_GEM_EMBED_TEMPLATE = register("round_gem_embed_template", new RoundGemEmbedTemplateItem(new FabricItemSettings()));
        TRIANGLE_GEM_EMBED_TEMPLATE = register("triangle_gem_embed_template", new TriangleGemEmbedTemplateItem(new FabricItemSettings()));
        SQUARE_GEM_EMBED_TEMPLATE = register("square_gem_embed_template", new SquareGemEmbedTemplateItem(new FabricItemSettings()));
        OCTAGON_GEM_EMBED_TEMPLATE = register("octagon_gem_embed_template", new OctagonGemEmbedTemplateItem(new FabricItemSettings()));

        EMPTY_TRIANGLE_GEM = register("empty_triangle_gem", new Item(new FabricItemSettings()));
//        CRITICAL_DAMAGE_GEM_COMMON = register("critical_damage_gem_common", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_CRITICAL_DAMAGE, ExtraRarity.COMMON));
//        CRITICAL_DAMAGE_GEM_SENIOR = register("critical_damage_gem_senior", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_CRITICAL_DAMAGE, ExtraRarity.SENIOR));
//        CRITICAL_DAMAGE_GEM_RARE = register("critical_damage_gem_rare", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_CRITICAL_DAMAGE, ExtraRarity.RARE));
//        CRITICAL_DAMAGE_GEM_ARTIFACT = register("critical_damage_gem_artifact", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_CRITICAL_DAMAGE, ExtraRarity.ARTIFACT));

        HEALTH_UPTAKE_GEM_COMMON = register("health_uptake_gem_common", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_HEALTH_UPTAKE, GemItem.Level.LEVEL_1, GemItem.GemType.TRIANGLE));
        HEALTH_UPTAKE_GEM_SENIOR = register("health_uptake_gem_senior", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_HEALTH_UPTAKE, GemItem.Level.LEVEL_2, GemItem.GemType.TRIANGLE));
        HEALTH_UPTAKE_GEM_RARE = register("health_uptake_gem_rare", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_HEALTH_UPTAKE, GemItem.Level.LEVEL_3, GemItem.GemType.TRIANGLE));
        HEALTH_UPTAKE_GEM_ARTIFACT = register("health_uptake_gem_artifact", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_HEALTH_UPTAKE, GemItem.Level.LEVEL_4, GemItem.GemType.TRIANGLE));

        EXTRA_DAMAGE_GEM_COMMON = register("extra_damage_gem_common", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_EXTRA_DAMAGE, GemItem.Level.LEVEL_1, GemItem.GemType.TRIANGLE));
        EXTRA_DAMAGE_GEM_SENIOR = register("extra_damage_gem_senior", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_EXTRA_DAMAGE, GemItem.Level.LEVEL_2, GemItem.GemType.TRIANGLE));
        EXTRA_DAMAGE_GEM_RARE = register("extra_damage_gem_rare", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_EXTRA_DAMAGE, GemItem.Level.LEVEL_3, GemItem.GemType.TRIANGLE));
        EXTRA_DAMAGE_GEM_ARTIFACT = register("extra_damage_gem_artifact", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_EXTRA_DAMAGE, GemItem.Level.LEVEL_4, GemItem.GemType.TRIANGLE));

        DAMAGE_ABSORPTION_GEM_COMMON = register("damage_absorption_gem_common", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_DAMAGE_ABSORPTION, GemItem.Level.LEVEL_1, GemItem.GemType.SQUARE));
        DAMAGE_ABSORPTION_GEM_SENIOR = register("damage_absorption_gem_senior", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_DAMAGE_ABSORPTION, GemItem.Level.LEVEL_2, GemItem.GemType.SQUARE));
        DAMAGE_ABSORPTION_GEM_RARE = register("damage_absorption_gem_rare", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_DAMAGE_ABSORPTION, GemItem.Level.LEVEL_3, GemItem.GemType.SQUARE));
        DAMAGE_ABSORPTION_GEM_ARTIFACT = register("damage_absorption_gem_artifact", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_DAMAGE_ABSORPTION, GemItem.Level.LEVEL_4, GemItem.GemType.SQUARE));
        DAMAGE_ABSORPTION_GEM_LEGEND = register("damage_absorption_gem_legend", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_DAMAGE_ABSORPTION, GemItem.Level.LEVEL_5, GemItem.GemType.SQUARE));
        DAMAGE_ABSORPTION_GEM_EPIC = register("damage_absorption_gem_epic", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_DAMAGE_ABSORPTION, GemItem.Level.LEVEL_6, GemItem.GemType.SQUARE));
        EMBED_GEM_TABLE = register("embed_gem_table", new BlockItem(GemSlotBlocks.EMBED_GEM_TABLE, new FabricItemSettings()));
    }

    private static Item register(String id, Item entry) {
        return Registry.register(Registries.ITEM, identifier(id), entry);
    }
}
