package com.robertx22.mmo_skills.config;

import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;

public class BlockConfig {

    public String blockId;
    public int exp;

    public BlockConfig(String blockId, int exp) {
        this.blockId = blockId;
        this.exp = exp;
    }

    public BlockConfig(Block block, int exp) {
        this.blockId = Registry.BLOCK.getId(block)
            .toString();
        this.exp = exp;
    }

    public BlockConfig() {

    }

}
