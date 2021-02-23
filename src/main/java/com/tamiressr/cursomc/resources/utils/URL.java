package com.tamiressr.cursomc.resources.utils;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
/*
 * Classe criada para converter dados url (string) em uma lista de integer  e passar por parametro
 */
public class URL {
	
	
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	 public static List<Integer> decodeIntList(String s){
		 String[] vetor= s.split(",");
		 List<Integer> list=new ArrayList<>();
		 for(int i=0;i<vetor.length;i++) {
			 list.add( Integer.parseInt(vetor[i]));
		 }
		 return list;
	 }
}
