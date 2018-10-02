package com.karyaanilo.seatmap.adapter;

public abstract class AttachViewHolder<T> implements OnAttachViewHolder<T> {
    public int getItemViewType(int position, Object object, int lastposisi) {
        return 0;
    }

    public void setSelectedRow(Integer selected) {
    }

    public void setNumberSelected(Integer selected) {
    }

}
