package io.github.yanggx98.immersive.aelements.attribute;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtraDamageHelper {
    private static List<ExtraDamageProvider> providers = new ArrayList<>();

    public static int damage(PlayerEntity player, Entity entity, DamageSource source, float amount) {
        float percent = 0;
        for (ExtraDamageProvider provider : providers) {
            percent += provider.getExtraDamage(player, entity, source, amount);
        }
        return Math.round(percent * amount);
    }

    public static void registerAttributes(ExtraDamageProvider provider) {
        providers.add(provider);
    }

    public interface ExtraDamageProvider {
        float getExtraDamage(PlayerEntity player, Entity entity, DamageSource source, float amount);
    }
}
