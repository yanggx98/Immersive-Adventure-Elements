package io.github.yanggx98.immersive.aelements.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.github.yanggx98.immersive.aelements.attribute.IAEAttributes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.Vanishable;

import java.util.UUID;

public class StaffItem extends Item implements Vanishable {
    protected static final UUID SPELL_DAMAGE_MODIFIER_ID = UUID.fromString("23cf0006-20ad-4f3c-89b5-988ef08a0104");
    protected static final UUID CAST_SPEED_MODIFIER_ID = UUID.fromString("c41d142c-50c5-4e36-ac60-5ac50f43c1a7");
    protected static final UUID MANA_MODIFIER_ID = UUID.fromString("fea5fbef-83d7-4007-a2da-38e22f09989a");

    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    private final int mana;
    private final int spell_damage;
    private final float speed;


    public StaffItem(int mana, int spell_damage, float speed, Settings settings) {
        super(settings);
        this.mana = mana;
        this.spell_damage = spell_damage;
        this.speed = speed;

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(IAEAttributes.SPELL_DAMAGE, new EntityAttributeModifier(SPELL_DAMAGE_MODIFIER_ID, "Weapon modifier", (double) this.spell_damage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(IAEAttributes.SPELL_CAST_SPEED, new EntityAttributeModifier(CAST_SPEED_MODIFIER_ID, "Weapon modifier", (double) speed, EntityAttributeModifier.Operation.ADDITION));
        builder.put(IAEAttributes.MAX_MANA, new EntityAttributeModifier(MANA_MODIFIER_ID, "Weapon modifier", (double) mana, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return this.attributeModifiers;
        }
        return super.getAttributeModifiers(slot);
    }
}
