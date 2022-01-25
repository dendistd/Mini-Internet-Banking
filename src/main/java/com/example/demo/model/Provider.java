package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dendi_provider")
public class Provider {
	@Id
	@Column(name = "id_provider")
	private String idProvider;
	@Column(name = "nama_provider")
	private String namaProvider;
	@Column(name = "biaya_admin")
	private float biayaAdmin;
	@Column(name = "status")
	private String status;
	
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "idProvider")
//	List<Pembelian> pembelians;
//	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idProvider")
//	List<Voucher> vouchers;
//	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
//	List<Pembayaran> pembayarans;
	
	public Provider() {
		super();
	}

public Provider(String idProvider, String namaProvider, float biayaAdmin, String status) {
	super();
	this.idProvider = idProvider;
	this.namaProvider = namaProvider;
	this.biayaAdmin = biayaAdmin;
	this.status = status;
}

public String getIdProvider() {
	return idProvider;
}

public void setIdProvider(String idProvider) {
	this.idProvider = idProvider;
}

public String getNamaProvider() {
	return namaProvider;
}

public void setNamaProvider(String namaProvider) {
	this.namaProvider = namaProvider;
}

public float getBiayaAdmin() {
	return biayaAdmin;
}

public void setBiayaAdmin(float biayaAdmin) {
	this.biayaAdmin = biayaAdmin;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

@Override
public String toString() {
	return "Provider [idProvider=" + idProvider + ", namaProvider=" + namaProvider + ", biayaAdmin=" + biayaAdmin
			+ ", status=" + status + "]";
}
	
	
}
