/**
 * @author Can BEYAZNAR - 161044038
 *
 */

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;

public class ListofColors{

    private BufferedImage image;
    private int width;
    private int height;
    private PriorityQueue myColors;
    private String FileName = null;
    private int CountofInsertedColor=0;


    public ListofColors(String Input_FileName) {
        FileName = new String(Input_FileName);
    }

    public void readFile()
    {
        myLEX lex = new myLEX();
        myEUC euc = new myEUC();
        myBMX bmx = new myBMX();

        PriorityQueue LEX_QUEUE = new PriorityQueue(lex);
        PriorityQueue EUC_QUEUE = new PriorityQueue(euc);
        PriorityQueue BMX_QUEUE = new PriorityQueue(bmx);

        Thread Thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    File inputFile = new File(FileName);
                    image = ImageIO.read(inputFile);
                    width = image.getWidth();
                    height = image.getHeight();

                    myColors = new PriorityQueue(lex);
                    CountofInsertedColor = 0;

                    for (int i = 0; i < height; i++) {
                        for (int j = 0; j < width; j++) {

                            Color c = new Color(image.getRGB(j, i));
                            ColorClass _color = new ColorClass(c.getRed(), c.getGreen(),c.getBlue() );
                            myColors.offer(_color);
                            LEX_QUEUE.offer(_color);
                            EUC_QUEUE.offer(_color);
                            BMX_QUEUE.offer(_color);
                            System.out.println("Thread 1: "+_color);
                            CountofInsertedColor++;

                            if(CountofInsertedColor>=100)
                            {
                                Thread Thread2 = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            System.out.println("Thread 2: "+LEX_QUEUE.remove()+" Size: "+LEX_QUEUE.getSize());

                                        }
                                        catch (Exception e){
                                            System.out.println("Thread 2 Exception");
                                        }
                                    }
                                });
                                Thread2.start();

                                Thread Thread3 = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            System.out.println("Thread 3: "+EUC_QUEUE.remove()+" Size: "+EUC_QUEUE.getSize());
                                        }
                                        catch (Exception e){
                                            System.out.println("Thread 3 Exception");
                                        }
                                    }
                                });
                                Thread3.start();

                                Thread Thread4 = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            System.out.println("Thread 4: "+BMX_QUEUE.remove()+" Size: "+BMX_QUEUE.getSize());
                                        }
                                        catch (Exception e){
                                            System.out.println("Thread 4 Exception");
                                        }
                                    }
                                });
                                Thread4.start();

                            }
                        }
                    }
                }
                catch (Exception e) {
			System.out.println("Thread 1 Exception");
                }
            }
        });
        Thread1.start();
    }
}
