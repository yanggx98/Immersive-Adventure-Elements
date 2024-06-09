package io.github.yanggx98.immersive.aelements.mixin;


import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static io.github.yanggx98.immersive.aelements.api.LivingEntityAPIs.APLLPY_ARMOR_TO_DAMAGE_EVENT;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public abstract int getArmor();

//    @Inject(method = "applyArmorToDamage", at = @At("TAIL"))
//    private void injectApplyArmorToDamage(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
//        if (!source.isIn(DamageTypeTags.BYPASSES_ARMOR)) {
//            float newAmount = APLLPY_ARMOR_TO_DAMAGE_EVENT.invoker().applyArmorToDamage((LivingEntity) (Object) this, source, amount);
//            cir.setReturnValue(newAmount);
//        }
//    }

    @Redirect(method = "applyArmorToDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/DamageUtil;getDamageLeft(FFF)F"))
    private float redirectApplyArmorToDamage(float damage, float armor, float armorToughness, @Local float amount, @Local DamageSource source) {
        float value = DamageUtil.getDamageLeft(amount, (float) this.getArmor(), (float) ((LivingEntity) (Object) this).getAttributeValue(EntityAttributes.GENERIC_ARMOR_TOUGHNESS));
        value = APLLPY_ARMOR_TO_DAMAGE_EVENT.invoker().applyArmorToDamage((LivingEntity) (Object) this, source, value);
        return value;
    }

}
