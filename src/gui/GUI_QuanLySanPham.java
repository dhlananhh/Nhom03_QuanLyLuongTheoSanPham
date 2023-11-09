package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;

import connection.ConnectDB;
import dao.CongDoan_dao;
import dao.SanPham_dao;
import entity.CongDoan;
import entity.SanPham;

public class GUI_QuanLySanPham extends JFrame implements ActionListener, MouseListener {
	private JPanel pnContent;
	private JLabel lblTieuDe, lblMSP, lblMCD, lblTenSP, lblTenCD, lblLuongSP, lblTrangThai, lblSoLuong, lblGiaThanh, lblThuTu;
	private DefaultTableModel modelSP;
	private JTable tableSP, tableCD;
	private DefaultTableModel modelCD;
	private JButton btnLoc, btnThem, btnSua, btnXoa, btnXoaTrang, btnLuu;
	private JComboBox<String> cbLoc, cbTrangThai;
	private JTextField txtLoc, txtMSP, txtMCD, txtTenSP, txtTenCD, txtLuongSP, txtSoLuong, txtGiaThanh, txtThuTu;
	private CongDoan_dao cd_dao;
	private SanPham_dao sp_dao;
	private int soLuongSP;
	private int soLuongCD;
	private Map<String, Boolean> daNhap = new HashMap<>();
	private Font BVNPro;
	public GUI_QuanLySanPham() {
		setTitle("Quản lý sản phẩm");
		setSize(1300, 700);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//font
		try {
			String fileName = "fonts/BeVietnamPro-Regular.ttf";
			BVNPro = Font.createFont(Font.TRUETYPE_FONT, new File(fileName)).deriveFont(30f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fileName)));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		//connect
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cd_dao = new CongDoan_dao();
		sp_dao = new SanPham_dao();
		
		//panel
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		add(pnContent);
		
		JPanel pnTop = new JPanel();
		pnTop.setBackground(new Color(0, 102, 204));
		
		lblTieuDe = new JLabel("QUẢN LÝ SẢN PHẨM");
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
		//Center
		Box b ,b1, b2;
		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		//b1
		b1.add(Box.createHorizontalStrut(700));
		b1.add(btnLoc = new JButton("Lọc"));
		b1.add(Box.createHorizontalStrut(10));
		b1.add(cbLoc = new JComboBox<String>());
		cbLoc.addItem("Mã sản phẩm");
		cbLoc.addItem("Tên sản phẩm");
		cbLoc.addItem("Trạng thái");
		b1.add(Box.createHorizontalStrut(10));
		b1.add(txtLoc = new JTextField(15));
		
		btnLoc.setBackground(new Color(0, 153, 204));
		btnLoc.setForeground(Color.WHITE);
		//tạo panel chứa box b3 chứa dữ liệu.
		JPanel pnBoxContent = new JPanel();
		pnBoxContent.setBackground(new Color(206, 234, 255));
		b2.add(pnBoxContent);
	
		Box b3, b3_1, b3_2, b3_3, b3_4, b3_5, b3_6;
		b3 = Box.createVerticalBox();
		b3_1 = Box.createHorizontalBox();
		b3_2 = Box.createHorizontalBox();
		b3_3 = Box.createHorizontalBox();
		b3_4 = Box.createHorizontalBox();
		b3_5 = Box.createHorizontalBox();
		b3_6 = Box.createHorizontalBox();
		//add giá trị
		b3_1.add(lblMSP = new JLabel("Mã sản phẩm:"));
		b3_1.add(Box.createHorizontalStrut(20));
		b3_1.add(txtMSP = new JTextField(15));
		b3_1.add(Box.createHorizontalStrut(200));
		b3_1.add(lblMCD = new JLabel("Mã công đoạn:"));
		b3_1.add(Box.createHorizontalStrut(20));
		b3_1.add(txtMCD = new JTextField(15));
		
