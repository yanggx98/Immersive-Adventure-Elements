package io.github.yanggx98.immersive.aelements.mixin;

import com.google.common.collect.Lists;
import io.github.yanggx98.immersive.aelements.ExtraSmithingRecipesHelper;
import io.github.yanggx98.immersive.aelements.IExtraSmithingRecipesProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.SmithingScreenHandler;
import net.minecraft.screen.slot.ForgingSlotsManager;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(SmithingScreenHandler.class)
public abstract class SmithingScreenHandlerMixin extends ForgingScreenHandler {
    @Shadow
    @Final
    private List<SmithingRecipe> recipes;

    public SmithingScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    @Inject(method = "isValidIngredient",at = @At("HEAD"),cancellable = true)
    private void injectIsValidIngredient(ItemStack stack, CallbackInfoReturnable<Boolean> cir){
        if(getExtraTemplateItem(stack) || getExtraBaseItem(stack) || getExtraAdditionItem(stack)){
            cir.setReturnValue(true);
            cir.cancel();
        }
    }

    @Shadow
    public abstract int getSlotFor(ItemStack stack);

    @Shadow
    @Final
    private World world;

    @Inject(method = "getForgingSlotsManager", at = @At("HEAD"), cancellable = true)
    private void injectGetForgingSlotsManager(CallbackInfoReturnable<ForgingSlotsManager> cir) {
        ForgingSlotsManager manager = ForgingSlotsManager.create()
                .input(0, 8, 48, stack ->
                        getExtraTemplateItem(stack) || this.recipes.stream().anyMatch(recipe -> recipe.testTemplate(stack)))
                .input(1, 26, 48, stack ->
                        getExtraBaseItem(stack) || this.recipes.stream().anyMatch(smithingRecipe -> smithingRecipe.testBase(stack)))
                .input(2, 44, 48, stack ->
                        getExtraAdditionItem(stack) || this.recipes.stream().anyMatch(smithingRecipe -> smithingRecipe.testAddition(stack)))
                .output(3, 98, 48).build();
        cir.setReturnValue(manager);
        cir.cancel();
    }

    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    private void rejectUpdateResult(CallbackInfo ci) {
        ItemStack templateStack = this.input.getStack(0);
        ItemStack baseStack = this.input.getStack(1);
        ItemStack additionStack = this.input.getStack(2);
        if (templateStack == ItemStack.EMPTY || baseStack == ItemStack.EMPTY || additionStack == ItemStack.EMPTY) {

        } else {
            if (templateStack.getItem() instanceof SmithingTemplateItem smithingTemplateItem) {
                IExtraSmithingRecipesProvider provider = ExtraSmithingRecipesHelper.get(smithingTemplateItem);
                if (provider != null) {
                    ItemStack outputStack = provider.resultItemStack(templateStack, baseStack, additionStack);
                    if (outputStack != null && outputStack.isItemEnabled(this.world.getEnabledFeatures())) {
                        this.output.setStack(0, outputStack);
                        ci.cancel();
                    }
                }
            }
        }

    }

    @Inject(method = "canTakeOutput",at=@At("HEAD"),cancellable = true)
    private void injectCanTakeOutput(PlayerEntity player, boolean present, CallbackInfoReturnable<Boolean> cir){
        if (this.output.getStack(0)!=ItemStack.EMPTY){
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
    private boolean getExtraTemplateItem(ItemStack stack) {
        for (SmithingTemplateItem smithingTemplateItem : ExtraSmithingRecipesHelper.getExtraSimthingTemplateList()) {
            return compareItem(stack.getItem(), smithingTemplateItem);
        }
        return false;
    }

    private boolean getExtraBaseItem(ItemStack stack) {
        List<Item> baseItemList = Lists.newArrayList();
        ItemStack templateStack = this.input.getStack(0);
        if (templateStack != null && templateStack.getItem() instanceof SmithingTemplateItem smithingTemplateItem) {
            IExtraSmithingRecipesProvider provider = ExtraSmithingRecipesHelper.get(smithingTemplateItem);
            baseItemList = provider.getBaseList(templateStack);
            for (Item item : baseItemList) {
                return compareItem(stack.getItem(), item);
            }
        }
        return false;
    }

    private boolean getExtraAdditionItem(ItemStack stack) {
        List<Item> additionItemList = Lists.newArrayList();
        ItemStack templateStack = this.input.getStack(0);
        if (templateStack != null && templateStack.getItem() instanceof SmithingTemplateItem smithingTemplateItem) {
            IExtraSmithingRecipesProvider provider = ExtraSmithingRecipesHelper.get(smithingTemplateItem);
            additionItemList = provider.getAdditionList(templateStack);
        }

        for (Item item : additionItemList) {
            if(compareItem(stack.getItem(), item)){
                return true;
            }
        }
        return false;
    }

    private boolean compareItem(Item a, Item b) {
        return a.getRegistryEntry().registryKey().getValue().toString().equals(b.getRegistryEntry().registryKey().getValue().toString());
    }

}

