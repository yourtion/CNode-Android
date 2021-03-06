package com.yourtion.cnode_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yourtion.cnode_android.Modules.Topic;

import java.util.ArrayList;

/**
 * Created by Yourtion on 9/2/16.
 */
public class CNodeListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Topic> mTopics;
    private Boolean mTagVisible = true;

    public CNodeListAdapter(Context context, ArrayList<Topic> topics) {
        this.mContext = context;
        this.mTopics = topics;
    }

    public void setTopics(ArrayList<Topic> topics) {
        mTopics = topics;
    }

    public void setTagVisible(Boolean tagVisible) {
        mTagVisible = tagVisible;
    }

    @Override
    public int getCount() {
        if (mTopics == null) return 0;
        return mTopics.size();
    }

    @Override
    public Topic getItem(int position) {
        return mTopics.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.topic_list_item, null);
            holder.tab = (TextView) convertView.findViewById(R.id.list_item_tab);
            holder.title = (TextView) convertView.findViewById(R.id.list_item_title);
            holder.count = (TextView) convertView.findViewById(R.id.list_item_count);
            holder.author = (TextView) convertView.findViewById(R.id.list_item_author);
            holder.time = (TextView) convertView.findViewById(R.id.list_item_time);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Topic topic = mTopics.get(position);
        String tab_string = topic.getTabString();
        if (tab_string != null) {
            holder.tab.setText(tab_string);
        }

        if (mTagVisible && tab_string != null) {
            holder.tab.setVisibility(View.VISIBLE);
        } else {
            holder.tab.setVisibility(View.GONE);
        }
        if (topic.mTop || topic.mGood) {
            holder.tab.setVisibility(View.VISIBLE);
            holder.tab.setBackgroundColor(mContext.getResources().getColor(R.color.colorTagH));
            holder.tab.setTextColor(mContext.getResources().getColor(R.color.colorTagTextH));
        } else {
            holder.tab.setBackgroundColor(mContext.getResources().getColor(R.color.colorTag));
            holder.tab.setTextColor(mContext.getResources().getColor(R.color.colorTagText));
        }


        holder.title.setText(topic.getTitle());
        holder.count.setText(topic.getCountText());
        holder.author.setText(topic.getAuthor().getLoginname());
        holder.time.setText(Utils.getTimeFormatText(topic.getLastReplyAt()));

        return convertView;
    }

    final class ViewHolder {
        TextView tab;
        TextView title;
        TextView count;
        TextView author;
        TextView time;
    }
}
