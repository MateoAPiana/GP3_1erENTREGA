package vistas;

import entidades.Alumno;
import persistencia.AlumnoData;
import java.time.LocalDate;
import java.util.List;
import persistencia.MiConexion;

public class AD {
    private AlumnoData alumnoData;
    private MiConexion conexion;

    public static void main(String[] args) {

        LocalDate fecha = LocalDate.now();
        Alumno estudioso = new Alumno(28180533, "El ko Ala", LocalDate.now(), false); // entidad
        new AD().conectar(estudioso);
        System.out.println("Alumno " + estudioso.getNombre() + " guardado con exito");
    }

    void conectar(Alumno estudioso) {
        //conexionS = conexionS.buscarConexion();

        //conexion = new miConexion("jdbc:mariadb://localhost/universidad", "root", ""); 
        
        
        // Profe: Cambia AQUI 3008 a 3006 !!!!
        conexion = new MiConexion("jdbc:mysql://localhost:3308/c1g3universidad", "root", "");  
               
        alumnoData = new AlumnoData(conexion);   // alumno
        
        List<Alumno> alumnos = alumnoData.listarAlumnos();
        
        for(Alumno a : alumnos){
            System.out.println(a.toString());
        }
        
//        alumnoData.guardarAlumno(estudioso);     // persistencia
//        Alumno alu = alumnoData.buscarAlumno(estudioso.getId());
//        System.out.println(alu.toString());
    }
}

