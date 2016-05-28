package pl.coreorb.selectiondialogs.dialogs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pl.coreorb.selectiondialogs.R;
import pl.coreorb.selectiondialogs.data.SelectableIcon;

/**
 * Standard ArrayAdapter to use with ListViews for SelectableIcon objects.
 */
public class IconListAdapter extends ArrayAdapter<SelectableIcon> {

    public IconListAdapter(Context context, ArrayList<SelectableIcon> selectionDialogsColors) {
        super(context, R.layout.selectiondialogs_dialog_item, selectionDialogsColors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = convertView;

        if (rootView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootView = inflater.inflate(R.layout.selectiondialogs_dialog_item, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.iconIV = (ImageView) rootView.findViewById(R.id.selectiondialogs_icon_iv);
            viewHolder.nameTV = (TextView) rootView.findViewById(R.id.selectiondialogs_name_tv);
            rootView.setTag(viewHolder);
        }

        SelectableIcon item = getItem(position);
        ViewHolder holder = (ViewHolder) rootView.getTag();
        holder.iconIV.setImageResource(item.getDrawableResId());
        holder.nameTV.setText(item.getName());

        return rootView;
    }

    static class ViewHolder {
        public ImageView iconIV;
        public TextView nameTV;
    }
}
