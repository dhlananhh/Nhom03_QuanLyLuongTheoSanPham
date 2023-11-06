package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;

public class GUI_BangChamCong extends JFrame {
	private JPanel pnContent;
	private JLabel lblTieuDe, lblNgay, lblMaCN, lblTenCD, lblMaSP, lblTenSP, lblMaCD, lblTenCN, lblChiTieu, lblSoLuongHT;
	private Font BVNPro;
	private DefaultTableModel modelCC, modelHT;
	private JTable tableCC, tableHT;
	//private JLabel lblNgay;
	private JButton btnLoc, btnLuu;
	private JComboBox<String> cbLoc;
	private JTextField txtLoc, txtMaCN, txtTenCN, txtMaSP, txtTenSP, txtMaCD, txtTenCD, txtChiTieu, txtSoLuongHT;
	private JCheckBox ckDatCT;
	public GUI_BangChamCong() {
		setTitle("Bảng chấm công");
		setSize(1300, 700);
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//setSize(screenSize.width, screenSize.height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//font
		try {
			String fileName = "fonts/BeVietnamPro-Black.ttf";
			BVNPro = Font.createFont(Font.TRUETYPE_FONT, new File(fileName)).deriveFont(30f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fileName)));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		//panel
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		add(pnContent);
		
		JPanel pnTop = new JPanel();
		pnTop.setBackground(new Color(0, 102, 204));
		
