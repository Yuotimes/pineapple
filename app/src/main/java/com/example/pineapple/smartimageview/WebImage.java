package com.example.pineapple.smartimageview;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class WebImage implements SmartImage {
    private static final int CONNECT_TIMEOUT = 5000;
    private static final int READ_TIMEOUT = 10000;

    private static WebImageCache webImageCache;

    private String url;

    public WebImage(String url) {
        this.url = url;
    }

    public Bitmap getBitmap(Context context) {
        //初始化图片缓存类
        if(webImageCache == null) {     /*准备缓存*/
            webImageCache = new WebImageCache(context);
        }

        //读取图片依次顺序，内存->本地->网络
        Bitmap bitmap = null;            /*没有url，就没有key*/
        if(url != null) {               /*如果不为空，*/
        	//内存->本地
            bitmap = webImageCache.get(url);
            if(bitmap == null) {/*从缓存里往外拿*/
            	//网络
                bitmap = getBitmapFromUrl(url);
                if(bitmap != null){     /*如果为空，就从缓存里拿*/
                	//bitmap加入缓存
                    webImageCache.put(url, bitmap);
                }
            }
        }
        return bitmap;
    }

    private Bitmap getBitmapFromUrl(String url) {
        Bitmap bitmap = null;

        try {
            URLConnection conn = new URL(url).openConnection();
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);
            bitmap = BitmapFactory.decodeStream((InputStream) conn.getContent());
        } catch(Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public static void removeFromCache(String url) {
        if(webImageCache != null) {
            webImageCache.remove(url);
        }
    }
}

