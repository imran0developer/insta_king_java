package com.unitapplications.InstaKing.Models;

public class NoteModel {
    String note;

    public NoteModel(String note) {
        this.note = note;
    }

    public NoteModel() {
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}