package io.github.yanggx98.immersive.aelements.item;

import io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Rarity;

public class IAEItems {
    public static Item BASIC_STAFF = new StaffItem(20, 7, 5, new FabricItemSettings().rarity(Rarity.UNCOMMON));

    public static void onInitialize() {
        register("basic_staff", BASIC_STAFF);
    }

    private static void register(String id, Item item) {
        Registry.register(Registries.ITEM, ImmersiveAdventureElements.identifier(id), item);
    }
}
