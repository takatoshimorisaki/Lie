package mori.Lie.node.parser;

import static mori.Lie.Node.*;
import java.util.Vector;
import mori.Lie.Node;
import mori.Lie.Token;

public class AlphabetParser extends mori.Lie.Lie{

	public Vector<Node> mNodes;
	
	public Node mExe(String arg)throws Exception{
		
		mNodes = new Vector<Node>();
		
		String str = arg.substring(0);
		
		for(int id = 0; id < mTokens.size(); id++){
			
			Token token = mTokens.get(id);
			
			if(str.startsWith(token.mValue)){
				
				Node node = new Node();
				
				node.mNodeType = MONO_NODE;
				
				node.mToken = new String( token.mValue );
				
				mNodes.add(node);
				
				id += token.mValue.length();
			}
		}// for id
		
		Node ans = new Node();
		
		if(mNodes.size() == 0){
		
			return ans;
			
		}else
		if(mNodes.size() == 1){
			
			return mNodes.get(0);
			
		}else{
			
			ans.mNodeType = MULTI_NODE;
			
			for(int id = 0; id < mNodes.size(); id++){
				
				Node node = mNodes.get(id);
				
				ans.add(node);
			}
			
			return ans;
		}
	}
}
