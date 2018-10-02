package com.karyaanilo.seatmap.adapter;

import android.view.ViewGroup;

public interface OnAttachViewHolder<T> {
    void bindView(T t, int i, Object obj);

    T onCreateVH(ViewGroup holder, int i);
}
