package com.yourtion.cnode_android;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.yourtion.cnode_android.Modules.Topic;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView mListView;
    private ArrayList<Topic> mTopics;
    private ArrayList<HashMap<String, String>> mListData;
    private SimpleAdapter mSimpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mListView = (ListView) findViewById(R.id.TopicList);

        mListData = new ArrayList<HashMap<String, String>>();

        //生成适配器，数组===》ListItem
        mSimpleAdapter = new SimpleAdapter(this, //没什么解释
                mListData,//数据来源
                R.layout.topic_list_item,//ListItem的XML实现
                new String[]{"ItemTitle", "ItemText"},
                //ListItem的XML文件里面的两个TextView ID
                new int[]{R.id.ItemTitle, R.id.ItemText});
        //添加并且显示
        mListView.setAdapter(mSimpleAdapter);

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        CNodeClient client = new CNodeClient();

        if (id == R.id.nav_all) {
            // Handle the camera action
            client.getTopics("", 0, 0, new CNodeClient.Callback() {
                @Override
                public void success(Object res) {
                    mTopics = (ArrayList<Topic>) res;
                    for (int i = 0; i < mTopics.size(); i++) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        Topic topic = mTopics.get(i);
                        map.put("ItemTitle", topic.getTitle());
                        map.put("ItemText", topic.getAuthorId());
                        mListData.add(map);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mSimpleAdapter.notifyDataSetChanged();
                        }
                    });

                }

                @Override
                public void fail() {

                }
            });
        } else if (id == R.id.nav_essence) {
        } else if (id == R.id.nav_faq) {

        } else if (id == R.id.nav_jobs) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
