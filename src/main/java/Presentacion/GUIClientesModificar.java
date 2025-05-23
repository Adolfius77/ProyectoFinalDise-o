/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import Control.ControlNavegacion;
import DTOS.ConsultarClienteDTO;
import DTOS.ModificarClienteDTO;
import Negocio.GestionUsuarios;
import javax.swing.JOptionPane;

/**
 *
 * @author riosr
 */
public class GUIClientesModificar extends javax.swing.JFrame {

    private ConsultarClienteDTO clienteOriginal;
    /**
     * Creates new form GUIClientesModificar
     */
    public GUIClientesModificar(ConsultarClienteDTO cliente) {
        this.clienteOriginal = cliente;
        initComponents();
        configurarNavegacion();
        cargarDatosCliente();
        setLocationRelativeTo(null);
        if(lblIdCliente != null && this.clienteOriginal != null){
            lblIdCliente.setText("ID del Cliente: " + this.clienteOriginal.getIdCliente());
        }else if(lblIdCliente != null ){
            lblIdCliente.setText("ID del Cliente: N/A");
        }
    }
    
    public GUIClientesModificar(){
        initComponents();
        configurarNavegacion();
        setLocationRelativeTo(null);
        btnEditarCliente.setEnabled(false);
        if(lblIdCliente != null){
            lblIdCliente.setText("ID del Cliente: N/A");
        }
        
    }
    
    private void configurarNavegacion() {
        final ControlNavegacion navegador = ControlNavegacion.getInstase();

        if (BtnInicio != null) {
             BtnInicio.addActionListener(evt -> navegador.navegarAdminGui(this));
        }
        if (CMBOpciones != null) {
            CMBOpciones.addActionListener(evt -> manejarAccionOpciones());
        }
        if (btnRegresarGestionCliente != null) {
            btnRegresarGestionCliente.addActionListener(evt -> {
                int confirmacion = JOptionPane.showConfirmDialog(
                        this,
                        "¿Está seguro de que desea regresar? Los cambios no guardados se perderán.",
                        "Confirmar Salida",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    navegador.navegarInicioGestionClientes(this);
                }
            });
        }
        // El botón "Editar Cliente" (o "Guardar Cambios") llamará a modificarClienteExistente()
        if (btnEditarCliente != null) { 
            // Asegurarse de que no haya listeners duplicados
            for (java.awt.event.ActionListener al : btnEditarCliente.getActionListeners()) {
                btnEditarCliente.removeActionListener(al);
            }
            btnEditarCliente.addActionListener(evt -> modificarClienteExistente());
        }
    }
    
    private void cargarDatosCliente(){
        if(clienteOriginal != null){
            lblIdCliente.setText("ID del Cliente: " + clienteOriginal.getIdCliente());
            txtFldNombreCliente.setText(clienteOriginal.getNombreCliente());
            txtFldApellido.setText(clienteOriginal.getApellidoCliente());
            txtFldCorreoElectronico.setText(clienteOriginal.getCorreoElectronico());
            txtFldContraseña.setText("");
            cmbBoxEstado.setSelectedItem(clienteOriginal.isActivo() ? "activo" : "no activo");
            btnEditarCliente.setEnabled(true);
        }else{
            JOptionPane.showMessageDialog(this, "No se han cargado los datos del cliente para modificar", "Error", JOptionPane.ERROR_MESSAGE);
            txtFldNombreCliente.setEnabled(false);
            txtFldApellido.setEnabled(false);
            txtFldCorreoElectronico.setEnabled(false);
            txtFldContraseña.setEnabled(false);
            cmbBoxEstado.setEnabled(false);
            btnEditarCliente.setEnabled(false);
        }
    }
    
