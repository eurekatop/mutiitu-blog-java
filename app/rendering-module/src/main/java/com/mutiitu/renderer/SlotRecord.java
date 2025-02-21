package com.mutiitu.renderer;

public class SlotRecord {
    public int key;
    public int getKey() {
        return this.key;
    }
    public void setKey(int key) {
        this.key = key;
    }
    public String getSlot0() {
        return this.slot0;
    }
    public void setSlot0(String slot0) {
        this.slot0 = slot0;
    }
    public String slot0;
    public SlotRecord(int key, String slot0) {
        this.key = key;
        this.slot0 = slot0;
    }
    public String toString() {
        return "SlotRecord{key=" + this.key + ", slot0='" + this.slot0 + "'}";
    }
}