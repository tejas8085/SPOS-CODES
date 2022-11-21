


import java.io.*;
import java.util.*;
public class passIIassembler {

    public static void main(String args[])throws IOException 
    {
        BufferedReader b1=new BufferedReader(new FileReader("C:\\Users\\Mahesh Chaudhari\\Desktop\\intermediate.txt"));
        BufferedReader b2=new BufferedReader(new FileReader("C:\\Users\\Mahesh Chaudhari\\Desktop\\symtab.txt"));
        BufferedReader b3=new BufferedReader(new FileReader("C:\\Users\\Mahesh Chaudhari\\Desktop\\littab.txt"));

        
        FileWriter f1=new FileWriter("C:\\Users\\Mahesh Chaudhari\\Desktop\\pass2.txt");
        HashMap<Integer, String> sysSymbol=new HashMap<Integer,String>();
        HashMap<Integer, String> litSymbol=new HashMap<Integer,String>();
        HashMap<Integer, String> litAddr=new HashMap<Integer,String>();
        
        String s;
        int symtabPointer=1,littabPointer=1,offset;
        while((s=b2.readLine())!=null) {
            String word[]=s.split("\t\t\t");
            sysSymbol.put(symtabPointer++, word[1]);
            
        }
        
        while((s=b3.readLine())!=null) {
            String word[]=s.split("\t\t");
            litSymbol.put(littabPointer, word[0]);
            litAddr.put(littabPointer++, word[1]);
            
        }
        
        while((s=b1.readLine())!=null) {
            if(s.substring(1,6).compareToIgnoreCase("IS,00")==0) {
                f1.write("+ 00 0 000\n");
            }
            else if(s.substring(1,3).compareToIgnoreCase("IS")==0) {
                f1.write("+ "+s.substring(4,6)+" ");
                if(s.charAt(9)==')') {
                    f1.write(s.charAt(8)+" ");
                    offset=3;
                }
                else {
                    f1.write("0 ");
                    offset=0;
                }
                if(s.charAt(8+offset)=='S') {
                    f1.write(sysSymbol.get(Integer.parseInt(s.substring(10+offset,s.length()-1)))+"\n");
                }
                else {
                    f1.write(litAddr.get(Integer.parseInt(s.substring(10+offset,s.length()-1)))+"\n");
                }
            }
            else if(s.substring(1,6).compareToIgnoreCase("DL,01")==0) {
                    String s1=s.substring(10,s.length()-1),s2="";
                    for(int i=0;i<3-s1.length();i++) {
                        s2+="0";
                    }
                    s2+=s1;
                    f1.write("+ 00 0 "+s2+"\n");
                }
            else if(s.substring(1,6).compareToIgnoreCase("DL,02")==0) {
                String s1=s.substring(10,s.length()-1),s2="";
                for(int i=0;i<3-s1.length();i++) {
                    s2+="0";
                }
                s2+=s1;
                f1.write("+ 00 0 "+s2+"\n");
            }
            else {
                f1.write("\n");
            }
            
        }
        f1.close();
        b1.close();
        b2.close();
        b3.close();
    }

 