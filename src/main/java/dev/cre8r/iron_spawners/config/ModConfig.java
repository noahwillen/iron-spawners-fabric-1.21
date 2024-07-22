package dev.cre8r.iron_spawners.config;

import eu.midnightdust.lib.config.MidnightConfig;

import java.util.List;

public class ModConfig extends MidnightConfig {
    @Comment(category = "numbers")
    public static Comment maxDelayComment;

    @Entry(category = "numbers")
    public static int ironMaxDelay = 600;
    @Entry(category = "numbers")
    public static int ironSpawnCt = 3;

    @Entry(category = "numbers")
    public static int goldMaxDelay = 400;
    @Entry(category = "numbers")
    public static int goldSpawnCt = 3;

    @Entry(category = "numbers")
    public static int emeraldMaxDelay = 300;
    @Entry(category = "numbers")
    public static int emeraldSpawnCt = 3;

    @Entry(category = "numbers")
    public static int diamondMaxDelay = 200;
    @Entry(category = "numbers")
    public static int diamondSpawnCt = 4;

    @Entry(category = "numbers")
    public static int netheriteMaxDelay = 80;
    @Entry(category = "numbers")
    public static int netheriteSpawnCt = 5;

    @Entry(category = "numbers")
    public static List<String> eggBlacklist = List
            .of("minecraft:iron_golem_spawn_egg",
                    "minecraft:elder_guardian_spawn_egg",
                    "minecraft:ender_dragon_spawn_egg",
                    "minecraft:wither_spawn_egg",
                    "minecraft:warden_spawn_egg");
}
