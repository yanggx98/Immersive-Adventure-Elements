package io.github.yanggx98.immersive.aelements;

import com.google.common.collect.Lists;
import io.github.yanggx98.immersive.aelements.gemslot.GemSlotClientModule;
import io.github.yanggx98.immersive.aelements.gemslot.IEmbeddable;
import io.github.yanggx98.immersive.aelements.gemslot.item.GemItem;
import io.github.yanggx98.immersive.aelements.rarity.ExtraRarity;
import io.github.yanggx98.immersive.aelements.rarity.ExtraRarityClientModule;
import io.github.yanggx98.immersive.aelements.rarity.ExtraRarityHelper;
import io.github.yanggx98.immersive.aelements.rarity.IExtraRarityItem;
import io.github.yanggx98.immersive.aelements.tooltip.EmptyGemTooltipComponent;
import io.github.yanggx98.immersive.tooltip.TooltipHelper;
import io.github.yanggx98.immersive.tooltip.api.ItemBorderColorProvider;
import io.github.yanggx98.immersive.tooltip.api.ItemDisplayNameProvider;
import io.github.yanggx98.immersive.tooltip.api.ItemRarityNameProvider;
import io.github.yanggx98.immersive.tooltip.component.ColorBorderComponent;
import io.github.yanggx98.immersive.tooltip.component.HeaderTooltipComponent;
import io.github.yanggx98.kaleido.render.tooltip.api.TooltipComparatorProvider;
import io.github.yanggx98.kaleido.render.tooltip.api.TooltipComponentAPI;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ImmersiveAdventureElementsClient implements ClientModInitializer {
    private static final List<IAEClientModule> modules = Lists.newArrayList(new GemSlotClientModule(), new ExtraRarityClientModule());

    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        modules.forEach(IAEClientModule::onClientModuleInitialize);
        TooltipHelper.setRarityNameProvider(new ItemRarityNameProvider() {
            @Override
            public Text getRarityName(ItemStack itemStack) {
                ExtraRarity rarity = null;
                if (itemStack.getItem() instanceof IExtraRarityItem item) {
                    rarity = item.getExtraRarity();
                } else {
                    rarity = ExtraRarityHelper.convert(itemStack.getRarity());
                }
                MutableText gradeText = Text.empty().append(
                        Text.translatable(ImmersiveAdventureElements.identifier("translation.grade").toTranslationKey())
                                .formatted(Formatting.GRAY)
                );
                gradeText.append(
                        Text.translatable(
                                ImmersiveAdventureElements.identifier("grade.rarity.").withSuffixedPath(rarity.name().toLowerCase()).toTranslationKey()
                        ).formatted(rarity.formatting));
                return gradeText;
            }
        });
        TooltipHelper.setDisplayNameProvider(new ItemDisplayNameProvider() {
            @Override
            public Text getDisplayName(ItemStack itemStack) {
                ExtraRarity rarity = null;
                if (itemStack.getItem() instanceof IExtraRarityItem item) {
                    rarity = item.getExtraRarity();
                } else {
                    rarity = ExtraRarityHelper.convert(itemStack.getRarity());
                }
                return Text.empty().append(itemStack.getName()).formatted(rarity.formatting);
            }
        });
        TooltipHelper.setBorderColorProvider(new ItemBorderColorProvider() {
            @Override
            public int getItemBorderColor(ItemStack itemStack) {
                if (itemStack.getItem() instanceof IExtraRarityItem item) {
                    Integer color = item.getExtraRarity().formatting.getColorValue();
                    if (color == null) {
                        color = 0xffffffff;
                    }
                    return color;
                } else {
                    Integer color = itemStack.getRarity().formatting.getColorValue();
                    if (color == null) {
                        color = 0xffffffff;
                    }
                    return color;
                }
            }
        });
        TooltipComponentAPI.EVENT.register((list, itemStack) -> {
            int index = list.size();
            if (itemStack.getItem() instanceof IEmbeddable embeddable) {
                List<GemItem.GemType> gemTypes = embeddable.immersive_Adventure_Elements$getEmptyGemSlotList(itemStack);
                if (!gemTypes.isEmpty()) {
                    list.add(index++, TooltipComponent.of(Text.empty().asOrderedText()));
                    Identifier emptySlot = ImmersiveAdventureElements.identifier("name.empty_gem_slots");
                    list.add(index++, TooltipComponent.of(
                            Text.empty().append(Text.translatable(emptySlot.toTranslationKey()))
                                    .setStyle(Style.EMPTY.withFormatting(Formatting.GRAY))
                                    .asOrderedText()));
                }
                for (GemItem.GemType gemType : gemTypes) {
                    Identifier texture = ImmersiveAdventureElements.identifier("textures/gui/" + gemType.name().toLowerCase() + "_gem_slot.png");
                    Identifier desc = ImmersiveAdventureElements.identifier(gemType.name().toLowerCase() + "_gem_slot.desc");
                    list.add(index++, new EmptyGemTooltipComponent(texture,
                            Text.empty().append(Text.translatable(desc.toTranslationKey()))
                                    .setStyle(Style.EMPTY.withFormatting(Formatting.GRAY))
                                    .asOrderedText()));
                }
            }
        });
    }
}