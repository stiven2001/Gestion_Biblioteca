package com.mycompany.ilib;

/*import com.mycompany.db.Database;*/
import com.mycompany.interfaces.DAOLendings;
import com.mycompany.models.Books;
import com.mycompany.models.Lendings;
import com.mycompany.models.Users;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*import java.sql.PreparedStatement;
import java.sql.ResultSet;*/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOLendingsImpl implements DAOLendings {
    
    private List<Lendings> lendings = new ArrayList<>();
    
        @Override
    public void registrar(Lendings lending) throws Exception {
        lendings.add(lending);
        guardarLendings();
    }

    @Override
    public void modificar(Lendings lending) throws Exception {
        for (Lendings l : lendings) {
            if (l.getId() == lending.getId()) {
                l.setUser_id(lending.getUser_id());
                l.setBook_id(lending.getBook_id());
                l.setDate_out(lending.getDate_out());
                l.setDate_return(lending.getDate_return());
                guardarLendings();
                break;
            }
        }
    }

    @Override
    public Lendings getLending(Users user, Books book) throws Exception {
        for (Lendings l : lendings) {
            if (l.getUser_id() == user.getId() && l.getBook_id() == book.getId() && l.getDate_return() == null) {
                return l;
            }
        }
        return null;
    }

    @Override
    public List<Lendings> listar() throws Exception {
        return new ArrayList<>(lendings);
    }
    
    public DAOLendingsImpl() {
        cargarLendings();
    }

    private void cargarLendings() {
           try (Scanner scanner = new Scanner(new File("lendings.txt"))) {
        while (scanner.hasNextLine()) {
            String[] campos = scanner.nextLine().split(",");
            String date_return = campos.length > 4 ? campos[4] : null;  // Si date_return no está presente, lo establecemos como null
            Lendings lending = new Lendings(Integer.parseInt(campos[0]), Integer.parseInt(campos[1]), Integer.parseInt(campos[2]), campos[3], date_return);
            lendings.add(lending);
        }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DAOLendingsImpl.class.getName()).log(Level.SEVERE, null, ex);
            }    
    }

    private void guardarLendings() {
        try (PrintWriter out = new PrintWriter(new FileWriter("lendings.txt"))) {
            for (Lendings lending : lendings) {
            String date_return = lending.getDate_return() != null ? lending.getDate_return() : "";  // Si date_return es null, lo escribimos como una cadena vacía
            out.println(lending.getId() + "," + lending.getUser_id() + "," + lending.getBook_id() + "," + lending.getDate_out() + "," + date_return);
        }
        } catch (IOException e) {
            System.out.println("Error al guardar los préstamos: " + e.getMessage());
        }
    }
    
    
    //Metodo conectar a BD.
   /* @Override
    public void registrar(Lendings lending) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO lendings(user_id, book_id, date_out) VALUES(?,?,?)");
            st.setInt(1, lending.getUser_id());
            st.setInt(2, lending.getBook_id());
            st.setString(3, lending.getDate_out());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void modificar(Lendings lending) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE lendings SET user_id = ?, book_id = ?, date_out = ?, date_return = ? WHERE id = ?");
            st.setInt(1, lending.getUser_id());
            st.setInt(2, lending.getBook_id());
            st.setString(3, lending.getDate_out());
            st.setString(4, lending.getDate_return());
            st.setInt(5, lending.getId());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public Lendings getLending(Users user, Books book) throws Exception {
        Lendings lending = null;
        
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM lendings WHERE user_id = ? AND book_id = ? AND date_return IS NULL AND ROWNUM <=1 ORDER BY id DESC");
            st.setInt(1, user.getId());
            st.setInt(2, book.getId());
            
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lending = new Lendings();
                lending.setId(rs.getInt("id"));
                lending.setUser_id(rs.getInt("user_id"));
                lending.setBook_id(rs.getInt("book_id"));
                lending.setDate_out(rs.getString("date_out"));
                lending.setDate_return(rs.getString("date_return"));
            }
            
            st.close();
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        
        return lending;
    }

    @Override
    public List<Lendings> listar() throws Exception {
        List<Lendings> lista = null;
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM lendings ORDER BY id DESC");
            
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Lendings lending = new Lendings();
                lending.setId(rs.getInt("id"));
                lending.setUser_id(rs.getInt("user_id"));
                lending.setBook_id(rs.getInt("book_id"));
                lending.setDate_out(rs.getString("date_out"));
                lending.setDate_return(rs.getString("date_return"));
                lista.add(lending);
            }
            rs.close();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }*/

}
