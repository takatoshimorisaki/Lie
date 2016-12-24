package mori.tools;

import static java.lang.System.out;

public class AlphabetChecker {

	public static boolean mExe(String token){
		
		char charValue = token.charAt(0);

		if(charValue == '^'
		|| charValue == '['
		|| charValue == ']'
		|| charValue == ','){
			return false;
		}else
		if(charValue >= 'A' && charValue <= 'z'){
			return true;
		}else{
			return false;
		}
	}
	
	public static void main(String[] args){
		
		AlphabetChecker nc = new AlphabetChecker();
		
		boolean ans = nc.mExe("0");
		
		out.println(ans);
		
		ans = nc.mExe("1");

		out.println(ans);
		
		ans = nc.mExe("A");

		out.println(ans);

		ans = nc.mExe("a");

		out.println(ans);

		ans = nc.mExe("^");

		out.println(ans);
	}
}
