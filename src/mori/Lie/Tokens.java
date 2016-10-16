package mori.Lie;

import static java.lang.System.out;

public class Tokens{
	
	public java.util.Vector mValues = new java.util.Vector<Token>();
	
	public Tokens(){
		
	}
	
	public boolean mCheck(String arg){
		
		if(arg == null){
			return true;
		}
		for(int cnt = 0; cnt < mValues.size(); cnt++){
			
			Token token = (Token)mValues.elementAt(cnt);
			
			if(token.mValue.equals(arg)){
				return true;
			}
		}
		return false;
	}
	public void add(String arg){
		
		String trimedStr = arg.trim();
		
		if(trimedStr.length() < 1){
			
			// output list.
			for(int cnt = 0; cnt < mValues.size(); cnt++){
				
				Token token = (Token)mValues.elementAt(cnt);
				
				out.printf("%s %d\n", token.mValue, token.mValue.length());
			}
		}else{
	
			// check duplications.
			boolean match = false;
			
			for(int cnt = 0; cnt < mValues.size(); cnt++){
				
				Token token = (Token)mValues.elementAt(cnt);
				
				if(trimedStr.equals( token.mValue ) ){
					
					match = true;
					
					break;
				}
			}
			
			if(match == false){
				Token token = new Token(trimedStr);
				
				mValues.add(token);
			}
		}
	}
	
}

