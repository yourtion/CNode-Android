package com.yourtion.cnode_android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yourtion.cnode_android.Modules.Topic;

import java.util.ArrayList;

/**
 * Created by Yourtion on 9/2/16.
 */
public class CNodeListAdapter extends RecyclerView.Adapter<CNodeListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Topic> mTopics;

    public CNodeListAdapter(Context context, ArrayList<Topic> topics) {
        this.mContext = context;
        this.mTopics = topics;
    }

    public void setTopics(ArrayList<Topic> topics) {
        mTopics = topics;
    }

    @Override
    public int getItemCount() {
        if (mTopics == null) return 0;
        return mTopics.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.topic_list_item, parent, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Topic topic = mTopics.get(position);
        String tab_string = topic.getTabString();

        if (topic.mTop || topic.mGood) {
            holder.tab.setBackgroundColor(mContext.getResources().getColor(R.color.colorTagH));
            holder.tab.setTextColor(mContext.getResources().getColor(R.color.colorTagTextH));
        }
        if (tab_string != null) {
            holder.tab.setText(topic.getTabString());
        } else {
            holder.tab.setVisibility(View.GONE);
        }

        holder.title.setText(topic.getTitle());
        holder.count.setText(topic.getCountText());
        holder.author.setText(topic.getAuthor().getLoginname());
        holder.time.setText(Utils.getTimeFormatText(topic.getLastReplyAt()));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tab;
        TextView title;
        TextView count;
        TextView author;
        TextView time;

        public ViewHolder(View convertView) {
            super(convertView);
            tab = (TextView) convertView.findViewById(R.id.list_item_tab);
            title = (TextView) convertView.findViewById(R.id.list_item_title);
            count = (TextView) convertView.findViewById(R.id.list_item_count);
            author = (TextView) convertView.findViewById(R.id.list_item_author);
            time = (TextView) convertView.findViewById(R.id.list_item_time);
        }
    }
}

