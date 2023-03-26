/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Model.InventoryLog;
import Model.Medication;
import Service.InventoryLogService;
import Service.MedicationService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.print.SimpleDoc;
import javax.print.attribute.standard.Media;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class XuatNhapJFrame extends javax.swing.JFrame {

    private InventoryLogService inventoryLogService;
    private MedicationService medicationService;

    /**
     * Creates new form XuatNhapJFrame
     */
    public XuatNhapJFrame() {
        initComponents();
        inventoryLogService = new InventoryLogService();
        loadTable();
        clearForm();
        medicationService = new MedicationService();
    }

    private void loadTable() {
        DefaultTableModel dtm = (DefaultTableModel) tblNhapXuat.getModel();
        dtm.setRowCount(0);
        for (InventoryLog inventoryLog : inventoryLogService.getAll()) {
            Object[] rowData = {
                inventoryLog.getMedication().getMedicationCode(),
                inventoryLog.getFormId(),
                inventoryLog.getTypeForm() == 1 ? "Import" : "Export",
                inventoryLog.getCreatedDate(),
                inventoryLog.getQuantity(),
                inventoryLog.getMedication().getQuantity(),};
            dtm.addRow(rowData);
        }

    }

    private void clearForm() {
        txtMaPhieu.setText("");
        txtMaSP.setText("");
        txtSoLuong.setText("");
        txtNgay.setText("");
        rdoNhap.setSelected(true);
    }

    private boolean checkSo(String text) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(text);
        return m.matches();
    }

    private boolean checkMaThuoc(String text) {
        for (Medication medication : medicationService.getAll()) {
            if (medication.getMedicationCode().equals(text)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkTonTai(String maPhieu, Integer idSP) {
        int idThuoc = inventoryLogService.getIdThuoc(txtMaPhieu.getText().trim());

        for (InventoryLog inventoryLog : inventoryLogService.getAll()) {
            if (inventoryLog.getFormId().equals(maPhieu) && idThuoc == idSP) {
                return true;
            }
        }
        return false;
    }

    private boolean checkMaSP(String text) {
        Pattern p = Pattern.compile("^M\\d+$");
        Matcher m = p.matcher(text);
        return m.matches();
    }

    private boolean checkMaPhieu(String text) {
        Pattern p = Pattern.compile("^P\\d+$");
        Matcher m = p.matcher(text);
        return m.matches();
    }

    private InventoryLog getFormData() {
        String maSP = txtMaSP.getText().trim();
        String maPhieu = txtMaPhieu.getText().trim();
        String soLuong = txtSoLuong.getText().trim();
        String ngayTao = txtNgay.getText().trim();
        Integer loaiPhieu = rdoNhap.isSelected() ? 1 : rdoNhap.isSelected() ? 0 : -1;
        Medication medication = medicationService.getAllByMa(maSP);
        LocalDate localDate = LocalDate.now();
        Date ngay = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (maSP.length() == 0 || maPhieu.length() == 0 || soLuong.length() == 0 || ngayTao.length() == 0 || loaiPhieu == null) {
            JOptionPane.showMessageDialog(this, "Moi ban nhap day du thong tin");
            return null;
        }
        if (!checkMaSP(maSP)) {
            JOptionPane.showMessageDialog(this, "Mã thuoc bắt đầu 'M' + số");
            return null;
        }
        if (!checkMaPhieu(maPhieu)) {
            JOptionPane.showMessageDialog(this, "Mã phiếu bắt đầu 'P' + số");
            return null;
        }
//        Calendar c = Calendar.getInstance();
//        int thang = c.get(Calendar.MONTH) + 1;
//        int nam = c.get(Calendar.YEAR);
//        int ngay1 = c.get(Calendar.DAY_OF_MONTH);
        try {
            ngay = sdf.parse(ngayTao);
            if (((ngay.getYear() + 1900) > localDate.getYear())
                    || ((ngay.getMonth() + 1) > localDate.getMonthValue() && (ngay.getYear() + 1900) == localDate.getYear())
                    || (ngay.getDate() > localDate.getDayOfMonth() && (ngay.getMonth() + 1) == localDate.getMonthValue() && (ngay.getYear() + 1900) == localDate.getYear())) {
                JOptionPane.showMessageDialog(this, "Ngay tao phải < ngay hien tai");
                return null;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Sai dinh dang ngay");
            return null;
        }

        if (!checkSo(soLuong)) {
            JOptionPane.showMessageDialog(this, "So luong phai nguyen duong");
            return null;
        }
        if (!checkMaThuoc(maSP)) {
            JOptionPane.showMessageDialog(this, "Ma thuoc ko ton tai");
            return null;
        }

        InventoryLog inventoryLog = new InventoryLog(maPhieu, loaiPhieu, Integer.parseInt(soLuong), ngay, medication);
        return inventoryLog;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtMaPhieu = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNgay = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rdoNhap = new javax.swing.JRadioButton();
        rdoXuat = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhapXuat = new javax.swing.JTable();
        btnThoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 0));
        jLabel1.setText("NHẬP XUẤT");

        jLabel3.setText("MedicationCode");

        jLabel4.setText("FormId");

        jLabel5.setText("Quantity");

        jLabel6.setText("CreatedDate");

        jLabel7.setText("TypeForm");

        buttonGroup1.add(rdoNhap);
        rdoNhap.setText("Import");

        buttonGroup1.add(rdoXuat);
        rdoXuat.setText("Export");

        btnThem.setText("Insert");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setText("Delete");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setText("Update");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        tblNhapXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MedicationCode", "FormId", "TypeForm", "CreatedDate", "Quantity", "MedicationQuantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhapXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhapXuatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhapXuat);

        btnThoat.setText("Exit");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdoNhap)
                        .addGap(18, 18, 18)
                        .addComponent(rdoXuat)
                        .addGap(109, 109, 109))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(117, 117, 117))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btnSua)
                        .addGap(18, 18, 18)
                        .addComponent(btnThem)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa)
                        .addGap(18, 18, 18)
                        .addComponent(btnThoat)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(rdoNhap)
                            .addComponent(rdoXuat))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnXoa)
                            .addComponent(btnSua)
                            .addComponent(btnThoat))))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblNhapXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhapXuatMouseClicked
        // TODO add your handling code here:
        int row = tblNhapXuat.getSelectedRow();
        if (row == -1) {
            return;
        }
        String maSP = (String) tblNhapXuat.getValueAt(row, 0);
        String maPhieu = (String) tblNhapXuat.getValueAt(row, 1);
        String loaiPhieu = (String) tblNhapXuat.getValueAt(row, 2);
        Date ngayTao = (Date) tblNhapXuat.getValueAt(row, 3);
        Integer soLuong = (Integer) tblNhapXuat.getValueAt(row, 4);

        txtMaSP.setText(maSP);
        txtMaPhieu.setText(maPhieu);
        txtNgay.setText(ngayTao.toString());
        txtSoLuong.setText(soLuong.toString());
        if (loaiPhieu.equals("Import")) {
            rdoNhap.setSelected(true);
        } else {
            rdoXuat.setSelected(true);

        }
    }//GEN-LAST:event_tblNhapXuatMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:      
        InventoryLog inventoryLog = getFormData();
        if (inventoryLog == null) {
            return;
        }
        Integer updateQuantity = 0;
        String maSP = txtMaSP.getText().trim();
        Integer idSP = medicationService.getID(maSP);
        String soLuong = txtSoLuong.getText().trim();
        Integer loaiPhieu = rdoNhap.isSelected() ? 1 : 0;
        int ketQua = inventoryLogService.insert(inventoryLog);
        int so = Integer.parseInt(soLuong);
        int soLuongTon = medicationService.getSoLuong(maSP);
