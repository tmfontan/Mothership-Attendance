/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
/*from  w ww  . j a v  a 2s.c  o m*/
import javax.imageio.ImageIO;

public class CreateImageFromText {
    
    public int jLabelWidth = 0;
    public int jLabelHeight = 0;
    //495
    //100
    
    public CreateImageFromText() {
        //
    }
    
    public int determineFontSizeAmericanCaptain(String name, int w, int h, int size) {
        
        boolean withinSize = false;
        int fontSize = size;
        Font font;
        
        jLabelWidth = w;
        jLabelHeight = h;
        
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        font = new Font("American Captain", Font.PLAIN, fontSize);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();

        int width = fm.stringWidth(name);
        int height = fm.getHeight();
        
        if (width > jLabelWidth || height > jLabelHeight) {
            
            fontSize--;
            
            while (withinSize == false) {
            
                img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
                g2d = img.createGraphics();
                font = new Font("American Captain", Font.PLAIN, fontSize);
                g2d.setFont(font);
                fm = g2d.getFontMetrics();

                width = fm.stringWidth(name);
                height = fm.getHeight();

                if (width < jLabelWidth && height < jLabelHeight) {
                    withinSize = true;
                }
                else {
                    fontSize--;
                }

                g2d.dispose();
            }
        }
        else {
            
            fontSize++;
            
            while (withinSize == false) {
            
                img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
                g2d = img.createGraphics();
                font = new Font("American Captain", Font.PLAIN, fontSize);
                g2d.setFont(font);
                fm = g2d.getFontMetrics();

                width = fm.stringWidth(name);
                height = fm.getHeight();

                if (width > jLabelWidth || height > jLabelHeight) {
                    withinSize = true;
                    fontSize--;
                }
                else {
                    fontSize++;
                }

                g2d.dispose();
            }
        }        

        return fontSize;
    }
    
    public int determineFontSizeLucidaGrande(String value, int w, int h, int size) {
        
        boolean withinSize = false;
        int fontSize = size;
        Font font;
        
        jLabelWidth = w;
        jLabelHeight = h;
         
        while (withinSize == false) {

            BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = img.createGraphics();
            font = new Font("Lucida Grande", Font.BOLD, fontSize);
            g2d.setFont(font);
            FontMetrics fm = g2d.getFontMetrics();

            int width = fm.stringWidth(value);
            int height = fm.getHeight();

            if (width < jLabelWidth && height < jLabelHeight) {
                withinSize = true;
            }
            else {
                fontSize--;
            }

            g2d.dispose();
        }

        return fontSize;
    }
    
    /*
    public static void main(String[] args) throws Exception {
        String text = "Tyler Fontana";

        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        
        //jLabel30.setFont(new java.awt.Font("Avengeance Heroic Avenger", 1, 75)); // NOI18N
        //jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        
        Font font = new Font("Avengeance Heroic Avenger", 1, 75);
        
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = 500;
        int height = 135;
        g2d.dispose();

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
            RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
            RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING,
            RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
            RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
            RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        g2d.setColor(Color.BLACK);
        g2d.drawString(text, 0, fm.getAscent());
        g2d.dispose();

        ImageIO.write(img, "png", new File("Text.png"));
    }*/
}