		b3_2.add(lblTenSP = new JLabel("Tên sản phẩm:"));
		b3_2.add(Box.createHorizontalStrut(20));
		b3_2.add(txtTenSP = new JTextField(15));
		b3_2.add(Box.createHorizontalStrut(200));
		b3_2.add(lblTenCD = new JLabel("Tên công đoạn:"));
		b3_2.add(Box.createHorizontalStrut(20));
		b3_2.add(txtTenCD = new JTextField(15));
		
		b3_3.add(lblTrangThai = new JLabel("Trạng thái:"));
		b3_3.add(Box.createHorizontalStrut(20));
		b3_3.add(cbTrangThai = new JComboBox<String>());
		cbTrangThai.addItem("Còn sản xuất");
		cbTrangThai.addItem("Ngưng sản xuất");
		b3_3.add(Box.createHorizontalStrut(248));
		b3_3.add(lblLuongSP = new JLabel("Lương sản phẩm:"));
		b3_3.add(Box.createHorizontalStrut(22));
		b3_3.add(txtLuongSP = new JTextField(15));
		
		b3_4.add(lblSoLuong = new JLabel("Số lượng:"));
		b3_4.add(Box.createHorizontalStrut(20));
		b3_4.add(txtSoLuong = new JTextField(15));
		b3_4.add(Box.createHorizontalStrut(200));
		b3_4.add(lblThuTu = new JLabel("Thứ tự:"));
		b3_4.add(Box.createHorizontalStrut(21));
		b3_4.add(txtThuTu = new JTextField(15));
		
		b3_5.add(lblGiaThanh = new JLabel("Giá thành:"));
		b3_5.add(Box.createHorizontalStrut(20));
		b3_5.add(txtGiaThanh = new JTextField(15));
		b3_5.add(Box.createHorizontalStrut(480));
		
		b3_6.add(btnThem = new JButton("Thêm"));
		b3_6.add(Box.createHorizontalStrut(50));
		b3_6.add(btnSua = new JButton("Sửa"));
		b3_6.add(Box.createHorizontalStrut(50));
		b3_6.add(btnXoa = new JButton("Xóa"));
		b3_6.add(Box.createHorizontalStrut(50));
		b3_6.add(btnXoaTrang = new JButton("Xóa Trắng"));
		b3_6.add(Box.createHorizontalStrut(50));
		b3_6.add( btnLuu = new JButton("Lưu"));
		
		btnThem.setBackground(new Color(0, 153, 204));
		btnThem.setForeground(Color.WHITE);
        btnSua.setBackground(new Color(0, 153, 204));
        btnSua.setForeground(Color.WHITE);
        btnXoa.setBackground(new Color(0, 153, 204));
        btnXoa.setForeground(Color.WHITE);
        btnXoaTrang.setBackground(new Color(0, 153, 204));
        btnXoaTrang.setForeground(Color.WHITE);
        btnLuu.setBackground(new Color(0, 153, 204));
        btnLuu.setForeground(Color.WHITE);
		//preferencec b3
		lblMSP.setPreferredSize(lblTenSP.getPreferredSize());
		lblTrangThai.setPreferredSize(lblTenSP.getPreferredSize());
		lblSoLuong.setPreferredSize(lblTenSP.getPreferredSize());
		lblGiaThanh.setPreferredSize(lblTenSP.getPreferredSize());
		