//        int idThuoc = inventoryLogService.getIdThuoc(txtMaPhieu.getText().trim());
//        int dem = inventoryLogService.kiemTraID(idThuoc, txtMaPhieu.getText().trim());
//        InventoryLog nx = inventoryLogService.getAllByID(idThuoc, txtMaPhieu.getText().trim());
//        if (nx != null) {
//            JOptionPane.showMessageDialog(this, "Id da ton tai");
//            return;
//        }

//        if (dem == 1) {
//            JOptionPane.showMessageDialog(this, "Id da ton tai");
//            return;
//        }
//        if (dem == 0) {
//            JOptionPane.showMessageDialog(this, "Id ko ton tai");
//            return;
//        }
        if (ketQua == 0) {
            JOptionPane.showMessageDialog(this, "Id da ton tai");
            return;
        } else if (ketQua > 0) {
            if (loaiPhieu == 1) {
                updateQuantity = medicationService.addQuantity(idSP, Integer.parseInt(soLuong));
            } else {
                if (so > soLuongTon) {
                    JOptionPane.showMessageDialog(this, "So luong xuất phai < so luong ton");
                    return;
                } else {
                    updateQuantity = medicationService.minusQuantity(idSP, Integer.parseInt(soLuong));
                }
            }
            JOptionPane.showMessageDialog(this, "Thanh cong");
        } else {
            JOptionPane.showMessageDialog(this, "That bai");
        }
        clearForm();
        loadTable();

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int row = tblNhapXuat.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Moi ban chon 1 dong can sua");
            return;
        }
        InventoryLog inventoryLog = getFormData();
        if (inventoryLog == null) {
            return;
        }
        Integer updateQuantity = 0;
        String maSP = (String) tblNhapXuat.getValueAt(row, 0);
        String maPhieu = (String) tblNhapXuat.getValueAt(row, 1);
        Integer idSP = medicationService.getID(maSP);
        String soLuong = txtSoLuong.getText().trim();
        Integer loaiPhieu = rdoNhap.isSelected() ? 1 : 0;
        int ketQua = inventoryLogService.update(idSP, maPhieu, inventoryLog);
        int so = Integer.parseInt(soLuong);
        int soLuongTon = medicationService.getSoLuong(maSP);
