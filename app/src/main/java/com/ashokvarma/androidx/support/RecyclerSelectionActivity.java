package com.ashokvarma.androidx.support;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.ashokvarma.androidx.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.selection.OnContextClickListener;
import androidx.recyclerview.selection.OperationMonitor;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;


public class RecyclerSelectionActivity extends AppCompatActivity {

    SelectionTracker<String> selectionTracker;
    Toolbar toolbarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_recycler_selection_act);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        toolbarView = findViewById(R.id.tool_bar);

        List<Pokemon> pokemonData = Pokemon.catchThemAll();
        PokemonRecyclerAdapter pokemonRecyclerAdapter = new PokemonRecyclerAdapter(this, pokemonData);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        // adapter must be set before building selectionTracker
        recyclerView.setAdapter(pokemonRecyclerAdapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(this, R.dimen.pokemon_item_spacing));

        selectionTracker = new SelectionTracker.Builder<>(
                "pokemon-selection",//unique id
                recyclerView,
                new PokemonItemKeyProvider(pokemonData),
                new PokemonItemDetailsLookup(recyclerView),
                StorageStrategy.createStringStorage())
                .build();

        pokemonRecyclerAdapter.setSelectionTracker(selectionTracker);
//        pokemonRecyclerAdapter.setPokemonClickListener(new PokemonRecyclerAdapter.PokemonClickListener() {
//            @Override
//            public void onCLick(Pokemon pokemon) {
//                Toast.makeText(RecyclerSelectionActivity.this, pokemon.name + " clicked", Toast.LENGTH_LONG).show();
//            }
//        });

        setUpViews();
    }

    void setUpViews() {
        toolbarView.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectionTracker.clearSelection();
            }
        });

        updateViewsBasedOnSelection();
        selectionTracker.addObserver(new SelectionTracker.SelectionObserver<String>() {
            @Override
            public void onSelectionChanged() {
                updateViewsBasedOnSelection();
                super.onSelectionChanged();
            }

            @Override
            public void onSelectionRestored() {
                updateViewsBasedOnSelection();
                super.onSelectionRestored();
            }
        });
    }

    private void updateViewsBasedOnSelection() {
        if (selectionTracker.hasSelection()) {
            toolbarView.setVisibility(View.VISIBLE);
            toolbarView.setTitle(selectionTracker.getSelection().size() + " selected");
        } else {
            toolbarView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        selectionTracker.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        selectionTracker.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    private static class PokemonItemKeyProvider extends ItemKeyProvider<String> {

        private final Map<String, Integer> mKeyToPosition;
        private List<Pokemon> mPokemonList;

        PokemonItemKeyProvider(List<Pokemon> pokemonList) {
            super(SCOPE_CACHED);
            mPokemonList = pokemonList;

            mKeyToPosition = new HashMap<>(mPokemonList.size());
            int i = 0;
            for (Pokemon pokemon : pokemonList) {
                mKeyToPosition.put(pokemon.id, i);
                i++;
            }
        }

        @Nullable
        @Override
        public String getKey(int i) {
            return mPokemonList.get(i).id;// directly from position to key
        }

        @Override
        public int getPosition(@NonNull String s) {
            return mKeyToPosition.get(s);
        }
    }

    private static class PokemonItemDetailsLookup extends ItemDetailsLookup<String> {
        RecyclerView mRecyclerView;

        PokemonItemDetailsLookup(RecyclerView recyclerView) {
            this.mRecyclerView = recyclerView;
        }

        @Nullable
        @Override
        public ItemDetails<String> getItemDetails(@NonNull MotionEvent motionEvent) {
            View view = mRecyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            if (view != null) {
                RecyclerView.ViewHolder viewHolder = mRecyclerView.getChildViewHolder(view);
//                int position = viewHolder.getAdapterPosition();
                if (viewHolder instanceof PokemonRecyclerAdapter.PokemonViewHolder) {
                    return ((PokemonRecyclerAdapter.PokemonViewHolder) viewHolder).getPokemonItemDetails(motionEvent);
                }
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        if (selectionTracker.hasSelection()) {
            selectionTracker.clearSelection();
        } else {
            super.onBackPressed();
        }
    }
}