		lblMCD.setPreferredSize(lblLuongSP.getPreferredSize());
		lblTenCD.setPreferredSize(lblLuongSP.getPreferredSize());
		lblThuTu.setPreferredSize(lblLuongSP.getPreferredSize());
		//add có box b3 nhỏ
		b3.add(Box.createRigidArea(new Dimension(20,20)));
		b3.add(b3_1);
		b3.add(Box.createRigidArea(new Dimension(0,20)));
		b3.add(b3_2);
		b3.add(Box.createRigidArea(new Dimension(0,20)));
		b3.add(b3_3);
		b3.add(Box.createRigidArea(new Dimension(0,20)));
		b3.add(b3_4);
		b3.add(Box.createRigidArea(new Dimension(0,20)));
		b3.add(b3_5);
		b3.add(Box.createRigidArea(new Dimension(0,20)));
		b3.add(b3_6);
		pnBoxContent.add(b3);
		//add box b1 b2 vào b lớn
		b.add(Box.createRigidArea(new Dimension(20,20)));
		b.add(b1);
		b.add(Box.createRigidArea(new Dimension(20,20)));
		b.add(b2);
		pnCenter.add(b);
		//bot
		Box c;
		c = Box.createHorizontalBox();
		//table San pham
		JPanel pnSP = new JPanel();
		pnSP.setBorder(BorderFactory.createTitledBorder("Bảng sản phẩm"));
		pnSP.setBackground(new Color(245, 251, 255));
		String col2[] = {"Mã sản phẩm", "Tên sản phẩm","Số lượng","Giá thành","Trạng thái"};
		modelSP = new DefaultTableModel(col2, 0);
		tableSP = new JTable(modelSP);
        
