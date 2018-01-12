# Digital-Imaging
#!/bin/sh
javac -cp /opt/local/opencv/3.0/share/OpenCV/java/opencv-300.jar erosion.java
java -cp /opt/local/opencv/3.0/share/OpenCV/java/opencv-300.jar:. -Djava.library.path=/opt/local/opencv/3.0/share/OpenCV/java erosion lena.png lena_erosion.png

javac -cp /opt/local/opencv/3.0/share/OpenCV/java/opencv-300.jar dilation.java
java -cp /opt/local/opencv/3.0/share/OpenCV/java/opencv-300.jar:. -Djava.library.path=/opt/local/opencv/3.0/share/OpenCV/java dilation lena.png lena_dilation.png

javac -cp /opt/local/opencv/3.0/share/OpenCV/java/opencv-300.jar erosion.java dilation.java opening.java
java -cp /opt/local/opencv/3.0/share/OpenCV/java/opencv-300.jar:. -Djava.library.path=/opt/local/opencv/3.0/share/OpenCV/java opening lena.png lena_opening.png

javac -cp /opt/local/opencv/3.0/share/OpenCV/java/opencv-300.jar erosion.java dilation.java closing.java
java -cp /opt/local/opencv/3.0/share/OpenCV/java/opencv-300.jar:. -Djava.library.path=/opt/local/opencv/3.0/share/OpenCV/java closing lena.png lena_closing.png
