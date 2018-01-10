import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**

 * @ClassName:     erosion

 * @Description:   erode a picture 

 * @author          Shaw

 * @version         V1.0  

 * @Date           10/01/2018

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
//			Mat sourceImage2 = Imgcodecs.imread("src/lena.png");
//			Mat sysImage= sysErosion(sourceImage2);
			Imgcodecs.imwrite("src/lena_erosion.png", newImage);
//			Imgcodecs.imwrite("src/lena_erosion_system.png", sysImage);
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
		
		for(int i=2; i<weight-2; i++) {
			for(int j=2; j<hight-2; j++) {
				double[] min= {255.00};
				for(int x=i-2; x<i+3; x++) {
					for(int y=j-2; y<j+3; y++) {
							if(m.get(x, y)[0] < min[0]) {
								min = m.get(x, y);
								result.put(i, j, min);					
							}
						}
					}
				}
			}
		return result;
	}
	
	public static void main(String[] args){
		new erosion();
	}
}
