package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dendi_tagihan")
public class Tagihan {
	@Id
	@Column(name = "id_tagihan")
	private String idTagihan;
	
	@Column(name = "tanggal_tagihan")
	private Date tanggalTagihan;
	
	@Column(name = "id_nasabah")
	private String idNasabah;
	
	@Column(name = "total_tagihan")
	private float totalTagihan;
	
	@Column(name = "status")
	private String status;
	

	public Tagihan() {
		super();
	}

	public Tagihan(Date tanggalTagihan, String idNasabah, float totalTagihan, String status) {
		super();
		this.tanggalTagihan = tanggalTagihan;
		this.idNasabah = idNasabah;
		this.totalTagihan = totalTagihan;
		this.status = status;
	}

	public Tagihan(String idTagihan, Date tanggalTagihan, String idNasabah, float totalTagihan, String status) {
		super();
		this.idTagihan = idTagihan;
		this.tanggalTagihan = tanggalTagihan;
		this.idNasabah = idNasabah;
		this.totalTagihan = totalTagihan;
		this.status = status;
	}

	public String getIdTagihan() {
		return idTagihan;
	}

	public void setIdTagihan(String idTagihan) {
		this.idTagihan = idTagihan;
	}

	public Date getTanggalTagihan() {
		return tanggalTagihan;
	}

	public void setTanggalTagihan(Date tanggalTagihan) {
		this.tanggalTagihan = tanggalTagihan;
	}

	public String getIdNasabah() {
		return idNasabah;
	}

	public void setIdNasabah(String idNasabah) {
		this.idNasabah = idNasabah;
	}

	public float getTotalTagihan() {
		return totalTagihan;
	}

	public void setTotalTagihan(float totalTagihan) {
		this.totalTagihan = totalTagihan;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
