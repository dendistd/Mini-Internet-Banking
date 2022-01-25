package com.example.demo.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

//@Entity
public class OutputPembelian {
//	@Column(name = "tanggal_pembelian")
	@JsonProperty("tanggal_pembelian")
	private String tanggalPembelian;
//	@Id
//	@Column(name = "id_pembelian")
	@JsonProperty("id_pembelian")
	private String idPembelian;
	
//	@Column(name = "id_nasabah")
	@JsonProperty("id_nasabah")
	private String nasabah;
//	@Column(name = "id_provider")
	@JsonProperty("id_provider")
	private String provider;
//	@Column(name = "nama_provider")
	@JsonProperty("nama_provider")
	private String namaProvider;
//	@Column(name = "no_handphone")
	@JsonProperty("no_handphone")
	private String noHandphone;
//	@Column(name = "id_voucher")
	@JsonProperty("id_voucher")
	private String voucher;
//	@Column(name = "nama_voucher")
	@JsonProperty("nama_voucher")
	private String namaVoucher;
//	@Column(name = "status_transaksi")
	@JsonProperty("status_transaksi")
	private String statusTransaksi;
	
	
	public OutputPembelian() {
		super();
	}


	public OutputPembelian(String tanggalPembelian, String idPembelian, String nasabah, String provider,
			String namaProvider, String noHandphone, String voucher, String namaVoucher, String statusTransaksi) {
		super();
		this.tanggalPembelian = tanggalPembelian;
		this.idPembelian = idPembelian;
		this.nasabah = nasabah;
		this.provider = provider;
		this.namaProvider = namaProvider;
		this.noHandphone = noHandphone;
		this.voucher = voucher;
		this.namaVoucher = namaVoucher;
		this.statusTransaksi = statusTransaksi;
	}

	public String getTanggalPembelian() {
		return tanggalPembelian;
	}


	public void setTanggalPembelian(String tanggalPembelian) {
		
		this.tanggalPembelian = tanggalPembelian;
	}


	public String getIdPembelian() {
		return idPembelian;
	}


	public void setIdPembelian(String idPembelian) {
		this.idPembelian = idPembelian;
	}


	public String getIdNasabah() {
		return nasabah;
	}


	public void setIdNasabah(String nasabah) {
		this.nasabah = nasabah;
	}


	public String getIdProvider() {
		return provider;
	}


	public void setIdProvider(String provider) {
		this.provider = provider;
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


	public String getIdVoucher() {
		return voucher;
	}


	public void setIdVoucher(String voucher) {
		this.voucher = voucher;
	}


	public String getNamaVoucher() {
		return namaVoucher;
	}


	public void setNamaVoucher(String namaVoucher) {
		this.namaVoucher = namaVoucher;
	}


	public String getStatusTransaksi() {
		return statusTransaksi;
	}


	public void setStatusTransaksi(String statusTransaksi) {
		this.statusTransaksi = statusTransaksi;
	}
	
	

}
