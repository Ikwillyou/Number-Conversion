package com.company;

import java.util.ArrayList;

public class Conversion {
    public Conversion(){

    }

    public static String decimalToHex(int value){
        String hexNum = toUnsignedString(value,4);
        return hexNum;
    }

    public static String decimalToBinary(int value){
        String binNum = toUnsignedString(value,1);
        return binNum;
    }

    public static String decimalToBCD(int value){
        int[]decimal = Integer.toString(value).chars().map(c -> c-'0').toArray();
        ArrayList<String> bcd = new ArrayList<String>();
        for(int x = 0 ; x < decimal.length; x++){
            switch(decimal[x]) {
                case (1):
                    bcd.add("0001 ");
                    break;
                case (2):
                    bcd.add("0010 ");
                    break;
                case (3):
                    bcd.add("0011 ");
                    break;
                case (4):
                    bcd.add("0100 ");
                    break;
                case (5):
                    bcd.add("0101 ");
                    break;
                case (6):
                    bcd.add("0110 ");
                    break;
                case (7):
                    bcd.add("0111 ");
                    break;
                case (8):
                    bcd.add("1000 ");
                    break;
                case (9):
                    bcd.add("1001 ");
                    break;
            }
        }
        StringBuffer sb = new StringBuffer();
        for(String s : bcd){
            sb.append(s);
            sb.append(" ");
        }
        String str = sb.toString();
        return(str);
    }

    public static double hexToDecimal(String value){
        String beforePoint = value.substring(0,value.indexOf('.'));
        String afterPoint = value.substring(value.indexOf('.')+1);
        double fullNum = 0;
        int count = beforePoint.length()-1;
        int beforeTotal = 0;

        for(int x = 0; x < beforePoint.length(); x++){
            double temp;
            switch(String.valueOf(beforePoint.toCharArray()[count]).toUpperCase()) {
                case "A":
                    temp = 10;
                    break;
                case "B":
                    temp = 11;
                    break;
                case "C":
                    temp = 12;
                    break;
                case "D":
                    temp = 13;
                    break;
                case "E":
                    temp = 14;
                    break;
                case "F":
                    temp = 15;
                    break;
                default:
                    temp = Double.parseDouble(String.valueOf(beforePoint.toCharArray()[count]));
                    break;
            }
            temp = temp * Math.pow(16,x);
            beforeTotal += Math.round(temp);
            count--;
        }

        double afterTotal = 0.0;
        count = 0;
        for(int x = 1; x < afterPoint.length()+1; x++){
            double temp;
            switch(String.valueOf(afterPoint.toCharArray()[count]).toUpperCase()) {
                case "A":
                    temp = 10;
                    break;
                case "B":
                    temp = 11;
                    break;
                case "C":
                    temp = 12;
                    break;
                case "D":
                    temp = 13;
                    break;
                case "E":
                    temp = 14;
                    break;
                case "F":
                    temp = 15;
                    break;
                default:
                    temp = Double.parseDouble(String.valueOf(afterPoint.toCharArray()[count]));
                    break;
            }
            temp = temp * 1/Math.pow(16,x);
            afterTotal += temp;
            count++;
        }

        fullNum = afterTotal + beforeTotal;

        return(fullNum);


    }

    public static double binaryToDecimal(String value){
        String beforePoint = value.substring(0,value.indexOf('.'));
        String afterPoint = value.substring(value.indexOf('.')+1);
        double fullNum = 0;
        int count = beforePoint.length()-1;
        int beforeTotal = 0;
        for(int x = 0; x < beforePoint.length(); x++){
            double temp;
            temp = Double.parseDouble(String.valueOf(beforePoint.toCharArray()[count]));
            temp = temp * Math.pow(2,x);
            beforeTotal += Math.round(temp);
            count--;
        }
        double afterTotal = 0.0;
        count = 0;
        for(int x = 1; x < afterPoint.length()+1; x++){
            double temp;
            temp = Double.parseDouble(String.valueOf(afterPoint.toCharArray()[count]));
            temp = temp * 1/Math.pow(2,x);
            afterTotal += temp;
            count++;
        }

        fullNum = afterTotal + beforeTotal;

        return(fullNum);


    }

    private static String toUnsignedString(int val, int shift) {
        // assert shift > 0 && shift <=5 : "Illegal shift value";
        int mag = Integer.SIZE - Integer.numberOfLeadingZeros(val);
        int chars = Math.max(((mag + (shift - 1)) / shift), 1);
        char[] buf = new char[chars];

        formatUnsignedInt(val, shift, buf, 0, chars);

        // Use special constructor which takes over "buf".
        return new String(buf);
    }

    private static int formatUnsignedInt(int val, int shift, char[] buf, int offset, int len) {
        int charPos = len;
        int radix = 1 << shift;
        int mask = radix - 1;
        do {
        buf[offset + --charPos] = digits[val & mask];
        val >>>= shift;
    } while (val != 0 && charPos > 0);

        return charPos;
}

    final static char[] digits = {
            '0' , '1' , '2' , '3' , '4' , '5' ,
            '6' , '7' , '8' , '9' , 'a' , 'b' ,
            'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
            'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
            'o' , 'p' , 'q' , 'r' , 's' , 't' ,
            'u' , 'v' , 'w' , 'x' , 'y' , 'z'
    };


}
