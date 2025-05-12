package Presentacion;

import Control.ControlNavegacion;
import DTOS.LibroDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

public class GUICategorias extends javax.swing.JFrame {

    private List<LibroDTO> librosDisponibles;

    public GUICategorias() {
        initComponents();
        setLocationRelativeTo(null);
        librosDisponibles = obtenerLibros();
        configurarNavegacionYCategorias();
    }

    private void configurarNavegacionYCategorias() {
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
        if (CMBCategorias != null) {
            CMBCategorias.addItemListener(evt -> {
                if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                    String seleccion = (String) CMBCategorias.getSelectedItem();
                    if (seleccion != null) {
                        mostrarLibrosPorCategoria(seleccion);
                    }
                }
            });
        }
        // Carga inicial
        String categoriaInicial = (String) CMBCategorias.getSelectedItem();
        if (categoriaInicial != null) {
            mostrarLibrosPorCategoria(categoriaInicial);
        } else if (librosDisponibles != null && !librosDisponibles.isEmpty()) {
            mostrarLibrosPorCategoria(librosDisponibles.get(0).getCategoria());
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
    

    private List<LibroDTO> obtenerLibros() {
        List<LibroDTO> libros = new ArrayList<>();
        Date fecha1 = new GregorianCalendar(2023, 10, 15).getTime();
        Date fecha2 = new GregorianCalendar(2022, 5, 20).getTime();
        Date fecha3 = new GregorianCalendar(2021, 2, 10).getTime();
        Date fecha4 = new GregorianCalendar(2020, 9, 5).getTime();
        Date fecha5 = new GregorianCalendar(2019, 12, 1).getTime();
        Date fecha6 = new GregorianCalendar(2018, 7, 25).getTime();
        Date fecha7 = new GregorianCalendar(2017, 4, 12).getTime();
        Date fecha8 = new GregorianCalendar(2016, 11, 30).getTime();

        libros.add(new LibroDTO("Las pruebas del sol", "Aiden Thomas", "978-6078828463", fecha1, "FANTASIA", 389.00, 47, "/img/LasPruebasDelSol1.jpg"));
        libros.add(new LibroDTO("Los juegos del hambre", "Suzanne Collins", "978-6074001907", fecha2, "FANTASIA", 379.00, 67, "/img/losJuegosDelHambre1.jpg"));
        libros.add(new LibroDTO("Harry Potter y la piedra filosofal", "J. K. Rowling", "978-6073193009", fecha3, "FANTASIA", 3229.00, 23, "/img/harryPotter.jpg"));
        libros.add(new LibroDTO("Splatoon Ikasu Artbook", "FAMITSU", "978-4047336551", fecha4, "FANTASIA", 229.00, 23, "/img/SplatoonArtbook1.jpg"));
        libros.add(new LibroDTO("Divergente", "Veronica Roth", "978-6074009842", fecha5, "FANTASIA", 4229.00, 23, "/img/divergente1.jpg"));
        libros.add(new LibroDTO("Amigo Imaginario", "Stephen Chbosky", "978-6070761515", fecha6, "FANTASIA", 429.00, 2, "/img/amigoImaginario1.jpg"));
        //
        libros.add(new LibroDTO("IT", "Stephen King", "978-6073105521", fecha1, "TERROR", 439.00, 47, "/img/IT.jpg"));
        libros.add(new LibroDTO("El resplandor", "Stephen King", "978-6073118392", fecha2, "TERROR", 379.00, 67, "/img/elResplandor1.jpg"));
        libros.add(new LibroDTO("La chicha de gris", "Antonio Runa", "978-8445014752", fecha3, "TERROR", 3229.00, 23, "/img/laChicaDeGris1.jpg"));
        libros.add(new LibroDTO("Casa de las sombras", "Adam Nevill", "978-8445014882", fecha4, "TERROR", 229.00, 23, "/img/casaSombra.jpg"));
        libros.add(new LibroDTO("Amigo imaginario", "Stephen Chbosky", "978-6070761515", fecha5, "TERROR", 4229.00, 23, "/img/amigoImaginario1.jpg"));
        libros.add(new LibroDTO("El chico de piel de cerdo", "Raiza Revelles", "978-6070772245", fecha6, "TERROR", 429.00, 2, "/img/ElChicoDeLaPielDeCerdo.jpg"));

        //Libros Cocina
        libros.add(new LibroDTO("Stardew Valley Cookbook", "ConcernedApe", "978-1984862051", fecha1, "COCINA", 616.00, 4, "/img/StardewCookbook.jpg"));
        libros.add(new LibroDTO("Oh, My Cookie!", "Noelia Toré", "978-8410442542", fecha2, "COCINA", 529.00, 12, "/img/ohMyCookie.jpg"));
        libros.add(new LibroDTO("Con las manos en la masa madre", "Bernardo Flores Alanís", "978-6072134058", fecha3, "COCINA", 569.00, 43, "/img/masaMadre.jpg"));
        libros.add(new LibroDTO("El gran libro de la reposteria", "Christian Teubner", "978-8424108229", fecha4, "COCINA", 616.00, 50, "/img/ElGranLibroDeLaReposteria.jpg"));
        libros.add(new LibroDTO("Cocinologia: la ciencia de la cocina", "Stuart Farrimond", "978-1465486844", fecha5, "COCINA", 515.00, 37, "/img/cocinologia.jpg"));
        libros.add(new LibroDTO("La ciencia de la pasteleria", "Dario Bressanini", "978-8417127077", fecha6, "COCINA", 900.00, 30, "/img/laCienciaDeLaReposteria.jpg"));

        //Libros Educacion
        libros.add(new LibroDTO("El valor de educar", "Fernando Savater", "978-8434433960", fecha1, "EDUCACION", 691.00, 60, "/img/elValorDeEducar.jpg"));
        libros.add(new LibroDTO("Enseñar a transgredir", "Marta Malo", "978-8412281842", fecha2, "EDUCACION", 489.00, 14, "/img/enseñarTransgredir.jpg"));
        libros.add(new LibroDTO("Educar en la naturaleza", "Katia Hueso", "978-8418285936", fecha3, "EDUCACION", 719.00, 19, "/img/educarNaturaleza.jpg"));
        libros.add(new LibroDTO("EducaFakes", "Daniel Turienzo", "978-8412878714", fecha4, "EDUCACION", 429.00, 28, "/img/educaFakes.jpg"));
        libros.add(new LibroDTO("El arte de educar jugando", "Silvia Álava", "978-8412334296", fecha5, "EDUCACION", 706.00, 28, "/img/elArteDeEducar.jpg"));
        libros.add(new LibroDTO("Aprendiendo a aprender", "Hector Ruiz Martin", "978-8418045301", fecha6, "EDUCACION", 547.00, 4, "/img/aprendiendoAprender.jpg"));

        return libros;
    }

    private void mostrarLibrosPorCategoria(String categoria) {
        if (PanelDinamico == null) { 
            System.err.println("PanelDinamico no inicializado en GUICategorias");
            return;
        }
        PanelDinamico.removeAll(); // Limpia el panel
        List<LibroDTO> librosFiltrados = filtrarLibrosPorCategoria(categoria);

        // Obtiene la lista COMPARTIDA del carrito desde el Navegador/Controlador
        List<LibroDTO> carritoCompartido = ControlNavegacion.getInstase().getCarrito();

        if (PanelDinamico.getLayout() == null || !(PanelDinamico.getLayout() instanceof java.awt.FlowLayout)) {
            PanelDinamico.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 15)); // Espaciado
        }

