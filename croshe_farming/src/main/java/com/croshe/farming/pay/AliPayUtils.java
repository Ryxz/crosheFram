package com.croshe.farming.pay;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.PayTask;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class AliPayUtils {

    // 商户PID
    public static final String PARTNER = "2088121227185139";
    // 商户收款账号
    public static final String SELLER = "2595153833@qq.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM2rVMpNcdc5f0Mk\n" +
            "+e+ZyJmTqlwqhCNI7vEbYunoKUBW7/ovb+68vBacp9DwmlXeS4AR27adDI3bahcB\n" +
            "71hVRAYd8nloyPEeWwA6QSgKfch8x4g8p3s/JElAB0j3mAn2ZCtlKBOhPXYf23W3\n" +
            "TsUaUeymDShI/J03KlLbh4lUAWFJAgMBAAECgYAVvDuTvaeARVVLtUasqaXnhRGF\n" +
            "+ABK8lQ+KtTDP4PMhuOJnyXHJy8BfMDHdgX5eSqleD2uCN7gxwVpNQ0kWq57flxX\n" +
            "4B7Zpso+BFKQO3WtX0RvhwtcTlkZd4jBf07m/ugvBb6P2s3M+nG5DemlUmuowTZz\n" +
            "T3FNoumk2rZM79ABYQJBAPhBWIK65roL+lC+vLlkBCoeuXg5EFC3XFetOKDdLQsj\n" +
            "D0m1tR5D3eBR9cKIJai5OCIkhEzyziZ8NOcQkp0xpvUCQQDUFeDv3CZsJgoH8eBp\n" +
            "f8BxpAwR3wdKx2poYWsKpaKscu/6H0sE3279RmUilUUIJOVrw86Ro3UqSNlp9jNT\n" +
            "8JSFAkBI1Qr9l423SWpPpBXEIhaisM7i+YLS22iJBHXXo+viF5AVpOyVC34doI4E\n" +
            "W2MJBAX25lW2KYnXepseqxsIaRe9AkB7tQNOhZg3zvxc4/4lK2pCHxEXP4NOYAE2\n" +
            "P1SjkjfP5P8LY0Nr5TQlMfF9+ZmZhYP4lu8fzjtiXbnLb9tCo/rNAkEAkU++ksLd\n" +
            "fwUEOVtMnFzFox7Ag+Q6PewIUSw2bPwG3lhDe8LhHqaZDp0emOL7o5ddTJrHnRY+\n" +
            "yIHgmKTHQBoPmw==";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDNq1TKTXHXOX9DJPnvmciZk6pc\n" +
            "KoQjSO7xG2Lp6ClAVu/6L2/uvLwWnKfQ8JpV3kuAEdu2nQyN22oXAe9YVUQGHfJ5\n" +
            "aMjxHlsAOkEoCn3IfMeIPKd7PyRJQAdI95gJ9mQrZSgToT12H9t1t07FGlHspg0o\n" +
            "SPydNypS24eJVAFhSQIDAQAB\n";

    /**
     * call alipay sdk pay. 调用SDK支付
     */
    public static void pay(final Activity activity, final Handler mHandler,
                           String orderCode, String subject, String body, String money, String url) {
        // 订单
        String orderInfo = getOrderInfo(orderCode, subject, body, money, url);

        // 对订单做RSA 签名
        String sign = sign(orderInfo);
        try {
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 完整的符合支付宝参数规范的订单信息
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
                + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(activity);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo);

                Message msg = new Message();
                msg.what = 1;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * call alipay sdk pay. 调用SDK支付
     */
    public static void pay(final Activity activity, final Handler mHandler, String orderInfo) {
        // 从服务器获取的订单信息
        final String payInfo = orderInfo;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(activity);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo);

                Message msg = new Message();
                msg.what = 1;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * create the order info. 创建订单信息
     */
    public static String getOrderInfo(String orderCode, String subject,
                                      String body, String price, String url) {
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + orderCode + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + url + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    public static String sign(String content) {
        return AliSignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    public static String getSignType() {
        return "sign_type=\"RSA\"";
    }

}
