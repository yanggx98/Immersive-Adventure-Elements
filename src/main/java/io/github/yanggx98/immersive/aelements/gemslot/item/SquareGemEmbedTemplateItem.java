package io.github.yanggx98.immersive.aelements.gemslot.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

import static io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements.identifier;

public class SquareGemEmbedTemplateItem extends GemEmbedTemplateItem {

    public SquareGemEmbedTemplateItem(Settings settings) {
        super(settings);
    }

    @Override
    public GemItem.GemType getGemSlotType() {
        return GemItem.GemType.SQUARE;
    }
}
