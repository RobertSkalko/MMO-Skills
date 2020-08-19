package com.robertx22.mmo_skills.config;

import com.robertx22.mmo_skills.skill_rewards.DoubleBlockDropsReward;
import com.robertx22.mmo_skills.skill_rewards.DoubleChestItemDropsReward;
import com.robertx22.mmo_skills.skill_rewards.ExtraBlockDropsReward;
import com.robertx22.mmo_skills.skill_rewards.ExtraChestItemDropsReward;
import com.robertx22.mmo_skills.storage.PlayerSkills;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import net.minecraft.block.Blocks;

@me.sargunvohra.mcmods.autoconfig1u.annotation.Config(name = "mmo_skills")
public class MmoConfig implements ConfigData {

    public MmoConfig() {

        initMining();
        initFarming();
        initDungeoneering();
    }

    private void initMining() {

        Mining.addReward(new SkillRewardConfig(PlayerSkills.MINING, new ExtraBlockDropsReward().getId(), 1F));

        SkillRewardConfig doubleDrops = new SkillRewardConfig(PlayerSkills.MINING, new DoubleBlockDropsReward().getId(), 0.2F);
        doubleDrops.rewardsExp = false;
        doubleDrops.priority -= 1;
        Mining.addReward(doubleDrops);

        Mining.validBlocksConfig.blocks.add(new BlockConfig(Blocks.COAL_ORE, 1));
        Mining.validBlocksConfig.blocks.add(new BlockConfig(Blocks.REDSTONE_ORE, 2));
        Mining.validBlocksConfig.blocks.add(new BlockConfig(Blocks.NETHER_QUARTZ_ORE, 2));
        Mining.validBlocksConfig.blocks.add(new BlockConfig(Blocks.IRON_ORE, 3));
        Mining.validBlocksConfig.blocks.add(new BlockConfig(Blocks.LAPIS_ORE, 5));
        Mining.validBlocksConfig.blocks.add(new BlockConfig(Blocks.GOLD_ORE, 10));
        Mining.validBlocksConfig.blocks.add(new BlockConfig(Blocks.DIAMOND_ORE, 20));
        Mining.validBlocksConfig.blocks.add(new BlockConfig(Blocks.EMERALD_ORE, 20));

    }

    private void initFarming() {

        Farming.addReward(new SkillRewardConfig(PlayerSkills.FARMING, new ExtraBlockDropsReward().getId(), 1F));

        SkillRewardConfig doubleDrops = new SkillRewardConfig(PlayerSkills.FARMING, new DoubleBlockDropsReward().getId(), 1);
        doubleDrops.levelsNeededForTier = 5;
        doubleDrops.rewardsExp = false;
        doubleDrops.priority -= 1;
        Farming.addReward(doubleDrops);

        Farming.validBlocksConfig.blocks.add(new BlockConfig(Blocks.SUGAR_CANE, 1));
        Farming.validBlocksConfig.blocks.add(new BlockConfig(Blocks.WHEAT, 2));
        Farming.validBlocksConfig.blocks.add(new BlockConfig(Blocks.POTATOES, 2));
        Farming.validBlocksConfig.blocks.add(new BlockConfig(Blocks.CARROTS, 2));
        Farming.validBlocksConfig.blocks.add(new BlockConfig(Blocks.COCOA, 2));
        Farming.validBlocksConfig.blocks.add(new BlockConfig(Blocks.BEETROOTS, 2));
        Farming.validBlocksConfig.blocks.add(new BlockConfig(Blocks.MELON, 2));
    }

    private void initDungeoneering() {

        Dungeoneering.extraExpPerAction = 25;

        SkillRewardConfig extradrops = new SkillRewardConfig(PlayerSkills.DUNGEONEERING, new ExtraChestItemDropsReward().getId(), 1);
        extradrops.levelsNeededForTier = 2;
        Dungeoneering.addReward(extradrops);

        SkillRewardConfig doubleDrops = new SkillRewardConfig(PlayerSkills.DUNGEONEERING, new DoubleChestItemDropsReward().getId(), 1);
        doubleDrops.rewardsExp = false;
        doubleDrops.levelsNeededForTier = 5;
        Dungeoneering.addReward(doubleDrops);

    }

    @ConfigEntry.Gui.CollapsibleObject
    public SkillConfig Mining = new SkillConfig();

    @ConfigEntry.Gui.CollapsibleObject
    public SkillConfig Farming = new SkillConfig();

    @ConfigEntry.Gui.CollapsibleObject
    public SkillConfig Dungeoneering = new SkillConfig();

    public static MmoConfig get() {
        return AutoConfig.getConfigHolder(MmoConfig.class)
            .getConfig();
    }

}

