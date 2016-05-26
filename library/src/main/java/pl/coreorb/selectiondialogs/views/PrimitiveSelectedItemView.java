package pl.coreorb.selectiondialogs.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.concurrent.locks.Condition;

import pl.coreorb.selectiondialogs.R;
import pl.coreorb.selectiondialogs.data.PrimitiveSelectionDialogsItem;

/**
 * Class of primitive view used to display selected item from this library dialogs.
 */
public abstract class PrimitiveSelectedItemView<T extends PrimitiveSelectionDialogsItem> extends LinearLayout {

    private static String TAG = PrimitiveSelectedItemView.class.getSimpleName();

    private TextView hintTV;
    private RelativeLayout buttonRL;
    protected TextView textTV;
    protected ImageView iconIV;

    protected T currentItem;

    public PrimitiveSelectedItemView(Context context) {
        this(context, null);
    }

    public PrimitiveSelectedItemView(Context context, int id) {
        this(context, null, id);
    }

    public PrimitiveSelectedItemView(Context context, AttributeSet attrs) {
        this(context, attrs, null);
    }

    public PrimitiveSelectedItemView(Context context, AttributeSet attrs, Integer id) {
        super(context, attrs);
        if (id != null) this.setId(id);

        Log.d(TAG, "SelectedItemView() [id: " + getId() + "]");

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.PrimitiveSelectedItemView, 0, 0);
        String hint = a.getString(R.styleable.PrimitiveSelectedItemView_hint);
        int icon = a.getResourceId(R.styleable.PrimitiveSelectedItemView_buttonIcon, -1);
        int tint = a.getInt(R.styleable.PrimitiveSelectedItemView_buttonIconTint, -1);
        String text = a.getString(R.styleable.PrimitiveSelectedItemView_buttonText);
        //TODO: research and add onClick
        a.recycle();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.selectiondialogs_selected_item_view, this, true);

        hintTV = (TextView) rootView.findViewById(R.id.selectiondialogs_hint_tv);
        buttonRL = (RelativeLayout) rootView.findViewById(R.id.selectiondialogs_button_ll);
        textTV = (TextView) rootView.findViewById(R.id.selectiondialogs_text_tv);
        iconIV = (ImageView) rootView.findViewById(R.id.selectiondialogs_icon_iv);

        if (hint != null) hintTV.setText(hint);
        if (icon != -1) iconIV.setImageResource(icon);
        if (tint != -1) iconIV.setColorFilter(tint);
        if (text != null) textTV.setText(text);

    }

    public void setOnClickListener(OnClickListener onClickListener) {
        buttonRL.setOnClickListener(onClickListener);
    }

    /**
     * Sets item to be displayed in this view.
     * @param item item do display
     */
    public abstract void setItem(T item);

    public T getItem() {
        return currentItem;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        Log.d(TAG, "onSaveInstanceState() [id: " + getId() + "]");
        SavedState<T> ss = new SavedState<>(superState);
        ss.stateToSave = currentItem;
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        Log.d(TAG, "onRestoreInstanceState() [id: " + getId() + "]");
        SavedState<T> ss = (SavedState<T>) state;
        if (ss.stateToSave != null) {
            setItem(ss.stateToSave);
        }
        super.onRestoreInstanceState(ss.getSuperState());
    }

    static class SavedState<T extends PrimitiveSelectionDialogsItem> extends BaseSavedState {
        T stateToSave;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.stateToSave = in.readParcelable(Condition.class.getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeParcelable(this.stateToSave, flags);
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
