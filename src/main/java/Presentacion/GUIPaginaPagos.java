/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import Control.ControlNavegacion;
import DTOS.LibroDTO;
import Negocio.ManejoPagos;
import Negocio.PagoMastercard;
import Negocio.PagoPaypal;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author emiim
 */
public class GUIPaginaPagos extends javax.swing.JFrame {

    private List<LibroDTO> carrito = new ArrayList<>();

    /**
     * Creates new form GUIPaginaPagos
     */
    public GUIPaginaPagos() {
        initComponents();
        configurarNavegacion();
        actualizarEtiquetasYResumen();
        setLocationRelativeTo(null);
    }

    private void configurarNavegacion() {
        final ControlNavegacion navegador = ControlNavegacion.getInstase();

        if (BtnInicio != null) {
            BtnInicio.addActionListener(evt -> navegador.navegarInicio(this));
        }
        if (btnCategorias != null) {
            btnCategorias.addActionListener(evt -> navegador.navegarCategorias(this));
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
        if (BTNPaypal != null) {
            BTNPaypal.addActionListener(evt -> navegador.navegarPaginaPagoPaypal(this));
        }
        if (BTNTarjeta1 != null) {
            BTNTarjeta1.addActionListener(evt -> navegador.navegarPaginaPagoTarjeta(this));
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

    private double calcularMontoTotal() {
        ControlNavegacion nav = ControlNavegacion.getInstase();
        return nav.getMontoTotalCarrito();
    }

    private int obtenerCantidadTotalArticulos() {
        ControlNavegacion nav = ControlNavegacion.getInstase();
        return nav.getCantidadTotalArticulos();
    }

    private void actualizarEtiquetasYResumen() {
        double montoTotal = calcularMontoTotal();
        int cantidadArticulos = obtenerCantidadTotalArticulos();
        ControlNavegacion nav = ControlNavegacion.getInstase();
        List<LibroDTO> carritoActual = nav.getCarrito();

        // Actualizar etiquetas de totales
        if (lblTotalArticulos != null) {
            lblTotalArticulos.setText(String.valueOf(cantidadArticulos));
        }
        if (totalApagar != null) {
            totalApagar.setText(String.format("%.2f", montoTotal));
        }

        // --- Lógica para mostrar resumen de items (similar a GUIEnvioEstafeta) ---
        if (PanelDinamico2 != null && carritoActual != null && !carritoActual.isEmpty()) {
            PanelDinamico2.removeAll();
            // Usar BoxLayout para apilar verticalmente
            PanelDinamico2.setLayout(new BoxLayout(PanelDinamico2, BoxLayout.Y_AXIS));

            // Contar libros duplicados
            Map<String, Integer> conteoLibros = new HashMap<>();
            Map<String, LibroDTO> libroPorIsbn = new HashMap<>();
            for (LibroDTO libro : carritoActual) {
                if (libro != null && libro.getIsbn() != null) {
                    String isbn = libro.getIsbn();
                    conteoLibros.put(isbn, conteoLibros.getOrDefault(isbn, 0) + 1);
                    if (!libroPorIsbn.containsKey(isbn)) {
                        libroPorIsbn.put(isbn, libro);
                    }
                }
            }

            // Crear un panel para cada item
            for (Map.Entry<String, Integer> entry : conteoLibros.entrySet()) {
                LibroDTO libro = libroPorIsbn.get(entry.getKey());
                int cantidad = entry.getValue();

                if (libro != null) {
                    // Panel contenedor para imagen y texto
                    JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
                    itemPanel.setBackground(new java.awt.Color(255, 255, 255)); // Fondo blanco para contraste

                    // Label para la imagen
                    JLabel imgLabel = new JLabel();
                    imgLabel.setPreferredSize(new java.awt.Dimension(40, 60)); // Tamaño fijo pequeño

                    // Label para el texto
                    JLabel textLabel = new JLabel(String.format("%d x %s ($%.2f c/u)",
                            cantidad, libro.getTitulo(), libro.getPrecio()));
                    textLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // Fuente más pequeña

                    // Cargar y escalar imagen
                    try {
                        java.net.URL imgUrl = getClass().getResource(libro.getRutaImagen());
                        if (imgUrl != null) {
                            ImageIcon icon = new ImageIcon(imgUrl);
                            Image image = icon.getImage().getScaledInstance(imgLabel.getPreferredSize().width, imgLabel.getPreferredSize().height, Image.SCALE_SMOOTH);
                            imgLabel.setIcon(new ImageIcon(image));
                        } else {
                            imgLabel.setText("Img"); // Fallback
                        }
                    } catch (Exception e) {
                        imgLabel.setText("Err"); // Fallback
                        System.err.println("Error img resumen pago: " + libro.getRutaImagen() + e.getMessage());
                    }

                    itemPanel.add(imgLabel);
                    itemPanel.add(textLabel);
                    PanelDinamico2.add(itemPanel); // Añadir al panel principal
                }
            }

            // Asegurar que los cambios se muestren
            PanelDinamico2.revalidate();
            PanelDinamico2.repaint();
            // Si panelResumenItemsPago está dentro de un JScrollPane, también repintar el scrollPane
            if (jScrollPane2 != null) { // Asumiendo que así se llama el JScrollPane
                jScrollPane2.revalidate();
                jScrollPane2.repaint();
            }

        } else if (PanelDinamico2 != null) { // Si el panel existe pero el carrito está vacío
            PanelDinamico2.removeAll();
            PanelDinamico2.setLayout(new FlowLayout());
            PanelDinamico2.add(new JLabel("El carrito está vacío."));
            PanelDinamico2.revalidate();
            PanelDinamico2.repaint();
            if (jScrollPane2 != null) {
                jScrollPane2.revalidate();
                jScrollPane2.repaint();
            }
        } else {
            System.err.println("Advertencia: panelResumenItemsPago no encontrado en GUIPaginaPagos.");
        }
        // --- Fin lógica de resumen ---
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
        BtnPerfil = new javax.swing.JButton();
        btnCategorias = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        BTNTarjeta1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        BTNPaypal = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        PanelDinamico2 = new javax.swing.JPanel();
        lblTotalArticulos = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        totalApagar = new javax.swing.JLabel();

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

        BtnPerfil.setBackground(new java.awt.Color(101, 85, 143));
        BtnPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario.png"))); // NOI18N
        BtnPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerfilActionPerformed(evt);
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
                .addGap(86, 86, 86)
                .addComponent(btnCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(269, 269, 269)
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
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnCarrito)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CMBOpciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnPerfil)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnInicio)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 65, Short.MAX_VALUE)
        );

