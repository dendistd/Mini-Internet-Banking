package com.example.demo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

//@Entity
public class InputCreatePembelian {
//	@Id
//	@Column(name = "id_pembelian")
	@JsonProperty("id_pembelian")
	private String idPembelian;
	
//	@Column(name = "id_nasabah")
	@JsonProperty("id_nasabah")
	private String idNasabah;
	
//	@Column(name = "id_provider")
	@JsonProperty("id_provider")
	private String idProvider;
	
//	@Column(name = "nama_provider")
	@JsonProperty("nama_provider")
	private String namaProvider;
	
//	@Column(name = "no_handphone")
	@JsonProperty("no_handphone")
	private String noHandphone;
	
//	@Column(name = "id_voucher")
	@JsonProperty("id_voucher")
	private String idVoucher;
	
//	@Column(name = "nama_voucher")
	@JsonProperty("nama_voucher")
	private String namaVoucher;
	
//	@Column(name = "nominal_pembelian")
	@JsonProperty("nominal_voucher")
	private float nominalVoucher;
	
//	@Column(name = "challenge_token")
	@JsonProperty("challenge_token")
	private String challengeToken;
	
//	@Column(name = "respon_keybca")
	@JsonProperty("respon_keybca_appli")
	private String responKeyBcaAppli;
	
	public InputCreatePembelian() {
		super();
	}

	public InputCreatePembelian(String idPembelian, String idNasabah, String idProvider, String namaProvider,
			String noHandphone, String idVoucher, String namaVoucher, float nominalVoucher, String challengeToken,
			String responKeyBcaAppli) {
		super();
		this.idPembelian = idPembelian;
		this.idNasabah = idNasabah;
		this.idProvider = idProvider;
		this.namaProvider = namaProvider;
		this.noHandphone = noHandphone;
		this.idVoucher = idVoucher;
		this.namaVoucher = namaVoucher;
		this.nominalVoucher = nominalVoucher;
		this.challengeToken = challengeToken;
		this.responKeyBcaAppli = responKeyBcaAppli;
	}

	public String getIdPembelian() {
		return idPembelian;
	}

	public void setIdPembelian(String idPembelian) {
		this.idPembelian = idPembelian;
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

	public String getIdVoucher() {
		return idVoucher;
	}

	public void setIdVoucher(String idVoucher) {
		this.idVoucher = idVoucher;
	}

	public String getNamaVoucher() {
		return namaVoucher;
	}

	public void setNamaVoucher(String namaVoucher) {
		this.namaVoucher = namaVoucher;
	}

	public float getNominalVoucher() {
		return nominalVoucher;
	}

	public void setNominalVoucher(float nominalVoucher) {
		this.nominalVoucher = nominalVoucher;
	}

	public String getChallengeToken() {
		return challengeToken;
	}

	public void setChallengeToken(String challengeToken) {
		this.challengeToken = challengeToken;
	}

	public String getResponKeyBcaAppli() {
		return responKeyBcaAppli;
	}

	public void setResponKeyBcaAppli(String responKeyBcaAppli) {
		this.responKeyBcaAppli = responKeyBcaAppli;
	}

	@Override
	public String toString() {
		return "InputCreatePembelian [idPembelian=" + idPembelian + ", idNasabah=" + idNasabah + ", idProvider="
				+ idProvider + ", namaProvider=" + namaProvider + ", noHandphone=" + noHandphone + ", idVoucher="
				+ idVoucher + ", namaVoucher=" + namaVoucher + ", nominalVoucher=" + nominalVoucher
				+ ", challengeToken=" + challengeToken + ", responKeyBcaAppli=" + responKeyBcaAppli + "]";
	}
	
	

	
	

}
