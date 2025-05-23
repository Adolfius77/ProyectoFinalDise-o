/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import Control.ControlNavegacion;
import DTOS.EntradaHistorialDTO;
import DTOS.LibroDTO;
import Negocio.BoProductos;
import expciones.PersistenciaException;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author garfi
 */
public class GUIRegistrarEntrada extends javax.swing.JFrame {

    /**
     * Creates new form GUIPagPrincipal
     */
    private String rutaImagenSeleccionada = "";
    private LibroDTO libroEcontrado = null;

    public GUIRegistrarEntrada() {
       initComponents();
        setLocationRelativeTo(null);
        configurarNavegacion();
        habilitarCamposRegistroEntrada(false);

        habilitarCamposInfoLibro(false);
    }

    private void configurarNavegacion() {
        final ControlNavegacion navegador = ControlNavegacion.getInstase();

        if (btnInicio != null) {
            btnInicio.addActionListener(evt -> navegador.navegarAdminGui(this));
        }

        if (CmbOpciones != null) {
            CmbOpciones.addActionListener(evt -> manejarAccionOpciones());
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

    private void inicializarCamposEntrada() {

        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
        Date now = new Date();
        if (txtFechaEntrada != null) {
            txtFechaEntrada.setText(sdfDate.format(now));
        }
        if (txtHoraEntrada != null) {
            txtHoraEntrada.setText(sdfTime.format(now));
        
        }

    }

    private void habilitarCamposInfoLibro(boolean habilitar) {
      txtTitulo.setEditable(false);
        txtAutor.setEditable(false);
        txtStockActual.setEditable(false);

        txtTitulo.setEnabled(habilitar);
        txtAutor.setEnabled(habilitar);
        txtStockActual.setEnabled(habilitar);
        lblImagen.setEnabled(habilitar);


    }

    private void habilitarCamposRegistroEntrada(boolean habilitar) {
        txtCantidadEntrada.setEnabled(habilitar);
        txtFechaEntrada.setEnabled(habilitar);
        txtHoraEntrada.setEnabled(habilitar);
        txtNotasAdicionales.setEnabled(habilitar);
        btnRegresar.setEnabled(habilitar);
    }

    private void limpiarCamposInfoLibro() {
        txtTitulo.setText("");
        txtAutor.setText("");
        txtStockActual.setText("");
        if (lblImagen != null) {
            lblImagen.setIcon(null);
            lblImagen.setText("Portada del Libro");
        }
    }

    private void limpiarCamposRegistroEntrada() {
        txtCantidadEntrada.setText("");
        inicializarCamposEntrada();
        txtNotasAdicionales.setText("");

    }

    private void limpiarCamposParaNuevaBusqueda() {
        txtIsbn.setText("");
        txtIsbn.setEditable(true);
        BtnBuscarIsbn.setEnabled(true);

        libroEcontrado = null;

        limpiarCamposInfoLibro();
        habilitarCamposInfoLibro(false);

        limpiarCamposRegistroEntrada();
        habilitarCamposRegistroEntrada(false);

        txtIsbn.requestFocus();
    }

    private void limpiarPrimeraBusqueda() {
        txtIsbn.setText("");
        txtIsbn.setEditable(true);
        BtnBuscarIsbn.setEnabled(true);

        libroEcontrado = null;

        limpiarCamposInfoLibro();
        habilitarCamposInfoLibro(false);

        habilitarCamposRegistroEntrada(false);

        txtIsbn.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombreProducto = new javax.swing.JLabel();
        txtFldNombreProducto = new javax.swing.JTextField();
        txtFldAutor = new javax.swing.JTextField();
        txtFldPrecio = new javax.swing.JTextField();
        cmbBoxCategoria = new javax.swing.JComboBox<>();
        txtFldEditorial = new javax.swing.JTextField();
        btnRegresar = new javax.swing.JButton();
        txtFldStock = new javax.swing.JTextField();
        lblStock = new javax.swing.JLabel();
        lblEditorial = new javax.swing.JLabel();
        lblCategoria = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        lblAutor = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblFechaLanzamiento = new javax.swing.JLabel();
        txtFldFechaLanzamiento = new javax.swing.JTextField();
        lblISBN = new javax.swing.JLabel();
        txtFldISBN = new javax.swing.JTextField();
        txtFldNumPaginas = new javax.swing.JTextField();
        lblNumPaginas = new javax.swing.JLabel();
        btnAgregarPortada = new javax.swing.JButton();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnInicio = new javax.swing.JButton();
        LblLogo = new javax.swing.JLabel();
        lblGestionLibros = new javax.swing.JLabel();
        CmbOpciones = new javax.swing.JComboBox<>();
        jPanelAgregarPortada = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtIsbn = new javax.swing.JTextField();
        BtnBuscarIsbn = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtStockActual = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtAutor = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCantidadEntrada = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtFechaEntrada = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtHoraEntrada = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNotasAdicionales = new javax.swing.JTextField();

        lblNombreProducto.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblNombreProducto.setText("Titulo:");

        txtFldNombreProducto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtFldNombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFldNombreProductoActionPerformed(evt);
            }
        });

