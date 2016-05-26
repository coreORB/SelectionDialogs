# Selection Dialogs
Selection Dialogs is Android library allowing quickly create colors and icons selection dialogs, and providing simple views to display selected items.

## Download
Comming soon...

## Usage
All code presented here is from sample app, so sources can be checked for more details.

### Icon selection dialog
Include below method (or something similar), which builds and shows dialog, in your `Fragment` or `Activity`. Then, call it from onClick of some view.

```java
    private void showIconSelectDialog() {
        new IconSelectDialog.Builder(getContext())
                .setIcons(sampleIcons())
                .setTitle(R.string.selectiondialogs_icon_dialog_title)
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
                .setColorsMaterialDesign500()
                .setTitle(R.string.selectiondialogs_color_dialog_title)
                .setOnColorSelectedListener(this)
                .build().show(getFragmentManager(), TAG_SELECT_COLOR_DIALOG);
    }
```

### Selected color view
Include view in your `Fragment` or `Activity` layout.

```xml
<pl.coreorb.selectiondialogs.views.SelectedIconView
        android:id="@+id/icon_siv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:hint="@string/icon_hint"
        app:buttonIcon="@drawable/selectiondialogs_dialog_default_icon"
        app:buttonText="Black" />
```

Then use `findViewById()` and add `onClick()` in `onCreate()` or `onCreateView()`.

```java
colorSIV = (SelectedColorView) rootView.findViewById(R.id.color_siv);
colorSIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColorSelectDialog();
            }
        });
```

### Selected icon view
Include view in your `Fragment` or `Activity` layout.

```xml
<pl.coreorb.selectiondialogs.views.SelectedColorView
        android:id="@+id/color_siv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:hint="@string/color_hint"
        app:buttonText="Black"
        app:buttonIconTint="@color/colorPrimary" />
```

Then use `findViewById()` and add `onClick()` in `onCreate()` or `onCreateView()`.

```java
iconSIV = (SelectedIconView) rootView.findViewById(R.id.icon_siv);
iconSIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIconSelectDialog();
            }
        });
```

##Handling device configuration changes
Comming soon...

## JavaDoc
Comming soon...

## Licence
Comming soon...