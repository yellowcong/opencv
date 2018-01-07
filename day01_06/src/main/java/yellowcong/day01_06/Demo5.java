package yellowcong.day01_06;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import com.yellowcong.face.FaceDemo;

/**
 * 创建日期:2018年1月7日
 * 创建时间:上午10:38:06
 * 创建者    :yellowcong
 * 机能概要:利用Opencv 来查找轮廓图
 */
public class Demo5 {
	public static void main(String[] args) {
		//图片地址
		String inputImagePath = FaceDemo.class.getClassLoader().getResource("pics/autojump.png").getFile();
		
		String outPath = "D:/demo5.png";
		
		//加载lib,这个lib的名称
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    
		//读取图片信息
		Mat image = Highgui.imread(new File(inputImagePath).getAbsolutePath());
		
		//将rgb灰化处理
	    Imgproc.cvtColor(image, image,Imgproc.COLOR_BGR2GRAY,0);
	    
	    //平滑处理
	    Imgproc.blur(image, image, new Size(2, 2));
	
	    //轮廓
	    //使用Canndy检测边缘
	    double lowThresh =100;//双阀值抑制中的低阀值 
	    double heightThresh = 200;//双阀值抑制中的高阀值
	    Imgproc.Canny(image, image,lowThresh, heightThresh);
	   
	    
	    List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
	    Mat hierarchy = new Mat();
	    //第4个参数 RETR_TREE， 检测所有轮廓，所有轮廓建立一个等级树结构。外层轮廓包含内层轮廓，内层轮廓还可以继续包含内嵌轮廓。
	    //第五个参数：int型的method，定义轮廓的近似方法
	    //	CHAIN_APPROX_NONE 保存物体边界上所有连续的轮廓点到contours向量内
	    //	CHAIN_APPROX_SIMPLE 仅保存轮廓的拐点信息，把所有轮廓拐点处的点保存入contours 向量内，拐点与拐点之间直线段上的信息点不予保留
	    //	CHAIN_APPROX_TC89_L1，CV_CHAIN_APPROX_TC89_KCOS使用teh-Chinl chain 近 似算法
	    Imgproc.findContours(image, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
	    
	    int cnt = contours.size();
	    System.out.println("轮廓个数\t"+cnt);
	    
	    double maxVal = 0;
	    int maxValIdx = 0;
	    //获取轮廓信息
	    for (int contourIdx = 0; contourIdx < contours.size(); contourIdx++){
	    	 
	    	 MatOfPoint points = contours.get(contourIdx);
	    	
	    	 Rect rect = Imgproc.boundingRect(points);
	    	 
	    	 int x = rect.x;
	    	 int y = rect.y;
	    	 System.out.printf("(x,y)\t(%d,%d)\r\n",x,y);
	    	 //计算最大面积的轮廓
	    	 double contourArea = Imgproc.contourArea(contours.get(contourIdx));
	    	 if (maxVal < contourArea){
		        maxVal = contourArea;
		        maxValIdx = contourIdx;
	    	 }
	    }	
	    Scalar color =new Scalar(255d,153d,0);
	    //图片
	    Imgproc.drawContours(image, contours, maxValIdx, color, 5);
	    
	    MatOfPoint points = contours.get(maxValIdx);
	    Rect rect = Imgproc.boundingRect(points);
	    System.out.printf("最大轮廓坐标(x,y)\t(%d,%d)\r\n",rect.x,rect.y);
   	 
	    // 写入到文件
	    Highgui.imwrite(outPath, image);
	    
	}
}
