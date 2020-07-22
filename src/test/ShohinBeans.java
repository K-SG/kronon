package test;

import java.io.Serializable;
import java.sql.Date;

public class ShohinBeans implements Serializable{

	private String id;
	private String name;
	private int kakaku;
	private Date startdate;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKakaku() {
		return kakaku;
	}
	public void setKakaku(int kakaku) {
		this.kakaku = kakaku;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

}
