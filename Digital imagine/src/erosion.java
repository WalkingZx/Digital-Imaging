import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * 
 */

/**
 * @author Shaw
 *
 */
public class erosion {
	static{	
		System.out.println("Welcome to OpenCV" + Core.VERSION);
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);};
		
	private int MORPH_RECT = 0;
	
	public erosion(){
		try {
			Mat sourceImage = Imgcodecs.imread("src/lena.png");
			Mat newImage = myErosion(sourceImage);
			Mat sysImage= sysErosion(sourceImage);

			Imgcodecs.imwrite("src/lena_erosion.png", newImage);
			Imgcodecs.imwrite("src/lena_erosion_system.png", sysImage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Mat sysErosion(Mat m) {
		Mat rImage = new Mat();
		Imgproc.erode(m, rImage, Imgproc.getStructuringElement(MORPH_RECT, new Size(5,5)));
		return rImage;
	}
	
	public Mat myErosion(Mat m) {
		Mat result = m.clone();
		int weight = m.rows();
		int hight = m.cols();
		
		for(int i=2; i<weight-3; i++) {
			for(int j=2; j<hight-3; j++) {
				double[] min= {255.0};
				boolean change = false;		
				for(int x=i-2; x<i+3; x++) {
					for(int y=j-2; y<j+3; y++) {
							if(m.get(x, y)[0] < min[0]) {
								min = m.get(x, y);
								change = true;						
							}
						}
					}
				if(change) result.put(i, j, min);
				}
			}
		return result;
	}
	
	public static void main(String[] args){
		new erosion();
	}
}
