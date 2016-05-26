package pl.coreorb.selectiondialogs.views;

import android.content.Context;
import android.util.AttributeSet;

import pl.coreorb.selectiondialogs.data.SelectionDialogsIcon;

/**
 * View class used to display selected icon from this library dialogs.
 */
public class SelectedIconView extends PrimitiveSelectedItemView<SelectionDialogsIcon> {
    public SelectedIconView(Context context) {
        super(context);
    }

    public SelectedIconView(Context context, int id) {
        super(context, id);
    }

    public SelectedIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectedIconView(Context context, AttributeSet attrs, Integer id) {
        super(context, attrs, id);
    }

    public void setItem(SelectionDialogsIcon item) {
        currentItem = item;
        iconIV.setImageResource(item.getDrawableResId());
        textTV.setText(item.getName());
    }
}
