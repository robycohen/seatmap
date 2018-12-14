package com.karyaanilo.seatmap;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.karyaanilo.seatmap.adapter.BaseRecyclerAdapter;
import com.karyaanilo.seatmap.bean.SeatMapBean;
import com.karyaanilo.seatmap.holder.SeatMapBindView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    NestedScrollView scrollView;
    RecyclerView recyclerView;
    Spinner gerbongSpinner;


    private List<SeatMapBean.StrukturKursi> strukturKursi;
    private List<SeatMapBean> listSeatMap;
    private ArrayAdapter<SeatMapBean> spinnerAdapter;
    private BaseRecyclerAdapter adapter;
    private GridLayoutManager gridLayoutManager;

    //select dummy (value >1 = multiple select)
    int numberSelected = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView = (NestedScrollView) findViewById(R.id.scrollView);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        gerbongSpinner = (Spinner) findViewById(R.id.gerbongSpinner);

        scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        scrollView.setFocusableInTouchMode(true);

        initSetup();
    }


    private void initSetup() {
        strukturKursi = new ArrayList();
        listSeatMap = new ArrayList();
        setupAdapter();
        setupSpinnerAdapter();
        setupSeatMap();
    }


    private void setupAdapter() {
        gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new BaseRecyclerAdapter(strukturKursi).onCreateVHolder(new SeatMapBindView());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        //adapter.setNumberSelected(numberSelected);
        adapter.setOnItemClicked(new BaseRecyclerAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(View view, int i, boolean z, int i2) {
                if (strukturKursi.get(i) != null && strukturKursi.get(i).getStatus() == 0) {
                    selectedSeat(i);
                }
            }
        });
    }

    private void setupSpinnerAdapter() {
        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listSeatMap);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gerbongSpinner.setAdapter(spinnerAdapter);
        gerbongSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setStrukturKursiMap(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void setupSeatMap() {
        //dummy seatmap value
        String seatSubClass = "[[\"K3AC\",1,[[1,1,1,\"A\",\"J\",1],[1,2,1,\"B\",\"J\",1],[1,4,1,\"C\",\"J\",1],[2,1,2,\"A\",\"J\",1]]],[\"K3AC\",2,[[1,1,1,\"A\",\"J\",1],[1,2,1,\"B\",\"J\",1],[1,4,1,\"C\",\"J\",1],[2,1,2,\"A\",\"J\",1],[2,2,2,\"B\",\"J\",1],[2,4,2,\"C\",\"J\",1],[2,5,2,\"D\",\"J\",1],[3,1,3,\"A\",\"J\",1],[3,2,3,\"B\",\"J\",1],[3,4,3,\"C\",\"J\",1],[3,5,3,\"D\",\"J\",1],[4,1,4,\"A\",\"J\",1],[4,2,4,\"B\",\"J\",1],[4,4,4,\"C\",\"J\",1],[4,5,4,\"D\",\"J\",1],[10,1,10,\"A\",\"J\",1],[10,2,10,\"B\",\"J\",1],[10,4,10,\"C\",\"J\",1],[10,5,10,\"D\",\"J\",1],[11,1,11,\"A\",\"J\",0],[11,2,11,\"B\",\"J\",0],[11,4,11,\"C\",\"J\",0],[11,5,11,\"D\",\"J\",1],[12,1,12,\"A\",\"J\",0],[12,2,12,\"B\",\"J\",0],[12,4,12,\"C\",\"J\",0],[12,5,12,\"D\",\"J\",0],[13,2,13,\"B\",\"J\",1],[13,4,13,\"C\",\"J\",0],[13,5,13,\"D\",\"J\",0]]],[\"K3AC\",3,[[1,1,1,\"A\",\"J\",1],[1,2,1,\"B\",\"J\",1],[1,4,1,\"C\",\"J\",1],[2,1,2,\"A\",\"J\",0]]],[\"K3AC\",4,[[1,1,1,\"A\",\"J\",1],[1,2,1,\"B\",\"J\",1],[1,4,1,\"C\",\"J\",0],[2,1,2,\"A\",\"J\",0]]],[\"K3AC\",5,[[1,1,1,\"A\",\"J\",1],[1,2,1,\"B\",\"J\",1],[1,4,1,\"C\",\"J\",1],[2,1,2,\"A\",\"J\",0]]]]";

        Object[][] ob = new Gson().fromJson(seatSubClass, Object[][].class);
        for (int i = 0; i < ob.length; i++) {
            Object[] obj = new Gson().fromJson(String.valueOf(ob[i][2]), Object[].class);
            List<SeatMapBean.StrukturKursi> listStr = new ArrayList();
            for (Object valueOf : obj) {
                Object[] objVelue = new Gson().fromJson(String.valueOf(valueOf), Object[].class);
                SeatMapBean.StrukturKursi strukturKursi = new SeatMapBean().new StrukturKursi();
                for (int i2 = 0; i2 < objVelue.length; i2++) {
                    strukturKursi.setBaris(new Double(objVelue[0].toString()).intValue());
                    strukturKursi.setKolom(new Double(objVelue[1].toString()).intValue());
                    strukturKursi.setSeatRow(new Double(objVelue[2].toString()).intValue());
                    strukturKursi.setSeatCol((String) objVelue[3]);
                    strukturKursi.setSubclass((String) objVelue[4]);
                    strukturKursi.setStatus(new Double(objVelue[5].toString()).intValue());
                }
                listStr.add(strukturKursi);
            }
            listSeatMap.add(new SeatMapBean((String) ob[i][0], new Double(ob[i][1].toString()).intValue(), listStr));
        }
        setStrukturKursiMap(0);
    }

    //selected seat
    private void selectedSeat(int position) {
        int j;
        if (numberSelected > 1) {
            int i = 0;
            j = 0;
            while (j < strukturKursi.size()) {
                if (strukturKursi.get(j) != null && strukturKursi.get(j).isSelected()) {
                    i++;
                    if (i >= numberSelected) {
                        break;
                    }
                }
                j++;
            }
            if (i < numberSelected) {
                strukturKursi.get(position).setSelected(!strukturKursi.get(position).isSelected());
            } else if (strukturKursi.get(position).isSelected()) {
                strukturKursi.get(position).setSelected(false);
            } else {
                Toast.makeText(this, "Anda sudah memilih " + numberSelected + " kursi", Toast.LENGTH_SHORT).show();
            }
        } else {
            j = 0;
            while (j < strukturKursi.size()) {
                if (strukturKursi.get(j) != null && strukturKursi.get(j).isSelected()) {
                    strukturKursi.get(j).setSelected(false);
                    break;
                }
                j++;
            }
            strukturKursi.get(position).setSelected(true);
        }
        adapter.notifyDataSetChanged();
    }

    private void setStrukturKursiMap(int selectedIndex) {
        strukturKursi.clear();
        for (int t = 0; t < listSeatMap.get(selectedIndex).getStrukturList().size(); t++) {
            listSeatMap.get(selectedIndex).getStrukturList().get(t).setSelected(false);
        }
        int row = listSeatMap.get(selectedIndex).
                getStrukturList().get(listSeatMap.get(selectedIndex).getStrukturList().size() - 1).getBaris();
        final int colums = getColumsCount(listSeatMap.get(selectedIndex).getStrukturList());
        Object[][] ob11 = (Object[][]) Array.newInstance(Object.class, new int[]{row, colums});
        for (SeatMapBean.StrukturKursi da : listSeatMap.get(selectedIndex).getStrukturList()) {
            ob11[da.getBaris() - 1][da.getKolom() - 1] = da;
        }
        for (int ii = 0; ii < ob11.length; ii++) {
            for (Object data : ob11[ii]) {
                strukturKursi.add((SeatMapBean.StrukturKursi) data);
            }
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView.getLayoutParams().width=getResources().getDimensionPixelSize(R.dimen.seat_size)*colums;
                gridLayoutManager.setSpanCount(colums);
                spinnerAdapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }
        });

    }


    private int getColumsCount(List<SeatMapBean.StrukturKursi> list) {
        int col = 0;
        for (int ii = 0; ii < list.size(); ii++) {
            boolean isDistinct = false;
            for (int j = 0; j < ii; j++) {
                if (list.get(ii).getSeatCol().equals(list.get(j).getSeatCol())) {
                    isDistinct = true;
                    break;
                }
            }
            if (!isDistinct) {
                col++;
            }
        }
        return col + 1;
    }
}
