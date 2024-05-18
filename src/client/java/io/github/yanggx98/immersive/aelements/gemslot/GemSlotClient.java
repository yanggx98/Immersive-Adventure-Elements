package io.github.yanggx98.immersive.aelements.gemslot;

import io.github.yanggx98.immersive.aelements.IAEClientModule;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

import static io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements.identifier;

public class GemSlotClient implements IAEClientModule {
    @Override
    public void onClientModuleInitialize() {
        ItemTooltipCallback.EVENT.register(new ItemTooltipCallback() {

            @Override
            public void getTooltip(ItemStack stack, TooltipContext context, List<Text> lines) {
                if (stack.getItem() instanceof IEmbeddable slottableItem){
                    List<GemEffectEntry> entries = slottableItem.immersive_Adventure_Elements$getGemEmbededList(stack);
                    if (!entries.isEmpty()){
                        lines.add(Text.translatable(identifier("name.embedded").toTranslationKey()).setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
                        for (GemEffectEntry entry : entries) {
                            Text text = Text.empty().append(" ").append(Text.translatable(entry.getTranslateKey()));
                            lines.add(text);
                        }
                    }
                }
            }
        });
    }
}
