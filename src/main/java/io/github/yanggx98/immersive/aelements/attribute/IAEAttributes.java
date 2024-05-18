package io.github.yanggx98.immersive.aelements.attribute;

import io.github.yanggx98.immersive.aelements.ImmersiveAdventureElements;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class IAEAttributes {
    public static EntityAttribute CRITICAL_DAMAGE = register("generic.critical_damage", new ClampedEntityAttribute(ImmersiveAdventureElements.identifier("attribute.name.generic.critical_damage").toTranslationKey(), 0, 0, 1024.0).setTracked(false));
    public static EntityAttribute CRITICAL_PROBABILITY = register("generic.critical_probability", new ClampedEntityAttribute(ImmersiveAdventureElements.identifier("attribute.name.generic.critical_probability").toTranslationKey(), 0.0, 0.0, 1.0).setTracked(true));
    public static EntityAttribute CRITICAL_INCREASE = register("generic.critical_increase", new ClampedEntityAttribute(ImmersiveAdventureElements.identifier("attribute.name.generic.critical_increase").toTranslationKey(), 0.0, 0.0, 1.0).setTracked(true));

    static EntityAttribute register(String id, EntityAttribute attribute) {
        return Registry.register(Registries.ATTRIBUTE, id, attribute);
    }
}
