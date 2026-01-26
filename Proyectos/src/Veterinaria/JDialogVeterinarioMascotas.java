/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Veterinaria;
import Veterinaria.Conexion;
import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
/**
 *
 * @author Administrador
 */
public class JDialogVeterinarioMascotas extends javax.swing.JDialog {
    private DefaultTableModel modelo;
    /**
     * Creates new form JDialogVeterinarioMascotas
     */
    public JDialogVeterinarioMascotas(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ajustarImagenLogo();
        ajustarImagenPdf();
        
        modelo = new DefaultTableModel(
            null,
            new String[]{"ID", "Nombre", "Especie", "Edad", "Sexo", "Peso (Kg)", "Vacunas", "Historial", "Info del Dueño"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 5) return Double.class;
                return String.class;
            }
        };
        DefaultTableCellRenderer allignDerecha = new DefaultTableCellRenderer();
        allignDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        
        DefaultTableCellRenderer allignIzquierda = new DefaultTableCellRenderer();
        allignIzquierda.setHorizontalAlignment(SwingConstants.LEFT);

        jTableMascotas.setModel(modelo);
        jTableMascotas.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTableMascotas.getColumnModel().getColumn(0).setCellRenderer(allignIzquierda);
        jTableMascotas.getColumnModel().getColumn(1).setPreferredWidth(60);
        jTableMascotas.getColumnModel().getColumn(1).setCellRenderer(allignIzquierda);
        jTableMascotas.getColumnModel().getColumn(2).setPreferredWidth(165);
        jTableMascotas.getColumnModel().getColumn(2).setCellRenderer(allignIzquierda);
        jTableMascotas.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTableMascotas.getColumnModel().getColumn(3).setCellRenderer(allignIzquierda);
        jTableMascotas.getColumnModel().getColumn(4).setPreferredWidth(40);
        jTableMascotas.getColumnModel().getColumn(4).setCellRenderer(allignIzquierda);
        jTableMascotas.getColumnModel().getColumn(5).setPreferredWidth(50);
        jTableMascotas.getColumnModel().getColumn(5).setCellRenderer(allignDerecha);
        jTableMascotas.getColumnModel().getColumn(6).setPreferredWidth(50);
        jTableMascotas.getColumnModel().getColumn(6).setCellRenderer(allignIzquierda);
        jTableMascotas.getColumnModel().getColumn(7).setPreferredWidth(90);
        jTableMascotas.getColumnModel().getColumn(7).setCellRenderer(allignIzquierda);
        jTableMascotas.getColumnModel().getColumn(8).setPreferredWidth(135);
        jTableMascotas.getColumnModel().getColumn(8).setCellRenderer(allignIzquierda);        

