package com.example.damagecalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class DamageFragment extends Fragment {

    public Character character;
    EditText[] quantities = new EditText[5];
    EditText[] bonuses = new EditText[6];
    static Random random = new Random();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_damage, container, false);
        if(character != null) {
            getActivity().setTitle(character.name);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        quantities[0] = getActivity().findViewById(R.id.d4_quantity_edit);
        quantities[1] = getActivity().findViewById(R.id.d6_quantity_edit);
        quantities[2] = getActivity().findViewById(R.id.d8_quantity_edit);
        quantities[3] = getActivity().findViewById(R.id.d10_quantity_edit);
        quantities[4] = getActivity().findViewById(R.id.d12_quantity_edit);
        for (int i = 0; i < 5; i++) {
            if (character.diceQuantities[i] > 0) {
                quantities[i].setText(Integer.toString(character.diceQuantities[i]));
            }
            quantities[i].addTextChangedListener(new CharacterWatcher(i) {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().isEmpty()) {
                        character.diceQuantities[position] = Integer.parseInt(s.toString());
                    } else {
                        character.diceQuantities[position] = 0;
                    }
                }
            });
        }
        bonuses[0] = getActivity().findViewById(R.id.d4_bonus_edit);
        bonuses[1] = getActivity().findViewById(R.id.d6_bonus_edit);
        bonuses[2] = getActivity().findViewById(R.id.d8_bonus_edit);
        bonuses[3] = getActivity().findViewById(R.id.d10_bonus_edit);
        bonuses[4] = getActivity().findViewById(R.id.d12_bonus_edit);
        bonuses[5] = getActivity().findViewById(R.id.d20_bonus_edit);
        for (int i = 0; i < 6; i++) {
            if (character.diceBonus[i] > 0) {
                bonuses[i].setText(Integer.toString(character.diceBonus[i]));
            }
            bonuses[i].addTextChangedListener(new CharacterWatcher(i) {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().isEmpty()) {
                        character.diceBonus[position] = Integer.parseInt(s.toString());
                    } else {
                        character.diceBonus[position] = 0;
                    }
                }
            });
        }

        FloatingActionButton rollButton = getActivity().findViewById(R.id.roll_button);
        rollButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roll(v);
            }
        });
    }

    public void roll(View view) {
        int attackTotal = randomNumber(20) + character.diceBonus[5];
        TextView attackText = getActivity().findViewById(R.id.attack_result_text);
        boolean critical = attackTotal - character.diceBonus[5] == 20;
        if (critical) { // critical damage
            attackText.setText("Nat 20");
        } else{
            attackText.setText(Integer.toString(attackTotal));
        }
        int damageTotal = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < character.diceQuantities[i]; j++) {
                damageTotal += randomNumber(i * 2 + 4);
                if (critical) {
                    damageTotal += i * 2 + 4;
                }
            }
            damageTotal += character.diceBonus[i];
            if (critical){
                damageTotal += character.diceBonus[i];
            }
        }
        TextView damageText = getActivity().findViewById(R.id.damage_result_text);
        damageText.setText(Integer.toString(damageTotal));
    }

    private static int randomNumber(int max) {
        //return (int) (Math.random() * ((max - min) + 1)) + min;
        return random.nextInt(max) + 1;
    }
}
