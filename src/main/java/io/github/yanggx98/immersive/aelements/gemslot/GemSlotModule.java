package io.github.yanggx98.immersive.aelements.gemslot;

import io.github.yanggx98.immersive.aelements.gemslot.item.GemEmbedTemplateItem;
import io.github.yanggx98.immersive.aelements.kaleido.ExtraAnvilRecipesHelper;
import io.github.yanggx98.immersive.aelements.kaleido.ExtraSmithingRecipesHelper;
import io.github.yanggx98.immersive.aelements.IAEModule;
import io.github.yanggx98.immersive.aelements.kaleido.IExtraSmithingRecipesProvider;
import io.github.yanggx98.immersive.aelements.attribute.DamageAbsorption;
import io.github.yanggx98.immersive.aelements.attribute.ExtraDamageHelper;
import io.github.yanggx98.immersive.aelements.attribute.HealthUptakeHelper;
import io.github.yanggx98.immersive.aelements.gemslot.item.GemItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.item.ToolItem;
import net.minecraft.screen.Property;

import java.util.Map;

import static io.github.yanggx98.immersive.aelements.gemslot.GemSlotItems.*;

public class GemSlotModule implements IAEModule {

    @Override
    public void onInitialize() {
        GemSlotBlocks.onInitialize();
        GemSlotItems.onInitialize();
//        ExtraSmithingRecipesHelper.registerRecipes((SmithingTemplateItem) TRIANGLE_GEM_EMBED_TEMPLATE, (IExtraSmithingRecipesProvider) TRIANGLE_GEM_EMBED_TEMPLATE);
//        ExtraSmithingRecipesHelper.registerRecipes((SmithingTemplateItem) SQUARE_GEM_EMBED_TEMPLATE, (IExtraSmithingRecipesProvider) SQUARE_GEM_EMBED_TEMPLATE);
        ExtraAnvilRecipesHelper.PROVIDER.register(new ExtraAnvilRecipesHelper.IExtraAnvilRecipeProvider() {
            @Override
            public ItemStack get(ItemStack stack, ItemStack stack2, Property levelCount) {
                if (stack2.getItem() instanceof GemEmbedTemplateItem templateItem) {
                    return templateItem.get(stack, stack2, levelCount);
                }
                return ItemStack.EMPTY;
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
        DamageAbsorption.registerAttributes(new DamageAbsorption.DamageAbsorptionProvider() {
            @Override
            public float getAmount(LivingEntity entity, DamageSource source, float amount) {
                float total = 0;
                ItemStack chestArmor = entity.getEquippedStack(EquipmentSlot.CHEST);
                ItemStack legsArmor = entity.getEquippedStack(EquipmentSlot.LEGS);
                if (chestArmor != ItemStack.EMPTY) {
                    if (chestArmor.getItem() instanceof IEmbeddable embeddable) {
                        Map<GemEffectEntry, GemItem.Level> gemSlotEntryMap = GemSlotHelper.getGemSlotEntryMap(chestArmor);
                        for (GemEffectEntry entry : gemSlotEntryMap.keySet()) {
                            if (entry instanceof DamageAbsorptionGemEffectEntry damageAbsorptionGemEffectEntry) {
                                GemItem.Level level = gemSlotEntryMap.get(entry);
                                total += damageAbsorptionGemEffectEntry.getAmount(level);
                            }
                        }
                    }
                }

                if (legsArmor != ItemStack.EMPTY) {
                    if (legsArmor.getItem() instanceof IEmbeddable embeddable) {
                        Map<GemEffectEntry, GemItem.Level> gemSlotEntryMap = GemSlotHelper.getGemSlotEntryMap(legsArmor);
                        for (GemEffectEntry entry : gemSlotEntryMap.keySet()) {
                            if (entry instanceof DamageAbsorptionGemEffectEntry damageAbsorptionGemEffectEntry) {
                                GemItem.Level level = gemSlotEntryMap.get(entry);
                                total += damageAbsorptionGemEffectEntry.getAmount(level);
                            }
                        }
                    }
                }
                return total;
            }
        });

    }
}
