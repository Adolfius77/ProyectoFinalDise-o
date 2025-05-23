/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import Control.ControlNavegacion;
import DTOS.LibroDTO;
import Negocio.BoProductos;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author garfi
 */
public class GUIEditarLibro extends javax.swing.JFrame {

    /**
     * Creates new form GUIPagPrincipal
     */
    private LibroDTO libroOriginal;
    private String rutaImagenSeleccionada = "";

    public GUIEditarLibro(LibroDTO libroAEditar) {
        initComponents();
        this.libroOriginal = libroAEditar;
        cargarDatosLibro();
        setLocationRelativeTo(null);
        configurarNavegacion();
    }

    private void configurarNavegacion() {
        final ControlNavegacion navegador = ControlNavegacion.getInstase();

        if (btnInicio != null) {
            btnInicio.addActionListener(evt -> navegador.navegarAdminGui(this));
        }
        if (btnPerfil != null) {
            btnPerfil.addActionListener(evt -> navegador.navegarPerfil(this));
        }
        if (CmbOpciones != null) {
            CmbOpciones.addActionListener(evt -> manejarAccionOpciones());
        }
        if (btnRegresar != null) {
            btnRegresar.addActionListener(evt -> {
                int confirmacion = JOptionPane.showConfirmDialog(
                        this,
                        "¿Está seguro de que desea regresar? Perderá los datos no guardados.",
                        "Confirmar Salida",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    navegador.navegarGestionLibro(this);
                }
            });
        }

    }