//        int idThuoc = inventoryLogService.getIdThuoc(txtMaPhieu.getText().trim());
//        int dem = inventoryLogService.kiemTraID(idThuoc, txtMaPhieu.getText().trim());
//        if (dem == 0) {
//            JOptionPane.showMessageDialog(this, "Id ko ton tai");
//            return;
//        }
        if (ketQua == 0) {
            JOptionPane.showMessageDialog(this, "Id ko ton tai");
            return;
        } else if (ketQua > 0) {
            if (loaiPhieu == 1) {
                updateQuantity = medicationService.addQuantity(idSP, Integer.parseInt(soLuong));
            } else {
                if (so > soLuongTon) {
                    JOptionPane.showMessageDialog(this, "So luong xuất phai < so luong ton");
                    return;
                } else {
                    updateQuantity = medicationService.minusQuantity(idSP, Integer.parseInt(soLuong));
                }
            }
            JOptionPane.showMessageDialog(this, "Thanh cong");

        } else {
            JOptionPane.showMessageDialog(this, "That bai");
        }
        clearForm();
        loadTable();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = tblNhapXuat.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Moi ban chon 1 dong can xoa");
            return;
        }
        Integer updateQuantity = 0;
        String maSP = (String) tblNhapXuat.getValueAt(row, 0);
        String maPhieu = (String) tblNhapXuat.getValueAt(row, 1);
        Integer idSP = medicationService.getID(maSP);
        String soLuong = txtSoLuong.getText().trim();
        Integer loaiPhieu = rdoNhap.isSelected() ? 1 : 0;
        int ketQua = inventoryLogService.delete(idSP, maPhieu);
//        int dem = inventoryLogService.kiemTraID(idSP,maPhieu);
//        if (dem == 0) {
//            JOptionPane.showMessageDialog(this, "Id ko ton tai");
//            return;
//        }
        if (ketQua > -1) {
            JOptionPane.showMessageDialog(this, "Thanh cong");
            if (loaiPhieu == 1) {
                updateQuantity = medicationService.addQuantity(idSP, Integer.parseInt(soLuong));
            } else {
                updateQuantity = medicationService.minusQuantity(idSP, Integer.parseInt(soLuong));
            }
        } else {
            JOptionPane.showMessageDialog(this, "That bai");
        }
        clearForm();
        loadTable();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnThoatActionPerformed

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
            java.util.logging.Logger.getLogger(XuatNhapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XuatNhapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XuatNhapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XuatNhapJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new XuatNhapJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoNhap;
    private javax.swing.JRadioButton rdoXuat;
    private javax.swing.JTable tblNhapXuat;
    private javax.swing.JTextField txtMaPhieu;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtNgay;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
