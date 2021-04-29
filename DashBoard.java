/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clothing_store;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.FontSelector;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.mysql.jdbc.Connection;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Dell
 */
public class DashBoard extends javax.swing.JFrame {

    /**
     * Creates new form DashBoard
     */
    Timer timer;
    Random rand = new Random();

    Date date = new Date();
    DateFormat date_format = new SimpleDateFormat("yyyy/MM/dd");
    String entry_date = date_format.format(date);
//    System.out.println(entry_date.toString());

    public static Connection ConnectionProvider() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/cloth_store_db", "root", "");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
    }
    
    public void retrieve_sup_details(){
        Connection con=null;
        con=ConnectionProvider();
        try {
            Statement st=con.createStatement();
            ResultSet tb_rs=st.executeQuery("select * from suppliers");
            DefaultTableModel sup_dm=(DefaultTableModel)sup_details_table.getModel();
            sup_dm.setRowCount(0);
            while(tb_rs.next()){
               String id=tb_rs.getString("s_id");
               String invoice=tb_rs.getString("bill_no");
               String supplier=tb_rs.getString("supplier_name");
               String contact=tb_rs.getString("contact");
               String email=tb_rs.getString("email");
               String total_cost=tb_rs.getString("total_cost");
               String ent_date=tb_rs.getString("entry_date");
               
               String suptb_data[]={id,invoice,supplier,contact,email,total_cost,ent_date};
               
               sup_dm.addRow(suptb_data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void retrieve_items_details(){
        Connection con=null;
        con=ConnectionProvider();
        try {
            Statement st=con.createStatement();
            ResultSet tb_rs=st.executeQuery("select * from items");
            DefaultTableModel item_dm=(DefaultTableModel)mn_items_details_table.getModel();
            item_dm.setRowCount(0);
            while(tb_rs.next()){
               String id=tb_rs.getString("i_id"); 
               String category=tb_rs.getString("category");
               String manufacturer=tb_rs.getString("manufacturer");
               String size=tb_rs.getString("size");
               String rate=tb_rs.getString("rate");
               String quantity=tb_rs.getString("quantity");
               String s_price=tb_rs.getString("selling_price");
               
               
               String itemstb_data[]={id,category,manufacturer,size,rate,quantity,s_price};
               
               
               item_dm.addRow(itemstb_data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void retrieve_sales_details(){
        Connection con=null;
        con=ConnectionProvider();
        try {
            Statement st=con.createStatement();
            ResultSet tb_rs=st.executeQuery("select * from sales");
            DefaultTableModel sale_dm=(DefaultTableModel)sale_details_table.getModel();
            sale_dm.setRowCount(0);
            while(tb_rs.next()){
               String id=tb_rs.getString("sale_id"); 
               String invoice=tb_rs.getString("bill_no");
               String cust_name=tb_rs.getString("customer_name");
               String contact=tb_rs.getString("contact");
               String email=tb_rs.getString("email");
               String tcost=tb_rs.getString("total_cost");
               String disocunt=tb_rs.getString("discount");
               String paid=tb_rs.getString("paid");
               String entry_date=tb_rs.getString("entry_date");
               
               
               String salestb_data[]={id,invoice,cust_name,contact,email,tcost,disocunt,paid,entry_date};
               
               
               sale_dm.addRow(salestb_data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public DashBoard() {
        initComponents();
        
        retrieve_sup_details();
        retrieve_items_details();
        retrieve_sales_details();

//        retrive_sup_details sup_table = new retrive_sup_details();

        ActionListener actionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                DateFormat timeFormat1 = new SimpleDateFormat("HH:mm:ss");
                String time = timeFormat1.format(date);
                curtime.setText(time);

                Date date1 = new Date();
                DateFormat timeFormat2 = new SimpleDateFormat("dd/MM/yyyy");
                String time1 = timeFormat2.format(date);
                curdate.setText(time1);

            }
        };
        timer = new Timer(1000, actionListener);
        timer.setInitialDelay(0);
        timer.start();

//        Connection con=null;
//        con=ConnectionProvider();
//        try {
//            Statement st=con.createStatement();
//            ResultSet tb_rs=st.executeQuery("select * from suppliers");
//            while(tb_rs.next()){
//               String supplier=tb_rs.getString("supplier_name");
//               String contact=tb_rs.getString("contact");
//               String email=tb_rs.getString("email");
//               String total_cost=tb_rs.getString("total_cost");
//               String ent_date=tb_rs.getString("entry_date");
//               
//               String suptb_data[]={supplier,contact,email,total_cost,ent_date};
//               
//               DefaultTableModel sup_dm=(DefaultTableModel)sup_details_table.getModel();
//               sup_dm.addRow(suptb_data);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

//    public void retrive_sup_details(){
//        
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        HeadingPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        logout_btn = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        curtime = new javax.swing.JLabel();
        curdate = new javax.swing.JLabel();
        NavigationPanel = new javax.swing.JPanel();
        POS_btn = new javax.swing.JButton();
        suppliers_btn = new javax.swing.JButton();
        manage_items_btn = new javax.swing.JButton();
        purchase_items_btn = new javax.swing.JButton();
        sales_btn = new javax.swing.JButton();
        MainPanel = new javax.swing.JPanel();
        POS_sale_panel = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        cust_name_txt = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        cust_email_txt = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        cust_contact_txt = new javax.swing.JTextField();
        validate_cust_cont = new javax.swing.JLabel();
        validate_cust_name = new javax.swing.JLabel();
        validate_cust_email = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        sale_cgry_txt = new javax.swing.JTextField();
        sale_mfr_txt = new javax.swing.JTextField();
        sale_qty_txt = new javax.swing.JTextField();
        sale_sp_txt = new javax.swing.JTextField();
        sale_size_cmbox = new javax.swing.JComboBox();
        sale_add_to_cart = new javax.swing.JButton();
        sale_reset_all = new javax.swing.JButton();
        validate_sale_sp = new javax.swing.JLabel();
        validate_sale_qty = new javax.swing.JLabel();
        validate_sale_mfr = new javax.swing.JLabel();
        validate_sale_cgry = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        sale_items_table = new javax.swing.JTable();
        jLabel38 = new javax.swing.JLabel();
        sale_generate_bill = new javax.swing.JButton();
        sale_discount_txt = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        sale_paid_txt = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        sale_total_cost_lb = new javax.swing.JLabel();
        purchase_items_panel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        supp_name_txt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        supp_email_txt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        supp_contact_txt = new javax.swing.JTextField();
        validate_sup_name = new javax.swing.JLabel();
        validate_sup_cont = new javax.swing.JLabel();
        validate_sup_email = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        pur_cgry_txt = new javax.swing.JTextField();
        pur_mfr_txt = new javax.swing.JTextField();
        pur_rate_txt = new javax.swing.JTextField();
        pur_qty_txt = new javax.swing.JTextField();
        pur_sp_txt = new javax.swing.JTextField();
        pur_size_cmbox = new javax.swing.JComboBox();
        pur_add_to_cart = new javax.swing.JButton();
        pur_reset_all = new javax.swing.JButton();
        validate_rate = new javax.swing.JLabel();
        validate_qty = new javax.swing.JLabel();
        validate_sp = new javax.swing.JLabel();
        validate_pur_mfr = new javax.swing.JLabel();
        validate_pur_cgry = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        purchase_items_table = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        save_pur_bill = new javax.swing.JButton();
        pur_total_cost = new javax.swing.JLabel();
        suppliers_panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        srch_sup_d_txt = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        sup_details_table = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        show_items_table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        manage_items_panel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        search_mn_items = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        mn_items_details_table = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        mn_cgry_txt = new javax.swing.JTextField();
        mn_mfg_txt = new javax.swing.JTextField();
        mn_qty_txt = new javax.swing.JTextField();
        mn_rate_txt = new javax.swing.JTextField();
        mn_sp_txt = new javax.swing.JTextField();
        mn_delete_item = new javax.swing.JButton();
        mn_update_item_details = new javax.swing.JButton();
        mn_size_cmbox = new javax.swing.JComboBox();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        mn_i_id_lb = new javax.swing.JLabel();
        sales_report_panel = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        srch_sale_txt = new javax.swing.JTextField();
        show_sale_btn = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        sale_details_table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        HeadingPanel.setBackground(new java.awt.Color(0, 153, 102));
        HeadingPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        HeadingPanel.setPreferredSize(new java.awt.Dimension(1200, 80));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Jay Gurudev Cloth Store");
        jLabel1.setPreferredSize(new java.awt.Dimension(800, 80));

        logout_btn.setFont(new java.awt.Font("Lucida Bright", 0, 40)); // NOI18N
        logout_btn.setText("Logout");
        logout_btn.setBorder(null);
        logout_btn.setMargin(new java.awt.Insets(0, 0, 0, 0));
        logout_btn.setPreferredSize(new java.awt.Dimension(161, 60));
        logout_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout_btnActionPerformed(evt);
            }
        });

        jPanel23.setBackground(new java.awt.Color(0, 153, 102));
        jPanel23.setPreferredSize(new java.awt.Dimension(220, 76));

        curtime.setBackground(new java.awt.Color(255, 153, 0));
        curtime.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        curtime.setForeground(new java.awt.Color(255, 255, 255));
        curtime.setText("curtime");

        curdate.setFont(new java.awt.Font("Lucida Bright", 0, 18)); // NOI18N
        curdate.setForeground(new java.awt.Color(255, 255, 255));
        curdate.setText("curdate");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(curtime)
                    .addComponent(curdate))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(curtime, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(curdate, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout HeadingPanelLayout = new javax.swing.GroupLayout(HeadingPanel);
        HeadingPanel.setLayout(HeadingPanelLayout);
        HeadingPanelLayout.setHorizontalGroup(
            HeadingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeadingPanelLayout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128)
                .addComponent(logout_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        HeadingPanelLayout.setVerticalGroup(
            HeadingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeadingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(logout_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(HeadingPanelLayout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        NavigationPanel.setBackground(new java.awt.Color(255, 153, 0));
        NavigationPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        NavigationPanel.setPreferredSize(new java.awt.Dimension(220, 590));

        POS_btn.setBackground(new java.awt.Color(0, 153, 102));
        POS_btn.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 26)); // NOI18N
        POS_btn.setForeground(new java.awt.Color(255, 255, 255));
        POS_btn.setText("POS Sale");
        POS_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                POS_btnActionPerformed(evt);
            }
        });

        suppliers_btn.setBackground(new java.awt.Color(0, 153, 102));
        suppliers_btn.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 26)); // NOI18N
        suppliers_btn.setForeground(new java.awt.Color(255, 255, 255));
        suppliers_btn.setText("Suppliers");
        suppliers_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MouseClicked(evt);
            }
        });
        suppliers_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suppliers_btnActionPerformed(evt);
            }
        });

        manage_items_btn.setBackground(new java.awt.Color(0, 153, 102));
        manage_items_btn.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 26)); // NOI18N
        manage_items_btn.setForeground(new java.awt.Color(255, 255, 255));
        manage_items_btn.setText("Manage Items");
        manage_items_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manage_items_btnActionPerformed(evt);
            }
        });

        purchase_items_btn.setBackground(new java.awt.Color(0, 153, 102));
        purchase_items_btn.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 26)); // NOI18N
        purchase_items_btn.setForeground(new java.awt.Color(255, 255, 255));
        purchase_items_btn.setText("Purchase Items");
        purchase_items_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MouseClicked(evt);
            }
        });
        purchase_items_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchase_items_btnActionPerformed(evt);
            }
        });

        sales_btn.setBackground(new java.awt.Color(0, 153, 102));
        sales_btn.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 26)); // NOI18N
        sales_btn.setForeground(new java.awt.Color(255, 255, 255));
        sales_btn.setText("Sales");
        sales_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sales_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NavigationPanelLayout = new javax.swing.GroupLayout(NavigationPanel);
        NavigationPanel.setLayout(NavigationPanelLayout);
        NavigationPanelLayout.setHorizontalGroup(
            NavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(POS_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(suppliers_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sales_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(manage_items_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
            .addComponent(purchase_items_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        NavigationPanelLayout.setVerticalGroup(
            NavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NavigationPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(POS_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(purchase_items_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(suppliers_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(manage_items_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(sales_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        MainPanel.setPreferredSize(new java.awt.Dimension(980, 590));
        MainPanel.setLayout(new java.awt.CardLayout());

        jPanel11.setBackground(new java.awt.Color(255, 153, 0));
        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        jPanel11.setPreferredSize(new java.awt.Dimension(570, 49));

        jLabel27.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("POS Sale");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(jLabel27)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel28.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jLabel28.setText("Customer Name:");

        cust_name_txt.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        cust_name_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cust_name_txtActionPerformed(evt);
            }
        });
        cust_name_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cust_name_txtKeyReleased(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jLabel29.setText(" Contact:");

        cust_email_txt.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        cust_email_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cust_email_txtKeyReleased(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jLabel30.setText(" Email:");

        cust_contact_txt.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        cust_contact_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cust_contact_txtKeyReleased(evt);
            }
        });

        validate_cust_cont.setForeground(new java.awt.Color(0, 153, 51));

        validate_cust_name.setForeground(new java.awt.Color(0, 153, 51));

        validate_cust_email.setForeground(new java.awt.Color(0, 153, 51));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(validate_cust_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cust_name_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
                .addGap(72, 72, 72)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(validate_cust_cont, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cust_contact_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                .addGap(75, 75, 75)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(validate_cust_email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cust_email_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(validate_cust_cont, javax.swing.GroupLayout.DEFAULT_SIZE, 11, Short.MAX_VALUE)
                    .addComponent(validate_cust_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(validate_cust_email, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 11, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(cust_name_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(cust_email_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(cust_contact_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel17.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jLabel17.setText("Category:");

        jLabel18.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jLabel18.setText("Size:");

        jLabel37.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jLabel37.setText("Manufacturer:");

        jLabel39.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jLabel39.setText("Quantity:");

        jLabel40.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jLabel40.setText("Price:");

        sale_cgry_txt.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        sale_cgry_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sale_cgry_txtActionPerformed(evt);
            }
        });
        sale_cgry_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sale_cgry_txtKeyReleased(evt);
            }
        });

        sale_mfr_txt.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        sale_mfr_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sale_mfr_txtActionPerformed(evt);
            }
        });
        sale_mfr_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sale_mfr_txtKeyReleased(evt);
            }
        });

        sale_qty_txt.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        sale_qty_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sale_qty_txtActionPerformed(evt);
            }
        });
        sale_qty_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sale_qty_txtKeyReleased(evt);
            }
        });

        sale_sp_txt.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        sale_sp_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sale_sp_txtActionPerformed(evt);
            }
        });
        sale_sp_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sale_sp_txtKeyReleased(evt);
            }
        });

        sale_size_cmbox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SM", "MD", "L", "XL" }));
        sale_size_cmbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sale_size_cmboxActionPerformed(evt);
            }
        });

        sale_add_to_cart.setBackground(new java.awt.Color(0, 153, 102));
        sale_add_to_cart.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        sale_add_to_cart.setForeground(new java.awt.Color(255, 255, 255));
        sale_add_to_cart.setText("Add to Cart");
        sale_add_to_cart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sale_add_to_cartActionPerformed(evt);
            }
        });

        sale_reset_all.setBackground(new java.awt.Color(0, 153, 102));
        sale_reset_all.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        sale_reset_all.setForeground(new java.awt.Color(255, 255, 255));
        sale_reset_all.setText("Reset");
        sale_reset_all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sale_reset_allActionPerformed(evt);
            }
        });

        validate_sale_sp.setForeground(new java.awt.Color(0, 153, 51));

        validate_sale_qty.setForeground(new java.awt.Color(0, 153, 51));

        validate_sale_mfr.setForeground(new java.awt.Color(0, 153, 51));

        validate_sale_cgry.setForeground(new java.awt.Color(0, 153, 51));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(sale_reset_all, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)
                        .addComponent(jLabel18)
                        .addComponent(jLabel39)
                        .addComponent(jLabel40)))
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sale_cgry_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sale_mfr_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(validate_sale_mfr, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(validate_sale_cgry, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sale_add_to_cart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sale_sp_txt)
                            .addComponent(validate_sale_sp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sale_qty_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sale_size_cmbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(validate_sale_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(validate_sale_cgry, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sale_cgry_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addComponent(validate_sale_mfr, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(sale_mfr_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sale_size_cmbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addComponent(validate_sale_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sale_qty_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addGap(18, 18, 18)
                .addComponent(validate_sale_sp, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sale_sp_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sale_add_to_cart)
                    .addComponent(sale_reset_all))
                .addGap(68, 68, 68))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        sale_items_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Category", "Manufacturer", "Size", "Qunatity", "Selling Price", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(sale_items_table);

        jLabel38.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel38.setText("Discount");

        sale_generate_bill.setBackground(new java.awt.Color(0, 153, 102));
        sale_generate_bill.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        sale_generate_bill.setForeground(new java.awt.Color(255, 255, 255));
        sale_generate_bill.setText("Generate Bill");
        sale_generate_bill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sale_generate_billActionPerformed(evt);
            }
        });

        sale_discount_txt.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        sale_discount_txt.setText("0.0");
        sale_discount_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sale_discount_txtActionPerformed(evt);
            }
        });
        sale_discount_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sale_discount_txtKeyReleased(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel42.setText("Pay Amount");

        sale_paid_txt.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        sale_paid_txt.setText("0.0");
        sale_paid_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sale_paid_txtActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel43.setText("Total Cost");

        sale_total_cost_lb.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        sale_total_cost_lb.setText("0.0");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sale_total_cost_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addGap(55, 55, 55)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sale_discount_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addGap(30, 30, 30)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(sale_paid_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sale_generate_bill)
                .addGap(19, 19, 19))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(jLabel38)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sale_discount_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sale_total_cost_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sale_paid_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(sale_generate_bill, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout POS_sale_panelLayout = new javax.swing.GroupLayout(POS_sale_panel);
        POS_sale_panel.setLayout(POS_sale_panelLayout);
        POS_sale_panelLayout.setHorizontalGroup(
            POS_sale_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(POS_sale_panelLayout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        POS_sale_panelLayout.setVerticalGroup(
            POS_sale_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(POS_sale_panelLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(POS_sale_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        MainPanel.add(POS_sale_panel, "card2");

        jPanel4.setBackground(new java.awt.Color(255, 153, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Purchase Items");
        jLabel6.setPreferredSize(new java.awt.Dimension(263, 49));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(347, 347, 347)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel7.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jLabel7.setText("Supplier Name:");

        supp_name_txt.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        supp_name_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supp_name_txtActionPerformed(evt);
            }
        });
        supp_name_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                supp_name_txtKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jLabel8.setText(" Contact:");

        supp_email_txt.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        supp_email_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                supp_email_txtKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jLabel9.setText(" Email:");

        supp_contact_txt.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        supp_contact_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                supp_contact_txtKeyReleased(evt);
            }
        });

        validate_sup_name.setForeground(new java.awt.Color(0, 153, 51));

        validate_sup_cont.setForeground(new java.awt.Color(0, 153, 51));

        validate_sup_email.setForeground(new java.awt.Color(0, 153, 51));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(validate_sup_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(supp_name_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
                .addGap(72, 72, 72)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(supp_contact_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(validate_sup_cont, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(supp_email_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validate_sup_email, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(validate_sup_name, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validate_sup_cont, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validate_sup_email, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(supp_name_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(supp_email_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(supp_contact_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel10.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jLabel10.setText("Category:");

        jLabel11.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jLabel11.setText("Size:");

        jLabel12.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jLabel12.setText("Manufacturer:");

        jLabel13.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jLabel13.setText("Rate");

        jLabel14.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jLabel14.setText("Quantity:");

        jLabel15.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jLabel15.setText("Selling Price:");

        pur_cgry_txt.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        pur_cgry_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pur_cgry_txtActionPerformed(evt);
            }
        });
        pur_cgry_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pur_cgry_txtKeyReleased(evt);
            }
        });

        pur_mfr_txt.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        pur_mfr_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pur_mfr_txtActionPerformed(evt);
            }
        });
        pur_mfr_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pur_mfr_txtKeyReleased(evt);
            }
        });

        pur_rate_txt.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        pur_rate_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pur_rate_txtActionPerformed(evt);
            }
        });
        pur_rate_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pur_rate_txtKeyReleased(evt);
            }
        });

        pur_qty_txt.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        pur_qty_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pur_qty_txtActionPerformed(evt);
            }
        });
        pur_qty_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pur_qty_txtKeyReleased(evt);
            }
        });

        pur_sp_txt.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        pur_sp_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pur_sp_txtActionPerformed(evt);
            }
        });
        pur_sp_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pur_sp_txtKeyReleased(evt);
            }
        });

        pur_size_cmbox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SM", "MD", "L", "XL" }));
        pur_size_cmbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pur_size_cmboxActionPerformed(evt);
            }
        });

        pur_add_to_cart.setBackground(new java.awt.Color(0, 153, 102));
        pur_add_to_cart.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        pur_add_to_cart.setForeground(new java.awt.Color(255, 255, 255));
        pur_add_to_cart.setText("Add to Cart");
        pur_add_to_cart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pur_add_to_cartActionPerformed(evt);
            }
        });

        pur_reset_all.setBackground(new java.awt.Color(0, 153, 102));
        pur_reset_all.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        pur_reset_all.setForeground(new java.awt.Color(255, 255, 255));
        pur_reset_all.setText("Reset All");
        pur_reset_all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pur_reset_allActionPerformed(evt);
            }
        });

        validate_rate.setForeground(new java.awt.Color(0, 153, 51));

        validate_qty.setForeground(new java.awt.Color(0, 153, 51));

        validate_sp.setForeground(new java.awt.Color(0, 153, 51));

        validate_pur_mfr.setForeground(new java.awt.Color(0, 153, 51));

        validate_pur_cgry.setForeground(new java.awt.Color(0, 153, 51));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(pur_reset_all)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(pur_add_to_cart))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(validate_rate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pur_sp_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(pur_size_cmbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pur_mfr_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(pur_cgry_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(pur_rate_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(pur_qty_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(validate_qty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(validate_sp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(validate_pur_mfr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(validate_pur_cgry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(validate_pur_cgry, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(pur_cgry_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(validate_pur_mfr, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(pur_mfr_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(pur_size_cmbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(validate_rate, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(pur_rate_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(validate_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pur_qty_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(11, 11, 11)
                .addComponent(validate_sp, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(pur_sp_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pur_add_to_cart)
                    .addComponent(pur_reset_all))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        purchase_items_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Category", "Manufacturer", "Size", "Rate", "Qunatity", "Selling Price", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(purchase_items_table);

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel16.setText("Total Cost");

        save_pur_bill.setBackground(new java.awt.Color(0, 153, 102));
        save_pur_bill.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        save_pur_bill.setForeground(new java.awt.Color(255, 255, 255));
        save_pur_bill.setText("Save");
        save_pur_bill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_pur_billActionPerformed(evt);
            }
        });

        pur_total_cost.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        pur_total_cost.setText("0.0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel16)
                .addGap(37, 37, 37)
                .addComponent(pur_total_cost, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(save_pur_bill, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pur_total_cost, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(save_pur_bill, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout purchase_items_panelLayout = new javax.swing.GroupLayout(purchase_items_panel);
        purchase_items_panel.setLayout(purchase_items_panelLayout);
        purchase_items_panelLayout.setHorizontalGroup(
            purchase_items_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(purchase_items_panelLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        purchase_items_panelLayout.setVerticalGroup(
            purchase_items_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(purchase_items_panelLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(purchase_items_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        MainPanel.add(purchase_items_panel, "card3");

        suppliers_panel.setBackground(new java.awt.Color(204, 204, 255));
        suppliers_panel.setPreferredSize(new java.awt.Dimension(980, 590));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel2.setFont(new java.awt.Font("Lucida Bright", 0, 18)); // NOI18N
        jLabel2.setText("Search");

        srch_sup_d_txt.setFont(new java.awt.Font("Lucida Bright", 0, 18)); // NOI18N
        srch_sup_d_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                srch_sup_d_txtActionPerformed(evt);
            }
        });
        srch_sup_d_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                srch_sup_d_txtKeyReleased(evt);
            }
        });

        sup_details_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Invoice No", "Supplier", "Contact", "Email", "Total Cost", "Entry Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sup_details_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sup_details_tableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(sup_details_table);
        if (sup_details_table.getColumnModel().getColumnCount() > 0) {
            sup_details_table.getColumnModel().getColumn(0).setResizable(false);
            sup_details_table.getColumnModel().getColumn(0).setPreferredWidth(10);
            sup_details_table.getColumnModel().getColumn(1).setResizable(false);
            sup_details_table.getColumnModel().getColumn(1).setPreferredWidth(28);
            sup_details_table.getColumnModel().getColumn(2).setResizable(false);
            sup_details_table.getColumnModel().getColumn(2).setPreferredWidth(50);
            sup_details_table.getColumnModel().getColumn(3).setResizable(false);
            sup_details_table.getColumnModel().getColumn(3).setPreferredWidth(50);
            sup_details_table.getColumnModel().getColumn(4).setResizable(false);
            sup_details_table.getColumnModel().getColumn(4).setPreferredWidth(60);
            sup_details_table.getColumnModel().getColumn(5).setResizable(false);
            sup_details_table.getColumnModel().getColumn(5).setPreferredWidth(40);
            sup_details_table.getColumnModel().getColumn(6).setResizable(false);
            sup_details_table.getColumnModel().getColumn(6).setPreferredWidth(45);
        }

        show_items_table.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        show_items_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Invoice No.", "Category", "Manufacturer", "Size", "Rate", "Quantity", "Selling Price", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(show_items_table);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(srch_sup_d_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(srch_sup_d_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 153, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Supplier");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(426, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(409, 409, 409))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout suppliers_panelLayout = new javax.swing.GroupLayout(suppliers_panel);
        suppliers_panel.setLayout(suppliers_panelLayout);
        suppliers_panelLayout.setHorizontalGroup(
            suppliers_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        suppliers_panelLayout.setVerticalGroup(
            suppliers_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, suppliers_panelLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MainPanel.add(suppliers_panel, "card4");

        jPanel8.setBackground(new java.awt.Color(255, 153, 0));
        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        jPanel8.setPreferredSize(new java.awt.Dimension(572, 53));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Manage Items");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(326, 326, 326))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        search_mn_items.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        search_mn_items.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_mn_itemsActionPerformed(evt);
            }
        });
        search_mn_items.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_mn_itemsKeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Lucida Bright", 0, 18)); // NOI18N
        jLabel21.setText("Search");

        mn_items_details_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Category", "Manufacturer", "Size", "Rate", "Quantity", "Selling Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        mn_items_details_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mn_items_details_tableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(mn_items_details_table);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel21)
                .addGap(35, 35, 35)
                .addComponent(search_mn_items)
                .addGap(233, 233, 233))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_mn_items, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane3))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel22.setFont(new java.awt.Font("Lucida Bright", 0, 18)); // NOI18N
        jLabel22.setText("ID");

        jLabel23.setFont(new java.awt.Font("Lucida Bright", 0, 18)); // NOI18N
        jLabel23.setText("Manufacturer:");

        jLabel24.setFont(new java.awt.Font("Lucida Bright", 0, 18)); // NOI18N
        jLabel24.setText("Quantity:");

        jLabel25.setFont(new java.awt.Font("Lucida Bright", 0, 18)); // NOI18N
        jLabel25.setText("Rate:");

        jLabel26.setFont(new java.awt.Font("Lucida Bright", 0, 18)); // NOI18N
        jLabel26.setText("Selling Price:");

        mn_cgry_txt.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        mn_cgry_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mn_cgry_txtActionPerformed(evt);
            }
        });

        mn_mfg_txt.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        mn_mfg_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mn_mfg_txtActionPerformed(evt);
            }
        });

        mn_qty_txt.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        mn_qty_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mn_qty_txtActionPerformed(evt);
            }
        });

        mn_rate_txt.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        mn_rate_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mn_rate_txtActionPerformed(evt);
            }
        });

        mn_sp_txt.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        mn_sp_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mn_sp_txtActionPerformed(evt);
            }
        });

        mn_delete_item.setBackground(new java.awt.Color(0, 153, 102));
        mn_delete_item.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        mn_delete_item.setForeground(new java.awt.Color(255, 255, 255));
        mn_delete_item.setText("Delete");
        mn_delete_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mn_delete_itemActionPerformed(evt);
            }
        });

        mn_update_item_details.setBackground(new java.awt.Color(0, 153, 102));
        mn_update_item_details.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        mn_update_item_details.setForeground(new java.awt.Color(255, 255, 255));
        mn_update_item_details.setText("Update");
        mn_update_item_details.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mn_update_item_detailsActionPerformed(evt);
            }
        });

        mn_size_cmbox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SM", "MD", "L", "XL" }));
        mn_size_cmbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mn_size_cmboxActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Lucida Bright", 0, 18)); // NOI18N
        jLabel46.setText("Size:");

        jLabel47.setFont(new java.awt.Font("Lucida Bright", 0, 18)); // NOI18N
        jLabel47.setText("Category:");

        mn_i_id_lb.setFont(new java.awt.Font("Lucida Bright", 0, 18)); // NOI18N
        mn_i_id_lb.setText("ID");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel46))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mn_rate_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mn_qty_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mn_size_cmbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mn_mfg_txt))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel47))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mn_cgry_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mn_i_id_lb)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(mn_update_item_details, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(mn_sp_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mn_delete_item, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mn_i_id_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(mn_cgry_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(mn_mfg_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(mn_size_cmbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(mn_qty_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mn_rate_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(33, 33, 33)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mn_sp_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(49, 49, 49)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mn_update_item_details, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mn_delete_item, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );

        javax.swing.GroupLayout manage_items_panelLayout = new javax.swing.GroupLayout(manage_items_panel);
        manage_items_panel.setLayout(manage_items_panelLayout);
        manage_items_panelLayout.setHorizontalGroup(
            manage_items_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
            .addGroup(manage_items_panelLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        manage_items_panelLayout.setVerticalGroup(
            manage_items_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manage_items_panelLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(manage_items_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        MainPanel.add(manage_items_panel, "card2");

        jPanel13.setBackground(new java.awt.Color(255, 153, 0));
        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Sales");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(474, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(409, 409, 409))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel4.setFont(new java.awt.Font("Lucida Bright", 0, 18)); // NOI18N
        jLabel4.setText("Search:");

        srch_sale_txt.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        srch_sale_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                srch_sale_txtKeyReleased(evt);
            }
        });

        show_sale_btn.setBackground(new java.awt.Color(0, 153, 102));
        show_sale_btn.setFont(new java.awt.Font("Lucida Bright", 0, 22)); // NOI18N
        show_sale_btn.setForeground(new java.awt.Color(255, 255, 255));
        show_sale_btn.setText("View");
        show_sale_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                show_sale_btnActionPerformed(evt);
            }
        });

        sale_details_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Invoice No", "Customer", "Contact", "Email", "Total Cost", "Discount", "Paid", "Entry Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sale_details_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sale_details_tableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(sale_details_table);
        if (sale_details_table.getColumnModel().getColumnCount() > 0) {
            sale_details_table.getColumnModel().getColumn(0).setResizable(false);
            sale_details_table.getColumnModel().getColumn(0).setPreferredWidth(10);
            sale_details_table.getColumnModel().getColumn(1).setResizable(false);
            sale_details_table.getColumnModel().getColumn(1).setPreferredWidth(28);
            sale_details_table.getColumnModel().getColumn(2).setResizable(false);
            sale_details_table.getColumnModel().getColumn(2).setPreferredWidth(50);
            sale_details_table.getColumnModel().getColumn(3).setResizable(false);
            sale_details_table.getColumnModel().getColumn(3).setPreferredWidth(50);
            sale_details_table.getColumnModel().getColumn(4).setResizable(false);
            sale_details_table.getColumnModel().getColumn(4).setPreferredWidth(60);
            sale_details_table.getColumnModel().getColumn(5).setResizable(false);
            sale_details_table.getColumnModel().getColumn(5).setPreferredWidth(40);
            sale_details_table.getColumnModel().getColumn(6).setResizable(false);
            sale_details_table.getColumnModel().getColumn(7).setResizable(false);
            sale_details_table.getColumnModel().getColumn(8).setResizable(false);
            sale_details_table.getColumnModel().getColumn(8).setPreferredWidth(45);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(srch_sale_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(show_sale_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(srch_sale_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(show_sale_btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout sales_report_panelLayout = new javax.swing.GroupLayout(sales_report_panel);
        sales_report_panel.setLayout(sales_report_panelLayout);
        sales_report_panelLayout.setHorizontalGroup(
            sales_report_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sales_report_panelLayout.setVerticalGroup(
            sales_report_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sales_report_panelLayout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MainPanel.add(sales_report_panel, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(HeadingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(NavigationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(HeadingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NavigationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                    .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)))
        );

        setBounds(0, 0, 1216, 709);
    }// </editor-fold>//GEN-END:initComponents

    private void POS_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_POS_btnActionPerformed
        // TODO add your handling code here:
        // Removing the Panel
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();

        // Adding the Panel
        MainPanel.add(POS_sale_panel);
        MainPanel.repaint();
        MainPanel.revalidate();

    }//GEN-LAST:event_POS_btnActionPerformed

    private void suppliers_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suppliers_btnActionPerformed
        // TODO add your handling code here:
        // Removing the Panel
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();

        // Adding the Panel
        MainPanel.add(suppliers_panel);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_suppliers_btnActionPerformed

    private void MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_MouseClicked

    private void purchase_items_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchase_items_btnActionPerformed
        // TODO add your handling code here:
        // Removing the Panel
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();

        // Adding the Panel
        MainPanel.add(purchase_items_panel);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_purchase_items_btnActionPerformed

    private void manage_items_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manage_items_btnActionPerformed
        // TODO add your handling code here:
        // Removing the Panel
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();

        // Adding the Panel
        MainPanel.add(manage_items_panel);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_manage_items_btnActionPerformed

    private void sales_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sales_btnActionPerformed
        // TODO add your handling code here:
        // Removing the Panel
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();

        // Adding the Panel
        MainPanel.add(sales_report_panel);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_sales_btnActionPerformed

    private void srch_sup_d_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_srch_sup_d_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_srch_sup_d_txtActionPerformed

    private void supp_name_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supp_name_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supp_name_txtActionPerformed

    private void pur_cgry_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pur_cgry_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pur_cgry_txtActionPerformed

    private void pur_mfr_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pur_mfr_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pur_mfr_txtActionPerformed

    private void pur_rate_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pur_rate_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pur_rate_txtActionPerformed

    private void pur_qty_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pur_qty_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pur_qty_txtActionPerformed

    private void pur_sp_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pur_sp_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pur_sp_txtActionPerformed

    private void pur_size_cmboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pur_size_cmboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pur_size_cmboxActionPerformed

    private void search_mn_itemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_mn_itemsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_mn_itemsActionPerformed

    private void mn_cgry_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mn_cgry_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mn_cgry_txtActionPerformed

    private void mn_mfg_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mn_mfg_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mn_mfg_txtActionPerformed

    private void mn_qty_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mn_qty_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mn_qty_txtActionPerformed

    private void mn_rate_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mn_rate_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mn_rate_txtActionPerformed

    private void mn_sp_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mn_sp_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mn_sp_txtActionPerformed

    private void mn_delete_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mn_delete_itemActionPerformed
        // TODO add your handling code here:
        String mn_i_id = mn_i_id_lb.getText();
        String mn_category = mn_cgry_txt.getText();
        String mn_mfg = mn_mfg_txt.getText();
        Object mn_size = mn_size_cmbox.getSelectedItem();
        String mn_rate = mn_rate_txt.getText();
        String mn_qty = mn_qty_txt.getText();
        String mn_sp = mn_sp_txt.getText();
        
        
        if (mn_i_id.isEmpty() || mn_category.isEmpty() || mn_mfg.isEmpty() || mn_rate.isEmpty() || mn_qty.isEmpty() || mn_sp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select the Item");
        } else {
            double sale_price = Double.parseDouble(mn_rate_txt.getText());
            double sale_qty = Double.parseDouble(mn_qty_txt.getText());
            double total_price_item = (sale_price * sale_qty);
            int result = JOptionPane.showConfirmDialog(this,"Sure? You want to Update?", "Confirm Updation",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
            if(result==JOptionPane.YES_NO_OPTION){
                try {
                    Connection con = null;
                    con = ConnectionProvider();
                    Statement up_st=null;
                    up_st = con.createStatement();
                    up_st.execute("delete from items WHERE i_id = '"+mn_i_id+"'");
                    retrieve_items_details();
                    retrieve_sup_details();
                    clear_mn_item_widget();
                } catch (SQLException ex) {
                    Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_mn_delete_itemActionPerformed

    private void cust_name_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cust_name_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cust_name_txtActionPerformed

    private void sale_cgry_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sale_cgry_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sale_cgry_txtActionPerformed

    private void sale_mfr_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sale_mfr_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sale_mfr_txtActionPerformed

    private void sale_qty_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sale_qty_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sale_qty_txtActionPerformed

    private void sale_sp_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sale_sp_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sale_sp_txtActionPerformed

    private void sale_size_cmboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sale_size_cmboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sale_size_cmboxActionPerformed

    private void sale_discount_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sale_discount_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sale_discount_txtActionPerformed

    private void sale_paid_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sale_paid_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sale_paid_txtActionPerformed
    double purschase_total_cost = 0;
    private void pur_add_to_cartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pur_add_to_cartActionPerformed
        // TODO add your handling code here:
        if (pur_mfr_txt.getText().isEmpty() || pur_cgry_txt.getText().isEmpty() || pur_rate_txt.getText().isEmpty() || pur_qty_txt.getText().isEmpty() || pur_sp_txt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill all the details");
        } else {
            double pur_rate = Double.parseDouble(pur_rate_txt.getText());
            double pur_qty = Double.parseDouble(pur_qty_txt.getText());
            double total_price_item = (pur_rate * pur_qty);
            purschase_total_cost = purschase_total_cost + total_price_item;
            DefaultTableModel dm = (DefaultTableModel) purchase_items_table.getModel();
            dm.addRow(new Object[]{pur_cgry_txt.getText(), pur_mfr_txt.getText(), pur_size_cmbox.getSelectedItem(), pur_rate_txt.getText(),
                pur_qty_txt.getText(), pur_sp_txt.getText(), total_price_item});
            pur_total_cost.setText(String.valueOf(purschase_total_cost));
        }

    }//GEN-LAST:event_pur_add_to_cartActionPerformed

    private void pur_reset_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pur_reset_allActionPerformed
        // TODO add your handling code here:
        pur_cgry_txt.setText("");
        pur_mfr_txt.setText("");
        pur_rate_txt.setText("");
        pur_qty_txt.setText("");
        pur_sp_txt.setText("");
        
    }//GEN-LAST:event_pur_reset_allActionPerformed
    double sale_total_cost = 0;
    private void sale_add_to_cartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sale_add_to_cartActionPerformed
        // TODO add your handling code here:
        if (sale_cgry_txt.getText().isEmpty() || sale_mfr_txt.getText().isEmpty() || sale_qty_txt.getText().isEmpty() || sale_qty_txt.getText().isEmpty() || sale_sp_txt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill all the details");
        } else {
            double sale_price = Double.parseDouble(sale_sp_txt.getText());
            double sale_qty = Double.parseDouble(sale_qty_txt.getText());
            double total_price_item = (sale_price * sale_qty);
            sale_total_cost = sale_total_cost + total_price_item;
            DefaultTableModel dm = (DefaultTableModel) sale_items_table.getModel();
            dm.addRow(new Object[]{sale_cgry_txt.getText(), sale_mfr_txt.getText(), sale_size_cmbox.getSelectedItem(), sale_qty_txt.getText(), sale_sp_txt.getText(), total_price_item});
            sale_total_cost_lb.setText(String.valueOf(sale_total_cost));
        }

    }//GEN-LAST:event_sale_add_to_cartActionPerformed

    private void sale_reset_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sale_reset_allActionPerformed
        // TODO add your handling code here:
        sale_cgry_txt.setText("");
        sale_mfr_txt.setText("");
        sale_qty_txt.setText("");
        sale_sp_txt.setText("");
    }//GEN-LAST:event_sale_reset_allActionPerformed
    
    private void clear_sale_widget(){
        cust_name_txt.setText("");
        cust_contact_txt.setText("");
        cust_email_txt.setText("");
        DefaultTableModel sale_dm = (DefaultTableModel) sale_items_table.getModel();
        sale_dm.setRowCount(0);
        sale_discount_txt.setText("0.0");
        sale_total_cost_lb.setText("");
        sale_paid_txt.setText("0.0");
        sale_qty_txt.setText("");
        sale_sp_txt.setText("");
        sale_cgry_txt.setText("");
        sale_mfr_txt.setText("");
        sale_total_cost_lb.setText("0.0");
        
    }
    
    private void clear_pur_widget(){
        supp_name_txt.setText("");
        supp_contact_txt.setText("");
        supp_email_txt.setText("");
        DefaultTableModel pur_dm = (DefaultTableModel) purchase_items_table.getModel();
        pur_dm.setRowCount(0);
        pur_cgry_txt.setText("");
        pur_mfr_txt.setText("");
        pur_rate_txt.setText("");
        pur_qty_txt.setText("");
        pur_sp_txt.setText("");
        pur_total_cost.setText("0.0");
        
    }
    
    private void clear_mn_item_widget(){
        mn_i_id_lb.setText("ID");
        mn_cgry_txt.setText("");
        mn_mfg_txt.setText("");
        mn_rate_txt.setText("");
        mn_qty_txt.setText("");
        mn_sp_txt.setText("");
    }
    
    private void save_pur_billActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_pur_billActionPerformed
        // TODO add your handling code here:
        String supp_name = supp_name_txt.getText();
        String supp_contact = supp_contact_txt.getText();
        String supp_email = supp_email_txt.getText();
//        String regex_email = "^(.+)@(.+)$";
//        String regex_contact = "[7-9][0-9]{9}";
        int pur_bill_no = rand.nextInt(4000);

        DefaultTableModel dm = (DefaultTableModel) purchase_items_table.getModel();

        if (supp_name.isEmpty() || supp_contact.isEmpty() || supp_email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill all the details");
        }else if(supp_name.matches("^[0-9]+$")){
            JOptionPane.showMessageDialog(this, "Supplier name should be a string");
        }else if (supp_contact.matches("^[0-9]+$")) {
            if (supp_contact.matches("[7-9][0-9]{9}")) {
                if (supp_email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z]{1,10}+.[A-Za-z]{1,5}$")) {
                    if (pur_total_cost.getText().equals("0.0")) {
                        JOptionPane.showMessageDialog(this, "Add the item in cart");
                    } else {
                        Connection con = null;
                        con = ConnectionProvider();
                        String sup_sql = "insert into suppliers(bill_no, supplier_name, contact, email, total_cost, entry_date)"
                                + " values('" + pur_bill_no + "','" + supp_name + "'," + supp_contact + ",'" + supp_email + "','" + pur_total_cost.getText() + "','" + entry_date + "')";

                        try {
                            Statement st = con.createStatement();
                            st.executeUpdate(sup_sql);

                            for (int i = 0; i < dm.getRowCount(); i++) {
                                String category = dm.getValueAt(i, 0).toString();
                                String manufacturer = dm.getValueAt(i, 1).toString();
                                String size = dm.getValueAt(i, 2).toString();
                                String rate = dm.getValueAt(i, 3).toString();
                                String quantity = dm.getValueAt(i, 4).toString();
                                String selling_price = dm.getValueAt(i, 5).toString();
                                String total = dm.getValueAt(i, 6).toString();

                                String item_sql = "insert into items(bill_no, category, manufacturer, size, rate, quantity, selling_price, total)"
                                        + " values('" + pur_bill_no + "','" + category + "','" + manufacturer + "','" + size + "','" + rate + "','" + quantity + "','" + selling_price + "','" + total + "')";

//                            Statement st=con.createStatement();
                                st.executeUpdate(item_sql);

                            }
                            retrieve_sup_details();
                            retrieve_items_details();
                            clear_pur_widget();
                            // record added.
                        } catch (SQLException ex) {
                            Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Email format is not correct");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Contact no must be of 10 digit and start with 7 to 9");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Character is not allowed");
        }
        
    }//GEN-LAST:event_save_pur_billActionPerformed

    private void logout_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout_btnActionPerformed
        // TODO add your handling code here:
        dispose();
//        Login_Window lwindow = new Login_Window();
//        lwindow.show();
    }//GEN-LAST:event_logout_btnActionPerformed

    private void pur_rate_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pur_rate_txtKeyReleased
        // TODO add your handling code here:
        if (pur_rate_txt.getText().matches("^[0-9]+$")  || pur_rate_txt.getText().matches("")) {
            validate_rate.setText("");
        } else {
            validate_rate.setText("Only numbers");
        }
    }//GEN-LAST:event_pur_rate_txtKeyReleased

    private void pur_qty_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pur_qty_txtKeyReleased
        // TODO add your handling code here:
        if (pur_qty_txt.getText().matches("^[0-9]+$")  || pur_qty_txt.getText().matches("")) {
            validate_qty.setText("");
        } else {
            validate_qty.setText("Only numbers");
        }
    }//GEN-LAST:event_pur_qty_txtKeyReleased

    private void pur_sp_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pur_sp_txtKeyReleased
        // TODO add your handling code here:
        if (pur_sp_txt.getText().matches("^[0-9]+$") || pur_sp_txt.getText().matches("")) {
            validate_sp.setText("");
        } else {
            validate_sp.setText("Only numbers");
        }
    }//GEN-LAST:event_pur_sp_txtKeyReleased

    private void sale_qty_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sale_qty_txtKeyReleased
        // TODO add your handling code here:
        if (sale_qty_txt.getText().matches("^[0-9]+$") || sale_qty_txt.getText().matches("")) {
            validate_sale_qty.setText("");
        } else {
            validate_sale_qty.setText("Only numbers");
        }
    }//GEN-LAST:event_sale_qty_txtKeyReleased

    private void sale_sp_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sale_sp_txtKeyReleased
        // TODO add your handling code here:
        if (sale_sp_txt.getText().matches("^[0-9]+$") || sale_sp_txt.getText().matches("")) {
            validate_sale_sp.setText("");
        } else {
            validate_sale_sp.setText("Only numbers");
        }
    }//GEN-LAST:event_sale_sp_txtKeyReleased

    private void sale_generate_billActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sale_generate_billActionPerformed
        // TODO add your handling code here:
        String cust_name = cust_name_txt.getText();
        String cust_contact = cust_contact_txt.getText();
        String cust_email = cust_email_txt.getText();
        String discount = sale_discount_txt.getText();
        String paid = sale_paid_txt.getText();
        float paid_am=Float.parseFloat(sale_total_cost_lb.getText())-Float.parseFloat(discount);
        String regex_email = "^(.+)@(.+)$";
        String regex_contact = "[7-9][0-9]{9}";
        int sale_bill_no = rand.nextInt(4000);

        DefaultTableModel dm = (DefaultTableModel) sale_items_table.getModel();

        if (cust_name.isEmpty() || cust_contact.isEmpty() || cust_email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill all the details");
        }else if(cust_name.matches("^[0-9]+$")){
            JOptionPane.showMessageDialog(this, "Customer name should be a string");
        }else if (cust_contact.matches("^[0-9]+$")) {
            if (cust_contact.matches("[7-9][0-9]{9}")) {
                if (cust_email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z]{1,10}+.[A-Za-z]{1,5}$")) {
                    if (sale_total_cost_lb.getText().equals("0.0")) {
                        JOptionPane.showMessageDialog(this, "Add the item in cart");
                    } else if (sale_paid_txt.getText().equals("") || (Float.parseFloat(paid)<paid_am)) {
                        JOptionPane.showMessageDialog(this, "Pay the Amount");
                    } else {
                        Connection con = null;
                        con = ConnectionProvider();
                        String cust_sql = "insert into sales(bill_no, customer_name, contact, email, total_cost, discount, paid, entry_date)"
                                + " values('" + sale_bill_no + "','" + cust_name + "'," + cust_contact + ",'" + cust_email + "','" + sale_total_cost_lb.getText() + "','" + discount + "','" + paid + "','" + entry_date + "')";

                        Statement st = null;
                        try {
                            st = con.createStatement();
                            st.executeUpdate(cust_sql);
                        } catch (SQLException ex) {
                            Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
                        }


                        Document doc = new Document();
                        String userDirectory = System.getProperty("user.dir");

                        try {
                            PdfWriter.getInstance(doc, new FileOutputStream(userDirectory + "/customer_bills/" + sale_bill_no + ".pdf"));
                            doc.open();

//                          FontSelector fs = new FontSelector();
                            Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, Font.BOLD);
//			  fs.addFont(font);
                            String para = "Welcome to Jay Gurudev Cloth Store";
                            Paragraph heading = new Paragraph(para, font);
                            heading.setSpacingAfter(20.0f);
                            heading.setAlignment(Paragraph.ALIGN_CENTER);
                            Paragraph name = new Paragraph(cust_name);
                            name.setIndentationLeft(20);
                            Paragraph contact = new Paragraph(cust_contact);
                            contact.setIndentationLeft(20);
                            Paragraph email = new Paragraph(cust_email);
                            email.setIndentationLeft(20);

                            PdfPTable table = new PdfPTable(5);
                            table.setWidthPercentage(100);
//                          table.setWidths(new float[] {2,1,1,3,3});
                            table.setSpacingBefore(20.0f);
                            table.addCell("Product");
                            table.addCell("Size");
                            table.addCell("Qty");
                            table.addCell("Unit Price");
                            table.addCell("Total");

                            for (int i = 0; i < dm.getRowCount(); i++) {
                                String category = dm.getValueAt(i, 0).toString();
                                String manufacturer = dm.getValueAt(i, 0).toString();
                                String size = dm.getValueAt(i, 2).toString();
                                String quantity = dm.getValueAt(i, 3).toString();
                                String selling_price = dm.getValueAt(i, 4).toString();
                                String total = dm.getValueAt(i, 5).toString();

//                                ResultSet uprs;
                                try {
                                    PreparedStatement qt_st = con.prepareStatement("select i_id,quantity from items where category='" + category + "' and manufacturer='" + manufacturer + "' and size='" + size + "'");
                                    ResultSet uprs = qt_st.executeQuery();                                                                    
                                    while (uprs.next()) {
                                        String id=uprs.getString("i_id");
//                                        System.out.println(id);
                                        int db_qty = uprs.getInt("quantity");
//                                        System.out.println(id);
                                        int rem_qty=db_qty - Integer.parseInt(quantity);
                                        qt_st.execute("update items set quantity='"+rem_qty+"' WHERE i_id = '"+id+"'");
//                                        uprs.updateInt("quantity", db_qty - Integer.parseInt(quantity));
//                                        uprs.updateRow();
                                    }
                                } catch (SQLException ex) {
                                    Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                table.addCell(category);
                                table.addCell(size);
                                table.addCell(quantity);
                                table.addCell(selling_price);
                                table.addCell(total);

                            }

                            PdfPTable accounts = new PdfPTable(2);
                            accounts.setWidthPercentage(100);
                            accounts.addCell("Subtotal  Rs." + sale_total_cost_lb.getText());
                            accounts.addCell("Discount  Rs." + discount);
                            PdfPCell summaryU = new PdfPCell(accounts);
                            summaryU.setColspan(6);
                            table.addCell(summaryU);

                            PdfPTable total_amt = new PdfPTable(1);
                            total_amt.setWidthPercentage(100);
                            total_amt.addCell("Total Amount  Rs." + paid);
                            PdfPCell summaryL = new PdfPCell(total_amt);
                            summaryL.setColspan(6);
                            table.addCell(summaryL);

                            doc.add(heading);
                            doc.add(name);
                            doc.add(contact);
                            doc.add(email);
                            doc.add(table);

                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (DocumentException ex) {
                            Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        doc.close();

                        File pdfFile = new File("customer_bills/" + sale_bill_no + ".pdf");
                        try {
                            Desktop.getDesktop().open(pdfFile);
                        } catch (IOException ex) {
                            Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        retrieve_sup_details();
                        retrieve_items_details();
                        retrieve_sales_details();
                        clear_sale_widget();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Email format is not correct");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Contact no must be of 10 digit and start with 7 to 9");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Character is not allowed");
        }
        
    }//GEN-LAST:event_sale_generate_billActionPerformed

    private void sale_discount_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sale_discount_txtKeyReleased
        // TODO add your handling code here:
//        float discount=Float.parseFloat(sale_discount_txt.getText());
//        float fl=Float.parseFloat(sale_total_cost_lb.getText());
//        String paid=String.valueOf(fl-discount);
//        if(discount>0 || discount==Float.parseFloat("")){
//            sale_paid_txt.setText(paid);
//        }


    }//GEN-LAST:event_sale_discount_txtKeyReleased

    private void srch_sup_d_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_srch_sup_d_txtKeyReleased
        // TODO add your handling code here:
        DefaultTableModel sup_dm = (DefaultTableModel) sup_details_table.getModel();
        String Search = srch_sup_d_txt.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(sup_dm);
        sup_details_table.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(Search));
    }//GEN-LAST:event_srch_sup_d_txtKeyReleased

    private void sup_details_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sup_details_tableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel sup_dm = (DefaultTableModel) sup_details_table.getModel();
        int selectedRowIndex = sup_details_table.getSelectedRow();

        String invoice = sup_dm.getValueAt(selectedRowIndex, 1).toString();
        
        Connection con=null;
        con=ConnectionProvider();
        try {
            Statement st=con.createStatement();
            ResultSet tb_rs=st.executeQuery("select * from items where bill_no="+invoice);
            DefaultTableModel items_dm = (DefaultTableModel) show_items_table.getModel();
            items_dm.setRowCount(0);
            while(tb_rs.next()){
               String id=tb_rs.getString("i_id"); 
               String invoice_no=tb_rs.getString("bill_no");
               String category=tb_rs.getString("category");
               String manufacturer=tb_rs.getString("manufacturer");
               String size=tb_rs.getString("size");
               String rate=tb_rs.getString("rate");
               String quantity=tb_rs.getString("quantity");
               String s_price=tb_rs.getString("selling_price");
               String total=tb_rs.getString("total");
               
               
               String itemstb_data[]={id,invoice_no,category,manufacturer,size,rate,quantity,s_price,total};
               
               items_dm.addRow(itemstb_data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }//GEN-LAST:event_sup_details_tableMouseClicked

    private void search_mn_itemsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_mn_itemsKeyReleased
        // TODO add your handling code here:
        DefaultTableModel items_dm = (DefaultTableModel) mn_items_details_table.getModel();
        String Search = search_mn_items.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(items_dm);
        mn_items_details_table.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(Search));
    }//GEN-LAST:event_search_mn_itemsKeyReleased

    private void mn_size_cmboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mn_size_cmboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mn_size_cmboxActionPerformed

    private void mn_items_details_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mn_items_details_tableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel mn_items_dm = (DefaultTableModel) mn_items_details_table.getModel();
        int selectedRowIndex = mn_items_details_table.getSelectedRow();
        
        mn_i_id_lb.setText(mn_items_dm.getValueAt(selectedRowIndex, 0).toString());
        mn_cgry_txt.setText(mn_items_dm.getValueAt(selectedRowIndex, 1).toString());
        mn_mfg_txt.setText(mn_items_dm.getValueAt(selectedRowIndex, 2).toString());
        mn_size_cmbox.setSelectedItem(mn_items_dm.getValueAt(selectedRowIndex, 3).toString());
        mn_rate_txt.setText(mn_items_dm.getValueAt(selectedRowIndex, 4).toString());
        mn_qty_txt.setText(mn_items_dm.getValueAt(selectedRowIndex, 5).toString());
        mn_sp_txt.setText(mn_items_dm.getValueAt(selectedRowIndex, 6).toString());
    }//GEN-LAST:event_mn_items_details_tableMouseClicked

    private void mn_update_item_detailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mn_update_item_detailsActionPerformed
        // TODO add your handling code here:
        String mn_i_id = mn_i_id_lb.getText();
        String mn_category = mn_cgry_txt.getText();
        String mn_mfg = mn_mfg_txt.getText();
        Object mn_size = mn_size_cmbox.getSelectedItem();
        String mn_rate = mn_rate_txt.getText();
        String mn_qty = mn_qty_txt.getText();
        String mn_sp = mn_sp_txt.getText();
        
        
        if (mn_i_id.isEmpty() || mn_category.isEmpty() || mn_mfg.isEmpty() || mn_rate.isEmpty() || mn_qty.isEmpty() || mn_sp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select the Item");
        } else {
            double sale_price = Double.parseDouble(mn_rate_txt.getText());
            double sale_qty = Double.parseDouble(mn_qty_txt.getText());
            double total_price_item = (sale_price * sale_qty);
            int result = JOptionPane.showConfirmDialog(this,"Sure? You want to Update?", "Confirm Updation",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
            if(result==JOptionPane.YES_NO_OPTION){
                try {
                    Connection con = null;
                    con = ConnectionProvider();
                    Statement up_st=null;
                    up_st = con.createStatement();
                    up_st.execute("update items set category='"+mn_category+"', manufacturer='"+mn_mfg+"', size='"+mn_size+"', rate='"+mn_rate+"', quantity='"+mn_qty+"', selling_price='"+mn_sp+"', total='"+total_price_item+"' WHERE i_id = '"+mn_i_id+"'");
                    retrieve_items_details();
                    clear_mn_item_widget();
                } catch (SQLException ex) {
                    Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        
        
        
    }//GEN-LAST:event_mn_update_item_detailsActionPerformed

    private void show_sale_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_show_sale_btnActionPerformed
        // TODO add your handling code here:
        DefaultTableModel sale_dm = (DefaultTableModel) sale_details_table.getModel();
        int selectedRowIndex = sale_details_table.getSelectedRow();
        if(selectedRowIndex==-1){
            JOptionPane.showMessageDialog(this, "Select the Row");
        }else{
            String invoice = sale_dm.getValueAt(selectedRowIndex, 1).toString();
            File pdfFile = new File("customer_bills/" + invoice + ".pdf");
            try {
                Desktop.getDesktop().open(pdfFile);
            } catch (IOException ex) {
                Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_show_sale_btnActionPerformed

    private void sale_details_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sale_details_tableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_sale_details_tableMouseClicked

    private void srch_sale_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_srch_sale_txtKeyReleased
        // TODO add your handling code here:
        DefaultTableModel sales_dm = (DefaultTableModel) sale_details_table.getModel();
        String Search = srch_sale_txt.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(sales_dm);
        sale_details_table.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(Search));
    }//GEN-LAST:event_srch_sale_txtKeyReleased

    private void cust_contact_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cust_contact_txtKeyReleased
        // TODO add your handling code here:
        if (cust_contact_txt.getText().matches("^[0-9]+$") || cust_contact_txt.getText().matches("")) {
            validate_cust_cont.setText("");
        } else {
            validate_cust_cont.setText("Only numbers");
        }
    }//GEN-LAST:event_cust_contact_txtKeyReleased

    private void cust_name_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cust_name_txtKeyReleased
        // TODO add your handling code here:
        if (cust_name_txt.getText().matches("^[a-zA-Z]+$") || cust_name_txt.getText().matches("")) {
            validate_cust_name.setText("");
        } else {
            validate_cust_name.setText("Only String");
        }
    }//GEN-LAST:event_cust_name_txtKeyReleased

    private void sale_cgry_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sale_cgry_txtKeyReleased
        // TODO add your handling code here:
        if (sale_cgry_txt.getText().matches("^[a-zA-Z]+$") || sale_cgry_txt.getText().matches("")) {
            validate_sale_cgry.setText("");
        } else {
            validate_sale_cgry.setText("Only String");
        }
    }//GEN-LAST:event_sale_cgry_txtKeyReleased

    private void sale_mfr_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sale_mfr_txtKeyReleased
        // TODO add your handling code here:
        if (sale_mfr_txt.getText().matches("^[a-zA-Z]+$") || sale_mfr_txt.getText().matches("")) {
            validate_sale_mfr.setText("");
        } else {
            validate_sale_mfr.setText("Only String");
        }
    }//GEN-LAST:event_sale_mfr_txtKeyReleased

    private void pur_mfr_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pur_mfr_txtKeyReleased
        // TODO add your handling code here:
        if (pur_mfr_txt.getText().matches("^[a-zA-Z]+$") || pur_mfr_txt.getText().matches("")) {
            validate_pur_mfr.setText("");
        } else {
            validate_pur_mfr.setText("Only String");
        }
    }//GEN-LAST:event_pur_mfr_txtKeyReleased

    private void pur_cgry_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pur_cgry_txtKeyReleased
        // TODO add your handling code here:
        if (pur_cgry_txt.getText().matches("^[a-zA-Z]+$") || pur_cgry_txt.getText().matches("")) {
            validate_pur_cgry.setText("");
        } else {
            validate_pur_cgry.setText("Only String");
        }
    }//GEN-LAST:event_pur_cgry_txtKeyReleased

    private void supp_name_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_supp_name_txtKeyReleased
        // TODO add your handling code here:
        if (supp_name_txt.getText().matches("^[a-zA-Z]+$") || supp_name_txt.getText().matches("")) {
            validate_sup_name.setText("");
        } else {
            validate_sup_name.setText("Only String");
        }
    }//GEN-LAST:event_supp_name_txtKeyReleased

    private void supp_contact_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_supp_contact_txtKeyReleased
        // TODO add your handling code here:
        if (supp_contact_txt.getText().matches("^[0-9]+$") || supp_contact_txt.getText().matches("")) {
            validate_sup_cont.setText("");
        } else {
            validate_sup_cont.setText("Only numbers");
        }
    }//GEN-LAST:event_supp_contact_txtKeyReleased

    private void cust_email_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cust_email_txtKeyReleased
        // TODO add your handling code here:
        if (cust_email_txt.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z]{1,10}+.[A-Za-z]{1,5}$") || cust_email_txt.getText().matches("")) {
            validate_cust_email.setText("");
        } else {
            validate_cust_email.setText("Format is not correct");
        }
    }//GEN-LAST:event_cust_email_txtKeyReleased

    private void supp_email_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_supp_email_txtKeyReleased
        // TODO add your handling code here:
        if (supp_email_txt.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z]{1,10}+.[A-Za-z]{1,5}$") || supp_email_txt.getText().matches("")) {
            validate_sup_email.setText("");
        } else {
            validate_sup_email.setText("Format is not correct");
        }
    }//GEN-LAST:event_supp_email_txtKeyReleased

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
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashBoard().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel HeadingPanel;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel NavigationPanel;
    private javax.swing.JButton POS_btn;
    private javax.swing.JPanel POS_sale_panel;
    private javax.swing.JLabel curdate;
    private javax.swing.JLabel curtime;
    private javax.swing.JTextField cust_contact_txt;
    private javax.swing.JTextField cust_email_txt;
    private javax.swing.JTextField cust_name_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JButton logout_btn;
    private javax.swing.JButton manage_items_btn;
    private javax.swing.JPanel manage_items_panel;
    private javax.swing.JTextField mn_cgry_txt;
    private javax.swing.JButton mn_delete_item;
    private javax.swing.JLabel mn_i_id_lb;
    private javax.swing.JTable mn_items_details_table;
    private javax.swing.JTextField mn_mfg_txt;
    private javax.swing.JTextField mn_qty_txt;
    private javax.swing.JTextField mn_rate_txt;
    private javax.swing.JComboBox mn_size_cmbox;
    private javax.swing.JTextField mn_sp_txt;
    private javax.swing.JButton mn_update_item_details;
    private javax.swing.JButton pur_add_to_cart;
    private javax.swing.JTextField pur_cgry_txt;
    private javax.swing.JTextField pur_mfr_txt;
    private javax.swing.JTextField pur_qty_txt;
    private javax.swing.JTextField pur_rate_txt;
    private javax.swing.JButton pur_reset_all;
    private javax.swing.JComboBox pur_size_cmbox;
    private javax.swing.JTextField pur_sp_txt;
    private javax.swing.JLabel pur_total_cost;
    private javax.swing.JButton purchase_items_btn;
    private javax.swing.JPanel purchase_items_panel;
    private javax.swing.JTable purchase_items_table;
    private javax.swing.JButton sale_add_to_cart;
    private javax.swing.JTextField sale_cgry_txt;
    private javax.swing.JTable sale_details_table;
    private javax.swing.JTextField sale_discount_txt;
    private javax.swing.JButton sale_generate_bill;
    private javax.swing.JTable sale_items_table;
    private javax.swing.JTextField sale_mfr_txt;
    private javax.swing.JTextField sale_paid_txt;
    private javax.swing.JTextField sale_qty_txt;
    private javax.swing.JButton sale_reset_all;
    private javax.swing.JComboBox sale_size_cmbox;
    private javax.swing.JTextField sale_sp_txt;
    private javax.swing.JLabel sale_total_cost_lb;
    private javax.swing.JButton sales_btn;
    private javax.swing.JPanel sales_report_panel;
    private javax.swing.JButton save_pur_bill;
    private javax.swing.JTextField search_mn_items;
    private javax.swing.JTable show_items_table;
    private javax.swing.JButton show_sale_btn;
    private javax.swing.JTextField srch_sale_txt;
    private javax.swing.JTextField srch_sup_d_txt;
    private javax.swing.JTable sup_details_table;
    private javax.swing.JTextField supp_contact_txt;
    private javax.swing.JTextField supp_email_txt;
    private javax.swing.JTextField supp_name_txt;
    private javax.swing.JButton suppliers_btn;
    private javax.swing.JPanel suppliers_panel;
    private javax.swing.JLabel validate_cust_cont;
    private javax.swing.JLabel validate_cust_email;
    private javax.swing.JLabel validate_cust_name;
    private javax.swing.JLabel validate_pur_cgry;
    private javax.swing.JLabel validate_pur_mfr;
    private javax.swing.JLabel validate_qty;
    private javax.swing.JLabel validate_rate;
    private javax.swing.JLabel validate_sale_cgry;
    private javax.swing.JLabel validate_sale_mfr;
    private javax.swing.JLabel validate_sale_qty;
    private javax.swing.JLabel validate_sale_sp;
    private javax.swing.JLabel validate_sp;
    private javax.swing.JLabel validate_sup_cont;
    private javax.swing.JLabel validate_sup_email;
    private javax.swing.JLabel validate_sup_name;
    // End of variables declaration//GEN-END:variables

    

    

}
