package com.mycompany.models;

public class Lendings {
    
    private static int nextId = 1;  // Contador estático para los IDs
     private static int nextuser_id = 1;
      private static int nextIdbook_id = 1;
    
    public Lendings(int id, int user_id, int book_id, String date_out, String date_return) {
        this.id = id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.date_out = date_out;
        this.date_return = date_return;
    }

    public Lendings() {
        this.id = nextId++; // Puedes reemplazar esto con un contador o un generador de ID si lo necesitas
        this.user_id = nextuser_id++;
        this.book_id = nextIdbook_id++;
        this.date_out = "";
        this.date_return = "";
    }
    
    private int id;
    private int user_id;
    private int book_id;
    private String date_out;
    private String date_return;

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setDate_out(String date_out) {
        this.date_out = date_out;
    }

    public void setDate_return(String date_return) {
        this.date_return = date_return;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public String getDate_out() {
        return date_out;
    }

    public String getDate_return() {
        return date_return;
    }
}
