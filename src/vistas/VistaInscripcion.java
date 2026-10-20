/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas;

import entidades.Alumno;
import entidades.Cursada;
import entidades.Materia;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.AlumnoData;
import persistencia.CursadaData;
import persistencia.MateriaData;
import persistencia.MiConexion;

/**
 *
 * @author verit
 */
public class VistaInscripcion extends javax.swing.JInternalFrame {

    private DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; 
        }
    };

    public VistaInscripcion() {

        initComponents();
        armarCabeceraTabla();
        cargarAlumnos();
        
        jRadioButton1.setSelected(true);
        
        cargaDatosNoInscriptas();

    }

    private void cargarAlumnos() {
        MiConexion conexion = new MiConexion("jdbc:mysql://localhost:3308/c1g3universidad", "root", "");
        AlumnoData aluData = new AlumnoData(conexion);
        List<Alumno> listaAlumnos = aluData.listarAlumnos();

        for (Alumno a : listaAlumnos) {
            cbAlumnos.addItem(a);
        }
    }

    private void armarCabeceraTabla() {
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Estado");
        tMaterias.setModel(modelo);
    }

    private void borrarFilasTabla() {
        int filas = modelo.getRowCount() - 1;
        for (int i = filas; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }

    private void cargaDatosInscriptas() {
        borrarFilasTabla();
        Alumno seleccionado = (Alumno) cbAlumnos.getSelectedItem();
        if (seleccionado == null) {
            return;
        }

        MiConexion conexion = new MiConexion("jdbc:mysql://localhost:3308/c1g3universidad", "root", "");
        CursadaData cursadaData = new CursadaData(conexion);
        List<Materia> lista = cursadaData.obtenerMateriasCursadas(seleccionado.getId());

        System.out.println("Materias inscriptas encontradas: " + lista.size()); // <-- Mirá la consola de NetBeans

        for (Materia m : lista) {
            modelo.addRow(new Object[]{m.getId(), m.getNombre(), m.getEstado()});
        }
    }

    private void cargaDatosNoInscriptas() {
        borrarFilasTabla();
        Alumno seleccionado = (Alumno) cbAlumnos.getSelectedItem();
        if (seleccionado == null) {
            return;
        }

        MiConexion conexion = new MiConexion("jdbc:mysql://localhost:3308/c1g3universidad", "root", "");
        CursadaData cursadaData = new CursadaData(conexion);
        List<Materia> lista = cursadaData.obtenerMateriasNoCursadas(seleccionado.getId());
        
                System.out.println("Materias no inscriptas encontradas: " + lista.size()); // <-- Mirá la consola de NetBeans


        for (Materia m : lista) {
            modelo.addRow(new Object[]{m.getId(), m.getNombre(), m.getEstado()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbAlumnos = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        rbInscriptas = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tMaterias = new javax.swing.JTable();
        btnInscribir = new javax.swing.JButton();
        btnAnularInscripcion = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Formulario de Inscripcion");

        jLabel2.setText("Seleccione un Alumno:");

        cbAlumnos.addActionListener(this::cbAlumnosActionPerformed);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Listado de Materias:");

        buttonGroup1.add(rbInscriptas);
        rbInscriptas.setText("Materias Inscriptas");
        rbInscriptas.addActionListener(this::rbInscriptasActionPerformed);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Materias no Inscriptas");
        jRadioButton1.addActionListener(this::jRadioButton1ActionPerformed);

        tMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Año"
            }
        ));
        jScrollPane1.setViewportView(tMaterias);

        btnInscribir.setText("Inscribir");
        btnInscribir.addActionListener(this::btnInscribirActionPerformed);

        btnAnularInscripcion.setText("Anular Inscripcion");
        btnAnularInscripcion.addActionListener(this::btnAnularInscripcionActionPerformed);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(this::btnSalirActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbInscriptas)
                                .addGap(71, 71, 71)
                                .addComponent(jRadioButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(cbAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(91, 91, 91))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnInscribir)
                        .addGap(54, 54, 54)
                        .addComponent(btnAnularInscripcion)
                        .addGap(44, 44, 44)
                        .addComponent(btnSalir))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAnularInscripcion, btnInscribir, btnSalir});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbInscriptas)
                    .addComponent(jRadioButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInscribir)
                    .addComponent(btnAnularInscripcion)
                    .addComponent(btnSalir))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAnularInscripcion, btnInscribir, btnSalir});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAlumnosActionPerformed
        if (rbInscriptas.isSelected()) {
            cargaDatosInscriptas();
        } else if (jRadioButton1.isSelected()) {
            cargaDatosNoInscriptas();
        }    }//GEN-LAST:event_cbAlumnosActionPerformed

    private void btnInscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInscribirActionPerformed
        int filaSeleccionada = tMaterias.getSelectedRow();
        
        if (filaSeleccionada != -1) {
            if (jRadioButton1.isSelected()) {
                Alumno seleccionado = (Alumno) cbAlumnos.getSelectedItem();

                int idMateria = (Integer) tMaterias.getValueAt(filaSeleccionada, 0);
                String nombreMateria = (String) tMaterias.getValueAt(filaSeleccionada, 1);
                int estadoMateria = (Integer) tMaterias.getValueAt(filaSeleccionada, 2);

                Materia m = new Materia(idMateria, nombreMateria, estadoMateria);
                Cursada c = new Cursada(seleccionado, m, 0, 0, 1);

                MiConexion conexion = new MiConexion("jdbc:mysql://localhost:3308/c1g3universidad", "root", "");
                CursadaData cursadaData = new CursadaData(conexion);
                cursadaData.guardarCursada(c);

                cargaDatosNoInscriptas();
            } else {
                JOptionPane.showMessageDialog(this, "Solo se pueden realizar inscripciones desde la vista de Materias no Inscriptas.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una materia de la tabla.");
        }
    }//GEN-LAST:event_btnInscribirActionPerformed

    private void rbInscriptasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbInscriptasActionPerformed
        jRadioButton1.setSelected(false);
        cargaDatosInscriptas();

    }//GEN-LAST:event_rbInscriptasActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        rbInscriptas.setSelected(false);
        cargaDatosNoInscriptas();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void btnAnularInscripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularInscripcionActionPerformed
       int filaSeleccionada = tMaterias.getSelectedRow();
        
        if (filaSeleccionada != -1) {
            if (rbInscriptas.isSelected()) {
                Alumno seleccionado = (Alumno) cbAlumnos.getSelectedItem();
                int idMateria = (Integer) tMaterias.getValueAt(filaSeleccionada, 0);

                MiConexion conexion = new MiConexion("jdbc:mysql://localhost:3308/c1g3universidad", "root", "");
                CursadaData cursadaData = new CursadaData(conexion);
                cursadaData.borrarCursada(seleccionado.getId(), idMateria);

                cargaDatosInscriptas();
            } else {
                JOptionPane.showMessageDialog(this, "Solo se pueden anular inscripciones desde la vista de Materias Inscriptas.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una materia de la tabla.");
        }
    }//GEN-LAST:event_btnAnularInscripcionActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnularInscripcion;
    private javax.swing.JButton btnInscribir;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JComboBox<Alumno> cbAlumnos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbInscriptas;
    private javax.swing.JTable tMaterias;
    // End of variables declaration//GEN-END:variables
}
