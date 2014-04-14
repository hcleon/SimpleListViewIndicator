package com.example.listindicator;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 *
 */
public class SimpleListViewIndicator extends LinearLayout {
    private static final String TAG = SimpleListViewIndicator.class
            .getSimpleName();

    private Context context;
    private ListView listview;
    private LinearLayout itemContainer;
    private List<ImageView> items;

    public SimpleListViewIndicator(Context context) {
        super(context);
        this.context = context;
        setup();
    }

    public SimpleListViewIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setup();
    }

    public SimpleListViewIndicator(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        setup();
    }

    private void setup() {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (inflater != null) {
            inflater.inflate(R.layout.view_listview_indicator, this);

            itemContainer = (LinearLayout) findViewById(R.id.listview_indicator_container);

            items = new ArrayList<ImageView>();
        }
    }

    /**
     * Notifies the listview indicator that the data set has changed. Be sure to
     * notify the listview as well (though you may wish to place that call in
     * here yourself).
     */
    public void notifyDataSetChanged() {
        if (listview != null && listview.getAdapter() != null) {

            // remove the old items (if any exist)
            itemContainer.removeAllViews();

            // I'm sure this could be optimised a lot more, eg,
            // by reusing existing ImageViews, but it
            // does the job well enough for now.
            items.removeAll(items);

            // now create the new items.
            for (int i = 0; i < listview.getAdapter().getCount(); i++) {

                ImageView item = new ImageView(context);

                if (i == listview.getSelectedItemPosition()) {
                    item.setImageResource(R.drawable.bulb_lit);
                } else {
                    item.setImageResource(R.drawable.bulb_unlit);
                }

                item.setTag(i);
                // item.setOnClickListener(itemClickListener);
                items.add(item);

                itemContainer.addView(item);
            }
        }
    }

    public ListView getListView() {
        return listview;
    }

    public void setListView(ListView listview) {
        this.listview = listview;
    }

    public void setCurrentItem(int position) {
        if (listview != null && listview.getAdapter() != null) {
            int numberOfItems = listview.getAdapter().getCount();

            for (int i = 0; i < numberOfItems; i++) {
                ImageView item = items.get(i);
                if (item != null) {
                    if (i == position) {
                        item.setImageResource(R.drawable.bulb_lit);
                    } else {
                        item.setImageResource(R.drawable.bulb_unlit);
                    }
                }
            }
        }
    }
}