		lblTieuDe = new JLabel("BẢNG CHẤM CÔNG");
		lblTieuDe.setFont(new Font("BeVietnamPro-Black", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.WHITE);
		pnTop.add(lblTieuDe);
		pnContent.add(pnTop, BorderLayout.NORTH);
		
		JPanel pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setBackground(new Color(245, 251, 255));
		
		JPanel pnBot = new JPanel();
		pnContent.add(pnBot, BorderLayout.SOUTH);
		pnBot.setBackground(new Color(245, 251, 255));
		pnBot.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		//pnCenter
		Box b, b1, b2, b2_1, b2_2;
		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b2_1 = Box.createVerticalBox();
		b2_2 = Box.createVerticalBox();
		//Box 1
		b1.add(lblNgay = new JLabel("Ngày:"));
		b1.add(Box.createHorizontalStrut(20));
		
		JDateChooser chooserNgay = new JDateChooser();
        chooserNgay.getCalendarButton().setToolTipText("Chọn ngày");
        chooserNgay.getCalendarButton().setPreferredSize(new Dimension(30, 24));
        chooserNgay.getCalendarButton().setBackground(new Color(138, 255, 255));
        chooserNgay.setFont(new Font("Arial", Font.PLAIN, 16));
        chooserNgay.setDateFormatString("dd/MM/yyyy");
        chooserNgay.setBorder(new LineBorder(new Color(138, 255, 255), 1, true));
        
        b1.add(chooserNgay);
        b1.add(Box.createHorizontalStrut(100));
        b1.add(cbLoc = new JComboBox<String>());
        cbLoc.addItem("Mã nhân viên");
        cbLoc.addItem("Mã sản phẩm");
        cbLoc.addItem("Mã công đoạn");
        b1.add(Box.createHorizontalStrut(10));
        b1.add(btnLoc = new JButton("Lọc"));
        b1.add(Box.createHorizontalStrut(10));
        b1.add(txtLoc = new JTextField(5));
        b1.add(Box.createHorizontalStrut(10));
        b1.add(ckDatCT = new JCheckBox("Đặt chỉ tiêu"));
        
        btnLoc.setBackground(new Color(0, 153, 204));
        btnLoc.setForeground(Color.WHITE);
        //Box 2
        //Box 2 trái
        JPanel pnChamCong = new JPanel();
        pnChamCong.setBackground(new Color(130, 199, 250));
        Box c1, c2, c3, c4, c5;
        c1 = Box.createHorizontalBox();
        c2 = Box.createHorizontalBox();
        c3 = Box.createHorizontalBox();
        c4 = Box.createHorizontalBox();
        c5 = Box.createHorizontalBox();
        
        c1.add(lblMaCN = new JLabel("Mã công nhân:"));
        c1.add(Box.createHorizontalStrut(20));
        c1.add(txtMaCN = new JTextField(12));
        c1.add(Box.createHorizontalStrut(40));
        c1.add(lblTenCN = new JLabel("Tên công nhân:"));
        c1.add(Box.createHorizontalStrut(20));
        c1.add(txtTenCN = new JTextField(12));
        
        c2.add(lblMaSP = new JLabel("Mã sản phẩm:"));
        c2.add(Box.createHorizontalStrut(20));
        c2.add(txtMaSP = new JTextField(12));
        c2.add(Box.createHorizontalStrut(40));
        c2.add(lblTenSP = new JLabel("Tên sản phẩm:"));
        c2.add(Box.createHorizontalStrut(20));
        c2.add(txtTenSP = new JTextField(12));
        
        c3.add(lblMaCD = new JLabel("Mã công đoạn:"));
        c3.add(Box.createHorizontalStrut(20));
        c3.add(txtMaCD = new JTextField(12));
        c3.add(Box.createHorizontalStrut(40));
        c3.add(lblTenCD = new JLabel("Tên công đoạn:"));
        c3.add(Box.createHorizontalStrut(20));
        c3.add(txtTenCD = new JTextField(12));
        
        c4.add(lblChiTieu = new JLabel("Chỉ tiêu:"));
        c4.add(Box.createHorizontalStrut(20));
        c4.add(txtChiTieu = new JTextField(12));
        c4.add(Box.createHorizontalStrut(40));
        c4.add(lblSoLuongHT= new JLabel("Số lượng hoàn thành:"));
        c4.add(Box.createHorizontalStrut(20));
        c4.add(txtSoLuongHT = new JTextField(12));
        
        c5.add(Box.createHorizontalStrut(400));
        c5.add(btnLuu = new JButton("Lưu"));
        btnLuu.setBackground(new Color(0, 153, 204));
        btnLuu.setForeground(Color.WHITE);
        //preference
        lblMaCN.setPreferredSize(lblMaCD.getPreferredSize());
        lblMaSP.setPreferredSize(lblMaCD.getPreferredSize());
        lblChiTieu.setPreferredSize(lblMaCD.getPreferredSize());
        
        lblTenCD.setPreferredSize(lblSoLuongHT.getPreferredSize());
        lblTenCN.setPreferredSize(lblSoLuongHT.getPreferredSize());
        lblTenSP.setPreferredSize(lblSoLuongHT.getPreferredSize());
        b2_1.add(Box.createRigidArea(new Dimension(20, 20)));
        b2_1.add(c1);
        b2_1.add(Box.createRigidArea(new Dimension(0, 20)));
        b2_1.add(c2);
        b2_1.add(Box.createRigidArea(new Dimension(0, 20)));
        b2_1.add(c3);
        b2_1.add(Box.createRigidArea(new Dimension(0, 20)));
        b2_1.add(c4);
        b2_1.add(Box.createRigidArea(new Dimension(0, 20)));
        b2_1.add(c5);
        pnChamCong.add(b2_1);
        b2.add(pnChamCong);
        
        
        //box 2 phải
        JPanel pnHoanThanh = new JPanel();
        pnHoanThanh.setBorder(BorderFactory.createTitledBorder("Sản phẩm hoàn thành"));
        pnHoanThanh.setBackground(new Color(245, 251, 255));
        String col2[] = {"Mã sản phẩm","Tên sản phẩm","Số lượng hoàn thành"};
        modelHT = new DefaultTableModel(col2,0);
        tableHT = new JTable(modelHT);
        JScrollPane scroll2 = new JScrollPane(tableHT);
        b2_2.add(scroll2);
        pnHoanThanh.add(b2_2);
        b2.add(Box.createHorizontalStrut(50));
        b2.add(pnHoanThanh);
        
        
        b.add(Box.createRigidArea(new Dimension(20, 20)));
        b.add(b1);
        b.add(Box.createRigidArea(new Dimension(0, 20)));
        b.add(b2);
        pnCenter.add(b);
        //
        txtMaCN.setEditable(false);
        txtTenCN.setEditable(false);
        txtMaCD.setEditable(false);
        txtTenCD.setEditable(false);
        txtChiTieu.setEditable(false);
        txtMaSP.setEditable(false);
        txtTenSP.setEditable(false);
		//pbot
		String col[] = {"Mã công nhân","Tên công nhân","Mã sản phẩm","Tên sản phẩm","Mã công đoạn"
				,"Tên công đoạn","Chỉ tiêu","Hoàn thành"};
		modelCC = new DefaultTableModel(col,0);
		tableCC = new JTable(modelCC);
		JScrollPane scroll = new JScrollPane(tableCC);
		scroll.setPreferredSize(new Dimension(1000, 200));
		pnBot.add(scroll);
		
	}
	public static void main(String[] args) {
		FlatLightLaf.setup();	
		new GUI_BangChamCong().setVisible(true);
	}
}
