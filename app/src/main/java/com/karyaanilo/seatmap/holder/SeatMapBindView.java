package com.karyaanilo.seatmap.holder;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karyaanilo.seatmap.R;
import com.karyaanilo.seatmap.adapter.AttachViewHolder;
import com.karyaanilo.seatmap.bean.SeatMapBean;

public class SeatMapBindView extends AttachViewHolder<ViewHolder> {
    private final int VIEW_EMPTY = 5;
    private final int VIEW_SEAT_AVAILABLE = 2;
    private final int VIEW_SEAT_SELECTED = 4;
    private final int VIEW_SEAT_UNAVAILABLE = 3;

    public class EmptyViewHolder extends ViewHolder {
        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class SeatMapHolder extends ViewHolder {
        TextView seatNoTxt;

        public SeatMapHolder(View itemView) {
            super(itemView);
            this.seatNoTxt = (TextView) itemView.findViewById(R.id.seatNoTxt);
        }
    }

    public void bindView(ViewHolder holder, int position, Object data) {
        if (holder instanceof SeatMapHolder) {
            SeatMapBean.StrukturKursi value = (SeatMapBean.StrukturKursi) data;
            if (value != null) {
                ((SeatMapHolder) holder).seatNoTxt.setText(value.getBaris() + value.getSeatCol());
            }
        }
    }

    public ViewHolder onCreateVH(ViewGroup parent, int viewType) {
        if (viewType == VIEW_SEAT_AVAILABLE) {
            return new SeatMapHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seat_avalaible, parent, false));
        }
        if (viewType == VIEW_SEAT_UNAVAILABLE) {
            return new SeatMapHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seat_unavailable, parent, false));
        }
        if (viewType == VIEW_SEAT_SELECTED) {
            return new SeatMapHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seat_selected, parent, false));
        }
        if (viewType == VIEW_EMPTY) {
            return new EmptyViewHolder(new View(parent.getContext()));
        }
        return null;
    }

    public int getItemViewType(int position, Object object, int lastposisi) {
        SeatMapBean.StrukturKursi data = (SeatMapBean.StrukturKursi) object;
        if (data == null) {
            return VIEW_EMPTY;
        }
        if (data.getStatus() == 0 && !data.isSelected()) {
            return VIEW_SEAT_AVAILABLE;
        }
        if (data.getStatus() == 0 && data.isSelected()) {
            return VIEW_SEAT_SELECTED;
        }
        return VIEW_SEAT_UNAVAILABLE;
    }
}
