package mori.tools;

import static java.lang.System.out;

public class NumericChecker {

	public static boolean mExe(String token){
		
		char charValue = token.charAt(0);
		
		if(charValue >= '0' && charValue <= '9'){
			return true;
		}else{
			return false;
		}
	}
	
	public static void main(String[] args){
		
		NumericChecker nc = new NumericChecker();
		
		boolean ans = nc.mExe("0");
		
		out.println(ans);
		
		ans = nc.mExe("1");

		out.println(ans);
		
		ans = nc.mExe("A");

		out.println(ans);

		ans = nc.mExe("[");

		out.println(ans);

		ans = nc.mExe("]");

		out.println(ans);

		ans = nc.mExe(",");

		out.println(ans);
	}
}
