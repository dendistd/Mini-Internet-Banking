package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dendi_transaksi_kartukredit")
public class TransaksiKartuKredit {
	@Id
	@Column(name = "id_transaksi_kartukredit")
	private String idTransaksiKartuKredit;
	@Column(name = "tanggal_transaksi")
	private Date tanggalTransaksi;
	@Column(name = "id_kartukredit")
	private String idKartuKredit;
	
	@Column(name = "nominal_bayar")
	private float nominalBayar;
	
	@Column(name = "status")
	private String status;
	public TransaksiKartuKredit() {
		super();
	}
	public TransaksiKartuKredit(String idTransaksiKartuKredit, Date tanggalTransaksi, String idKartuKredit,
			float nominalBayar, String status) {
		super();
		this.idTransaksiKartuKredit = idTransaksiKartuKredit;
		this.tanggalTransaksi = tanggalTransaksi;
		this.idKartuKredit = idKartuKredit;
		this.nominalBayar = nominalBayar;
		this.status = status;
	}
	public String getIdTransaksiKartuKredit() {
		return idTransaksiKartuKredit;
	}
	public void setIdTransaksiKartuKredit(String idTransaksiKartuKredit) {
		this.idTransaksiKartuKredit = idTransaksiKartuKredit;
	}
	public Date getTanggalTransaksi() {
		return tanggalTransaksi;
	}
	public void setTanggalTransaksi(Date tanggalTransaksi) {
		this.tanggalTransaksi = tanggalTransaksi;
	}
	public String getIdKartuKredit() {
		return idKartuKredit;
	}
	public void setIdKartuKredit(String idKartuKredit) {
		this.idKartuKredit = idKartuKredit;
	}
	public float getNominalBayar() {
		return nominalBayar;
	}
	public void setNominalBayar(float nominalBayar) {
		this.nominalBayar = nominalBayar;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
