/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import Control.ControlNavegacion;
import DTOS.LibroDTO;
import DTOS.ReseñaUsuarioDTO;
import Negocio.BoProductos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author emiim
 */
public class GUIReseñas extends javax.swing.JFrame {

    private LibroDTO libroConResenas;
    private LibroDTO libroActual;

    /**
     * Creates new form GUIReseñas
     */
    public GUIReseñas(LibroDTO libro) {
        this.libroConResenas = libro;
        this.libroActual = libro;
        initComponents();
        configurarNavegacion();
        cargarResenas();
        setLocationRelativeTo(null);

        if (this.libroConResenas != null && this.libroConResenas.getTitulo() != null) {
            if (lblTituloLibro != null) {
                lblTituloLibro.setText(this.libroConResenas.getTitulo());
            }
            setTitle("Reseñas de: " + this.libroConResenas.getTitulo());
        } else {
            if (lblTituloLibro != null) {
                lblTituloLibro.setText("Libro no especificado");
            }
            setTitle("Reseñas de Libro");
        }

        cargarResenas();
        setLocationRelativeTo(null);
    }

    public GUIReseñas() {
        this(null);
    }

    private void configurarNavegacion() {
        final ControlNavegacion navegador = ControlNavegacion.getInstase();

        if (BtnInicio2 != null) {
            BtnInicio2.addActionListener(evt -> navegador.navegarInicio(this));
        }
        if (CMBCategorias != null) {
            CMBCategorias.addActionListener(evt -> {
                String categoriaSeleccionada = (String) CMBCategorias.getSelectedItem();
                navegador.setUltimaCategoriaSeleccionada(categoriaSeleccionada);
                navegador.navegarCategorias(this);
            });
        }
        if (BtnPerfil != null) {
            BtnPerfil.addActionListener(evt -> navegador.navegarPerfil(this));
        }
        if (BtnCarrito != null) {
            BtnCarrito.addActionListener(evt -> navegador.navegarCarrito(this));
        }
        if (CMBOpciones != null) {
            CMBOpciones.addActionListener(evt -> manejarAccionOpciones(navegador));
        }
        if (BtnRegresarADetalles != null) {
            BtnRegresarADetalles.addActionListener(evt -> {
                if (this.libroConResenas != null) {
                    navegador.navegarDetallesLibro(this, this.libroConResenas, navegador.getUltimaCategoriaSeleccionada());
                } else {
                    navegador.navegarCategorias(this);
                }
            });
        }
        if (BtnAgregarReseña != null) {
            BtnAgregarReseña.addActionListener(evt -> {
                if (this.libroConResenas != null) {
                    String nuevaResenaTexto = JOptionPane.showInputDialog(
                            this,
                            "Escribe tu reseña para: " + libroConResenas.getTitulo(),
                            "Agregar Nueva Reseña",
                            JOptionPane.PLAIN_MESSAGE
                    );

                    if (nuevaResenaTexto != null && !nuevaResenaTexto.trim().isEmpty()) {
                        // 1. Añadir la reseña al objeto LibroDTO en memoria
                        this.libroConResenas.agregarReseña(nuevaResenaTexto.trim());

                        // 2. Actualizar el libro en la "persistencia" (BoProductos)
                        BoProductos boProductos = new BoProductos();
                        try {
                            boolean actualizado = boProductos.actualizarLibro(this.libroConResenas);

                            if (actualizado) {
                                // 3. REGISTRAR LA RESEÑA COMO "DEL USUARIO"
                                ReseñaUsuarioDTO miResena = new ReseñaUsuarioDTO(this.libroConResenas.getTitulo(), nuevaResenaTexto.trim());
                                ControlNavegacion.getInstase().agregarResenaDelUsuario(miResena);

                                cargarResenas();
                                JOptionPane.showMessageDialog(this, "Reseña agregada y libro actualizado.");
                            } else {
                                JOptionPane.showMessageDialog(this, "No se pudo guardar la reseña (libro no encontrado para actualizar).", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "Error al guardar la reseña: " + e.getMessage(), "Error de Persistencia", JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No hay un libro seleccionado para agregar una reseña.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            });
        }
    }

    private void manejarAccionOpciones(ControlNavegacion navegador) {
        String seleccion = (String) CMBOpciones.getSelectedItem();
        if (seleccion == null || "Opciones".equals(seleccion) || CMBOpciones.getSelectedIndex() == 0) {
            CMBOpciones.setSelectedIndex(0);
            return;
        }
        switch (seleccion) {
            case "Cambiar Contraseña":
                navegador.navegarCambioPasssword(this);
                break;
            case "Cerrar Sesion":
                navegador.cerrarSesion(this);
                break;
            case "Registrar entrada":
                navegador.navegarRegistroEntrada(this);
                break;
            case "Ver Historial":
                navegador.navegarHistorialEntradas(this);
                break;
            case "Volver a Detalles del Libro":
                if (this.libroConResenas != null) {
                    navegador.navegarDetallesLibro(this, this.libroConResenas, navegador.getUltimaCategoriaSeleccionada());
                } else {
                    JOptionPane.showMessageDialog(this, "No hay detalles de libro para volver.");
                    navegador.navegarCategorias(this);
                }
                break;
            default:
                JOptionPane.showMessageDialog(this, "Opción '" + seleccion + "' no implementada.");
                break;
        }
        CMBOpciones.setSelectedIndex(0);
    }

    private void cargarResenas() {
        if (panelReseñas == null) {
            System.err.println("Error crítico: El panel para mostrar reseñas (jPanel4) no ha sido inicializado. Revisa initComponents().");
            return;
        }

        panelReseñas.removeAll();
        panelReseñas.setLayout(new BoxLayout(panelReseñas, BoxLayout.Y_AXIS));

        if (libroConResenas != null && libroConResenas.getReseñas() != null && !libroConResenas.getReseñas().isEmpty()) {

            JLabel lblTituloDelLibro = new JLabel("Reseñas para: " + libroConResenas.getTitulo());
            lblTituloDelLibro.setFont(new Font("Segoe UI", Font.BOLD, 20));
            lblTituloDelLibro.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblTituloDelLibro.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 0, 20, 0));
            jPanel4.add(lblTituloDelLibro);

            int contadorResenas = 1;
            for (String resenaTexto : libroConResenas.getReseñas()) {
                JPanel panelResenaIndividual = new JPanel(new BorderLayout(10, 5)); // Añadir espacio horizontal entre número y texto
                panelResenaIndividual.setBackground(new Color(248, 248, 248)); // Un fondo muy claro para cada reseña
                panelResenaIndividual.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)), // Línea divisoria más sutil
                        BorderFactory.createEmptyBorder(10, 15, 10, 15) // Más padding
                ));

                JLabel lblNumeroResena = new JLabel(contadorResenas + ". ");
                lblNumeroResena.setFont(new Font("Segoe UI", Font.BOLD, 15));
                lblNumeroResena.setVerticalAlignment(JLabel.TOP); // Alinear número arriba
                panelResenaIndividual.add(lblNumeroResena, BorderLayout.WEST);

                JTextArea areaResena = new JTextArea(resenaTexto);
                areaResena.setWrapStyleWord(true);
                areaResena.setLineWrap(true);
                areaResena.setEditable(false);
                areaResena.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                areaResena.setOpaque(false);

                panelResenaIndividual.add(areaResena, BorderLayout.CENTER);

                panelResenaIndividual.setMaximumSize(new Dimension(Integer.MAX_VALUE, panelResenaIndividual.getPreferredSize().height + 5));

                panelReseñas.add(panelResenaIndividual);
                contadorResenas++;
            }
        } else {

            panelReseñas.setLayout(new BorderLayout());
            JLabel noResenasLabel = new JLabel(
                    libroConResenas != null ? "No hay reseñas disponibles para este libro." : "No se ha seleccionado un libro para mostrar reseñas."
            );
            noResenasLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            noResenasLabel.setHorizontalAlignment(JLabel.CENTER);
            panelReseñas.add(noResenasLabel, BorderLayout.CENTER);
        }

