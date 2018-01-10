import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**

 * @ClassName:    closing

 * @Description:   take a closing operation in a picture 

 * @author          Shaw

 * @version         V1.0  

 * @Date           10/01/2018

 */

public class closing {
	static{	
		System.out.println("Welcome to OpenCV" + Core.VERSION);
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);};
		
	private int MORPH_RECT = 0;
	
	public closing(){
		try {
			Mat sourceImage = Imgcodecs.imread("src/lena.png");
			Mat newImage = myErosion(myDilation(sourceImage));
//			Mat sysImage = sysClose(sourceImage);
//			Imgcodecs.imwrite("src/lena_sys_closing.png", sysImage);
			Imgcodecs.imwrite("src/lena_my_closing.png", newImage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Mat sysClose(Mat m) {
		Mat rImage = new Mat();
		Imgproc.morphologyEx(m,rImage,Imgproc.MORPH_CLOSE,Imgproc.getStructuringElement(MORPH_RECT, new Size(5,5)));
//		Imgproc.MORPH_OPEN(m, rImage, Imgproc.getStructuringElement(MORPH_RECT, new Size(5,5)));
//		Imgproc.MORPH_OPEN();
		return rImage;
	}
	
	public Mat myErosion(Mat m) {
		Mat result = m.clone();
		int weight = m.rows();
		int hight = m.cols();
		
		for(int i=2; i<weight-2; i++) {
			for(int j=2; j<hight-2; j++) {
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
	
	public Mat myDilation(Mat m) {
		Mat result = m.clone();
		int weight = m.rows();
		int hight = m.cols();
		
		for(int i=2; i<weight-2; i++) {
			for(int j=2; j<hight-2; j++) {
				double[] max= {0.00};
				boolean change = false;
				for(int x=i-2; x<i+3; x++) {
					for(int y=j-2; y<j+3; y++) {
							if(m.get(x, y)[0] > max[0]) {
								max = m.get(x, y);
								change = true;						
							}
						}
					}
				if(change) result.put(i, j, max);
				}
			}
		return result;
	}
	
	public static void main(String[] args){
		new closing();
	}
}
