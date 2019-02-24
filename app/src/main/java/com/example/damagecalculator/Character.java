package com.example.damagecalculator;

public class Character {
    String name;
    int[] diceQuantities;
    int[] diceBonus;

    Character(String name){
        diceQuantities = new int[5];
        diceBonus = new int[6];
        for(int i = 0; i < 5; i++){
            diceQuantities[i] = 0;
        }
        for(int i = 0; i < 6; i++){
            diceBonus[i] = 0;
        }
        this.name = name;
    }
}
