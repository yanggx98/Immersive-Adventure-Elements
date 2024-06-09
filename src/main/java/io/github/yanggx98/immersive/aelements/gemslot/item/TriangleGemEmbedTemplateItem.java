package io.github.yanggx98.immersive.aelements.gemslot.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

import static io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements.identifier;
import static io.github.yanggx98.immersive.aelements.gemslot.GemSlotItems.*;
import static io.github.yanggx98.immersive.aelements.gemslot.GemSlotItems.HEALTH_UPTAKE_GEM_ARTIFACT;

public class TriangleGemEmbedTemplateItem extends GemEmbedTemplateItem {
    private static final Identifier EMPTY_SLOT_TEXTURE = identifier("item/empty_slot_triangle_gem");

    public TriangleGemEmbedTemplateItem(Text appliesToText, Text ingredientsText, Text titleText) {
        super(appliesToText, ingredientsText, titleText, GEM_EMBED_BASE_SLOT_DESCRIPTION_TEXT, GEM_EMBED_ADDITIONS_SLOT_DESCRIPTION_TEXT, getGemEmbedEmptyBaseSlotTextures(), getGemEmbedEmptyAdditionsSlotTextures());
    }

    public static TriangleGemEmbedTemplateItem createTriangleGemSlotTemplate() {
        return new TriangleGemEmbedTemplateItem(GEM_SLOT_TEMPLATE_APPLIES_TO, Text.empty(), GEM_EMBED);
    }

    protected static List<Identifier> getGemEmbedEmptyAdditionsSlotTextures() {
        return List.of(EMPTY_SLOT_TEXTURE);
    }

    @Override
    public boolean isSupportAdditionItem(ItemStack templateItemStack) {
        if (templateItemStack.getItem() instanceof GemItem gemItem) {
            return gemItem.getGemType() == GemItem.GemType.TRIANGLE;
        }
        return false;
    }

    @Override
    public boolean isSupportBaseItem(ItemStack stack) {
        return stack.getItem() instanceof ToolItem;
    }
}