		JScrollPane scrollSP = new JScrollPane(tableSP);
		scrollSP.setPreferredSize(new Dimension(500, 250));
		pnSP.add(scrollSP);
		c.add(pnSP);
		c.add(Box.createHorizontalStrut(80));
		//table Cong doan
		JPanel pnCD = new JPanel();
		pnCD.setBorder(BorderFactory.createTitledBorder("Bảng công đoạn"));
		pnCD.setBackground(new Color(245, 251, 255));
		String col3[] = {"Mã công đoạn", "Tên công đoạn","Lương SP","Thứ tự"};
		modelCD = new DefaultTableModel(col3, 0);
		tableCD = new JTable(modelCD);
		JScrollPane scrollCD = new JScrollPane(tableCD);
		scrollCD.setPreferredSize(new Dimension(500, 250));
		pnCD.add(scrollCD);
		c.add(pnCD);
		pnBot.add(c);
		//docdulieu
		//docDuLieuCDVaoTable();
		docDuLieuSPVaoTable();
		txtMSP.setEditable(false);
		txtMCD.setEditable(false);
		khoaCD();
		//action
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLoc.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnLuu.addActionListener(this);
		tableSP.addMouseListener(this);
		tableCD.addMouseListener(this);
	}
	public static void main(String[] args) {
		FlatLightLaf.setup();		
		new GUI_QuanLySanPham().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnThem)) {
			if(txtTenSP.isEditable()) {
				if(kiemTraRangBuocSP()) {
					themDuLieuSP();
					khoaSP();
					moCD();
				}
			}
			else if(txtTenCD.isEditable()) {
				if(kiemTraRangBuocCD()) {
					themDuLieuCD(txtMSP.getText());
					txtMCD.setText("");
					txtTenCD.setText("");
					txtLuongSP.setText("");
					txtThuTu.setText("");
				}
			}
			
		}
		if(o.equals(btnSua)) {
			if(!txtTenSP.getText().equals("") && !txtTenCD.getText().equals("")) {
				if(cbTrangThai.getSelectedItem().equals("Ngưng sản xuất")) {
					JOptionPane.showMessageDialog(this, "Sản phẩm ngưng sản xuất, không thể sửa công đoạn");
					return;
				}
				txtTenCD.setText("");
				txtLuongSP.setText("");
				txtThuTu.setText("");
				moCD();
			}
			else {
				moSP();
				khoaCD();
				txtTenSP.setText("");
				txtGiaThanh.setText("");
				txtSoLuong.setText("");
			}
		}
		if(o.equals(btnLuu)) {
			if(!txtTenSP.getText().equals("") && !txtTenCD.getText().equals("")) {
				suaDuLieuSP();
				modelSP.getDataVector().removeAllElements();
				docDuLieuSPVaoTable();
				suaDuLieuCD();
				modelCD.getDataVector().removeAllElements();
				docDuLieuCDVaoTable(txtMSP.getText());
			}
			else if(txtTenSP.getText().equals("")) {
				suaDuLieuCD();
				modelCD.getDataVector().removeAllElements();
				docDuLieuCDVaoTable(txtMSP.getText());
			}
			else {
				suaDuLieuSP();
				modelSP.getDataVector().removeAllElements();
				docDuLieuSPVaoTable();
			}
		}
		if(o.equals(btnXoa)) {
			int r = tableSP.getSelectedRow();
			String maSP = modelSP.getValueAt(r, 0).toString();
			modelSP.removeRow(r);
			txtMSP.setText("");
			txtTenSP.setText("");
			txtGiaThanh.setText("");
			txtSoLuong.setText("");
			ArrayList<CongDoan> dsCD = cd_dao.getAllCongDoanTheoSP(maSP);
			for(int i = dsCD.size()-1; i >= 0 ; i--) {
				modelCD.removeRow(i);
			}
		}	
		if(o.equals(btnLoc)) {
			if(cbLoc.getSelectedItem().equals("Mã sản phẩm")) {
				modelSP.getDataVector().removeAllElements();
				timSPTheoMa();
			}
			else if(cbLoc.getSelectedItem().equals("Tên sản phẩm")) {
				modelSP.getDataVector().removeAllElements();
				timSPTheoTen();
			}
			else {
				modelSP.getDataVector().removeAllElements();
				timSPTheoTrangThai();
			}
		}
		if(o.equals(btnXoaTrang)) {
			txtMSP.setText("");
			txtTenSP.setText("");
			txtMCD.setText("");
			txtTenCD.setText("");
			txtLuongSP.setText("");
			txtGiaThanh.setText("");
			txtThuTu.setText("");
			txtSoLuong.setText("");
			khoaCD();
			moSP();
		}
	}
	public void docDuLieuSPVaoTable() {
		List<SanPham> dsSP  = sp_dao.getalltbSanPham();
		soLuongSP = dsSP.size();
		for (SanPham sp : dsSP) {
			modelSP.addRow(new Object[] {
				sp.getMaSP(), sp.getTenSP(), sp.getSoLuongTon(), sp.getGiaThanh(),
				sp.getTrangThai()==true?"Còn sản xuất":"Ngưng sản xuất"
			});
		}
	}
	public void docDuLieuCDVaoTable(String maSP) {
		List<CongDoan> dsCD = cd_dao.getAllCongDoanTheoSP(maSP);
		soLuongCD = dsCD.size();
		for (CongDoan cd : dsCD) {
			modelCD.addRow(new Object[] {cd.getMaCD(), cd.getTenCD()
					,cd.getLuongTheoSanPham(),
					cd.getThuTu()});
		}
	}
	public boolean kiemTraRangBuocSP() {
		String tenSP = txtTenSP.getText();
		String soLuong = txtSoLuong.getText();
		String giaThanh = txtGiaThanh.getText();
		
		if(!tenSP.matches("[a-zA-Z' ]+")) {
			JOptionPane.showMessageDialog(this,	"Tên sản phẩm phải là ký tự");
			return false;
		}
		if(soLuong.length()>0) {
			try {
				int sl = Integer.parseInt(soLuong);
				if(sl < 0) {
					JOptionPane.showMessageDialog(this,	"Số lượng sản phẩm không được âm");
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this,	"Số lượng phải là số");
				return false;
			}
		}
		if(giaThanh.length()>0) {
			try {
				Double gt = Double.parseDouble(giaThanh);
				if(gt < 0) {
					JOptionPane.showMessageDialog(this,	"Giá thành không âm");
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this,	"Giá thành phải là số");
				return false;
			}
		}
		
		return true;
	}
	public boolean kiemTraRangBuocCD() {
		String tenCD = txtTenCD.getText();
		String thuTu = txtThuTu.getText();
		String luongSP = txtLuongSP.getText();
		if(!tenCD.matches("[a-zA-Z' ]+")) {
			JOptionPane.showMessageDialog(this,	"Tên công đoạn phải là ký tự");
			return false;
		}
		if(luongSP.length()>0) {
			try {
				Double sp = Double.parseDouble(luongSP);
				if(sp < 0) {
					JOptionPane.showMessageDialog(this,	"Lương SP của công đoạn không âm");
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this,	"Lương SP phải là số");
				return false;
			}
		}
		if(thuTu.length()>0) {
			
			try {
				int tt = Integer.parseInt(thuTu);
				if(tt < 0 || tt > 5) {
					JOptionPane.showMessageDialog(this,	"Thứ từ của công đoạn phải từ 1-5");
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this,	"Thứ tự phải là số");
				return false;
			}
		}
		return true;
	}
	public boolean themDuLieuSP() {	
		String tenSP = txtTenSP.getText();
		int soLuongTon = Integer.parseInt(txtSoLuong.getText());
		String trangThaiString = cbTrangThai.getSelectedItem().toString();
		boolean trangThai = (trangThaiString.equals("Còn sản xuất"))?true:false;
		double gia = Double.parseDouble(txtGiaThanh.getText());
		
		String maSP = String.format("%s%02d","SP",++soLuongSP);
		SanPham sp = new SanPham(maSP, tenSP, soLuongTon, gia, trangThai);	
		try {
			sp_dao.themSP(sp);
			modelSP.addRow(new Object[] {sp.getMaSP(), sp.getTenSP(), sp.getSoLuongTon()
					,sp.getGiaThanh(), sp.getTrangThai()==true?"Còn sản xuất":"Ngưng sản xuất"});
			txtMSP.setText(maSP);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	public boolean themDuLieuCD(String slsp) {
		String tenCD = txtTenCD.getText();
		double luongSP = Double.parseDouble(txtLuongSP.getText());
		int thuTu = Integer.parseInt(txtThuTu.getText());
		String maCD = String.format("%s%s%02d", slsp,"CD", ++soLuongCD);
		String maSP = txtMSP.getText();
		SanPham sp = new SanPham(maSP);
		CongDoan cd = new CongDoan(maCD, tenCD, luongSP, sp, thuTu);
		try {
			cd_dao.themCD(cd);
			modelCD.addRow(new Object[] {cd.getMaCD(), cd.getTenCD(), cd.getLuongTheoSanPham()
					,cd.getThuTu()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	public void khoaCD() {
		txtMCD.setEditable(false);
		txtTenCD.setEditable(false);
		txtLuongSP.setEditable(false);
		txtThuTu.setEditable(false);
	}
	public void moCD() {
		txtTenCD.setEditable(true);
		txtLuongSP.setEditable(true);
		txtThuTu.setEditable(true);
	}
	public void khoaSP() {
		txtMSP.setEditable(false);
		txtTenSP.setEditable(false);
		txtGiaThanh.setEditable(false);
		txtSoLuong.setEditable(false);
	}
	public void moSP() {
		txtTenSP.setEditable(true);
		txtGiaThanh.setEditable(true);
		txtSoLuong.setEditable(true);
	}
	public void timSPTheoMa() {
		String maSP = txtLoc.getText();
		List<SanPham> dssp = sp_dao.getalltbSanPhamTheoMa(maSP);
		for (SanPham sp : dssp) {
			modelSP.addRow(new Object[] {sp.getMaSP(), sp.getTenSP(), sp.getSoLuongTon()
					,sp.getGiaThanh(), sp.getTrangThai()==true?"Còn sản xuất":"Ngưng sản xuất"});
		}
	}
	public void timSPTheoTen() {
		String tenSP = txtLoc.getText();
		List<SanPham> dssp = sp_dao.getalltbSanPhamTheoTen(tenSP);
		for (SanPham sp : dssp) {
			modelSP.addRow(new Object[] {sp.getMaSP(), sp.getTenSP(), sp.getSoLuongTon()
					,sp.getGiaThanh(), sp.getTrangThai()==true?"Còn sản xuất":"Ngưng sản xuất"});
		}
	}
	public void timSPTheoTrangThai() {
		String tt = txtLoc.getText();
		boolean trangThai = true;
		if(tt.equals("Còn sản xuất")) {
			List<SanPham> dssp = sp_dao.getalltbSanPhamTheoTrangThai(trangThai);
			for (SanPham sp : dssp) {
				modelSP.addRow(new Object[] {sp.getMaSP(), sp.getTenSP(), sp.getSoLuongTon()
						,sp.getGiaThanh(), sp.getTrangThai()==true?"Còn sản xuất":"Ngưng sản xuất"});
			}
		}
		else {
			trangThai = false;
			List<SanPham> dssp = sp_dao.getalltbSanPhamTheoTrangThai(trangThai);
			for (SanPham sp : dssp) {
				modelSP.addRow(new Object[] {sp.getMaSP(), sp.getTenSP(), sp.getSoLuongTon()
						,sp.getGiaThanh(), sp.getTrangThai()==true?"Còn sản xuất":"Ngưng sản xuất"});
			}
		}	
	}
	public void suaDuLieuSP() {
		String maSp = txtMSP.getText();
		String tenSP = txtTenSP.getText();
		int soLuongTon = Integer.parseInt(txtSoLuong.getText());
		double giaThanh = Double.parseDouble(txtGiaThanh.getText());
		String trangThaiString = cbTrangThai.getSelectedItem().toString();
		boolean trangThai = (trangThaiString.equals("Còn sản xuất"))?true:false;
		SanPham sp = new SanPham(maSp, tenSP, soLuongTon, giaThanh, trangThai);
		sp_dao.capNhatSP(sp);	
	}
	public void suaDuLieuCD() {
		String maCD = txtMCD.getText();
		String tenCD = txtTenCD.getText();
		double luongTheoSP = Double.parseDouble(txtLuongSP.getText());
		int thuTu = Integer.parseInt(txtThuTu.getText());
		String maSP = txtMSP.getText();
		SanPham sp = new SanPham(maSP);
		CongDoan cd = new CongDoan(maCD, tenCD, luongTheoSP, sp, thuTu);
		cd_dao.capNhatCD(cd);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if(o.equals(tableSP)) {
		int rowSP = tableSP.getSelectedRow();
			txtMSP.setText(modelSP.getValueAt(rowSP, 0).toString());
			txtTenSP.setText(modelSP.getValueAt(rowSP, 1).toString());
			txtSoLuong.setText(modelSP.getValueAt(rowSP, 2).toString());
			txtGiaThanh.setText(modelSP.getValueAt(rowSP, 3).toString());
			cbTrangThai.setSelectedItem(modelSP.getValueAt(rowSP, 4).toString());
			String maSP = txtMSP.getText();
			if (!daNhap.containsKey(maSP) || !daNhap.get(maSP)) {
				modelCD.getDataVector().removeAllElements();
	            docDuLieuCDVaoTable(maSP);
	            daNhap.clear();
	            daNhap.put(maSP, true);
	        }
			khoaSP();
			moCD();
			if(cbTrangThai.getSelectedItem().equals("Ngưng sản xuất")) {
				khoaCD();
			}
		}
		if(o.equals(tableCD)) {
			int rowCD = tableCD.getSelectedRow();
			txtMCD.setText(modelCD.getValueAt(rowCD, 0).toString());
			txtTenCD.setText(modelCD.getValueAt(rowCD, 1).toString());
			txtLuongSP.setText(modelCD.getValueAt(rowCD, 2).toString());
			txtThuTu.setText(modelCD.getValueAt(rowCD, 3).toString());
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}