    private void modificarClienteExistente() {
        if (clienteOriginal == null) {
            JOptionPane.showMessageDialog(this, "No hay cliente cargado para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = txtFldNombreCliente.getText().trim();
        String apellido = txtFldApellido.getText().trim();
        String correo = txtFldCorreoElectronico.getText().trim();
        String nuevaContrasena = txtFldContraseña.getText().trim(); 
        boolean activo = "Activo".equalsIgnoreCase(cmbBoxEstado.getSelectedItem().toString());

        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre, apellido y correo son campos obligatorios.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }
         if (!correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un correo electrónico válido.", "Correo Inválido", JOptionPane.WARNING_MESSAGE);
            txtFldCorreoElectronico.requestFocus();
            return;
        }
        if (!nuevaContrasena.isEmpty() && nuevaContrasena.length() < 3) {
             JOptionPane.showMessageDialog(this, "Si desea cambiar la contraseña, esta debe tener al menos 3 caracteres.", "Nueva Contraseña Corta", JOptionPane.WARNING_MESSAGE);
            txtFldContraseña.requestFocus();
            return;
        }

        ModificarClienteDTO clienteModificado = new ModificarClienteDTO(
                clienteOriginal.getIdCliente(), 
                nombre,
                apellido,
                correo,
                activo,
                nuevaContrasena.isEmpty() ? null : nuevaContrasena 
        );

        if (GestionUsuarios.modificarUsuario(clienteModificado)) {
            JOptionPane.showMessageDialog(this, "Cliente modificado exitosamente.", "Modificación Exitosa", JOptionPane.INFORMATION_MESSAGE);
            ControlNavegacion.getInstase().navegarInicioGestionClientes(this); 
        } else {
             JOptionPane.showMessageDialog(this, "No se pudo modificar el cliente. Verifique los datos (ej. el nuevo correo podría ya estar en uso por otro usuario).", "Error de Modificación", JOptionPane.ERROR_MESSAGE);
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

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        BtnInicio = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        CMBOpciones = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblApellidoCliente = new javax.swing.JLabel();
        txtFldApellido = new javax.swing.JTextField();
        lblCorreoElectronico = new javax.swing.JLabel();
        txtFldCorreoElectronico = new javax.swing.JTextField();
        lblContraseña = new javax.swing.JLabel();
        txtFldContraseña = new javax.swing.JTextField();
        lblEstadoCliente = new javax.swing.JLabel();
        cmbBoxEstado = new javax.swing.JComboBox<>();
        lblNombreCliente1 = new javax.swing.JLabel();
        txtFldNombreCliente = new javax.swing.JTextField();
        btnEditarCliente = new javax.swing.JButton();
        btnRegresarGestionCliente = new javax.swing.JButton();
        lblIdCliente = new javax.swing.JLabel();

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
        jLabel6.setText("MODIFICAR CLIENTES");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel6)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel6)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(BtnInicio)
                .addGap(38, 38, 38)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CMBOpciones, 0, 236, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(BtnInicio)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CMBOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 15, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBackground(new java.awt.Color(217, 202, 218));

        lblApellidoCliente.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblApellidoCliente.setText("Apellido:");

        txtFldApellido.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblCorreoElectronico.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCorreoElectronico.setText("Correo Electronico:");

        txtFldCorreoElectronico.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblContraseña.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblContraseña.setText("Contraseña:");

        txtFldContraseña.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblEstadoCliente.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblEstadoCliente.setText("Estado:");

        cmbBoxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "No Activo" }));
        cmbBoxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBoxEstadoActionPerformed(evt);
            }
        });

        lblNombreCliente1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblNombreCliente1.setText("Nombre:");

        txtFldNombreCliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtFldNombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFldNombreClienteActionPerformed(evt);
            }
        });

        btnEditarCliente.setBackground(new java.awt.Color(101, 85, 143));
        btnEditarCliente.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnEditarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarCliente.setText("Editar");
        btnEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClienteActionPerformed(evt);
            }
        });

        btnRegresarGestionCliente.setBackground(new java.awt.Color(101, 85, 143));
        btnRegresarGestionCliente.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnRegresarGestionCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresarGestionCliente.setText("Regresar");
        btnRegresarGestionCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarGestionClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(btnRegresarGestionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEditarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(351, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblEstadoCliente)
                        .addGap(557, 557, 557))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFldContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFldCorreoElectronico, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFldNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(311, 311, 311))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblContraseña)
                        .addGap(537, 537, 537))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblCorreoElectronico)
                        .addGap(488, 488, 488))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblApellidoCliente)
                        .addGap(539, 539, 539))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblNombreCliente1)
                        .addGap(542, 542, 542))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(lblNombreCliente1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFldNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblApellidoCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCorreoElectronico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFldCorreoElectronico, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblContraseña)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFldContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblEstadoCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegresarGestionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        lblIdCliente.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblIdCliente.setText("Id");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(lblIdCliente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblIdCliente)
                .addContainerGap(607, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(148, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
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

    private void cmbBoxEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBoxEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBoxEstadoActionPerformed

    private void txtFldNombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldNombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldNombreClienteActionPerformed

    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed
        
    }//GEN-LAST:event_btnEditarClienteActionPerformed

    private void btnRegresarGestionClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarGestionClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegresarGestionClienteActionPerformed

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
            java.util.logging.Logger.getLogger(GUIClientesModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIClientesModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIClientesModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIClientesModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIClientesModificar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnInicio;
    private javax.swing.JComboBox<String> CMBOpciones;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnRegresarGestionCliente;
    private javax.swing.JComboBox<String> cmbBoxEstado;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblApellidoCliente;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblCorreoElectronico;
    private javax.swing.JLabel lblEstadoCliente;
    private javax.swing.JLabel lblIdCliente;
    private javax.swing.JLabel lblNombreCliente1;
    private javax.swing.JTextField txtFldApellido;
    private javax.swing.JTextField txtFldContraseña;
    private javax.swing.JTextField txtFldCorreoElectronico;
    private javax.swing.JTextField txtFldNombreCliente;
    // End of variables declaration//GEN-END:variables
}
