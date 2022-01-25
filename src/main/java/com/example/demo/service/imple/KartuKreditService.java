package com.example.demo.service.imple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TagihanDanTransaksiKartuKredit;
import com.example.demo.model.repo.TagihanRepo;
import com.example.demo.model.repo.TransaksiKartuKreditRepo;

@Service
public class KartuKreditService {
	@Autowired
	TransaksiKartuKreditRepo transaksiKartuKreditRepo;
	@Autowired
	TagihanRepo tagihanRepo;

	//Tampilkan DATA TRANSAKSI KARTU KREDIT BULAN INI 
	public List<Map<String, String>> getTransaksiKreditThisMonth(String nasabah){
		System.out.println("test");
		List<Map<String, String>> result = transaksiKartuKreditRepo.getTransaksiKreditBulanIni(nasabah);
		System.out.println("hasilnya "+result.toString());
		return result;
	}
	
	//Tampilkan DATA TRANSAKSI KARTU KREDIT BULAN INI (GAK JADI DIPAKE)
		public List<Map<String, String>> getTransaksiKredit( ){
			System.out.println("Test");
			List<Map<String, String>> result = transaksiKartuKreditRepo.getTransaksiKredit();
			//sysout yg bawah gak muncul di konsol
			System.out.println("Transaksi muncul gak : " +result.toString());
			return result;
		}
		
		//TAMPILKAN TAGIHAN & TRANSAKSI KARTU KREDIT BULAN LALU
				public TagihanDanTransaksiKartuKredit transaksiBulanLalu(String id_nasabah) {
					Map<String, Float> mapTagihan = new HashMap<>();
					List<Map<String, Object>> listTransaksi = new ArrayList<>();
					
					mapTagihan = tagihanRepo.totalTagihanByIdNasabah(id_nasabah);
					listTransaksi = transaksiKartuKreditRepo.getTransaksiKreditLastMonth(id_nasabah);
					
					TagihanDanTransaksiKartuKredit result = new TagihanDanTransaksiKartuKredit();
					result.setTagihan(mapTagihan);
					result.setTransaksiKartuKredit(listTransaksi);
					
					return result;
				}
		
		//Tampilkan DATA TRANSAKSI KARTU KREDIT BULAN LALU
		public List<Map<String, Object>> getTransaksiKreditLastMonth(String nasabah){
			System.out.println("test");
			List<Map<String, Object>> result = transaksiKartuKreditRepo.getTransaksiKreditLastMonth(nasabah);
			System.out.println("hasilnya "+result.toString());
			return result;
		}
		
}
