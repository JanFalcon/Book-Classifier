package com.categorizer.e_book;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class practice {

	public static void main(String[] args) {
		new practice();
	}

	String strCompare = "meow";
	StringBuffer sbCompare = new StringBuffer();
	StringBuilder sbuildCompare = new StringBuilder();
	
	String str = "";
	StringBuffer sb = new StringBuffer();
	StringBuilder sbuild = new StringBuilder();
	
	
	
	double before = 0;
	double after = 0;
	
	double time1 = 0, time2 = 0, time3 = 0;
	
	
	public void FilePracticeee() {
		try {
			char[] chars = new char[100*1024*1024];
			Arrays.fill(chars, 'A');
			String text = new String(chars);
			long start = System.nanoTime();
			BufferedWriter bw;
			bw = new BufferedWriter(new FileWriter("C:\\Users\\Asus\\eclipse-workspace\\Thesis_Project\\Romance.txt"));
			bw.write(text);
			bw.close();
			long time = System.nanoTime() - start;
			System.out.println("Wrote " + chars.length*1000L/time+" MB/s.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public practice() {
		
//		System.out.println(" ” \"");
		FilePracticeee();
//		sb.append("meow");
//		NewCalculations();
//		calculate();
	}
	
	public void NewCalculations() {
		before = System.currentTimeMillis();
		
		for(int x = 0; x < 1000; x++) {
			for(int y = 0; y < 100; y++) {
				sb.append("meow");
			}
		}
		str = sb.toString();
		
		after = System.currentTimeMillis();
		System.out.println(after - before);
	}
	
	public void calculate() {
		before = System.currentTimeMillis();
		str = "meow mowe moew woem";
		sbCompare.append("meow mowe moew woem");
		sbuildCompare.append("meow mowe moew woem");
		
		for(int x = 0; x < 10000; x++) {
			for(int y = 0; y < 10; y++) {
				String[] str1 = str.split(" ");
				for(int z = 0; z < str1.length; z++) {
					System.out.println(str1[z]);
				}
			}
		}
		after = System.currentTimeMillis();
		time1 = after - before;
		
		before = System.currentTimeMillis();
		for(int x = 0; x < 10000; x++) {
			for(int y = 0; y < 10; y++) {
				String[] str1 = sbCompare.toString().split(" ");
				for(int z = 0; z < str1.length; z++) {
					System.out.println(str1[z]);
				}
			}
		}
		after = System.currentTimeMillis();
		time2 = after - before;
		
		
		before = System.currentTimeMillis();
		for(int x = 0; x < 10000; x++) {
			for(int y = 0; y < 10; y++) {
				String[] str1 = sbuildCompare.toString().split(" ");
				for(int z = 0; z < str1.length; z++) {
					System.out.println(str1[z]);
				}
			}
		}
		after = System.currentTimeMillis();
		time3 = after - before;
		
		System.out.println("String : " + time1);
		System.out.println("StringBuffer : " + time2);
		System.out.println("StringBuilder : " + time3);
	}
	
}
