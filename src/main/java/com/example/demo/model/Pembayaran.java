package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DENDI_PEMBAYARAN")
public class Pembayaran {
	@Id
	@Column(name = "id_pembayaran")
	private String idPembayaran;
	@Column(name = "tanggal_pembayaran")
	private Date tanggalPembayaran;
	
	@Column(name = "id_nasabah")
	private String idNasabah;
	
	@Column(name = "id_provider")
	private String provider;
	
	@Column(name = "no_handphone")
	private String noHandphone;
	@Column(name = "nominal_pembayaran")
	private float nominalPembayaran;
	
	@Column(name = "status")
	private String status;
	@Column(name = "keterangan")
	private String keterangan;
	
	public Pembayaran() {
		super();
	}

	public Pembayaran(String idPembayaran, Date tanggalPembayaran, String idNasabah, String provider,
			String noHandphone, float nominalPembayaran, String status, String keterangan) {
		super();
		this.idPembayaran = idPembayaran;
		this.tanggalPembayaran = tanggalPembayaran;
		this.idNasabah = idNasabah;
		this.provider = provider;
		this.noHandphone = noHandphone;
		this.nominalPembayaran = nominalPembayaran;
		this.status = status;
		this.keterangan = keterangan;
	}

	public String getIdPembayaran() {
		return idPembayaran;
	}

	public void setIdPembayaran(String idPembayaran) {
		this.idPembayaran = idPembayaran;
	}

	public Date getTanggalPembayaran() {
		return tanggalPembayaran;
	}

	public void setTanggalPembayaran(Date tanggalPembayaran) {
		this.tanggalPembayaran = tanggalPembayaran;
	}

	public String getIdNasabah() {
		return idNasabah;
	}

	public void setIdNasabah(String idNasabah) {
		this.idNasabah = idNasabah;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getNoHandphone() {
		return noHandphone;
	}

	public void setNoHandphone(String noHandphone) {
		this.noHandphone = noHandphone;
	}

	public float getNominalPembayaran() {
		return nominalPembayaran;
	}

	public void setNominalPembayaran(float nominalPembayaran) {
		this.nominalPembayaran = nominalPembayaran;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	@Override
	public String toString() {
		return "Pembayaran [idPembayaran=" + idPembayaran + ", tanggalPembayaran=" + tanggalPembayaran + ", idNasabah="
				+ idNasabah + ", provider=" + provider + ", noHandphone=" + noHandphone + ", nominalPembayaran="
				+ nominalPembayaran + ", status=" + status + ", keterangan=" + keterangan + "]";
	}

	

	
	

}