        txtFldAutor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtFldPrecio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        cmbBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cocina", "Fantasia", "Terror", "Educacion", " ", " " }));
        cmbBoxCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBoxCategoriaActionPerformed(evt);
            }
        });

        txtFldEditorial.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnRegresar.setBackground(new java.awt.Color(101, 85, 143));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        txtFldStock.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblStock.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblStock.setText("Stock:");

        lblEditorial.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblEditorial.setText("Editorial:");

        lblCategoria.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCategoria.setText("Categoria:");

        lblPrecio.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblPrecio.setText("Precio:");

        lblAutor.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblAutor.setText("Autor:");

        btnAgregar.setBackground(new java.awt.Color(101, 85, 143));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(217, 202, 218));

        lblFechaLanzamiento.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblFechaLanzamiento.setText("Fecha Lanzamiento:");

        txtFldFechaLanzamiento.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblISBN.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblISBN.setText("ISBN:");

        txtFldISBN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtFldNumPaginas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblNumPaginas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblNumPaginas.setText("Número de Páginas:");

        btnAgregarPortada.setBackground(new java.awt.Color(101, 85, 143));
        btnAgregarPortada.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregarPortada.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarPortada.setText("Agregar Portada");
        btnAgregarPortada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPortadaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(381, 381, 381)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblFechaLanzamiento)
                        .addComponent(txtFldFechaLanzamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblISBN)
                        .addComponent(txtFldISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFldNumPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNumPaginas))
                    .addComponent(btnAgregarPortada, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblFechaLanzamiento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFldFechaLanzamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblISBN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFldISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNumPaginas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFldNumPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89))
                    .addComponent(btnAgregarPortada, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        jFormattedTextField1.setText("jFormattedTextField1");

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

        LblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOG.png"))); // NOI18N

        lblGestionLibros.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblGestionLibros.setForeground(new java.awt.Color(255, 255, 255));
        lblGestionLibros.setText("Registro de Entrada");

        CmbOpciones.setFont(new java.awt.Font("Segoe UI Black", 0, 16)); // NOI18N
        CmbOpciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Opciones", "Gestion de Libros", "Registrar Entrada", "Ver Historial entrada", "Gestion de Clientes", "Cerrar Sesion" }));
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
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(lblGestionLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(LblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180)
                .addComponent(CmbOpciones, 0, 414, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CmbOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGestionLibros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelAgregarPortada.setBackground(new java.awt.Color(222, 188, 222));

        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanelAgregarPortadaLayout = new javax.swing.GroupLayout(jPanelAgregarPortada);
        jPanelAgregarPortada.setLayout(jPanelAgregarPortadaLayout);
        jPanelAgregarPortadaLayout.setHorizontalGroup(
            jPanelAgregarPortadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAgregarPortadaLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );
        jPanelAgregarPortadaLayout.setVerticalGroup(
            jPanelAgregarPortadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAgregarPortadaLayout.createSequentialGroup()
                .addGap(529, 529, 529)
                .addComponent(jLabel1)
                .addContainerGap(231, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAgregarPortadaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel2.setText("Buscar libro Existente");

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel3.setText("Ingrese el ISBN del libro:");

        BtnBuscarIsbn.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        BtnBuscarIsbn.setText("Buscar");
        BtnBuscarIsbn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarIsbnActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel4.setText("Informacion del libro encontrado");

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel5.setText("Titulo:");

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel6.setText("Autor:");

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel7.setText("Stock Actual:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtStockActual, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(500, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(9, 9, 9)
                .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(1, 1, 1)
                .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtStockActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancelar.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(BtnBuscarIsbn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCancelar)
                                .addGap(22, 22, 22))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnBuscarIsbn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnRegistrar.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnRegistrar.setText("Registrar Entrada");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel8.setText("Registrar Detalles de la nueva entrada");

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel9.setText("Cantidad de entrada:");

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel10.setText("Fecha de entrada:");

        txtFechaEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaEntradaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel11.setText("Hora de entrada");

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel12.setText("Notas adicionales:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegistrar)
                .addGap(29, 29, 29))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(txtCantidadEntrada)
                    .addComponent(txtFechaEntrada)
                    .addComponent(txtHoraEntrada)
                    .addComponent(txtNotasAdicionales))
                .addContainerGap(486, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidadEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNotasAdicionales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegistrar)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanelAgregarPortada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelAgregarPortada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
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
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed

    }//GEN-LAST:event_btnInicioActionPerformed

    private void txtFldNombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldNombreProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldNombreProductoActionPerformed

    private void cmbBoxCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBoxCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBoxCategoriaActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String titulo = txtFldNombreProducto.getText().trim();
        String autor = txtFldAutor.getText().trim();
        String isbn = txtFldISBN.getText().trim();
        String fechaStr = txtFldFechaLanzamiento.getText().trim();
        String categoria = (String) cmbBoxCategoria.getSelectedItem();
        String editorial = txtFldEditorial.getText().trim();

        int numPaginas = 0;
        int cantidad = 0;
        double precio = 0.0;

        if (titulo.isEmpty() || autor.isEmpty() || isbn.isEmpty() || fechaStr.isEmpty()
                || editorial.isEmpty() || txtFldNumPaginas.getText().trim().isEmpty()
                || txtFldStock.getText().trim().isEmpty() || txtFldPrecio.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (this.rutaImagenSeleccionada == null || this.rutaImagenSeleccionada.trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar una imagen de portada para el libro.",
                    "Portada Requerida",
                    JOptionPane.ERROR_MESSAGE);
            return;

        }

        String isbnLimpio = isbn.replaceAll("[\\s-]+", ""); // Limpiar guiones y espacios
        boolean formatoIsbnCorrecto = false;
        if (isbnLimpio.length() == 10) {
            if (isbnLimpio.substring(0, 9).matches("\\d{9}")) { // Primeros 9 son dígitos
                char ultimoCaracter = isbnLimpio.charAt(9);
                if (Character.isDigit(ultimoCaracter) || Character.toUpperCase(ultimoCaracter) == 'X') {
                    formatoIsbnCorrecto = true;
                }
            }
        } else if (isbnLimpio.length() == 13) {
            if (isbnLimpio.matches("\\d{13}")) { // Todos son dígitos
                formatoIsbnCorrecto = true;
            }
        }

        if (!formatoIsbnCorrecto) {
            JOptionPane.showMessageDialog(this,
                    "El formato del ISBN no es válido.\n"
                    + "Debe ser de 10 dígitos (el último puede ser 'X') o 13 dígitos numéricos.\n"
                    + "Los guiones y espacios son opcionales y se ignorarán para la validación.",
                    "ISBN Inválido",
                    JOptionPane.ERROR_MESSAGE);
            txtFldISBN.requestFocus();
            return;
        }

        try {

            try {
                numPaginas = Integer.parseInt(txtFldNumPaginas.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Número de páginas debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                cantidad = Integer.parseInt(txtFldStock.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Stock (Cantidad) debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                precio = Double.parseDouble(txtFldPrecio.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Precio debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (cantidad < 0) {
                JOptionPane.showMessageDialog(this, "El stock no puede ser negativo.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (precio < 0) {
                JOptionPane.showMessageDialog(this, "El precio no puede ser negativo.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (numPaginas <= 0) {
                JOptionPane.showMessageDialog(this, "El número de páginas debe ser mayor que cero.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            Date fechaLanzamiento = sdf.parse(fechaStr);

            LibroDTO nuevoLibro = new LibroDTO(
                    titulo,
                    autor,
                    isbnLimpio,
                    fechaLanzamiento,
                    categoria,
                    precio,
                    editorial,
                    numPaginas,
                    cantidad,
                    rutaImagenSeleccionada
            );

            BoProductos boProductos = new BoProductos();
            boolean agregadoExitosamente = boProductos.agregarLibro(nuevoLibro);

            if (agregadoExitosamente) {
                int resultado = JOptionPane.showConfirmDialog(this,
                        "¿Está seguro de querer agregar el producto \"" + titulo + "\"?",
                        "Confirmación Agregar Libro",
                        JOptionPane.YES_NO_OPTION);

                if (resultado == JOptionPane.YES_OPTION) {
                    System.out.println("Se agregó el libro con éxito: " + titulo);
                    JOptionPane.showMessageDialog(this, "Libro \"" + titulo + "\" agregado con éxito.");

                    ControlNavegacion.getInstase().navegarGestionLibro(this);

                    // Limpiar campos
                    txtFldNombreProducto.setText("");
                    txtFldAutor.setText("");
                    txtFldISBN.setText("");
                    txtFldFechaLanzamiento.setText("");
                    cmbBoxCategoria.setSelectedIndex(0);
                    txtFldEditorial.setText("");
                    txtFldNumPaginas.setText("");
                    txtFldStock.setText("");
                    txtFldPrecio.setText("");
                    rutaImagenSeleccionada = "";
                    lblImagen.setIcon(null);
                    lblImagen.setText("Portada aquí...");

                } else {
                    System.out.println("Adición del libro \"" + titulo + "\" cancelada por el usuario.");

                }
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar el libro. El ISBN podría ya existir.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this, "Asegúrese de que los campos numéricos (Precio, Stock, Páginas) sean válidos.", "Error de Formato General", JOptionPane.ERROR_MESSAGE);
        } catch (java.text.ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Por favor, use dd/MM/yyyy.", "Error de Formato de Fecha", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnAgregarPortadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPortadaActionPerformed
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
    }//GEN-LAST:event_btnAgregarPortadaActionPerformed

    private void CmbOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbOpcionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbOpcionesActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void txtFechaEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaEntradaActionPerformed

    private void BtnBuscarIsbnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarIsbnActionPerformed
        String isbnBuscado = txtIsbn.getText().trim();
        if (isbnBuscado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un ISBN para buscar.", "ISBN Vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }

        BoProductos boProductos = new BoProductos();
        try {
            libroEcontrado = boProductos.obtenerLibrosPorIsbn(isbnBuscado);

            if (libroEcontrado != null) {
                txtTitulo.setText(libroEcontrado.getTitulo());
                txtAutor.setText(libroEcontrado.getAutor());
                txtStockActual.setText(String.valueOf(libroEcontrado.getCantidad()));

                txtIsbn.setEditable(false);
                BtnBuscarIsbn.setEnabled(false);

                if (lblImagen != null && libroEcontrado.getRutaImagen() != null && !libroEcontrado.getRutaImagen().isEmpty()) {
                    URL imgUrl = getClass().getResource(libroEcontrado.getRutaImagen());
                    if (imgUrl != null) {
                        ImageIcon icon = new ImageIcon(imgUrl);

                        int anchoImg = lblImagen.getWidth();
                        int altoImg = lblImagen.getHeight();

                        if (anchoImg <= 0 || altoImg <= 0) {
                            if (jPanelAgregarPortada != null && jPanelAgregarPortada.getWidth() > 0 && jPanelAgregarPortada.getHeight() > 0) {
                                anchoImg = jPanelAgregarPortada.getWidth() - 10;
                                altoImg = jPanelAgregarPortada.getHeight() - 20;
                            } else {
                                anchoImg = 480;
                                altoImg = 665;
                            }
                        }

                        if (anchoImg <= 0) {
                            anchoImg = 480;
                        }
                        if (altoImg <= 0) {
                            altoImg = 665;
                        }

                        Image img = icon.getImage().getScaledInstance(anchoImg, altoImg, Image.SCALE_SMOOTH);
                        lblImagen.setIcon(new ImageIcon(img));
                        lblImagen.setText("");
                    } else {
                        lblImagen.setText("Portada no encontrada");
                        lblImagen.setIcon(null);
                        System.err.println("URL de imagen no encontrada para: " + libroEcontrado.getRutaImagen());
                    }
                } else if (lblImagen != null) {
                    lblImagen.setText("Sin portada");
                    lblImagen.setIcon(null);
                }

                habilitarCamposInfoLibro(true);
                habilitarCamposRegistroEntrada(true);
                txtCantidadEntrada.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Libro con ISBN '" + isbnBuscado + "' no encontrado.", "No Encontrado", JOptionPane.INFORMATION_MESSAGE);
                limpiarPrimeraBusqueda();
            }
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar el libro: " + e.getMessage(), "Error de Búsqueda", JOptionPane.ERROR_MESSAGE);
            limpiarCamposParaNuevaBusqueda();
        }
    }//GEN-LAST:event_BtnBuscarIsbnActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if (libroEcontrado == null) {
            JOptionPane.showMessageDialog(this, "Primero debe buscar y encontrar un libro.", "Libro No Seleccionado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String cantidadEntradaStr = txtCantidadEntrada.getText().trim();
        String fechaEntradaStr = txtFechaEntrada.getText().trim();
        String horaEntradaStr = txtHoraEntrada.getText().trim();

        if (cantidadEntradaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar la cantidad de entrada.", "Campo Vacío", JOptionPane.WARNING_MESSAGE);
            txtCantidadEntrada.requestFocus();
            return;
        }
        int cantidadEntrada;
        try {
            cantidadEntrada = Integer.parseInt(cantidadEntradaStr);
            if (cantidadEntrada <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad de entrada debe ser un número positivo.", "Cantidad Inválida", JOptionPane.ERROR_MESSAGE);
                txtCantidadEntrada.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad de entrada debe ser un número válido.", "Formato Incorrecto", JOptionPane.ERROR_MESSAGE);
            txtCantidadEntrada.requestFocus();
            return;
        }
        Date fechaEntrada;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            fechaEntrada = sdf.parse(fechaEntradaStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha de entrada incorrecto. Use dd/MM/yyyy.", "Formato de Fecha", JOptionPane.ERROR_MESSAGE);
            txtFechaEntrada.requestFocus();
            return;
        }
        Date fechaHoraActual = Calendar.getInstance().getTime();

        int stockActual = libroEcontrado.getCantidad();
        int nuevoStock = stockActual + cantidadEntrada;

        LibroDTO libroActualizado = new LibroDTO(
                libroEcontrado.getTitulo(),
                libroEcontrado.getAutor(),
                libroEcontrado.getIsbn(),
                libroEcontrado.getFechaLanzamiento(),
                libroEcontrado.getCategoria(),
                libroEcontrado.getPrecio(),
                libroEcontrado.getEditorial(),
                libroEcontrado.getNumPaginas(),
                nuevoStock,
                libroEcontrado.getRutaImagen()
        );
        BoProductos boProductos = new BoProductos();
        try {
            boolean actualizado = boProductos.actualizarLibro(libroActualizado);
            if (actualizado) {
                EntradaHistorialDTO entradaHistorialDTO = new EntradaHistorialDTO(libroEcontrado.getIsbn(),
                        libroEcontrado.getTitulo(),
                        cantidadEntrada,
                        fechaHoraActual
                );
                ControlNavegacion.getInstase().agregarEntradaAlHistorial(entradaHistorialDTO);
                JOptionPane.showMessageDialog(this,
                        "Entrada de " + cantidadEntrada + " unidades registrada para el libro: '" + libroEcontrado.getTitulo() + "'.\n"
                        + "Nuevo stock: " + nuevoStock,
                        "Entrada Registrada",
                        JOptionPane.INFORMATION_MESSAGE);

                limpiarCamposParaNuevaBusqueda();

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error de persistencia al actualizar el libro: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarPrimeraBusqueda();


    }//GEN-LAST:event_btnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(GUIRegistrarEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIRegistrarEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIRegistrarEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIRegistrarEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new GUIRegistrarEntrada().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscarIsbn;
    private javax.swing.JComboBox<String> CmbOpciones;
    private javax.swing.JLabel LblLogo;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarPortada;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbBoxCategoria;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelAgregarPortada;
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
    private javax.swing.JLabel lblStock;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtCantidadEntrada;
    private javax.swing.JTextField txtFechaEntrada;
    private javax.swing.JTextField txtFldAutor;
    private javax.swing.JTextField txtFldEditorial;
    private javax.swing.JTextField txtFldFechaLanzamiento;
    private javax.swing.JTextField txtFldISBN;
    private javax.swing.JTextField txtFldNombreProducto;
    private javax.swing.JTextField txtFldNumPaginas;
    private javax.swing.JTextField txtFldPrecio;
    private javax.swing.JTextField txtFldStock;
    private javax.swing.JTextField txtHoraEntrada;
    private javax.swing.JTextField txtIsbn;
    private javax.swing.JTextField txtNotasAdicionales;
    private javax.swing.JTextField txtStockActual;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
