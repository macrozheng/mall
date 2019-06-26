package com.macro.mall.sms.validate.image;

import com.macro.mall.sms.validate.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Author: HanLong
 * @Date: Create in 2018/3/19 21:09
 * @Description:
 */
public class ImageCode extends ValidateCode {

    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireId) {
        super(code, LocalDateTime.now().plusSeconds(expireId));
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime localDateTime) {
        super(code, localDateTime);
        this.image = image;
    }


    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
