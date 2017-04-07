package com.tt.gwentapp.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.tt.gwentapp.models.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tturcic
 *         \date 6.4.2017.
 */
public class StringUtils {

    public static final String EMPTY = "";

    @NonNull
    public static String createTagsForCard(Card card){
        List<String> tags = new ArrayList<>();
        if(!card.getLane().isEmpty())
            tags.add(card.getLane());
        if(!card.getLoyalty().isEmpty())
            tags.add(card.getLoyalty());
        if(!card.getTraits().isEmpty())
            tags.add(card.getTraits());

        if(tags.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            final String SEPARATOR = "\n";
            for (int i = 0; i < tags.size(); i++) {
                stringBuilder.append(tags.get(i));
                if (i != tags.size() - 1)
                    stringBuilder.append(SEPARATOR);
            }
            return stringBuilder.toString()
                    .replace("/", "\n")
                    .replace(", ", "\n");
        }
        return EMPTY;
    }
}
