# Selection Dialogs
Selection Dialogs is Android library allowing quickly create colors and icons selection dialogs, and providing simple views to display selected items.

Current version is 0.9.1b

![Screenshot_Fragment](https://github.com/ZaYeR-PL/SelectionDialogs/blob/master/screens/Screenshot_Fragment.png?raw=true)
![Screenshot_ColorSelectionDialog](https://github.com/ZaYeR-PL/SelectionDialogs/blob/master/screens/Screenshot_ColorSelectionDialog.png?raw=true)
![Screenshot_IconSelectionDialog](https://github.com/ZaYeR-PL/SelectionDialogs/blob/master/screens/Screenshot_IconSelectionDialog.png?raw=true)

## Download
Library is available as jcenter. Just make sure that grandle uses this repository:

```java
    repositories {
        jcenter()
    }
```

And add dependency to module.

```java
    dependencies {
        compile 'pl.coreorb:selection-dialogs:0.9.1'
    }
```

## Usage
All code presented here shows all methods and properties available, so just remove parts you don't need.

### Icon selection dialog
Include below method (or something similar), which builds and shows dialog, in your `Fragment` or `Activity`. Then, call it from onClick of some view.

```java
    private void showIconSelectDialog() {
        new IconSelectDialog.Builder(getContext())
                .setIcons(sampleIcons())
                .setIcons(R.array.icons_ids,
                        R.array.icons_names,
                        R.array.icons_colors)
                .setTitle(R.string.title)
                .setTitle("Title")
                .setSortIconsByName(true)
                .setOnIconSelectedListener(this)
                .build().show(getFragmentManager(), TAG_SELECT_ICON_DIALOG);
    }
```

### Color selection dialog
Include below method (or something similar), which builds and shows dialog, in your `Fragment` or `Activity`. Then, call it from onClick of some view.

```java
    private void showColorSelectDialog() {
        new ColorSelectDialog.Builder(getContext())
                .setColors(ColorPalettes.loadMaterialDesignColors500(getContext(), false))
                .setColors(R.array.colors_ids,
                        R.array.colors_names,
                        R.array.colors_colors)
                .setTitle(R.string.title)
                .setTitle("Title")
                .setSortColorsByName(true)
                .setOnColorSelectedListener(this)
                .build().show(getFragmentManager(), TAG_SELECT_COLOR_DIALOG);
    }
```

### Selected item view
View can be used to display selected item from dialogs (color, icon or text).

Include view in your `Fragment` or `Activity` layout.

```xml
    <pl.coreorb.selectiondialogs.views.SelectedItemView
            android:id="@+id/icon_siv"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:hint="@string/icon_hint"
            app:itemId="green"
            app:itemName="Green"
            app:itemIcon="@drawable/selectiondialogs_dialog_default_icon"
            app:itemColor="@color/selectiondialogs_materialdesign500_green"/>
```

Then use `findViewById()` and add `onClick()` in `onCreate()` or `onCreateView()`.

```java
    colorSIV = (SelectedItemView) rootView.findViewById(R.id.color_siv);
    colorSIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColorSelectDialog();
            }
        });
```

Views has standard setters and getters.

```java
    SelectedItemView selectedItemView = new SelectedItemView(getContext());
    
    selectedItemView.setHint("hint");
    selectedItemView.setHint(R.string.icon_hint);
    selectedItemView.setSelectedName("Android");
    selectedItemView.setSelectedName(R.string.icon_hint);
    selectedItemView.setSelectedIcon(new SelectableIcon());
    selectedItemView.setSelectedColor(new SelectableColor());
    
    String hint = selectedItemView.getHint();
    String name = selectedItemView.getSelectedName();
    SelectableIcon icon = selectedItemView.getSelectedIcon();
    SelectableColor color = selectedItemView.getSelectedColor();
```

### Color palettes
Library has build in color palettes. Currently only Material Design primary colors (500).
Contact me if You want more palettes added.

To obtain palette as ArrayList<SelectableColor> just call one of methods in ColorPalettes class.
Note that palette can be sorted by name via second argument, e.g.:

```java
    ColorPalettes.loadMaterialDesignColors500(getContext(), false)
```

### Utils
Library delivers Utils class with convert methods:

```java
    public static ArrayList<SelectableColor> convertResourceArraysToColorsArrayList(Context context, boolean sortByName, @ArrayRes int idsArray, @ArrayRes int namesArray, @ArrayRes int colorsArray)
    
    public static ArrayList<SelectableIcon> convertResourceArraysToIconsArrayList(Context context, boolean sortByName, @ArrayRes int idsArray, @ArrayRes int namesArray, @ArrayRes int drawablesArray)
```


##Handling device configuration changes
If device is rotated with dialog opened, dialog will stay opened, but parent Activity/Fragment will no longer receive callback with selected item.

One way to handle this is to set listener to `this`(Activity/Fragment) and re-set listener in `onResume()` like this:

```java
    public class MyFragment extends Fragment implements IconSelectDialog.OnIconSelectedListener {
        private static final String TAG_SELECT_ICON_DIALOG = "TAG_SELECT_ICON_DIALOG";
        
        //fragment code
        
        private void showIconSelectDialog() {
            //set listener to this
            new IconSelectDialog.Builder(getContext())
                    .setIcons(sampleIcons())
                    .setOnIconSelectedListener(this)
                    .build().show(getFragmentManager(), TAG_SELECT_ICON_DIALOG);
        }
        
        @Override
        public void onResume() {
            super.onResume();
            //find dialog and set listener
            IconSelectDialog iconDialog = (IconSelectDialog) getFragmentManager().findFragmentByTag(TAG_SELECT_ICON_DIALOG);
            if (iconDialog != null) {
                iconDialog.setOnIconSelectedListener(this);
            }
        }
    }
```

For complete implementation take a look at [MainActivityFragment.java](sample/src/main/java/pl/coreorb/selectiondialogssample/MainActivityFragment.java).

## JavaDoc
Coming soon...

## Licence

[Apache License, Version 2.0](LICENCE).

## Change log

### 0.9.1b
- "Handling device configuration changes" chapter filled
- added new constructors to SelectableColor and SelectableIcon

### 0.9.1
- SelectedIconView and SelectedColorView simplified into one view: SelectedItemView
- ColorPalettes class added
- Utils class added
- updated sample with sane defaults

### 0.9.0
Initial release
