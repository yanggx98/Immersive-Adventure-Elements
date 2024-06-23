package io.github.yanggx98.immersive.aelements.gemslot.block;

import io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements;
import io.github.yanggx98.immersive.aelements.gemslot.screen.EmbedGemTableScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EmbedGemTable extends CraftingTableBlock {
    private static final Text TITLE = Text.translatable(ImmersiveAdventureElements.identifier("container.embed").toTranslationKey());

    public EmbedGemTable(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            // 这里会调用 BlockWithEntity 的 createScreenHandlerFactory 方法，会将返回的方块实体强转为
            // 一个 namedScreenHandlerFactory。如果你的方块没有继承 BlockWithEntity，那就需要单独实现 createScreenHandlerFactory。
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null) {
                // 这个调用会让服务器请求客户端开启合适的 Screenhandler
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {

        return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> new EmbedGemTableScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, pos)), TITLE);
    }
}
