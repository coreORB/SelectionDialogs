package pl.coreorb.selectiondialogs.utils;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import pl.coreorb.selectiondialogs.data.SelectableColor;
import pl.coreorb.selectiondialogs.data.SelectableIcon;
import pl.coreorb.selectiondialogs.data.SelectableItemNameComparator;

/**
 * Class with util methods.
 */
public class Utils {

    private static final String LOG_TAG = Utils.class.getSimpleName();

    /**
     * Converts 3 resource arrays to ArrayList<SelectableColor> of colors. Colors can be sorted by name at runtime, note that colors will be sorted in language displays to user.
     * Note: all arrays must have equal lengths.
     *
     * @param context     current context
     * @param sortByName  if true colors will be sorted by name, otherwise colors will be left as they are
     * @param idsArray    array resource id to use as colors ids
     * @param namesArray  array resource id to use as colors names
     * @param colorsArray array resource id to use as colors, well color values
     * @return colors ArrayList
     */
    public static ArrayList<SelectableColor> convertResourceArraysToColorsArrayList(Context context, boolean sortByName, @ArrayRes int idsArray, @ArrayRes int namesArray, @ArrayRes int colorsArray) {
        //get and check arrays
        String[] ids = context.getResources().getStringArray(idsArray);
        int[] colors = context.getResources().getIntArray(colorsArray);
        String[] names = context.getResources().getStringArray(namesArray);

        if (ids.length != colors.length && ids.length != names.length) {
            Log.e(LOG_TAG, "convertResourceArraysToColorsArrayList(): Arrays must have equals lengths!");
            return null;
        }

        //create ArrayList
        ArrayList<SelectableColor> result = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            result.add(new SelectableColor(ids[i], names[i], colors[i]));
        }

        //sort by names
        if (sortByName) {
            Collections.sort(result, new SelectableItemNameComparator<SelectableColor>());
        }

        return result;
    }

    /**
     * Converts 3 resource arrays to ArrayList<SelectableIcons> of icons. Icons can be sorted by name at runtime, note that icons will be sorted in language displays to user.
     * Note: all arrays must have equal lengths.
     *
     * @param context        current context
     * @param sortByName     if true colors will be sorted by name, otherwise colors will be left as they are
     * @param idsArray       array resource id to use as icons ids
     * @param namesArray     array resource id to use as icons names
     * @param drawablesArray array resource id to use as icons drawables
     * @return icons ArrayList
     */
    public static ArrayList<SelectableIcon> convertResourceArraysToIconsArrayList(Context context, boolean sortByName, @ArrayRes int idsArray, @ArrayRes int namesArray, @ArrayRes int drawablesArray) {
        //get and check arrays
        String[] ids = context.getResources().getStringArray(idsArray);
        int[] drawables = context.getResources().getIntArray(drawablesArray);
        String[] names = context.getResources().getStringArray(namesArray);

        if (ids.length != drawables.length && ids.length != names.length) {
            Log.e(LOG_TAG, "convertResourceArraysToIconsArrayList(): Arrays must have equals lengths!");
            return null;
        }

        //create ArrayList
        ArrayList<SelectableIcon> result = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            result.add(new SelectableIcon(ids[i], names[i], drawables[i]));
        }

        //sort by names
        if (sortByName) {
            Collections.sort(result, new SelectableItemNameComparator<SelectableIcon>());
        }

        return result;
    }

}
