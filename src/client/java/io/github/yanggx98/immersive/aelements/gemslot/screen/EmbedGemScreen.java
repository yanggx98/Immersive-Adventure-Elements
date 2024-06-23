package io.github.yanggx98.immersive.aelements.gemslot.screen;

import io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CyclingSlotIcon;
import net.minecraft.client.gui.screen.ingame.ForgingScreen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.StonecuttingRecipe;
import net.minecraft.screen.StonecutterScreenHandler;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class EmbedGemScreen extends ForgingScreen<EmbedGemTableScreenHandler> {
    private static final Identifier EMPTY_ROUND_SLOT = ImmersiveAdventureElements.identifier("item/empty_slot_round_gem");
    private static final Identifier EMPTY_SQUARE_SLOT = ImmersiveAdventureElements.identifier("item/empty_slot_square_gem");
    private static final Identifier EMPTY_OCTAGON_SLOT = ImmersiveAdventureElements.identifier("item/empty_slot_octagon_gem");
    private static final Identifier EMPTY_TRIANGLE_SLOT = ImmersiveAdventureElements.identifier("item/empty_slot_triangle_gem");

    private static final Identifier TEXTURE = ImmersiveAdventureElements.identifier("textures/gui/container/embed_gem_table.png");
    private static final int SCROLLBAR_WIDTH = 12;
    private static final int SCROLLBAR_HEIGHT = 15;
    private static final int RECIPE_LIST_COLUMNS = 4;
    private static final int RECIPE_LIST_ROWS = 3;
    private static final int RECIPE_ENTRY_WIDTH = 16;
    private static final int RECIPE_ENTRY_HEIGHT = 18;

    private float scrollAmount;
    private int scrollOffset;
    private boolean canCraft;

    private final PlayerEntity player;
    private boolean mouseClicked;

    private final CyclingSlotIcon templateSlotIcon = new CyclingSlotIcon(1);

    public EmbedGemScreen(EmbedGemTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title, TEXTURE);
        this.player = inventory.player;
        handler.setContentsChangedListener(this::onInventoryChange);
    }

    @Override
    protected void handledScreenTick() {
        super.handledScreenTick();
        if (!this.handler.get().isEmpty()) {
            EmbedGemTableScreenHandler.EmbeddableItem item = this.handler.get().get(handler.getSelectedItemIndex());
            Identifier texture = switch (item.gemType) {
                case TRIANGLE -> EMPTY_TRIANGLE_SLOT;
                case ROUND -> EMPTY_ROUND_SLOT;
                case SQUARE -> EMPTY_SQUARE_SLOT;
                case OCTAGON -> EMPTY_OCTAGON_SLOT;
            };
            this.templateSlotIcon.updateTexture(List.of(texture));
        } else {
            this.templateSlotIcon.updateTexture(List.of(EMPTY_TRIANGLE_SLOT, EMPTY_OCTAGON_SLOT, EMPTY_SQUARE_SLOT, EMPTY_ROUND_SLOT));
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    protected void drawMouseoverTooltip(DrawContext context, int x, int y) {
        super.drawMouseoverTooltip(context, x, y);
        if (this.canCraft) {
            int i = this.x + 52;
            int j = this.y + 14;
            int k = this.scrollOffset + 12;
            List<EmbedGemTableScreenHandler.EmbeddableItem> list = (this.handler).get();

            for (int l = this.scrollOffset; l < k && l < (this.handler).getAvailableItemCount(); ++l) {
                int m = l - this.scrollOffset;
                int n = i + m % 4 * 16;
                int o = j + m / 4 * 18 + 2;
                if (x >= n && x < n + 16 && y >= o && y < o + 18) {
                    EmbedGemTableScreenHandler.EmbeddableItem embeddableItem = list.get(l);
                    if (embeddableItem instanceof EmbedGemTableScreenHandler.EmbeddableItem.GemEmbeddableItem gemEmbeddableItem) {
                        context.drawItemTooltip(this.textRenderer, gemEmbeddableItem.item.getDefaultStack(), x, y);
                    }
                }
            }
        }

    }

    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        this.renderBackground(context);
        int i = this.x;
        int j = this.y;
        context.drawTexture(TEXTURE, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
        int k = (int) (41.0F * this.scrollAmount);
        context.drawTexture(TEXTURE, i + 119, j + 15 + k, 176 + (this.shouldScroll() ? 0 : 12), 0, 12, 15);
        int l = this.x + 52;
        int m = this.y + 14;
        int n = this.scrollOffset + 12;
        this.renderRecipeBackground(context, mouseX, mouseY, l, m, n);
        this.renderRecipeIcons(context, l, m, n);
        this.templateSlotIcon.render(this.handler, context, delta, this.x, this.y);
    }

    private void renderRecipeBackground(DrawContext context, int mouseX, int mouseY, int x, int y, int scrollOffset) {
        for (int i = this.scrollOffset; i < scrollOffset && i < (this.handler).getAvailableItemCount(); ++i) {
            int j = i - this.scrollOffset;
            int k = x + j % 4 * 16;
            int l = j / 4;
            int m = y + l * 18 + 2;
            int n = this.backgroundHeight;
            if (i == (this.handler).getSelectedItemIndex()) {
                n += 18;
            } else if (mouseX >= k && mouseY >= m && mouseX < k + 16 && mouseY < m + 18) {
                n += 36;
            }
            context.drawTexture(TEXTURE, k, m - 1, 0, n, 16, 18);
        }
    }

    private void renderRecipeIcons(DrawContext context, int x, int y, int scrollOffset) {
        List<EmbedGemTableScreenHandler.EmbeddableItem> list = this.handler.get();

        for (int i = this.scrollOffset; i < scrollOffset && i < this.handler.getAvailableItemCount(); ++i) {
            int j = i - this.scrollOffset;
            int k = x + j % 4 * 16;
            int l = j / 4;
            int m = y + l * 18 + 2;
            if (list.get(i) instanceof EmbedGemTableScreenHandler.EmbeddableItem.GemEmbeddableItem gemEmbeddableItem) {
                context.drawItem(gemEmbeddableItem.item.getDefaultStack(), k, m);

            } else if (list.get(i) instanceof EmbedGemTableScreenHandler.EmbeddableItem.EmptyEmbeddableItem emptyEmbeddableItem) {
                context.drawTexture(emptyEmbeddableItem.texture, k + 2, m + 2, 0, 0, 12, 12, 12, 12);
            }
//            context.drawText(textRenderer, list.get(i).itemDesc, k, m, -1, true);
        }

    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.mouseClicked = false;
        if (this.canCraft) {
            int i = this.x + 52;
            int j = this.y + 14;
            int k = this.scrollOffset + 12;

            for (int l = this.scrollOffset; l < k; ++l) {
                int m = l - this.scrollOffset;
                double d = mouseX - (double) (i + m % 4 * 16);
                double e = mouseY - (double) (j + m / 4 * 18);
                if (d >= 0.0 && e >= 0.0 && d < 16 && e < 18.0 && this.handler.onButtonClick(this.client.player, l)) {
                    MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                    this.client.interactionManager.clickButton(this.handler.syncId, l);
                    return true;
                }
            }

            i = this.x + 119;
            j = this.y + 9;
            if (mouseX >= (double) i && mouseX < (double) (i + 12) && mouseY >= (double) j && mouseY < (double) (j + 54)) {
                this.mouseClicked = true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.mouseClicked && this.shouldScroll()) {
            int i = this.y + 14;
            int j = i + 54;
            this.scrollAmount = ((float) mouseY - (float) i - 7.5F) / ((float) (j - i) - 15.0F);
            this.scrollAmount = MathHelper.clamp(this.scrollAmount, 0.0F, 1.0F);
            this.scrollOffset = (int) ((double) (this.scrollAmount * (float) this.getMaxScroll()) + 0.5) * 4;
            return true;
        } else {
            return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        }
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (this.shouldScroll()) {
            int i = this.getMaxScroll();
            float f = (float) amount / (float) i;
            this.scrollAmount = MathHelper.clamp(this.scrollAmount - f, 0.0F, 1.0F);
            this.scrollOffset = (int) ((double) (this.scrollAmount * (float) i) + 0.5) * 4;
        }

        return true;
    }


    protected int getMaxScroll() {
        return (this.handler.getAvailableItemCount() + 4 - 1) / 4 - 3;
    }

    private boolean shouldScroll() {
        return this.canCraft && this.handler.getAvailableItemCount() > 12;
    }

    @Override
    protected void drawInvalidRecipeArrow(DrawContext context, int x, int y) {

    }

    private void onInventoryChange() {
        this.canCraft = this.handler.canCraft();
        if (!this.canCraft) {
            this.scrollAmount = 0.0F;
            this.scrollOffset = 0;
        }
    }
}
