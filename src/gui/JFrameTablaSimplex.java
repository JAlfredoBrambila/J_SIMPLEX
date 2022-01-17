/**
 * Implementation of the Simplex Method 
 * @version 0.9.1
 * @author José Alfredo Brambila Hernández <alfredo.brambila@outlook.com>
 * 
 * SOFTWARE LICENCE
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
 * and associated documentation files (the "Software"), to deal in the Software without restriction, 
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, 
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or 
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */


package gui;

import domain.GUILoader;
import domain.SimplexTable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author al_x3
 */
public class JFrameTablaSimplex extends javax.swing.JFrame {

    SimplexTable simplexModel;
    double[][] simplexTable;
    String[] encabezadosTabla;
    Object[][] simplexValuesObj;
    /**
     * Creates new form JFrameTablaSimplex
     */
    
    public JFrameTablaSimplex() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    
    
    public JFrameTablaSimplex(String file) {
        
        
                
        initComponents();
        simplexModel = new SimplexTable();
        
        simplexModel.simplexFromFile(file);
        llenarTabla();
        /*simplexTable = simplexModel.getSimplexTableMatrix();
        encabezadosTabla = new String[simplexTable[0].length];
        
        encabezadosTabla[0] = "i";
        for(int i=1; i<simplexTable[0].length; i++) {
            encabezadosTabla[i] = "P"+(i-1);
        }
        
        /*for(int i=0; i<simplexTable[0].length; i++) {
            System.out.println(encabezadosTabla[i]);
        }*/
        simplexValuesObj = new Object[simplexTable.length][simplexTable[0].length];
        for(int i=0; i<simplexTable.length; i++) {
            for(int j=0; j<simplexTable[0].length; j++) {
                simplexValuesObj[i][j] = simplexTable[i][j];
            }
        }
        /*
        jTableSimplexTable.setModel(new javax.swing.table.DefaultTableModel(
            simplexValuesObj, encabezadosTabla ));
        this.jLabelResultados.setText(simplexModel.getValoresResultado());
        */
        setLocationRelativeTo(null);
    }
    
    public JFrameTablaSimplex(GUILoader g) {
        
        /*SimplexTable simplexModel = new SimplexTable();
        double[][] simplexTable;
        String[] encabezadosTabla;
        Object[][] simplexValuesObj;*/
                
        initComponents();
        simplexModel = new SimplexTable();
        
        simplexModel.simplexFromGUI(g);
        llenarTabla();
        
        setLocationRelativeTo(null);
    }
    
    public void llenarTabla() {
        simplexTable = simplexModel.getSimplexTableMatrix();
        encabezadosTabla = new String[simplexTable[0].length];
        
        encabezadosTabla[0] = "Xi";
        for(int i=1; i<simplexTable[0].length; i++) {
            encabezadosTabla[i] = "P"+(i-1);
        }
        
        /*for(int i=0; i<simplexTable[0].length; i++) {
            System.out.println(encabezadosTabla[i]);
        }*/
        simplexValuesObj = new Object[simplexTable.length][simplexTable[0].length];
        for(int i=0; i<simplexTable.length; i++) {
            for(int j=0; j<simplexTable[0].length; j++) {
                simplexValuesObj[i][j] = simplexTable[i][j];
            }
        }
        
        jTableSimplexTable.setModel(new javax.swing.table.DefaultTableModel(
            simplexValuesObj, encabezadosTabla ));
        this.jLabelResultados.setText(simplexModel.getValoresResultado());
        
        JTableHeader header = jTableSimplexTable.getTableHeader();
        DefaultTableCellRenderer render = (DefaultTableCellRenderer) jTableSimplexTable.getTableHeader().getDefaultRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        header.setDefaultRenderer(render);
        
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        //table.getColumnModel().getColumn(column).setCellRe nderer(tcr);
        for(int i=0; i<jTableSimplexTable.getColumnCount(); i++) {
            
            jTableSimplexTable.getColumnModel().getColumn(i).setCellRenderer(tcr);
            jTableSimplexTable.getColumnModel().getColumn(i).setPreferredWidth(10);
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
        jLabel3 = new javax.swing.JLabel();
        jPanelResultados = new javax.swing.JPanel();
        jLabelResLBL = new javax.swing.JLabel();
        jLabelResultados = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonPasos = new javax.swing.JButton();
        jButtonResolver = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSimplexTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Método Simplex");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Método SIMPLEX");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanelResultados.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel resultados"));

        jLabelResLBL.setText("Resultados:");

        jLabelResultados.setBackground(new java.awt.Color(255, 255, 255));
        jLabelResultados.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanelResultadosLayout = new javax.swing.GroupLayout(jPanelResultados);
        jPanelResultados.setLayout(jPanelResultadosLayout);
        jPanelResultadosLayout.setHorizontalGroup(
            jPanelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResultadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelResLBL)
                .addContainerGap(879, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelResultadosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 862, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelResultadosLayout.setVerticalGroup(
            jPanelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResultadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelResLBL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelResultados, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        jButtonPasos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/support/icos/siguente_16.png"))); // NOI18N
        jButtonPasos.setText("por paso");
        jButtonPasos.setToolTipText("Ver resultados tabla por tabla");
        jButtonPasos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPasosActionPerformed(evt);
            }
        });

        jButtonResolver.setBackground(new java.awt.Color(0, 204, 255));
        jButtonResolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/support/icos/play_16.png"))); // NOI18N
        jButtonResolver.setText("Solución");
        jButtonResolver.setToolTipText("Ver solución directa");
        jButtonResolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(706, Short.MAX_VALUE)
                .addComponent(jButtonPasos, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonResolver, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPasos)
                    .addComponent(jButtonResolver))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabla Simplex"));

        jTableSimplexTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"No data"}
            },
            new String [] {
                "Tabla"
            }
        ));
        jScrollPane1.setViewportView(jTableSimplexTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonResolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResolverActionPerformed
        // TODO add your handling code here:
        simplexModel.resolver();
        llenarTabla();
    }//GEN-LAST:event_jButtonResolverActionPerformed

    private void jButtonPasosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPasosActionPerformed
        // TODO add your handling code here:
        if(simplexModel.pasoAPaso()) {
            this.jButtonPasos.setEnabled(false);
            llenarTabla();
            JOptionPane.showMessageDialog(null, "Solución encontrada", "Solución", JOptionPane.INFORMATION_MESSAGE);
        } else {
            llenarTabla();
        }
    }//GEN-LAST:event_jButtonPasosActionPerformed

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
            java.util.logging.Logger.getLogger(JFrameTablaSimplex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameTablaSimplex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameTablaSimplex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameTablaSimplex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameTablaSimplex().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPasos;
    private javax.swing.JButton jButtonResolver;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelResLBL;
    private javax.swing.JLabel jLabelResultados;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelResultados;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableSimplexTable;
    // End of variables declaration//GEN-END:variables
}