        try (Connection con = Conexion.getConexion()) {
            String sql = "SELECT id, nombre, especie, edad, sexo, peso, vacunas, historial, id_dueno FROM mascota";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String especie = rs.getString("especie");
                String edad = rs.getString("edad");
                String sexo = rs.getString("sexo");
                double peso = rs.getDouble("peso");
                boolean vacunas = rs.getBoolean("vacunas");
                String vacunasT = vacunas ? "Sí" : "No";
                String historial = rs.getString("historial");
                String infoDueno = "No tiene dueño";
                
                String sql2 = "SELECT nombre, apellidos FROM usuario WHERE id =?";
                PreparedStatement ps2 = con.prepareStatement(sql2);
                ps2.setString(1, rs.getString("id_dueno"));
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    infoDueno = rs2.getString("nombre") + " " + rs2.getString("apellidos");
                }
                modelo.addRow(new Object[]{
                    id, nombre, especie, edad, sexo, peso, vacunasT, historial, infoDueno
                });
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Error al cargar las mascotas: " + ex.getMessage(),
                "Error BD",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonAltaMascota = new javax.swing.JButton();
        jButtonEditarMascota = new javax.swing.JButton();
        jButtonBajaMascota = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMascotas = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(847, 320));
        setSize(new java.awt.Dimension(847, 275));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/logoVeterinaria.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 10, 0));
        jPanel2.add(jLabel1);

        jButtonAltaMascota.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButtonAltaMascota.setForeground(new java.awt.Color(105, 211, 183));
        jButtonAltaMascota.setText("Dar de alta");
        jButtonAltaMascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAltaMascotaActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonAltaMascota);

        jButtonEditarMascota.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButtonEditarMascota.setForeground(new java.awt.Color(52, 164, 175));
        jButtonEditarMascota.setText("Editar mascota");
        jButtonEditarMascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarMascotaActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonEditarMascota);

        jButtonBajaMascota.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButtonBajaMascota.setForeground(new java.awt.Color(255, 153, 0));
        jButtonBajaMascota.setText("Dar de baja");
        jButtonBajaMascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBajaMascotaActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonBajaMascota);
        jPanel2.add(jLabel2);

        jTableMascotas.setForeground(new java.awt.Color(52, 164, 175));
        jTableMascotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableMascotas);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/logopdf.png"))); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(86, 83));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEditarMascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarMascotaActionPerformed
       int[] filas = jTableMascotas.getSelectedRows();
        if (filas.length == 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona una mascota.");
            return;
        }
        
        if (filas.length > 1){
            javax.swing.JOptionPane.showMessageDialog(this, "No puedes editar más de una mascota al mismo tiempo.");
            return;
        }

        int modelRow = jTableMascotas.convertRowIndexToModel(filas[0]);
        int id = (Integer) modelo.getValueAt(modelRow, 0);

        JDialogEditaMascota dlg = new JDialogEditaMascota(this, true, id);
        dlg.setVisible(true);
        cargarTablaMascotas();
    }//GEN-LAST:event_jButtonEditarMascotaActionPerformed

    private void jButtonAltaMascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAltaMascotaActionPerformed
        JDialogAltaMascota am = new JDialogAltaMascota(this, true);
        am.setVisible(true);
    }//GEN-LAST:event_jButtonAltaMascotaActionPerformed

    private void jButtonBajaMascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBajaMascotaActionPerformed
        int[] filas = jTableMascotas.getSelectedRows();
        if (filas.length == 0) {
            JOptionPane.showMessageDialog(this, "Selecciona al menos una mascota.");
            return;
        }
        
        String mensaje, mensaje2;
        if (filas.length == 1) {
            mensaje = "¿Seguro que quieres dar de baja esta mascota?";
            mensaje2 = "Mascota dada de alta correctamente";
        } else {
            mensaje = "¿Seguro que quieres dar de baja a estas " + filas.length + " mascotas?";
            mensaje2 = "Mascotas dadas de alta correctamente";
        }

        int confirm = JOptionPane.showConfirmDialog(
            this,
            mensaje,
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try (Connection con = Conexion.getConexion()) {
            String sql = "DELETE FROM mascota WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            for (int fila : filas) {
                int modelRow = jTableMascotas.convertRowIndexToModel(fila);
                int id = (Integer) modelo.getValueAt(modelRow, 0);
                ps.setInt(1, id);
                ps.executeUpdate();
            }

            JOptionPane.showMessageDialog(this, mensaje2);
            cargarTablaMascotas();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "Error al eliminar mascota(s): " + ex.getMessage(),
                "Error BD",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_jButtonBajaMascotaActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres descargar este pdf?", "Confirmación", JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                //Ruta del informe
                String fileJasper = "informes/veterinariaMascotas.jasper";

                //Parametros: En usuarios se necesita ROL(Cliente/Veterinario/Administrador), en productos CANTIDAD(Precio minimo por el que buscar, int), en consultas FECHA (Una fecha)
                Map<String, Object> parameters = new HashMap<>();

                //Conectarse
                JasperPrint print = JasperFillManager.fillReport(fileJasper, parameters, Conexion.getConexion());


                //Nombre del informe y exportar a pdf
                String outputFile = "informes/informeMascotas.pdf";
                JasperExportManager.exportReportToPdfFile(print, outputFile);

                //Mensaje si sale bien, y si se puede abrir el informe automáticamente.
                JOptionPane.showMessageDialog(this,"Informe generado correctamente:\n \"informeMascotas.pdf\" se abrirá automáticamente", "PDF generado", JOptionPane.INFORMATION_MESSAGE);
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(new File(outputFile));
                }
            } catch (Exception ex) { //Por si hay algún error
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al generar el informe: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jLabel3MouseClicked
    private void ajustarImagenLogo() {
    // Cargar la imagen original desde los recursos
        javax.swing.ImageIcon originalIcon = new javax.swing.ImageIcon(
            getClass().getResource("/recursos/logoVeterinaria.png")
        );

        // Escalar la imagen al tamaño del JLabel
        java.awt.Image imagenEscalada = originalIcon.getImage().getScaledInstance(
            logo.getWidth(),
            logo.getHeight(),
            java.awt.Image.SCALE_SMOOTH
        );

        // Crear un nuevo icono con la imagen escalada
        javax.swing.ImageIcon iconoEscalado = new javax.swing.ImageIcon(imagenEscalada);

        // Asignarlo al JLabel
        logo.setIcon(iconoEscalado);
    }
    
    private void ajustarImagenPdf(){
        javax.swing.ImageIcon originalIcon = new javax.swing.ImageIcon(getClass().getResource("/recursos/logopdf.png"));
        
        java.awt.Image imagenEscalada = originalIcon.getImage().getScaledInstance(jLabel3.getWidth(), jLabel3.getHeight(), java.awt.Image.SCALE_SMOOTH);
        
        javax.swing.ImageIcon iconoEscalado = new javax.swing.ImageIcon(imagenEscalada);
        
        jLabel3.setIcon(iconoEscalado);
    }
    
    public void cargarTablaMascotas() {
        modelo.setRowCount(0);

        try (Connection con = Conexion.getConexion()) {
            String sql = "SELECT id, nombre, especie, edad, sexo, peso, vacunas, historial, id_dueno FROM mascota";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String especie = rs.getString("especie");
                String edad = rs.getString("edad");
                String sexo = rs.getString("sexo");
                double peso = rs.getDouble("peso");
                boolean vacunas = rs.getBoolean("vacunas");
                String vacunasT = vacunas ? "Sí" : "No";
                String historial = rs.getString("historial");
                String infoDueno = "DNI: " + rs.getString("id_dueno");
                
                String sql2 = "SELECT nombre, apellidos FROM usuario WHERE id =?";
                PreparedStatement ps2 = con.prepareStatement(sql2);
                ps2.setString(1, rs.getString("id_dueno"));
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    infoDueno = infoDueno + ". Nombre: " + rs2.getString("nombre") + " " + rs2.getString("apellidos");
                }

                if (infoDueno == null || infoDueno.isEmpty()) {
                    infoDueno = "La mascota no tiene dueño";
                } 
                modelo.addRow(new Object[]{
                    id, nombre, especie, edad, sexo, peso, vacunasT, historial, infoDueno
                });
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Error al cargar las mascotas: " + ex.getMessage(),
                "Error BD",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDialogVeterinarioMascotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogVeterinarioMascotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogVeterinarioMascotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogVeterinarioMascotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogVeterinarioMascotas dialog = new JDialogVeterinarioMascotas(new javax.swing.JDialog(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAltaMascota;
    private javax.swing.JButton jButtonBajaMascota;
    private javax.swing.JButton jButtonEditarMascota;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMascotas;
    private javax.swing.JLabel logo;
    // End of variables declaration//GEN-END:variables
}
