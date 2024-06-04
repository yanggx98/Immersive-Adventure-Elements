package io.github.yanggx98.immersive.aelements;

import com.google.common.collect.Lists;
import io.github.yanggx98.immersive.aelements.gemslot.GemSlotItems;
import io.github.yanggx98.immersive.aelements.gemslot.GemSlotModule;
import io.github.yanggx98.kaleido.attribute.api.ILivingEntityAttributeAddition;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Supplier;

public class ImmersiveAdventureElements implements ModInitializer {
    public static final String MOD_ID = "immersive-aelements";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static final List<IAEModule> modules = Lists.newArrayList(new GemSlotModule());
    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM_GROUP, identifier("immersive-aelements_group"), FabricItemGroup.builder()
                .entries((displayContext, entries) -> {
                    entries.add(GemSlotItems.EMPTY_GEM_EMBED_TEMPLATE);
                    entries.add(GemSlotItems.ROUND_GEM_EMBED_TEMPLATE);
                    entries.add(GemSlotItems.TRIANGLE_GEM_EMBED_TEMPLATE);
                    entries.add(GemSlotItems.SQUARE_GEM_EMBED_TEMPLATE);
                    entries.add(GemSlotItems.OCTAGON_GEM_EMBED_TEMPLATE);
                    entries.add(GemSlotItems.CRITICAL_DAMAGE_GEM_LEVEL_1);
                    entries.add(GemSlotItems.CRITICAL_DAMAGE_GEM_LEVEL_2);
                    entries.add(GemSlotItems.CRITICAL_DAMAGE_GEM_LEVEL_3);
                    entries.add(GemSlotItems.EXTRA_DAMAGE_GEM_LEVEL_1);
                    entries.add(GemSlotItems.EXTRA_DAMAGE_GEM_LEVEL_2);
                    entries.add(GemSlotItems.EXTRA_DAMAGE_GEM_LEVEL_3);
                    entries.add(GemSlotItems.HEALTH_UPTAKE_GEM_LEVEL_1);
                    entries.add(GemSlotItems.HEALTH_UPTAKE_GEM_LEVEL_2);
                    entries.add(GemSlotItems.HEALTH_UPTAKE_GEM_LEVEL_3);
                })
                .displayName(Text.translatable(identifier("display.name").toTranslationKey())).icon(() -> GemSlotItems.EMPTY_GEM_EMBED_TEMPLATE.getDefaultStack())
                .build());
        ILivingEntityAttributeAddition.EVENT.register(builder -> {

        });
        modules.forEach(IAEModule::onInitialize);

    }

    public static Identifier identifier(String id) {
        return new Identifier(MOD_ID, id);
    }
}