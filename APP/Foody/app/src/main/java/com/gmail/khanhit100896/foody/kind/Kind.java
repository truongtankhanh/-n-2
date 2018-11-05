package com.gmail.khanhit100896.foody.kind;

public class Kind {

    private int id;
    private String kind_code;
    private String kind_name;

    public Kind(int id, String kind_code, String kind_name) {
        this.setId(id);
        this.setKind_code(kind_code);
        this.setKind_name(kind_name);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getKind_code() {
        return kind_code;
    }

    private void setKind_code(String kind_code) {
        this.kind_code = kind_code;
    }

    public String getKind_name() {
        return kind_name;
    }

    private void setKind_name(String kind_name) {
        this.kind_name = kind_name;
    }
}
