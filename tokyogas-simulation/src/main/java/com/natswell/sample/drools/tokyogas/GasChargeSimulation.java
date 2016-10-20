package com.natswell.sample.drools.tokyogas;

import java.util.List;

public class GasChargeSimulation {
	public enum GasInstrument {
		WATER_HEATER("従来タイプ給湯器"),
		ECO_WELL("エコジョーズ給湯器"),
		STOVE_BURNER("ガスコンロ"),
		FUN_HEATER("ガスファンヒーター"),
		FLOOR_HEATING("ガス温水床暖房"),
		BATH_HEATING_AND_DRYING("ガス温水浴室暖房乾燥機");
		public String jpname;
		GasInstrument(String jpname) {
			this.jpname = jpname;
		}
	}
	public enum Area {
		TOKYO("東京地区等（群馬・群馬南・四街道12A地区以外の地区）"),
		YOTSUKAIDO("四街道12A地区（大日・四街道・和良比ほか）"),
		GUNMA("群馬地区（群馬県高崎市・前橋市・藤岡市（白石・三ツ木））"),
		SOUTH_GUNMA("群馬南地区（群馬県藤岡市・高崎市（新町））");
		public String jpname;
		Area(String jpname) {
			this.jpname = jpname;
		}
	}
	public enum Contract {
		STANDARD("一般契約", "101"),
		YUTTARI_ECO("湯ったりエコぷらん", "284"),
		DANRAN("暖らんぷらん", "288"),
		DANRAN_BATH("暖らんぷらん＋バス暖割", "285"),
		DANRAN_ECO("暖らんぷらん＋エコ割", "286"),
		DANRAN_SET("暖らんぷらん＋セット割", "287"),
		DONNO("わからない", "999"),;
		public String jpname;
		public String code;
		Contract(String jpname, String code) {
			this.jpname = jpname;
			this.code = code;
		}
		public String getJpname() { return jpname; }
		public String getCode() { return code; }
	}
	public enum Option {
		BATH("バス暖割", ""),
		ECO("エコ割", ""),
		SET("セット割", "");
		public String jpname;
		public String code;
		Option(String jpname, String code) {
			this.jpname = jpname;
			this.code = code;
		}
	}
	private String id;
	private List<GasInstrument> instruments;
	private Area livingArea;
	private Integer sampleManth;
	private Double sampleConsumption;
	private Contract currentContract;
	private Option currentOption;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<GasInstrument> getInstruments() {
		return instruments;
	}
	public void setInstruments(List<GasInstrument> instruments) {
		this.instruments = instruments;
	}
	public Area getLivingArea() {
		return livingArea;
	}
	public void setLivingArea(Area livingArea) {
		this.livingArea = livingArea;
	}
	public Integer getSampleManth() {
		return sampleManth;
	}
	public void setSampleManth(Integer sampleManth) {
		this.sampleManth = sampleManth;
	}
	public Double getSampleConsumption() {
		return sampleConsumption;
	}
	public void setSampleConsumption(Double sampleConsumption) {
		this.sampleConsumption = sampleConsumption;
	}
	public Contract getCurrentContract() {
		return currentContract;
	}
	public void setCurrentContract(Contract currentContract) {
		this.currentContract = currentContract;
	}
	public Option getCurrentOption() {
		return currentOption;
	}
	public void setCurrentOption(Option currentOption) {
		this.currentOption = currentOption;
	}
}
