package io.github.yanggx98.immersive.aelements;

import io.github.yanggx98.immersive.aelements.item.IAEItems;
import io.github.yanggx98.kaleido.attribute.api.ILivingEntityAttributeAddition;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

import static io.github.yanggx98.immersive.aelements.attribute.IAEAttributes.MAX_MANA;
import static io.github.yanggx98.immersive.aelements.attribute.IAEAttributes.SPELL_DAMAGE;

public class ImmersiveAdventureElements implements ModInitializer {
    public static final String MOD_ID = "immersive-aelements";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM_GROUP, identifier("immersive-aelements_group"), FabricItemGroup.builder()
                .entries((displayContext, entries) -> {
                    entries.add(IAEItems.BASIC_STAFF);
                })
                .icon(() -> IAEItems.BASIC_STAFF.getDefaultStack())
                .displayName(Text.translatable(identifier("display.name").toTranslationKey()))
                .build());
        ILivingEntityAttributeAddition.EVENT.register(builder -> {
            builder.add(SPELL_DAMAGE).add(MAX_MANA);
        });
        IAEItems.onInitialize();
    }

    public static Identifier identifier(String id) {
        return new Identifier(MOD_ID, id);
    }
}