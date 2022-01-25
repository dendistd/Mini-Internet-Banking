package com.example.demo.model.repo;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Nasabah;
import com.example.demo.model.Pembayaran;
@Repository
public interface PembayaranRepository extends JpaRepository<Pembayaran, String>{
/* 1.	Date
2.	ID Pembayaran
3.	ID Nasabah
4.	ID Provider
5.	Nama Provider
6.	Nomor Handphone
7.	Nominal Pembayaran
8.	Status transaksi
*/
	//Tampilkan Data Pembayaran Berdasarkan ID Nasabah
	@Query(value = "SELECT pb.tanggal_pembayaran, pb.id_pembayaran, pb.id_nasabah, pb.id_provider, p.nama_provider, pb.no_handphone, pb.nominal_pembayaran, pb.status FROM DENDI_PEMBAYARAN pb JOIN DENDI_NASABAH n ON pb.id_nasabah = n.id_nasabah JOIN DENDI_PROVIDER p ON pb.id_provider = p.id_provider WHERE pb.id_nasabah = :nasabah", nativeQuery = true)
	List<Map<String, String>> findPembayaranByNasabah(@PathParam("nasabah") String nasabah);
	
	//1.	ID Nasabah
	//2.	Nomor Handphone
	//Fitur tampilkan data nomor handphone dari history Pembayaran
	@Query(value = "SELECT DISTINCT (pb.no_handphone), pb.id_nasabah FROM DENDI_PEMBAYARAN pb JOIN DENDI_NASABAH n ON pb.id_nasabah = n.id_nasabah WHERE pb.id_nasabah = :nasabah", nativeQuery = true)
	List<Map<String, String>> findNoHandphoneByNasabah(@PathParam("nasabah") String nasabah);
	
	//Cek Nasabah telah terdaftar dan aktif 
	@Query(value = "SELECT n.status FROM DENDI_NASABAH n WHERE n.status='AKTIF' and n.id_nasabah = :id_nasabah", nativeQuery = true)
	Map<String, String> findStatusNasabah(@PathParam("id_nasabah") String id_nasabah);
	
	//Cek Provider ada dalam db dan status = aktif
	@Query(value = "SELECT p.status FROM DENDI_PROVIDER p WHERE p.id_provider = :id_provider AND p.status = 'AKTIF'", nativeQuery = true)
	Map<String, String> findStatusProvider(@PathParam("id_provider") String id_provider);
	
	// (GAK JADI DIPAKE) Cek Saldo Nasabah Lebih Besar Dari Nominal Pembayaran/total_bayar dari tabel Provider nya
	@Query(value = "SELECT n.saldo FROM DENDI_PEMBAYARAN pb JOIN DENDI_NASABAH n ON pb.id_nasabah = n.id_nasabah JOIN DENDI_PROVIDER p ON pb.id_provider = p.id_provider WHERE pb.id_nasabah = :id_nasabah \r\n" + 
		"AND n.saldo > (SELECT p.total_bayar FROM DENDI_PROVIDER p WHERE p.id_provider = :id_provider)", nativeQuery = true)
	public List<Map<String, String>> findSaldoNasabah(@PathParam("id_nasabah") String id_nasabah, @PathParam("id_provider") String id_provider);
	
	
	//Update Saldo Nasabah - Nominal Pembayaran 
	@Transactional
	@Modifying(clearAutomatically = true)
//	@Query(value = "UPDATE DENDI_NASABAH n SET n.saldo = n.saldo - (select (pb.nominal_pembayaran + p.total_bayar) from dendi_pembayaran pb JOIN DENDI_PROVIDER p where pb.id_pembayaran = :id_pembayaran AND p.id_provider = :id_provider) WHERE n.id_nasabah = :id_nasabah", nativeQuery = true)
	@Query(value = "UPDATE DENDI_NASABAH n SET n.saldo = n.saldo - ((select (pb.nominal_pembayaran) from dendi_pembayaran pb where pb.id_pembayaran = :id_pembayaran)\r\n" + 
			"+ (SELECT p.biaya_admin FROM DENDI_PROVIDER p WHERE p.id_provider = :id_provider ))\r\n" + 
			"WHERE n.id_nasabah = :id_nasabah", nativeQuery = true)
	public void updateSaldoNasabah(@PathParam("id_pembayaran") String id_pembayaran, @PathParam("id_provider") String id_provider, @PathParam("id_nasabah") String id_nasabah);

	//(GAK JADI DIPAKE) UPDATE 2
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE DENDI_NASABAH n SET n.saldo = n.saldo - (SELECT p.total_bayar FROM DENDI_PROVIDER p WHERE p.id_provider = :id_provider)   WHERE n.id_nasabah = :id_nasabah", nativeQuery = true)
	public void updateSaldoNasabah2();
	
	//CEK ID PEMBAYARAN 
	@Query(value = "SELECT id_pembayaran FROM DENDI_PEMBAYARAN WHERE id_pembayaran = :id_pembayaran", nativeQuery = true)
	public Map<String, String> cekIdPembayaran(@PathParam("id_pembayaran") String id_pembayaran);
	
	//CEK ID NASABAH
	@Query(value = "SELECT id_nasabah FROM DENDI_NASABAH WHERE id_nasabah = :id_nasabah", nativeQuery = true)
	public Map<String, String> cekIdNasabah(@PathParam("id_nasabah") String id_nasabah);
}
