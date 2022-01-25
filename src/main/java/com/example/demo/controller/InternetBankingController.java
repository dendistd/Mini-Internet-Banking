package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.InputCreatePembelian;
import com.example.demo.dto.InputPembayaran;
import com.example.demo.dto.OutputPembelian;
import com.example.demo.dto.ResponseCreatePembayaran;
import com.example.demo.dto.ResponseCreatePembelian;
import com.example.demo.dto.TagihanDanTransaksiKartuKredit;
import com.example.demo.enumeration.ErrorEnum;
//import com.example.demo.model.Nasabah;
import com.example.demo.model.Pembelian;
import com.example.demo.model.repo.PembelianRepository;
import com.example.demo.payload.ErrorSchema;
import com.example.demo.payload.ResponseSchema;
import com.example.demo.service.imple.KartuKreditService;

import com.example.demo.service.imple.PembayaranService;
import com.example.demo.service.imple.PembelianService;
//import com.example.demo.service.imple.TagihanDanTransaksiKartuKreditService;

@RestController
public class InternetBankingController {
	@Autowired
	PembelianService pembelianService;
	@Autowired
	PembayaranService pembayaranService;
	@Autowired
	KartuKreditService kartuKreditService;
//	@Autowired
//	TagihanDanTransaksiKartuKreditService tagihanDanTransaksiService;
	
	//TAMPILKAN DATA PEMBELIAN BERDASARKAN ID NASABAH
	@GetMapping("/pembelian/{nasabah}")
	public ResponseEntity<?> showPembelianByIdNasabah(@PathVariable("nasabah") String nasabah){
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.SUCCESS);
		List<Map<String, String>>  listOP = pembelianService.showPembelianByIdNasabah(nasabah);
		ResponseSchema<List<Map<String, String>> > responseSchema = new ResponseSchema<>(errorSchema);
		responseSchema.setOutputSchema(listOP);
		return ResponseEntity.status(HttpStatus.OK).body(responseSchema);
	}
	
	//CREATE PEMBELIAN 
		@PostMapping("/pembelian")
		public ResponseEntity<?> createPembelian (@RequestBody InputCreatePembelian input) {
			ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.SUCCESS);
