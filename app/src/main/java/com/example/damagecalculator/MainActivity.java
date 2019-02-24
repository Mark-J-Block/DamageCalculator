package com.example.damagecalculator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity implements NameDialog.OnInputListener{

    RecyclerView recyclerView;
    LinearLayoutManager viewManager;
    CharacterAdapter viewAdapter;

    RecyclerTouchListener recyclerTouchListener;
    String mode = "edit";
    FragmentManager fragmentManager = getSupportFragmentManager();

    ArrayList<Character> characters = new ArrayList<>();

    @Override
    public void sendInput(String input) {
        characters.add(new Character(input));
        viewAdapter.notifyItemInserted(characters.size() - 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Characters");
        viewAdapter = new CharacterAdapter(characters);
        viewManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recycler_view_characters);
        recyclerView.setLayoutManager(viewManager);
        recyclerView.setAdapter(viewAdapter);
        recyclerTouchListener = new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position, float x, float y) {
                if (mode.equals("edit")) {
                    DamageFragment damageFragment = new DamageFragment();
                    damageFragment.character = characters.get(position);
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.fragment, damageFragment);
                    findViewById(R.id.main_view).setVisibility(View.GONE);
                    fragmentTransaction.commit();
                    fragmentTransaction.addToBackStack(null);
                } else {
                    characters.remove(position);
                    viewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onLongClick(View view, int position, float x, float y) {
            }
        });
        recyclerView.addOnItemTouchListener(recyclerTouchListener);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        if(findViewById(R.id.main_view).getVisibility() == View.GONE){
            findViewById(R.id.main_view).setVisibility(View.VISIBLE);
            setTitle("Characters");
        }
    }

    public void add(View view) {
        NameDialog nameDialog = new NameDialog();
        nameDialog.show(getSupportFragmentManager(), "NameDialog");
    }

    public void remove(View view) {
        findViewById(R.id.remove_button).setVisibility(View.GONE);
        findViewById(R.id.add_button).setVisibility(View.GONE);
        findViewById(R.id.done_button).setVisibility(View.VISIBLE);
        mode = "delete";
    }

    public void done(View view) {
        findViewById(R.id.remove_button).setVisibility(View.VISIBLE);
        findViewById(R.id.add_button).setVisibility(View.VISIBLE);
        findViewById(R.id.done_button).setVisibility(View.GONE);
        mode = "edit";
    }
}