        BTNTarjeta1.setBackground(new java.awt.Color(101, 85, 143));
        BTNTarjeta1.setFont(new java.awt.Font("Segoe UI Black", 0, 20)); // NOI18N
        BTNTarjeta1.setForeground(new java.awt.Color(255, 255, 255));
        BTNTarjeta1.setText("Pagar con tarjeta de crédito/débito");
        BTNTarjeta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNTarjeta1ActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/visamasteercard.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTNTarjeta1)
                .addGap(26, 26, 26))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(BTNTarjeta1)
                .addContainerGap())
        );

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/paypal.png"))); // NOI18N

        BTNPaypal.setBackground(new java.awt.Color(101, 85, 143));
        BTNPaypal.setFont(new java.awt.Font("Segoe UI Black", 0, 20)); // NOI18N
        BTNPaypal.setForeground(new java.awt.Color(255, 255, 255));
        BTNPaypal.setText("Pagar con PayPal");
        BTNPaypal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNPaypalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTNPaypal, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(30, 30, 30)
                .addComponent(BTNPaypal)
                .addGap(19, 19, 19))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Total de artículos:");

        PanelDinamico2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportView(PanelDinamico2);

        lblTotalArticulos.setText(".");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setText("Total a pagar: $");

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 36)); // NOI18N
        jLabel1.setText("Seleccionar método de pago:");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        totalApagar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        totalApagar.setText("jLabel6");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(totalApagar)
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(totalApagar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTotalArticulos))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 802, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(196, 196, 196)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblTotalArticulos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(20, 20, 20)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(129, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInicioActionPerformed
//        GUIINICIO inicio = new GUIINICIO();
//        inicio.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_BtnInicioActionPerformed

    private void BtnCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCarritoActionPerformed
//        GUICarrito carrito = new GUICarrito(this.carrito);
//        carrito.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_BtnCarritoActionPerformed

    private void CMBOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBOpcionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CMBOpcionesActionPerformed

    private void BtnPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerfilActionPerformed
//        GUIPerfil perfil = new GUIPerfil();
//        perfil.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_BtnPerfilActionPerformed

    private void BTNPaypalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNPaypalActionPerformed
        try {
            ControlNavegacion nav = ControlNavegacion.getInstase();
            ManejoPagos mp = nav.getManejoPagos();
            mp.setMetodoPago(new PagoPaypal());

            double montoTotal = calcularMontoTotal();
            List<LibroDTO> carritoActual = nav.getCarrito();

            nav.navegarPaypal(this, montoTotal, carritoActual);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al preparar el pago con PayPal: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_BTNPaypalActionPerformed

    private void BTNTarjeta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNTarjeta1ActionPerformed
        try {
            ControlNavegacion nav = ControlNavegacion.getInstase();
            ManejoPagos mp = nav.getManejoPagos();
            mp.setMetodoPago(new PagoMastercard());

            double montoTotal = calcularMontoTotal();
            List<LibroDTO> carritoActual = nav.getCarrito();

            nav.navegarPagoConTarjeta(this, montoTotal, carritoActual);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al preparar el pago con Tarjeta: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }//GEN-LAST:event_BTNTarjeta1ActionPerformed

    private void btnCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriasActionPerformed
//        GUICategorias categorias = new GUICategorias();
//        categorias.setVisible(true);
//        this.dispose();
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
            java.util.logging.Logger.getLogger(GUIPaginaPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIPaginaPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIPaginaPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIPaginaPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIPaginaPagos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNPaypal;
    private javax.swing.JButton BTNTarjeta1;
    private javax.swing.JButton BtnCarrito;
    private javax.swing.JButton BtnInicio;
    private javax.swing.JButton BtnPerfil;
    private javax.swing.JComboBox<String> CMBOpciones;
    private javax.swing.JPanel PanelDinamico2;
    private javax.swing.JButton btnCategorias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTotalArticulos;
    private javax.swing.JLabel totalApagar;
    // End of variables declaration//GEN-END:variables
}
