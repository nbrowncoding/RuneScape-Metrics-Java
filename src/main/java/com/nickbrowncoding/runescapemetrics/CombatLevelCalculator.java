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
     * @param statMap A key pair of stat name and level
     * @return The combat level
     */
    public static int calculateR3CombatLevel(Map<String, Integer> statMap) {

        int combatLevel = (int) Math.floor(statMap.get("Attack") + statMap.get("Strength"));
        return combatLevel;
    }

    public static int calculateOSRSCombatLevel(Map<String, Integer> statMap) {

        int combatLevel = (int) Math.floor(statMap.get("Attack") + statMap.get("Strength"));
        return combatLevel;
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
            characterStats.put("Attack", uInput.nextInt());
            System.out.println();
            // Get Strength
            System.out.print("Strength Level: ");
            characterStats.put("Strength", uInput.nextInt());
            System.out.println();
            // Get Range
            // Get Magic
            // Get Hit Points
            // Get Prayer
            // Show OSRS Combat Level
            if(rsVersion.equals("OSRS")) {

                System.out.println(calculateOSRSCombatLevel(characterStats));
                System.exit(0);
            }
            else {

                // RS 3 Only: Get Summoner
                // Show R3 Combat Level
                System.out.println(calculateR3CombatLevel(characterStats));
                System.exit(0);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
