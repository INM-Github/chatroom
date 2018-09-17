package com.pack;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/** �Զ������Ϣ�����
 * 
 * @author Administrator
 * 
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 * 
 * @pdOid d4519964-11f3-4894-ae01-7066a926f56e */
public class MyObjectOutputStream extends BufferedOutputStream implements PackInterface {
   /** �����
    * 
    * 
    * @pdOid a0d98b93-cf11-4211-8011-04fb6f6bc0ca */
   private PackOper packOper = PackOper.getInstance();
   
   /** ���캯��
    * 
    * @param out
    * @pdOid 973b157d-768f-4889-876b-2354e7519466 */
   public MyObjectOutputStream(OutputStream out) {
   	super(out);
   }
   
   /** ���һ����Ϣ
    * 
    * @param object
    * @throws IOException
    * @pdOid 47e93763-60ee-43de-8c63-ecb473e76c75 */
   public synchronized void writeMessage(Object object) throws IOException {
   	super.write(packOper.createPackage(object));
   	super.flush();
   }

}