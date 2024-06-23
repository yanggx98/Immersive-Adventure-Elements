package io.github.yanggx98.immersive.aelements.gemslot.screen;

import io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

@SuppressWarnings("deprecation")
public class GemSlotScreenTypes {
    public static ScreenHandlerType<EmbedGemTableScreenHandler> EMBED_GEM;

    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String id, ScreenHandlerRegistry.SimpleClientHandlerFactory<T> factory) {
        return ScreenHandlerRegistry.registerSimple(ImmersiveAdventureElements.identifier(id), factory);
    }

    public static void onInitialize() {
        EMBED_GEM = register("embed_gem", EmbedGemTableScreenHandler::new);
    }
}
