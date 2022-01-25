package com.example.demo.service.imple;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.InputPembayaran;
import com.example.demo.dto.ResponseCreatePembayaran;
import com.example.demo.dto.ResponseCreatePembelian;
import com.example.demo.enumeration.ErrorEnum;
import com.example.demo.model.Pembayaran;
import com.example.demo.model.repo.NasabahRepo;
import com.example.demo.model.repo.PembayaranRepository;
import com.example.demo.payload.ErrorSchema;
import com.example.demo.payload.GagalOutputSchema;
import com.example.demo.payload.ResponseSchema;

@Service
public class PembayaranService {
	@Autowired
	PembayaranRepository pembayaranRepository;
	@Autowired
	NasabahRepo nasabahRepo;
	
	//Tampilkan Pembayaran By ID Nasabah
	public List<Map<String, String>> getPembayaranByNasabah (String nasabah){
		List<Map<String, String>> listPembayaran = pembayaranRepository.findPembayaranByNasabah(nasabah);
		return listPembayaran;
	}
	
	//Tampilkan Pembayaran By No Handphone
	public List<Map<String, String>> getNoHandPhoneByNasabah(String nasabah){
		List<Map<String, String>> result = pembayaranRepository.findNoHandphoneByNasabah(nasabah);
		return result;
	}
	
	//Create Pembayaran (JADINYA DIPAKE)
		public ResponseSchema<?> createPembayaran(InputPembayaran input) {
			/*  * 1.	Periksa nasabah telah terdaftar dan berstatus aktif.
			 	* 2.	Periksa provider yang diterima ada di tabel dan memiliki status Aktif.
			 	* 3.	Periksa saldo nasabah lebih besar dari nominal pembayaran.
			 	* 4.	Jika semua sudah sesuai, simpan semua data ke table pembayaran dengan status berhasil dan saldo nasabah berkurang. Jika ada data tidak sesuai, simpan semua data ke table pembelian dengan status gagal*/
			//Input : String idPembayaran, String idNasabah, String idProvider, String namaProvider, String noHandphone,String nominalPembayaran, String responKeyBcaAppli1
			//field Pemabayaran : String idPembayaran, Date tanggalPembayaran, String idNasabah, String provider,String noHandphone, float nominalPembayaran, String status, String keterangan
			ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.SUCCESS);
			ResponseSchema<ResponseCreatePembayaran> responseSchema =  new ResponseSchema<>(errorSchema);
			Pembayaran objPembayaran = new Pembayaran();
			objPembayaran.setIdPembayaran(input.getIdPembayaran());
			objPembayaran.setIdNasabah(input.getIdNasabah());
			objPembayaran.setProvider(input.getIdProvider());
			objPembayaran.setNoHandphone(input.getNoHandphone());
			objPembayaran.setNominalPembayaran(input.getNominalPembayaran());
		
			Pembayaran pembayaran = new Pembayaran();
			
			//ID PEMBAYARAN TIDAK ADA DALAM DB, SEHINGGA BISA CREATE PEMBAYARAN
			if(pembayaranRepository.cekIdPembayaran(input.getIdPembayaran()).size() < 1) {
			//CEK ID NASABAH ADA DALAM DB
				if(pembayaranRepository.cekIdNasabah(input.getIdNasabah()).size() > 0) {
					//NO HP TIDAK NULL DAN TIDAK MENGANDUNG SPASI 
					if(!(input.getNoHandphone().contains(" ") || input.getNoHandphone().length() == 0 )) {
					//CEK KONDISI SESUAI DENGAN SOAL
						if(pembayaranRepository.findStatusNasabah(input.getIdNasabah()).size() > 0 && pembayaranRepository.findStatusProvider(input.getIdProvider()).size() > 0 && nasabahRepo.findsaldoNasabah(input.getIdNasabah()).getSaldo() > input.getNominalPembayaran()) {
							objPembayaran.setTanggalPembayaran(new Date());
							objPembayaran.setStatus("BERHASIL");
							objPembayaran.setKeterangan("TRANSAKSI SUCCESS");
							System.out.println("isi dari objPembayaran" + objPembayaran.toString());
							pembayaran = pembayaranRepository.save(objPembayaran);
							System.out.println("String setelah save ke db");
							pembayaranRepository.updateSaldoNasabah(input.getIdPembayaran(), input.getIdProvider(), input.getIdNasabah());
							
						} else {
							objPembayaran.setTanggalPembayaran(new Date());
							objPembayaran.setStatus("GAGAL");
							objPembayaran.setKeterangan("TRANSAKSI GAGAL");
							System.out.println("isi dari objPembayaran" + objPembayaran.toString());
							pembayaran = pembayaranRepository.save(objPembayaran);
							System.out.println("String setelah SAVE DB DENGAN VALUE GAGAL");
						}
					}
					//NO HP MENGANDUNG SPASI DAN JUGA NULL
					else {
						ErrorSchema errorSchema2 = new ErrorSchema();
						errorSchema2.setErrorCode("IKSEI-777-77");
						Map<String, String> map = new HashMap<>();
						map.put("indonesian", "GAGAL");
						map.put("english", "Failed");
						errorSchema2.setErrorMessage(map);
						ResponseSchema<GagalOutputSchema> responseSchema2 = new ResponseSchema<>(errorSchema2);
						responseSchema2.setOutputSchema(new GagalOutputSchema("NO HP TIDAK BOLEH KOSONG ATAU MENGANDUNG SPASI"));
						return responseSchema2;
					}
				}
				//ID NASABAH TIDAK ADA DALAM DB
				else {
					ErrorSchema errorSchema2 = new ErrorSchema();
					errorSchema2.setErrorCode("IKSEI-777-77");
					Map<String, String> map = new HashMap<>();
					map.put("indonesian", "GAGAL");
					map.put("english", "Failed");
					errorSchema2.setErrorMessage(map);
					ResponseSchema<GagalOutputSchema> responseSchema2 = new ResponseSchema<>(errorSchema2);
					responseSchema2.setOutputSchema(new GagalOutputSchema("ID NASABAH TIDAK ADA DALAM DB, CREATE PEMBAYARAN GAGAL DILAKUKAN"));
					return responseSchema2;
				}
				
			}
			//ID PEMBAYARAN ADA DALAM DB
			else {
				ErrorSchema errorSchema2 = new ErrorSchema();
				errorSchema2.setErrorCode("IKSEI-777-77");
				Map<String, String> map = new HashMap<>();
				map.put("indonesian", "GAGAL");
				map.put("english", "Failed");
				errorSchema2.setErrorMessage(map);
				ResponseSchema<GagalOutputSchema> responseSchema2 = new ResponseSchema<>(errorSchema2);
				responseSchema2.setOutputSchema(new GagalOutputSchema("ID PEMBAYARAN SUDAH TERPAKAI, CREATE PEMBAYARAN GAGAL DILAKUKAN"));
				return responseSchema2;
			}
			
