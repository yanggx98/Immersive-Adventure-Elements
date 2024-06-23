package io.github.yanggx98.immersive.aelements.gemslot.screen;

import com.google.common.collect.Lists;
import io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements;
import io.github.yanggx98.immersive.aelements.gemslot.GemSlotHelper;
import io.github.yanggx98.immersive.aelements.gemslot.IEmbeddable;
import io.github.yanggx98.immersive.aelements.gemslot.item.GemItem;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.ForgingSlotsManager;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldEvents;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static io.github.yanggx98.immersive.aelements.gemslot.GemSlotBlocks.EMBED_GEM_TABLE;

public class EmbedGemTableScreenHandler extends ForgingScreenHandler {
    private final Property selectedItem = Property.create();
    private List<EmbeddableItem> embeddableItems = Lists.newArrayList();
    private Runnable contentsChangedListener = () -> {
    };

    public EmbedGemTableScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public EmbedGemTableScreenHandler(int syncId, PlayerInventory inventory, ScreenHandlerContext context) {
        super(GemSlotScreenTypes.EMBED_GEM, syncId, inventory, context);
        this.addProperty(this.selectedItem);

    }

    @Override
    protected boolean canTakeOutput(PlayerEntity player, boolean present) {
        return true;
    }

    @Override
    protected void onTakeOutput(PlayerEntity player, ItemStack stack) {
        stack.onCraft(player.getWorld(), player, stack.getCount());
        this.decrementStack(0);
        this.decrementStack(1);
        this.decrementStack(2);
        this.context.run((world, pos) -> world.syncWorldEvent(WorldEvents.SMITHING_TABLE_USED, (BlockPos) pos, 0));
    }

    private void decrementStack(int slot) {
        ItemStack itemStack = this.input.getStack(slot);
        if (!itemStack.isEmpty()) {
            itemStack.decrement(1);
            this.input.setStack(slot, itemStack);
        }
    }

    @Override
    protected boolean canUse(BlockState state) {
        return state.isOf(EMBED_GEM_TABLE);
    }

    @Override
    public void updateResult() {
        embeddableItems.clear();
        ItemStack itemStack = input.getStack(0);
        ItemStack gemStack = input.getStack(1);
        if (itemStack == ItemStack.EMPTY || !(itemStack.getItem() instanceof IEmbeddable embeddable)) {
            output.setStack(0, ItemStack.EMPTY);
            return;
        }

        embeddableItems.addAll(embeddable.immersive_Adventure_Elements$getEmptyGemSlotList(itemStack).
                stream().map(new Function<GemItem.GemType, EmbeddableItem>() {
                    @Override
                    public EmbeddableItem apply(GemItem.GemType type) {
                        Identifier desc = ImmersiveAdventureElements.identifier(type.name().toLowerCase() + "_gem_slot.desc");
                        Identifier texture = ImmersiveAdventureElements.identifier("textures/gui/" + type.name().toLowerCase() + "_gem_slot.png");
                        return new EmbeddableItem.EmptyEmbeddableItem(texture, type, Text.translatable(desc.toTranslationKey()));
                    }
                }).

                toList());
        embeddable.immersive_Adventure_Elements$getGemEmbededList(itemStack).

                forEach(new Consumer<GemItem>() {
                    @Override
                    public void accept(GemItem gemItem) {
                        embeddableItems.add(new EmbeddableItem.GemEmbeddableItem(gemItem));
                    }
                });
        contentsChangedListener.run();

        if (!(gemStack.getItem() instanceof GemItem)) {
            output.setStack(0, ItemStack.EMPTY);
            return;
        }

        ItemStack resultItem;
        int index = getSelectedItemIndex();
        EmbeddableItem embeddableItem = this.embeddableItems.get(index);

        resultItem = GemSlotHelper.embedGem(embeddableItem, itemStack.copy(), (GemItem) gemStack.getItem());
        output.setStack(0, resultItem);
    }


    @Override
    protected ForgingSlotsManager getForgingSlotsManager() {
        return ForgingSlotsManager.create()
                .input(0, 20, 33, stack -> true)
                .input(1, 144, 15, stack -> {
                    int index = getSelectedItemIndex();
                    if (!embeddableItems.isEmpty()) {
                        EmbeddableItem embeddableItem = this.embeddableItems.get(index);
                        if (stack.getItem() instanceof GemItem gemItem) {
                            return embeddableItem.gemType == gemItem.getGemType();
                        } else if (stack == ItemStack.EMPTY) {
                            return true;
                        }
                    }
                    return false;
                })
                .output(2, 144, 53).build();
    }

    public int getAvailableItemCount() {
        return embeddableItems.size();
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        if (this.isInBounds(id) && this.input.getStack(1) == ItemStack.EMPTY) {
            this.selectedItem.set(id);
            this.populateResult();
        }
        return true;
    }

    void populateResult() {
        if (!this.embeddableItems.isEmpty() && this.isInBounds(this.selectedItem.get())) {
//
        } else {
        }
        this.sendContentUpdates();
    }


    private boolean isInBounds(int id) {
        return id >= 0 && id < this.getAvailableItemCount();
    }

    public boolean canCraft() {
        return this.input.getStack(0) != ItemStack.EMPTY && !this.embeddableItems.isEmpty();
    }

    public void setContentsChangedListener(Runnable contentsChangedListener) {
        this.contentsChangedListener = contentsChangedListener;
    }

    public int getSelectedItemIndex() {
        return this.selectedItem.get();
    }

    public List<EmbeddableItem> get() {
        return embeddableItems;
    }

    public abstract static class EmbeddableItem {
        public final GemItem.GemType gemType;
        final Text itemDesc;

        EmbeddableItem(GemItem.GemType type, Text desc) {
            gemType = type;
            itemDesc = desc;
        }

        public static class EmptyEmbeddableItem extends EmbeddableItem {
            final Identifier texture;

            EmptyEmbeddableItem(Identifier texture, GemItem.GemType type, Text desc) {
                super(type, desc);
                this.texture = texture;
            }
        }

        public static class GemEmbeddableItem extends EmbeddableItem {
            public final GemItem item;

            GemEmbeddableItem(GemItem item) {
                super(item.getGemType(), item.getGemEffectEntry().getDescText(item.getLevel()));
                this.item = item;
            }
        }
    }
}
