package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dendi_voucher")
public class Voucher {
	@Id
	@Column(name = "id_voucher")
	private String idVoucher;
	
	@Column(name = "id_provider")
	private String idProvider;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "nominal_voucher")
	private float nominalVoucher;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "nama_voucher")
	private String namaVoucher;
	
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "idVoucher")
//	private List<Pembelian> pembelians;
	public Voucher() {
		super();
	}

public Voucher(String idVoucher, String idProvider, float price, float nominalVoucher, String status, String namaVoucher) {
	super();
	this.idVoucher = idVoucher;
	this.idProvider = idProvider;
	this.price = price;
	this.nominalVoucher = nominalVoucher;
	this.status = status;
	this.namaVoucher = namaVoucher;
}

public String getIdVoucher() {
	return idVoucher;
}

public void setIdVoucher(String idVoucher) {
	this.idVoucher = idVoucher;
}

public String getIdProvider() {
	return idProvider;
}

public void setIdProvider(String idProvider) {
	this.idProvider = idProvider;
}

public float getPrice() {
	return price;
}

public void setPrice(float price) {
	this.price = price;
}

public float getNomonalVoucher() {
	return nominalVoucher;
}

public void setNominalVoucher(float nominalVoucher) {
	this.nominalVoucher = nominalVoucher;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getNamaVoucher() {
	return namaVoucher;
}

public void setNamaVoucher(String namaVoucher) {
	this.namaVoucher = namaVoucher;
}

@Override
public String toString() {
	return "Voucher [idVoucher=" + idVoucher + ", idProvider=" + idProvider + ", price=" + price + ", nominalVoucher="
			+ nominalVoucher + ", status=" + status + ", namaVoucher=" + namaVoucher + "]";
}
	
	
	
	
}
