package io.github.yanggx98.immersive.aelements.gemslot.item;

import com.google.common.collect.Lists;
import io.github.yanggx98.immersive.aelements.gemslot.GemEffectEntry;
import io.github.yanggx98.immersive.aelements.rarity.ExtraRarity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.item.TooltipData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class GemItem extends Item {
    private final GemEffectEntry effectEntry;
    private final Level level;
    private final GemType type;

    public GemItem(GemEffectEntry effectEntry, Level level, GemType type) {
        super(new Settings());
        this.effectEntry = effectEntry;
        this.level = level;
        this.type = type;
    }

    public GemEffectEntry getGemEffectEntry() {
        return effectEntry;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.empty().append(effectEntry.getDescText(this.level)).setStyle(Style.EMPTY.withFormatting(Formatting.GRAY)));
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return switch (level) {
            case LEVEL_1 -> Rarity.COMMON;
            case LEVEL_2 -> Rarity.UNCOMMON;
            case LEVEL_3 -> Rarity.RARE;
            case LEVEL_4, LEVEL_5, LEVEL_6 -> Rarity.EPIC;
        };
    }

    public Level getLevel() {
        return level;
    }

    public GemType getGemType() {
        return type;
    }

    public enum Level {
        LEVEL_1(1), LEVEL_2(2), LEVEL_3(3), LEVEL_4(4), LEVEL_5(5), LEVEL_6(6);
        public final int value;

        Level(int value) {
            this.value = value;
        }
    }

    public enum GemType {
        TRIANGLE, ROUND, SQUARE, OCTAGON, CUSTOM
    }
}
