/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import Control.ControlNavegacion;
import DTOS.LibroDTO;
import Negocio.BoProductos;
import Negocio.BuscarPorISBN;
import Negocio.BuscarPorTitulo;
import Negocio.IBuscadorLibro;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author garfi
 */
public class GUIPagInicioGestionLibros extends javax.swing.JFrame {

    /**
     * Creates new form GUIPagPrincipal
     */
    private List<LibroDTO> todosLosLibrosDelInventario;
    private IBuscadorLibro buscadorPorTitulo;
    private IBuscadorLibro buscadorPorISBN;

    public GUIPagInicioGestionLibros() {
        initComponents();
        BoProductos boProductos = new BoProductos();
        this.todosLosLibrosDelInventario = boProductos.obtenerTodosLosLibros();
        this.buscadorPorTitulo = new BuscarPorTitulo();
        this.buscadorPorISBN = new BuscarPorISBN();
        setLocationRelativeTo(null);
        configurarNavegacion();
        cargarLibrosEnPanel(this.todosLosLibrosDelInventario);
    }

    private void ejecutarBusqueda() {
        String criterio = txtFldBuscador.getText().trim();

        if (criterio.isEmpty()) {
            cargarLibrosEnPanel(this.todosLosLibrosDelInventario);
            return;
        }

        if (this.buscadorPorTitulo == null || this.buscadorPorISBN == null) {
            System.err.println("Error: Estrategias de búsqueda no inicializadas.");
            this.buscadorPorTitulo = new BuscarPorTitulo();
            this.buscadorPorISBN = new BuscarPorISBN();
        }

        List<LibroDTO> resultadosPorTitulo = this.buscadorPorTitulo.buscar(criterio, this.todosLosLibrosDelInventario);
        List<LibroDTO> resultadosPorISBN = this.buscadorPorISBN.buscar(criterio, this.todosLosLibrosDelInventario);

        Set<LibroDTO> resultadosCombinadosSet = new LinkedHashSet<>(resultadosPorTitulo);
        resultadosCombinadosSet.addAll(resultadosPorISBN);

        cargarLibrosEnPanel(new ArrayList<>(resultadosCombinadosSet));
    }

    private void generarPdfListaLibros(List<LibroDTO> libros) {
        if (libros == null || libros.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay libros para generar el PDF.", "Lista Vacía", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Reporte PDF de Libros");
        fileChooser.setSelectedFile(new File("ReporteInventarioLibros.pdf"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF (*.pdf)", "pdf"));

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getAbsolutePath().toLowerCase().endsWith(".pdf")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
            }

            com.lowagie.text.Document document = new com.lowagie.text.Document(PageSize.A4.rotate());

            try {
                PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
                document.open();

                // Título del Documento
                Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, java.awt.Color.BLACK);
                Paragraph tituloDoc = new Paragraph("Reporte de Inventario de Libros", titleFont);
                tituloDoc.setAlignment(Element.ALIGN_CENTER);
                tituloDoc.setSpacingAfter(15);
                document.add(tituloDoc);

                // Fecha de Generación
                SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Paragraph fechaGen = new Paragraph("Generado el: " + dateTimeFormat.format(new Date()));
                fechaGen.setAlignment(Element.ALIGN_RIGHT);
                fechaGen.setSpacingAfter(10);
                document.add(fechaGen);

                // Creacion tabla 
                PdfPTable table = new PdfPTable(9);
                table.setWidthPercentage(100);
                float[] columnWidths = {2.5f, 4f, 3f, 2f, 2.5f, 1.8f, 1f, 1.5f, 1f};
                try {
                    table.setWidths(columnWidths);
                } catch (DocumentException ex) {
                    System.err.println("Error al establecer anchos de columna: " + ex.getMessage());
                }

                // Encabezados de la tabla 
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, java.awt.Color.WHITE);
                String[] headers = {
                    "ISBN", "Título", "Autor", "Categoría",
                    "Fecha Lanz.", "Editorial", "Páginas", "Precio", "Stock"
                };
                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(new java.awt.Color(101, 85, 143));
                    cell.setPadding(4);
                    table.addCell(cell);
                }
                table.setHeaderRows(1);

