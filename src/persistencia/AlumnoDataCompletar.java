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

public class AlumnoData{
    private Connection con = null;
    
    public AlumnoData(MiConexion conexion){
        this.con = conexion.buscarConexion();
    }
    
    public void guardarAlumno(Alumno a){    // obj alumno sin id valido
        String sql = "INSERT INTO alumno(dni, nombre, fecNac, activo) VALUES (?,?,?,?)";  //1
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //2
            //ps.setInt(1.........
            ps.setDate(3, Date.valueOf(a.getFecNac()));
            //ps.setBoolean(4..........
            ps.executeUpdate();     // 3
            
            ResultSet rs = ps.getGeneratedKeys();  // recupero y asigno
            if(rs.next())
                a.setId(rs.getInt(1));
            else
                System.out.println("No se pudo tener ID");
            ps.close();
            System.out.println("Guardado!");
        } catch (SQLException ex) {
            System.out.println("No pude insertar");    
        }
        
    }   // INSERT INTO
    
    public Alumno buscarAlumno(int id){
      Alumno a= null;
      String sql = "SELECT * FROM alumno WHERE idAlumno= ?";  //1
      
      PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);    // 2
          //  ps.setInt(1,........
		  ResultSet rs= ps.executeQuery();  //3
            while (rs.next()) {  // 4 armo el objeto
                a=new Alumno();
                a.setId(....);
                a.....(rs.getInt("dni"));
                ......(rs............);
                a.setFecNac(rs.getDate("fecNac").toLocalDate()); // Date.valueOf( )
                a.setActivo(rs.getBoolean("activo"));
            }
            ps.close(); // 5
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a; 
    }  // SELECT 1 ALUMNO
    
    public List<Alumno> listarAlumnos(){
        Alumno a= null;   // ALUMNO recipiente
        //        CREAR ARRAYLIST     
        String query = "SELECT * ................";  // 1
        try {
            PreparedStatement ps = con.prepareStatement(query); //2
            ResultSet rs = ps.executeQuery();  //3
            while(rs.next()){     //4
                // instanciar alumno recipiente
                a.setId(rs.getInt("idAlumno"));
                a.setDni(rs.getInt("dni"));
                a.setNombre(rs.getString("nombre"));
                a.setFecNac(rs.getDate("fechaNac").toLocalDate());
                a.setActivo(rs.getBoolean("activo"));
            //    alumnos.add(a);    // agregar al ARRAYLIST
            }
            ps.close();   // 5
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return alumnos;
    } // SELECT *
    
    public void actualizarAlumno(Alumno a){
        String query = "UPDATE ..........................................";  //1
        
        try {
           // PreparedStatement ps = ........................(query); //2
            //setString(1, a.getNombre());
            //setInt(2, .......);
            ps.setDate(... , Date.valueOf(a.getFecNac()));
            ps.setBoolean(... , a.getActivo());
            //................(5, a.getId());
            ps.executeUpdate();     // 3
                       
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }  // UPDATE SET 
    
    public void borrarAlumno(int id){
            
        String query = "DELETE FROM.........................";  //1
        
        try {
            PreparedStatement ps = con.prepareStatement(query); //2
            //setInt(... , id);
            ps.executeUpdate();     // 3
            
            ps.close();  // 4
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }// UPDATE SET / DELETE
}

