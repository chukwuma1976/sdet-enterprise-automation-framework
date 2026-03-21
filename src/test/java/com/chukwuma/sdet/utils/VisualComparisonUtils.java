package com.chukwuma.sdet.utils;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VisualComparisonUtils {

    public static ImageComparisonResult compareImages(
            BufferedImage expected,
            BufferedImage actual) {

        ImageComparison imageComparison = new ImageComparison(expected, actual);

        imageComparison.setPixelToleranceLevel(0.01); // 1% tolerance

        return imageComparison.compareImages();
    }

    public static BufferedImage loadImage(String path) throws IOException {
        return ImageIO.read(new File(path));
    }
}