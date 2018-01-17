import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**

 * @ClassName:     closing

 * @Description:   take a closing operation in a picture 

 * @author          Shaw

 * @version         V1.0  

 * @Date           10/01/2018

 */

public class closing {
	static{	
		System.out.println("Closing operation is under processing. ");
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);};
	
	/** The value is used for the shape of the structure element. */
	private int MORPH_RECT = 0;
	
	public closing(){
	}
	
	/**
	 * @param m the Mat of the original picture
	 * @return the Mat after opening by using dilation and erosion methods that created by me
	 */
	public Mat myClosing(Mat m) {
		Mat rImage = new Mat();
		erosion er = new erosion();
		dilation d = new dilation();
		//erode the dilation result
		rImage = er.myErosion(d.myDilation(m));
		return rImage;
	}
	
	/**
	 * @param m the Mat of the original picture
	 * @return the Mat after closing by using opencv
	 */
	public Mat sysClose(Mat m) {
		Mat rImage = new Mat();
		Imgproc.morphologyEx(m,rImage,Imgproc.MORPH_CLOSE,Imgproc.getStructuringElement(MORPH_RECT, new Size(5,5)));
		return rImage;
	}
	
	public static void main(String[] args){
		closing close = new closing();
		try {
			Mat sourceImage = Imgcodecs.imread(args[0]);
			Mat newImage = close.myClosing(sourceImage);
			Imgcodecs.imwrite(args[1], newImage);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
