package club.gclmit.payment.qr.model.enums;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.share.entity
 * @author: gclm
 * @date: 2018-12-23 14:18
 * @description:  返回状态码
 */
public class StatusCode {

    /**
     *  成功
     */
    public static final  int  OK = 20000;

    /**
     *  失败
     */
    public static final  int ERROR = 20001;

    /**
     * 账号或密码错误
     */
    public static final  int LOGIN_ERROR = 20002;

    /**
     * 权限不足
     */
    public static final  int ACCES_ERROR = 20003;

    /**
     * 远程调用失败
     */
    public static final  int REMOTE_ERROR = 20004;

    /**
     *  重复提交
     */
    public static final  int  REP_ERROR = 20005;

    /**
     * 获取数据超时
     */
    public  static  final int TIMEOUT_ERROR = 20006;

    /**
     * 当前网络已经断开
     */
    public  static  final int UNKNOWN_HOST_ERROR = 20007;

    /**
     * 上传文件失败
     */
    public  static  final int UPLOAD_FILE_ERROR = 20008;

    /**
     * 上传文件成功
     */
    public  static  final int UPLOAD_FILE_OK = 20009;


    /**
     * 二维码解析失败
     */
    public  static  final int PARSE_QR_ERROR = 20010;

    /**
     * 二维码解析成功
     */
    public  static  final int PARSE_QR_OK = 20011;

    /**
     * 异常处理
     */
    public  static  final int EXCEPTION_CODE = 20012;

}
