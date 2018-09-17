package com.pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * 自定义的读消息输入流
 * 
 * 
 */
public class MyObjectInputStream extends BufferedReader implements PackInterface {

	private PackOper packOper = PackOper.getInstance();

	public MyObjectInputStream(InputStream in)
			throws UnsupportedEncodingException {
		super(new InputStreamReader(in, CHARSET), 10240);
	}

	public synchronized Message readMessage() throws IOException {
		String ch = null;
		StringBuffer buf = null;
		int temp = 0;
		while (true) {
			temp = this.read();
			if (temp == -1)
				throw new IOException();
			ch = (char) temp + "";
			if (ch.equals(STARTSEPARATOR)) {
				buf = new StringBuffer();
				buf.append(ch);
				temp = this.read();
				if (temp == -1)
					throw new IOException();
				ch = (char) temp + "";
				while (!ch.equals(ENDSEPARATOR)) {
					buf.append(ch);
					temp = this.read();
					if (temp == -1)
						throw new IOException();
					ch = (char) temp + "";
				}
				buf.append(ch);
				return packOper.unbindPackage(buf.toString().getBytes(CHARSET));
			}
		}
	}

}