package pl.coreorb.selectiondialogs.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class of primitive object used in every dialog in this library.
 */
public abstract class PrimitiveSelectionDialogsItem implements Parcelable {

    protected String id;
    protected String name;

    public PrimitiveSelectionDialogsItem(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    public PrimitiveSelectionDialogsItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns unique identifier of this item.
     * @return unique identifier of this item.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets unique identifier of this item.
     * @param id unique identifierto use for this item
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns name of this item.
     * @return name of item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of item that will be shown to user in dialog and view.
     * @param name name to use form this item
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
    }
}
