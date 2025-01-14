package com.gildedgames.aether.common.entity.monster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class FireMinionEntity extends MonsterEntity
{
    public FireMinionEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }
    
//  public FireMinionEntity(World worldIn) {
//      super(AetherEntityTypes.FIRE_MINION.get(), worldIn);
//  }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1.0));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.5, true));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static AttributeModifierMap.MutableAttribute createMobAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.FOLLOW_RANGE, 12.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 10.0)
                .add(Attributes.MAX_HEALTH, 40.0);
    }

    @Override
    public void tick() {
        super.tick();

        IParticleData particle = ParticleTypes.FLAME;
        if (this.hasCustomName()) {
            String name = TextFormatting.stripFormatting(this.getName().getString());
            if ("JorgeQ".equals(name) || "Jorge_SunSpirit".equals(name)) {
                particle = ParticleTypes.ITEM_SNOWBALL;
            }
        }

        for (int i = 0; i < 1; i++) {
            double d = random.nextFloat() - 0.5F;
            double d1 = random.nextFloat();
            double d2 = random.nextFloat() - 0.5F;
            double d3 = this.getX() + d*d1;
            double d4 = this.getBoundingBox().minY + d1 + 0.5;
            double d5 = this.getZ() + d2*d1;

            this.level.addParticle(particle, d3, d4, d5, 0.0, -0.075000002980232239, 0.0);
        }
    }
}
