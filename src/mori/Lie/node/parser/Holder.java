package mori.Lie.node.parser;

import mori.Lie.Node;

public class Holder {

	public static Node mExe(String arg)throws Exception{
		
		TokenParser tp = new TokenParser();
		
		for(int id = 0; id < arg.length(); id++){
			
			String str = arg.substring(id, id+1);
			
			if(str.equals(" ") || str.equals("\t")){
				continue;
			}
			
			tp.mExe(str);
		}
		
		return tp.mParse();
	}
}