        if (librosFiltrados.isEmpty()) {
            PanelDinamico.add(new javax.swing.JLabel("No hay libros en esta categoría."));
        } else {
            for (LibroDTO libro : librosFiltrados) {
                // Crea el panel para este libro, pasando el carrito compartido
                PanelLibro panelito = new PanelLibro(libro, carritoCompartido);

                // *** VERIFICACIÓN SI ESTÁ EN CARRITO ***
                if (carritoCompartido.contains(libro)) {
                    panelito.setAsAddedToCart(); // Llama al método para deshabilitar botón
                }
                // *** FIN VERIFICACIÓN ***

                PanelDinamico.add(panelito); // Añade el panel configurado
            }
        }

        // Actualiza la UI para mostrar los cambios
        PanelDinamico.revalidate();
        PanelDinamico.repaint();

        // Actualiza el título de la categoría
        if (jLabel1 != null) {
            jLabel1.setText(categoria.toUpperCase());
        }
    }

    private List<LibroDTO> filtrarLibrosPorCategoria(String categoria) {
        List<LibroDTO> librosFiltrados = new ArrayList<>();
        for (LibroDTO libro : librosDisponibles) {
            if (libro.getCategoria().equalsIgnoreCase(categoria)) {
                librosFiltrados.add(libro);
            }
        }
        return librosFiltrados;
    }
    public void agregarNuevoLibroo(LibroDTO nuevoLibro){
        if(librosDisponibles != null){
            librosDisponibles.add(nuevoLibro);
            
            String categoriaActual = (String) CMBCategorias.getSelectedItem();
            if(categoriaActual != null){
                mostrarLibrosPorCategoria(categoriaActual);
            }else if(!librosDisponibles.isEmpty()){
                mostrarLibrosPorCategoria(librosDisponibles.get(0).getCategoria());
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

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        BtnInicio = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        CMBCategorias = new javax.swing.JComboBox<>();
        BtnPerfil = new javax.swing.JButton();
        BtnCarrito = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        CMBOpciones = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PanelDesplasamientoLibroFantasia = new javax.swing.JScrollPane();
        PanelDinamico = new javax.swing.JPanel();

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
        CMBOpciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Opciones", "Registrar entrada", "Ver Historial", "Cambiar Contraseña", "Cerrar Sesion" }));
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

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setText("FANTASIA");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(52, 52, 52))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PanelDinamico.setBackground(new java.awt.Color(101, 85, 143));
        PanelDinamico.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PanelDesplasamientoLibroFantasia.setViewportView(PanelDinamico);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PanelDesplasamientoLibroFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 1426, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelDesplasamientoLibroFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInicioActionPerformed
//        GUIINICIO inicio = new GUIINICIO();
//        inicio.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_BtnInicioActionPerformed

    private void CMBCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBCategoriasActionPerformed
        String seleccion = (String) CMBCategorias.getSelectedItem();
        mostrarLibrosPorCategoria(seleccion);

    }//GEN-LAST:event_CMBCategoriasActionPerformed

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

    private void CMBCategoriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CMBCategoriasItemStateChanged
        String seleccion = (String) CMBCategorias.getSelectedItem();
        mostrarLibrosPorCategoria(seleccion);
    }//GEN-LAST:event_CMBCategoriasItemStateChanged

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
            java.util.logging.Logger.getLogger(GUICategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUICategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUICategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUICategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUICategorias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCarrito;
    private javax.swing.JButton BtnInicio;
    private javax.swing.JButton BtnPerfil;
    private javax.swing.JComboBox<String> CMBCategorias;
    private javax.swing.JComboBox<String> CMBOpciones;
    private javax.swing.JScrollPane PanelDesplasamientoLibroFantasia;
    private javax.swing.JPanel PanelDinamico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
