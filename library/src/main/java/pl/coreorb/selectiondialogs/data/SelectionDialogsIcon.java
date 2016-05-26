package pl.coreorb.selectiondialogs.data;

import android.os.Parcel;
import android.support.annotation.DrawableRes;

/**
 * Object defining selectable icon in this library.
 */
public class SelectionDialogsIcon extends PrimitiveSelectionDialogsItem {

    private @DrawableRes int drawableResId;

    public SelectionDialogsIcon(Parcel in) {
        super(in);
        drawableResId = in.readInt();
    }

    public SelectionDialogsIcon(String id, String name, @DrawableRes int drawableResId) {
        super(id, name);
        this.drawableResId = drawableResId;
    }

    /**
     * Returns resource ID of this icon drawable.
     * @return resource ID of this icon drawable
     */
    public int getDrawableResId() {
        return drawableResId;
    }

    /**
     * Sets resource ID of this icon drawable.
     * @param drawableResId drawable resource ID to use as this icon drawable
     */
    public void setDrawableResId(int drawableResId) {
        this.drawableResId = drawableResId;
    }

    @Override
    public String toString() {
        return "SelectionDialogsIcon{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", drawableResId=" + drawableResId +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(drawableResId);
    }

    public static final Creator<SelectionDialogsIcon> CREATOR = new Creator<SelectionDialogsIcon>() {

        @Override
        public SelectionDialogsIcon createFromParcel(Parcel source) {
            return new SelectionDialogsIcon(source);
        }

        @Override
        public SelectionDialogsIcon[] newArray(int size) {
            return new SelectionDialogsIcon[size];
        }
    };

}
