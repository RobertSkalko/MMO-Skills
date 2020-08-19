package com.robertx22.mmo_skills.config;

import com.robertx22.mmo_skills.skill_rewards.base.SkillReward;
import com.robertx22.mmo_skills.skill_rewards.base.SkillRewards;
import com.robertx22.mmo_skills.storage.PlayerSkills;
import com.robertx22.mmo_skills.storage.PlayerSkillsComponent;

public class SkillRewardConfig {

    public PlayerSkills skill;

    public String skillReward;

    public int unlocksAtLevel = 1;

    public int levelsNeededForTier = 1;

    public float valuePerTier = 0;

    public boolean rewardsExp = true;

    // what reward is done first, this is used so extra drops is called after double drops.
    public int priority = 0;

    public SkillRewardConfig(PlayerSkills skill, String skillReward, float pertier) {
        this.skill = skill;
        this.skillReward = skillReward;
        this.valuePerTier = pertier;
    }

    public SkillReward getReward() {
        return SkillRewards.INSTANCE.get(skillReward);
    }

    public boolean isUnlocked(PlayerSkills skill, PlayerSkillsComponent comp) {
        return comp.getLevel(skill) >= unlocksAtLevel;
    }

    public float getValue(int lvl) {
        int tiers = lvl / levelsNeededForTier;

        float val = valuePerTier * tiers;

        return val;
    }

    public float getValue(PlayerSkills skill, PlayerSkillsComponent comp) {
        int lvl = comp.getLevel(skill);
        return getValue(lvl);
    }

}
