package Presentacion;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import Control.ControlNavegacion;
import DTOS.LibroDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import java.net.URL;
import javax.swing.JOptionPane;

/**
 *
 * @author emiim
 */
public class GUIDetallesLibro extends javax.swing.JFrame {

private LibroDTO libroActual;
    
    /**
     * Creates new form GUIDetallesLibro
     */
    public GUIDetallesLibro(LibroDTO libro) {
        this.libroActual = libro;
        initComponents();
        configurarNavegacion();
        setLocationRelativeTo(this);     
        cargarDetallesLibro();
    }
    
    public GUIDetallesLibro() {
        this(null);
    }
    
    private void configurarNavegacion() {
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
        if (CMBCategorias != null) {
            CMBCategorias.addActionListener(evt -> navegador.navegarCategorias(this));
        }
        if (CMBOpciones != null) {
//            CMBOpciones.addActionListener(evt -> manejarAccionOpciones());
        }
        if (BtnRegresar != null) {
       BtnRegresar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               ControlNavegacion navegador = ControlNavegacion.getInstase();
               navegador.navegarCategorias(GUIDetallesLibro.this, navegador.getUltimaCategoriaSeleccionada());
           }
       });
      }
        if (BtnVerReseñas != null) {
            BtnVerReseñas.addActionListener(evt -> {
                if (libroActual != null) {
                    navegador.navegarAReseñas(this, libroActual);
                } else {
                    JOptionPane.showMessageDialog(this, "No hay un libro seleccionado para ver reseñas.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }
 
    private void manejarAccionOpciones(ControlNavegacion navegador) {
        String seleccion = (String) CMBOpciones.getSelectedItem();
        if (seleccion == null || "Opciones".equals(seleccion) || CMBOpciones.getSelectedIndex() == 0) {
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
            default:
                JOptionPane.showMessageDialog(this, "'" + seleccion + "' no implementado.");
                break;
        }
        CMBOpciones.setSelectedIndex(0); 
    }
    
    private void cargarDetallesLibro() {
        if (libroActual != null) {
            if (lblTituloLibro != null) lblTituloLibro.setText(libroActual.getTitulo());
            if (lblTituloLibro1 != null) lblTituloLibro1.setText(libroActual.getTitulo());
            if (lblAutorLibro != null) lblAutorLibro.setText("Autor: " + libroActual.getAutor());
            if (lblIsbnLibro != null) lblIsbnLibro.setText("ISBN: " + libroActual.getIsbn());
//            if (lblEditorialLibro != null) lblEditorialLibro.setText("Editorial: " + libroActual.getEditorial());
            if (lblCategoriaLibro != null) lblCategoriaLibro.setText("Categoría: " + libroActual.getCategoria());
            if (lblNumPaginasLibro != null) lblNumPaginasLibro.setText("Páginas: " + String.valueOf(libroActual.getNumPaginas()));
            if (lblPrecioLibro != null) lblPrecioLibro.setText(String.format("Precio: $%.2f", libroActual.getPrecio()));
            if (lblStockLibro != null) lblStockLibro.setText("Disponibles: " + String.valueOf(libroActual.getCantidad()));
            
//            if (lblTituloSinopsisPanel != null) {
//                lblTituloSinopsisPanel.setText("Sinopsis");
//            }
            
            if (txtAreaSinopsis != null) { 
                if (libroActual.getSinopsis() != null && !libroActual.getSinopsis().isEmpty()) {
                    txtAreaSinopsis.setText(libroActual.getSinopsis());
                } else {
                    txtAreaSinopsis.setText("Sinopsis no disponible para este libro.");
                }
                txtAreaSinopsis.setWrapStyleWord(true);
                txtAreaSinopsis.setLineWrap(true);
                txtAreaSinopsis.setEditable(false);
                txtAreaSinopsis.setCaretPosition(0);
            }
            
            if (lblPortadaLibro != null && libroActual.getRutaImagen() != null && !libroActual.getRutaImagen().isEmpty()) {
                try {
                    URL imgUrl = getClass().getResource(libroActual.getRutaImagen());
                    if (imgUrl != null) {
                        ImageIcon icon = new ImageIcon(imgUrl);
                        lblPortadaLibro.setIcon(icon);
                        lblPortadaLibro.setText(""); 
                    } else {
                        lblPortadaLibro.setText("Portada no disponible");
                        lblPortadaLibro.setIcon(null);
                    }
                } catch (Exception e) {
                    lblPortadaLibro.setText("Error al cargar portada");
                    lblPortadaLibro.setIcon(null);
                    e.printStackTrace();
                }
            } else if (lblPortadaLibro != null) {
                lblPortadaLibro.setText("Sin portada");
                lblPortadaLibro.setIcon(null);
            }
            
            if (lblFechaLanzamientoLibro != null && libroActual.getFechaLanzamiento() != null) {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
                lblFechaLanzamientoLibro.setText("Publicado: " + sdf.format(libroActual.getFechaLanzamiento()));
            }

        } else {
            if (lblTituloLibro != null) lblTituloLibro.setText("Detalles del Libro no disponibles");
            if (lblTituloLibro1 != null) lblTituloLibro1.setText("Detalles del Libro no disponibles");
            if (lblAutorLibro != null) lblAutorLibro.setText("");
            if (lblIsbnLibro != null) lblIsbnLibro.setText("");
//            if (lblEditorialLibro != null) lblEditorialLibro.setText("");
            if (lblCategoriaLibro != null) lblCategoriaLibro.setText("");
            if (lblNumPaginasLibro != null) lblNumPaginasLibro.setText("");
            if (lblPrecioLibro != null) lblPrecioLibro.setText("");
            if (lblStockLibro != null) lblStockLibro.setText("");
            if (txtAreaSinopsis != null) txtAreaSinopsis.setText("No se ha seleccionado ningún libro para mostrar detalles.");
            if (lblPortadaLibro != null) lblPortadaLibro.setIcon(null);
            if (lblFechaLanzamientoLibro != null) lblFechaLanzamientoLibro.setText("");
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
        BtnInicio = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        CMBCategorias = new javax.swing.JComboBox<>();
        BtnPerfil = new javax.swing.JButton();
        BtnCarrito = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        CMBOpciones = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblPortadaLibro = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaSinopsis = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblTituloLibro = new javax.swing.JLabel();
        lblAutorLibro = new javax.swing.JLabel();
        lblNumPaginasLibro = new javax.swing.JLabel();
        lblPrecioLibro = new javax.swing.JLabel();
        lblCategoriaLibro = new javax.swing.JLabel();
        lblStockLibro = new javax.swing.JLabel();
        lblIsbnLibro = new javax.swing.JLabel();
        lblFechaLanzamientoLibro = new javax.swing.JLabel();
        BtnRegresar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblTituloLibro1 = new javax.swing.JLabel();
        BtnVerReseñas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(101, 85, 143));

        BtnInicio.setBackground(new java.awt.Color(101, 85, 143));
        BtnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/inicio.png"))); // NOI18N
        BtnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInicioActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(BtnInicio)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CMBCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                .addComponent(BtnPerfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnCarrito)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(CMBOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(CMBCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnInicio)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(BtnCarrito, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CMBOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(BtnPerfil, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(19, 19, 19))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(217, 202, 218));

        lblPortadaLibro.setText("Imagen");

        txtAreaSinopsis.setColumns(20);
        txtAreaSinopsis.setRows(5);
        jScrollPane1.setViewportView(txtAreaSinopsis);

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel2.setText("Sinopsis");
        jLabel2.setToolTipText("");

        jPanel3.setBackground(new java.awt.Color(217, 202, 218));

        lblTituloLibro.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lblTituloLibro.setText("Nombre Libro");

        lblAutorLibro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblAutorLibro.setText("Autor/a");

        lblNumPaginasLibro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblNumPaginasLibro.setText("Paginas");

        lblPrecioLibro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblPrecioLibro.setText("Precio");

        lblCategoriaLibro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCategoriaLibro.setText("Categoria");

        lblStockLibro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblStockLibro.setText("Stock");

        lblIsbnLibro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIsbnLibro.setText("ISBN");

        lblFechaLanzamientoLibro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblFechaLanzamientoLibro.setText("Publicacion");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaLanzamientoLibro)
                    .addComponent(lblIsbnLibro)
                    .addComponent(lblStockLibro)
                    .addComponent(lblCategoriaLibro)
                    .addComponent(lblAutorLibro)
                    .addComponent(lblNumPaginasLibro)
                    .addComponent(lblPrecioLibro))
                .addGap(99, 99, 99))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblTituloLibro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloLibro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAutorLibro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCategoriaLibro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNumPaginasLibro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPrecioLibro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStockLibro)
                .addGap(12, 12, 12)
                .addComponent(lblIsbnLibro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFechaLanzamientoLibro)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        BtnRegresar.setBackground(new java.awt.Color(101, 85, 143));
        BtnRegresar.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        BtnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        BtnRegresar.setText("Regresar Categorias");
        BtnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegresarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setText("Detalles del libro:");

        lblTituloLibro1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblTituloLibro1.setText("Nombre Libro");

        BtnVerReseñas.setBackground(new java.awt.Color(101, 85, 143));
        BtnVerReseñas.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        BtnVerReseñas.setForeground(new java.awt.Color(255, 255, 255));
        BtnVerReseñas.setText("Ver Reseñas");
        BtnVerReseñas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVerReseñasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTituloLibro1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1043, 1043, 1043)
                        .addComponent(BtnVerReseñas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(135, 135, 135))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(200, 200, 200))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BtnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblPortadaLibro)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 605, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblTituloLibro1))
                        .addGap(166, 166, 166)
                        .addComponent(lblPortadaLibro)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnVerReseñas)
                    .addComponent(BtnRegresar))
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    
    private void BtnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInicioActionPerformed
         ControlNavegacion.getInstase().navegarInicio(this);
    }//GEN-LAST:event_BtnInicioActionPerformed

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

    // Función que hace que te regreses a la categoría en la que estabas
    private void BtnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegresarActionPerformed

    }//GEN-LAST:event_BtnRegresarActionPerformed

    private void BtnVerReseñasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVerReseñasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnVerReseñasActionPerformed

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
            java.util.logging.Logger.getLogger(GUIDetallesLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIDetallesLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIDetallesLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIDetallesLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
//                new GUIDetallesLibro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCarrito;
    private javax.swing.JButton BtnInicio;
    private javax.swing.JButton BtnPerfil;
    private javax.swing.JButton BtnRegresar;
    private javax.swing.JButton BtnVerReseñas;
    private javax.swing.JComboBox<String> CMBCategorias;
    private javax.swing.JComboBox<String> CMBOpciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAutorLibro;
    private javax.swing.JLabel lblCategoriaLibro;
    private javax.swing.JLabel lblFechaLanzamientoLibro;
    private javax.swing.JLabel lblIsbnLibro;
    private javax.swing.JLabel lblNumPaginasLibro;
    private javax.swing.JLabel lblPortadaLibro;
    private javax.swing.JLabel lblPrecioLibro;
    private javax.swing.JLabel lblStockLibro;
    private javax.swing.JLabel lblTituloLibro;
    private javax.swing.JLabel lblTituloLibro1;
    private javax.swing.JTextArea txtAreaSinopsis;
    // End of variables declaration//GEN-END:variables
}
