package com.robertx22.mmo_skills.main;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.robertx22.mmo_skills.storage.PlayerSkills;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class ShowLevelsCommand {

    static class suggestions extends CommandSuggestion {

        @Override
        public List<String> suggestions() {
            List<String> collect = Arrays.stream(PlayerSkills.values())
                .map(x -> x.name())
                .collect(Collectors.toList());
            collect.add("ALL_SKILLS");
            return collect;
        }
    }

    public static void register(CommandDispatcher<ServerCommandSource> commandDispatcher) {
        commandDispatcher.register(

            literal(Ref.MODID)
                .then(literal("show")
                    .then(literal("levels")
                        .then(argument("skill", StringArgumentType.word())
                            .suggests(new suggestions())
                            .requires(e -> e.hasPermissionLevel(2))
                            .executes(ctx -> run(ctx.getSource(), StringArgumentType.getString(ctx, "skill")))))));
    }

    private static int run(ServerCommandSource source, String id) {

        try {

            PlayerEntity player = source.getPlayer();

            if (id.equals("ALL_SKILLS")) {
                for (PlayerSkills skill : PlayerSkills.values()) {
                    skill.getShortTooltip(Components.INSTANCE.SKILLS.get(player))
                        .forEach(x -> player
                            .sendMessage(x, false));
                }
            } else {
                PlayerSkills skill = PlayerSkills.valueOf(id);

                skill.getFullTooltip(Components.INSTANCE.SKILLS.get(player))
                    .forEach(x -> player
                        .sendMessage(x, false));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
