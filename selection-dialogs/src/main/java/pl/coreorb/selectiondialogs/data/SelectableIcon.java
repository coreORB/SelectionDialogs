package pl.coreorb.selectiondialogs.data;

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import pl.coreorb.selectiondialogs.R;

/**
 * Object defining selectable icon in this library.
 */
public class SelectableIcon extends PrimitiveSelectableItem {

    private @DrawableRes int drawableResId;

    public SelectableIcon() {
        super();
        drawableResId = R.drawable.selectiondialogs_default_item_icon;
    }

    public SelectableIcon(Parcel in) {
        super(in);
        drawableResId = in.readInt();
    }

    public SelectableIcon(String id, String name, @DrawableRes int drawableResId) {
        super(id, name);
        this.drawableResId = drawableResId;
    }

    public SelectableIcon(Context context, @StringRes int idResId, @StringRes int nameResId, @DrawableRes int drawableResId) {
        super(context, idResId, nameResId);
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
        return "SelectableIcon{" +
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

    public static final Creator<SelectableIcon> CREATOR = new Creator<SelectableIcon>() {

        @Override
        public SelectableIcon createFromParcel(Parcel source) {
            return new SelectableIcon(source);
        }

        @Override
        public SelectableIcon[] newArray(int size) {
            return new SelectableIcon[size];
        }
    };

}
