package com.example.demo.dto;




import com.example.demo.payload.GagalOutputSchema;
import com.fasterxml.jackson.annotation.JsonProperty;
//@Entity
public class ResponseCreatePembelian {
	
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
	
//	@Column(name = "nominal_voucher")
	@JsonProperty("nominal_voucher")
	private float nominalVoucher;
	
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
	
	
	public ResponseCreatePembelian() {
		super();
	}
	
	
	//new ResponseCreatePembelian(pembelian.getIdPembelian(),pembelian.getTanggalPembelian().toString(), pembelian.getIdNasabah(), pembelian.getIdProvider(), (float) pembelian.getNominalVoucher(), pembelian.getNoHandphone(), pembelian.getIdVoucher(), pembelian.getStatusTransaksi());
	
	
	public ResponseCreatePembelian( String idPembelian,String tanggalPembelian, String nasabah, String provider,
			float nominalVoucher, String noHandphone, String voucher,String statusTransaksi) {
		super();
		this.idPembelian = idPembelian;
		this.tanggalPembelian = tanggalPembelian;
		this.nasabah = nasabah;
		this.provider = provider;
		this.nominalVoucher = nominalVoucher;
		this.noHandphone = noHandphone;
		this.voucher = voucher;
		this.statusTransaksi = statusTransaksi;
	}

	public ResponseCreatePembelian( String idPembelian,String tanggalPembelian, String nasabah, String provider,
			float nominalVoucher, String voucher,String statusTransaksi) {
		super();
		this.idPembelian = idPembelian;
		this.tanggalPembelian = tanggalPembelian;
		this.nasabah = nasabah;
		this.provider = provider;
		this.nominalVoucher = nominalVoucher;
		
		this.voucher = voucher;
		this.statusTransaksi = statusTransaksi;
	}
	
	
	public ResponseCreatePembelian(String tanggalPembelian, String idPembelian, String nasabah, String provider,
			String namaProvider, float nominalVoucher , String voucher, String namaVoucher,
			String statusTransaksi) {
		super();
		this.tanggalPembelian = tanggalPembelian;
		this.idPembelian = idPembelian;
		this.nasabah = nasabah;
		this.provider = provider;
		this.namaProvider = namaProvider;
		this.nominalVoucher = nominalVoucher;
	
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


	public String getNasabah() {
		return nasabah;
	}


	public void setNasabah(String nasabah) {
		this.nasabah = nasabah;
	}


	public String getProvider() {
		return provider;
	}


	public void setProvider(String provider) {
		this.provider = provider;
	}


	public String getNamaProvider() {
		return namaProvider;
	}


	public void setNamaProvider(String namaProvider) {
		this.namaProvider = namaProvider;
	}


	public float getNominalVoucher() {
		return nominalVoucher;
	}


	public void setNominalVoucher(float nominalVoucher) {
		this.nominalVoucher = nominalVoucher;
	}


	public String getNoHandphone() {
		return noHandphone;
	}


	public void setNoHandphone(String noHandphone) {
		this.noHandphone = noHandphone;
	}


	public String getVoucher() {
		return voucher;
	}


	public void setVoucher(String voucher) {
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


	@Override
	public String toString() {
		return "ResponseCreatePembelian [tanggalPembelian=" + tanggalPembelian + ", idPembelian=" + idPembelian
				+ ", nasabah=" + nasabah + ", provider=" + provider + ", namaProvider=" + namaProvider
				+ ", nominalVoucher=" + nominalVoucher + ", noHandphone=" + noHandphone + ", voucher=" + voucher
				+ ", namaVoucher=" + namaVoucher + ", statusTransaksi=" + statusTransaksi + "]";
	}
	
	
	
	
}
