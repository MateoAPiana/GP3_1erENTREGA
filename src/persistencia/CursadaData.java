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
    
    public Cursada buscarCursada(int id){
        String sql = "SELECT * FROM cursada WHERE idCursada = ?";
        
        AlumnoData ad = new AlumnoData(0, con);
        MateriaData md = new MateriaData(0, con);
        Cursada c = new Cursada();
        
        try{
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs =  ps.executeQuery();
            
            while(rs.next()){
                c.setId(rs.getInt(1));
                Alumno a = ad.buscarAlumno(rs.getInt(2));
                c.setAlumno(a);
                Materia m = md.buscarMateria(rs.getInt(3));
                c.setMateria(m);
                c.setNota(rs.getFloat(4));
                c.setAsist(rs.getInt(5));
                c.setCursa(rs.getInt(6));
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Cursada: " + ex.getMessage());
        }
        return c;
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
    
    public List<Cursada> obtenerCursadasPorAlumno(int idAlumno) {
        List<Cursada> cursadas = new ArrayList<>();
        String sql = "SELECT * FROM cursada WHERE idAlumno = ?";
        
        AlumnoData ad = new AlumnoData(0, con);
        MateriaData md = new MateriaData(0, con);
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cursada c = new Cursada();
                c.setId(rs.getInt(1));
                Alumno a = ad.buscarAlumno(idAlumno);
                c.setAlumno(a);
                Materia m = md.buscarMateria(rs.getInt(3));
                c.setMateria(m);
                c.setNota(rs.getFloat(4));
                c.setAsist(rs.getInt(5));
                c.setCursa(rs.getInt(6));
                cursadas.add(c);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener materias cursadas: " + ex.getMessage());
        }
        return cursadas;
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
    
    public void actualizarCursada(Cursada c){
        String sql = "UPDATE cursada SET idAlumno = ?, idMateria = ?, nota = ?, asist = ?, cursa = ? WHERE idCursada = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, c.getAlumno().getId());
            ps.setInt(2, c.getMateria().getId());
            ps.setFloat(3, c.getNota());
            ps.setFloat(4, c.getAsist());
            ps.setInt(5, c.getCursa());
            ps.setInt(6, c.getId());
            ps.executeUpdate();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Cursada: " + ex.getMessage());
        }
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
