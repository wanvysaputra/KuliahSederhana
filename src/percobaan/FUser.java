/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package percobaan;

import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static percobaan.Config.writeLog;
import java.sql.Timestamp;
/**
 *
 * @author wanvy
 */
public class FUser extends javax.swing.JFrame {

    /**
     * Creates new form FUser
     */
    
        private void load_table(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("no");
        model.addColumn("email");
        model.addColumn("password");
        model.addColumn("last_login");
        
        //menampilkan data database kedalam tabel
        try {
            int no=1;
            String sql = "select * from t_user";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[] {no++,res.getString(1),res.getString(2),res.getString(3)});
            }
            tblUser.setModel(model);
            writeLog("Tampilkan data ke Frame "+getClass().getSimpleName());
        } catch (Exception e) {
          writeLog("Data tidak dapat ditampilkan : " + e.getMessage());
        }
    }
    
    private void bersihkan(){
        txtEmail.setText(null);
        txtPassword.setText(null);
        txtEmail.requestFocus();
    }
    
    public FUser() {
        initComponents();
        load_table();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        btTambah = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();
        btClear = new javax.swing.JButton();
        btKembali = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Email");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 30, 37, -1));

        jLabel2.setText("Password");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 64, 60, -1));
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 260, -1));
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 260, -1));

        btTambah.setText("Tambah");
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });
        getContentPane().add(btTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 75, -1));

        btEdit.setText("Edit");
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });
        getContentPane().add(btEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 75, -1));

        btHapus.setText("Hapus");
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });
        getContentPane().add(btHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 75, -1));

        btClear.setText("Clear");
        btClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearActionPerformed(evt);
            }
        });
        getContentPane().add(btClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 80, -1));

        btKembali.setText("Kembali");
        btKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btKembaliActionPerformed(evt);
            }
        });
        getContentPane().add(btKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 160, -1));

        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUser);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 360, 133));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
        // TODO add your handling code here:
        Timestamp tanggal = new Timestamp(System.currentTimeMillis());
        try {
            String sql = "INSERT INTO t_user VALUES ('"+txtEmail.getText()+
                                "','"+txtPassword.getText()+
                                "','"+tanggal+"')";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
            writeLog("Penyimpanan Data Berhasil dengan Email "+txtEmail.getText());
            load_table();
            bersihkan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            writeLog("Data gagal disimpan : " + e.getMessage());
        }
    }//GEN-LAST:event_btTambahActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
        // TODO add your handling code here:
        try {
            if ("".equals(txtEmail.getText())){
                JOptionPane.showMessageDialog(this, "Isikan email terlebih dahulu");
            }else{
                String sql ="UPDATE t_user SET password = '"+ txtPassword.getText()+
                "' WHERE email = '"+txtEmail.getText()+"'";
                java.sql.Connection conn=(Connection)Config.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diperbaharui dengan email"+txtEmail.getText());
                writeLog("Data Berhasil Diperbaharui dengan email "+txtEmail.getText());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
            writeLog("Perubahan Data Gagal : " + e.getMessage());
        }
        load_table();
        bersihkan();
    }//GEN-LAST:event_btEditActionPerformed

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
        // TODO add your handling code here:
        try {
            if ("".equals(txtEmail.getText())){
                JOptionPane.showMessageDialog(this, "Isikan email terlebih dahulu");
            }else{
                String sql ="DELETE FROM t_user WHERE email='"+ txtEmail.getText()+"'";
                java.sql.Connection conn=(Connection)Config.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus dengan email "+txtEmail.getText());
                writeLog("Data Berhasil Dihapus dengan email "+txtEmail.getText());
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            writeLog("Data gagal dihapus : " + e.getMessage());
        }
        load_table();
        bersihkan();
    }//GEN-LAST:event_btHapusActionPerformed

    private void btClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearActionPerformed
        // TODO add your handling code here:
        bersihkan();
    }//GEN-LAST:event_btClearActionPerformed

    private void btKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btKembaliActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btKembaliActionPerformed

    private void tblUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserMouseClicked
        // TODO add your handling code here:
        int baris = tblUser.rowAtPoint(evt.getPoint());
        String email =tblUser.getValueAt(baris, 1).toString();
        String password = tblUser.getValueAt(baris,2).toString();
       
        txtEmail.setText(email);
        txtPassword.setText(password);
        txtEmail.requestFocus();
    }//GEN-LAST:event_tblUserMouseClicked

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
            java.util.logging.Logger.getLogger(FUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btClear;
    private javax.swing.JButton btEdit;
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btKembali;
    private javax.swing.JButton btTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUser;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtPassword;
    // End of variables declaration//GEN-END:variables
}
