package pl.coreorb.selectiondialogs.data;

import android.os.Parcel;

/**
 * Object defining selectable color in this library.
 */
public class SelectableColor extends PrimitiveSelectableItem {

    private int color;

    public SelectableColor() {
        super();
        color = 0;
    }

    public SelectableColor(Parcel in) {
        super(in);
        color = in.readInt();
    }

    public SelectableColor(String id, String name, int color) {
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
        return "SelectableColor{" +
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
