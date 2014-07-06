package rejasupotaro.sample.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.IconTextView;

import rejasupotaro.sample.R;

public class DrawerListAdapter extends ArrayAdapter<String> {

    public static class ViewHolder {

        public final View root;

        public final IconTextView textView;

        public ViewHolder(View view) {
            root = view;
            textView = (IconTextView) view.findViewById(R.id.text);
        }
    }

    public DrawerListAdapter(Context context, String[] items) {
        super(context, -1, items);
    }

    @Override
    public final View getView(int i, View view, ViewGroup parent) {
        if (i == 0) {
            return onCreateHeaderView();
        }

        ViewHolder holder;
        if (view == null) {
            view = onCreateView(parent, i);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        onBindView(holder, i);
        return view;
    }

    private View onCreateHeaderView() {
        return View.inflate(getContext(), R.layout.header_drawer_list, null);
    }

    public View onCreateView(ViewGroup parent, int i) {
        return View.inflate(parent.getContext(), R.layout.list_item_drawer, null);
    }

    public void onBindView(ViewHolder holder, final int i) {
        final String item = getItem(i);
        holder.textView.setText(item);
    }
}

