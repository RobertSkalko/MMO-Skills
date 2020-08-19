package com.robertx22.mmo_skills.storage;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class SingleSkillData {

    @Store
    private int exp = 0;
    @Store
    public int lvl = 1;

    // return true if lvl up
    public boolean addExp(int add, PlayerSkills skill) {
        this.exp += add;

        if (this.exp >= getExpNeededForNextLevel()) {
            if (skill.getConfig().maximumLevel > lvl) {
                this.exp -= getExpNeededForNextLevel();
                lvl++;
                return true;
            }
        }
        return false;
    }

    public int getExp() {
        return exp;
    }

    public int getExpNeededForNextLevel() {
        return lvl * lvl * 20;
    }

}
