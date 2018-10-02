package com.karyaanilo.seatmap.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class BaseRecyclerAdapter<T extends ViewHolder> extends Adapter<T> {
    private boolean isLoading;
    private int lastVivibleItem;
    private List list;
    private OnItemClickedListener listener;
    private AttachViewHolder onAttachViewHolder;
    private OnLoadMoreListener onLoadMoreListener;
    private int totalItemCount;
    int visibleThreshold = 5;

    public interface OnItemClickedListener {
        void onItemClicked(View view, int i, boolean z, int i2);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public BaseRecyclerAdapter(List listBeanList) {
        this.list = listBeanList;
    }

    public BaseRecyclerAdapter(RecyclerView recyclerView, List listBeanList) {
        this.list = listBeanList;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                BaseRecyclerAdapter.this.totalItemCount = linearLayoutManager.getItemCount();
                BaseRecyclerAdapter.this.lastVivibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!BaseRecyclerAdapter.this.isLoading && BaseRecyclerAdapter.this.totalItemCount <= BaseRecyclerAdapter.this.lastVivibleItem + BaseRecyclerAdapter.this.visibleThreshold) {
                    if (BaseRecyclerAdapter.this.onLoadMoreListener != null) {
                        BaseRecyclerAdapter.this.onLoadMoreListener.onLoadMore();
                    }
                    BaseRecyclerAdapter.this.isLoading = true;
                }
            }
        });
    }

    public BaseRecyclerAdapter onCreateVHolder(AttachViewHolder AttachImpl) {
        this.onAttachViewHolder = AttachImpl;
        return this;
    }

    public void setOnItemClicked(OnItemClickedListener listener) {
        this.listener = listener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        return (T) this.onAttachViewHolder.onCreateVH(parent, viewType);
    }

    public void onBindViewHolder(T holder, final int position) {
        Object ob = this.list.get(position);
        if (this.listener != null) {
            holder.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    BaseRecyclerAdapter.this.listener
                            .onItemClicked(view, position, BaseRecyclerAdapter.this.isLoading, BaseRecyclerAdapter.this.list.size());
                }
            });
        }
        this.onAttachViewHolder.bindView(holder, position, ob);
    }

    public void filterList(ArrayList<String> filterdNames) {
        this.list = filterdNames;
        notifyDataSetChanged();
    }

    public void stopLoading() {
        this.isLoading = false;
    }

    public int getItemCount() {
        return this.list == null ? 0 : this.list.size();
    }

    public int getItemViewType(int position) {
        return this.onAttachViewHolder.getItemViewType(position, this.list.get(position), this.list.size());
    }

    public void setSelected(Integer selected) {
        this.onAttachViewHolder.setSelectedRow(selected);
        notifyDataSetChanged();
    }

    public void setNumberSelected(Integer selected) {
        this.onAttachViewHolder.setNumberSelected(selected);
    }

}
