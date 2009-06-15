package com.haina.beluga.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Component;

import com.haina.beluga.webservice.Constant;
@Component
public class IMService {

	public static int getQQStatus(int qqCode){
		if(qqCode <= 10000 || qqCode >1425000000)
			return Constant.QQ_ARG_ERROE;
		URL feedUrl;
		try {
			feedUrl = new URL("http://wpa.qq.com/pa?p=1:"+qqCode+":4");
			java.awt.image.BufferedImage bi = javax.imageio.ImageIO.read(feedUrl);
			if(bi.getWidth()==Constant.QQ_OFFLINE_WIDTH)
				return Constant.QQ_OFFLINE;
			else
				return Constant.QQ_ONLINE;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return Constant.NETWORK_ERROR;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return Constant.NETWORK_ERROR;
		}catch (IOException e) {
			e.printStackTrace();
			return Constant.NETWORK_ERROR;
		}
	}
}
