package com.utils;

import java.io.File;
import java.io.IOException;

public class PickOut {
	public static void pickOutFromDir(String from,String to){
		File[] fs=null;
		File f=new File(from);
		if(f.isDirectory()){
			fs=f.listFiles();
			for (File file : fs) {
				pickOutFromDir(file.getAbsolutePath(),to);
			}
		}else{
			String fName=f.getName().substring(0,f.getName().lastIndexOf("."));
			if(fName.endsWith("_1")){
				String outPath=f.getAbsolutePath();
				outPath=outPath.substring(0,outPath.lastIndexOf("\\"))+"\\";
				outPath=outPath.substring(outPath.indexOf("\\")+1);
				
				File tmpDir=new File(to+outPath);
				outPath=to+outPath+f.getName();
				File tmpFile=new File(outPath);
				
				if(!tmpDir.exists()){
					tmpDir.mkdirs();
				}
				
				if(!tmpFile.exists()){
					try {
						tmpFile.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					FileAccess.copyFileUsingFileChannels(f, tmpFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
