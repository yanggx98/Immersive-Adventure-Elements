package io.github.yanggx98.immersive.aelements.gemslot;

import io.github.yanggx98.immersive.aelements.IAEClientModule;
import io.github.yanggx98.immersive.aelements.gemslot.item.GemItem;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;
import java.util.Map;

import static io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements.identifier;

public class GemSlotClientModule implements IAEClientModule {
    @Override
    public void onClientModuleInitialize() {
        ItemTooltipCallback.EVENT.register(new ItemTooltipCallback() {
            @Override
            public void getTooltip(ItemStack stack, TooltipContext context, List<Text> lines) {
                if (stack.getItem() instanceof IEmbeddable slottableItem) {
                    Map<GemEffectEntry, GemItem.Level> entries = slottableItem.immersive_Adventure_Elements$getGemEmbededList(stack);
                    if (!entries.isEmpty()) {
                        lines.add(Text.translatable(identifier("name.embedded").toTranslationKey()).setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
                        for (GemEffectEntry entry : entries.keySet()) {
                            Style style = switch (entries.get(entry)) {
                                case LEVEL_1 -> Style.EMPTY.withFormatting(Formatting.GREEN);
                                case LEVEL_2 -> Style.EMPTY.withFormatting(Formatting.BLUE);
                                case LEVEL_3 -> Style.EMPTY.withFormatting(Formatting.DARK_PURPLE);
                                case LEVEL_4 -> Style.EMPTY.withFormatting(Formatting.LIGHT_PURPLE);
                                case LEVEL_5 -> Style.EMPTY.withFormatting(Formatting.GOLD);
                                case LEVEL_6 -> Style.EMPTY.withFormatting(Formatting.RED);
                            };
                            Text text = Text.empty().append("⩺ ").append(Text.translatable(entry.getTranslateKey())).setStyle(style);
                            Style descStyle = Style.EMPTY.withFormatting(Formatting.GRAY);
                            Text descText = Text.empty().append("  ").append(entry.getDescText(entries.get(entry))).setStyle(descStyle);
                            lines.add(text);
                            lines.add(descText);
                        }
                    }
                }
            }
        });
    }
}
