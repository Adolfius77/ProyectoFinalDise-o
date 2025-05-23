/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import Control.ControlNavegacion;
import DTOS.ConsultarClienteDTO;
import DTOS.LibroDTO;
import DTOS.ReseñaUsuarioDTO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author emiim
 */
public class GUIPerfil extends javax.swing.JFrame {

    /**
     * Creates new form Semen
     */
    private List<LibroDTO> carrito = new ArrayList<>();

    public GUIPerfil() {
        initComponents();
        configurarNavegacionPerfil();
        cargarMisReseñas();
        cargarDatosUsuario();
        setLocationRelativeTo(null);
    }

    private void configurarNavegacionPerfil() {
        final ControlNavegacion navegador = ControlNavegacion.getInstase();
        if (BtnInicio != null) {
            BtnInicio.addActionListener(evt -> navegador.navegarInicio(this));
        }
        if (BtnPerfil != null) {
            BtnPerfil.addActionListener(evt -> navegador.navegarPerfil(this));
        }
        if (BtnCarrito != null) {
            BtnCarrito.addActionListener(evt -> navegador.navegarCarrito(this));
        }
        if (CMBOpciones != null) {
            CMBOpciones.addActionListener(evt -> manejarAccionOpciones());
        }
        if (btnCategorias != null) {
            btnCategorias.addActionListener(evt -> navegador.navegarCategorias(this));
        }
        if (btnCerrarSesion != null) {
            btnCerrarSesion.addActionListener(evt -> navegador.cerrarSesion(this));
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
        CMBOpciones.setSelectedIndex(0); // Resetear
    }

    private void cargarMisReseñas() {
        if (panelReseñas == null) {
            System.err.println("Error: panelContenedorMisResenas no está inicializado en GUIPerfil.");
            panelReseñas = new JPanel();
            panelReseñas.setLayout(new BoxLayout(panelReseñas, BoxLayout.Y_AXIS));
            if (jScrollMisReseñas != null) {
                jScrollMisReseñas.setViewportView(panelReseñas);
            } else {
                jPanel4.add(panelReseñas);
            }
        }

        panelReseñas.removeAll();
        panelReseñas.setLayout(new BoxLayout(panelReseñas, BoxLayout.Y_AXIS));
        panelReseñas.setBackground(new Color(240, 230, 240));

        List<ReseñaUsuarioDTO> misResenas = ControlNavegacion.getInstase().getMisResenasDelUsuario();

        if (misResenas.isEmpty()) {
            JLabel noResenasLabel = new JLabel("Aún no has escrito ninguna reseña.");
            noResenasLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            noResenasLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            noResenasLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
            panelReseñas.add(noResenasLabel);
        } else {
            JLabel tituloSeccion = new JLabel("Mis Reseñas");
            tituloSeccion.setFont(new Font("Segoe UI", Font.BOLD, 20));
            tituloSeccion.setAlignmentX(Component.CENTER_ALIGNMENT);
            tituloSeccion.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
            panelReseñas.add(tituloSeccion);

            for (ReseñaUsuarioDTO resena : misResenas) {
                JPanel panelResenaIndividual = new JPanel();
                panelResenaIndividual.setLayout(new BoxLayout(panelResenaIndividual, BoxLayout.Y_AXIS));
                panelResenaIndividual.setBackground(Color.WHITE);
                panelResenaIndividual.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
                panelResenaIndividual.setAlignmentX(Component.LEFT_ALIGNMENT);

                JLabel lblTituloLibroResena = new JLabel("Libro: " + resena.getTituloLibro());
                lblTituloLibroResena.setFont(new Font("Segoe UI", Font.BOLD, 16));
                panelResenaIndividual.add(lblTituloLibroResena);

                JTextArea txtTextoResena = new JTextArea(resena.getTextoResena());
                txtTextoResena.setWrapStyleWord(true);
                txtTextoResena.setLineWrap(true);
                txtTextoResena.setEditable(false);
                txtTextoResena.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                txtTextoResena.setOpaque(false);
                txtTextoResena.setFocusable(false);
                panelResenaIndividual.add(txtTextoResena);

                panelResenaIndividual.setMaximumSize(new Dimension(Integer.MAX_VALUE, panelResenaIndividual.getPreferredSize().height));

                panelReseñas.add(panelResenaIndividual);
                panelReseñas.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        panelReseñas.revalidate();
        panelReseñas.repaint();
        if (jScrollMisReseñas != null) {
            jScrollMisReseñas.revalidate();
            jScrollMisReseñas.repaint();
        }
    }

    private void cargarDatosUsuario() {
        ControlNavegacion navegador = ControlNavegacion.getInstase();
        ConsultarClienteDTO usuario = navegador.getUsuarioActual();

        if (usuario != null) {

            if (lblNombreCompleto != null) {
                lblNombreCompleto.setText(usuario.getNombreCliente() + " " + usuario.getApellidoCliente());
            }
            if (jLabel12 != null) {
                jLabel12.setText(usuario.getNombreCliente());
            }
            if (jLabel11 != null) {
                jLabel11.setText(usuario.getApellidoCliente());
            }
            if (txtCorreo != null) {
                txtCorreo.setText(usuario.getCorreoElectronico());
            }

        } else {
            if (lblNombreCompleto != null) {
                lblNombreCompleto.setText("Usuario no disponible");
            }
            if (jLabel12 != null) {
                jLabel12.setText("");
            }
            if (jLabel11 != null) {
                jLabel11.setText("");
            }
            if (txtCorreo != null) {
                txtCorreo.setText("");
            }
            JOptionPane.showMessageDialog(this,"no se pudo cargar la informacion del usuario", "error",JOptionPane.ERROR_MESSAGE);
        }
    }
        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        BTNCambioContraseña = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        txtMiembro = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        BtnInicio = new javax.swing.JButton();
        BtnCarrito = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        CMBOpciones = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        BtnPerfil = new javax.swing.JButton();
        btnCategorias = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblNombreCompleto = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        txtCorreo = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollMisReseñas = new javax.swing.JScrollPane();
        panelReseñas = new javax.swing.JPanel();

        jLabel9.setBackground(new java.awt.Color(217, 202, 218));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ojo.png"))); // NOI18N

        jLabel7.setBackground(new java.awt.Color(217, 202, 218));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setText("Contraseña: ");

        BTNCambioContraseña.setBackground(new java.awt.Color(101, 85, 143));
        BTNCambioContraseña.setFont(new java.awt.Font("Segoe UI Black", 0, 20)); // NOI18N
        BTNCambioContraseña.setForeground(new java.awt.Color(255, 255, 255));
        BTNCambioContraseña.setText("Cambiar contraseña");
        BTNCambioContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCambioContraseñaActionPerformed(evt);
            }
        });

