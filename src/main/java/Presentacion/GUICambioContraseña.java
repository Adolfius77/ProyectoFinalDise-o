/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import Control.ControlNavegacion;
import DTOS.LibroDTO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author emiim
 */
public class GUICambioContraseña extends javax.swing.JFrame {

    private List<LibroDTO> carrito = new ArrayList<>();

    /**
     * Creates new form GUICambioContraseña
     */
    public GUICambioContraseña() {
        initComponents();
        configurarNavegacion();
        setLocationRelativeTo(null);
    }
    
    private void configurarNavegacion() {
        final ControlNavegacion navegador = ControlNavegacion.getInstase();

        if (BtnInicio != null) {
            BtnInicio.addActionListener(evt -> navegador.navegarInicio(this));
        }
        
        if (BtnPerfil1 != null) {
            BtnPerfil1.addActionListener(evt -> navegador.navegarPerfil(this));
        }
        if (CMBOpciones != null) {
            CMBOpciones.addActionListener(evt -> manejarAccionOpciones());
        }
        

        if (BTNCambioContraseña != null) {
            BTNCambioContraseña.addActionListener(evt -> navegador.navegarPerfil(this));
        }
    }

    private void manejarAccionOpciones() {
        String seleccion = (String) CMBOpciones.getSelectedItem();
        if (seleccion == null || "Opciones".equals(seleccion) || CMBOpciones.getSelectedIndex() == 0) {
            return;
        }

        final ControlNavegacion navegador = ControlNavegacion.getInstase();
        switch (seleccion) {
            case "Cambiar Contraseña":
                navegador.navegarCambioPasssword(this);
                break;
            case "Cerrar Sesion":
                navegador.cerrarSesion(this);
                break;
            // ... otros casos ...
            default:
                JOptionPane.showMessageDialog(this, "'" + seleccion + "' no implementado.");
                break;
        }
        CMBOpciones.setSelectedIndex(0);
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
        BtnInicio = new javax.swing.JButton();
        BtnCarrito = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        CMBOpciones = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        BtnPerfil1 = new javax.swing.JButton();
        btnCategorias = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        TxtFldNuevaContraseña = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TxtFldContraseñaActual = new javax.swing.JTextField();
        TxtFldRepetirContraseña = new javax.swing.JTextField();
        BTNCambioContraseña = new javax.swing.JButton();
        TxtFldNewContra = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(101, 85, 143));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        BtnInicio.setBackground(new java.awt.Color(101, 85, 143));
        BtnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/inicio.png"))); // NOI18N
        BtnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInicioActionPerformed(evt);
            }
        });

        BtnCarrito.setBackground(new java.awt.Color(101, 85, 143));
        BtnCarrito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/carrito.png"))); // NOI18N
        BtnCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCarritoActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOG.png"))); // NOI18N

        CMBOpciones.setFont(new java.awt.Font("Segoe UI Black", 0, 20)); // NOI18N
        CMBOpciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Opciones", "Registrar entrada", "Ver Historial", "Cambiar Contraseña", "Cerrar Sesion" }));
        CMBOpciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        CMBOpciones.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CMBOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMBOpcionesActionPerformed(evt);
            }
        });

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/list.png"))); // NOI18N

        BtnPerfil1.setBackground(new java.awt.Color(101, 85, 143));
        BtnPerfil1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario.png"))); // NOI18N
        BtnPerfil1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerfil1ActionPerformed(evt);
            }
        });

        btnCategorias.setFont(new java.awt.Font("Segoe UI Black", 0, 20)); // NOI18N
        btnCategorias.setText("CATEGORIAS");
        btnCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(BtnInicio)
                .addGap(66, 66, 66)
                .addComponent(btnCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 308, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(BtnPerfil1)
                .addGap(47, 47, 47)
                .addComponent(BtnCarrito)
                .addGap(28, 28, 28)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CMBOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnCarrito, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CMBOpciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnPerfil1, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnInicio))))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBackground(new java.awt.Color(217, 202, 218));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(217, 202, 218), 5, true));

        TxtFldNuevaContraseña.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        TxtFldNuevaContraseña.setText("Nueva contraseña: ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Contraseña actual: ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Repetir contraseña:");

        TxtFldContraseñaActual.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        TxtFldContraseñaActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtFldContraseñaActualActionPerformed(evt);
            }
        });

        TxtFldRepetirContraseña.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        TxtFldRepetirContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtFldRepetirContraseñaActionPerformed(evt);
            }
        });

        BTNCambioContraseña.setBackground(new java.awt.Color(101, 85, 143));
        BTNCambioContraseña.setFont(new java.awt.Font("Segoe UI Black", 0, 20)); // NOI18N
        BTNCambioContraseña.setForeground(new java.awt.Color(255, 255, 255));
        BTNCambioContraseña.setText("Cambiar contraseña");
        BTNCambioContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCambioContraseñaActionPerformed(evt);
            }
        });

        TxtFldNewContra.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        TxtFldNewContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtFldNewContraActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 36)); // NOI18N
        jLabel1.setText("Cambio de contraseña:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(681, 681, 681)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(766, 766, 766)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(760, 760, 760)
                        .addComponent(TxtFldNuevaContraseña))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(760, 760, 760)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(734, 734, 734)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtFldContraseñaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(TxtFldRepetirContraseña, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                                .addComponent(TxtFldNewContra, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BTNCambioContraseña)
                .addGap(631, 631, 631))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtFldContraseñaActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TxtFldNuevaContraseña)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtFldNewContra, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtFldRepetirContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(BTNCambioContraseña)
                .addContainerGap(266, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInicioActionPerformed

    }//GEN-LAST:event_BtnInicioActionPerformed

    private void BtnCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCarritoActionPerformed

    }//GEN-LAST:event_BtnCarritoActionPerformed

    private void CMBOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBOpcionesActionPerformed

    }//GEN-LAST:event_CMBOpcionesActionPerformed

    private void TxtFldContraseñaActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtFldContraseñaActualActionPerformed
        String actual = TxtFldContraseñaActual.getText().trim();

        if (actual.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "debes llenar este campo obligatoriamente", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_TxtFldContraseñaActualActionPerformed

    private void TxtFldRepetirContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtFldRepetirContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtFldRepetirContraseñaActionPerformed

    private void BtnPerfil1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerfil1ActionPerformed
        GUIPerfil perfil = new GUIPerfil();
        perfil.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnPerfil1ActionPerformed

    private void BTNCambioContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCambioContraseñaActionPerformed
        String actual = TxtFldContraseñaActual.getText().trim();
        String repetir = TxtFldRepetirContraseña.getText().trim();
        String nueva = TxtFldNewContra.getText().trim();
        if (actual.isEmpty() || repetir.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Su contraseña ha sido cambiada con éxito", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_BTNCambioContraseñaActionPerformed

    private void TxtFldNewContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtFldNewContraActionPerformed
        String nueva = TxtFldNewContra.getText().trim();

        if (nueva.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "debes llenar este campo obligatoriamente", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_TxtFldNewContraActionPerformed

    private void btnCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriasActionPerformed

    }//GEN-LAST:event_btnCategoriasActionPerformed

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
            java.util.logging.Logger.getLogger(GUICambioContraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUICambioContraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUICambioContraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUICambioContraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUICambioContraseña().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNCambioContraseña;
    private javax.swing.JButton BtnCarrito;
    private javax.swing.JButton BtnInicio;
    private javax.swing.JButton BtnPerfil1;
    private javax.swing.JComboBox<String> CMBOpciones;
    private javax.swing.JTextField TxtFldContraseñaActual;
    private javax.swing.JTextField TxtFldNewContra;
    private javax.swing.JLabel TxtFldNuevaContraseña;
    private javax.swing.JTextField TxtFldRepetirContraseña;
    private javax.swing.JButton btnCategorias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
