package com.yourtion.cnode_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.yourtion.cnode_android.Modules.Topic;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public final static String TOPIC_KEY = "com.yourtion.cnode_android.topic";

    private ListView mListView;
    private ArrayList<Topic> mTopics;
    private CNodeListAdapter mListAdapter;
    private CNodeClient client = new CNodeClient();
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mListView = (ListView) findViewById(R.id.TopicList);
        mListAdapter = new CNodeListAdapter(this, mTopics);
        mListView.setAdapter(mListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Topic topic = mListAdapter.getItem(position);
                Intent i = new Intent(getApplicationContext(), ContentActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(TOPIC_KEY, topic);
                i.putExtras(mBundle);
                startActivity(i);
            }
        });

        loadData("");
    }

    public void loadData(String channal) {
        if (channal == null) {
            channal = "";
        }
        client.getTopics(channal, 0, 0, new CNodeClient.Callback() {
            @Override
            public void success(Object res) {
                mTopics = (ArrayList<Topic>) res;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mListAdapter.setTopics(mTopics);
                        mListAdapter.notifyDataSetChanged();
                    }
                });

            }

            @Override
            public void fail() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "网络请求失败", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return false;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        mListAdapter.setTagVisible(false);

        switch (id) {
            case R.id.nav_good: {
                loadData("good");
                mToolbar.setTitle("CNode社区 - 精华");
                break;
            }
            case R.id.nav_ask: {
                loadData("ask");
                mToolbar.setTitle("CNode社区 - 问答");
                break;
            }
            case R.id.nav_job: {
                loadData("job");
                mToolbar.setTitle("CNode社区 - 招聘");
                break;
            }
            case R.id.nav_share: {
                loadData("share");
                mToolbar.setTitle("CNode社区 - 分享");
                break;
            }
            default: {
                loadData("");
                mListAdapter.setTagVisible(true);
                mToolbar.setTitle("CNode社区");
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
