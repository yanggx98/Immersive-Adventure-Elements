package io.github.yanggx98.immersive.aelements.tooltip;

import io.github.yanggx98.immersive.aelements.gemslot.item.GemItem;
import io.github.yanggx98.immersive.tooltip.TooltipHelper;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.text.OrderedText;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;

public class EmptyGemTooltipComponent implements TooltipComponent {
    private static final int TEXTURE_SIZE = 12;
    private static final int SPACING = 4;
    private final Identifier TEXTURE;
    private final OrderedText descText;

    public EmptyGemTooltipComponent(Identifier texture, OrderedText descText) {
        this.TEXTURE = texture;
        this.descText = descText;
    }

    @Override
    public int getHeight() {
        return TEXTURE_SIZE + 2;
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        return textRenderer.getWidth(descText) + SPACING + TEXTURE_SIZE;
    }

    public int getTitleOffset() {
        return SPACING + TEXTURE_SIZE;
    }

    @Override
    public void drawText(TextRenderer textRenderer, int x, int y, Matrix4f matrix, VertexConsumerProvider.Immediate vertexConsumers) {
        float startDrawX = (float) x + getTitleOffset();
        float startDrawY = y + ((float) getHeight() / 2 - ((float) textRenderer.fontHeight / 2));
        textRenderer.draw(descText, startDrawX, startDrawY, -1, true, matrix, vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, 0xF000F0);
    }

    @Override
    public void drawItems(TextRenderer textRenderer, int x, int y, DrawContext context) {
        context.drawTexture(TEXTURE, x, y, 0, 0, TEXTURE_SIZE, TEXTURE_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
    }

}
