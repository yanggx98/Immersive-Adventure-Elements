package io.github.yanggx98.immersive.aelements.gemslot;

import io.github.yanggx98.immersive.aelements.gemslot.item.GemItem;
import io.github.yanggx98.immersive.aelements.gemslot.item.RoundGemEmbedTemplateItem;
import io.github.yanggx98.immersive.aelements.gemslot.item.SquareGemEmbedTemplateItem;
import io.github.yanggx98.immersive.aelements.gemslot.item.TriangleGemEmbedTemplateItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements.identifier;

public class GemSlotItems {

    public static Item ROUND_GEM_EMBED_TEMPLATE;
    public static Item TRIANGLE_GEM_EMBED_TEMPLATE;
    public static Item SQUARE_GEM_EMBED_TEMPLATE;

    public static Item CRITICAL_DAMAGE_GEM_LEVEL_1;
    public static void onInitialize(){
        ROUND_GEM_EMBED_TEMPLATE = register("round_gem_embed_template", RoundGemEmbedTemplateItem.createRoundGemSlotTemplate());
        TRIANGLE_GEM_EMBED_TEMPLATE = register("triangle_gem_embed_template", TriangleGemEmbedTemplateItem.createTriangleGemSlotTemplate());
        SQUARE_GEM_EMBED_TEMPLATE = register("square_gem_embed_template", SquareGemEmbedTemplateItem.createSquareGemSlotTemplate());
        CRITICAL_DAMAGE_GEM_LEVEL_1 = register("critical_damage_gem_level_1", new GemItem(GemEffectEntries.GEM_EFFECT_ENTRY_CRITICAL_DAMAGE_LEVEL_1));
    }
    private static Item register(String id,Item entry){
        return Registry.register(Registries.ITEM,identifier(id),entry);
    }
}
