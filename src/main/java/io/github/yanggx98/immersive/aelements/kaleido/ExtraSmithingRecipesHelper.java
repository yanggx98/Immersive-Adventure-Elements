package io.github.yanggx98.immersive.aelements.kaleido;

import net.minecraft.item.SmithingTemplateItem;

import java.util.HashMap;
import java.util.Map;

public class ExtraSmithingRecipesHelper {
    private static final Map<SmithingTemplateItem, IExtraSmithingRecipesProvider> EXTRA_SMITHING_RECIPES = new HashMap<>();

    public static void registerRecipes(SmithingTemplateItem templateItem, IExtraSmithingRecipesProvider provider) {
        EXTRA_SMITHING_RECIPES.put(templateItem, provider);
    }

    public static boolean hasSmithingTemplateItem(SmithingTemplateItem item) {
        return EXTRA_SMITHING_RECIPES.containsKey(item);
    }

    public static IExtraSmithingRecipesProvider get(SmithingTemplateItem item) {
        return EXTRA_SMITHING_RECIPES.get(item);
    }
}
