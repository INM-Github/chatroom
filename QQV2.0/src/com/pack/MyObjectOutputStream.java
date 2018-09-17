package com.pack;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/** 自定义的消息输出流
 * 
 * @author Administrator
 * 
 * TODO 要更改此生成的类型注释的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 * 
 * @pdOid d4519964-11f3-4894-ae01-7066a926f56e */
public class MyObjectOutputStream extends BufferedOutputStream implements PackInterface {
   /** 解包类
    * 
    * 
    * @pdOid a0d98b93-cf11-4211-8011-04fb6f6bc0ca */
   private PackOper packOper = PackOper.getInstance();
   
   /** 构造函数
    * 
    * @param out
    * @pdOid 973b157d-768f-4889-876b-2354e7519466 */
   public MyObjectOutputStream(OutputStream out) {
   	super(out);
   }
   
   /** 输出一条消息
    * 
    * @param object
    * @throws IOException
    * @pdOid 47e93763-60ee-43de-8c63-ecb473e76c75 */
   public synchronized void writeMessage(Object object) throws IOException {
   	super.write(packOper.createPackage(object));
   	super.flush();
   }

}