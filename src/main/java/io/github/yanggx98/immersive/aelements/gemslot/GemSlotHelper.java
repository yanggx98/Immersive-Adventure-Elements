package io.github.yanggx98.immersive.aelements.gemslot;

import io.github.yanggx98.immersive.aelements.gemslot.item.GemEmbedTemplateItem;
import io.github.yanggx98.immersive.aelements.gemslot.item.GemItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;

import java.util.*;

public class GemSlotHelper {
    private static final String NBT_GEM_SLOT_ID = "iae.slot.gem";
    private static final String NBT_GEM_EMPTY_SLOT_ID = "iae.slot.empty";

    public static ItemStack embedGem(ItemStack stack, GemItem item) {
        ItemStack temp_stack = stack.copy();
        NbtCompound rootCompound = temp_stack.getOrCreateNbt();
        NbtCompound gemSlotNbtCompound = null;
        if (rootCompound.contains(NBT_GEM_SLOT_ID)) {
            gemSlotNbtCompound = rootCompound.getCompound(NBT_GEM_SLOT_ID);
        } else {
            gemSlotNbtCompound = new NbtCompound();
            rootCompound.put(NBT_GEM_SLOT_ID, gemSlotNbtCompound);
        }
        gemSlotNbtCompound.putInt(item.getGemEffectEntry().getTranslateKey(), item.getLevel().value);
        return temp_stack;
    }

    public static Map<GemEffectEntry, GemItem.Level> getGemSlotEntryMap(ItemStack stack) {
        NbtCompound rootCompound = stack.getOrCreateNbt();
        HashMap<GemEffectEntry, GemItem.Level> gemEffectMap = new HashMap<>();
        if (rootCompound.contains(NBT_GEM_SLOT_ID)) {
            NbtCompound gemSlotNbtCompound = rootCompound.getCompound(NBT_GEM_SLOT_ID);
            for (String key : gemSlotNbtCompound.getKeys()) {
                GemEffectEntry entry = GemEffectEntries.GEM_EFFECT_ENTRY_MAP.get(key);
                int value = gemSlotNbtCompound.getInt(key);
                gemEffectMap.put(entry, getLevel(value));
            }
        }
        return gemEffectMap;
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