        jPasswordField1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jPasswordField1.setText("jPasswordField1");
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(217, 202, 218));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("Miembro desde: ");

        txtMiembro.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtMiembro.setText("jLabel10");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(217, 202, 218));

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

        jLabel2.setBackground(new java.awt.Color(217, 202, 218));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOG.png"))); // NOI18N

        CMBOpciones.setFont(new java.awt.Font("Segoe UI Black", 0, 20)); // NOI18N
        CMBOpciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Opciones", "Cerrar Sesion" }));
        CMBOpciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        CMBOpciones.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CMBOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMBOpcionesActionPerformed(evt);
            }
        });

        jLabel21.setBackground(new java.awt.Color(217, 202, 218));
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/list.png"))); // NOI18N

        BtnPerfil.setBackground(new java.awt.Color(101, 85, 143));
        BtnPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario.png"))); // NOI18N

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
                .addGap(81, 81, 81)
                .addComponent(btnCategorias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(BtnPerfil)
                .addGap(47, 47, 47)
                .addComponent(BtnCarrito)
                .addGap(51, 51, 51)
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
                        .addGap(42, 42, 42)
                        .addComponent(BtnInicio))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnCarrito)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CMBOpciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnPerfil)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnCategorias))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(217, 202, 218));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 0, 51), new java.awt.Color(51, 0, 51), java.awt.Color.white, new java.awt.Color(51, 0, 51)));

        jPanel3.setBackground(new java.awt.Color(217, 202, 218));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblNombreCompleto.setBackground(new java.awt.Color(217, 202, 218));
        lblNombreCompleto.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        lblNombreCompleto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario.png"))); // NOI18N
        lblNombreCompleto.setText("Nombre completo");

        jLabel4.setBackground(new java.awt.Color(217, 202, 218));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Correo electrónico:");

        btnCerrarSesion.setBackground(new java.awt.Color(101, 85, 143));
        btnCerrarSesion.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 24)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        txtCorreo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtCorreo.setText("jLabel6");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("Nombre:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel10.setText("Apellidos:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel11.setText("jLabel11");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel12.setText("jLabel12");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(501, 501, 501)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCerrarSesion)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCorreo))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel12))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel11)))))
                        .addGap(0, 50, Short.MAX_VALUE))
                    .addComponent(lblNombreCompleto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblNombreCompleto)
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCorreo))
                .addGap(28, 28, 28)
                .addComponent(btnCerrarSesion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 233, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jLabel1.setBackground(new java.awt.Color(217, 202, 218));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/balatro.png"))); // NOI18N

        jLabel8.setBackground(new java.awt.Color(217, 202, 218));
        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 36)); // NOI18N
        jLabel8.setText("¡Disfruta del libro del Balatro con un 10% de descuento!");

        jScrollMisReseñas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        panelReseñas.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelReseñasLayout = new javax.swing.GroupLayout(panelReseñas);
        panelReseñas.setLayout(panelReseñasLayout);
        panelReseñasLayout.setHorizontalGroup(
            panelReseñasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1018, Short.MAX_VALUE)
        );
        panelReseñasLayout.setVerticalGroup(
            panelReseñasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );

        jScrollMisReseñas.setViewportView(panelReseñas);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jScrollMisReseñas, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(284, 284, 284)
                        .addComponent(jLabel1)))
                .addContainerGap(261, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(308, 308, 308))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollMisReseñas, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void BtnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInicioActionPerformed

    }//GEN-LAST:event_BtnInicioActionPerformed

    private void BtnCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCarritoActionPerformed

    }//GEN-LAST:event_BtnCarritoActionPerformed

    private void CMBOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBOpcionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CMBOpcionesActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void BTNCambioContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCambioContraseñaActionPerformed

    }//GEN-LAST:event_BTNCambioContraseñaActionPerformed

    private void btnCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriasActionPerformed

    }//GEN-LAST:event_btnCategoriasActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

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
            java.util.logging.Logger.getLogger(GUIPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUIPerfil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNCambioContraseña;
    private javax.swing.JButton BtnCarrito;
    private javax.swing.JButton BtnInicio;
    private javax.swing.JButton BtnPerfil;
    private javax.swing.JComboBox<String> CMBOpciones;
    private javax.swing.JButton btnCategorias;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollMisReseñas;
    private javax.swing.JLabel lblNombreCompleto;
    private javax.swing.JPanel panelReseñas;
    private javax.swing.JLabel txtCorreo;
    private javax.swing.JLabel txtMiembro;
    // End of variables declaration//GEN-END:variables
}
