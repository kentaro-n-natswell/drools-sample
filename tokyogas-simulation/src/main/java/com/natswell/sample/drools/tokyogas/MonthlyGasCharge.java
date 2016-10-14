package com.natswell.sample.drools.tokyogas;

import java.math.BigDecimal;

import com.natswell.sample.drools.tokyogas.GasChargeSimulation.Area;
import com.natswell.sample.drools.tokyogas.GasChargeSimulation.Contract;

public class MonthlyGasCharge {
	enum DivisionSeason {
		通期,
		夏期,
		冬期,
		夏期以外,
		その他期;
	}

	// 計算対象月
	private Integer targetMonth;
	// 契約
	private String contract;
	// 地区
	private String area;
	// ガス使用量
	private Double consumption;
	// 計算対象月のガス料金
	private BigDecimal charge;

	// 基本料金
	private BigDecimal basicCharge;
	// 従量料金
	private BigDecimal specificCharge;
	// 従量料金の単位料金
	private BigDecimal specificChargeRate;

	// 料金表
	private String rateTable;
	// 適用期
	private DivisionSeason season;

	// デバッグ用toString実装
	public String toString() {
		StringBuilder sb = new StringBuilder(String.format("【[%s]で[%s]に住むユーザの%s月のガス料金】\n", contract, area, targetMonth));
		sb.append("    ").append(String.format(
				"ガス料金=%s (%s m3)\n",
				printFormat(charge), printFormat(consumption)));
		sb.append("      ").append(String.format(
				"適用期：%s  料金表：%s\n",
				season, rateTable));
		sb.append("        ").append(String.format(
				"基本料金=%s\n",
				printFormat(basicCharge), printFormat(specificCharge), printFormat(specificChargeRate)));
		sb.append("        ").append(String.format(
				"従量料金=%s (%s / m3)",
				printFormat(specificCharge), printFormat(specificChargeRate)));
		return sb.toString();
	}
	private String printFormat(Double target) {
		if (target == null) return null;
		return printFormat(new BigDecimal(target));
	}
	private String printFormat(BigDecimal target) {
		if (target == null) return null;
		BigDecimal result = target;
		result.setScale(3, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return result.toString();
	}

	// コンストラクタ
	public MonthlyGasCharge(Integer targetMonth, Contract contract, Area area, Double consumption) {
		this.targetMonth = targetMonth;
		this.consumption = consumption;
		setContract(contract);
		setArea(area);
	}

	/*
	 * ちょっと独特なGetter&Setter実装
	 */
	public String getSeason() {
		return season == null ? null : season.name();
	}
	public void setSeason(String season) {
		this.season = DivisionSeason.valueOf(season);
	}
	public void setContract(Contract contract) {
		this.contract = contract.jpname;
	}
	public void setArea(Area area) {
		this.area = area.jpname;
	}

	public Integer getTargetMonth() {
		return targetMonth;
	}
	public void setTargetMonth(Integer targetMonth) {
		this.targetMonth = targetMonth;
	}
	public String getContract() {
		return contract;
	}
	public String getArea() {
		return area;
	}
	public Double getConsumption() {
		return consumption;
	}
	public void setConsumption(Double consumption) {
		this.consumption = consumption;
	}
	public BigDecimal getCharge() {
		return charge;
	}
	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}
	public BigDecimal getBasicCharge() {
		return basicCharge;
	}
	public void setBasicCharge(BigDecimal basicCharge) {
		this.basicCharge = basicCharge;
	}
	public BigDecimal getSpecificCharge() {
		return specificCharge;
	}
	public void setSpecificCharge(BigDecimal specificCharge) {
		this.specificCharge = specificCharge;
	}
	public BigDecimal getSpecificChargeRate() {
		return specificChargeRate;
	}
	public void setSpecificChargeRate(BigDecimal specificChargeRate) {
		this.specificChargeRate = specificChargeRate;
	}
	public String getRateTable() {
		return rateTable;
	}
	public void setRateTable(String rateTable) {
		this.rateTable = rateTable;
	}
}
