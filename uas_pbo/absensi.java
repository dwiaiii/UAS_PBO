/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_pbo;

import uas_pbo.Koneksi;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import sun.util.logging.PlatformLogger;

public class absensi extends javax.swing.JFrame {

    public absensi() {
        initComponents();

        this.tabel();
        this.textfieldfals();

        ok.setEnabled(false);
        update.setEnabled(false);
        hapus.setEnabled(false);
    }

    public void simpan_data() {

    if (knama.getText().equals("")||ktanggal.getText().equals("") ||kdiv.getSelectedItem().equals("") || kmasuk.getText().equals("") || kpul.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "Tidak Boleh Kosong");
    } else {

        String nama = knama.getText();
        String tanggal = ktanggal.getText();
        String divisi = kdiv.getSelectedItem().toString();
        String masuk = kmasuk.getText();
        String pulang = kpul.getText();

        String ket;

        if (masuk.compareTo("08:00") <= 0) {
            ket = "Tidak Telat";
        } else {
            ket = "Telat";
        }

        kket.setText(ket);

        try {
            Connection con = Koneksi.getConnection();
            Statement stat = con.createStatement();

            int sikat = stat.executeUpdate(
                "INSERT INTO absensi VALUES('"
                + nama + "','"
                + tanggal + "','"
                + divisi + "','"
                + masuk + "','"
                + pulang + "','"
                + ket + "')"
            );

            if (sikat == 1) {
                JOptionPane.showMessageDialog(null, "Berhasil Disimpan");
                tabel();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
    public void updatedata() {
        try {
            Connection con = Koneksi.getConnection();

            PreparedStatement stat = con.prepareStatement(
                    "UPDATE absensi SET nama=?, tanggal=?, divisi=?, masuk=?, pulang=?, ket=? WHERE nama=?"
            );

            stat.setString(1, knama.getText());
            stat.setString(2, ktanggal.getText());
            stat.setString(3, kdiv.getSelectedItem().toString());
            stat.setString(4, kmasuk.getText());
            stat.setString(5, kpul.getText());
            stat.setString(6, kket.getText().toString());
            stat.setString(7, knama.getText());

            stat.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            tabel();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void bersihtext() {
        knama.setText("");
        kdiv.setSelectedItem("");
        kmasuk.setText("");
        kpul.setText("");
        kket.setText("");
    }

    public void tabel() {
        try {
            Connection con = Koneksi.getConnection();
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("select*from absensi");

            String[] kolom = {"Nama", "Tanggal", "Divisi", "Masuk", "Pulang", "Ket"};
            DefaultTableModel tabela = new DefaultTableModel();
            tabela.setColumnIdentifiers(kolom);
            tabel.setModel(tabela);

            while (rs.next()) {
                tabela.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void delete_data() {
    int OK = JOptionPane.showConfirmDialog(
            null,
            "Apakah Anda Yakin Ingin Menghapus Data Ini?",
            "Konfirmasi",
            JOptionPane.YES_NO_OPTION);

    if (OK == JOptionPane.YES_OPTION) {
        try {
            Connection con = Koneksi.getConnection();

            PreparedStatement stat = con.prepareStatement(
                "DELETE FROM absensi WHERE nama=?"
            );

            stat.setString(1, knama.getText());

            stat.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");

            bersihtext();
            tabel();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}

    public void textfieldfals() {
        knama.setEnabled(false);
        kdiv.setEnabled(false);
        kmasuk.setEnabled(false);
        kpul.setEnabled(false);
        kket.setEnabled(false);
    }

    public void textfieldtrue() {
        knama.setEnabled(true);
        kdiv.setEnabled(true);
        kmasuk.setEnabled(true);
        kpul.setEnabled(true);
        kket.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        judul = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ok = new javax.swing.JButton();
        update = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        knama = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        kdiv = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        kmasuk = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        hapus = new javax.swing.JButton();
        NEW = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        kpul = new javax.swing.JTextField();
        kket = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ktanggal = new javax.swing.JTextField();

        jTextField2.setText("jTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(51, 51, 255));

        judul.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        judul.setForeground(new java.awt.Color(0, 0, 102));
        judul.setText("REKAP ABSENSI KARYAWAN PT UNDIRA");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Nama           :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Divisi           :");

        ok.setText("OK");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        update.setText("UPDATE");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        exit.setText("EXIT");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        knama.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        knama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                knamaActionPerformed(evt);
            }
        });

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nama", "Tanggal", "Divisi", "Masuk", "Pulang", "Keterangan"
            }
        ));
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel);

        kdiv.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        kdiv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HR", "Finance & Accounting", "Operasional", "IT", "Legal" }));
        kdiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kdivActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Masuk         :");

        kmasuk.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        kmasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kmasukActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Keterangan  :");

        hapus.setText("HAPUS");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        NEW.setText("NEW");
        NEW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NEWActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Pulang         :");

        kpul.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        kket.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        kket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kketActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Tanggal       :");

        ktanggal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ktanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ktanggalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(NEW)
                        .addGap(26, 26, 26)
                        .addComponent(ok)
                        .addGap(26, 26, 26)
                        .addComponent(update)
                        .addGap(30, 30, 30)
                        .addComponent(hapus)
                        .addGap(27, 27, 27)
                        .addComponent(exit))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(1, 1, 1)))
                            .addComponent(jLabel6))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(kdiv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(kmasuk)
                            .addComponent(kpul)
                            .addComponent(kket, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(knama)
                            .addComponent(ktanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(judul, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(judul, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(knama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ktanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kdiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kmasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kpul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ok)
                    .addComponent(update)
                    .addComponent(exit)
                    .addComponent(hapus)
                    .addComponent(NEW))
                .addGap(48, 48, 48)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void knamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_knamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_knamaActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        this.updatedata();
    }//GEN-LAST:event_updateActionPerformed

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        this.simpan_data();
    }//GEN-LAST:event_okActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        new login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_exitActionPerformed

    private void kdivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kdivActionPerformed
        kdiv.addItem("HR");
        kdiv.addItem("Finance & Accounting");
        kdiv.addItem("Operasional");
        kdiv.addItem("IT");
        kdiv.addItem("Legal");

    }//GEN-LAST:event_kdivActionPerformed

    private void kmasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kmasukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kmasukActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        this.delete_data();
    }//GEN-LAST:event_hapusActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        int baris = tabel.getSelectedRow();
        knama.setText(String.valueOf(tabel.getValueAt(baris, 0)));
        ktanggal.setText(String.valueOf(tabel.getValueAt(baris, 1)));
        kdiv.setSelectedItem(String.valueOf(tabel.getValueAt(baris, 2)));
        kmasuk.setText(String.valueOf(tabel.getValueAt(baris, 3)));
        kpul.setText(String.valueOf(tabel.getValueAt(baris, 4)));
        kket.setText(String.valueOf(tabel.getValueAt(baris, 5)));
        textfieldtrue();
        ok.setEnabled(true);
        update.setEnabled(true);
        hapus.setEnabled(true);


    }//GEN-LAST:event_tabelMouseClicked

    private void NEWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NEWActionPerformed
        this.textfieldtrue();
        this.bersihtext();
        update.setEnabled(false);
        ok.setEnabled(true);
        hapus.setEnabled(false);
    }//GEN-LAST:event_NEWActionPerformed

    private void kketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kketActionPerformed
        
    }//GEN-LAST:event_kketActionPerformed

    private void ktanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ktanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ktanggalActionPerformed

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
            java.util.logging.Logger.getLogger(absensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(absensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(absensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(absensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new absensi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton NEW;
    private javax.swing.JButton exit;
    private javax.swing.JButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel judul;
    private javax.swing.JComboBox<String> kdiv;
    private javax.swing.JTextField kket;
    private javax.swing.JTextField kmasuk;
    private javax.swing.JTextField knama;
    private javax.swing.JTextField kpul;
    private javax.swing.JTextField ktanggal;
    private javax.swing.JButton ok;
    private javax.swing.JTable tabel;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
