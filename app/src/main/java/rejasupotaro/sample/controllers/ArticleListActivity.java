package rejasupotaro.sample.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rejasupotaro.sample.R;
import rejasupotaro.sample.adapters.ArticleListAdapter;
import rejasupotaro.sample.data.model.Article;
import rejasupotaro.sample.listeners.OnRecyclerViewItemClickListener;

public class ArticleListActivity extends Activity {

    private ArticleListAdapter adapter;

    @InjectView(R.id.list)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.inject(this);
        setupViews();
    }

    private void setupViews() {
        List<Article> articleList = Article.createDummyData();
        adapter = new ArticleListAdapter(articleList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnRecyclerViewItemClickListener<Article>() {
            @Override
            public void onItemClick(View view, Article article) {
                openDetailActivity(article, view);
            }
        });
    }

    private void openDetailActivity(Article article, View view) {
        startActivity(ArticleDetailActivity.createIntent(this, article),
                ArticleDetailActivity.createOption(this, view));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

