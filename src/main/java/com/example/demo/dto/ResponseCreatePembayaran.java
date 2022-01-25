package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseCreatePembayaran {
	/* 1.	ID Pembayaran
2.	ID Nasabah
3.	ID Provider
4.	Nama Provider
5.	Nomor Handphone
6.	Nominal pembayaran
7.	Status transaksi
*/ 
	@JsonProperty("id_pembayaran")
	private String idPembayaran;
	
	@JsonProperty("id_nasabah")
	private String idNasabah;
	
	@JsonProperty("id_provider")
	private String idProvider;
	
	@JsonProperty("nama_provider")
	private String namaProvider;
	
	@JsonProperty("no_handphone")
	private String noHandphone;
	
	@JsonProperty("nominal_pembayaran")
	private float nominalPembayaran;
	
	@JsonProperty("status_transaksi")
	private String statusTransaksi;
	
	public ResponseCreatePembayaran() {
		super();
	}

	public ResponseCreatePembayaran(String idPembayaran, String idNasabah, String idProvider, String namaProvider,
			String noHandphone, float nominalPembayaran, String statusTransaksi) {
		super();
		this.idPembayaran = idPembayaran;
		this.idNasabah = idNasabah;
		this.idProvider = idProvider;
		this.namaProvider = namaProvider;
		this.noHandphone = noHandphone;
		this.nominalPembayaran = nominalPembayaran;
		this.statusTransaksi = statusTransaksi;
	}

	public String getIdPembayaran() {
		return idPembayaran;
	}

	public void setIdPembayaran(String idPembayaran) {
		this.idPembayaran = idPembayaran;
	}

	public String getIdNasabah() {
		return idNasabah;
	}

	public void setIdNasabah(String idNasabah) {
		this.idNasabah = idNasabah;
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

	public String getStatusTransaksi() {
		return statusTransaksi;
	}

	public void setStatusTransaksi(String statusTransaksi) {
		this.statusTransaksi = statusTransaksi;
	}

	@Override
	public String toString() {
		return "ResponseCreatePembayaran [idPembayaran=" + idPembayaran + ", idNasabah=" + idNasabah + ", idProvider="
				+ idProvider + ", namaProvider=" + namaProvider + ", noHandphone=" + noHandphone
				+ ", nominalPembayaran=" + nominalPembayaran + ", statusTransaksi=" + statusTransaksi + "]";
	}
	
	
	

}
