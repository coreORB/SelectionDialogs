package pl.coreorb.selectiondialogs.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import pl.coreorb.selectiondialogs.R;
import pl.coreorb.selectiondialogs.data.SelectableColor;
import pl.coreorb.selectiondialogs.data.SelectableIcon;

/**
 * View class used to display selected item (icon, color or text) from this library dialogs.
 */
public class SelectedItemView extends LinearLayout {

    private static String TAG = SelectedItemView.class.getSimpleName();

    private TextView hintTV;
    private RelativeLayout buttonRL;
    private TextView nameTV;
    private ImageView iconIV;

    private String currentHint;
    private String currentItemId;
    private String currentItemName;
    private
    @DrawableRes
    int currentItemIcon;
    private
    @ColorInt
    int currentItemColor;

    public SelectedItemView(Context context) {
        this(context, null);
    }

    public SelectedItemView(Context context, int id) {
        this(context, null, id);
    }

    public SelectedItemView(Context context, AttributeSet attrs) {
        this(context, attrs, null);
    }

    public SelectedItemView(Context context, AttributeSet attrs, Integer id) {
        super(context, attrs);
        if (id != null) this.setId(id);

        Log.d(TAG, "SelectedItemView() [id: " + getId() + "]");

        //get style of view
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.SelectedItemView, 0, 0);
        String hint = a.getString(R.styleable.SelectedItemView_hint);
        String itemId = a.getString(R.styleable.SelectedItemView_itemId);
        int itemIcon = a.getResourceId(R.styleable.SelectedItemView_itemIcon, -1);
        int itemColor = a.getInt(R.styleable.SelectedItemView_itemColor, -1);
        String itemName = a.getString(R.styleable.SelectedItemView_itemName);
        a.recycle();

        //inflate and find child views
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.selectiondialogs_selected_item_view, this, true);

        hintTV = (TextView) rootView.findViewById(R.id.selectiondialogs_hint_tv);
        buttonRL = (RelativeLayout) rootView.findViewById(R.id.selectiondialogs_button_ll);
        nameTV = (TextView) rootView.findViewById(R.id.selectiondialogs_name_tv);
        iconIV = (ImageView) rootView.findViewById(R.id.selectiondialogs_icon_iv);

        //update views and set up initial values for current variables
        if (hint != null) {
            hintTV.setText(hint);
            currentHint = hint;
        } else {
            currentHint = getResources().getString(R.string.selectiondialogs_default_hint);
        }

        if (itemId != null) {
            currentItemId = itemId;
        } else {
            currentItemId = getResources().getString(R.string.selectiondialogs_default_item_id);
        }

        if (itemName != null) {
            nameTV.setText(itemName);
            currentItemName = itemName;
        } else {
            currentItemName = getResources().getString(R.string.selectiondialogs_default_item_name);
        }

        if (itemIcon != -1) {
            iconIV.setImageResource(itemIcon);
            currentItemIcon = itemIcon;
        } else {
            currentItemIcon = R.drawable.selectiondialogs_default_item_icon;
        }

        if (itemColor != -1) {
            iconIV.setColorFilter(itemColor);
            currentItemColor = itemColor;
        } else {
            currentItemColor = ContextCompat.getColor(context, R.color.selectiondialogs_default_item_color);
        }
    }

    /**
     * Sets listener to call when button is being clicked.
     *
     * @param onClickListener listener to use
     */
    public void setOnClickListener(OnClickListener onClickListener) {
        buttonRL.setOnClickListener(onClickListener);
    }

    /**
     * Sets hint displayed above button.
     *
     * @param hint hint to display
     */
    public void setHint(String hint) {
        currentHint = hint;
        hintTV.setText(hint);
    }

    /**
     * Sets hint displayed above button.
     *
     * @param hintResId resource id of string to use as a hint
     */
    public void setHint(@StringRes int hintResId) {
        currentHint = getResources().getString(hintResId);
        hintTV.setText(hintResId);
    }

    /**
     * Returns hint displayed above button.
     *
     * @return hint displayed above button
     */
    public String getHint() {
        return currentHint;
    }

    /**
     * Sets name of currently displayed item.
     *
     * @param name name of currently displayed item
     */
    public void setSelectedName(String name) {
        currentItemName = name;
        nameTV.setText(name);
    }

    /**
     * Sets name of currently displayed item.
     *
     * @param nameResId resource id of string to use as name of currently displayed item
     */
    public void setSelectedName(@StringRes int nameResId) {
        currentItemName = getResources().getString(nameResId);
        nameTV.setText(nameResId);
    }

    /**
     * Returns currently displayed name of item selected.
     *
     * @return name of item selected
     */
    public String getSelectedName() {
        return currentItemName;
    }

    /**
     * Sets currently displayed icon.
     *
     * @param icon icon to display
     */
    public void setSelectedIcon(SelectableIcon icon) {
        currentItemId = icon.getId();
        currentItemName = icon.getName();
        currentItemIcon = icon.getDrawableResId();

        iconIV.setImageResource(icon.getDrawableResId());
        nameTV.setText(icon.getName());
    }

    /**
     * Returns currently displayed icon.
     *
     * @return currently displayed icon
     */
    public SelectableIcon getSelectedIcon() {
        return new SelectableIcon(currentItemId, currentItemName, currentItemIcon);

    }

    /**
     * Sets currently displayed color.
     *
     * @param color color to display
     */
    public void setSelectedColor(SelectableColor color) {
        currentItemId = color.getId();
        currentItemName = color.getName();
        currentItemColor = color.getColorValue();

        iconIV.setColorFilter(color.getColorValue());
        nameTV.setText(color.getName());
    }

    /**
     * Returns currently displayed color.
     *
     * @return currently displayed color
     */
    public SelectableColor getSelectedColor() {
        return new SelectableColor(currentItemId, currentItemName, currentItemColor);

    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        Log.d(TAG, "onSaveInstanceState() [id: " + getId() + "]");
        SavedState ss = new SavedState(superState);
        ss.hint = currentHint;
        ss.id = currentItemId;
        ss.name = currentItemName;
        ss.iconDrawableResId = currentItemIcon;
        ss.iconColorValue = currentItemColor;
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        Log.d(TAG, "onRestoreInstanceState() [id: " + getId() + "]");
        SavedState ss = (SavedState) state;

        currentHint = ss.hint;
        currentItemId = ss.id;
        currentItemName = ss.name;
        currentItemIcon = ss.iconDrawableResId;
        currentItemColor = ss.iconColorValue;

        hintTV.setText(currentHint);
        nameTV.setText(currentItemName);
        iconIV.setImageResource(currentItemIcon);
        iconIV.setColorFilter(currentItemColor);

        super.onRestoreInstanceState(ss.getSuperState());
    }

    static class SavedState extends BaseSavedState {
        String hint;
        String id;
        String name;
        Integer iconDrawableResId;
        Integer iconColorValue;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.id = in.readString();
            this.name = in.readString();
            this.iconDrawableResId = in.readInt();
            this.iconColorValue = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeString(this.id);
            out.writeString(this.name);
            out.writeInt(this.iconDrawableResId);
            out.writeInt(this.iconColorValue);
        }

        public static final Creator<SavedState> CREATOR =
                new Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }

}
