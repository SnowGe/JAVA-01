package com.jvmClassload.customer;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Base64.Decoder;

import javax.imageio.stream.FileCacheImageInputStream;

/**
 * @author Snow
 *
 */
public class CustomerCL extends  ClassLoader{
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException
	{
		String file = CustomerCL.class.getResource("Hello.xlass").getPath();
		Class<?> customerClass = new CustomerCL().findClass(file);
		Object obj = customerClass.newInstance();
		Method method = customerClass.getMethod("hello");
		
		method.invoke(obj);
		
		//System.out.println(file);
	}
	
	
	@Override
	protected Class<?> findClass(String file)throws ClassNotFoundException
	{
		byte[] bytes =decode(file); 
		
		return defineClass("Hello",bytes,0,bytes.length);
	}
	
	public byte[] decode(String file)
	{
		byte[] bytes = null;
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = null;
		
		
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			baos = new ByteArrayOutputStream();
			int length;
			byte[] data = new byte[1024];
			while((length = bis.read(data))!= -1)
			{
				baos.write(data, 0,length );
				
			}
			bytes = baos.toByteArray();
			for (int i = 0; i< bytes.length; i++)
			{
				bytes[i] = (byte)(255-bytes[i]);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}
}
