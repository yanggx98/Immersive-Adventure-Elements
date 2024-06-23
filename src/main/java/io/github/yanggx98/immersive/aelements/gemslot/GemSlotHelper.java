package io.github.yanggx98.immersive.aelements.gemslot;

import io.github.yanggx98.immersive.aelements.gemslot.item.GemEmbedTemplateItem;
import io.github.yanggx98.immersive.aelements.gemslot.item.GemItem;
import io.github.yanggx98.immersive.aelements.gemslot.screen.EmbedGemTableScreenHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.*;

public class GemSlotHelper {
    private static final String NBT_GEM_SLOT_ID = "iae.slot.gem";
    private static final String NBT_GEM_EMPTY_SLOT_ID = "iae.slot.empty";

    public static ItemStack embedGem(EmbedGemTableScreenHandler.EmbeddableItem embeddableItem, ItemStack stack, GemItem gemItem) {
        GemItem.GemType gemType = embeddableItem.gemType;
        if (gemType != gemItem.getGemType()) {
            return ItemStack.EMPTY;
        }
        if (embeddableItem instanceof EmbedGemTableScreenHandler.EmbeddableItem.EmptyEmbeddableItem emptyEmbeddableItem) {
            if (stack.getItem() instanceof IEmbeddable embeddable) {
                NbtCompound rootCompound = stack.getOrCreateNbt();
                NbtList SlotNbtCompound = null;
                if (rootCompound.contains(NBT_GEM_EMPTY_SLOT_ID)) {
                    SlotNbtCompound = rootCompound.getList(NBT_GEM_EMPTY_SLOT_ID, NbtElement.INT_TYPE);
                } else {
                    return ItemStack.EMPTY;
                }
                int removeIndex = -1;
                for (int i = 0; i < SlotNbtCompound.size(); i++) {
                    NbtElement element = SlotNbtCompound.get(i);
                    NbtInt nbtInt = (NbtInt) element;
                    if (nbtInt.intValue() == gemType.value) {
                        removeIndex = i;
                        break;
                    }
                }
                if (removeIndex != -1) {
                    SlotNbtCompound.remove(removeIndex);
                } else {
                    return ItemStack.EMPTY;
                }
            }
        } else if (embeddableItem instanceof EmbedGemTableScreenHandler.EmbeddableItem.GemEmbeddableItem gemEmbeddableItem) {
            if (stack.getItem() instanceof IEmbeddable embeddable) {
                NbtCompound rootCompound = stack.getOrCreateNbt();
                NbtList SlotNbtCompound = null;
                if (rootCompound.contains(NBT_GEM_SLOT_ID)) {
                    SlotNbtCompound = rootCompound.getList(NBT_GEM_SLOT_ID, NbtElement.STRING_TYPE);
                } else {
                    return ItemStack.EMPTY;
                }
                int removeIndex = -1;
                for (int i = 0; i < SlotNbtCompound.size(); i++) {
                    NbtElement element = SlotNbtCompound.get(i);
                    String key = gemEmbeddableItem.item.getRegistryEntry().registryKey().getValue().toString();
                    if (element.asString().equals(key)) {
                        removeIndex = i;
                        break;
                    }
                }
                if (removeIndex != -1) {
                    SlotNbtCompound.remove(removeIndex);
                } else {
                    return ItemStack.EMPTY;
                }
            }
        }
        return embedGem(stack, gemItem);

    }

    public static ItemStack embedGem(ItemStack stack, GemItem item) {
        ItemStack temp_stack = stack.copy();
        NbtCompound rootCompound = temp_stack.getOrCreateNbt();
        NbtList slotNbtCompound = null;

        if (rootCompound.contains(NBT_GEM_SLOT_ID)) {
            slotNbtCompound = rootCompound.getList(NBT_GEM_SLOT_ID, NbtElement.STRING_TYPE);
        } else {
            slotNbtCompound = new NbtList();
            rootCompound.put(NBT_GEM_SLOT_ID, slotNbtCompound);
        }
        String key = item.getRegistryEntry().registryKey().getValue().toString();
        slotNbtCompound.add(NbtString.of(key));
        return temp_stack;
    }

    public static List<GemItem> getGemItemList(ItemStack stack) {
        NbtCompound rootCompound = stack.getOrCreateNbt();
        List<GemItem> gemItemList = new ArrayList<>();
        if (rootCompound.contains(NBT_GEM_SLOT_ID)) {
            NbtList slotNbtCompound = rootCompound.getList(NBT_GEM_SLOT_ID, NbtElement.STRING_TYPE);
            for (NbtElement element : slotNbtCompound) {
                Item item = Registries.ITEM.get(new Identifier(element.asString()));
                if (item instanceof GemItem gemItem) {
                    gemItemList.add(gemItem);
                }
            }
        }
        return gemItemList;
    }


    private static GemItem.Level getLevel(int value) {
        return switch (value) {
            case 2 -> GemItem.Level.LEVEL_2;
            case 3 -> GemItem.Level.LEVEL_3;
            case 4 -> GemItem.Level.LEVEL_4;
            case 5 -> GemItem.Level.LEVEL_5;
            case 6 -> GemItem.Level.LEVEL_6;
            default -> GemItem.Level.LEVEL_1;
        };
    }

    public static void openSlot(ItemStack stack, GemEmbedTemplateItem templateItem) {
        GemItem.GemType gemType = templateItem.getGemSlotType();
        if (stack.getItem() instanceof IEmbeddable embeddable) {
            List<GemItem.GemType> slots = embeddable.immersive_Adventure_Elements$canOpenSlot(stack);
            GemItem.GemType slotType = templateItem.getGemSlotType();
            if (slots.contains(slotType)) ;
            {
                NbtCompound rootCompound = stack.getOrCreateNbt();
                NbtList SlotNbtCompound = null;
                if (rootCompound.contains(NBT_GEM_EMPTY_SLOT_ID)) {
                    SlotNbtCompound = rootCompound.getList(NBT_GEM_EMPTY_SLOT_ID, NbtElement.INT_TYPE);
                } else {
                    SlotNbtCompound = new NbtList();
                    rootCompound.put(NBT_GEM_EMPTY_SLOT_ID, SlotNbtCompound);
                }
                NbtInt nbtInt = NbtInt.of(gemType.value);
                SlotNbtCompound.add(nbtInt);
            }
        }
    }


    public static List<GemItem.GemType> getEmptyGemSlotList(ItemStack stack) {
        List<GemItem.GemType> slotList = new ArrayList<>();
        NbtCompound rootCompound = stack.getOrCreateNbt();
        HashMap<GemEffectEntry, GemItem.Level> gemEffectMap = new HashMap<>();
        if (rootCompound.contains(NBT_GEM_EMPTY_SLOT_ID)) {
            NbtList gemSlotNbtCompound = rootCompound.getList(NBT_GEM_EMPTY_SLOT_ID, NbtElement.INT_TYPE);
            for (int i = 0; i < gemSlotNbtCompound.size(); i++) {
                int enumValue = gemSlotNbtCompound.getInt(i);
                GemItem.GemType type = GemItem.GemType.get(enumValue);
                if (type != null) {
                    slotList.add(type);
                }
            }
        }
        return slotList;
    }
}
