package rejasupotaro.sample.controllers;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rejasupotaro.sample.R;
import rejasupotaro.sample.data.model.Article;

public class ArticleDetailActivity extends Activity {

    private static final String EXTRA_RECIPE = "detail:_recipe";

    public static final String VIEW_NAME_PHOTO = "detail:photo";

    public static final String VIEW_NAME_TITLE = "detail:title";

    public static Intent createIntent(Context context, Article article) {
        Intent intent = new Intent(context, ArticleDetailActivity.class);
        intent.putExtra(EXTRA_RECIPE, article);
        return intent;
    }

    public static Bundle createOption(Activity activity, View view) {
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(
                activity,
                new Pair<View, String>(view.findViewById(R.id.list_item_photo),
                        ArticleDetailActivity.VIEW_NAME_PHOTO),
                new Pair<View, String>(view.findViewById(R.id.list_item_title),
                        ArticleDetailActivity.VIEW_NAME_TITLE)
        );
        return activityOptions.toBundle();
    }

    private Article article;

    @InjectView(R.id.detail_photo)
    ImageView photoImageView;

    @InjectView(R.id.detail_title)
    TextView titleTextView;

    @InjectView(R.id.detail_body)
    TextView bodyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);
        getArgs();
        setupActionBar();
        setupViews();
    }

    private void getArgs() {
        article = getIntent().getParcelableExtra(EXTRA_RECIPE);
    }

    private void setupActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setupViews() {
        photoImageView.setViewName(VIEW_NAME_PHOTO);
        titleTextView.setViewName(VIEW_NAME_TITLE);

        Picasso.with(this)
                .load(Uri.parse(article.getPhotoUrl()))
                .into(photoImageView);

        titleTextView.setText(article.getTitle());

        bodyTextView.setText(article.getBody());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
