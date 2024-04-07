package com.mycompany.models;

public class Books {
    
    private static int nextId = 1;  // Contador estático para los IDs
    
    public Books(int id, String title, String fecha, String author, String category, String edit, String lang, String pages, String description, String ejemplares, int stock, int available) {
        this.id = id;
        this.title = title;
        this.fecha = fecha;
        this.author = author;
        this.category = category;
        this.edit = edit;
        this.lang = lang;
        this.pages = pages;
        this.description = description;
        this.ejemplares = ejemplares;
        this.stock = stock;
        this.available = available;
    }
    
    private int id;
    private String title;
    private String fecha;
    private String author;
    private String category;
    private String edit;
    private String lang;
    private String pages;
    private String description;
    private String ejemplares;
    private int stock;
    private int available;

    /*public Books() {
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
   public Books() {
    this.id = nextId++;
    this.title = "";
    this.fecha = "";
    this.author = "";
    this.category = "";
    this.edit = "";
    this.lang = "";
    this.pages = "";
    this.description = "";
    this.ejemplares = "";
    this.stock = stock;
    this.available = available;
}

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String fecha) {
        this.fecha = fecha;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEjemplares(String ejemplares) {
        this.ejemplares = ejemplares;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return fecha;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getEdit() {
        return edit;
    }

    public String getLang() {
        return lang;
    }

    public String getPages() {
        return pages;
    }

    public String getDescription() {
        return description;
    }

    public String getEjemplares() {
        return ejemplares;
    }

    public int getStock() {
        return stock;
    }

    public int getAvailable() {
        return available;
    }
}
