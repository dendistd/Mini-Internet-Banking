package com.example.demo.model.repo;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.ProjectedPayload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.InputCreatePembelian;
import com.example.demo.dto.OutputPembelian;
import com.example.demo.dto.ResponseCreatePembelian;
import com.example.demo.model.Nasabah;
import com.example.demo.model.Pembelian;

//@Component
//@NoRepositoryBean
@Repository
public interface PembelianRepository extends JpaRepository<Pembelian, String>  {

/* TAMPILKAN DATA PEMBELIAN BY ID NASABAH */
@Query(value = "SELECT pb.tanggal_pembelian, pb.id_pembelian, pb.id_nasabah, pb.id_provider, p.nama_provider, pb.no_handphone, pb.id_voucher, v.nama_voucher, pb.status_transaksi FROM DENDI_PEMBELIAN pb JOIN DENDI_PROVIDER p ON pb.id_provider = p.id_provider JOIN DENDI_VOUCHER v ON pb.id_voucher = v.id_voucher WHERE pb.id_nasabah = :nasabah", nativeQuery = true)
List<Map<String, String>> findPembelianByIdNasabah(@PathParam("nasabah") String nasabah);

/* QUERY untuk menampilkan Seluruh Transaksi Pembelian */
@Query(value = "SELECT pb.tanggal_pembelian, pb.id_pembelian, pb.id_nasabah, pb.id_provider, p.nama_provider, pb.nominal_voucher, pb.no_handphone, pb.id_voucher, v.nama_voucher, pb.status_transaksi FROM DENDI_PEMBELIAN pb JOIN DENDI_NASABAH n ON pb.id_nasabah = n.id_nasabah JOIN DENDI_PROVIDER p ON pb.id_provider = p.id_provider JOIN DENDI_VOUCHER v ON pb.id_voucher = v.id_voucher  ", nativeQuery = true)
List<Map<String, String>> showPembelians();

/* QUERY  untuk menampilkan Response Transaksi Pembelian dari CUSTOM QUERY SAVE PEMBELIAN */
@Query(value = "SELECT pb.tanggal_pembelian, pb.id_pembelian, pb.id_nasabah, pb.id_provider, p.nama_provider, pb.nominal_pembelian, pb.no_handphone, pb.id_voucher, v.nama_voucher, pb.status_transaksi FROM DENDI_PEMBELIAN pb JOIN DENDI_NASABAH n ON pb.id_nasabah = n.id_nasabah JOIN DENDI_PROVIDER p ON pb.id_provider = p.id_provider JOIN DENDI_VOUCHER v ON pb.id_voucher = v.id_voucher WHERE pb.id_pembelian = :id_pembelian ", nativeQuery = true)
Map<String, String> showPembelian(@PathParam("id_pembelian") String id_pembelian);

/* CUSTOM QUERY => DATA KESIMPAN DI DB, TAPI DI POSTMAN ADA EROR could not execute query; nested exception is org.hibernate.exception.GenericJDBCException: could not execute query */
@Query(value = "INSERT INTO DENDI_PEMBELIAN (id_pembelian, tanggal_pembelian, no_handphone, nominal_pembelian, status_transaksi, keterangan, id_nasabah, id_provider, id_voucher) VALUES (:id_pembelian,sysdate,  :no_handphone, :nominal_pembelian,'BERHASIL','TRANSAKSI SUCCESS', :id_nasabah, :id_provider, :id_voucher)",nativeQuery =true )
void savePembelian(@PathParam("id_pembelian") String id_pembelian, @PathParam("no_handphone") String no_handphone, @PathParam("nominal_pembelian") float nominal_pembelian, @PathParam("id_nasabah") String id_nasabah, @PathParam("id_provider") String id_provider, @PathParam("id_voucher") String id_voucher);


//Cek APakah id_nasabah ada dalam DB dan status Nasabah = AKTIF
@Query(value = "SELECT n.status FROM DENDI_NASABAH n WHERE n.id_nasabah = :id_nasabah AND n.status = 'AKTIF'", nativeQuery = true)
public Map<String, String> findPembelianByStatusNasabah(@PathParam("id_nasabah") String id_nasabah);

//GAK JADI DIPAKE
//CEK APAKAH ID_NASABAH ADA DALAM DB DAN STATUS NASABAH = AKTIF MENGGUNAKAN BOOLEAN -> COUNT(N.STATUS)
@Query(value = "SELECT CASE WHEN COUNT(n.status) > 0 THEN true ELSE false END FROM DENDI_PEMBELIAN pb JOIN DENDI_NASABAH n ON pb.id_nasabah = n.id_nasabah WHERE pb.id_nasabah = :nasabah AND n.status = 'AKTIF'", nativeQuery = true)
public  Boolean existsByIdNasabah(@PathParam("nasabah") String nasabah);

//CEK PROVIDER ADA DAN BERSTATUS AKTIF 
@Query(value = "SELECT p.status FROM DENDI_PROVIDER p WHERE p.id_provider = :id_provider AND p.status = 'AKTIF'", nativeQuery = true)
public Map<String, String> findPembelianByProvider(@PathParam("id_provider") String id_provider);

//CEK VOUCHER ADA DAN MERUPAKAN ID VOUCHER DARI PROVIDER YG DIPILIH DAN BERSTATUS AKTIF
@Query(value = "SELECT v.status FROM DENDI_VOUCHER v JOIN DENDI_PROVIDER p ON v.id_provider = p.id_provider WHERE v.id_voucher = :id_voucher AND p.id_provider = :id_provider AND v.status = 'AKTIF' AND p.status = 'AKTIF' ", nativeQuery = true)
public List<Map<String, String>> findPembelianByVoucher(@PathParam("id_voucher") String id_voucher, @PathParam("id_provider") String id_provider);
//@Query(value = "SELECT v.status FROM DENDI_PEMBELIAN pb JOIN DENDI_PROVIDER p ON pb.id_provider = p.id_provider JOIN DENDI_VOUCHER v ON pb.id_voucher = v.id_voucher WHERE pb.id_voucher = :id_voucher AND v.status = 'AKTIF'", nativeQuery = true)
//@Query(value = "select v.id_voucher, v.status from dendi_pembelian pb join dendi_voucher v on pb.id_voucher = v.id_voucher join \r\n" + 
//		"dendi_provider p on p.id_provider = v.id_provider where v.id_voucher = :id_voucher AND v.status = 'AKTIF'", nativeQuery = true)
//public List<Map<String, String>> findPembelianByVoucher(@PathParam("id_voucher") String id_voucher);


// (GAK JADI DIPAKE) CEK APAKAH SALDO NASABAH LEBIH BESAR DARI NOMINAL VOUCHER/PEMBELIAN
@Query(value = "SELECT n.saldo FROM DENDI_PEMBELIAN pb JOIN DENDI_NASABAH n ON pb.id_nasabah = n.id_nasabah JOIN DENDI_VOUCHER v ON pb.id_voucher = v.id_voucher WHERE pb.id_nasabah = :id_nasabah \r\n" + 
		"AND n.saldo > (SELECT v.nominal_voucher FROM DENDI_VOUCHER v WHERE v.id_voucher = :id_voucher)", nativeQuery = true)
public List<Map<String, String>> findPembelianBySaldoNasabah(@PathParam("id_nasabah") String id_nasabah, @PathParam("id_voucher") String id_voucher);


//UPDATE SALDO_NASABAH - NOMINAL_VOUCHER 
@Transactional
@Modifying(clearAutomatically = true)
@Query(value = "UPDATE DENDI_NASABAH n SET n.saldo = n.saldo - (select pb.nominal_voucher from dendi_pembelian pb where pb.id_pembelian = :id_pembelian ) WHERE n.id_nasabah = :id_nasabah", nativeQuery = true)
public void UpdateSaldoNasabah (@PathParam("id_pembelian") String id_pembelian, @PathParam("id_nasabah") String id_nasabah);

//CEK ID PEMBELIAN SUDAH ADA ATAU BELUM DI DB
@Query(value = "SELECT pb.id_pembelian FROM DENDI_PEMBELIAN pb Where pb.id_pembelian = :id_pembelian", nativeQuery = true)
public Map<String, String> cekIdPembelian(@PathParam("id_pembelian") String id_pembelian);

//CEK APAKAH ID NASABAH TERDAFTAR PADA DB
@Query(value = "SELECT id_nasabah FROM DENDI_NASABAH WHERE id_nasabah = :id_nasabah", nativeQuery = true)
Map<String, String> cekIdNasabah(@PathParam("id_nasabah") String id_nasabah);
	
}
