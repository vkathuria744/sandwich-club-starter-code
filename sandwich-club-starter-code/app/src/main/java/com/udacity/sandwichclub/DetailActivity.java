package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView mOriginTv , mAlsoKnownTv , mDescriptionTv , mIngredientsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        mOriginTv = findViewById(R.id.origin_tv);
        mAlsoKnownTv = findViewById(R.id.also_known_tv);
        mDescriptionTv = findViewById(R.id.description_tv);
        mIngredientsTv = findViewById(R.id.ingredients_tv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> arrayList = (ArrayList) sandwich.getAlsoKnownAs();

        for (int i = 0; i < arrayList.size(); i++)
        {
            stringBuilder.append(arrayList.get(i));

            if((arrayList.size() - 1) != 1)
                stringBuilder.append("\n");
    }

        mAlsoKnownTv.setText(stringBuilder.toString());
            if (mAlsoKnownTv.getText(). length() == 0)
                mAlsoKnownTv.setVisibility(View.GONE);

            stringBuilder.delete(0, stringBuilder.length());

            mOriginTv.setText(sandwich.getPlaceOfOrigin());
                if (mOriginTv.getText(). length() == 0)
                    mOriginTv.setVisibility(View.GONE);


        mDescriptionTv.setText(sandwich.getDescription());

                    arrayList = (ArrayList) sandwich.getIngredients();
                    for (int i = 0 ; i < arrayList.size() ; i++)
                    {
                        stringBuilder.append(arrayList.get(i));
                        if ((arrayList.size() - 1) != 1)

                            stringBuilder.append("\n");
                    }

                        mIngredientsTv.setText(stringBuilder.toString());


    }
}
