package vistas;

import entidades.Alumno;
import persistencia.AlumnoData;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import persistencia.MiConexion;

public class AD {
    private AlumnoData alumnoData;
    private MiConexion conexion;

    public static void main(String[] args) {
        AlumnoData alumnoData= new AD().conectar();
        
        Alumno alumno1 = new Alumno(28180533, "Mateo Piana", LocalDate.of(2007, Month.FEBRUARY, 9), true);
        Alumno alumno2 = new Alumno(28180533, "Donato Santagata", LocalDate.of(2004, Month.MARCH, 11), true);
        Alumno alumno3 = new Alumno(28180533, "Genaro Farias", LocalDate.of(2007, Month.SEPTEMBER, 9), true);
        Alumno alumno4 = new Alumno(28180533, "Veronica Gonzalez", LocalDate.of(1990, Month.JULY, 1), true);
        
        alumnoData.guardarAlumno(alumno1);
        alumnoData.guardarAlumno(alumno2);
        alumnoData.guardarAlumno(alumno3);
        alumnoData.guardarAlumno(alumno4);
        
        List<Alumno> alumnos = alumnoData.listarAlumnos();
        
        for(Alumno a : alumnos){
            System.out.println(a.toString());
        }
        
    }

    AlumnoData conectar() {
        //conexionS = conexionS.buscarConexion();

        //conexion = new miConexion("jdbc:mariadb://localhost/universidad", "root", ""); 
        
        
        // Profe: Cambia AQUI 3008 a 3006 !!!!
        conexion = new MiConexion("jdbc:mysql://localhost:3308/c1g3universidad", "root", "");  
               
        alumnoData = new AlumnoData(conexion);   // alumno
        return alumnoData;
    }
}

