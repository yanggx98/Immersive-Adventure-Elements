package io.github.yanggx98.immersive.aelements.gemslot;

import io.github.yanggx98.immersive.aelements.gemslot.item.GemItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GemSlotHelper {
    private static final String NBT_GEM_SLOT_ID = "iae.slot.gem";
    private static final String NBT_GEM_SLOT_LIST_ID = "gem_list";
    public static ItemStack embedGem(ItemStack stack, GemItem item){
        ItemStack temp_stack = stack.copy();
        NbtCompound rootCompound = temp_stack.getOrCreateNbt();
        NbtCompound gemSlotNbtCompound = null;
        if (rootCompound.contains(NBT_GEM_SLOT_ID)) {
            gemSlotNbtCompound = rootCompound.getCompound(NBT_GEM_SLOT_ID);
        }else{
            gemSlotNbtCompound = new NbtCompound();
            rootCompound.put(NBT_GEM_SLOT_ID,gemSlotNbtCompound);
        }
        NbtList effectList = new NbtList();
        if (gemSlotNbtCompound.contains(NBT_GEM_SLOT_LIST_ID,NbtElement.LIST_TYPE)){
            effectList = gemSlotNbtCompound.getList(NBT_GEM_SLOT_LIST_ID,NbtElement.STRING_TYPE);
        }else {
            gemSlotNbtCompound.put(NBT_GEM_SLOT_LIST_ID,effectList);
        }
        effectList.add(NbtString.of(item.getGemEffectEntry().getTranslateKey()));
        return temp_stack;
    }
    public static List<GemEffectEntry> getGemSlotEntryList(ItemStack stack){
        NbtCompound rootCompound = stack.getOrCreateNbt();
        if (rootCompound.contains(NBT_GEM_SLOT_ID)) {
            NbtCompound gemSlotNbtCompound = rootCompound.getCompound(NBT_GEM_SLOT_ID);
            if(gemSlotNbtCompound.contains(NBT_GEM_SLOT_LIST_ID, NbtElement.LIST_TYPE)){
                List<GemEffectEntry> entries = new ArrayList<>();
                NbtList keys = gemSlotNbtCompound.getList(NBT_GEM_SLOT_LIST_ID,NbtElement.STRING_TYPE);
                for (NbtElement element : keys) {
                    String id = element.asString();
                    GemEffectEntry gemSlotEntry = GemEffectEntries.GEM_EFFECT_ENTRY_MAP.get(id);
                    if (gemSlotEntry!=null){
                        entries.add(gemSlotEntry);
                    }
                }
                return entries;
            }
        }
        return Collections.emptyList();
    }
}
