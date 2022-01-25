package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "dendi_pembelian")
public class Pembelian {

		@Id
		@Column(name = "id_pembelian")
		private String idPembelian;
//		@ManyToOne(fetch = FetchType.LAZY)
//		@JoinColumn(name = "id_nasabah")
		@Column(name = "id_nasabah")
		private String idNasabah;
		
//		@ManyToOne(fetch = FetchType.LAZY)
//		@JoinColumn(name = "id_provider")
		@Column(name = "id_provider")
		private String idProvider;
		
//		@ManyToOne(fetch = FetchType.LAZY)
//		@JoinColumn(name = "id_voucher")
		@Column(name = "id_voucher")
		private String idVoucher;
		
		@Column(name = "tanggal_pembelian")
		private Date tanggalPembelian;
		
		@Column(name = "no_handphone")
		private String noHandphone;
		
		@Column(name = "nominal_voucher")
		private double nominalVoucher;
		
		@Column(name = "status_transaksi")
		private String statusTransaksi;
		
		@Column(name = "keterangan")
		private String keterangan;
		
		public Pembelian() {
			super();
		}
		
		//new Pembelian(input.getIdPembelian(), input.getIdNasabah(), input.getIdProvider(), input.getIdVoucher(), input.getNoHandphone(), input.getNominalVoucher());
		
		public Pembelian(String idPembelian, String idNasabah, String idProvider, String idVoucher,
				String noHandphone, double nominalVoucher) {
			super();
			this.idPembelian = idPembelian;
			this.idNasabah = idNasabah;
			this.idProvider = idProvider;
			this.idVoucher = idVoucher;
			this.noHandphone = noHandphone;
			this.nominalVoucher = nominalVoucher;
		
		}

		public Pembelian(String idPembelian, String idNasabah, String idProvider, String idVoucher,
				Date tanggalPembelian, String noHandphone, double nominalVoucher, String statusTransaksi,
				String keterangan) {
			super();
			this.idPembelian = idPembelian;
			this.idNasabah = idNasabah;
			this.idProvider = idProvider;
			this.idVoucher = idVoucher;
			this.tanggalPembelian = tanggalPembelian;
			this.noHandphone = noHandphone;
			this.nominalVoucher = nominalVoucher;
			this.statusTransaksi = statusTransaksi;
			this.keterangan = keterangan;
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

		public String getIdVoucher() {
			return idVoucher;
		}

		public void setIdVoucher(String idVoucher) {
			this.idVoucher = idVoucher;
		}

		public Date getTanggalPembelian() {
			return tanggalPembelian;
		}

		public void setTanggalPembelian(Date tanggalPembelian) {
			this.tanggalPembelian = tanggalPembelian;
		}

		public String getNoHandphone() {
			return noHandphone;
		}

		public void setNoHandphone(String noHandphone) {
			this.noHandphone = noHandphone;
		}

		public double getNominalVoucher() {
			return nominalVoucher;
		}

		public void setNominalVoucher(double nominalVoucher) {
			this.nominalVoucher = nominalVoucher;
		}

		public String getStatusTransaksi() {
			return statusTransaksi;
		}

		public void setStatusTransaksi(String statusTransaksi) {
			this.statusTransaksi = statusTransaksi;
		}

		public String getKeterangan() {
			return keterangan;
		}

		public void setKeterangan(String keterangan) {
			this.keterangan = keterangan;
		}

		@Override
		public String toString() {
			return "Pembelian [idPembelian=" + idPembelian + ", idNasabah=" + idNasabah + ", idProvider=" + idProvider
					+ ", idVoucher=" + idVoucher + ", tanggalPembelian=" + tanggalPembelian + ", noHandphone="
					+ noHandphone + ", nominalVoucher=" + nominalVoucher + ", statusTransaksi=" + statusTransaksi
					+ ", keterangan=" + keterangan + "]";
		}
		

		


	}


