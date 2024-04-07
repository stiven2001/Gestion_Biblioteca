package com.mycompany.models;

public class Users {
    
    private static int nextId = 1;  // Contador estático para los IDs
    
    public Users(int id, String name, String last_name_p, String last_name_m, String domicilio, String tel, int sanctions, int sanc_money) {
        this.id =id;
        this.name = name;
        this.last_name_p = last_name_p;
        this.last_name_m = last_name_m;
        this.domicilio = domicilio;
        this.tel = tel;
        this.sanctions = sanctions;
        this.sanc_money = sanc_money;
    }

    public Users() {
        this.id = nextId++;
        this.name = "";
        this.last_name_p = "";
        this.last_name_m = "";
        this.domicilio = "";
        this.tel = "";
        this.sanctions = sanctions;
        this.sanc_money = sanc_money;
    }

    
    private int id;
    private String name;
    private String last_name_p;
    private String last_name_m;
    private String domicilio;
    private String tel;
    private int sanctions;
    private int sanc_money;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLast_name_p(String last_name_p) {
        this.last_name_p = last_name_p;
    }

    public void setLast_name_m(String last_name_m) {
        this.last_name_m = last_name_m;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setSanctions(int sanctions) {
        this.sanctions = sanctions;
    }

    public void setSanc_money(int sanc_money) {
        this.sanc_money = sanc_money;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLast_name_p() {
        return last_name_p;
    }

    public String getLast_name_m() {
        return last_name_m;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getTel() {
        return tel;
    }

    public int getSanctions() {
        return sanctions;
    }

    public int getSanc_money() {
        return sanc_money;
    }
}
