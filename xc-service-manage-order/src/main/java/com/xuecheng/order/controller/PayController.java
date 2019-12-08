package com.xuecheng.order.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.xuecheng.framework.web.BaseController;
import com.xuecheng.order.config.AlipayConfig;
import com.xuecheng.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author User
 * @date 2019/12/7
 * @description
 */
@Controller
@RequestMapping("/order/pay")
public class PayController extends BaseController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/zhifubao", method = RequestMethod.POST)
    public void pay() {
        try {
            //获得初始化的AlipayClient
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

            //设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            alipayRequest.setReturnUrl(AlipayConfig.return_url);
            alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

            //商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = new String(request.getParameter("orderNumber").getBytes("ISO-8859-1"), "UTF-8");
            //付款金额，必填
            String total_amount = new String(request.getParameter("price").getBytes("ISO-8859-1"), "UTF-8");
            //订单名称，必填
            String subject = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
            //商品描述，可空
            String body = "箩筐在线课程支付";
            System.out.println(out_trade_no + "    " + total_amount + "  " + subject);
            alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                    + "\"total_amount\":\"" + total_amount + "\","
                    + "\"subject\":\"" + subject + "\","
                    + "\"body\":\"" + body + "\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
            //请求
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            ServletOutputStream outputStream = response.getOutputStream();
            response.setHeader("Content-type", "text/html;charset=utf-8");
            //输出
            outputStream.write(result.getBytes("utf-8"));

            //outputStream.write(pageHtml.getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 支付成功异步通知
     */
    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    public boolean payNotify() {
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            try {
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            params.put(name, valueStr);
        }

        boolean signVerified = true; //调用SDK验证签名
        /*try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }*/

        //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
        if (signVerified) {//验证成功
            try {

                //商户订单号
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //支付宝交易号
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //交易状态
                String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
                System.out.println(trade_status);
                if (trade_status.equals("TRADE_FINISHED")) {
                    System.out.println("finsh");
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //如果有做过处理，不执行商户的业务程序
                    orderService.updateOrderPayStatus(out_trade_no,trade_no);
                    //注意：
                    //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知

                } else if (trade_status.equals("TRADE_SUCCESS")) {
                    System.out.println("success");
                    orderService.updateOrderPayStatus(out_trade_no,trade_no);
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //如果有做过处理，不执行商户的业务程序

                    //注意：
                    //付款完成后，支付宝系统发送该交易状态通知
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {//验证失败
            return false;
        }
    }

    @RequestMapping(value = "/returnUrl",method = RequestMethod.GET)
    public String returnUrl() {
        return "redirect:http://ucenter.xuecheng.com/#/order";
    }
}
