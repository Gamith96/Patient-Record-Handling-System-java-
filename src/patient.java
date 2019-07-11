
import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Gamith
 */
public class patient {

    public void insertUpdateDeletePatient(char operation, Integer id, String nic, String fname, String lname, String sex, String bdate, String phone, String address, String email, String gname, String gphone) {

        Connection con = MyConnection.getConnection();
        PreparedStatement ps;

        if (operation == 'i') {

            try {
                ps = con.prepareStatement("INSERT INTO patient(nic, first_name, last_name, sex, birthdate, phone, address, email, gurdian_name, gurdian_phone) VALUES (?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, nic);
                ps.setString(2, fname);
                ps.setString(3, lname);
                ps.setString(4, sex);
                ps.setString(5, bdate);
                ps.setString(6, phone);
                ps.setString(7, address);
                ps.setString(8, email);
                ps.setString(9, gname);
                ps.setString(10, gphone);

                if (ps.executeUpdate() > 0) {

                    JOptionPane.showMessageDialog(null, "New Patient Added");

                }

            } catch (SQLException ex) {
                Logger.getLogger(patient.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (operation == 'u') {

            try {
                ps = con.prepareStatement("UPDATE `patient` SET `nic`= ?,`first_name`= ?,`last_name`= ?,`sex`= ?,`birthdate`= ?,`phone`= ?,`address`= ?,`email`= ?,`gurdian_name`= ?,`gurdian_phone`= ? WHERE `pid`= ? ");
                ps.setString(1, nic);
                ps.setString(2, fname);
                ps.setString(3, lname);
                ps.setString(4, sex);
                ps.setString(5, bdate);
                ps.setString(6, phone);
                ps.setString(7, address);
                ps.setString(8, email);
                ps.setString(9, gname);
                ps.setString(10, gphone);
                ps.setInt(11, id);

                if (ps.executeUpdate() > 0) {

                    JOptionPane.showMessageDialog(null, "Patient Details Updated");

                }

            } catch (SQLException ex) {
                Logger.getLogger(patient.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (operation == 'd') {

            try {
                ps = con.prepareStatement("DELETE FROM `patient` WHERE `pid`= ?");
                ps.setInt(1, id);

                if (ps.executeUpdate() > 0) {

                    JOptionPane.showMessageDialog(null, "Patient Details Deleted");

                }

            } catch (SQLException ex) {
                Logger.getLogger(patient.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void fillStudentJtable(JTable table, String valueToSearch) {

        Connection con = MyConnection.getConnection();
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("SELECT * FROM `patient` WHERE CONCAT(`first_name`, `last_name`, `phone`, `address`) LIKE ?");
            ps.setString(1, "%" + valueToSearch + "%");

            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            Object[] row;

            while (rs.next()) {

                row = new Object[11];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                row[10] = rs.getString(11);

                model.addRow(row);

            }

        } catch (SQLException ex) {

            Logger.getLogger(patient.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
