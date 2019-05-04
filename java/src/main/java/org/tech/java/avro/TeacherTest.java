package org.tech.java.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.SeekableByteArrayInput;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.tech.java.compress.gzip.GzipTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherTest {

	public static void main(String[] args) throws Exception {
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("teacher.avsc");  
		Schema schema = new Schema.Parser().parse(inputStream);  
		
		List<Teacher> list = new ArrayList<>();
		for (int i=0; i<10000; i++) {
			list.add(new Teacher("zhang"+i, i+1));
		}


		// to file
		serializeAvroToFile(schema, list);
		deserialzeAvroFromFile(schema);

		// to byte
		byte[] content = serializeAvroToByteArray(schema, list);
		System.out.println("序列化byte大小："+content.length);
		// ------------------压缩-------------------------------
		long b = System.currentTimeMillis();
		byte[] compressContent = GzipTest.compress(content);
		System.out.println("压缩后byte大小："+compressContent.length);
		long e = System.currentTimeMillis();
		System.out.println("压缩时间："+(e-b));
		byte[] deCompressContent = GzipTest.uncompress(compressContent);
		System.out.println("解压缩byte大小："+deCompressContent.length);
		long e2 = System.currentTimeMillis();
		System.out.println("解压缩时间："+(e2-e));
		// ------------------压缩-------------------------------

		System.out.println("byte大小："+content.length);
		deserialzeAvroFromByteArray(schema, content);
	}
	
	public static byte[] getFile() throws Exception {
        File file = new File("E:/teacher.avro");  
        Long len = file.length();
        byte[] content  = new byte[len.intValue()];
        FileInputStream is = new FileInputStream(file);
        is.read(content);
        is.close();
        return content;
    }
	
	public static void serializeAvroToFile(Schema schema, List<Teacher> list) throws IOException {
		File diskFile = new File("E:/teacher.avro");  
		DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);  
		DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);  
		dataFileWriter.create(schema, diskFile);  
		for (Teacher t: list) {
			GenericRecord tc = new GenericData.Record(schema);  
			tc.put("name", t.getName());  
			tc.put("age", t.getAge());
            dataFileWriter.append(tc);  
        }
		dataFileWriter.close();  
	}
	
	public static void deserialzeAvroFromFile(Schema schema) throws IOException {
		File diskFile = new File("E:/teacher.avro");  
		DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);  
		DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(diskFile, datumReader);  
		GenericRecord _current = null;  
		while (dataFileReader.hasNext()) {  
		    _current = dataFileReader.next(_current);  
//		    System.out.println(_current);
		}  
		dataFileReader.close();  
	}
	
	public static byte[] serializeAvroToByteArray(Schema schema, List<Teacher> list) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);  
		DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);  
        dataFileWriter.create(schema, baos);
        for (Teacher t: list) {
			GenericRecord tc = new GenericData.Record(schema);  
			tc.put("name", t.getName());  
			tc.put("age", t.getAge());
            dataFileWriter.append(tc);  
        }
        dataFileWriter.close();
        return baos.toByteArray();
    }
	
	public static void deserialzeAvroFromByteArray(Schema schema, byte[] usersByteArray) throws IOException {
		SeekableByteArrayInput sbai = new SeekableByteArrayInput(usersByteArray);
		DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);  
		DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(sbai, datumReader);  
		GenericRecord _current = null;  
		while (dataFileReader.hasNext()) {  
		    _current = dataFileReader.next(_current);  
//		    System.out.println(_current);
		}  
		dataFileReader.close(); 
    }

}
