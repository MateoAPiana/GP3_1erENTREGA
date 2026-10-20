package persistencia;

import entidades.Alumno;
import entidades.Cursada;
import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CursadaData {
    private Connection con = null;

    public CursadaData(MiConexion conexion) {
        con = conexion.buscarConexion();
    }

public void guardarCursada(Cursada cursada) {
    String sql = "INSERT INTO cursada (nota, asist, cursa, idAlumno, idMateria) VALUES (?, ?, ?, ?, ?)";
    try {
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setFloat(1, cursada.getNota());
        ps.setFloat(2, cursada.getAsist());
        ps.setInt(3, cursada.getCursa());
        ps.setInt(4, cursada.getAlumno().getId());
        ps.setInt(5, cursada.getMateria().getId());
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            cursada.setId(rs.getInt(1));
            JOptionPane.showMessageDialog(null, "¡Inscripción realizada con éxito!");
        }
        ps.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Cursada: " + ex.getMessage());
    }
}

public List<Materia> obtenerMateriasCursadas(int idAlumno) {
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT m.idMateria, m.nombre, m.estado FROM materia m JOIN cursada c ON (m.idMateria = c.idMateria) WHERE c.idAlumno = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setId(rs.getInt(1));    
                materia.setNombre(rs.getString(2)); 
                materia.setEstado(rs.getInt(3));   
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener materias cursadas: " + ex.getMessage());
        }
        return materias;
    }

    public List<Materia> obtenerMateriasNoCursadas(int idAlumno) {
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT idMateria, nombre, estado FROM materia WHERE idMateria NOT IN (SELECT idMateria FROM cursada WHERE idAlumno = ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setId(rs.getInt(1));
                materia.setNombre(rs.getString(2));
                materia.setEstado(rs.getInt(3));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener materias no cursadas: " + ex.getMessage());
        }
        return materias;
    }

    public void borrarCursada(int idAlumno, int idMateria) {
        String sql = "DELETE FROM cursada WHERE idAlumno = ? AND idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "¡Inscripción anulada con éxito!");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar cursada: " + ex.getMessage());
        }
    }
}