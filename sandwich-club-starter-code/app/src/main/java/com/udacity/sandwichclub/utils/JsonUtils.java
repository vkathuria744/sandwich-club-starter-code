package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class JsonUtils {

    private static String sMainName, sPlaceOfOrigin, sDescription, sImage;

    private static JSONArray sAlsoKnownAsArray, sIngredientsArray;

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = new Sandwich();
        ArrayList<String> alsoKnownAs = new ArrayList<>();
        ArrayList<String> ingredients = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            sMainName = jsonObject.getJSONObject("name").getString("mainName");
            sAlsoKnownAsArray = jsonObject.getJSONObject("name").getJSONArray("alsoKnownas");

            for(int i=0 ; i < sAlsoKnownAsArray.length() ; i++)

                alsoKnownAs.add(sAlsoKnownAsArray.getString(i));

            sPlaceOfOrigin = jsonObject.getString("placeOfOrigin");

            sDescription = jsonObject.getString("description");

            sImage = jsonObject.getString("image");

            sIngredientsArray = jsonObject.getJSONArray("ingredients");

                for(int i = 0 ; i < sIngredientsArray.length() ; i++)

                    ingredients.add(sIngredientsArray.getString(i));

        }
            catch (JSONException e)
            {
                Log.e("JsonUtils", e.getMessage());
            }


                sandwich.setMainName(sMainName);
                sandwich.setAlsoKnownAs(alsoKnownAs);
                sandwich.setPlaceOfOrigin(sPlaceOfOrigin);
                sandwich.setDescription(sDescription);
                sandwich.setImage(sImage);
                sandwich.setIngredients(ingredients);








        return sandwich;
    }
}
