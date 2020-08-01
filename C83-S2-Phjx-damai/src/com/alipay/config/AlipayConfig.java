package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000116658173";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCp2Q9E8IBjVRzp+eMjhZ9z42CnP4eVp3khiMALWZa9m7lZN0bPmzmzKSxfmeoREnaNULdHzQmbfIPBEeLjlcIXXaeINpsl3d59tKH8RENmvaxtRmNaQNCxl77hwCrvJII1MegQJPlkPvjiP8sJIAi5fiD9FxfDB6SdGWvI/29Tb9GYVGSrB5KRm4dRgdcK29bx7wvupBpRzytQPhRUMcwOdDhzSSpf1gCL7IQ61in4B3tG/ghx5v+1rV2tMYw5r1u11XgvFsrLrqeEAe00DdDZV6Fg+fomIgOy2zmaSlo27+2JpACekhz4iVj5o6eGJqV6yJOMAEReiS5J3Rb4O5WxAgMBAAECggEAIDtt92JAqxka3Jgsb7BK+z4aOIMI4vCePyvpbf35alAkE3c2++z0PTjCD/5wef7r+pNnxLt3aEdv5XLUgOYJqE+AnIvoU35QW8CswOffdp7mGvKxpg4IzLsEYWkLfMiL4TZwWKf7n/eHWcBLcpbesPzrm7wHKV7I8XtChilbYhTWHvfoyWGypiyLbq/jdFZwS4SxDghDWx3lrKLFzlv+mVSWtM6h+v8Ouab0ayNoOoGADP0PYDAuvrrmFNWyPy/Go5EWXavmUQOzJlSEXqGqRjxPTWqWtrbnDHaXQwK9b0L35vYSwp5/DMVZICL80jUqmTiF9wg6nbRt7cVcr3m0QQKBgQDW01QPhGt5CSY9G7yM5+GVWbszXoAcjPPF/sUVscbbdY/YWBQkISHZlk3+Q9iIAFlJYhQzA8F7t7z1DGMTzPMjYfgMoBKblbpXnBVERjGzvHz2jSSVRlgXVtbXKGse2t+qR8jV5FpSTfafZgbAb3tJTCXg4usztmXNW/rgyGakCQKBgQDKZtnDg82P1JfrepiZcO4uXsYvPzLiy+Q1vP4u1r7m2rxRlircQwFDvdLDbQrHNI1aNSrJ22oFCIGB48+pn08b9MYPGfjCvHgA56npuUSEMrkAdiN8b7KWEJsQLgcnuQLrzGKdGNYL2XmtN2U5ezNcYUokjzmhCFM5CKg3xm9eaQKBgCvxorph3hIuOvI5IEK6lDyW1GJXrGEqvBr+WJZtfT4bOEeLDOOvG1uFEYM3oa2AVMNcGsGsvQFvvmlqFUJRpFLSkYkTnXrSEAIn3XnP2g7oOa+9g4Q9lXuAugXdMLxpDWOpt315brhzVkWY+zyWEWQW/ZCZGn/DieOOMy2UENrxAoGAcz0wC5PFvHisrtQ0gli8JAauDESNcnfoTXh3uK9oLgSoaEN5z8MOu0Ue9UnmcxlGye+vlxKoQIlhQ1Uz7T3ZSu+IvRxqeGXaZZmBMqPbkPFYSBz7g2QNaEe4IfQy+rjqIttR4mQ7qq9+CdOafKnaymRSVJJ/m2IkEDsPnAINctECgYA6qz/Oe1yPBVXP+2NRL0UYbnuR7z95K8hN1PLqrp8u53ivmi+3ufWNy6YICV5fHvvB1761ALgWs3IM5wy1nAewDTzZShG55Klkro+9YeCjZ7DkzcGvy9VMsAwYl5AKowBs5ZWWAKVWMxD5AFcqGO7W1nnv2fZS7O6u69uUM8mWVA==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA9Qua8fdV0YuqQ6qU3ohQ/cXYH9ccgrGs0oEJoX8peIHoI9lT0hKlPsnzooKt9RgwN+kk7vQmNe3svBBTx3fiMAmow3prD/qlbqMeLsWVSalTEuCLWe3djbBNjaBrUoOG5Wc8EKj2F4fTVMegkm5l1Ey1j8zAvVNQLrTyB8ftBvdpY4v7TdcYYaH5j+Hr4GpSpcYFrzpqUTI1VxW1hhG74xJKhSAvK/ygEnzp/LCrly6Il3o9FoUKMom29KRLkcNoUk23v/6lbmq97gd3rpYLN0LhKXeoot6y2RVp6QYaMikmlfMBjotsKVBvCJhrvp8BIoGA6XUiiddIobT/OdXZmQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://127.0.0.1/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://39.98.119.108:8080/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "https://openapi.alipaydev.com/gateway.do";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

