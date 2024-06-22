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

    public TriangleGemEmbedTemplateItem(Settings settings) {
        super(settings);
    }

    @Override
    public GemItem.GemType getGemSlotType() {
        return GemItem.GemType.TRIANGLE;
    }
}
