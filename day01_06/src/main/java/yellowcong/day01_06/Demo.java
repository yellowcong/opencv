package yellowcong.day01_06;

import com.yellowcong.utils.WindowsCommonUtils;

/**
 * 创建日期:2018年1月6日<br/>
 * 创建时间:上午11:27:02<br/>
 * 创建者    :yellowcong<br/>
 * 机能概要:
 */
public class Demo {

	public static void main(String[] args) throws InterruptedException {
		
		WindowsCommonUtils.exec("adb shell input keyevent 26");
		Thread.sleep(1000);
		WindowsCommonUtils.exec("adb shell input swipe 250 700 250 50");
		Thread.sleep(500);
		WindowsCommonUtils.exec("adb shell input keyevent 7");
		Thread.sleep(500);
		WindowsCommonUtils.exec("adb shell input keyevent 13");
		Thread.sleep(500);
		WindowsCommonUtils.exec("adb shell input keyevent 10");
		Thread.sleep(500);
		WindowsCommonUtils.exec("adb shell input keyevent 8");
	}
}
