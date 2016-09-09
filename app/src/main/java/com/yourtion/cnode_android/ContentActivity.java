package com.yourtion.cnode_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.yourtion.cnode_android.Modules.Topic;

/**
 * Created by Yourtion on 9/2/16.
 */
public class ContentActivity extends AppCompatActivity {

    private TextView mTitleTextView;
    private TextView mContentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mTitleTextView = (TextView) findViewById(R.id.text_view_title);
        mContentTextView = (TextView) findViewById(R.id.text_view_content);
        Topic topic = (Topic) getIntent().getSerializableExtra(MainActivity.TOPIC_KEY);
        mTitleTextView.setText(topic.getTitle());
        mContentTextView.setText(Html.fromHtml(topic.getContent()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