			ResponseCreatePembayaran rcp = new ResponseCreatePembayaran();
			rcp.setIdPembayaran(pembayaran.getIdPembayaran());
			rcp.setIdNasabah(pembayaran.getIdNasabah());
			rcp.setIdProvider(pembayaran.getProvider());
			rcp.setNamaProvider(input.getNamaProvider());
			rcp.setNoHandphone(pembayaran.getNoHandphone());
			rcp.setNominalPembayaran(pembayaran.getNominalPembayaran());
			rcp.setStatusTransaksi(pembayaran.getStatus());
			responseSchema.setOutputSchema(rcp);
			return responseSchema;
		
		}

	//Create Pembayaran (GAK JADI DIPAKE)
	public ResponseCreatePembayaran createPembayaran2(InputPembayaran input) {
		/*  * 1.	Periksa nasabah telah terdaftar dan berstatus aktif.
		 	* 2.	Periksa provider yang diterima ada di tabel dan memiliki status Aktif.
		 	* 3.	Periksa saldo nasabah lebih besar dari nominal pembayaran.
		 	* 4.	Jika semua sudah sesuai, simpan semua data ke table pembayaran dengan status berhasil dan saldo nasabah berkurang. Jika ada data tidak sesuai, simpan semua data ke table pembelian dengan status gagal*/
		//Input : String idPembayaran, String idNasabah, String idProvider, String namaProvider, String noHandphone,String nominalPembayaran, String responKeyBcaAppli1
		//field Pemabayaran : String idPembayaran, Date tanggalPembayaran, String idNasabah, String provider,String noHandphone, float nominalPembayaran, String status, String keterangan
		
		Pembayaran objPembayaran = new Pembayaran();
		objPembayaran.setIdPembayaran(input.getIdPembayaran());
		objPembayaran.setIdNasabah(input.getIdNasabah());
		objPembayaran.setProvider(input.getIdProvider());
		objPembayaran.setNoHandphone(input.getNoHandphone());
		objPembayaran.setNominalPembayaran(input.getNominalPembayaran());
	
		Pembayaran pembayaran = new Pembayaran();
		
		//CEK KONDISI PROSES JIKA YANG DI COMPARE SALDO NASABAH > NOMINAL PEMBAYARAN DARI TABEL DENDI_PEMBAYARAN / INPUT PARAM NYA
		if(pembayaranRepository.findStatusNasabah(input.getIdNasabah()).size() > 0 && pembayaranRepository.findStatusProvider(input.getIdProvider()).size() > 0 && nasabahRepo.findsaldoNasabah(input.getIdNasabah()).getSaldo() > input.getNominalPembayaran()) {
			objPembayaran.setTanggalPembayaran(new Date());
			objPembayaran.setStatus("BERHASIL");
			objPembayaran.setKeterangan("TRANSAKSI SUCCESS");
			System.out.println("isi dari objPembayaran" + objPembayaran.toString());
			pembayaran = pembayaranRepository.save(objPembayaran);
			System.out.println("String setelah save ke db");
			pembayaranRepository.updateSaldoNasabah(input.getIdPembayaran(), input.getIdProvider(), input.getIdNasabah());
			
		} else {
			objPembayaran.setTanggalPembayaran(new Date());
			objPembayaran.setStatus("GAGAL");
			objPembayaran.setKeterangan("TRANSAKSI GAGAL");
			System.out.println("isi dari objPembayaran" + objPembayaran.toString());
			pembayaran = pembayaranRepository.save(objPembayaran);
			System.out.println("String setelah SAVE DB DENGAN VALUE GAGAL");
		}
		
		ResponseCreatePembayaran rcp = new ResponseCreatePembayaran();
		rcp.setIdPembayaran(pembayaran.getIdPembayaran());
		rcp.setIdNasabah(pembayaran.getIdNasabah());
		rcp.setIdProvider(pembayaran.getProvider());
		rcp.setNamaProvider(input.getNamaProvider());
		rcp.setNoHandphone(pembayaran.getNoHandphone());
		rcp.setNominalPembayaran(pembayaran.getNominalPembayaran());
		rcp.setStatusTransaksi(pembayaran.getStatus());
		return rcp;
	
	}
}
