package com.mro.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import biz.source_code.base64Coder.Base64Coder;

// Use this class to encode / decode Serializable-objects
public class Base64Encode {

	public static String encodeObject(Serializable ob) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);

		oos.writeObject(ob);
		oos.close();

		return new String(Base64Coder.encode(bos.toByteArray()));
	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T decodeObject(String str)
			throws IOException, ClassNotFoundException {

		byte[] data = Base64Coder.decode(str);
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(
				data));

		Object ob = ois.readObject();

		ois.close();

		return (T) ob;
	}
}