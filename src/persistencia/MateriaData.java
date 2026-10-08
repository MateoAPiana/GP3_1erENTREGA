package persistencia;

import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MateriaData {
    private Connection con = null;

    public MateriaData(MiConexion con) {
        this.con = con.buscarConexion();
    }
    
    public void guardarMateria(Materia m){
        String sql = "INSERT INTO Materia (nombre, estado) VALUES (?, ?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getNombre());
            ps.setBoolean(2, m.isEstado());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();  // recupero y asigno
            if (rs.next()) {
                m.setId(rs.getInt(1));
            } else {
                System.out.println("No se pudo tener ID");
            }
            ps.close();
            System.out.println("Guardado!");
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }
        
        
    }
}
