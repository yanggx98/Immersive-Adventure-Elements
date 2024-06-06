package io.github.yanggx98.immersive.aelements.mixin;

import io.github.yanggx98.immersive.aelements.api.LivingEntityAPIs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Shadow
    public abstract boolean damage(DamageSource source, float amount);

    @Redirect(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private boolean redirectDamage(Entity e, DamageSource source, float amount) {
        amount = LivingEntityAPIs.BEFORE_ENTITY_DAMAGE_EVENT.invoker().beforeDamage((PlayerEntity) (Object)this,e, source, amount);
        return e.damage(source, amount);
    }
}
