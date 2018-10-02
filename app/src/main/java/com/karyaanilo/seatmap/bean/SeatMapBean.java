package com.karyaanilo.seatmap.bean;

import java.util.List;

public class SeatMapBean {
    private String namaGerbong;
    private int noGerbong;
    private List<StrukturKursi> strukturList;

    public class StrukturKursi {
        private int baris;
        private int kolom;
        private String seatCol;
        private int seatRow;
        private boolean selected = false;
        private int status;
        private String subclass;

        public int getKolom() {
            return this.kolom;
        }

        public void setKolom(int kolom) {
            this.kolom = kolom;
        }

        public int getSeatRow() {
            return this.seatRow;
        }

        public void setSeatRow(int seatRow) {
            this.seatRow = seatRow;
        }

        public String getSeatCol() {
            return this.seatCol;
        }

        public void setSeatCol(String seatCol) {
            this.seatCol = seatCol;
        }

        public String getSubclass() {
            return this.subclass;
        }

        public void setSubclass(String subclass) {
            this.subclass = subclass;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getBaris() {
            return this.baris;
        }

        public void setBaris(int baris) {
            this.baris = baris;
        }

        public boolean isSelected() {
            return this.selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }

    public SeatMapBean(String namaGerbong, int noGerbong, List<StrukturKursi> strukturList) {
        this.namaGerbong = namaGerbong;
        this.noGerbong = noGerbong;
        this.strukturList = strukturList;
    }

    public SeatMapBean(){}

    public String getNamaGerbong() {
        return this.namaGerbong;
    }

    public void setNamaGerbong(String namaGerbong) {
        this.namaGerbong = namaGerbong;
    }

    public int getNoGerbong() {
        return this.noGerbong;
    }

    public void setNoGerbong(int noGerbong) {
        this.noGerbong = noGerbong;
    }

    public List<StrukturKursi> getStrukturList() {
        return this.strukturList;
    }

    public void setStrukturList(List<StrukturKursi> strukturList) {
        this.strukturList = strukturList;
    }

    public String toString() {
        return (/*this.namaGerbong +*/ "GERBONG " + this.noGerbong).toUpperCase();
    }
}
