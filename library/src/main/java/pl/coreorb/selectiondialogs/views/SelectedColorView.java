package pl.coreorb.selectiondialogs.views;

import android.content.Context;
import android.util.AttributeSet;

import pl.coreorb.selectiondialogs.data.SelectionDialogsColor;

/**
 * View class used to display selected color from this library dialogs.
 */
public class SelectedColorView extends PrimitiveSelectedItemView<SelectionDialogsColor> {
    public SelectedColorView(Context context) {
        super(context);
    }

    public SelectedColorView(Context context, int id) {
        super(context, id);
    }

    public SelectedColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectedColorView(Context context, AttributeSet attrs, Integer id) {
        super(context, attrs, id);
    }

    public void setItem(SelectionDialogsColor item) {
        currentItem = item;
        iconIV.setColorFilter(item.getColor());
        textTV.setText(item.getName());
    }
}
