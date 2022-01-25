package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputPembayaran {
	
	/* Request parameter:
1.	ID Nasabah
2.	ID Provider
3.	Nama Provider
4.	Nomor Handphone
5.	Nominal pembayaran
6.	Respon keyBCA Appli1
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
	
	@JsonProperty("respon_key_bca_appli1")
	private String ResponKeyBcaAppli1;
	
	public InputPembayaran() {
		super();
	}
	
	public InputPembayaran(String idPembayaran, String idNasabah, String idProvider, String namaProvider,
			String noHandphone, float nominalPembayaran, String responKeyBcaAppli1) {
		super();
		this.idPembayaran = idPembayaran;
		this.idNasabah = idNasabah;
		this.idProvider = idProvider;
		this.namaProvider = namaProvider;
		this.noHandphone = noHandphone;
		this.nominalPembayaran = nominalPembayaran;
		ResponKeyBcaAppli1 = responKeyBcaAppli1;
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

	public String getResponKeyBcaAppli1() {
		return ResponKeyBcaAppli1;
	}

	public void setResponKeyBcaAppli1(String responKeyBcaAppli1) {
		ResponKeyBcaAppli1 = responKeyBcaAppli1;
	}

	@Override
	public String toString() {
		return "InputPembayaran [idNasabah=" + idNasabah + ", idProvider=" + idProvider + ", namaProvider="
				+ namaProvider + ", noHandphone=" + noHandphone + ", nominalPembayaran=" + nominalPembayaran
				+ ", ResponKeyBcaAppli1=" + ResponKeyBcaAppli1 + "]";
	}
	
	

}
