package io.github.yanggx98.immersive.aelements.attribute;

import io.github.yanggx98.immersive.aelements.IAEModule;
import io.github.yanggx98.immersive.aelements.api.LivingEntityAPIs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

public class AttributeModule implements IAEModule {
    @Override
    public void onInitialize() {


        LivingEntityAPIs.BEFORE_ENTITY_DAMAGE_EVENT.register(new LivingEntityAPIs.BeforeEntityDamage() {
            @Override
            public float beforeDamage(PlayerEntity playerEntity, Entity entity, DamageSource source, float amount) {
                float extraDamage = ExtraDamageHelper.damage(playerEntity, entity, source, amount);
                float healthUptakeAmount = HealthUptakeHelper.uptake(playerEntity, entity, source, amount);
                if (healthUptakeAmount > 0) {
                    playerEntity.heal(healthUptakeAmount);
                }
                return amount + extraDamage;
            }
        });
    }
}
