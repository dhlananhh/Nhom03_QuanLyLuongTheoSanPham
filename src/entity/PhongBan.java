package entity;


import java.io.Serializable;
import java.util.Objects;


public class PhongBan implements Serializable {
	private String ID;
	private String tenPhongBan;
	private String ghiChu;
	
	
	//---constructors---
	public PhongBan() {
		
	}

	
	public PhongBan(String ID) {
		this.ID = ID;
	}

	
	public PhongBan(String ID, String tenPhongBan) {
		this.ID = ID;
		this.tenPhongBan = tenPhongBan;
	}


	public PhongBan(String ID, String tenPhongBan, String ghiChu) {
		this.ID = ID;
		this.tenPhongBan = tenPhongBan;
		this.ghiChu = ghiChu;
	}


	//---getters/setters---
	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public String getTenPhongBan() {
		return tenPhongBan;
	}


	public void setTenPhongBan(String tenPhongBan) {
		this.tenPhongBan = tenPhongBan;
	}


	public String getGhiChu() {
		return ghiChu;
	}


	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}


	//---hashCode/equals---
	@Override
	public int hashCode() {
		return Objects.hash(ID);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhongBan other = (PhongBan) obj;
		return Objects.equals(ID, other.ID);
	}
	
}