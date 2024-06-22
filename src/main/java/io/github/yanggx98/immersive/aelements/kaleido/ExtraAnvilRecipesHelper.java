package io.github.yanggx98.immersive.aelements.kaleido;

import io.github.yanggx98.immersive.aelements.api.LivingEntityAPIs;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.Property;

public class ExtraAnvilRecipesHelper {
    public static final Event<IExtraAnvilRecipeProvider> PROVIDER =
            EventFactory.createArrayBacked(IExtraAnvilRecipeProvider.class, callbacks -> (s1, s2, levelCount) -> {
                for (IExtraAnvilRecipeProvider callback : callbacks) {
                    ItemStack result = callback.get(s1, s2, levelCount);
                    if (result != null) {
                        return result;
                    }
                }
                return null;
            });

    public interface IExtraAnvilRecipeProvider {
        ItemStack get(ItemStack stack, ItemStack stack2, Property levelCount);
    }
}
