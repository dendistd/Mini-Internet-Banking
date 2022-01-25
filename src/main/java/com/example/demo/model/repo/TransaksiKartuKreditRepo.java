package com.example.demo.model.repo;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TransaksiKartuKredit;
@Repository
public interface TransaksiKartuKreditRepo extends JpaRepository<TransaksiKartuKredit, String> {
//	1.	Date
//	2.	ID Kartu Kredit
//	3.	ID Nasabah
//	4.	Nominal
//	5.	Status

	@Query(value = "select  tk.tanggal_transaksi, tk.id_kartukredit, t.id_nasabah, tk.nominal_bayar, tk.status from dendi_transaksi_kartukredit tk JOIN dendi_kartukredit t ON tk.id_kartukredit = t.id_kartukredit where tk.tanggal_transaksi >= trunc(sysdate,'mm') AND t.id_nasabah = :nasabah", nativeQuery = true)
	List<Map<String, String>> getTransaksiKreditBulanIni(@PathParam("nasabah") String nasabah);
	
	/*
	 * select tk.id_transaksi_kartukredit, tk.tanggal_transaksi, tk.id_kartukredit, t.id_nasabah, tk.nominal_bayar, tk.status from dendi_transaksi_kartukredit tk JOIN dendi_kartukredit t ON tk.id_kartukredit = t.id_kartukredit where tanggal_transaksi >= trunc(sysdate,'mm') where t.id
	 */
	
	//Cek Transaksi Kartu Kredit Bulan ini TANPA ADA ID NASABAH
	@Query(value = "SELECT  tk.tanggal_transaksi, tk.id_kartukredit, t.id_nasabah , tk.nominal_bayar , tk.status FROM DENDI_TRANSAKSI_KARTUKREDIT tk JOIN DENDI_KARTUKREDIT t ON tk.id_kartukredit = t.id_kartukredit WHERE tk.tanggal_transaksi >= trunc(SYSDATE, 'mm')", nativeQuery = true)
	List<Map<String, String>> getTransaksiKredit();
	
	//Tampilkan Transaksi Kartu Kredit Bulan Lalu
	@Query(value = "SELECT tr.tanggal_transaksi, tr.id_kartukredit, k.id_nasabah, tr.nominal_bayar, tr.status FROM dendi_transaksi_kartukredit tr JOIN dendi_kartukredit k ON tr.id_kartukredit = k.id_kartukredit where tr.tanggal_transaksi >= trunc(ADD_MONTHS(sysdate, -1), 'MM')\r\n" + 
			"AND tr.tanggal_transaksi < trunc(sysdate, 'MM') AND k.id_nasabah = :id_nasabah", nativeQuery = true)
	List<Map<String, Object>> getTransaksiKreditLastMonth(@PathParam("id_nasabah") String id_nasabah);

}
