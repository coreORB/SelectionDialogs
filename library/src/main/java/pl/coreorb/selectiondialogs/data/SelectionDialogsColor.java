package pl.coreorb.selectiondialogs.data;

import android.os.Parcel;

/**
 * Object defining selectable color in this library.
 */
public class SelectionDialogsColor extends PrimitiveSelectionDialogsItem {

    private int color;

    public SelectionDialogsColor(Parcel in) {
        super(in);
        color = in.readInt();
    }

    public SelectionDialogsColor(String id, String name, int color) {
        super(id, name);
        this.color = color;
    }

    /**
     * Returns color integer value of this color.
     * @return color integer value of this color
     */
    public int getColor() {
        return color;
    }

    /**
     * Sets color integer value of this color.
     * @param color color integer value to use as this color value
     */
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "SelectionDialogsColor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", color=" + color +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(color);
    }

    public static final Creator<SelectionDialogsColor> CREATOR = new Creator<SelectionDialogsColor>() {

        @Override
        public SelectionDialogsColor createFromParcel(Parcel source) {
            return new SelectionDialogsColor(source);
        }

        @Override
        public SelectionDialogsColor[] newArray(int size) {
            return new SelectionDialogsColor[size];
        }
    };
}
