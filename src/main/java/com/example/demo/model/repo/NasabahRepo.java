package com.example.demo.model.repo;

import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Nasabah;
@Repository
public interface NasabahRepo extends JpaRepository<Nasabah, String> {
	@Query(value = "SELECT * FROM DENDI_NASABAH n WHERE n.id_nasabah = :id_nasabah", nativeQuery = true)
	public Nasabah findsaldoNasabah(@PathParam("id_nasabah") String id_nasabah);
}
