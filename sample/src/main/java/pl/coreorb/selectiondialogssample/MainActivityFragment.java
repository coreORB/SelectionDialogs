package pl.coreorb.selectiondialogssample;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pl.coreorb.selectiondialogs.dialogs.ColorSelectDialog;
import pl.coreorb.selectiondialogs.data.SelectionDialogsColor;
import pl.coreorb.selectiondialogs.data.SelectionDialogsIcon;
import pl.coreorb.selectiondialogs.dialogs.IconSelectDialog;
import pl.coreorb.selectiondialogs.views.SelectedColorView;
import pl.coreorb.selectiondialogs.views.SelectedIconView;

/**
 * Fragment for sample app. This one is more interesting :)
 */
public class MainActivityFragment extends Fragment implements IconSelectDialog.OnIconSelectedListener,
        ColorSelectDialog.OnColorSelectedListener {

    private static final String TAG_SELECT_ICON_DIALOG = "TAG_SELECT_ICON_DIALOG";
    private static final String TAG_SELECT_COLOR_DIALOG = "TAG_SELECT_COLOR_DIALOG";

    private SelectedIconView iconSIV;
    private SelectedColorView colorSIV;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        iconSIV = (SelectedIconView) rootView.findViewById(R.id.icon_siv);
        iconSIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIconSelectDialog();
            }
        });

        colorSIV = (SelectedColorView) rootView.findViewById(R.id.color_siv);
        colorSIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColorSelectDialog();
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        IconSelectDialog iconDialog = (IconSelectDialog) getFragmentManager().findFragmentByTag(TAG_SELECT_ICON_DIALOG);
        if (iconDialog != null) {
            iconDialog.setOnIconSelectedListener(this);
        }
        ColorSelectDialog colorDialog = (ColorSelectDialog) getFragmentManager().findFragmentByTag(TAG_SELECT_COLOR_DIALOG);
        if (colorDialog != null) {
            colorDialog.setOnColorSelectedListener(this);
        }

    }

    /**
     * Displays selected icon in {@link SelectedIconView} view.
     * @param selectedItem selected {@link SelectionDialogsIcon} object containing: id, name and drawable resource id.
     */
    @Override
    public void onIconSelected(SelectionDialogsIcon selectedItem) {
        iconSIV.setItem(selectedItem);
    }

    /**
     * Displays selected color in {@link SelectedColorView} view.
     * @param selectedItem selected {@link SelectionDialogsColor} object containing: id, name and color value.
     */
    @Override
    public void onColorSelected(SelectionDialogsColor selectedItem) {
        colorSIV.setItem(selectedItem);
    }

    /**
     * Shows icon selection dialog with sample icons.
     */
    private void showIconSelectDialog() {
        new IconSelectDialog.Builder(getContext())
                .setIcons(sampleIcons())
                .setTitle(R.string.selectiondialogs_icon_dialog_title)
                .setSortIconsByName(true)
                .setOnIconSelectedListener(this)
                .build().show(getFragmentManager(), TAG_SELECT_ICON_DIALOG);
    }

    /**
     * Shows color selection dialog with default Material Design icons.
     */
    private void showColorSelectDialog() {
        new ColorSelectDialog.Builder(getContext())
                .setColorsMaterialDesign500()
                .setTitle(R.string.selectiondialogs_color_dialog_title)
                .setOnColorSelectedListener(this)
                .build().show(getFragmentManager(), TAG_SELECT_COLOR_DIALOG);
    }

    /**
     * Creates sample ArrayList of icons to display in dialog.
     * @return sample icons
     */
    public static ArrayList<SelectionDialogsIcon> sampleIcons() {
        ArrayList<SelectionDialogsIcon> selectionDialogsColors = new ArrayList<>();
        selectionDialogsColors.add(new SelectionDialogsIcon("puzzle", "Puzzle",  R.drawable.sample_icon_puzzle));
        selectionDialogsColors.add(new SelectionDialogsIcon("android", "Android", R.drawable.sample_icon_android));
        selectionDialogsColors.add(new SelectionDialogsIcon("bookmark", "Bookmark", R.drawable.sample_icon_bookmark));
        return selectionDialogsColors;
    }
}
