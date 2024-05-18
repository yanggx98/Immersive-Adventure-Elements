package io.github.yanggx98.immersive.aelements.gemslot.item;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

import static io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements.identifier;

public class RoundGemEmbedTemplateItem extends GemEmbedTemplateItem{
    private static final Identifier EMPTY_SLOT_TEXTURE = identifier("item/empty_slot_round_gem");
    private RoundGemEmbedTemplateItem(Text appliesToText, Text ingredientsText, Text titleText) {
        super(appliesToText, ingredientsText, titleText,GEM_EMBED_BASE_SLOT_DESCRIPTION_TEXT,GEM_EMBED_ADDITIONS_SLOT_DESCRIPTION_TEXT,getGemEmbedEmptyBaseSlotTextures(),getGemEmbedEmptyAdditionsSlotTextures());
    }
    public static RoundGemEmbedTemplateItem createRoundGemSlotTemplate() {
        return new RoundGemEmbedTemplateItem(GEM_SLOT_TEMPLATE_APPLIES_TO, Text.empty(), GEM_EMBED);
    }
    protected static List<Identifier> getGemEmbedEmptyAdditionsSlotTextures() {
        return List.of(EMPTY_SLOT_TEXTURE);
    }
}
