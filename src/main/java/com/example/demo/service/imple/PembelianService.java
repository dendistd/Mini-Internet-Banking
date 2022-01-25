package com.example.demo.service.imple;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.dto.InputCreatePembelian;
import com.example.demo.dto.OutputPembelian;
import com.example.demo.dto.ResponseCreatePembelian;
import com.example.demo.enumeration.ErrorEnum;
//import com.example.demo.model.Nasabah;
import com.example.demo.model.Pembelian;
import com.example.demo.model.repo.NasabahRepo;
import com.example.demo.model.repo.PembelianRepository;
import com.example.demo.payload.ErrorSchema;
import com.example.demo.payload.GagalOutputSchema;
import com.example.demo.payload.ResponseSchema;

@Service
public class PembelianService  {
	@Autowired
	PembelianRepository pembelianRepository;
	@Autowired
	NasabahRepo nasabahRepo;

	//TAMPILKAN DATA PEMBELIAN BY ID NASABAH
	public List<Map<String, String>> showPembelianByIdNasabah(String nasabah){
		List<Map<String, String>> listOP = pembelianRepository.findPembelianByIdNasabah(nasabah);
		return listOP;
	}
	
	//TAMPILKAN SEMUA DATA PEMBELIAN
	public List<Map<String, String>> showPembelians( ){
		//OutputPembelia op = new OutputPembelian(input.getIdNasabah(), input.getIdProvider(),input.getNamaProvider(),input.getNoHandphone(),input.getIdVoucher(),input.getNamaVoucher(), input.getNominalVoucher(), input.getChallengeToken(), input.getResponKeyBcaAppli2());
//		List<Map<String, String>> simpan = pembelianRepository.savePembelian();
		System.out.println("masuk gak");
		List<Map<String, String>>  listOP = pembelianRepository.showPembelians();
		System.out.println("value dari map :" +listOP.toString());
		return listOP;
	}
	//METHODE CREATE PEMBELIAN (YANG JADINYA DIPAKE)
	public ResponseSchema<?> createPembelian(InputCreatePembelian input) {
		/* Kondisi
		 * 1.Periksa nasabah telah terdaftar dan berstatus aktif. (DONE)
	2.	Periksa provider yang diterima ada di tabel dan memiliki status Aktif. (DONE)
	3.	Periksa voucher yang diterima ada di tabel, merupakan voucher dari provider terpilih dan memiliki status Aktif. (DONE)
	4.	Periksa 2 digit pertama challenge token harus memiliki nilai “55”.  (DONE)
	5.	Periksa saldo nasabah lebih besar dari nominal voucher. 
	6.	Jika semua sudah sesuai, simpan semua data ke table pembelian dengan status berhasil dan saldo nasabah berkurang. Jika ada data tidak sesuai, simpan semua data ke table pembelian dengan status gagal.

	*/
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.SUCCESS);
		ResponseSchema<ResponseCreatePembelian> responseSchema =  new ResponseSchema<>(errorSchema);
		//convert icp (InputCreatePembelian ke Pembelian)
		Pembelian objPembelian = new Pembelian(input.getIdPembelian(), input.getIdNasabah(), input.getIdProvider(), input.getIdVoucher(), input.getNoHandphone(), input.getNominalVoucher());
		Pembelian pembelian = new Pembelian();
		
