package com.nickbrowncoding.runescapemetrics;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Calculates a RuneScape users combat level based on the core stats of the character. The stats
 * and equations vary depending on if it is a RuneScape 3 or OSRS character.
 * @author nbrown
 * @Date 2023/06/26
 */
public class CombatLevelCalculator {

    /**
     * Calculates the players combat level for RuneScape 3 based on the core stats
     * @param levelMap A key pair of stat name and level
     * @return Map - The combat level for Melee, Range, Magic, and Overall
     */
    public static Map<String, Integer> calculateR3CombatLevel(Map<String, Integer> levelMap) {

        // Set the level values here to make the equations more readable
        int att = levelMap.get("ATT");
        int str = levelMap.get("STR");
        int def = levelMap.get("STR");
        int rng = levelMap.get("STR");
        int mag = levelMap.get("STR");
        int hp = levelMap.get("STR");
        int pray = levelMap.get("STR");
        int summ = levelMap.get("SUMM");

        int combatLevel = (int) Math.floor(levelMap.get("ATT") + levelMap.get("STR"));
        return null;
    }

    /**
     * Calculates the players combat level for Old School RuneScape based on the core stats
     * @param levelMap A key pair of stat name and level
     * @return Map - The combat level for Melee, Range, Magic, and Overall
     */
    public static Map<String, Integer> calculateOSRSCombatLevel(Map<String, Integer> levelMap) {

        // Set the level values here to make the equations more readable
        int att = levelMap.get("ATT");
        int str = levelMap.get("STR");
        int def = levelMap.get("DEF");
        int rng = levelMap.get("RNG");
        int mag = levelMap.get("MAG");
        int hp = levelMap.get("HP");
        int pray = levelMap.get("PRAY");

        // Calculate the base (Def, HP, and Prayer)
        double base = (Math.floor(pray/2.0) + def + hp) * 0.25;

        // Calculate Melee
        int melee = (int) Math.floor((att + str) * 0.325 + base);

        // Calculate Range
        int range = (int) Math.floor((Math.floor(rng/2.0) + rng) * 0.325 + base);

        // Calculate Magic
        int magic = (int) Math.floor ((Math.floor(mag/2.0) + mag) * 0.325 + base);

        // Overall Level (max of melee, range, and magic)
        int overall = Math.max(magic, Math.max(range, melee));

        Map<String, Integer> combatLevels = new HashMap<>();
        combatLevels.put("Melee", melee);
        combatLevels.put("Range", range);
        combatLevels.put("Magic", magic);
        combatLevels.put("Combat", overall);

        return combatLevels;
    }

    public static void main(String[] args) {

        // Create scanner for user input
        Scanner uInput = new Scanner(System.in);

        // Create HashMap of character stats and levels
        Map<String, Integer> characterStats = new HashMap<>();

        // Print welcome message
        System.out.println("RuneScape Combat Level Calculator 1.0\n");

        // Get the RS version
        System.out.print("RuneScape Version (R3 or OSRS)?");
        String rsVersion = uInput.nextLine();
        System.out.print("\n");

        // Check is user entered acceptable RuneScape Version
        if(!rsVersion.equals("R3")&&!rsVersion.equals("OSRS")) {

            // Return error to user
            System.out.println("Invalid RuneScape Version!");
            System.exit(0);
        }

        // Get Player Name
        System.out.print("Character Name: ");
        String characterName = uInput.nextLine();
        System.out.println();

        try {

            // Get Attack
            System.out.print("Attack Level: ");
            characterStats.put("ATT", uInput.nextInt());
            System.out.println();

            // Get Strength
            System.out.print("Strength Level: ");
            characterStats.put("STR", uInput.nextInt());
            System.out.println();

            // Get Defence
            System.out.print("Defence Level: ");
            characterStats.put("DEF", uInput.nextInt());
            System.out.println();

            // Get Range
            System.out.print("Range Level: ");
            characterStats.put("RNG", uInput.nextInt());
            System.out.println();

            // Get Magic
            System.out.print("Magic Level: ");
            characterStats.put("MAG", uInput.nextInt());
            System.out.println();

            // Get Hit Points
            System.out.print("HP Level: ");
            characterStats.put("HP", uInput.nextInt());
            System.out.println();

            // Get Prayer
            System.out.print("Prayer Level: ");
            characterStats.put("PRAY", uInput.nextInt());
            System.out.println();

            // If OSRS, then calculate combat level
            if(rsVersion.equals("OSRS")) {

                // Get Combat Levels
                Map<String, Integer> combatLevels = calculateOSRSCombatLevel(characterStats);

                // Show player name and combat levels
                System.out.println(characterName + "'s Combat Level is: " + combatLevels.get("Combat"));
                System.out.println("Melee: " + combatLevels.get("Melee"));
                System.out.println("Range: " + combatLevels.get("Range"));
                System.out.println("Magic: " + combatLevels.get("Magic"));
                System.exit(0);
            }
            else {

                // RS 3 Only: Get Summoner
                System.out.print("Summoner Level: ");
                characterStats.put("SUMM", uInput.nextInt());
                System.out.println();

                // Show player name and combat level
                System.out.print(characterName + "'s Combat Level is: ");
                System.out.println(calculateR3CombatLevel(characterStats));
                System.exit(0);
            }
        }
        catch (Exception e) {
            // Show stack trace
            e.printStackTrace();

            // Gracefully shutdown. Do not return status code other than 0, else gradle will hide exception
            System.exit(0);
        }
    }
}
