package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CountCodeLine {
	
	private static int lineNum = 0;
	
	public static void countLine(File file) throws Exception{
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(int i=0;i<files.length;i++) {
				CountCodeLine.countLine(files[i]);
			}
		}else {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while((line=reader.readLine())!=null) {
				CountCodeLine.lineNum++;
			}
			reader.close();
		}
	}
	public static void search(File file) throws Exception{
		if(file.isDirectory()&&file.getName().equals("src")) {
				CountCodeLine.countLine(file);
		}else {
			if(file.isDirectory()) {
				File[] files = file.listFiles();
				for(int i=0;i<files.length;i++) {
					CountCodeLine.search(files[i]);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		File file = new File("F:\\workspace");
		CountCodeLine.countLine(file);
		System.out.println(CountCodeLine.lineNum);
	}
}