//			ResponseCreatePembelian  listOP = pembelianService.createPembelian(input);
//			ResponseSchema<ResponseCreatePembelian> responseSchema =  new ResponseSchema<>(errorSchema);
//			responseSchema.setOutputSchema(listOP);
			
			ResponseSchema<?> responseSchema = pembelianService.createPembelian(input);
			return ResponseEntity.status(HttpStatus.OK).body(responseSchema);
		}
	
	//SAVE PEMBELIAN -> Masih error
	@PostMapping("/pembelian/save")
	public ResponseEntity<?> savePembelian(@RequestBody InputCreatePembelian input){
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.SUCCESS);
		Map<String, String>  listOP = pembelianService.savePembelian(input);
		ResponseSchema<Map<String, String> > responseSchema =  new ResponseSchema<>(errorSchema);
		responseSchema.setOutputSchema(listOP);
		return ResponseEntity.status(HttpStatus.OK).body(responseSchema);
	}
	
	@GetMapping("/pembelian")
	public ResponseEntity<?> showPembelians( ){
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.SUCCESS);
		List<Map<String, String>>  listOP = pembelianService.showPembelians();
		ResponseSchema<List<Map<String, String>>> responseSchema =  new ResponseSchema<>(errorSchema);
		responseSchema.setOutputSchema(listOP);
		return ResponseEntity.status(HttpStatus.OK).body(responseSchema);
	}
	
	@GetMapping("/pembelian/save/{id_pembelian}")
	public ResponseEntity<?> showPembelian(@PathVariable("id_pembelian") String id_pembelian ){
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.SUCCESS);
		Map<String, String>  listOP = pembelianService.showPembelian(id_pembelian);
		ResponseSchema<Map<String, String>> responseSchema =  new ResponseSchema<>(errorSchema);
		responseSchema.setOutputSchema(listOP);
		return ResponseEntity.status(HttpStatus.OK).body(responseSchema);
	}
	
	
	
	//Tampilkan Data Pembayaran By Id Nasabah
	@GetMapping("pembayaran/{nasabah}")
	public ResponseEntity<?> showPembayaranByNasabah(@PathVariable("nasabah") String nasabah){
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.SUCCESS);
		List<Map<String, String>>  result = pembayaranService.getPembayaranByNasabah(nasabah);
		ResponseSchema<List<Map<String, String>> > responseSchema = new ResponseSchema<>(errorSchema);
		responseSchema.setOutputSchema(result);
		return ResponseEntity.status(HttpStatus.OK).body(responseSchema);
	}
	
	//Tampilkan Data No Handphone Pembayaran By Id Nasabah
	@GetMapping("/pembayaran/nohp/{nasabah}")
	public ResponseEntity<?> showNoHandphoneByNasabah(@PathVariable("nasabah") String nasabah){
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.SUCCESS);
		List<Map<String, String>>  result = pembayaranService.getNoHandPhoneByNasabah(nasabah);
		ResponseSchema<List<Map<String, String>> > responseSchema = new ResponseSchema<>(errorSchema);
		responseSchema.setOutputSchema(result);
		return ResponseEntity.status(HttpStatus.OK).body(responseSchema);
	}
	
	//CREATE PEMBAYARAN
	@PostMapping("/pembayaran")
	public ResponseEntity<?> showNoHandphoneByNasabah(@RequestBody InputPembayaran input){
//		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.SUCCESS);
//		ResponseCreatePembayaran  result = pembayaranService.createPembayaran(input);
//		ResponseSchema<ResponseCreatePembayaran > responseSchema = new ResponseSchema<>(errorSchema);
//		responseSchema.setOutputSchema(result);
		ResponseSchema<?> responseSchema = pembayaranService.createPembayaran(input);
		return ResponseEntity.status(HttpStatus.OK).body(responseSchema);
	}
	
	
	//TAMPILKAN TRANSAKSI KARTU KREDIT BULAN INI
	@GetMapping("kartukredit/{nasabah}")
	public ResponseEntity<?> getTransaksiKredit(@PathVariable("nasabah") String nasabah){
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.SUCCESS);
		List<Map<String, String>>  result = kartuKreditService.getTransaksiKreditThisMonth(nasabah);
		ResponseSchema<List<Map<String, String>> > responseSchema = new ResponseSchema<>(errorSchema);
		responseSchema.setOutputSchema(result);
		return ResponseEntity.status(HttpStatus.OK).body(responseSchema);
	}
	
	//TAMPILKAN TAGIHAN & TRANSAKSI KARTU KREDIT BULAN LALU
			@GetMapping("kartukredit/tagihan/{nasabah}")
			public ResponseEntity<?> getTransaksiKreditLastMonth(@PathVariable("nasabah") String nasabah){
				ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.SUCCESS);
				TagihanDanTransaksiKartuKredit result = kartuKreditService.transaksiBulanLalu(nasabah);
				ResponseSchema<TagihanDanTransaksiKartuKredit > responseSchema = new ResponseSchema<>(errorSchema);
				responseSchema.setOutputSchema(result);
				return ResponseEntity.status(HttpStatus.OK).body(responseSchema);
			}
		//TAMPILKAN TRANSAKSI KREDIT BULAN INI TANPA ID NASABAH
			@GetMapping("kartukredit")
			public ResponseEntity<?> getTransaksiThisMonth(){
				ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.SUCCESS);
				List<Map<String, String>>  result = kartuKreditService.getTransaksiKredit();
				ResponseSchema<List<Map<String, String>> > responseSchema = new ResponseSchema<>(errorSchema);
				responseSchema.setOutputSchema(result);
				return ResponseEntity.status(HttpStatus.OK).body(responseSchema);
			}
	
			
//	//TAMPILKAN TRANSAKSI KARTU KREDIT BULAN LALU
//	@GetMapping("kartukredit/tagihan/{nasabah}")
//	public ResponseEntity<?> getTransaksiKreditLastMonth(@PathVariable("nasabah") String nasabah){
//		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.SUCCESS);
//		List<Map<String, String>>  result = kartuKreditService.getTransaksiKreditLastMonth(nasabah);
//		ResponseSchema<List<Map<String, String>> > responseSchema = new ResponseSchema<>(errorSchema);
//		responseSchema.setOutputSchema(result);
//		return ResponseEntity.status(HttpStatus.OK).body(responseSchema);
//	}
	

	
}
