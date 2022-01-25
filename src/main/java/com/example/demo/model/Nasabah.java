package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dendi_nasabah")
public class Nasabah {
	@Id
	@Column(name = "id_nasabah")
	private String idNasabah;
	@Column(name = "nama_nasabah")
	private String namaNasabah;
	@Column(name = "no_kartu")
	private String noKartu;
	@Column(name = "no_rekening")
	private String noRekening;
	@Column(name = "no_handphone")
	private String noHandphone;
	@Column(name = "alamat")
	private String alamat;
	@Column(name = "saldo")
	private float saldo;

	@Column(name = "status")
	private String status;
	
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "idNasabah")
//	private List<Pembelian> pembelians;
//	
//	@OneToMany(mappedBy = "idNasabah")
//	private List<Pembayaran> pembayarans;
	
//	@OneToMany(mappedBy = "nasabah")
//	private List<KartuKredit> kartukredits;
	
	public Nasabah() {
		super();
	}
	public Nasabah(String namaNasabah, String noKartu, String noRekening, String noHandphone, String alamat,
			float saldo, String status) {
		super();
		this.namaNasabah = namaNasabah;
		this.noKartu = noKartu;
		this.noRekening = noRekening;
		this.noHandphone = noHandphone;
		this.alamat = alamat;
		this.saldo = saldo;
		this.status = status;
	}
	public Nasabah(String idNasabah, String namaNasabah, String noKartu, String noRekening, String noHandphone,
			String alamat, float saldo, String status) {
		super();
		this.idNasabah = idNasabah;
		this.namaNasabah = namaNasabah;
		this.noKartu = noKartu;
		this.noRekening = noRekening;
		this.noHandphone = noHandphone;
		this.alamat = alamat;
		this.saldo = saldo;
		this.status = status;
	}
	public String getIdNasabah() {
		return idNasabah;
	}
	public void setIdNasabah(String idNasabah) {
		this.idNasabah = idNasabah;
	}
	public String getNamaNasabah() {
		return namaNasabah;
	}
	public void setNamaNasabah(String namaNasabah) {
		this.namaNasabah = namaNasabah;
	}
	public String getNoKartu() {
		return noKartu;
	}
	public void setNoKartu(String noKartu) {
		this.noKartu = noKartu;
	}
	public String getNoRekening() {
		return noRekening;
	}
	public void setNoRekening(String noRekening) {
		this.noRekening = noRekening;
	}
	public String getNoHandphone() {
		return noHandphone;
	}
	public void setNoHandphone(String noHandphone) {
		this.noHandphone = noHandphone;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
//	public List<Pembelian> getPembelians() {
//		return pembelians;
//	}
//	public void setPembelians(List<Pembelian> pembelians) {
//		this.pembelians = pembelians;
//	}
//	public List<Pembayaran> getPembayarans() {
//		return pembayarans;
//	}
//	public void setPembayarans(List<Pembayaran> pembayarans) {
//		this.pembayarans = pembayarans;
//	}
//	public List<KartuKredit> getKartukredits() {
//		return kartukredits;
//	}
//	public void setKartukredits(List<KartuKredit> kartukredits) {
//		this.kartukredits = kartukredits;
//	}
	
	
	
}
