package lorenz875013.a01;

import cgtools.Vec3;
import static cgtools.Vec3.*;
import java.io.IOException;
import java.util.function.*;
import lorenz875013.Image;

public class Main {

    public static void main(String[] args) {
        //final int width = 160;
        //final int height = 90;
        final int width = 3440;
        final int height = 1440;

        /** static color **/

        Image image = new Image(width, height);

        class ConstantColor {
            Vec3 color;

            ConstantColor(Vec3 color) {
                this.color = color;
            }

            Vec3 pixelColor(double x, double y) {
                return color;
            }
        }

        ConstantColor allGray = new ConstantColor(Vec3.gray);
        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                image.setPixel(x, y, allGray.pixelColor(x, y));
            }
        }

        write(image, "doc/a01.png");

        /** square image **/

        Image imageSquare = new Image(width, height);

        class ColoredSquare{
            Vec3 color;

            ColoredSquare(Vec3 color) { this.color = color; }

            Vec3 pixelColor(double x, double y){ return color;}
        }

        ConstantColor squareColor = new ConstantColor(Vec3.red);
        int imageMiddleX = width / 2;
        int imageMiddleY = height / 2;
        double squareSizeMultiplier = 0.8;
        int squareRadius;
        if(height > width){
            squareRadius = (int) (width * squareSizeMultiplier / 2);
        } else {
            squareRadius = (int) (height * squareSizeMultiplier / 2);
        }

        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                /** define left and right space around the square **/
                if(x > imageMiddleX - squareRadius && x < imageMiddleX + squareRadius) {
                    /** define top and bottom space around the square **/
                    if(y > imageMiddleY - squareRadius && y < imageMiddleY + squareRadius) {
                        image.setPixel(x, y, squareColor.pixelColor(x, y));
                    }
                }
            }
        }

        write(image, "doc/a01-square");

        /** checkerboard **/

        Image imageCheckerBoard = new Image(width, height);

        // size in pixels on how wide one box of the checker board is
        // a checker board has 8 squares
        int checkerBoxSize = (squareRadius * 2) / 8;
        boolean primaryColor = true;
        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                /** define left and right space around the square **/
                if(x > imageMiddleX - squareRadius && x < imageMiddleX + squareRadius) {
                    /** define top and bottom space around the square **/
                    if(y > imageMiddleY - squareRadius && y < imageMiddleY + squareRadius) {
                        /** define the checkerboard **/
                    }
                } else {
                    if (primaryColor){

                    } else {

                    }
                }
            }
        }

        write(imageCheckerBoard, "doc/a01-checkered-background.png");
    }

    static void write(Image image, String filename) {
        try {
            image.write(filename);
            System.out.println("Wrote image: " + filename);
        } catch (IOException error) {
            System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
        }
    }

}