        if (jScrollPaneParaResenas != null) {
            jScrollPaneParaResenas.revalidate();
            jScrollPaneParaResenas.repaint();
        } else {
            panelReseñas.revalidate();
            panelReseñas.repaint();
        }
    }

    private void cargarDetallesLibro() {
        if (libroActual != null) {
            if (lblTituloLibro != null) {
                lblTituloLibro.setText(libroActual.getTitulo());
            }
            if (lblTituloLibro != null) {
                lblTituloLibro.setText(libroActual.getTitulo());
            }
        } else {
            if (lblTituloLibro != null) {
                lblTituloLibro.setText("Detalles del Libro no disponibles");
            }
            if (lblTituloLibro != null) {
                lblTituloLibro.setText("Detalles del Libro no disponibles");
            }
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

        jPanel3 = new javax.swing.JPanel();
        BtnInicio2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        CMBCategorias = new javax.swing.JComboBox<>();
        BtnPerfil = new javax.swing.JButton();
        BtnCarrito = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        CMBOpciones = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        BtnRegresarADetalles = new javax.swing.JButton();
        jScrollPaneParaResenas = new javax.swing.JScrollPane();
        panelReseñas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblTituloLibro = new javax.swing.JLabel();
        BtnAgregarReseña = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(101, 85, 143));

        BtnInicio2.setBackground(new java.awt.Color(101, 85, 143));
        BtnInicio2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/inicio.png"))); // NOI18N
        BtnInicio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInicio2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 0, 25)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CATEGORIAS");

        CMBCategorias.setFont(new java.awt.Font("Segoe UI Black", 0, 20)); // NOI18N
        CMBCategorias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "COCINA", "FANTASIA", "TERROR", "EDUCACION" }));
        CMBCategorias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CMBCategoriasItemStateChanged(evt);
            }
        });
        CMBCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMBCategoriasActionPerformed(evt);
            }
        });

        BtnPerfil.setBackground(new java.awt.Color(101, 85, 143));
        BtnPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario.png"))); // NOI18N
        BtnPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerfilActionPerformed(evt);
            }
        });

        BtnCarrito.setBackground(new java.awt.Color(101, 85, 143));
        BtnCarrito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/carrito.png"))); // NOI18N
        BtnCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCarritoActionPerformed(evt);
            }
        });

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/list.png"))); // NOI18N

        CMBOpciones.setFont(new java.awt.Font("Segoe UI Black", 0, 20)); // NOI18N
        CMBOpciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Opciones", "Cerrar Sesion" }));
        CMBOpciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        CMBOpciones.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CMBOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMBOpcionesActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOG.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(BtnInicio2)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CMBCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnPerfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnCarrito)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(CMBOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(CMBCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnInicio2)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(BtnCarrito, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CMBOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(BtnPerfil, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(19, 19, 19))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(217, 202, 218));

        BtnRegresarADetalles.setBackground(new java.awt.Color(101, 85, 143));
        BtnRegresarADetalles.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        BtnRegresarADetalles.setForeground(new java.awt.Color(255, 255, 255));
        BtnRegresarADetalles.setText("Regresar a Detalles del Libro");
        BtnRegresarADetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegresarADetallesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelReseñasLayout = new javax.swing.GroupLayout(panelReseñas);
        panelReseñas.setLayout(panelReseñasLayout);
        panelReseñasLayout.setHorizontalGroup(
            panelReseñasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1021, Short.MAX_VALUE)
        );
        panelReseñasLayout.setVerticalGroup(
            panelReseñasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 576, Short.MAX_VALUE)
        );

        jScrollPaneParaResenas.setViewportView(panelReseñas);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 2, 36)); // NOI18N
        jLabel1.setText("Reseñas para el libro:");

        lblTituloLibro.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTituloLibro.setText("Titulo del libro");

        BtnAgregarReseña.setBackground(new java.awt.Color(101, 85, 143));
        BtnAgregarReseña.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        BtnAgregarReseña.setForeground(new java.awt.Color(255, 255, 255));
        BtnAgregarReseña.setText("Agregar Reseña");
        BtnAgregarReseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarReseñaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblTituloLibro)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1)
                            .addComponent(BtnRegresarADetalles, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnAgregarReseña, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                        .addComponent(jScrollPaneParaResenas, javax.swing.GroupLayout.PREFERRED_SIZE, 1023, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTituloLibro)
                        .addGap(399, 399, 399)
                        .addComponent(BtnAgregarReseña)
                        .addGap(18, 18, 18)
                        .addComponent(BtnRegresarADetalles))
                    .addComponent(jScrollPaneParaResenas, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnInicio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInicio2ActionPerformed
        ControlNavegacion.getInstase().navegarInicio(this);
    }//GEN-LAST:event_BtnInicio2ActionPerformed

    private void CMBCategoriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CMBCategoriasItemStateChanged

    }//GEN-LAST:event_CMBCategoriasItemStateChanged

    private void CMBCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBCategoriasActionPerformed
        ControlNavegacion.getInstase().navegarCategorias(this);
    }//GEN-LAST:event_CMBCategoriasActionPerformed

    private void BtnPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerfilActionPerformed
        ControlNavegacion.getInstase().navegarPerfil(this);
    }//GEN-LAST:event_BtnPerfilActionPerformed

    private void BtnCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCarritoActionPerformed
        ControlNavegacion.getInstase().navegarCarrito(this);
    }//GEN-LAST:event_BtnCarritoActionPerformed

    private void CMBOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBOpcionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CMBOpcionesActionPerformed

    private void BtnRegresarADetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegresarADetallesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRegresarADetallesActionPerformed

    private void BtnAgregarReseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarReseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAgregarReseñaActionPerformed

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
            java.util.logging.Logger.getLogger(GUIReseñas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIReseñas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIReseñas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIReseñas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUIReseñas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregarReseña;
    private javax.swing.JButton BtnCarrito;
    private javax.swing.JButton BtnInicio2;
    private javax.swing.JButton BtnPerfil;
    private javax.swing.JButton BtnRegresarADetalles;
    private javax.swing.JComboBox<String> CMBCategorias;
    private javax.swing.JComboBox<String> CMBOpciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPaneParaResenas;
    private javax.swing.JLabel lblTituloLibro;
    private javax.swing.JPanel panelReseñas;
    // End of variables declaration//GEN-END:variables
}
