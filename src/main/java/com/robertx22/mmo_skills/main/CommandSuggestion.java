package com.robertx22.mmo_skills.main;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.server.command.CommandSource;
import net.minecraft.server.command.ServerCommandSource;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public abstract class CommandSuggestion implements SuggestionProvider<ServerCommandSource> {

    public abstract List<String> suggestions();

    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context,
                                                         SuggestionsBuilder builder) {
        CommandSource.suggestMatching(this.suggestions(), builder);
        return builder.buildFuture();
    }

}