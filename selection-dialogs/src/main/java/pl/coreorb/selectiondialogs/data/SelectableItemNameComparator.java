package pl.coreorb.selectiondialogs.data;

import java.util.Comparator;

/**
 * Class to compare name of item. Used to sort items by name in dialogs.
 */
public class SelectableItemNameComparator<T extends PrimitiveSelectableItem> implements Comparator<T> {

    @Override
    public int compare(T o1, T o2) {
        return o1.getName().compareTo(o2.getName());
    }

}