		/*CEK APAKAH ID NASABAH YANG DI INPUT ADA DI DB ATAU TIDAK 
		 * ID NASABAH ADA DI DB SEHINGGA BISA LAKUKAN CREATE PEMBELIAN */
		if(pembelianRepository.cekIdNasabah(input.getIdNasabah()).size() > 0) {
			//ID PEMBELIAN TIDAK ADA DI DB SEHINGGA BISA CREATE PEMVELIAN 
			if(pembelianRepository.cekIdPembelian(input.getIdPembelian()).size() < 1) {
				
				//NO HP TIDAK NULL ATAU MENGANDUNG SPASI (KARNA PAKAI NOT (!) ) , SEHINGGA BISA CREATE PEMBELIAN :
				if (!(input.getNoHandphone().contains(" ") || input.getNoHandphone().length() == 0)) {
						/*CEK KONDISI DARI SOAL-> NASABAH AKTIF, PROVIDER AKTIF, VOUCHER AKTIF , CHALLENGE TOKEN DIAWALI 55
						  DAN SALDO NASABAH LEBIH BESAR DARI NOMINAL VOUCHER */
						if( pembelianRepository.findPembelianByStatusNasabah(input.getIdNasabah()).size() > 0 && pembelianRepository.findPembelianByProvider(input.getIdProvider()).size() > 0 && pembelianRepository.findPembelianByVoucher(input.getIdVoucher(), input.getIdProvider()).size() > 0 && input.getChallengeToken().startsWith("55") && nasabahRepo.findsaldoNasabah(input.getIdNasabah()).getSaldo() > input.getNominalVoucher() ) {	
							System.out.println(pembelianRepository.findPembelianByProvider(input.getIdProvider().toString()));
							//Jika Sleuruh kondisi pengecekan if terpenuhi, maka set statusTransaksi = BERHASIL, Keterangan dan Tanggal_transaksi
							objPembelian.setStatusTransaksi("BERHASIL"); 	
							objPembelian.setKeterangan("TRANSAKSI SUCCESS"); 	
							objPembelian.setTanggalPembelian(new Date()); 	
							System.out.println("isi dari objPembelian "+objPembelian.toString());
							//PROSES SAVE KE DB dari objPembelian
							pembelian = pembelianRepository.save(objPembelian);
							//UPDATE SALDO NASABAH ( PROSES PENGURANGAN SALDO NASABAH - NOMINAL VOUCHER) :
							pembelianRepository.UpdateSaldoNasabah(input.getIdPembelian(), input.getIdNasabah());
							System.out.println("string setelah save ke db");
						}
						
						/*JIKA KONDISI IF TIDAK TERPENU , MAKA SET STATUSTRANSAKSI = GAGAL, KETERANGAN DAN TANGGAL
						 * DAN TIDAK ADA PROSES PENGURANGAN SALDO , KARNA TRANSAKSINYA GAGAL */
						else {
							System.out.println(pembelianRepository.findPembelianByProvider(input.getIdProvider().toString()));
							objPembelian.setStatusTransaksi("GAGAL");
							objPembelian.setKeterangan("TRANSAKSI GAGAL");
							objPembelian.setTanggalPembelian(new Date());
							System.out.println("isi dari objPembelian "+objPembelian.toString());
							pembelian = pembelianRepository.save(objPembelian);
							System.out.println("STRING SAVE KE DB DENGAN TRANSAKSI GAGAL");
						}
					} 
					//ELSE UNTUK NO HP JIKA MENGANDUNG SPASI ATAU NULL, MAKA TIDAK BISA CREATE PEMBELIAN
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
			//ID PEMBELIAN TERDAPAT DALAM DB, SEHINGGA TIDAK BISA CREATE PEMBELIAN
			else {
				ErrorSchema errorSchema2 = new ErrorSchema();
				errorSchema2.setErrorCode("IKSEI-777-77");
				Map<String, String> map = new HashMap<>();
				map.put("indonesian", "GAGAL");
				map.put("english", "Failed");
				errorSchema2.setErrorMessage(map);
				ResponseSchema<GagalOutputSchema> responseSchema2 = new ResponseSchema<>(errorSchema2);
				responseSchema2.setOutputSchema(new GagalOutputSchema("ID PEMBELIAN TERDAPAT DALAM DB, CREATE PEMBELIAN GAGAL DILAKUKAN"));
				return responseSchema2;
			}
		}
		// ID NASABAH TIDAK TERDAPAT DALAM DB, SEHINGGA TIDAK BISA CREATE PEMBELIAN
		else {
			ErrorSchema errorSchema2 = new ErrorSchema();
			errorSchema2.setErrorCode("IKSEI-777-77");
			Map<String, String> map = new HashMap<>();
			map.put("indonesian", "GAGAL");
			map.put("english", "Failed");
			errorSchema2.setErrorMessage(map);
			ResponseSchema<GagalOutputSchema> responseSchema2 = new ResponseSchema<>(errorSchema2);
			responseSchema2.setOutputSchema(new GagalOutputSchema("ID NASABAH TIDAK TERDAPAT DALAM DB, CREATE PEMBELIAN GAGAL DILAKUKAN"));
			return responseSchema2;
		}
		
		//TAMPILKAN RESPONSE/OUTPUT DI POSTMAN NYA
		System.out.println("test masuk gak");
		ResponseCreatePembelian rcp = new ResponseCreatePembelian(pembelian.getIdPembelian(),pembelian.getTanggalPembelian().toString(), pembelian.getIdNasabah(), pembelian.getIdProvider(), (float) pembelian.getNominalVoucher(), pembelian.getNoHandphone(), pembelian.getIdVoucher(), pembelian.getStatusTransaksi());
		System.out.println(rcp.toString());
		rcp.setNamaProvider(input.getNamaProvider());
		rcp.setNamaVoucher(input.getNamaVoucher());
		responseSchema.setOutputSchema(rcp);
		return responseSchema;
		
	}
	

