package yellowcong.day01_06;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        Mat mat = Mat.eye( 3, 3, CvType.CV_8UC1 );
        System.out.println( "mat = " + mat.dump() );
	}

}
