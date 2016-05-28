package pl.coreorb.selectiondialogs.utils;

import android.content.Context;

import java.util.ArrayList;

import pl.coreorb.selectiondialogs.R;
import pl.coreorb.selectiondialogs.data.SelectableColor;

/**
 * Class containing methods with predefined color palettes.
 */
public class ColorPalettes {

    /**
     * Creates default Material Design colors (500 - primary) as ArrayList. Colors can be sorted by name at runtime, note that colors will be sorted in language displays to user.
     *
     * @param context    current context
     * @param sortByName if true colors will be sorted by name, otherwise colors will be left as they are
     * @return ArrayList of Material Desing colors as {@link SelectableColor}
     */
    public static ArrayList<SelectableColor> loadMaterialDesignColors500(Context context, boolean sortByName) {
        return Utils.convertResourceArraysToColorsArrayList(context,
                sortByName,
                R.array.selectiondialogs_materialdesign500_ids,
                R.array.selectiondialogs_materialdesign500_names,
                R.array.selectiondialogs_materialdesign500_colors);
    }


}