	// METHOD/SERVICE UNTUK CREATE PEMBELIAN (GAK JADI DIPAKE)
	public ResponseSchema<?> createPembelian2(InputCreatePembelian input) {
		/* Kondisi
		 * 1.Periksa nasabah telah terdaftar dan berstatus aktif. (DONE)
	2.	Periksa provider yang diterima ada di tabel dan memiliki status Aktif. (DONE)
	3.	Periksa voucher yang diterima ada di tabel, merupakan voucher dari provider terpilih dan memiliki status Aktif. (DONE)
	4.	Periksa 2 digit pertama challenge token harus memiliki nilai “55”.  (DONE)
	5.	Periksa saldo nasabah lebih besar dari nominal voucher. 
	6.	Jika semua sudah sesuai, simpan semua data ke table pembelian dengan status berhasil dan saldo nasabah berkurang. Jika ada data tidak sesuai, simpan semua data ke table pembelian dengan status gagal.

	*/
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.SUCCESS);
		ResponseSchema<ResponseCreatePembelian> responseSchema =  new ResponseSchema<>(errorSchema);
		//convert icp (InputCreatePembelian ke Pembelian)
		Pembelian objPembelian = new Pembelian(input.getIdPembelian(), input.getIdNasabah(), input.getIdProvider(), input.getIdVoucher(), input.getNoHandphone(), input.getNominalVoucher());
		Pembelian pembelian = new Pembelian();
		
