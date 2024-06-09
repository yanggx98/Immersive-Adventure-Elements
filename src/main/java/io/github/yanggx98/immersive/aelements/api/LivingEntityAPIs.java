package io.github.yanggx98.immersive.aelements.api;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

public class LivingEntityAPIs {
    public static final Event<BeforeEntityDamage> BEFORE_DAMAGE_EVENT =
            EventFactory.createArrayBacked(BeforeEntityDamage.class, callbacks -> (p, e, s, f) -> {
                float totalAmount = f;
                for (BeforeEntityDamage callback : callbacks) {
                    totalAmount = callback.beforeDamage(p, e, s, totalAmount);
                }
                return totalAmount;
            });
    public static final Event<ApplyArmorToDamage> APLLPY_ARMOR_TO_DAMAGE_EVENT =
            EventFactory.createArrayBacked(ApplyArmorToDamage.class, callbacks -> (e, s, f) -> {
                float totalAmount = f;
                for (ApplyArmorToDamage callback : callbacks) {
                    totalAmount = callback.applyArmorToDamage(e, s, totalAmount);
                }
                return totalAmount;
            });

    public interface BeforeEntityDamage {
        float beforeDamage(PlayerEntity playerEntity, Entity entity, DamageSource source, float amount);
    }

    public interface ApplyArmorToDamage {
        float applyArmorToDamage(LivingEntity entity, DamageSource source, float amount);
    }
}
