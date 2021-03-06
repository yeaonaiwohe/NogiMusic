package com.example.nogimusic;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<HistorySearch> mhistorySearchList;
    private OnItemClickListener mOnItemClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView historysearch_content;

        public ViewHolder(View view){
            super(view);
            historysearch_content = (TextView) view.findViewById(R.id.search_content);
        }
    }

    public interface OnItemClickListener
    {
        //子条目单机事件
        void onItemClick(View view, int position);
    }

    public  void setmOnItemClickListener (OnItemClickListener mOnItemClickListener)
    {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    public SearchAdapter(List<HistorySearch> historySearchList){
        mhistorySearchList = historySearchList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        //activity调用setOnItemClickListener() 如果调用接口不为空执行下面逻辑
        if (mOnItemClickListener != null) {
            holder.historysearch_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //返回对应view的信息
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.historysearch_content, pos);
                }
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistorySearch search = mhistorySearchList.get(position);
        holder.historysearch_content.setText(search.getContent());
    }

    @Override
    public int getItemCount() {
        return mhistorySearchList.size();
    }
}
