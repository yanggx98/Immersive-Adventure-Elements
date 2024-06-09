package io.github.yanggx98.immersive.aelements.gemslot.item;

import io.github.yanggx98.immersive.aelements.IExtraSmithingRecipesProvider;
import io.github.yanggx98.immersive.aelements.gemslot.GemSlotHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.Util;

import java.util.ArrayList;
import java.util.List;

import static io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements.identifier;

public class GemEmbedTemplateItem extends SmithingTemplateItem implements IExtraSmithingRecipesProvider {
    private static final Identifier EMPTY_ARMOR_SLOT_HELMET_TEXTURE;
    private static final Identifier EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE;
    private static final Identifier EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE;
    private static final Identifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE;
    private static final Identifier EMPTY_SLOT_HOE_TEXTURE;
    private static final Identifier EMPTY_SLOT_AXE_TEXTURE;
    private static final Identifier EMPTY_SLOT_SWORD_TEXTURE;
    private static final Identifier EMPTY_SLOT_SHOVEL_TEXTURE;
    private static final Identifier EMPTY_SLOT_PICKAXE_TEXTURE;
    private static final Identifier EMPTY_SLOT_INGOT_TEXTURE;
    protected static final Text GEM_EMBED = Text.translatable(Util.createTranslationKey("embed", identifier("gem_embed"))).formatted(Formatting.GRAY);
    protected static final Text GEM_SLOT_TEMPLATE_APPLIES_TO = Text.translatable(Util.createTranslationKey("item", identifier("gem_embed_template.gem_embed.applies_to"))).formatted(Formatting.BLUE);

    protected static final Text GEM_EMBED_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", identifier("gem_embed_template.round_gem_embed.base_slot_description")));
    protected static final Text GEM_EMBED_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", identifier("gem_embed_template.round_gem_embed.additions_slot_description")));

    protected GemEmbedTemplateItem(Text appliesToText, Text ingredientsText, Text titleText, Text baseSlotDescriptionText, Text additionsSlotDescriptionText, List<Identifier> emptyBaseSlotTextures, List<Identifier> emptyAdditionsSlotTextures) {
        super(appliesToText, ingredientsText, titleText, baseSlotDescriptionText, additionsSlotDescriptionText, emptyBaseSlotTextures, emptyAdditionsSlotTextures);
    }


    protected static List<Identifier> getGemEmbedEmptyBaseSlotTextures() {
        return List.of(EMPTY_ARMOR_SLOT_HELMET_TEXTURE, EMPTY_SLOT_SWORD_TEXTURE, EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE, EMPTY_SLOT_PICKAXE_TEXTURE, EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE, EMPTY_SLOT_AXE_TEXTURE, EMPTY_ARMOR_SLOT_BOOTS_TEXTURE, EMPTY_SLOT_HOE_TEXTURE, EMPTY_SLOT_SHOVEL_TEXTURE);
    }

    protected static List<Identifier> getGemEmbedEmptyAdditionsSlotTextures() {
        return List.of(EMPTY_SLOT_INGOT_TEXTURE);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.UNCOMMON;
    }

    static {
        EMPTY_ARMOR_SLOT_HELMET_TEXTURE = new Identifier("item/empty_armor_slot_helmet");
        EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = new Identifier("item/empty_armor_slot_chestplate");
        EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = new Identifier("item/empty_armor_slot_leggings");
        EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = new Identifier("item/empty_armor_slot_boots");
        EMPTY_SLOT_HOE_TEXTURE = new Identifier("item/empty_slot_hoe");
        EMPTY_SLOT_AXE_TEXTURE = new Identifier("item/empty_slot_axe");
        EMPTY_SLOT_SWORD_TEXTURE = new Identifier("item/empty_slot_sword");
        EMPTY_SLOT_SHOVEL_TEXTURE = new Identifier("item/empty_slot_shovel");
        EMPTY_SLOT_PICKAXE_TEXTURE = new Identifier("item/empty_slot_pickaxe");
        EMPTY_SLOT_INGOT_TEXTURE = new Identifier("item/empty_slot_ingot");
    }

    @Override
    public boolean isSupportBaseItem(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isSupportAdditionItem(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack resultItemStack(ItemStack templateItemStack, ItemStack baseItem, ItemStack additionItem) {
        if (additionItem.getItem() instanceof GemItem gemItem) {
            return GemSlotHelper.embedGem(baseItem, gemItem);
        }
        return null;
    }
}
