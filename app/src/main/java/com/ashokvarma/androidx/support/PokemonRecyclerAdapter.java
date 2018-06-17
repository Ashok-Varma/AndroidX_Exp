package com.ashokvarma.androidx.support;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashokvarma.androidx.R;
import com.ashokvarma.androidx.util.ResourcesUtils;

import java.util.List;

import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;

/**
 * Class description
 *
 * @author ashok
 * @version 1.0
 * @since 16/06/18
 */
public class PokemonRecyclerAdapter extends RecyclerView.Adapter<PokemonRecyclerAdapter.PokemonViewHolder> {
    @NonNull
    private final LayoutInflater mLayoutInflater;
    @NonNull
    private final List<Pokemon> mPokemons;
    @Nullable
    private SelectionTracker<String> mSelectionTracker;

    PokemonRecyclerAdapter(@NonNull Context context, @NonNull List<Pokemon> pokemons) {
        mLayoutInflater = LayoutInflater.from(context);
        mPokemons = pokemons;
    }

    public void setSelectionTracker(SelectionTracker<String> selectionTracker) {
        this.mSelectionTracker = selectionTracker;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PokemonViewHolder(mLayoutInflater.inflate(R.layout.pokemon_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder pokemonViewHolder, int position) {
        Pokemon pokemon = mPokemons.get(position);

        boolean isSelected = false;
        if (mSelectionTracker != null) {
            if (mSelectionTracker.isSelected(pokemon.id)) {
                isSelected = true;
            }
        }

        pokemonViewHolder.bind(position, isSelected, pokemon);
    }

    @Override
    public int getItemCount() {
        return mPokemons.size();
    }

    static class PokemonViewHolder extends RecyclerView.ViewHolder {
        final TextView numberView;
        final TextView nameView;
        final TextView typeView;
        final CheckBox checkboxView;
        final ImageView imageView;
        final View bgColorView;
        private final PokemonItemDetails pokemonItemDetails;

        PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            numberView = itemView.findViewById(R.id.pokemon_list_number);
            nameView = itemView.findViewById(R.id.pokemon_list_name);
            typeView = itemView.findViewById(R.id.pokemon_list_type);
            checkboxView = itemView.findViewById(R.id.pokemon_list_checkbox);
            imageView = itemView.findViewById(R.id.pokemon_list_image);
            bgColorView = itemView.findViewById(R.id.pokemon_list_background);
            pokemonItemDetails = new PokemonItemDetails();
        }

        void bind(int position, boolean isSelected, Pokemon pokemon) {
            pokemonItemDetails.position = position;
            pokemonItemDetails.identifier = pokemon.id;

            numberView.setText("#" + pokemon.number);
            nameView.setText(pokemon.name);
            typeView.setText(pokemon.type);

            bgColorView.setBackgroundColor(pokemon.bgColor);
            imageView.setImageResource(ResourcesUtils.getDrawResIdentifier("pokemon_" + pokemon.number));

            checkboxView.setChecked(isSelected);
            itemView.setActivated(isSelected);
            if (isSelected) {
                bgColorView.setAlpha(0.5f);
            } else {
                bgColorView.setAlpha(0.1f);
            }
        }

        public ItemDetailsLookup.ItemDetails<String> getPokemonItemDetails(@NonNull MotionEvent motionEvent) {
            return pokemonItemDetails;
        }
    }

    static class PokemonItemDetails extends ItemDetailsLookup.ItemDetails<String> {
        int position;
        String identifier;

        @Override
        public int getPosition() {
            return position;
        }

        @Nullable
        @Override
        public String getSelectionKey() {
            return identifier;
        }

        @Override
        public boolean inSelectionHotspot(@NonNull MotionEvent e) {
            return false;
        }

        @Override
        public boolean inDragRegion(@NonNull MotionEvent e) {
            return true;
        }
    }
}