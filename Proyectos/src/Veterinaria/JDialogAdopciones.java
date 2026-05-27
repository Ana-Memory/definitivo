/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Veterinaria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


/**
 *
 * @author DAM2Alu2
 */
public class JDialogAdopciones extends javax.swing.JDialog {
    private String usuario;
    private String contrasena;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(JDialogAdopciones.class.getName());
    
    /**
     * Creates new form Adopciones
     */
    public JDialogAdopciones(java.awt.Dialog parent, boolean modal, String usuario, String contrasena) {
        super(parent, modal);
        initComponents();
        ajustarImagenLogo();
        this.usuario = usuario;
        this.contrasena = contrasena;

        try (Connection con = Conexion.getConexion()) {
            jPanelMascotas.setLayout(new BoxLayout(jPanelMascotas, BoxLayout.Y_AXIS));

            String sql = "SELECT nombre, especie, edad, sexo, peso, vacunas FROM mascota WHERE id_dueno IS NULL";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Object[]> lista = new ArrayList<>();
            while (rs.next()) {
                Object[] m = new Object[6];
                m[0] = rs.getString("nombre");
                m[1] = rs.getString("especie");
                m[2] = rs.getString("edad");
                m[3] = rs.getString("sexo");
                m[4] = rs.getDouble("peso");
                m[5] = rs.getBoolean("vacunas");
                lista.add(m);
            }
            
            if (lista.isEmpty()) {
                jPanelMascotas.setLayout(new BorderLayout()); // Para centrar el mensaje
                JLabel mensajeArriba = new JLabel("NO HAY MASCOTAS");
                mensajeArriba.setFont(new Font("Candara", Font.BOLD, 50));
                mensajeArriba.setForeground(new Color(255, 153, 0));
                mensajeArriba.setHorizontalAlignment(JLabel.CENTER);
                mensajeArriba.setVerticalAlignment(JLabel.BOTTOM);
                mensajeArriba.setBorder(new EmptyBorder(30, 0, 0, 0));
                
                JLabel mensajeCentro = new JLabel("<html><div style='text-align:center;'>Le agradecemos su interés por adoptar a una mascota,<br>"
                                                + "pero todos los animales del refugio fueron adoptados<br>y tienen hogares permanentes.</div></html>");
                mensajeCentro.setFont(new Font("Candara", Font.BOLD, 30));
                mensajeCentro.setForeground(new Color(52, 164, 175));
                mensajeCentro.setHorizontalAlignment(JLabel.CENTER);
                mensajeCentro.setVerticalAlignment(JLabel.CENTER);
                
                JLabel mensajeAbajo = new JLabel("Por favor, vuelva otro día.");
                mensajeAbajo.setFont(new Font("Candara", Font.BOLD, 20));
                mensajeAbajo.setForeground(new Color(133, 210, 204));
                mensajeAbajo.setHorizontalAlignment(JLabel.CENTER);
                mensajeAbajo.setVerticalAlignment(JLabel.CENTER);
                
                jPanelMascotas.add(mensajeArriba, BorderLayout.NORTH);
                jPanelMascotas.add(mensajeCentro, BorderLayout.CENTER);
                jPanelMascotas.add(mensajeAbajo, BorderLayout.SOUTH);
                jPanelMascotas.revalidate();
                jPanelMascotas.repaint();
            } else {
                for (Object[] datosMascotaActual : lista) {
                    JPanel jPanelMascota = new JPanel(new BorderLayout());
                    jPanelMascota.setBackground(Color.white);
                    jPanelMascota.setBorder(new EmptyBorder(10, 10, 10, 10));

                    String nombre = datosMascotaActual[0].toString().toLowerCase();
                    ImageIcon icono = null;

                    String rutaClasspath = "/recursos/" + nombre + ".jpg";
                    java.net.URL url = getClass().getResource(rutaClasspath);

                    if (url != null) {
                        icono = new ImageIcon(url);
                    } else {
                        String rutaFuera = System.getProperty("user.dir") + "/recursos/" + nombre + ".jpg";
                        File imagenFile = new File(rutaFuera);

                        if (imagenFile.exists()) {
                            icono = new ImageIcon(imagenFile.getAbsolutePath());
                        } else {
                            java.net.URL urlDefault = getClass().getResource("/recursos/default.jpg");
                            icono = new ImageIcon(urlDefault);
                        }
                    }

                    Image img = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    JLabel lblImagen = new JLabel(new ImageIcon(img));
                    lblImagen.setBorder(new EmptyBorder(0, 0, 0, 10));
                    jPanelMascota.add(lblImagen, BorderLayout.WEST);
                    // Texto
                    JPanel panelTexto = new JPanel();
                    panelTexto.setBackground(Color.white);
                    panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
                    panelTexto.setBorder(new EmptyBorder(10,0,0,0));

                    if (lista.size() == 1) {
                        jPanelMascota.setAlignmentX(JPanel.LEFT_ALIGNMENT);
                        jPanelMascota.setAlignmentY(JPanel.CENTER_ALIGNMENT);
                        jPanelMascota.setMaximumSize(
                            new java.awt.Dimension(Integer.MAX_VALUE, 200)
                        );
                        panelTexto.setBorder(new EmptyBorder(0,0,0,0));
                    }

                    Font fuente = new Font("Candara", Font.ITALIC, 14);
                    Color color = new Color(52,164,175);
                    Font fuenteBoton = new Font("Candara", Font.BOLD, 14);
                    Color colorBoton = new Color(255,153,0);

                    panelTexto.add(new JLabel("Nombre: " + datosMascotaActual[0] + "."));
                    panelTexto.add(new JLabel("Especie: " + datosMascotaActual[1] + "."));
                    panelTexto.add(new JLabel("Edad: " + datosMascotaActual[2] + "."));
                    panelTexto.add(new JLabel("Sexo: " + datosMascotaActual[3] + "."));
                    panelTexto.add(new JLabel("Peso: " + datosMascotaActual[4] + "."));

                    boolean vacunado = (boolean) datosMascotaActual[5];
                    panelTexto.add(new JLabel(vacunado ? "Tiene todas sus vacunas" : "No está vacunado"));

                    // Descripción
                    String descripcion = switch (datosMascotaActual[0].toString()) {
                        case "Dante" -> "Nuestro querido Dante es un perro enérgico y muy amistoso. Perfecto para acompañarte en tus caminatas mañaneras.";
                        case "Pretzel" -> "Pretzel es una gatita que requiere mucha atención, pero es muy cariñosa y mansa.";
                        case "Sheldon" -> "Sheldon es perfecto para los amantes de los reptiles y los que prefieren mascotas tranquilas.";
                        case "Blue" -> "Si quieres una mascota cariñosa, inteligente y sociable, ¡Blue es para ti!";
                        case "Hamtaro" -> "Nuestro pequeño Hamtaro es tierno, tímido y tranquilo.";
                        default -> datosMascotaActual[0] + " ha llegado recientemente al refugio. ¡Dale una oportunidad!";
                    };
                    JLabel des = new JLabel(descripcion);
                    des.setFont(fuente);
                    des.setForeground(color);
                    panelTexto.add(des);

                    // Botón adopción
                    JButton jButtonAdopta = new JButton("ADOPTA YA");
                    jButtonAdopta.setFont(fuenteBoton);
                    jButtonAdopta.setForeground(colorBoton);
                    panelTexto.add(jButtonAdopta);

                    jButtonAdopta.addActionListener(e -> jButtonAdoptaActionPerformed(datosMascotaActual));

                    jPanelMascota.add(panelTexto, BorderLayout.CENTER);
                    jPanelMascotas.add(jPanelMascota);
                }

                jPanelMascotas.revalidate();
                jPanelMascotas.repaint();
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPaneRefugio = new javax.swing.JScrollPane();
        jPanelMascotas = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/logoVeterinaria.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout(2, 1, 0, -13));

        jLabel3.setFont(new java.awt.Font("Candara", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(52, 164, 175));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("VetPet Adopt");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(jLabel3);

        jLabel4.setFont(new java.awt.Font("Courier New", 3, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(105, 211, 183));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Dale un buen hogar a una o más adorables mascotas de nuestro refugio.");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel2.add(jLabel4);

        jScrollPaneRefugio.setBackground(new java.awt.Color(255, 255, 255));

        jPanelMascotas.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelMascotasLayout = new javax.swing.GroupLayout(jPanelMascotas);
        jPanelMascotas.setLayout(jPanelMascotasLayout);
        jPanelMascotasLayout.setHorizontalGroup(
            jPanelMascotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 821, Short.MAX_VALUE)
        );
        jPanelMascotasLayout.setVerticalGroup(
            jPanelMascotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 449, Short.MAX_VALUE)
        );

        jScrollPaneRefugio.setViewportView(jPanelMascotas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPaneRefugio)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneRefugio, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ajustarImagenLogo() {
        javax.swing.ImageIcon originalIcon = new javax.swing.ImageIcon(
            getClass().getResource("/recursos/logoVeterinaria.png")
        );

        java.awt.Image imagenEscalada = originalIcon.getImage().getScaledInstance(
            logo.getWidth(),
            logo.getHeight(),
            java.awt.Image.SCALE_SMOOTH
        );
        javax.swing.ImageIcon iconoEscalado = new javax.swing.ImageIcon(imagenEscalada);
        logo.setIcon(iconoEscalado);
    }

    private void jButtonAdoptaActionPerformed(Object[] mascota) {
        String nombre = (String) mascota[0];
        try (Connection con = Conexion.getConexionUsuario(usuario, contrasena)) {
            // Obtener id del usuario desde la tabla usuario
            String sqlId = "SELECT id FROM usuario WHERE usuario = ?";
            PreparedStatement psId = con.prepareStatement(sqlId);
            psId.setString(1, usuario);
            ResultSet rs = psId.executeQuery();

            String idUsuario = null;
            if (rs.next()) {
                idUsuario = rs.getString("id");
            }

            if (idUsuario != null) {
                String sqlUpdate = "UPDATE mascota SET id_dueno = ? WHERE nombre = ?";
                PreparedStatement psUpdate = con.prepareStatement(sqlUpdate);
                psUpdate.setString(1, idUsuario);
                psUpdate.setString(2, nombre);

                int opcion = JOptionPane.showConfirmDialog(this, "¿Vas a adoptar a " + nombre + "?", "Confirmar adopción", JOptionPane.YES_NO_OPTION);

                if (opcion == JOptionPane.YES_OPTION) {
                    int filas = psUpdate.executeUpdate();
                    if (filas > 0) {
                        JOptionPane.showMessageDialog(this, "¡Muchas gracias! ¡Mandaremos a " + nombre + " a su nuevo hogar tras una evaluación.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error: no se pudo actualizar el registro de la mascota.");
                    }
                    this.dispose();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JDialogAdopciones dialog = new JDialogAdopciones(new javax.swing.JDialog(), true, "", "");
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelMascotas;
    private javax.swing.JScrollPane jScrollPaneRefugio;
    private javax.swing.JLabel logo;
    // End of variables declaration//GEN-END:variables
}
