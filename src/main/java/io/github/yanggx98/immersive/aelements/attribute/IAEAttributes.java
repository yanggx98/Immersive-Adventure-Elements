package io.github.yanggx98.immersive.aelements.attribute;

import io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class IAEAttributes {
    public static EntityAttribute SPELL_DAMAGE = register("generic.spell_damage", new ClampedEntityAttribute(ImmersiveAdventureElements.identifier("attribute.name.generic.spell_damage").toTranslationKey(), 0, 0, 1024.0).setTracked(false));
    public static EntityAttribute SPELL_CAST_SPEED = register("generic.spell_cast_speed", new ClampedEntityAttribute(ImmersiveAdventureElements.identifier("attribute.name.generic.spell_cast_speed").toTranslationKey(), 1.0, 1.0, 5.0).setTracked(true));
    public static EntityAttribute MAX_MANA = register("generic.max_mana", new ClampedEntityAttribute(ImmersiveAdventureElements.identifier("attribute.name.generic.max_mana").toTranslationKey(), 0, 0, 1024.0).setTracked(true));

    static EntityAttribute register(String id, EntityAttribute attribute) {
        return Registry.register(Registries.ATTRIBUTE, id, attribute);
    }
}
