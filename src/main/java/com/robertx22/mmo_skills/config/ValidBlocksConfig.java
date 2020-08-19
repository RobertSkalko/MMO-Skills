package com.robertx22.mmo_skills.config;

import net.minecraft.block.Block;
import net.minecraft.block.CropBlock;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ValidBlocksConfig {

    public List<BlockConfig> blocks = new ArrayList<>();

    public boolean autoWhiteListOres = false;
    public boolean autoWhiteListCrops = false;

    public int expForAutoWhitelistedBlocks = 5;

    private HashMap<String, Integer> getExperienceMap() {

        HashMap map = new HashMap();

        blocks.forEach(x -> {
            try {
                map.put(x.blockId, x.exp);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        });

        return map;
    }

    public boolean isValidForSkill(Block block) {

        if (autoWhiteListOres) {
            if (Registry.BLOCK.getId(block)
                .getPath()
                .contains("ore")) {
                return true;
            }
        }
        if (autoWhiteListCrops) {
            if (block instanceof CropBlock) {
                return true;
            }
        }

        String id = Registry.BLOCK.getId(block)
            .toString();
        return getExperienceMap().containsKey(id);
    }

    public Integer getExp(Block block) {
        String id = Registry.BLOCK.getId(block)
            .toString();

        Integer exp = getExperienceMap().getOrDefault(id, 0);

        if (exp > 0) {
            return exp;
        }

        if (isValidForSkill(block)) {
            return expForAutoWhitelistedBlocks;
        }

        return 0;
    }
}
