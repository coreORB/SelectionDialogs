package pl.coreorb.selectiondialogs.data;

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;

/**
 * Object defining selectable colorValue in this library.
 */
public class SelectableColor extends PrimitiveSelectableItem {

    private
    @ColorInt
    int colorValue;

    public SelectableColor() {
        super();
        colorValue = 0;
    }

    public SelectableColor(Parcel in) {
        super(in);
        colorValue = in.readInt();
    }

    public SelectableColor(String id, String name, @ColorInt int colorValue) {
        super(id, name);
        this.colorValue = colorValue;
    }

    public SelectableColor(Context context, @StringRes int idResId, @StringRes int nameResId, @ColorInt int colorValue) {
        super(context, idResId, nameResId);
        this.colorValue = colorValue;
    }

    /**
     * Returns colorValue integer value of this colorValue.
     * @return colorValue integer value of this colorValue
     */
    public int getColorValue() {
        return colorValue;
    }

    /**
     * Sets colorValue integer value of this colorValue.
     * @param colorValue colorValue integer value to use as this colorValue value
     */
    public void setColorValue(int colorValue) {
        this.colorValue = colorValue;
    }

    @Override
    public String toString() {
        return "SelectableColor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", colorValue=" + colorValue +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(colorValue);
    }

    public static final Creator<SelectableColor> CREATOR = new Creator<SelectableColor>() {

        @Override
        public SelectableColor createFromParcel(Parcel source) {
            return new SelectableColor(source);
        }

        @Override
        public SelectableColor[] newArray(int size) {
            return new SelectableColor[size];
        }
    };
}
