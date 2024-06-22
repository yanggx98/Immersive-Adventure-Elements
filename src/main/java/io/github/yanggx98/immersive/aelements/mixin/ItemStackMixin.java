package io.github.yanggx98.immersive.aelements.mixin;

import io.github.yanggx98.immersive.aelements.rarity.ExtraRarity;
import io.github.yanggx98.immersive.aelements.rarity.ExtraRarityHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract Item getItem();

    @Redirect(method = "getTooltip", at = @At(value = "INVOKE",ordinal = 0, target = "Lnet/minecraft/text/MutableText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;"))
    private MutableText redirectGetTooltip(MutableText instance, Formatting formatting) {
        Rarity rarity = this.getItem().getRarity((ItemStack) (Object)this);
        ExtraRarity extraRarity = ExtraRarityHelper.convert(rarity);
        return instance.formatted(extraRarity.formatting);
    }
}

