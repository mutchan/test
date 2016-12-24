package com.powermock_test.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import static org.powermock.api.mockito.PowerMockito.*;

/**
 * テスト用ユーティリティ
 */
public class TestUtil {
	/**
	 * @param propertyName
	 *            プロパティファイルのパス
	 * @throws IOException
	 *             ファイルの読み込みに失敗
	 */
	public static void setenv(String propertyName) throws IOException {
		setenv(propertyName, false);
	}

	/**
	 * @param propertyName
	 *            プロパティファイルのパス
	 * @param verbose
	 *            環境変数の設定を出力
	 * @throws IOException
	 *             ファイルの読み込みに失敗
	 */
	public static void setenv(String propertyName, boolean verbose) throws IOException {

		// モックに設定
		spy(Env.class);

		// ファイルの指定
		FileInputStream fs = new FileInputStream(propertyName);

		// プロパティの設定
		Properties properties = new Properties();
		properties.load(fs);

		PrintStream stdout = System.out;
		// プロパティを環境変数(のモック)に登録
		for (String name : properties.stringPropertyNames()) {
			String value = properties.getProperty(name);
			try {
				System.setOut(new PrintStream("nul"));
				when(Env.class, "getEnv", name).thenReturn(value);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				System.setOut(stdout);
			}

			// 中身を見たいときは出力
			if (verbose) {
				System.out.println("[TestUtil#setenv] " + name + " = " + value);
			}
		}
	}
}
