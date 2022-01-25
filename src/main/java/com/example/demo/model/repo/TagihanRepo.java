package com.example.demo.model.repo;

import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Tagihan;

public interface TagihanRepo extends JpaRepository<Tagihan, String> {
	//GAK JADI DIPAKE
	@Query(value = "SELECT SUM(Distinct(tgh.total_tagihan)) as total_tagihan FROM DENDI_TAGIHAN tgh join dendi_kartukredit k on tgh.id_nasabah = k.id_nasabah join dendi_transaksi_kartukredit tr on k.id_kartukredit = tr.id_kartukredit WHERE tgh.id_nasabah = :id_nasabah", nativeQuery = true)
	Map<String, Float> findTotalTagihan(@PathParam("id_nasabah") String id_nasabah);
	
 /* TAMPILKAN OBJECT TAGIHAN Untuk Output di ResponseSchema */
//	@Query(value = "select  SUM(t.total_tagihan) as total_tagihan from\r\n" + 
//			"dendi_nasabah n join dendi_tagihan t on n.id_nasabah = t.id_nasabah where t.id_nasabah = :id_nasabah and\r\n" + 
//			"t.tanggal_tagihan >= trunc(ADD_MONTHS(sysdate, -1), 'MM') and t.tanggal_tagihan < trunc(sysdate, 'MM') ", nativeQuery = true)
	@Query(value = "select t.id_nasabah, n.nama_nasabah, t.total_tagihan, n.alamat from dendi_tagihan t join dendi_nasabah n on t.id_nasabah = n.id_nasabah where t.id_nasabah = :id_nasabah and t.tanggal_tagihan = trunc(SYSDATE, 'mm')", nativeQuery = true)
	public Map<String, Float> totalTagihanByIdNasabah(@PathParam("id_nasabah") String id_nasabah);
	
	//Method Insert data ke Tabel Tagihan DARI DATA TABEL TRANSAKSI KARTU KREDIT
	@Query(value = "insert into DENDI_TAGIHAN (id_tagihan, tanggal_tagihan, id_nasabah, total_tagihan, status) \r\n" + 
			"values ('TGH103', TO_DATE('2022/01/01', 'yyyy/mm/dd'), (select DISTINCT(k.id_nasabah) from dendi_kartukredit k join dendi_transaksi_kartukredit tr on k.id_kartukredit = tr.id_kartukredit where k.id_kartukredit = 'KRT101'),\r\n" + 
			"( select SUM(tr.nominal_bayar) from dendi_transaksi_kartukredit tr join dendi_kartukredit k on tr.id_kartukredit = k.id_kartukredit \r\n" + 
			"where k.id_nasabah = 'NB001' ), 'UNPAID'  )", nativeQuery = true)
	public void CreateTagihanByDataTransaksiKartuKredit();
}
