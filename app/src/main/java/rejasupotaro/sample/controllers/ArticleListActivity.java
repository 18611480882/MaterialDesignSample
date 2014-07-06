package rejasupotaro.sample.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rejasupotaro.sample.R;
import rejasupotaro.sample.adapters.ArticleListAdapter;
import rejasupotaro.sample.data.model.Article;
import rejasupotaro.sample.listeners.OnRecyclerViewItemClickListener;

public class ArticleListActivity extends Activity {

    private ArticleListAdapter adapter;

    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @InjectView(R.id.left_drawer)
    ListView drawerListView;

    @InjectView(R.id.list)
    RecyclerView articleListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.inject(this);
        setupDrawer();
        setupViews();
    }

    private void setupDrawer() {
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        String[] drawerItems = getResources().getStringArray(R.array.drawer_items);
        drawerListView.setAdapter(new ArrayAdapter<String>(
                this,
                R.layout.list_item_drawer,
                drawerItems
        ));

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close);
        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void setupViews() {
        List<Article> articleList = Article.createDummyData();
        adapter = new ArticleListAdapter(articleList);

        articleListView.setLayoutManager(new LinearLayoutManager(this));
        articleListView.setHasFixedSize(true);
        articleListView.setItemAnimator(new DefaultItemAnimator());
        articleListView.setAdapter(adapter);

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

