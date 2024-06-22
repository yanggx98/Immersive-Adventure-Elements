package io.github.yanggx98.immersive.aelements.mixin;

import io.github.yanggx98.immersive.aelements.kaleido.ExtraAnvilRecipesHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandleMixin extends ForgingScreenHandler {


    @Shadow
    @Final
    private Property levelCost;

    public AnvilScreenHandleMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    private void onUpdateResult(CallbackInfo ci) {
        ItemStack itemStack = this.input.getStack(0);
        if (!itemStack.isEmpty()) {
            this.output.setStack(0, ItemStack.EMPTY);
            this.levelCost.set(0);
            ci.cancel();
        }
        ItemStack itemStack2 = this.input.getStack(1);
        ItemStack itemStack3 = ExtraAnvilRecipesHelper.PROVIDER.invoker().get(itemStack, itemStack2, this.levelCost);
        if (itemStack3 != null && itemStack3 != ItemStack.EMPTY) {
            this.output.setStack(0, itemStack3);
            this.sendContentUpdates();
        }
    }
}
