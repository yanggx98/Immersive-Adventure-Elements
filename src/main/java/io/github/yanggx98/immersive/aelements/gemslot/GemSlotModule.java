package io.github.yanggx98.immersive.aelements.gemslot;

import io.github.yanggx98.immersive.aelements.ExtraSmithingRecipesHelper;
import io.github.yanggx98.immersive.aelements.IAEModule;
import io.github.yanggx98.immersive.aelements.IExtraSmithingRecipesProvider;
import io.github.yanggx98.immersive.aelements.attribute.ExtraDamageHelper;
import io.github.yanggx98.immersive.aelements.attribute.HealthUptakeHelper;
import io.github.yanggx98.immersive.aelements.gemslot.item.GemItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SmithingTemplateItem;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static io.github.yanggx98.immersive.aelements.gemslot.GemSlotItems.*;

public class GemSlotModule implements IAEModule {

    @Override
    public void onInitialize() {
        GemSlotItems.onInitialize();
        ExtraSmithingRecipesHelper.registerRecipes((SmithingTemplateItem) TRIANGLE_GEM_EMBED_TEMPLATE, new IExtraSmithingRecipesProvider() {

            @Override
            public List<Item> getBaseList(ItemStack templateItemStack) {
                return List.of(Items.STONE_SWORD);
            }

            @Override
            public List<Item> getAdditionList(ItemStack templateItemStack) {
                return List.of(
                        EXTRA_DAMAGE_GEM_COMMON, EXTRA_DAMAGE_GEM_SENIOR, EXTRA_DAMAGE_GEM_RARE, EXTRA_DAMAGE_GEM_ARTIFACT,
                        HEALTH_UPTAKE_GEM_COMMON, HEALTH_UPTAKE_GEM_SENIOR, HEALTH_UPTAKE_GEM_RARE, HEALTH_UPTAKE_GEM_ARTIFACT
                );
            }

            @Override
            public ItemStack resultItemStack(ItemStack templateItemStack, ItemStack baseItem, ItemStack additionItem) {
                if (additionItem.getItem() instanceof GemItem gemItem) {
                    return GemSlotHelper.embedGem(baseItem, gemItem);
                }
                return null;
            }
        });

        ExtraDamageHelper.registerAttributes(new ExtraDamageHelper.ExtraDamageProvider() {
            @Override
            public float getExtraDamage(PlayerEntity player, Entity entity, DamageSource source, float amount) {
                ItemStack stack = player.getEquippedStack(EquipmentSlot.MAINHAND);
                float total = 0;
                if (stack.getItem() instanceof IEmbeddable embeddable) {
                    Map<GemEffectEntry, GemItem.Level> gemSlotEntryMap = GemSlotHelper.getGemSlotEntryMap(stack);
                    for (GemEffectEntry entry : gemSlotEntryMap.keySet()) {
                        if (entry instanceof ExtraDamageGemEffectEntry extraDamageGemEffectEntry) {
                            GemItem.Level level = gemSlotEntryMap.get(entry);
                            total += extraDamageGemEffectEntry.getAmount(level);
                        }
                    }
                }
                return total;
            }
        });

        HealthUptakeHelper.registerAttributes(new HealthUptakeHelper.ExtraDamageProvider() {
            @Override
            public float getAmount(PlayerEntity player, Entity entity, DamageSource source, float amount) {
                ItemStack stack = player.getEquippedStack(EquipmentSlot.MAINHAND);
                float total = 0;
                if (stack.getItem() instanceof IEmbeddable embeddable) {
                    Map<GemEffectEntry, GemItem.Level> gemSlotEntryMap = GemSlotHelper.getGemSlotEntryMap(stack);
                    for (GemEffectEntry entry : gemSlotEntryMap.keySet()) {
                        if (entry instanceof HealthUptakeGemEffectEntry healthUptakeGemEffectEntry) {
                            GemItem.Level level = gemSlotEntryMap.get(entry);
                            total += healthUptakeGemEffectEntry.getAmount(level);
                        }
                    }
                }
                return total;
            }
        });
    }
}
