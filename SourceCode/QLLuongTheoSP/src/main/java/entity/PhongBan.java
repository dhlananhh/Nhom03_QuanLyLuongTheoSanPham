package entity;

import java.util.Objects;

public class PhongBan {
	private String maPB;
	private String tenPB;
	
	
	public PhongBan() {
		
	}
	
	
	public PhongBan (String maPB) {
		this.maPB = maPB;
	}


	public PhongBan(String maPB, String tenPB) {
		super();
		this.maPB = maPB;
		this.tenPB = tenPB;
	}


	@Override
	public int hashCode() {
		return Objects.hash(maPB);
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
		return Objects.equals(maPB, other.maPB);
	}
}
