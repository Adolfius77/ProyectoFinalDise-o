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
public class GUIDetallesLibroEntradas extends javax.swing.JFrame {

    private LibroDTO libroActual;

    /**
     * Creates new form GUIDetallesLibro
     */
    public GUIDetallesLibroEntradas(LibroDTO libro) {
        this.libroActual = libro;
        initComponents();
        configurarNavegacion();
        setLocationRelativeTo(this);
        cargarDetallesLibro();
    }

    public GUIDetallesLibroEntradas() {
        this(null);
    }

    private void configurarNavegacion() {
        final ControlNavegacion navegador = ControlNavegacion.getInstase();

        if (BtnInicio1 != null) {
            BtnInicio1.addActionListener(evt -> navegador.navegarAdminGui(this));
        }

        if (BtnRegresarHistorial != null) {
            BtnRegresarHistorial.addActionListener(evt -> navegador.navegarHistorialEntradas(this));
        }
        if (CMBOpciones1 != null) {
            CMBOpciones1.addActionListener(evt -> manejarAccionOpciones());
        }

    }

    private void manejarAccionOpciones() {
        String seleccion = (String) CMBOpciones1.getSelectedItem();
        if (seleccion == null || "Opciones".equals(seleccion) || CMBOpciones1.getSelectedIndex() == 0) {
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
        CMBOpciones1.setSelectedIndex(0); // Resetear
    }

    private void cargarDetallesLibro() {
        if (libroActual != null) {
          
            System.out.println("--- DEBUG: GUIDetallesLibroEntradas ---");
            System.out.println("Libro Titulo: " + libroActual.getTitulo());
            System.out.println("Sinopsis obtenida: [" + libroActual.getSinopsis() + "]");
            System.out.println("¿Es Sinopsis null? " + (libroActual.getSinopsis() == null));
            if (libroActual.getSinopsis() != null) {
                System.out.println("¿Es Sinopsis empty? " + libroActual.getSinopsis().isEmpty());
            }
            System.out.println("------------------------------------");

            if (lblTituloLibro != null) {
                lblTituloLibro.setText(libroActual.getTitulo());
            }
            if (lblTituloLibro1 != null) {
                lblTituloLibro1.setText(libroActual.getTitulo());
            }
            if (lblAutorLibro != null) {
                lblAutorLibro.setText("Autor: " + libroActual.getAutor());
            }
            if (lblIsbnLibro != null) {
                lblIsbnLibro.setText("ISBN: " + libroActual.getIsbn());
            }
           
            if (lblCategoriaLibro != null) {
                lblCategoriaLibro.setText("Categoría: " + libroActual.getCategoria());
            }
            if (lblNumPaginasLibro != null) {
                lblNumPaginasLibro.setText("Páginas: " + String.valueOf(libroActual.getNumPaginas()));
            }
            if (lblPrecioLibro != null) {
                lblPrecioLibro.setText(String.format("Precio: $%.2f", libroActual.getPrecio()));
            }
            if (lblStockLibro != null) {
                lblStockLibro.setText("Disponibles: " + String.valueOf(libroActual.getCantidad()));
            }

            if (txtAreaSinopsis != null) {
              
                if (libroActual.getSinopsis() != null && !libroActual.getSinopsis().isEmpty() && !libroActual.getSinopsis().equals("Sinopsis no disponible.")) {
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
                        System.err.println("Error en GUIDetallesLibroEntradas: URL de imagen no encontrada para: " + libroActual.getRutaImagen());
                    }
                } catch (Exception e) {
                    lblPortadaLibro.setText("Error al cargar portada");
                    lblPortadaLibro.setIcon(null);
                    System.err.println("Error en GUIDetallesLibroEntradas al cargar imagen: " + libroActual.getRutaImagen());
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
            // Manejo si libroActual es null
            if (lblTituloLibro != null) {
                lblTituloLibro.setText("Detalles del Libro no disponibles");
            }
            if (lblTituloLibro1 != null) {
                lblTituloLibro1.setText("Detalles del Libro no disponibles");
            }
            if (lblAutorLibro != null) {
                lblAutorLibro.setText("Autor: N/A");
            }
            if (lblIsbnLibro != null) {
                lblIsbnLibro.setText("ISBN: N/A");
            }
            if (lblCategoriaLibro != null) {
                lblCategoriaLibro.setText("Categoría: N/A");
            }
            if (lblNumPaginasLibro != null) {
                lblNumPaginasLibro.setText("Páginas: N/A");
            }
            if (lblPrecioLibro != null) {
                lblPrecioLibro.setText("Precio: N/A");
            }
            if (lblStockLibro != null) {
                lblStockLibro.setText("Disponibles: N/A");
            }
            if (txtAreaSinopsis != null) {
                txtAreaSinopsis.setText("No se ha seleccionado ningún libro para mostrar detalles.");
            }
            if (lblPortadaLibro != null) {
                lblPortadaLibro.setText("Sin portada");
                lblPortadaLibro.setIcon(null);
            }
            if (lblFechaLanzamientoLibro != null) {
                lblFechaLanzamientoLibro.setText("Publicado: N/A");
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

        BtnVerReseñas = new javax.swing.JButton();
        CMBCategorias = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        BtnInicio1 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        CMBOpciones1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaSinopsis = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblTituloLibro1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblTituloLibro = new javax.swing.JLabel();
        lblAutorLibro = new javax.swing.JLabel();
        lblCategoriaLibro = new javax.swing.JLabel();
        lblNumPaginasLibro = new javax.swing.JLabel();
        lblPrecioLibro = new javax.swing.JLabel();
        lblStockLibro = new javax.swing.JLabel();
        lblIsbnLibro = new javax.swing.JLabel();
        lblFechaLanzamientoLibro = new javax.swing.JLabel();
        lblPortadaLibro = new javax.swing.JLabel();
        BtnRegresarHistorial = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        BtnInicio2 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        CMBOpciones2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        BtnVerReseñas.setBackground(new java.awt.Color(101, 85, 143));
        BtnVerReseñas.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        BtnVerReseñas.setForeground(new java.awt.Color(255, 255, 255));
        BtnVerReseñas.setText("Ver Reseñas");
        BtnVerReseñas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVerReseñasActionPerformed(evt);
            }
        });

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

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 0, 25)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CATEGORIAS");

        jPanel5.setBackground(new java.awt.Color(101, 85, 143));

        BtnInicio1.setBackground(new java.awt.Color(101, 85, 143));
        BtnInicio1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/inicio.png"))); // NOI18N
        BtnInicio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInicio1ActionPerformed(evt);
            }
        });

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/list.png"))); // NOI18N

        CMBOpciones1.setFont(new java.awt.Font("Segoe UI Black", 0, 16)); // NOI18N
        CMBOpciones1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Opciones", "Gestion de Libros", "Registrar Entrada", "Ver Historial entrada", "Cambiar Contraseña", "ver historial de ventas", "Gestion de Clientes", "Cerrar Sesion" }));
        CMBOpciones1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        CMBOpciones1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CMBOpciones1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMBOpciones1ActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOG.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel7.setText("ADMINISTRADOR");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel7)
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel7)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(BtnInicio1)
                .addGap(38, 38, 38)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CMBOpciones1, 0, 186, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(BtnInicio1)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CMBOpciones1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 15, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(217, 202, 218));

        txtAreaSinopsis.setColumns(20);
        txtAreaSinopsis.setRows(5);
        jScrollPane1.setViewportView(txtAreaSinopsis);

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel2.setText("Sinopsis");
        jLabel2.setToolTipText("");

        jPanel3.setBackground(new java.awt.Color(217, 202, 218));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 79, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setText("Detalles del libro:");

        lblTituloLibro1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTituloLibro1.setText("Nombre Libro");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        lblTituloLibro.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lblTituloLibro.setText("Nombre Libro");

        lblAutorLibro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblAutorLibro.setText("Autor/a");

        lblCategoriaLibro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCategoriaLibro.setText("Categoria");

        lblNumPaginasLibro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblNumPaginasLibro.setText("Paginas");

        lblPrecioLibro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblPrecioLibro.setText("Precio");

        lblStockLibro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblStockLibro.setText("Stock");

        lblIsbnLibro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIsbnLibro.setText("ISBN");

        lblFechaLanzamientoLibro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblFechaLanzamientoLibro.setText("Publicacion");

        lblPortadaLibro.setText("Imagen");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaLanzamientoLibro)
                    .addComponent(lblIsbnLibro)
                    .addComponent(lblStockLibro)
                    .addComponent(lblPrecioLibro)
                    .addComponent(lblNumPaginasLibro)
                    .addComponent(lblCategoriaLibro)
                    .addComponent(lblAutorLibro)
                    .addComponent(lblTituloLibro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(lblPortadaLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblTituloLibro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAutorLibro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCategoriaLibro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNumPaginasLibro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPrecioLibro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblStockLibro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIsbnLibro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFechaLanzamientoLibro))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblPortadaLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        BtnRegresarHistorial.setBackground(new java.awt.Color(101, 85, 143));
        BtnRegresarHistorial.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        BtnRegresarHistorial.setForeground(new java.awt.Color(255, 255, 255));
        BtnRegresarHistorial.setText("Regresar a Historial");
        BtnRegresarHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegresarHistorialActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(101, 85, 143));

        BtnInicio2.setBackground(new java.awt.Color(101, 85, 143));
        BtnInicio2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/inicio.png"))); // NOI18N
        BtnInicio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInicio2ActionPerformed(evt);
            }
        });

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/list.png"))); // NOI18N

        CMBOpciones2.setFont(new java.awt.Font("Segoe UI Black", 0, 16)); // NOI18N
        CMBOpciones2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Opciones", "Gestion de Libros", "Registrar Entrada", "Ver Historial entrada", "Gestion de Clientes", "Cerrar Sesion" }));
        CMBOpciones2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        CMBOpciones2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CMBOpciones2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMBOpciones2ActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOG.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel8.setText("ADMINISTRADOR");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel8)
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel8)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(BtnInicio2)
                .addGap(38, 38, 38)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel23)
                .addGap(18, 18, 18)
                .addComponent(CMBOpciones2, 0, 240, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(BtnInicio2)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23)
                            .addComponent(CMBOpciones2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 15, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(BtnRegresarHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTituloLibro1))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblTituloLibro1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnRegresarHistorial))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void CMBCategoriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CMBCategoriasItemStateChanged

    }//GEN-LAST:event_CMBCategoriasItemStateChanged

    private void CMBCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBCategoriasActionPerformed
        ControlNavegacion.getInstase().navegarCategorias(this);
    }//GEN-LAST:event_CMBCategoriasActionPerformed

    // Función que hace que te regreses a la categoría en la que estabas
    private void BtnRegresarHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegresarHistorialActionPerformed

    }//GEN-LAST:event_BtnRegresarHistorialActionPerformed

    private void BtnVerReseñasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVerReseñasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnVerReseñasActionPerformed

    private void BtnInicio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInicio1ActionPerformed

    }//GEN-LAST:event_BtnInicio1ActionPerformed

    private void CMBOpciones1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBOpciones1ActionPerformed

    }//GEN-LAST:event_CMBOpciones1ActionPerformed

    private void BtnInicio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInicio2ActionPerformed

    }//GEN-LAST:event_BtnInicio2ActionPerformed

    private void CMBOpciones2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBOpciones2ActionPerformed

    }//GEN-LAST:event_CMBOpciones2ActionPerformed

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
            java.util.logging.Logger.getLogger(GUIDetallesLibroEntradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIDetallesLibroEntradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIDetallesLibroEntradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIDetallesLibroEntradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
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
    private javax.swing.JButton BtnInicio1;
    private javax.swing.JButton BtnInicio2;
    private javax.swing.JButton BtnRegresarHistorial;
    private javax.swing.JButton BtnVerReseñas;
    private javax.swing.JComboBox<String> CMBCategorias;
    private javax.swing.JComboBox<String> CMBOpciones1;
    private javax.swing.JComboBox<String> CMBOpciones2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
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
