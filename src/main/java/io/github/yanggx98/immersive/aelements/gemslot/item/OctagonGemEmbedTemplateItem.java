package io.github.yanggx98.immersive.aelements.gemslot.item;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

import static io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements.identifier;

public class OctagonGemEmbedTemplateItem extends GemEmbedTemplateItem {

    public OctagonGemEmbedTemplateItem(Settings settings) {
        super(settings);
    }

    @Override
    public GemItem.GemType getGemSlotType() {
        return GemItem.GemType.OCTAGON;
    }
}
