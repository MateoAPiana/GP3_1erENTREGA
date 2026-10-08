package persistencia;

import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MateriaData {

    private Connection con = null;

    public MateriaData(MiConexion con) {
        this.con = con.buscarConexion();
    }

    public void guardarMateria(Materia m) {
        String sql = "INSERT INTO Materia (nombre, estado) VALUES (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();  // recupero y asigno
            if (rs.next()) {
                m.setId(rs.getInt(1));
            } else {
                System.out.println("No se pudo tener ID");
            }
            ps.close();
            System.out.println("Guardado!");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public Materia buscarMateria(int id) {
        Materia m = null;

        String sql = "SELECT * FROM Materia WHERE idMateria = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                m = new Materia(id, rs.getString("nombre"), rs.getInt("estado"));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return m;
    }

    public List<Materia> listarMaterias() {
        Materia m = null;
        ArrayList<Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM Materia";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                m = new Materia(rs.getInt("idMateria"), rs.getString("nombre"), rs.getInt("estado"));
                materias.add(m);
            }
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materias;
    }
}
