package com.example.demo.dto;

import java.util.List;
import java.util.Map;

import com.example.demo.model.Tagihan;
import com.example.demo.model.TransaksiKartuKredit;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TagihanDanTransaksiKartuKredit {
	@JsonProperty("tagihan")
	private Map<String, Float> tagihan;
	@JsonProperty("transaksi_kartukredit")
	private List<Map<String, Object>> transaksiKartuKredit;
	public TagihanDanTransaksiKartuKredit() {
		super();
	}
	public TagihanDanTransaksiKartuKredit(Map<String, Float> tagihan, List<Map<String, Object>> transaksiKartuKredit) {
		super();
		this.tagihan = tagihan;
		this.transaksiKartuKredit = transaksiKartuKredit;
	}
	public Map<String, Float> getTagihan() {
		return tagihan;
	}
	public void setTagihan(Map<String, Float> tagihan) {
		this.tagihan = tagihan;
	}
	public List<Map<String, Object>> getTransaksiKartuKredit() {
		return transaksiKartuKredit;
	}
	public void setTransaksiKartuKredit(List<Map<String, Object>> transaksiKartuKredit) {
		this.transaksiKartuKredit = transaksiKartuKredit;
	}
	@Override
	public String toString() {
		return "TagihanDanTransaksiKartuKredit [tagihan=" + tagihan + ", transaksiKartuKredit=" + transaksiKartuKredit
				+ "]";
	}
	
	

}
