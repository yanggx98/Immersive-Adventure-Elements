package io.github.yanggx98.immersive.aelements.gemslot.item;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

import static io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements.identifier;

public class RoundGemEmbedTemplateItem extends GemEmbedTemplateItem{

    public RoundGemEmbedTemplateItem(Settings settings) {
        super(settings);
    }

    @Override
    public GemItem.GemType getGemSlotType() {
        return GemItem.GemType.ROUND;
    }
}
