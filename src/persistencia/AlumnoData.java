package persistencia;

import entidades.Alumno;
import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlumnoData {

    private Connection con = null;

    public AlumnoData(MiConexion conexion) {
        this.con = conexion.buscarConexion();
    }

    public void guardarAlumno(Alumno a) {    // obj alumno sin id valido
        String sql = "INSERT INTO alumno(dni, nombre, fecNac, activo) VALUES (?,?,?,?)";  //1

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //2
            ps.setInt(1, a.getDni());
            ps.setString(2, a.getNombre());
            ps.setDate(3, Date.valueOf(a.getFecNac()));
            ps.setBoolean(4, a.isActivo());
            ps.executeUpdate();     // 3

            ResultSet rs = ps.getGeneratedKeys();  // recupero y asigno
            if (rs.next()) {
                a.setId(rs.getInt(1));
            } else {
                System.out.println("No se pudo tener ID");
            }
            ps.close();
            System.out.println("Guardado!");
        } catch (SQLException ex) {
            System.out.println("No pude insertar");
        }

    }

    public Alumno buscarAlumno(int id) {
        Alumno a = null;
        String sql = "SELECT * FROM alumno WHERE idAlumno= ?";  //1

        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);    // 2
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();  //3
            while (rs.next()) {  // 4 armo el objeto
                a = new Alumno();
                a.setId(id);
                a.setDni(rs.getInt("dni"));
                a.setNombre(rs.getString("nombre"));
                a.setFecNac(rs.getDate("fecNac").toLocalDate()); // Date.valueOf( )
                a.setActivo(rs.getBoolean("activo"));
            }
            ps.close(); // 5

        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return a;
    }  // SELECT 1 ALUMNO

    public List<Alumno> listarAlumnos() {
        Alumno a = null;   // ALUMNO recipiente
        ArrayList<Alumno> alumnos = new ArrayList<>();
        String query = "SELECT * FROM Alumno";  // 1
        try {
            PreparedStatement ps = con.prepareStatement(query); //2
            ResultSet rs = ps.executeQuery();  //3
            while (rs.next()) {     //4
                a = new Alumno();
                a.setId(rs.getInt("idAlumno"));
                a.setDni(rs.getInt("dni"));
                a.setNombre(rs.getString("nombre"));
                a.setFecNac(rs.getDate("fecNac").toLocalDate());
                a.setActivo(rs.getBoolean("activo"));
                alumnos.add(a);
            }
            ps.close();   // 5

        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alumnos;
    } // SELECT *

    public void actualizarAlumno(Alumno a) {
        String query = "UPDATE `alumno` SET `dni` = ?, `nombre` = ?, `fecNac` = ?, `activo` = ? WHERE `alumno`.`idAlumno` = ? ";  //1

        try {
            PreparedStatement ps = con.prepareStatement(query); //2
            ps.setInt(1, a.getDni());
            ps.setString(2, a.getNombre());
            ps.setDate(3, Date.valueOf(a.getFecNac()));
            ps.setBoolean(4, a.isActivo());
            ps.setInt(5, a.getId());
            ps.executeUpdate();     // 3

            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }  // UPDATE SET

    public void borrarAlumno(int id) {

        String query = "DELETE FROM Alumno WHERE idAlumno=?";  //1

        try {
            PreparedStatement ps = con.prepareStatement(query); //2
            ps.setInt(1, id);
            ps.executeUpdate();     // 3

            ps.close();  // 4

            System.out.println("Se elimino el alumno con id " + id + " correctamente");

        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void bajaLogicaAlumno(int id) {
        String sql = "UPDATE alumno SET activo = 0 WHERE idAlumno = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Baja lógica realizada correctamente al alumno ID: " + id);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void altaLogicaAlumno(int id) {
    String sql = "UPDATE alumno SET activo = 1 WHERE idAlumno = ?";

    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        int filas = ps.executeUpdate();

        if (filas > 0) {
            System.out.println("Alta lógica realizada correctamente al alumno ID: " + id);
        }
        ps.close();
    } catch (SQLException ex) {
        Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    
    
}
