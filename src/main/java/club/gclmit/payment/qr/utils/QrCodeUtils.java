package club.gclmit.payment.qr.utils;

import club.gclmit.payment.qr.model.enums.QrCodeType;
import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.utils
 * @author: gclm
 * @date: 2019-02-14 19:52
 * @description: 基于 ZXing 二维码工具类
 */
@Slf4j
@Component
public class QrCodeUtils {

    @Autowired
    private ObjectIsNullUtils objectIsNullUtils;

    @Autowired
    private FileConversionUtil fileConversionUtil;

    @Autowired
    private IdWorker idWorker;

    /**
     * 二维码字符集默认编码格式
     */
    public final  static  String IMAGE_CHARACTER_ENCODE = "UTF-8";

    /**
     * 生成的二维码格式
     */
    public final  static String IMAGE_FORMAT_NAME = "png";

    /**
     * 根据 本地/网络 二维码图片————————解析二维码内容
     * （注：图片必须是二维码图片，但也可以是微信用户二维码名片，上面有名称、头像也是可以的）
     *
     *  @details 孤城落寞 2019-02-14 20:44
     * @param content 传输内容，可以为文件路径、可以为网络路径
     * @param qrCodeType
     * @return java.lang.String
     */
    public String parseQrCode(Object content,String qrCodeType) throws IOException, NotFoundException {

        /**
         * 创建 BufferedImage 对象
         */
        BufferedImage bufferedImage = null;

        /**
         * 根据 二维码类型来采用不同的 ImageIO 操作，
         * ImageIO.read(URL input) 读取网络图片文件转换为内存缓冲图像
         *        .read(File input) 读取本地图片文件...
         *        .read(InputStream input) 读取读取输入流
         *        .read(ImageInputStream stream) 读取图片输入流
         */
        if (QrCodeType.NET_QR_CODE.getDesc().equals(qrCodeType) && !objectIsNullUtils.isEmpty(content)){
            log.info("NET_QR_CODE content类型："+content.getClass());
            URL url = new URL((String) content);
            bufferedImage = ImageIO.read(url);
        } else if (QrCodeType.LOCAL_QR_CODE.getDesc().equals(qrCodeType) && !objectIsNullUtils.isEmpty(content)){
            log.info("LOCAL_QR_CODE content类型："+content.getClass());
            bufferedImage = ImageIO.read((File)content);
        } else {
            return null;
        }

        /**
         *  com.google.zxing.client.j2se.BufferedImageLuminanceSource: 缓冲图像亮度源
         *  作用：将 java.awt.image.BufferedImage 转为 zxing 的缓冲图像亮度源
         *  BinaryBitmap：二进制位图
         *  HybridBinarizer： 用于读取二维码图像数据
         */
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));

        /**
         * 如果内容包含中文，则解码的字符集格式应该和编码时一致
         */
        Hashtable hints = new Hashtable();
        hints.put(DecodeHintType.CHARACTER_SET,IMAGE_CHARACTER_ENCODE);

        /**
         * 如果图片不是二维码图片，则 decode 抛出异常 com.google.zxing.NotFoundException
         * MultiFormatWriter 的 encode 用于对内容进行编码成 2D 矩阵
         * MultiFormatReader 的 decode 用于读取二进制位图数据
         */
        Result result = new MultiFormatReader().decode(binaryBitmap, hints);

        return result.getText();
    }

    /**
     * 二维码生成工具类
     * @details 孤城落寞 2019-02-15 10:03
     * @param qrCode
     * @return java.lang.String
     */
    public String generateQrCode(QrCode qrCode) throws WriterException, IOException {

        /**
         * com.google.zxing.EncodeHintType 编码提示类型，枚举类型
         *  EncodeHintType.CHARACTER_SET 设置字符编码类型
         *  EncodeHintType.ERROR_CORRECTION 误差校正级别，详情看 QrCode 的 errorLevel 属性
         *  EncodeHintType.MARGIN 设置二维码边框，详情看 QrCode 的 margin 属性
         */
        Map<EncodeHintType,Object> hists = new HashMap<>();
        hists.put(EncodeHintType.CHARACTER_SET,qrCode.getEncode());
        hists.put(EncodeHintType.ERROR_CORRECTION,qrCode.getErrorLevel());
        hists.put(EncodeHintType.MARGIN,qrCode.getMargin());


        /**
         * MultiFormatWriter:多格式写入，这是一个工厂类，里面重载了两个 encode 方法，用于写入条形码或二维码
         *      encode(String contents,BarcodeFormat format,int width, int height,Map<EncodeHintType,?> hints)
         *      contents:条形码/二维码内容
         *      format：编码类型，如 条形码，二维码 等
         *      width：码的宽度
         *      height：码的高度
         *      hints：码内容的编码类型
         * BarcodeFormat：枚举该程序包已知的条形码格式，即创建何种码，如 1 维的条形码，2 维的二维码 等
         * BitMatrix：位(比特)矩阵或叫2D矩阵，也就是需要的二维码
         */
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix =  multiFormatWriter.encode(qrCode.getContent(), qrCode.getFormat(), qrCode.getWidth(), qrCode.getHeight(), hists);

        BufferedImage bufferedImage = bitMatrixToBufferedImage(bitMatrix, qrCode);

        if (("file").equals(qrCode.getGenerateType())){
            String path = qrCode.getGeneartePath().toString();

            log.info("path1 :"+path);
            log.info("path2 :"+new StringBuilder().append(idWorker.nextId()).append(".png").toString());

            File codeImgFile = null;
            if(path.lastIndexOf("/") == path.length()-1){
                codeImgFile =  fileConversionUtil.judgeFileExists(new StringBuilder().append(path).append(idWorker.nextId()).append(".png").toString());
            } else {
                codeImgFile =  fileConversionUtil.judgeFileExists(new StringBuilder().append(path).append("/").append(idWorker.nextId()).append(".png").toString());
            }

            log.info("path:"+codeImgFile.getPath());

            ImageIO.write(bufferedImage,IMAGE_FORMAT_NAME,codeImgFile);

            return  codeImgFile.getPath();
        } else {
            /**
             * 1. 创建字节输出流，将生成的二维码图片写入到创建的字节输出流里
             */
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage,IMAGE_FORMAT_NAME,outputStream);

            /**
             * 2.读取文件 --> 字节数组 --> 二进制
             *
             */
            byte[] bytes = outputStream.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encodeBuffer(bytes).trim();
        }
    }

    /**
     * BitMatrix 转换为 BufferedImage
     * @details 孤城落寞 2019-02-15 14:17
     * @param bitMatrix
     * @param qrCode
     * @return java.awt.image.BufferedImage
     */
    public BufferedImage  bitMatrixToBufferedImage(BitMatrix bitMatrix,QrCode qrCode){

        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();

        /**java.awt.image.BufferedImage：具有图像数据的可访问缓冲图像，实现了 RenderedImage 接口
         * BitMatrix 的 get(int x, int y) 获取比特矩阵内容，指定位置有值，则返回true，将其设置为前景色，否则设置为背景色
         * BufferedImage 的 setRGB(int x, int y, int rgb) 方法设置图像像素
         *      x：像素位置的横坐标，即列
         *      y：像素位置的纵坐标，即行
         *      rgb：像素的值，采用 16 进制,如 0xFFFFFF 白色
         */
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        for (int x = 0 ; x < width ; x++){
            for (int y = 0; y < height ; y++) {
                bufferedImage.setRGB(x,y,bitMatrix.get(x,y) ? qrCode.getFroutColor() : qrCode.getBackgroudColor());
            }
        }
        return bufferedImage;
    }


    /**
     * 初始化 Qrcode 对象
     */
    public QrCode instance(){
        return new QrCode();
    }

    /**
     * 生成二位码工具类
     *
     * 注意事项：
     * 1. 二维码宽和高单位：像素
     * 2. 颜色采用16进制表示，和前端页面 CSS 的取色是一样的，注意前后景颜色应该对比明显，如常见的黑白
     */
    @Data
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public class QrCode{

        /**
         * 二维码内容
         */
        private String content;

        /**
         * 生成类型，有 base64、file 二种方式
         * 默认为 base64
         */
        private String generateType = "base64";

        /**
         * 当 generateType 为 file 时，geneartePath 为文件保存路径
         */
        private Object geneartePath;

        /**
         * 二维码高,默认为400，单位：像素
         */
        private Integer height = 400;;

        /**
         * 二维码宽,默认为400，单位：像素
         */
        private Integer width = 400;;

        /**
         * 二维码前景色。默认为 黑色 0x000000 表示黑色
         *
         */
        private Integer froutColor = 0x000000;

        /**
         * 背景色，默认为白色 0xFFFFFF 表示白色
         */
        private Integer backgroudColor = 0xFFFFFF;

        /**
         * 编码格式，默认为 UTF-8
         */
        private String encode = IMAGE_CHARACTER_ENCODE;

        /**
         * BarcodeFormat ：设置编码类型，
         *  有条形码、二维码等，默认为二维码
         */
        private BarcodeFormat format = BarcodeFormat.QR_CODE;

        /**
         *  ErrorCorrectionLevel 误差校正级别
         *  L = ~7%
         *  M = ~15%
         *  Q = ~25%
         *  H = ~30%
         *  默认为 L,级别越高贮存的信息越少，生成的图案不同，但扫描结果一样
         */
        private ErrorCorrectionLevel errorLevel = ErrorCorrectionLevel.M;

        /**
         * 二维码边框，单位为：像素，值越小，二维码距离四周越近
         */
        private Integer margin = 1;

        public QrCode(String content, String generateType, Integer height, Integer width) {
            this.content = content;
            this.generateType = generateType;
            this.height = height;
            this.width = width;
        }
    }
}
