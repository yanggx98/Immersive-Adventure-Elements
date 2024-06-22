package io.github.yanggx98.immersive.aelements.gemslot;

import io.github.yanggx98.immersive.aelements.gemslot.block.EmbedGemTable;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

import static io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements.identifier;

public class GemSlotBlocks{
    public static  Block EMBED_GEM_TABLE;

    public static void onInitialize() {
        EMBED_GEM_TABLE = register("embed_gem_table", new EmbedGemTable(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5f).sounds(BlockSoundGroup.WOOD).burnable()));
    }

    private static Block register(String id, Block entry) {
        return Registry.register(Registries.BLOCK, identifier(id), entry);
    }
}
