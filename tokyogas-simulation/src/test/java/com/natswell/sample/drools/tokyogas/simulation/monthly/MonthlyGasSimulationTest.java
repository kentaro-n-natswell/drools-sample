package com.natswell.sample.drools.tokyogas.simulation.monthly;

import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.natswell.sample.drools.tokyogas.GasChargeSimulation.Area;
import com.natswell.sample.drools.tokyogas.GasChargeSimulation.Contract;
import com.natswell.sample.drools.tokyogas.MonthlyGasCharge;

@RunWith(Enclosed.class)
public class MonthlyGasSimulationTest {

	public static class 疎通テスト extends MonthlyGasSimulationTestBase {
		public void setupRuleTest() {
			// System.out.println(compileDecisionTable("com/natswell/sample/drools/tokyogas/simulation/monthly/適用期決定ルール.xls"));
			super.setupRuleTest();
		}

		@Test
		public void とりあえず実行するテスト() {
			MonthlyGasCharge fact = new MonthlyGasCharge(1, Contract.STANDARD, Area.TOKYO, 20D);
			ksession.insert(fact);
			ksession.fireAllRules(10);
			System.out.println(fact);
		}
	}


	public static abstract class MonthlyGasSimulationTestBase {

		private static final String KIE_BASE = "MonthlyGasChargeSimulation";
		private static final String PATH_FILE_LOGE_BASE = "src/test/resources/";

		protected static KieContainer kcontainer;
		protected static KieBase kbase;
		protected KieSession ksession;
		protected KieRuntimeLogger logger;

		@BeforeClass
		public static void init() {
			kcontainer = KieServices.Factory.get().newKieClasspathContainer();
			kbase = kcontainer.getKieBase(KIE_BASE);
		}

		@Rule
		public TestName testName = new TestName();

		@Before
		public void setupRuleTest() {
			ksession = kbase.newKieSession();
			initLogger(PATH_FILE_LOGE_BASE + testName.getClass().getPackage() + "/" + testName.getMethodName());
		}

		@Before
		public void startLog() {
			System.out.println("===== start " + testName.getMethodName() + "=====");
		}

		@After
		public void clean() {
			if (ksession != null) ksession.dispose();
			if (logger != null) logger.close();
		}

		@After
		public void endLog() {
			System.out.println("===== end " + testName.getMethodName() + "=====");
		}

		/**
		 * KieRuntimeLoggerをFileLoggerとして引数のログファイル名で初期化します。
		 * @param logFileName ログファイル名
		 */
		protected void initLogger(String logFileName) {
			logger = KieServices.Factory.get().getLoggers().newFileLogger(ksession, logFileName);
		}

		protected String compileDecisionTable(String resourcePath) {
			SpreadsheetCompiler compiler = new SpreadsheetCompiler();
			return compiler.compile(testName.getClass().getClassLoader().getResourceAsStream(resourcePath), InputType.XLS);
		}

	}
}
