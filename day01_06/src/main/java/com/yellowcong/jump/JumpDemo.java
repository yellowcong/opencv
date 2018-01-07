package com.yellowcong.jump;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import com.yellowcong.face.FaceDemo;

/**
 * 创建日期:2018年1月6日<br/>
 * 创建时间:下午7:07:19<br/>
 * 创建者    :yellowcong<br/>
 * 机能概要:
 */
public class JumpDemo {

	public static void main(String[] args) {
		//图片地址
		String inputImagePath = FaceDemo.class.getClassLoader().getResource("pics/autojump.png").getFile();
		String outPath = "D:/demo.png";
		//加载lib,这个lib的名称
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    
	    //读取图像的 mat信息
	    Mat image = Highgui.imread(new File(inputImagePath).getAbsolutePath());
	    
	    //计算
	    int rows = image.rows();
	    int cols = image.cols();
	    System.out.printf("行:%d\t列:%d\r\n",rows,cols);
	    
	    //增加 图片灰度
	    //Imgproc.COLOR_BGR2GRAY：<彩色图像转灰度图像> 
	    //0 图像的波段数，这个值默认是0
	    //转为单通道
	    Imgproc.cvtColor(image, image,Imgproc.COLOR_BGR2GRAY,0);
	    	
	    //平滑处理
	    Imgproc.blur(image, image, new Size(2, 2));
	    
	    //使用Canndy检测边缘
	    double lowThresh =100;//双阀值抑制中的低阀值 
	    double heightThresh = 200;//双阀值抑制中的高阀值
	    Imgproc.Canny(image, image,lowThresh, heightThresh);
	   
	    //运用霍夫变换识别圆  HoughCircles
	    Mat circles = new Mat();
	    
	    //找到轮廓
	    List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
	    //分别表示第i个轮廓的后一个轮廓、前一个轮廓、父轮廓、内嵌轮廓的索引编号
	    Mat hierarchy = new Mat();
	    //第4个参数 RETR_TREE， 检测所有轮廓，所有轮廓建立一个等级树结构。外层轮廓包含内层轮廓，内层轮廓还可以继续包含内嵌轮廓。
	    //第五个参数：int型的method，定义轮廓的近似方法
	    //	CHAIN_APPROX_NONE 保存物体边界上所有连续的轮廓点到contours向量内
	    //	CHAIN_APPROX_SIMPLE 仅保存轮廓的拐点信息，把所有轮廓拐点处的点保存入contours 向量内，拐点与拐点之间直线段上的信息点不予保留
	    //	CHAIN_APPROX_TC89_L1，CV_CHAIN_APPROX_TC89_KCOS使用teh-Chinl chain 近 似算法
	    Imgproc.findContours(image, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
	    
	    //轮廓 35条轮廓线
	    System.out.println(contours.size());
	    
	    //
	    for(int i=0;i<contours.size();i++){
	    	//颜色
	    	Scalar color = new Scalar(0, 0, 0);
	    	//
	    	int contourIdx = i;
	    	//Imgproc.drawContours(image, contours, contourIdx, color, 2, 8, hierarchy, 0, new Point());
	    }/*
	    for(MatOfPoint con:contours){
	    	
	    	Size size = con.size();
	    	System.out.printf("(x,y:)-->("+size.width+","+size.height+")\r");
	    	
	    	
	    	Imgproc.drawContours(image, contours, contourIdx, color);
	    	
	    }*/
	    
	    
	    // 写入到文件
	    Highgui.imwrite(outPath, image);
	}
}
