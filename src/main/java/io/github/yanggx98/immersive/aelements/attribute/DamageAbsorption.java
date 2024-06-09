package io.github.yanggx98.immersive.aelements.attribute;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class DamageAbsorption {
    private static List<DamageAbsorptionProvider> providers = new ArrayList<>();

    public static float apply(LivingEntity entity, DamageSource source, float amount) {
        float percent = 0;
        for (DamageAbsorptionProvider provider : providers) {
            percent += provider.getAmount(entity, source, amount);
        }
        return percent * amount;
    }

    public static void registerAttributes(DamageAbsorptionProvider provider) {
        providers.add(provider);
    }

    public interface DamageAbsorptionProvider {
        float getAmount(LivingEntity entity, DamageSource source, float amount);
    }
}
