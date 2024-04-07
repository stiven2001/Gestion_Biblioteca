package com.mycompany.ilib;

/*import com.mycompany.db.Database;*/
import com.mycompany.interfaces.DAOBooks;
import com.mycompany.models.Books;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.CharBuffer;
/*import java.sql.PreparedStatement;
import java.sql.ResultSet;*/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
//extends Database
public class DAOBooksImpl  implements DAOBooks {
    private List<Books> books = new ArrayList<>();
    
    
     /*public DAOBooksImpl() {
        // Quemar algunos libros
        books.add(new Books(1, "Don Quijote de la Mancha", "01/01/1605", "Miguel de Cervantes", "Novela", "Francisco de Robles", "Español", "863", "Una novela sobre un caballero andante", "1ra edición", 10, 10));
        books.add(new Books(2, "Cien años de soledad", "05/30/1967", "Gabriel García Márquez", "Realismo mágico", "Editorial Sudamericana", "Español", "471", "Una novela sobre la familia Buendía", "1ra edición", 10, 10));
        // Agrega más libros aquí...
    }*/
    
    @Override
    public void registrar(Books book) throws Exception {
        books.add(book);
        guardarLibros();
         System.out.println("Libro registrado: " + book.getTitle() + book.getId() + book.getStock() + book.getAvailable()); 
    }

    @Override
    public void modificar(Books book) throws Exception {
        for (Books b : books) {
            if (b.getId() == book.getId()) {
                b.setTitle(book.getTitle());
                b.setDate(book.getDate());
                b.setAuthor(book.getAuthor());
                b.setCategory(book.getCategory());
                b.setEdit(book.getEdit());
                b.setLang(book.getLang());
                b.setPages(book.getPages());
                b.setDescription(book.getDescription());
                b.setEjemplares(book.getEjemplares());
                b.setStock(book.getStock());
                b.setAvailable(book.getAvailable());
                guardarLibros();
                break;
            }
        }
    }

    @Override
    public void eliminar(int bookId) throws Exception {
        books.removeIf(b -> b.getId() == bookId);
        guardarLibros();
    }

    @Override
    public List<Books> listar(String title) throws Exception {
        if (title.isEmpty()) {
            return new ArrayList<>(books);
        } else {
            return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
        }
    }

    @Override
    public Books getBookById(int bookId) throws Exception {
        for (Books b : books) {
            if (b.getId() == bookId) {
                return b;
            }
        }
        return null;
    }
    
    public DAOBooksImpl() {
    cargarLibros();
}
    
    private void cargarLibros() {
    try (Scanner scanner = new Scanner(new File("books.txt"))) {
        while (scanner.hasNextLine()) {
            String[] campos = scanner.nextLine().split(",");
            Books book = new Books(Integer.parseInt(campos[0]), campos[1], campos[2], campos[3], campos[4], campos[5], campos[6], campos[7], campos[8], campos[9], Integer.parseInt(campos[10]), Integer.parseInt(campos[11]));
            books.add(book);
        }
    } catch (FileNotFoundException e) {
        System.out.println("No se encontró el archivo de libros: " + e.getMessage());
    }
}
    
    private void guardarLibros() {
    try (PrintWriter out = new PrintWriter(new FileWriter("books.txt"))) {
        for (Books book : books) {
            out.println(book.getId() + "," + book.getTitle() + "," + book.getDate()+ "," + book.getAuthor() + "," + book.getCategory() + "," + book.getEdit() + "," + book.getLang() + "," + book.getPages() + "," + book.getDescription() + "," + book.getEjemplares() + "," + book.getStock() + "," + book.getAvailable());
        }
    } catch (IOException e) {
        System.out.println("Error al guardar los libros: " + e.getMessage());
    }
}




    /*Si queremos conectarnos a una Bd así sería el metodo:*/
    /*@Override
    public void registrar(Books book) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO books(title, fecha, author, category, edit, lang, pages, description, ejemplares, stock, available) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            st.setString(1, book.getTitle());
            st.setString(2, book.getDate());
            st.setString(3, book.getAuthor());
            st.setString(4, book.getCategory());
            st.setString(5, book.getEdit());
            st.setString(6, book.getLang());
            st.setString(7, book.getPages());
            st.setString(8, book.getDescription());
            st.setString(9, book.getEjemplares());
            st.setInt(10, book.getStock());
            st.setInt(11, book.getAvailable());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void modificar(Books book) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE books SET title = ?, fecha = ?, author = ?, category = ?, edit = ?, lang = ?, pages = ?, description = ?, ejemplares = ?, stock = ?, available = ? WHERE id = ?");
            st.setString(1, book.getTitle());
            st.setString(2, book.getDate());
            st.setString(3, book.getAuthor());
            st.setString(4, book.getCategory());
            st.setString(5, book.getEdit());
            st.setString(6, book.getLang());
            st.setString(7, book.getPages());
            st.setString(8, book.getDescription());
            st.setString(9, book.getEjemplares());
            st.setInt(10, book.getStock());
            st.setInt(11, book.getAvailable());
            st.setInt(12, book.getId());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(int bookId) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM books WHERE id = ?");
            st.setInt(1, bookId);
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<Books> listar(String title) throws Exception {
        List<Books> lista = null;
        try {
            this.Conectar();
            String Query = title.isEmpty() ? "SELECT * FROM books" : "SELECT * FROM books WHERE LOWER(title) LIKE LOWER('%" + title + "%')";
            PreparedStatement st = this.conexion.prepareStatement(Query);
            
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Books book = new Books();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setDate(rs.getString("fecha"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setEdit(rs.getString("edit"));
                book.setLang(rs.getString("lang"));
                book.setPages(rs.getString("pages"));
                book.setDescription(rs.getString("description"));
                book.setEjemplares(rs.getString("ejemplares"));
                book.setStock(rs.getInt("stock"));
                book.setAvailable(rs.getInt("available"));
                lista.add(book);
            }
            rs.close();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }
    
 
    @Override
    public Books getBookById(int bookId) throws Exception {
        Books book = null;
        
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM books WHERE id = ? AND ROWNUM <= 1");
            st.setInt(1, bookId);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                book = new Books();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setDate(rs.getString("fecha"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setEdit(rs.getString("edit"));
                book.setLang(rs.getString("lang"));
                book.setPages(rs.getString("pages"));
                book.setDescription(rs.getString("description"));
                book.setEjemplares(rs.getString("ejemplares"));
                book.setStock(rs.getInt("stock"));
                book.setAvailable(rs.getInt("available"));
            }
            rs.close();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return book;
    }*/ 

    
}