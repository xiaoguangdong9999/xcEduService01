package com.xuecheng.order.config;

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
	public static String app_id = "2016101000652927";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCEJJEs3kFvdm/TBTRgE0KY3zhyaYj5imYbJ20TFJ0Dhkiuy0POiz/l6j+IEOcTDrzl1O3mQE7WfqZNNvLyhMGQh+7sxgSs1ERJzo+V+ymf3xyUEmmktxmBWza4MdRFFcxB2V/+tzqDlC3QJvPSnpxFAxS4uHkY/Ln80MwqQBUFHNSZFjAUBG5x9yfc4eiSCaPIHjxe2FF3yNPwn4oJUwaRY+SICj+mUzj7Ksb3CLXNciFU7nfFFO+tD7uvExjGgKflamoglVxQyytgrAIxcz4emvJuTtTmSiKcbZmiqxP5Rm/FqJM3tgkyFRJFrLabkvWYbyAih2vDI5gYJvWSCbExAgMBAAECggEAReQtYOThCTpEZQFAtXwaYk7WRTVqfEnmPYB54Xiqn12b/c+GuqzODCX8mlQOU/cclBcuCPWEfVCZVwe0Me9jjbVH+rW1bVAMGlmFeldQqb1RGUQv2i358IA1DfFVC4E++qkla8yvxz5NiOXBWmoHzzyU7iqr6jppiL1K7yHbvErDyR4IxffHv3rWyBUDBpE6SxtvMYiaZm7WMZYGtwZXRiwDU0BccYgjGCnRdCEu5XDDaVX+LIk25UmTVt5tgI2whDtoqK83n42ega3eOJigD8oyH6JZzVIfj46WujA+bhZJ2xzQbinENuCEu5RA97elBklX7WTlMz9OB5WjSIzlEQKBgQC9AlKTM9NGEXr0FI9J/ABaGCYD5Q4POLGwWDbQt+1+6IoWRf3mfmhPFzkhs9DQGuZn4j+XbFmUp3myzR5l8pBgeqhVtIauBETUzyQENx4+3eo4xzUjaUHG2Wia4NTH+KUaEOlHFVTYu/4pMBDYOVivQH8AeZ9YQR65pg1Hg3cGDQKBgQCy+oFWST7FZz0MPd7HW0iYs4Ykv+TNaMHNtRtrMeH4vbC1rW1E4kEYufOCbvUU1XWGZj4852SHlseMlUhKhilcNR5YqZfoGUaUX+nR3mx6T/+UaNDZEbVoE1tUDYIRuq9paiohatYY61WqxenEDC7pGndzvLF+ZUshrQBVjmuStQKBgHvqxOabiL2cgkb6H8N6w2ROBsZw6xnRqrWZ8D5BCCU3IileNAtk/tAzwpI1CQcXS836CIUnVUff0SsHzhE6yatnX2vpUo9OXy5CrDNTS7Id3gzhlhYkUSqkqS85jmeYve3r/nVLJ/h8sDKiv7hzSJCFsX5HPtAmyZUfp/8l0ugVAoGAQ0IIIJjd0ej98/+8HEadfWTZYC4MmXRogBky+JGCTatbIKrPiTdNJzGZ5MY1q2KcC6mYceKy0JFE4/ulcvv/xvrfMIpq33nAIDn68DcZpM0zSl4pPal99mJvy6JX4rm4+XA/7NbtJegqBUbilh3N8yNTCFc8jDcciMcufPhMTJECgYAFPlwlow2FNAkEb5yF7qwPJXBYMIaoIyfOjm7dpxQCECm+6m9LyWfqEqrLPxAp250E5j2JSh358k97pid6kI8CCZU1QCfUAOhKL4euCHtWiXRcyrBVPhMNCUe1Kv8hw7p/klwYOyr589zqoxAGESTf+Pyy0k0JOsXQBSR2QeupiQ==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsL4DZXKJXNrjlAPvY94ZKsmKgaI9b+076pPKUvZ/0SBE8sZHyJzeqLPe3BM1lhfdjtGMeF1km1MtEMR24GWCxvkX4SVGfRjCd5c6jebg5b0Dxnou8qD2mWKwWrAJrvzJWO4vuDn9Lhv0DLW3RCmSHVxbhcIw+M0Xp50/YFW2/O7qYcK/RV1OJzxyCzwOo9Vr4FmcfdaVEz9CeLEbcIPuFHffQibr/uQkT69rjosVFykSETSZ9pG2ytqG8CRx+1uVYrTZF/wyc9+402iAjh0LDIaEJkRylV9ONLWRMIC+4U2XDyW1zwFF7dBJXAYoP6OnouN020GgiOTdOChWZZaSRQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://xixi.natapp4.cc/order/pay/notify";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://xixi.natapp4.cc/order/pay/returnUrl";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


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

