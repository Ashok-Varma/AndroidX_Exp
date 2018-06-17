package com.ashokvarma.androidx.support;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.ashokvarma.androidx.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;


public class RecyclerSelectionActivity extends AppCompatActivity {

    SelectionTracker<String> selectionTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_recycler_selection_act);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        List<Pokemon> pokemonData = Pokemon.catchThemAll();
        PokemonRecyclerAdapter pokemonRecyclerAdapter = new PokemonRecyclerAdapter(this, pokemonData);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        // adapter must be set before building selectionTracker
        recyclerView.setAdapter(pokemonRecyclerAdapter);

        selectionTracker = new SelectionTracker.Builder<>(
                "pokemon-selection",//unique id
                recyclerView,
                new PokemonItemKeyProvider(pokemonData),
                new PokemonItemDetailsLookup(recyclerView),
                StorageStrategy.createStringStorage())
                .build();
//                .withOnItemActivatedListener(new OnItemActivatedListener<String>() {
//                    @Override
//                    public boolean onItemActivated(@NonNull ItemDetailsLookup.ItemDetails<String> itemDetails, @NonNull MotionEvent motionEvent) {
//                        return true;
//                    }
//                })
//                .withBandPredicate(new BandPredicate() {
//                    @Override
//                    public boolean canInitiate(MotionEvent motionEvent) {
//                        return true;
//                    }
//                })

        pokemonRecyclerAdapter.setSelectionTracker(selectionTracker);


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

}
