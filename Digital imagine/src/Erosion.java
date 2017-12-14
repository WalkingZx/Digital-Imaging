import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * 
 */

/**
 * @author zgz
 *
 */
public class Erosion {
	static{	
		System.out.println("Welcome to OpenCV" + Core.VERSION);
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);};
		
		private int MORPH_RECT = 0;
	
	public Erosion(){
		Mat source = Imgcodecs.imread("src/lena.png");
		Mat desImg = sysErosion(source);
		Imgcodecs.imwrite("src/lena1.png", desImg);
	}
	
	public Mat sysErosion(Mat m) {
		Mat rImage = new Mat();
		Imgproc.erode(m, rImage, Imgproc.getStructuringElement(MORPH_RECT, new Size(5,5)));
		return rImage;
	}
	
	public Mat myErosion(Mat m) {
		Mat rImage = new Mat();
		return rImage;
	}
	
	public static void main(String[] args){
		new Erosion();
	}
}
