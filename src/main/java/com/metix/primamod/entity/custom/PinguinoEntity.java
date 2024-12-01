package com.metix.primamod.entity.custom;


import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class PinguinoEntity extends Animal {
    public PinguinoEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
    }
    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }
    public static AttributeSupplier.Builder CreatoreCarratteristiche () {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 200)
                .add(Attributes.WATER_MOVEMENT_EFFICIENCY, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.20D);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }
}