                // Contenido de la tabla
                Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 8);
                SimpleDateFormat sdfParaCelda = new SimpleDateFormat("dd/MM/yy");

                for (LibroDTO libro : libros) {
                    table.addCell(new Phrase(libro.getIsbn() != null ? libro.getIsbn() : "N/A", cellFont));
                    table.addCell(new Phrase(libro.getTitulo() != null ? libro.getTitulo() : "N/A", cellFont));
                    table.addCell(new Phrase(libro.getAutor() != null ? libro.getAutor() : "N/A", cellFont));
                    table.addCell(new Phrase(libro.getCategoria() != null ? libro.getCategoria() : "N/A", cellFont));

                    // Fecha de Lanzamiento
                    String fechaLanzStr = "N/A";
                    if (libro.getFechaLanzamiento() != null) {
                        fechaLanzStr = sdfParaCelda.format(libro.getFechaLanzamiento());
                    }
                    table.addCell(new Phrase(fechaLanzStr, cellFont));

                    table.addCell(new Phrase(libro.getEditorial() != null ? libro.getEditorial() : "N/A", cellFont));
                    table.addCell(new Phrase(String.valueOf(libro.getNumPaginas()), cellFont));
                    table.addCell(new Phrase(String.format("$%.2f", libro.getPrecio()), cellFont));
                    table.addCell(new Phrase(String.valueOf(libro.getCantidad()), cellFont));
                }

                document.add(table);
                document.close();

                JOptionPane.showMessageDialog(this, "PDF generado exitosamente en:\n" + fileToSave.getAbsolutePath(), "PDF Generado", JOptionPane.INFORMATION_MESSAGE);

                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().open(fileToSave);
                    } catch (IOException ex) {
                        System.err.println("No se pudo abrir el PDF automáticamente: " + ex.getMessage());
                    }
                }

            } catch (DocumentException | FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Error al generar el PDF: " + e.getMessage(), "Error de PDF", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    public void cargarLibrosEnPanel(List<LibroDTO> librosAMostrar) {
        if (this.panelLibros == null) {
            System.err.println("Error: panelLibros es null en cargarLibrosEnPanel. Verifica el nombre en el diseñador.");
            return;
        }
        this.panelLibros.removeAll();

        if (librosAMostrar == null || librosAMostrar.isEmpty()) {
            this.panelLibros.add(new javax.swing.JLabel("No se encontraron libros."));
        } else {
            for (LibroDTO libroActual : librosAMostrar) {
                PanelGestionLibros itemPanel = new PanelGestionLibros(libroActual, this);
                this.panelLibros.add(itemPanel);
                this.panelLibros.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 8)));
            }
        }
        this.panelLibros.revalidate();
        this.panelLibros.repaint();
        if (jScrollPane2 != null) {
            jScrollPane2.revalidate();
            jScrollPane2.repaint();
        }
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
        if (btnAgregarLibro != null) {
            btnAgregarLibro.addActionListener(evt -> navegador.navegarAgregarLibro(this));
        }
        if (btnGenerarPDF != null) {
            btnGenerarPDF.addActionListener(evt -> {
                BoProductos boProd = new BoProductos();
                List<LibroDTO> librosActuales = boProd.obtenerTodosLosLibros();
                if (librosActuales.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No hay libros para generar el PDF.", "Lista Vacía", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                generarPdfListaLibros(librosActuales);
            });
        }
        if (btnBuscar != null) {
            btnBuscar.addActionListener(evt -> ejecutarBusqueda());
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
        txtFldBuscador = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        CmbOpciones = new javax.swing.JComboBox<>();
        jPanel24 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        panelLibros = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnAgregarLibro = new javax.swing.JButton();
        btnGenerarPDF = new javax.swing.JButton();

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

        btnBuscar.setBackground(new java.awt.Color(101, 85, 143));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        CmbOpciones.setFont(new java.awt.Font("Segoe UI Black", 0, 16)); // NOI18N
        CmbOpciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Opciones", "Gestion de Libros", "Registrar Entrada", "Ver Historial entrada", "Cambiar Contraseña", "ver historial de ventas", "Cerrar Sesion" }));
        CmbOpciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        CmbOpciones.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CmbOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbOpcionesActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel6.setText("ADMINISTRADOR");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel6)
                .addContainerGap(107, Short.MAX_VALUE))
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
                .addGap(17, 17, 17)
                .addComponent(btnInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(LblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFldBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CmbOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(LblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CmbOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFldBuscador)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );

        jScrollPane2.setBackground(new java.awt.Color(217, 202, 218));

        panelLibros.setBackground(new java.awt.Color(217, 202, 218));
        panelLibros.setLayout(new javax.swing.BoxLayout(panelLibros, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane2.setViewportView(panelLibros);

        jPanel3.setBackground(new java.awt.Color(217, 202, 218));

        btnAgregarLibro.setBackground(new java.awt.Color(93, 47, 96));
        btnAgregarLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        btnAgregarLibro.setToolTipText("");
        btnAgregarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarLibroActionPerformed(evt);
            }
        });

        btnGenerarPDF.setBackground(new java.awt.Color(93, 47, 96));
        btnGenerarPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pdf.png"))); // NOI18N
        btnGenerarPDF.setToolTipText("");
        btnGenerarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPDFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(btnAgregarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGenerarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(343, 343, 343))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilActionPerformed

    }//GEN-LAST:event_btnPerfilActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnGenerarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPDFActionPerformed
        BoProductos boProductos = new BoProductos();
        List<LibroDTO> todosLosLibros = boProductos.obtenerTodosLosLibros();

        if (todosLosLibros.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay libros en el inventario para generar un reporte.", "Inventario Vacío", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        generarPdfListaLibros(todosLosLibros);
    }//GEN-LAST:event_btnGenerarPDFActionPerformed

    private void btnAgregarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarLibroActionPerformed

    }//GEN-LAST:event_btnAgregarLibroActionPerformed

    private void CmbOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbOpcionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbOpcionesActionPerformed

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
            java.util.logging.Logger.getLogger(GUIPagInicioGestionLibros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIPagInicioGestionLibros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIPagInicioGestionLibros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIPagInicioGestionLibros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUIPagInicioGestionLibros().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CmbOpciones;
    private javax.swing.JLabel LblLogo;
    private javax.swing.JButton btnAgregarLibro;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGenerarPDF;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnPerfil;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelLibros;
    private javax.swing.JTextField txtFldBuscador;
    // End of variables declaration//GEN-END:variables
}
