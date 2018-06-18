package com.ashokvarma.androidx.recyclerview.selection;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description
 *
 * @author ashok
 * @version 1.0
 * @since 17/06/18
 */
public class Pokemon {
    public String id;
    public String name;
    public int number;
    public int bgColor;
    public String type;

    private static final String[] pokemonNames = {"Bulbasaur", "Ivysaur", "Venusaur", "Charmander", "Charmeleon", "Charizard", "Squirtle", "Wartortle", "Blastoise", "Caterpie", "Metapod", "Butterfree", "Weedle", "Kakuna", "Beedrill", "Pidgey", "Pidgeotto", "Pidgeot", "Ratttata", "Raticate", "Spearow", "Fearow", "Ekans", "Arbok", "Pikachu", "Raichu"};
    private static final String[] pokemonTypes = {"Grass", "Grass", "Grass", "Fire", "Fire", "Fire", "Water", "Water", "Water", "Bug", "Bug", "Bug", "Poison", "Poison", "Poison", "Flying", "Flying", "Flying", "Normal", "Normal", "Flying", "Flying", "Poison", "Poison", "Electric", "Electric"};
    private static final int[] pokemonColors = {-10513523, -16740296, -16218056, -3641328, -3133408, -4164864, -10056326, -9666394, -13600600, -11698113, -11694816, -8884064, -5011610, -5855738, -4551424, -4546452, -4148372, -4543136, -8372096, -5872128, -5414836, -5872078, -8886104, -9410416, -4151808, -3763456,};

    public static List<Pokemon> catchThemAll() {
        List<Pokemon> list = new ArrayList<>(26);
        int i = 1;
        while (i < 27) {
            Pokemon pokemon = new Pokemon();
            pokemon.id = pokemonNames[i - 1].toLowerCase();
            pokemon.name = pokemonNames[i - 1];
            pokemon.number = i;
            pokemon.type = pokemonTypes[i - 1];
            pokemon.bgColor = pokemonColors[i - 1];
            list.add(pokemon);
            i++;
        }
        return list;
    }
}