     private void manejarAccionOpciones() {
        String seleccion = (String) CmbOpciones.getSelectedItem();
        if (seleccion == null || "Opciones".equals(seleccion) || CmbOpciones.getSelectedIndex() == 0) {
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
            CmbOpciones.setSelectedIndex(0); // Resetear
    }

    private void cargarDatosLibro() {
        if (libroOriginal == null) {
            JOptionPane.showMessageDialog(this, "Error: No existe libro para cargar datos.", "Error", JOptionPane.ERROR_MESSAGE);
            if (btnEditar != null) {
                btnEditar.setEnabled(false);
            }
            return;
        }

        txtFldNombreProducto.setText(libroOriginal.getTitulo());
        txtFldAutor.setText(libroOriginal.getAutor());
        txtFldISBN.setText(libroOriginal.getIsbn());
        txtFldISBN.setEditable(false);

        if (libroOriginal.getFechaLanzamiento() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            txtFldFechaLanzamiento.setText(sdf.format(libroOriginal.getFechaLanzamiento()));
        }
        cmbBoxCategoria.setSelectedItem(libroOriginal.getCategoria());
        txtFldPrecio.setText(String.valueOf(libroOriginal.getPrecio()));
        txtFldEditorial.setText(libroOriginal.getEditorial());
        txtFldNumPaginas.setText(String.valueOf(libroOriginal.getNumPaginas()));

        this.rutaImagenSeleccionada = libroOriginal.getRutaImagen();
        if (this.rutaImagenSeleccionada != null && !this.rutaImagenSeleccionada.isEmpty() && lblImagen != null) {
            try {
                java.net.URL imgUrl = getClass().getResource(this.rutaImagenSeleccionada);
                if (imgUrl != null) {
                    ImageIcon icon = new ImageIcon(imgUrl);
                    int lblWidth = lblImagen.getWidth() > 0 ? lblImagen.getWidth() : (lblImagen.getPreferredSize() != null ? lblImagen.getPreferredSize().width : 100);
                    int lblHeight = lblImagen.getHeight() > 0 ? lblImagen.getHeight() : (lblImagen.getPreferredSize() != null ? lblImagen.getPreferredSize().height : 120);

                    if (lblWidth > 0 && lblHeight > 0) {
                        Image img = icon.getImage().getScaledInstance(lblWidth, lblHeight, Image.SCALE_SMOOTH);
                        lblImagen.setIcon(new ImageIcon(img));
                    } else {
                        lblImagen.setIcon(icon);
                    }
                    lblImagen.setText("");
                } else {
                    lblImagen.setText("Portada no encontrada");
                    lblImagen.setIcon(null);
                    System.err.println("Edición: Recurso de imagen no encontrado: " + this.rutaImagenSeleccionada);
                }
            } catch (Exception e) {
                lblImagen.setText("Error al cargar portada");
                lblImagen.setIcon(null);
                System.err.println("Edición: Excepción al cargar imagen '" + this.rutaImagenSeleccionada + "': " + e.getMessage());
            }
        } else if (lblImagen != null) {
            lblImagen.setText("Sin portada o error. Puede cargar una nueva.");
            lblImagen.setIcon(null);
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
        btnInicio = new javax.swing.JButton();
        btnPerfil = new javax.swing.JButton();
        LblLogo = new javax.swing.JLabel();
        lblGestionLibros = new javax.swing.JLabel();
        CmbOpciones = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        lblNombreProducto = new javax.swing.JLabel();
        txtFldNombreProducto = new javax.swing.JTextField();
        lblAutor = new javax.swing.JLabel();
        txtFldAutor = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        txtFldPrecio = new javax.swing.JTextField();
        lblFechaLanzamiento = new javax.swing.JLabel();
        txtFldFechaLanzamiento = new javax.swing.JTextField();
        lblCategoria = new javax.swing.JLabel();
        lblISBN = new javax.swing.JLabel();
        txtFldISBN = new javax.swing.JTextField();
        lblEditorial = new javax.swing.JLabel();
        txtFldNumPaginas = new javax.swing.JTextField();
        txtFldEditorial = new javax.swing.JTextField();
        lblNumPaginas = new javax.swing.JLabel();
        cmbBoxCategoria = new javax.swing.JComboBox<>();
        btnEditar = new javax.swing.JButton();
        btnEditarPortada = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jPanelEditarPortada = new javax.swing.JPanel();
        lblImagen = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(217, 202, 218));

        jPanel1.setBackground(new java.awt.Color(101, 85, 143));

        btnInicio.setBackground(new java.awt.Color(101, 85, 143));
        btnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/inicio.png"))); // NOI18N
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        btnPerfil.setBackground(new java.awt.Color(101, 85, 143));
        btnPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario.png"))); // NOI18N
        btnPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerfilActionPerformed(evt);
            }
        });

        LblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOG.png"))); // NOI18N

        lblGestionLibros.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblGestionLibros.setForeground(new java.awt.Color(255, 255, 255));
        lblGestionLibros.setText("Gestion de Libros");

        CmbOpciones.setFont(new java.awt.Font("Segoe UI Black", 0, 16)); // NOI18N
        CmbOpciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Opciones", "Ver Historial compras", "Cambiar Contraseña", "Gestion de Clientes", "Cerrar Sesion" }));
        CmbOpciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        CmbOpciones.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CmbOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbOpcionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblGestionLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144)
                .addComponent(btnPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CmbOpciones, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(LblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGestionLibros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CmbOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))))
        );

        jPanel4.setBackground(new java.awt.Color(217, 202, 218));

        lblNombreProducto.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblNombreProducto.setText("Titulo:");

        txtFldNombreProducto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtFldNombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFldNombreProductoActionPerformed(evt);
            }
        });

        lblAutor.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblAutor.setText("Autor:");

        txtFldAutor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblPrecio.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblPrecio.setText("Precio:");

        txtFldPrecio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblFechaLanzamiento.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblFechaLanzamiento.setText("Fecha Lanzamiento:");

        txtFldFechaLanzamiento.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblCategoria.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCategoria.setText("Categoria:");

        lblISBN.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblISBN.setText("ISBN:");

        txtFldISBN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblEditorial.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblEditorial.setText("Editorial:");

        txtFldNumPaginas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtFldEditorial.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblNumPaginas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblNumPaginas.setText("Número de Páginas:");

        cmbBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cocina", "Fantasia", "Terror", "Educacion" }));
        cmbBoxCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBoxCategoriaActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(101, 85, 143));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEditarPortada.setBackground(new java.awt.Color(101, 85, 143));
        btnEditarPortada.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditarPortada.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarPortada.setText("Editar Portada");
        btnEditarPortada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPortadaActionPerformed(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(101, 85, 143));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNombreProducto)
                    .addComponent(lblAutor)
                    .addComponent(txtFldNombreProducto)
                    .addComponent(txtFldAutor)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPrecio)
                                    .addComponent(txtFldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCategoria)
                                    .addComponent(lblEditorial)
                                    .addComponent(txtFldEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblFechaLanzamiento)
                                    .addComponent(txtFldFechaLanzamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblISBN)
                                    .addComponent(txtFldISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFldNumPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNumPaginas)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnEditarPortada, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lblNombreProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFldNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAutor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtFldAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblPrecio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblFechaLanzamiento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFldFechaLanzamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCategoria)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblISBN)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtFldISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblEditorial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFldEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblNumPaginas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFldNumPaginas)))
                        .addGap(89, 89, 89))
                    .addComponent(btnEditarPortada, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanelEditarPortada.setBackground(new java.awt.Color(222, 188, 222));

        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanelEditarPortadaLayout = new javax.swing.GroupLayout(jPanelEditarPortada);
        jPanelEditarPortada.setLayout(jPanelEditarPortadaLayout);
        jPanelEditarPortadaLayout.setHorizontalGroup(
            jPanelEditarPortadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEditarPortadaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1))
        );
        jPanelEditarPortadaLayout.setVerticalGroup(
            jPanelEditarPortadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEditarPortadaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelEditarPortadaLayout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelEditarPortada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 29, Short.MAX_VALUE))
                    .addComponent(jPanelEditarPortada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed

    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilActionPerformed

    }//GEN-LAST:event_btnPerfilActionPerformed

    private void txtFldNombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldNombreProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldNombreProductoActionPerformed

    private void cmbBoxCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBoxCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBoxCategoriaActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (libroOriginal == null) {
            JOptionPane.showMessageDialog(this, "Error: No hay información original del libro para guardar cambios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

       
        String titulo = txtFldNombreProducto.getText().trim();
        String autor = txtFldAutor.getText().trim();
        String isbnDelLibro = libroOriginal.getIsbn(); 
        String fechaStr = txtFldFechaLanzamiento.getText().trim();
        String categoria = (String) cmbBoxCategoria.getSelectedItem();
        String editorial = txtFldEditorial.getText().trim();
        String numPaginasStr = txtFldNumPaginas.getText().trim();
     
        int cantidadOriginal = libroOriginal.getCantidad();
        String precioStr = txtFldPrecio.getText().trim();
    

       
        if (titulo.isEmpty() || autor.isEmpty() || fechaStr.isEmpty() || editorial.isEmpty()
                || numPaginasStr.isEmpty() || precioStr.isEmpty()
                || (this.rutaImagenSeleccionada == null || this.rutaImagenSeleccionada.trim().isEmpty())) {
            JOptionPane.showMessageDialog(this, "Todos los campos (incluida la portada) son obligatorios.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Date fechaLanzamiento = null;
        int numPaginas = 0;
        double precio = 0.0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            fechaLanzamiento = sdf.parse(fechaStr);
            numPaginas = Integer.parseInt(numPaginasStr);
            precio = Double.parseDouble(precioStr);
            if (numPaginas <= 0 || precio < 0) {
                throw new NumberFormatException("Valores no permitidos");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en el formato de los campos numéricos o de fecha.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        LibroDTO libroActualizado = new LibroDTO(
                titulo, autor, isbnDelLibro, fechaLanzamiento, categoria, precio,
                editorial, numPaginas,
                cantidadOriginal,
                this.rutaImagenSeleccionada 
        );

        
        BoProductos boProductos = new BoProductos();
        boolean actualizadoExitosamente = boProductos.actualizarLibro(libroActualizado);

        if (actualizadoExitosamente) {
            JOptionPane.showMessageDialog(this, "Libro \"" + titulo + "\" actualizado con éxito.");
            ControlNavegacion.getInstase().navegarGestionLibro(this);
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el libro. Verifique los datos o contacte al administrador.", "Error de Actualización", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEditarPortadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPortadaActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de imagen", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);
        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                String destinationPathString = "src/main/resources/img";
                File destinationFolder = new File(destinationPathString);

                if (!destinationFolder.exists()) {
                    if (!destinationFolder.mkdirs()) {
                        JOptionPane.showMessageDialog(this, "Error: No se pudo crear la carpeta de destino para las portadas en " + destinationFolder.getAbsolutePath(), "Error de Directorio", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                String fileName = selectedFile.getName();
                Path destinationFilePath = Paths.get(destinationFolder.getAbsolutePath(), fileName);

                Files.copy(selectedFile.toPath(), destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Imagen copiada a: " + destinationFilePath.toString());

                this.rutaImagenSeleccionada = "/img/" + fileName;
                System.out.println("Ruta relativa guardada en DTO: " + this.rutaImagenSeleccionada);

                if (lblImagen != null) {
                    ImageIcon icon = new ImageIcon(destinationFilePath.toString());
                    int lblWidth = lblImagen.getWidth();
                    int lblHeight = lblImagen.getHeight();
                    if (lblWidth > 0 && lblHeight > 0) {
                        Image img = icon.getImage().getScaledInstance(lblWidth, lblHeight, Image.SCALE_SMOOTH);
                        lblImagen.setIcon(new ImageIcon(img));
                    } else {
                        Dimension prefSize = lblImagen.getPreferredSize();
                        if (prefSize.width > 0 && prefSize.height > 0) {
                            Image img = icon.getImage().getScaledInstance(prefSize.width, prefSize.height, Image.SCALE_SMOOTH);
                            lblImagen.setIcon(new ImageIcon(img));
                        } else {
                            lblImagen.setIcon(icon);
                        }
                    }
                    lblImagen.setText("");
                }
                JOptionPane.showMessageDialog(this, "Portada '" + fileName + "' seleccionada.");

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al copiar la imagen: " + e.getMessage(), "Error de Archivo", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                this.rutaImagenSeleccionada = "";
            }
        }
    }//GEN-LAST:event_btnEditarPortadaActionPerformed

    private void CmbOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbOpcionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbOpcionesActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegresarActionPerformed

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
            java.util.logging.Logger.getLogger(GUIEditarLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIEditarLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIEditarLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIEditarLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Date fechaEjemplo = new Date();
                LibroDTO libroDePrueba = new LibroDTO(
                        "Título de Prueba", "Autor de Prueba", "1234567890123",
                        fechaEjemplo, "FANTASIA", 29.99,
                        "Editorial Prueba", 300, 10, "/img/balatroLibro.jpg"
                );
                new GUIEditarLibro(libroDePrueba).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CmbOpciones;
    private javax.swing.JLabel LblLogo;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditarPortada;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnPerfil;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbBoxCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelEditarPortada;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblEditorial;
    private javax.swing.JLabel lblFechaLanzamiento;
    private javax.swing.JLabel lblGestionLibros;
    private javax.swing.JLabel lblISBN;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblNombreProducto;
    private javax.swing.JLabel lblNumPaginas;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JTextField txtFldAutor;
    private javax.swing.JTextField txtFldEditorial;
    private javax.swing.JTextField txtFldFechaLanzamiento;
    private javax.swing.JTextField txtFldISBN;
    private javax.swing.JTextField txtFldNombreProducto;
    private javax.swing.JTextField txtFldNumPaginas;
    private javax.swing.JTextField txtFldPrecio;
    // End of variables declaration//GEN-END:variables
}
