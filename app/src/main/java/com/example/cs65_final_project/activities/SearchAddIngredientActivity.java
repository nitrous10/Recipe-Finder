package com.example.cs65_final_project.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.cs65_final_project.FirebaseDatabaseHelper;
import com.example.cs65_final_project.Ingredient;
import com.example.cs65_final_project.R;
import com.example.cs65_final_project.adapters.SearchIngredientAdapter;
import com.example.cs65_final_project.exceptions.SpoonacularException;
import com.example.cs65_final_project.spoonacular.SpoonacularGatewayController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SearchAddIngredientActivity extends AppCompatActivity implements ListView.OnItemClickListener,
        SearchView.OnQueryTextListener, View.OnClickListener {

    private List<Ingredient> results;
    private SpoonacularGatewayController controller;
    private Handler resultsHandler;
    private SearchIngredientAdapter adapter;

    private FloatingActionButton backButton;

    public static final int RESULT_NUM = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_add_ingredient);
        setTitle("Search to add Ingredient");

        backButton = findViewById(R.id.leave_ingredient);
        backButton.setOnClickListener(this);

        controller = new SpoonacularGatewayController();
        results = new ArrayList<>();
        HandlerThread handlerThread = new HandlerThread("IngredientResultThread");
        handlerThread.start();
        resultsHandler = new Handler(handlerThread.getLooper());

        SearchView searchView = findViewById(R.id.ingredient_search_view);
        searchView.setOnQueryTextListener(this);

        ListView listView = findViewById(R.id.ingredient_list_view);
        adapter = new SearchIngredientAdapter(this, results);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        updateListView(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        Log.d("Ingredient Search", "Results list: " + results);
        updateListView(s);
        return false;
    }

    private void updateListView(String s){
        resultsHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    results = controller.getIngredients(s, RESULT_NUM, true);
                } catch (SpoonacularException e) {
                    e.printStackTrace();
                }
            }
        });
        if(results != null){
            adapter.updateData(results);
            adapter.notifyDataSetChanged();
        }
    }

    /** When an ingredient is chosen, prompt for amount*/
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Ingredient ingredientChosen = (Ingredient)adapterView.getItemAtPosition(i);
        Log.d("debug", "OnItemClick called for ingredient: " + ingredientChosen);

        // Start dialog for amount
        View dialogView = LayoutInflater.from(this).inflate(R.layout.are_you_sure, null);
        TextView text = dialogView.findViewById(R.id.saving_ingredient);
        text.setText(ingredientChosen.getName());
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Are you sure?")
                .setView(dialogView).create();

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Fix this part
                FirebaseDatabaseHelper.addIngredient(SearchAddIngredientActivity.this,
                        ingredientChosen.getName(), ingredientChosen.getAisle(), -1);
            }
        });

        dialog.show();
    }

    public void onClick(View v) {
        if (v.getId() == R.id.leave_ingredient) {
            finish();
        }
    }
}