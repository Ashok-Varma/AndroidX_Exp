package com.ashokvarma.androidx.slice;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.text.TextUtils;

import com.ashokvarma.androidx.R;
import com.ashokvarma.androidx.design.chip.ChipActivity;
import com.ashokvarma.androidx.recyclerview.selection.Pokemon;
import com.ashokvarma.androidx.recyclerview.selection.RecyclerSelectionActivity;
import com.ashokvarma.androidx.util.ResourcesUtils;

import java.util.List;

import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.SliceProvider;
import androidx.slice.builders.GridRowBuilder;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;


/**
 * Class description
 *
 * @author ashok
 * @version 1.0
 * @since 19/06/18
 */
@TargetApi(28)
public class AndroidXSliceProvider extends SliceProvider {

    @Override
    public boolean onCreateSliceProvider() {
        return false;
    }

    @Override
    public Slice onBindSlice(Uri sliceUri) {
        if (!TextUtils.isEmpty(sliceUri.getPath())) {
            if (sliceUri.getPath().startsWith("/pokemon")) {
                return buildPokemonSlice(sliceUri);
            } else if (sliceUri.getPath().startsWith("/pokemon_type")) {
                return buildPokemonTypeSlice(sliceUri);
            }
        }
        return null;
    }

    private Slice buildPokemonTypeSlice(Uri sliceUri) {

        return buildPokemonSlice(sliceUri);
    }

    List<Pokemon> pokemonList = null;
    boolean pokemonDataLoaded = false;

    private Slice buildPokemonSlice(Uri sliceUri) {
        //        sliceUri.getQueryParameter("id");
        int id = 1;

        ListBuilder listBuilder = new ListBuilder(getContext(), sliceUri, ListBuilder.INFINITY);
        ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder(listBuilder);
        if (!pokemonDataLoaded) {
            loadPokemonDetails(id);
            rowBuilder.setTitle("Pokemon").setSubtitle(null, true);

            listBuilder.addRow(rowBuilder);
        } else {
            Pokemon pokemon = getPokemonForId(id);
            rowBuilder.setTitle("Pokemon " + pokemon.name)
                    .setSubtitle(pokemon.type)
                    .addEndItem(getPokemonIntent())
                    .addEndItem(getChipIntent());

            listBuilder.addRow(rowBuilder);

            GridRowBuilder gridRowBuilder = new GridRowBuilder(listBuilder);

            IconCompat icon = IconCompat.createFromIcon(Icon.createWithResource(getContext(), ResourcesUtils.getDrawResIdentifier("pokemon_" + pokemon.number)));
            gridRowBuilder.addCell(
                    new GridRowBuilder.CellBuilder(gridRowBuilder)
                            .addImage(icon, ListBuilder.LARGE_IMAGE)
                            .addTitleText(pokemon.name)
                            .addText("#" + pokemon.number)
            );
            listBuilder.addGridRow(gridRowBuilder);
        }
        return listBuilder.build();
    }

    private Pokemon getPokemonForId(int id) {
        return pokemonList.get(id);
    }

    private void loadPokemonDetails(int id) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    // simulate data delay
                    sleep(1500);

                    pokemonList = Pokemon.catchThemAll();
                    pokemonDataLoaded = true;
                    getContext().getContentResolver().notifyChange(getUri(getContext(), "pokemon"), null);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();


    }

    public SliceAction getPokemonIntent() {
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, new Intent(getContext(), RecyclerSelectionActivity.class), 0);
        IconCompat icon = IconCompat.createFromIcon(Icon.createWithResource(getContext(), R.drawable.ic_adb_primary_24dp));
        return new SliceAction(pendingIntent, icon, "shortlist pokemon");
    }

    public SliceAction getChipIntent() {
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, new Intent(getContext(), ChipActivity.class), 0);
        IconCompat icon = IconCompat.createFromIcon(Icon.createWithResource(getContext(), R.drawable.ic_favorite_red_24dp));
        return new SliceAction(pendingIntent, icon, "shortlist pokemon");
    }


    public static Uri getUri(Context context, String path) {
        return new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(context.getPackageName())
                .appendPath(path)
                .build();
    }
}
