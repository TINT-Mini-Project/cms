package Frames;

import Model.Course;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.sql.*;
import DBConnector.DBCManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Catalogue {

    public static void main(String[] args) {
        new Catalogue();
    }

    public Catalogue() {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (Exception ex) {
            }
            
            JFrame frame = new JFrame("Course Management - Catalogue");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new TestPane());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.getContentPane().setBackground(Color.WHITE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        });
    }

    public class TestPane extends JPanel {

        private JPanel mainList;

        public TestPane() {
            setLayout(new BorderLayout());

            mainList = new JPanel(new GridBagLayout());
            mainList.setBackground(Color.WHITE);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = 1;
            mainList.add(new JPanel(), gbc);
            add(new JScrollPane(mainList));
            
            JPanel panel = new JPanel();
            panel.setBackground(Color.WHITE);
            panel.add(new Header());
            panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
            GridBagConstraints gbc1 = new GridBagConstraints();
            gbc1.gridwidth = GridBagConstraints.REMAINDER;
            gbc1.weightx = 1;
            gbc1.fill = GridBagConstraints.VERTICAL;
            mainList.add(panel, gbc1, 0);
            validate();
            repaint();
            
            try {
                Connection con = DBCManager.getConnection();
                PreparedStatement query = con.prepareStatement("SELECT * FROM Courses");
                ResultSet rs = query.executeQuery();
                int id = 1;
                while(rs.next()){
                    int Cid = rs.getInt(1);
                    String Cname = rs.getString(2);
                    int Iid = rs.getInt(3);
                    int Bid = rs.getInt(4);
                    String Iname = "";
                    String Iphone = "";
                    String Btitle = "";
                    String Bauthor = "";
                    String Bpublisher = "";
                    PreparedStatement q1 = con.prepareStatement("SELECT name, phone FROM Instructors WHERE id = ?");
                    q1.setInt(1, Iid);
                    ResultSet rs1 = q1.executeQuery();
                    if(rs1.next()) {
                        Iname = rs1.getString(1);
                        Iphone = rs1.getString(2);
                    }
                    PreparedStatement q2 = con.prepareStatement("SELECT title, author, publisher FROM Books WHERE id = ?");
                    q2.setInt(1, Bid);
                    ResultSet rs2 = q2.executeQuery();
                    if(rs2.next()) {
                        Btitle = rs2.getString(1);
                        Bauthor = rs2.getString(2);
                        Bpublisher = rs2.getString(3);
                    }
                    
                    Course c = new Course(Cid, Cname, Iname, Iphone, Btitle, Bauthor, Bpublisher);
                    
                    panel = new JPanel();
                    panel.setBackground(Color.WHITE);
                    panel.add(new CourseTemplate(c));
                    panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
                    gbc1 = new GridBagConstraints();
                    gbc1.gridwidth = GridBagConstraints.REMAINDER;
                    gbc1.weightx = 1;
                    gbc1.fill = GridBagConstraints.VERTICAL;
                    mainList.add(panel, gbc1, id++);
                    validate();
                    repaint();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Catalogue.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Catalogue.class.getName()).log(Level.SEVERE, null, ex);
            }
            
//            JButton add = new JButton("Add");
//            add.addActionListener((ActionEvent e) -> {
//                JPanel panel = new JPanel();
//                panel.add(new CourseTemplate());
//                panel.setBorder(new MatteBorder(0, 0, 0, 0, Color.GRAY));
//                GridBagConstraints gbc1 = new GridBagConstraints();
//                gbc1.gridwidth = GridBagConstraints.REMAINDER;
//                gbc1.weightx = 1;
//                gbc1.fill = GridBagConstraints.VERTICAL;
//                mainList.add(panel, gbc1, 0);
//                validate();
//                repaint();
//            });
//
//            add(add, BorderLayout.SOUTH);
            
//            JButton add = new JButton("Add");
//            add.addActionListener((ActionEvent e) -> {
//                JPanel panel = new JPanel();
//                panel.add(new CourseTemplate());
//                panel.setBorder(new MatteBorder(0, 0, 0, 0, Color.GRAY));
//                GridBagConstraints gbc1 = new GridBagConstraints();
//                gbc1.gridwidth = GridBagConstraints.REMAINDER;
//                gbc1.weightx = 1;
//                gbc1.fill = GridBagConstraints.VERTICAL;
//                mainList.add(panel, gbc1, 0);
//                validate();
//                repaint();
//            });
//
//            add(add, BorderLayout.SOUTH);
        }
    }
}