	//CEK KONDISI/PROSES 
		// CEK KONDISI TANPA ADA ID_PEMBELIAN
//		if( pembelianRepository.findPembelianByStatusNasabah(input.getIdNasabah()).size() > 0 && pembelianRepository.findPembelianByProvider(input.getIdProvider()).size() > 0 && pembelianRepository.findPembelianByVoucher(input.getIdVoucher(), input.getIdProvider()).size() > 0 && input.getChallengeToken().startsWith("55") && nasabahRepo.findsaldoNasabah(input.getIdNasabah()).getSaldo() > input.getNominalVoucher() ) {	
		//CEK  KONDISI DENGAN ADA ID_PEMBELIAN
//		if((input.getNoHandphone().contains(" ") || input.getNoHandphone().length() == 0) && pembelianRepository.findPembelianByStatusNasabah(input.getIdNasabah()).size() > 0 && pembelianRepository.findPembelianByProvider(input.getIdProvider()).size() > 0 && pembelianRepository.findPembelianByVoucher(input.getIdVoucher(), input.getIdProvider()).size() > 0 && input.getChallengeToken().startsWith("55") && nasabahRepo.findsaldoNasabah(input.getIdNasabah()).getSaldo() > input.getNominalVoucher() ) {	
//			ErrorSchema errorSchema2 = new ErrorSchema();
//			errorSchema2.setErrorCode("IKSEI-777-77");
//			Map<String, String> map = new HashMap<>();
//			map.put("indonesian", "GAGAL");
//			map.put("english", "Failed");
//			errorSchema2.setErrorMessage(map);
//			ResponseSchema<GagalOutputSchema> responseSchema2 = new ResponseSchema<>(errorSchema2);
//			responseSchema2.setOutputSchema(new GagalOutputSchema("NO HANDPHONE TIDAK BOLEH KOSONG/NULL"));
//			return responseSchema2;
//		}
		//JIKA SEMUA TERPENUHI MAKA AKAN DISAVE KE DATABASE
		if(pembelianRepository.cekIdPembelian(input.getIdPembelian()).size() < 1  && pembelianRepository.findPembelianByStatusNasabah(input.getIdNasabah()).size() > 0 && pembelianRepository.findPembelianByProvider(input.getIdProvider()).size() > 0 && pembelianRepository.findPembelianByVoucher(input.getIdVoucher(), input.getIdProvider()).size() > 0 && input.getChallengeToken().startsWith("55") && nasabahRepo.findsaldoNasabah(input.getIdNasabah()).getSaldo() > input.getNominalVoucher() ) {	
		
			System.out.println(pembelianRepository.findPembelianByProvider(input.getIdProvider().toString()));
			//Jika Sleuruh kondisi pengecekan if terpenuhi, maka set statusTransaksi = BERHASIL, Keterangan dan Tanggal_transaksi
			//hardcod di db nya
			objPembelian.setStatusTransaksi("BERHASIL");
			objPembelian.setKeterangan("TRANSAKSI SUCCESS");
			objPembelian.setTanggalPembelian(new Date());
			System.out.println("isi dari objPembelian "+objPembelian.toString());
			//PROSES SAVE KE DB dari objPembelian
			pembelian = pembelianRepository.save(objPembelian);
			pembelianRepository.UpdateSaldoNasabah(input.getIdPembelian(), input.getIdNasabah());
			System.out.println("string setelah save ke db");
		}
		
		//KONDISI JIKA MENGGUNAKAN ID PEMBELIAN YANG SAMA DI DB
		if(pembelianRepository.cekIdPembelian(input.getIdPembelian()).size() > 0  && pembelianRepository.findPembelianByStatusNasabah(input.getIdNasabah()).size() > 0 && pembelianRepository.findPembelianByProvider(input.getIdProvider()).size() > 0 && pembelianRepository.findPembelianByVoucher(input.getIdVoucher(), input.getIdProvider()).size() > 0 && input.getChallengeToken().startsWith("55") && nasabahRepo.findsaldoNasabah(input.getIdNasabah()).getSaldo() > input.getNominalVoucher() ) {	
			ErrorSchema errorSchema2 = new ErrorSchema();
			errorSchema2.setErrorCode("IKSEI-777-77");
			Map<String, String> map = new HashMap<>();
			map.put("indonesian", "GAGAL");
			map.put("english", "Failed");
			errorSchema2.setErrorMessage(map);
			ResponseSchema<GagalOutputSchema> responseSchema2 = new ResponseSchema<>(errorSchema2);
			responseSchema2.setOutputSchema(new GagalOutputSchema("ID PEMBELIAN YANG DI INPUT SUDAH TERPAKAI DI DATABASE, CREATE PEMBELIAN GAGAL DILAKUKAN"));
			return responseSchema2;
		}
		if(pembelianRepository.cekIdPembelian(input.getIdPembelian()).size() < 1  && pembelianRepository.cekIdNasabah(input.getIdNasabah()).size() < 0 && pembelianRepository.findPembelianByStatusNasabah(input.getIdNasabah()).size() > 0 && pembelianRepository.findPembelianByProvider(input.getIdProvider()).size() > 0 && pembelianRepository.findPembelianByVoucher(input.getIdVoucher(), input.getIdProvider()).size() > 0 && input.getChallengeToken().startsWith("55") && nasabahRepo.findsaldoNasabah(input.getIdNasabah()).getSaldo() > input.getNominalVoucher() ) {	
			ErrorSchema errorSchema2 = new ErrorSchema();
			errorSchema2.setErrorCode("IKSEI-777-77");
			Map<String, String> map = new HashMap<>();
			map.put("indonesian", "GAGAL");
			map.put("english", "Failed");
			errorSchema2.setErrorMessage(map);
			ResponseSchema<GagalOutputSchema> responseSchema2 = new ResponseSchema<>(errorSchema2);
			responseSchema2.setOutputSchema(new GagalOutputSchema("ID NASABAH TIDAK TERDAFTAR DI DB"));
			return responseSchema2;
		}

