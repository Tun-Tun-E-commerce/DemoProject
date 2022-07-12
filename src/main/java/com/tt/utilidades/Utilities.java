package com.tt.utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
	public static Date convertiFecha(String fch) {
    	SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy");
    	Date date=null;
    	try {
			date= formato.parse(fch);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return date;
    }
}
