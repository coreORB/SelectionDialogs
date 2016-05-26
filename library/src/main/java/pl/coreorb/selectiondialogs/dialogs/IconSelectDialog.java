package pl.coreorb.selectiondialogs.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import pl.coreorb.selectiondialogs.R;
import pl.coreorb.selectiondialogs.data.SelectionDialogsColor;
import pl.coreorb.selectiondialogs.data.SelectionDialogsIcon;
import pl.coreorb.selectiondialogs.data.SelectionDialogsItemNameComparator;
import pl.coreorb.selectiondialogs.dialogs.adapters.IconListAdapter;

/**
 * Class defining Dialog for selecting icons.
 */
public class IconSelectDialog extends DialogFragment {

    private static final String LOG_TAG = IconSelectDialog.class.getSimpleName();

    private static final String ARG_TITLE = "arg_title";
    private static final String ARG_ITEMS = "arg_items";
    private static final String ARG_SORT_BY_NAME = "arg_sort_by_name";

    private CharSequence mTitle;
    private ArrayList<SelectionDialogsIcon> mSelectionDialogsItems;
    private boolean mSortByName;
    private OnIconSelectedListener mListener;

    public static class Builder {
        protected Context context;
        protected Bundle bundle;
        private OnIconSelectedListener listener;

        /**
         * Class that will help you build colors selection dialog.
         */
        public Builder(Context context) {
            this.context = context;
            bundle = new Bundle();
        }

        /**
         * Sets dialog's title. If no setTitle() method is used, default
         * {@link R.string#selectiondialogs_icon_dialog_title} will be used
         * ("Select icon..." translated to supported languages).
         * @param title text to set as dialog's title
         * @return this {@link Builder} to let methods chaining
         */
        public Builder setTitle(CharSequence title) {
            bundle.putCharSequence(ARG_TITLE, title);
            return this;
        }

        /**
         * Sets dialog's title. If no setTitle() method is used, default
         * {@link R.string#selectiondialogs_icon_dialog_title} will be used
         * ("Select icon..." translated to supported languages).
         * @param titleResId string resource ID to set as dialog's title
         * @return this {@link Builder} to let methods chaining
         */
        public Builder setTitle(@StringRes int titleResId) {
            bundle.putCharSequence(ARG_TITLE, context.getString(titleResId));
            return this;
        }

        /**
         * Changes sorting of icons in dialog.
         * @param sort if true icons provided to dialog will be sorted by name,
         * otherwise list will be left as it is
         * @return this {@link Builder} to let methods chaining
         */
        public Builder setSortIconsByName(boolean sort) {
            bundle.putBoolean(ARG_SORT_BY_NAME, sort);
            return this;
        }

        /**
         * Sets listener to receive callbacks when icon is selected.
         * @param listener listener which will receive callbacks
         * @return this {@link Builder} to let methods chaining
         */
        public Builder setOnIconSelectedListener(OnIconSelectedListener listener) {
            this.listener = listener;
            return this;
        }

        /**
         * Sets icons to display in dialog as available choices.
         * @param items ArrayList of icons to use
         * @return this {@link Builder} to let methods chaining
         */
        public Builder setIcons(ArrayList<SelectionDialogsIcon> items) {
            bundle.putParcelableArrayList(ARG_ITEMS, items);
            return this;
        }

        /**
         * Sets icons to display in dialog as available choices.
         * Note: all arrays must have equal lengths.
         * @param idsArray arrray resource id to use as icons ids
         * @param namesArray arrray resource id to use as icons names
         * @param drawablesArray arrray resource id to use as icons drawables
         * @return this {@link Builder} to let methods chaining
         */
        public Builder setIcons(@ArrayRes int idsArray, @ArrayRes int namesArray, @ArrayRes int drawablesArray) {
            //get and check arrays
            String[] ids = context.getResources().getStringArray(idsArray);
            int[] drawables = context.getResources().getIntArray(drawablesArray);
            String[] names = context.getResources().getStringArray(namesArray);

            if (ids.length != drawables.length && ids.length != names.length) {
                Log.e(LOG_TAG, "setItems(): Arrays must have equals lengths!");
                return this;
            }

            //create ArrayList
            ArrayList<SelectionDialogsIcon> icons = new ArrayList<>();
            for (int i=0; i<ids.length; i++) {
                icons.add(new SelectionDialogsIcon(ids[i], names[i], drawables[i]));
            }

            return setIcons(icons);
        }

        public IconSelectDialog build() {
            IconSelectDialog dialog = new IconSelectDialog();
            dialog.setArguments(bundle);
            dialog.setOnIconSelectedListener(listener);
            return dialog;
        }

    }

    public void setOnIconSelectedListener(OnIconSelectedListener listener) {
        mListener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null && args.containsKey(ARG_TITLE)) {
            mTitle = args.getCharSequence(ARG_TITLE);
        } else {
            mTitle = getString(R.string.selectiondialogs_color_dialog_title);
        }

        if (args != null && args.containsKey(ARG_ITEMS)) {
            mSelectionDialogsItems = args.getParcelableArrayList(ARG_ITEMS);
        } else {
            Log.w(LOG_TAG, "No items provided! Creating default");
            mSelectionDialogsItems = new ArrayList<>();
            mSelectionDialogsItems.add(createDefaultItem());
        }

        if (args != null && args.containsKey(ARG_SORT_BY_NAME)) {
            mSortByName = args.getBoolean(ARG_SORT_BY_NAME);
            if (mSortByName) {
                Collections.sort(mSelectionDialogsItems, new SelectionDialogsItemNameComparator());
            }
        } else {
            mSortByName = false;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle(mTitle);
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        setUpListAdapter(builder);

        return builder.create();
    }

    /**
     * Creates default icon in dialog when no icons are provided.
     * @return default icon
     */
    private SelectionDialogsIcon createDefaultItem() {
        return new SelectionDialogsIcon("default",
                getText(R.string.selectiondialogs_undefined).toString(),
                R.drawable.selectiondialogs_dialog_default_icon);
    }

    /**
     * Sets up list adapter for icon select dialog.
     * @param builder builder used for creating dialog
     */
    private void setUpListAdapter(AlertDialog.Builder builder) {
        IconListAdapter adapter = new IconListAdapter(getContext(), mSelectionDialogsItems);
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mListener != null) {
                    mListener.onIconSelected(mSelectionDialogsItems.get(which));
                }
            }
        });
    }

    /**
     * Listener to receive callbacks when icon in dialog is selected.
     */
    public interface OnIconSelectedListener {
        /**
         * Called when icon in dialog is being selected.
         * @param selectedItem selected {@link SelectionDialogsIcon} object containing: id, name and drawable resource id.
         */
        void onIconSelected(SelectionDialogsIcon selectedItem);
    }


}
