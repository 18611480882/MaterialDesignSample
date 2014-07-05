package rejasupotaro.sample.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rejasupotaro.sample.R;
import rejasupotaro.sample.data.model.Article;
import rejasupotaro.sample.listeners.OnRecyclerViewItemClickListener;
import rejasupotaro.sample.tools.PaletteManager;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {

    private List<Article> articleList;

    private OnRecyclerViewItemClickListener<Article> onItemClickListener;

    private static final PaletteManager paletteManager = new PaletteManager();

    public ArticleListAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View root;

        @InjectView(R.id.list_item_title)
        TextView titleTextView;

        @InjectView(R.id.list_item_photo)
        ImageView photoImageView;

        public ViewHolder(View view) {
            super(view);
            root = view;
            ButterKnife.inject(this, view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = View.inflate(parent.getContext(), R.layout.list_item_recipe, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(final ViewHolder holder, final int i) {
        Article article = articleList.get(i);

        holder.photoImageView.setViewName("list_item:photo:" + article.getId());
        Picasso.with(holder.root.getContext())
                .load(Uri.parse(article.getPhotoUrl()))
                .into(holder.photoImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        paletteManager.updatePalette(paletteManager, holder.photoImageView, holder.titleTextView);
                    }

                    @Override
                    public void onError() {

                    }
                });


        holder.titleTextView.setViewName("list_item:title:" + article.getId());
        holder.titleTextView.setText(article.getTitle());

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    Article article = articleList.get(i);
                    onItemClickListener.onItemClick(view, article);
                }
            }
        });
    }

    public void add(Article article, int position) {
        articleList.add(position, article);
        notifyItemInserted(position);
    }

    public void remove(Article item) {
        int i = articleList.indexOf(item);
        articleList.remove(i);
        notifyItemRemoved(i);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