		else {
			System.out.println(pembelianRepository.findPembelianByProvider(input.getIdProvider().toString()));
			/*JIKA KONDISI IF TIDAK TERPENU , MAKA SET STATUSTRANSAKSI = GAGAL, KETERANGAN DAN TANGGAL
			 * DAN TIDAK ADA PROSES PENGURANGAN SALDO , KARNA TRANSAKSINYA GAGAL */
			objPembelian.setStatusTransaksi("GAGAL");
			objPembelian.setKeterangan("TRANSAKSI GAGAL");
			objPembelian.setTanggalPembelian(new Date());
			System.out.println("isi dari objPembelian "+objPembelian.toString());
			pembelian = pembelianRepository.save(objPembelian);
			System.out.println("string setelah save ke db");
		}
		//TAMPILKAN RESPONSE/OUTPUT DI POSTMAN NYA
		//RESPONSECREATEPEMBELIAN DENGAN ADA NO HANDPHONE
		System.out.println("test masuk gak");
		ResponseCreatePembelian rcp = new ResponseCreatePembelian(pembelian.getIdPembelian(),pembelian.getTanggalPembelian().toString(), pembelian.getIdNasabah(), pembelian.getIdProvider(), (float) pembelian.getNominalVoucher(), pembelian.getNoHandphone(), pembelian.getIdVoucher(), pembelian.getStatusTransaksi());
		
//		//RESPONSECREATEPEMBELIAN TANPA ADA NO HANDPHONE
//		ResponseCreatePembelian rcp = new ResponseCreatePembelian(pembelian.getIdPembelian(),pembelian.getTanggalPembelian().toString(), pembelian.getIdNasabah(), pembelian.getIdProvider(), (float) pembelian.getNominalVoucher(), pembelian.getIdVoucher(), pembelian.getStatusTransaksi());

		System.out.println(rcp.toString());
		rcp.setNamaProvider(input.getNamaProvider());
		rcp.setNamaVoucher(input.getNamaVoucher());
		responseSchema.setOutputSchema(rcp);
		return responseSchema;
		
	}
	
	
	//Method SAVE YANG PAKE CUSTUM QUERY DI REPOSITORY (JADINYA GAK DIPAKE)
		public Map<String, String>  savePembelian(InputCreatePembelian input){
			Map<String, String> result = new HashMap<>();
			try {
				System.out.println("sysout save " +input.toString());
				pembelianRepository.savePembelian(input.getIdPembelian(), input.getNoHandphone(), input.getNominalVoucher(), input.getIdNasabah(), input.getIdProvider(), input.getIdVoucher());

				//tampilkan respon nya 
				result = pembelianRepository.showPembelian(input.getIdPembelian());
				System.out.println("cek result" + result.toString());
				return result;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return result;
			}
			
		}
		
		//SEBAGAI RESPON PADA SAAT CREATE/SAVE PEMBELIAN KE DB (JADINYA GAK DIPAKE)
		public Map<String, String> showPembelian( String id_pembelian ){
			System.out.println("masuk gak");
			Map<String, String>  listOP = pembelianRepository.showPembelian(id_pembelian);
			System.out.println("value dari map :" +listOP.toString());
			return listOP;
		}
		
		

}
