import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**

 * @ClassName:     opening

 * @Description:   take a opening operation in a picture 

 * @author          Shaw

 * @version         V1.0  

 * @Date           10/01/2018

 */

public class opening {
	static{	
		System.out.println("Opening operation is under processing. ");
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);};
		
	private int MORPH_RECT = 0;
	
	public opening(){
	}
	
	public Mat myOpening(Mat m) {
		Mat rImage = new Mat();
		erosion er = new erosion();
		dilation d = new dilation();
		rImage = d.myDilation(er.myErosion(m));
		return rImage;
	}
	
	public Mat sysOpen(Mat m) {
		Mat rImage = new Mat();
		Imgproc.morphologyEx(m,rImage,Imgproc.MORPH_OPEN,Imgproc.getStructuringElement(MORPH_RECT, new Size(5,5)));
		return rImage;
	}
	
	public static void main(String[] args){
		opening open = new opening();
		try {
			Mat sourceImage = Imgcodecs.imread("src/lena.png");
			Mat newImage = open.myOpening(sourceImage);
			Imgcodecs.imwrite("src/lena_opening.png", newImage);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
