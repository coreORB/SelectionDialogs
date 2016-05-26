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
import pl.coreorb.selectiondialogs.data.SelectionDialogsItemNameComparator;
import pl.coreorb.selectiondialogs.dialogs.adapters.ColorListAdapter;

/**
 * Class defining Dialog for selecting colors.
 */
public class ColorSelectDialog extends DialogFragment {

    private static final String LOG_TAG = ColorSelectDialog.class.getSimpleName();

    private static final String ARG_TITLE = "arg_title";
    private static final String ARG_ITEMS = "arg_items";
    private static final String ARG_SORT_BY_NAME = "arg_sort_by_name";

    private CharSequence mTitle;
    private ArrayList<SelectionDialogsColor> mSelectionDialogsItems;
    private boolean mSortByName;
    private OnColorSelectedListener mListener;

    /**
     * Class that will help you build colors selection dialog.
     */
    public static class Builder {
        protected Context context;
        protected Bundle bundle;
        private OnColorSelectedListener listener;

        public Builder(Context context) {
            this.context = context;
            bundle = new Bundle();
        }

        /**
         * Sets dialog's title. If no setTitle() method is used, default
         * {@link R.string#selectiondialogs_color_dialog_title} will be used
         * ("Select color..." translated to supported languages).
         * @param title text to set as dialog's title
         * @return this {@link Builder} to let methods chaining
         */
        public Builder setTitle(CharSequence title) {
            bundle.putCharSequence(ARG_TITLE, title);
            return this;
        }

        /**
         * Sets dialog's title. If no setTitle() method is used, default
         * {@link R.string#selectiondialogs_color_dialog_title} will be used
         * ("Select color..." translated to supported languages).
         * @param titleResId string resource ID to set as dialog's title
         * @return this {@link Builder} to let methods chaining
         */
        public Builder setTitle(@StringRes int titleResId) {
            bundle.putCharSequence(ARG_TITLE, context.getString(titleResId));
            return this;
        }

        /**
         * Changes sorting of colors in dialog.
         * @param sort if true colors provided to dialog will be sorted by name,
         * otherwise list will be left as it is
         * @return this {@link Builder} to let methods chaining
         */
        public Builder setSortColorsByName(boolean sort) {
            bundle.putBoolean(ARG_SORT_BY_NAME, sort);
            return this;
        }

        /**
         * Sets listener to receive callbacks when color is selected.
         * @param listener listener which will receive callbacks
         * @return this {@link Builder} to let methods chaining
         */
        public Builder setOnColorSelectedListener(OnColorSelectedListener listener) {
            this.listener = listener;
            return this;
        }

        /**
         * Sets colors to display in dialog as available choices.
         * @param items ArrayList of colors to use
         * @return this {@link Builder} to let methods chaining
         */
        public Builder setColors(ArrayList<SelectionDialogsColor> items) {
            bundle.putParcelableArrayList(ARG_ITEMS, items);
            return this;
        }

        /**
         * Sets colors to display in dialog as available choices.
         * Note: all arrays must have equal lengths.
         * @param idsArray arrray resource id to use as colors ids
         * @param namesArray arrray resource id to use as colors names
         * @param colorsArray arrray resource id to use as colors, well color values
         * @return this {@link Builder} to let methods chaining
         */
        public Builder setColors(@ArrayRes int idsArray, @ArrayRes int namesArray, @ArrayRes int colorsArray) {
            //get and check arrays
            String[] ids = context.getResources().getStringArray(idsArray);
            int[] colors = context.getResources().getIntArray(colorsArray);
            String[] names = context.getResources().getStringArray(namesArray);

            if (ids.length != colors.length && ids.length != names.length) {
                Log.e(LOG_TAG, "setItems(): Arrays must have equals lengths!");
                return this;
            }

            //create ArrayList
            ArrayList<SelectionDialogsColor> icons = new ArrayList<>();
            for (int i=0; i<ids.length; i++) {
                icons.add(new SelectionDialogsColor(ids[i], names[i], colors[i]));
            }

            return setColors(icons);
        }

        /**
         * Sets default Material Design colors (500) to display in dialog as available choices.
         * @return this {@link Builder} to let methods chaining
         */
        public Builder setColorsMaterialDesign500() {
            return setColors(R.array.selectiondialogs_materialdesign500_ids,
                    R.array.selectiondialogs_materialdesign500_names,
                    R.array.selectiondialogs_materialdesign500_colors);
        }

        public ColorSelectDialog build() {
            ColorSelectDialog dialog = new ColorSelectDialog();
            dialog.setArguments(bundle);
            dialog.setOnColorSelectedListener(listener);
            return dialog;
        }
    }

    public void setOnColorSelectedListener(OnColorSelectedListener listener) {
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
     * Creates default color in dialog when no colors are provided.
     * @return default color
     */
    private SelectionDialogsColor createDefaultItem() {
        return new SelectionDialogsColor(
                getContext().getString(R.string.selectiondialogs_undefined),
                getContext().getString(R.string.selectiondialogs_undefined),
                getContext().getResources().getColor(R.color.selectiondialogs_textSecondary));
    }

    /**
     * Sets up list adapter for color select dialog.
     * @param builder builder used for creating dialog
     */
    private void setUpListAdapter(AlertDialog.Builder builder) {
        ColorListAdapter adapter = new ColorListAdapter(getContext(), mSelectionDialogsItems);
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mListener != null) {
                    mListener.onColorSelected(mSelectionDialogsItems.get(which));
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mListener = null;
    }

    /**
     * Listener to receive callbacks when color in dialog is selected.
     */
    public interface OnColorSelectedListener {
        /**
         * Called when color in dialog is being selected.
         * @param selectedItem selected {@link SelectionDialogsColor} object containing: id, name and color value.
         */
        void onColorSelected(SelectionDialogsColor selectedItem);
    }
}
