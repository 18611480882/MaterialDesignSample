package rejasupotaro.sample.listeners;

import android.view.View;

public interface OnRecyclerViewItemClickListener<T> {
    public void onItemClick(View view, T item);
}
