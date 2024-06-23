package io.github.yanggx98.immersive.aelements.gemslot;

import io.github.yanggx98.immersive.aelements.IAEClientModule;
import io.github.yanggx98.immersive.aelements.gemslot.item.GemItem;
import io.github.yanggx98.immersive.aelements.gemslot.screen.EmbedGemScreen;
import io.github.yanggx98.immersive.aelements.gemslot.screen.GemSlotScreenTypes;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
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
                    List<GemItem> gemItemList = slottableItem.immersive_Adventure_Elements$getGemEmbededList(stack);
                    if (!gemItemList.isEmpty()) {
                        lines.add(Text.translatable(identifier("name.embedded").toTranslationKey()).setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
                        for (GemItem gemItem : gemItemList) {
                            GemEffectEntry entry = gemItem.getGemEffectEntry();
                            GemItem.Level level = gemItem.getLevel();
                            Style style = switch (level) {
                                case LEVEL_1 -> Style.EMPTY.withFormatting(Formatting.GREEN);
                                case LEVEL_2 -> Style.EMPTY.withFormatting(Formatting.BLUE);
                                case LEVEL_3 -> Style.EMPTY.withFormatting(Formatting.DARK_PURPLE);
                                case LEVEL_4 -> Style.EMPTY.withFormatting(Formatting.LIGHT_PURPLE);
                                case LEVEL_5 -> Style.EMPTY.withFormatting(Formatting.RED);
                                case LEVEL_6 -> Style.EMPTY.withFormatting(Formatting.GOLD);
                            };
                            Text text = Text.empty().append("⩺ ").append(Text.translatable(entry.getTranslateKey())).setStyle(style);
                            Style descStyle = Style.EMPTY.withFormatting(Formatting.GRAY);
                            Text descText = Text.empty().append("  ").append(entry.getDescText(level)).setStyle(descStyle);
                            lines.add(text);
                            lines.add(descText);
                        }
                    }
                }
            }
        });
        HandledScreens.register(GemSlotScreenTypes.EMBED_GEM, EmbedGemScreen::new);
    }
}
