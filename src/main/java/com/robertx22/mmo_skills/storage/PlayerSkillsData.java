package com.robertx22.mmo_skills.storage;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.HashMap;

@Storable
public class PlayerSkillsData {

    @Store
    public HashMap<PlayerSkills, SingleSkillData> skills = new HashMap<>();

}
