package yellowcong.day01_06;

import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import com.yellowcong.face.FaceDemo;

/**
 * 创建日期:2018年1月7日
 * 创建时间:上午10:38:06
 * 创建者    :yellowcong
 * 机能概要:利用Opencv将图片灰化处理
 */
public class Demo2 {
	public static void main(String[] args) {
		//图片地址
		String inputImagePath = FaceDemo.class.getClassLoader().getResource("pics/demo.jpg").getFile();
		
		String outPath = "D:/demo2.png";
		
		//加载lib,这个lib的名称
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    
		//读取图片信息
		Mat image = Highgui.imread(new File(inputImagePath).getAbsolutePath());
		
		//将rgb灰化处理
	    Imgproc.cvtColor(image, image,Imgproc.COLOR_BGR2GRAY);
	    
	    // 写入到文件
	    Highgui.imwrite(outPath, image);
	}
}
