package io.github.yanggx98.immersive.aelements.attribute;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class HealthUptakeHelper {
    private static List<ExtraDamageProvider> providers = new ArrayList<>();

    public static float uptake(PlayerEntity player, Entity entity, DamageSource source, float amount) {
        float percent = 0;
        for (ExtraDamageProvider provider : providers) {
            percent += provider.getAmount(player, entity, source, amount);
        }
        return percent * amount;
    }

    public static void registerAttributes(ExtraDamageProvider provider) {
        providers.add(provider);
    }

    public interface ExtraDamageProvider {
        float getAmount(PlayerEntity player, Entity entity, DamageSource source, float amount);
    }
}
