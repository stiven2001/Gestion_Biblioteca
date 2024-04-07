package com.mycompany.ilib;

/*import com.mycompany.db.Database;*/
import com.mycompany.interfaces.DAOUsers;
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
import java.util.stream.Collectors;

public class DAOUsersImpl implements DAOUsers {
    
    private List<Users> users = new ArrayList<>();
    
    @Override
    public void registrar(Users user) throws Exception {
        users.add(user);
        guardarUsers();
    }

    @Override
    public void modificar(Users user) throws Exception {
        for (Users u : users) {
            if (u.getId() == user.getId()) {
                u.setName(user.getName());
                u.setLast_name_p(user.getLast_name_p());
                u.setLast_name_m(user.getLast_name_m());
                u.setDomicilio(user.getDomicilio());
                u.setTel(user.getTel());
                u.setSanctions(user.getSanctions());
                u.setSanc_money(user.getSanc_money());
                guardarUsers();
                break;
            }
        }
    }

    @Override
    public void eliminar(int userId) throws Exception {
        users.removeIf(u -> u.getId() == userId);
         guardarUsers();  // Agrega esta línea
    }

    @Override
    public List<Users> listar(String name) throws Exception {
        if (name.isEmpty()) {
            return new ArrayList<>(users);
        } else {
            return users.stream()
                .filter(u -> u.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        }
    }

    @Override
    public Users getUserById(int userId) throws Exception {
        for (Users u : users) {
            if (u.getId() == userId) {
                return u;
            }
        }
        return null;
    }
    
    @Override
    public void sancionar(Users user) throws Exception {
        for (Users u : users) {
            if (u.getId() == user.getId()) {
                u.setSanctions(user.getSanctions());
                u.setSanc_money(user.getSanc_money());
                break;
            }
        }
    }

    
    public DAOUsersImpl() {
    cargarUsers();
}
    
   private void cargarUsers() {
    try (Scanner scanner = new Scanner(new File("users.txt"))) {
        while (scanner.hasNextLine()) {
            String[] campos = scanner.nextLine().split(",");
            Users user = new Users(Integer.parseInt(campos[0]), campos[1], campos[2], campos[3], campos[4], campos[5], Integer.parseInt(campos[6]), Integer.parseInt(campos[7]));
            users.add(user);
        }
            } catch (FileNotFoundException e) {
                System.out.println("No se encontró el archivo de usuarios: " + e.getMessage());
            }
    }

    private void guardarUsers() {
    try (PrintWriter out = new PrintWriter(new FileWriter("users.txt"))) {
            for (Users user : users) {
                out.println(user.getId() + "," + user.getName() + "," + user.getLast_name_p() + "," + user.getLast_name_m() + "," + user.getDomicilio() + "," + user.getTel() + "," + user.getSanctions() + "," + user.getSanc_money());
            }
        } catch (IOException e) {
           System.out.println("Error al guardar los usuarios: " + e.getMessage());
        }
    }



    
    //Metodo conectar a BD.
   /* @Override
    public void registrar(Users user) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO users(name, last_name_p, last_name_m, domicilio, tel) VALUES(?,?,?,?,?)");
            st.setString(1, user.getName());
            st.setString(2, user.getLast_name_p());
            st.setString(3, user.getLast_name_m());
            st.setString(4, user.getDomicilio());
            st.setString(5, user.getTel());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void modificar(Users user) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE users SET name = ?, last_name_p = ?, last_name_m = ?, domicilio = ?, tel = ? WHERE id = ?");
            st.setString(1, user.getName());
            st.setString(2, user.getLast_name_p());
            st.setString(3, user.getLast_name_m());
            st.setString(4, user.getDomicilio());
            st.setString(5, user.getTel());
            st.setInt(6, user.getId());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(int userId) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM users WHERE id = ?");
            st.setInt(1, userId);
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<Users> listar(String name) throws Exception {
        List<Users> lista = null;
        try {
            this.Conectar();
            String Query = name.isEmpty() ? "SELECT * FROM users" : "SELECT * FROM users WHERE name LIKE '%" + name + "%'";
            PreparedStatement st = this.conexion.prepareStatement(Query);
            
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLast_name_p(rs.getString("last_name_p"));
                user.setLast_name_m(rs.getString("last_name_m"));
                user.setDomicilio(rs.getString("domicilio"));
                user.setTel(rs.getString("tel"));
                user.setSanctions(rs.getInt("sanctions"));
                user.setSanc_money(rs.getInt("sanc_money"));
                lista.add(user);
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
    public Users getUserById(int userId) throws Exception {
        Users user = null;
        
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM users WHERE id = ? AND ROWNUM <=1");
            st.setInt(1, userId);
            
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                user = new Users();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLast_name_p(rs.getString("last_name_p"));
                user.setLast_name_m(rs.getString("last_name_m"));
                user.setDomicilio(rs.getString("domicilio"));
                user.setTel(rs.getString("tel"));
                user.setSanctions(rs.getInt("sanctions"));
                user.setSanc_money(rs.getInt("sanc_money"));
            }
            rs.close();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return user;
    }

    @Override
    public void sancionar(Users user) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE users SET sanctions = ?, sanc_money = ? WHERE id = ?");
            st.setInt(1, user.getSanctions());
            st.setInt(2, user.getSanc_money());
            st.setInt(3, user.getId());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }*/
    
}
