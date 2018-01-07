package yellowcong.day01_06;

import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import com.yellowcong.face.FaceDemo;

/**
 * 创建日期:2018年1月7日
 * 创建时间:上午10:38:06
 * 创建者    :yellowcong
 * 机能概要:利用Opencv获取图片的轮廓
 */
public class Demo3 {
	public static void main(String[] args) {
		//图片地址
		String inputImagePath = FaceDemo.class.getClassLoader().getResource("pics/demo.jpg").getFile();
		
		String outPath = "D:/demo3.png";
		
		//加载lib,这个lib的名称
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    
		//读取图片信息
		Mat image = Highgui.imread(new File(inputImagePath).getAbsolutePath());
		
		//将rgb灰化处理
	    Imgproc.cvtColor(image, image,Imgproc.COLOR_BGR2GRAY);
	    
	    //平滑处理
	    Imgproc.blur(image, image, new Size(2, 2));
	
	    //轮廓
	    //使用Canndy检测边缘
	    double lowThresh =100;//双阀值抑制中的低阀值 
	    double heightThresh = 300;//双阀值抑制中的高阀值
	    Imgproc.Canny(image, image,lowThresh, heightThresh);
	   
	    // 写入到文件
	    Highgui.imwrite(outPath, image);
	    
	}
}
