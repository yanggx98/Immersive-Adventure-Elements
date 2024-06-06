package io.github.yanggx98.immersive.aelements.gemslot;

import io.github.yanggx98.immersive.aelements.ExtraSmithingRecipesHelper;
import io.github.yanggx98.immersive.aelements.IAEModule;
import io.github.yanggx98.immersive.aelements.IExtraSmithingRecipesProvider;
import io.github.yanggx98.immersive.aelements.attribute.ExtraDamageHelper;
import io.github.yanggx98.immersive.aelements.attribute.HealthUptakeHelper;
import io.github.yanggx98.immersive.aelements.gemslot.item.GemItem;
import io.github.yanggx98.immersive.aelements.gemslot.item.RoundGemEmbedTemplateItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SmithingTemplateItem;

import java.util.List;

import static io.github.yanggx98.immersive.aelements.gemslot.GemSlotItems.*;

public class GemSlotModule implements IAEModule {

    @Override
    public void onInitialize() {
        GemEffectEntries.onInitialize();
        GemSlotItems.onInitialize();
        ExtraSmithingRecipesHelper.registerRecipes((SmithingTemplateItem) TRIANGLE_GEM_EMBED_TEMPLATE, new IExtraSmithingRecipesProvider() {

            @Override
            public List<Item> getBaseList(ItemStack templateItemStack) {
                return List.of(Items.STONE_SWORD);
            }

            @Override
            public List<Item> getAdditionList(ItemStack templateItemStack) {
                return List.of(EXTRA_DAMAGE_GEM_LEVEL_1, EXTRA_DAMAGE_GEM_LEVEL_2, EXTRA_DAMAGE_GEM_LEVEL_3, HEALTH_UPTAKE_GEM_LEVEL_1, HEALTH_UPTAKE_GEM_LEVEL_2, HEALTH_UPTAKE_GEM_LEVEL_3);
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
                    List<GemEffectEntry> gemSlotEntries = GemSlotHelper.getGemSlotEntryList(stack);
                    for (GemEffectEntry entry : gemSlotEntries) {
                        if (entry instanceof ExtraDamageGemEffectEntry extraDamageGemEffectEntry) {
                            total += extraDamageGemEffectEntry.getExtraDamage();
                        }
                    }
                }
                return total;
            }
        });

        HealthUptakeHelper.registerAttributes(new HealthUptakeHelper.ExtraDamageProvider(){
            @Override
            public float getAmount(PlayerEntity player, Entity entity, DamageSource source, float amount) {
                ItemStack stack = player.getEquippedStack(EquipmentSlot.MAINHAND);
                float total = 0;
                if (stack.getItem() instanceof IEmbeddable embeddable) {
                    List<GemEffectEntry> gemSlotEntries = GemSlotHelper.getGemSlotEntryList(stack);
                    for (GemEffectEntry entry : gemSlotEntries) {
                        if (entry instanceof HealthUptakeGemEffectEntry healthUptakeGemEffectEntry) {
                            total += healthUptakeGemEffectEntry.getAmount();
                        }
                    }
                }
                return total;
            }
        });
    }
}
