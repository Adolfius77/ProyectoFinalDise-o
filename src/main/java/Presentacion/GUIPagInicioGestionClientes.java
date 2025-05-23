/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import Control.ControlNavegacion;
import DTOS.ConsultarClienteDTO;
import Negocio.GestionUsuarios;
import Persistencia.BuscarClienteId;
import Persistencia.BuscarClienteNombre;
import Persistencia.IBuscarCliente;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author riosr
 */
public class GUIPagInicioGestionClientes extends javax.swing.JFrame {

    private IBuscarCliente buscadorClientesNombre;
    private IBuscarCliente buscadorClientesId;

    public GUIPagInicioGestionClientes() {
        initComponents();
        this.buscadorClientesNombre = new BuscarClienteNombre();
        this.buscadorClientesId = new BuscarClienteId();
        configurarNavegacion();
        cargarClientesPanel(null);
        setLocationRelativeTo(null);
    }

    private void configurarNavegacion() {
        final ControlNavegacion navegador = ControlNavegacion.getInstase();

        if (BtnInicio != null) {
            BtnInicio.addActionListener(evt -> navegador.navegarAdminGui(this));
        }

        if (CMBOpciones != null) {
            CMBOpciones.addActionListener(evt -> manejarAccionOpciones());
        }
        if (btnAgregarCliente != null) {
            btnAgregarCliente.addActionListener(evt -> navegador.navegarAgregarCliente(this));
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
            case "Gestion de Libros":
                navegador.navegarGestionLibro(this);
                break;
            case "Registrar Entrada":
                navegador.navegarRegistroEntrada(this);
                break;
            case "Ver Historial entrada":
                navegador.navegarHistorialEntradas(this);
                break;
            case "Gestion de Clientes":
                navegador.navegarInicioGestionClientes(this);
                break;
        }
        CMBOpciones.setSelectedIndex(0); // Resetear
    }

    public void cargarClientesPanel(List<ConsultarClienteDTO> clientesAMostrar) {
        if (panelCliente == null) {
            System.err.println("El panel cliente es nulo, verifica el nombre del panel");
            return;
        }

        panelCliente.removeAll();
        panelCliente.setLayout(new BoxLayout(panelCliente, BoxLayout.Y_AXIS));

        List<ConsultarClienteDTO> clientesParaMostrar;
        if (clientesAMostrar == null) {
            clientesParaMostrar = GestionUsuarios.getListaUsuario(); //
        } else {
            clientesParaMostrar = clientesAMostrar;
        }

        if (clientesParaMostrar == null || clientesParaMostrar.isEmpty()) {
            panelCliente.add(new JLabel("No hay clientes para mostrar."));
        } else {
            for (ConsultarClienteDTO consultarClienteDTO : clientesParaMostrar) {
                PanelGestionClientes itemPanel = new PanelGestionClientes(consultarClienteDTO, this);

                itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, itemPanel.getPreferredSize().height + 10));
                panelCliente.add(itemPanel);
                panelCliente.add(Box.createRigidArea(new Dimension(0, 8)));
            }
        }

        panelCliente.revalidate();
        panelCliente.repaint();

        if (jScrollPane1 != null) {
            jScrollPane1.getViewport().setViewPosition(new Point(0, 0));
            jScrollPane1.revalidate();
            jScrollPane1.repaint();
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

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        BtnInicio = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        CMBOpciones = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        txtFldBuscador = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelCliente = new javax.swing.JPanel();
        btnAgregarCliente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(217, 202, 218));

        jPanel1.setBackground(new java.awt.Color(101, 85, 143));

        BtnInicio.setBackground(new java.awt.Color(101, 85, 143));
        BtnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/inicio.png"))); // NOI18N
        BtnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInicioActionPerformed(evt);
            }
        });

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/list.png"))); // NOI18N

        CMBOpciones.setFont(new java.awt.Font("Segoe UI Black", 0, 16)); // NOI18N
        CMBOpciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Opciones", "Gestion de Libros", "Registrar Entrada", "Ver Historial entrada", "Gestion de Clientes", "Cerrar Sesion" }));
        CMBOpciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        CMBOpciones.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CMBOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMBOpcionesActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOG.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel6.setText("ADMINISTRADOR");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel6)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel6)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btnBuscar.setBackground(new java.awt.Color(101, 85, 143));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtFldBuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFldBuscadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(BtnInicio)
                .addGap(18, 18, 18)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addGap(18, 18, 18)
                .addComponent(txtFldBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CMBOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFldBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar)
                            .addComponent(jLabel21)
                            .addComponent(CMBOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnInicio)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(217, 202, 218));

        panelCliente.setBackground(new java.awt.Color(217, 202, 218));
        panelCliente.setLayout(new javax.swing.BoxLayout(panelCliente, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(panelCliente);

        btnAgregarCliente.setBackground(new java.awt.Color(93, 47, 96));
        btnAgregarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        btnAgregarCliente.setToolTipText("");
        btnAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(535, 535, 535))
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInicioActionPerformed

    }//GEN-LAST:event_BtnInicioActionPerformed

    private void CMBOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBOpcionesActionPerformed

    }//GEN-LAST:event_CMBOpcionesActionPerformed

    private void btnAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClienteActionPerformed

    }//GEN-LAST:event_btnAgregarClienteActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
      String criterio = txtFldBuscador.getText();
        if (criterio == null || criterio.trim().isEmpty()) {
            cargarClientesPanel(null); 
            return;
        }

        List<ConsultarClienteDTO> todosLosClientes = GestionUsuarios.getListaUsuario(); 
        Set<ConsultarClienteDTO> resultadosSet = new HashSet<>(); 

        // Buscar por nombre
        List<ConsultarClienteDTO> resultadosPorNombre = buscadorClientesNombre.buscar(criterio, todosLosClientes); 
        if (resultadosPorNombre != null) {
            resultadosSet.addAll(resultadosPorNombre);
        }

        // Buscar por ID
        List<ConsultarClienteDTO> resultadosPorId = buscadorClientesId.buscar(criterio, todosLosClientes); 
        if (resultadosPorId != null) {
            resultadosSet.addAll(resultadosPorId);
        }
        
        List<ConsultarClienteDTO> resultadosCombinados = new ArrayList<>(resultadosSet);

        if (resultadosCombinados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron clientes con el criterio: " + criterio, "Búsqueda sin resultados", JOptionPane.INFORMATION_MESSAGE);
            cargarClientesPanel(new ArrayList<>());
        } else {
            cargarClientesPanel(resultadosCombinados);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtFldBuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldBuscadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldBuscadorActionPerformed

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
            java.util.logging.Logger.getLogger(GUIPagInicioGestionClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIPagInicioGestionClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIPagInicioGestionClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIPagInicioGestionClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUIPagInicioGestionClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnInicio;
    private javax.swing.JComboBox<String> CMBOpciones;
    private javax.swing.JButton btnAgregarCliente;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelCliente;
    private javax.swing.JTextField txtFldBuscador;
    // End of variables declaration//GEN-END:variables
}
