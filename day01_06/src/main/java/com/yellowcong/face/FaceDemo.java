package com.yellowcong.face;

import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

/**
 * 创建日期:2018年1月6日<br/>
 * 创建时间:上午11:59:19<br/>
 * 创建者    :yellowcong<br/>
 * 机能概要:
 */
public class FaceDemo {
	
	public static void main(String[] args) {
		
		//图片地址
		String inputImagePath = FaceDemo.class.getClassLoader().getResource("pics/demo.jpg").getFile();
		// 指定读出的图片路径和输出的文件
	    String outputImageFile = "D:/demo_identificate.png";
	    
	    //加载lib,这个lib的名称
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    
	    //读取图像的 mat信息
	    Mat image = Highgui.imread(new File(inputImagePath).getAbsolutePath());
	    
	    //初始化人脸识别类
	    MatOfRect faceDetections = new MatOfRect();
	    //添加识别模型
	    String xmlPath = FaceDemo.class.getClassLoader().getResource("cascade_storage.xml").getPath().substring(1);
	    CascadeClassifier faceDetector = new CascadeClassifier(xmlPath);
	    //识别脸
	    faceDetector.detectMultiScale(image, faceDetections);
	    
	    // 画出脸的位置
	    for (Rect rect : faceDetections.toArray()) {
	        Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 2);
	    }

	    // 写入到文件
	    Highgui.imwrite(outputImageFile, image);
	    
	    System.out.print("图片已经识别");
	}
}
