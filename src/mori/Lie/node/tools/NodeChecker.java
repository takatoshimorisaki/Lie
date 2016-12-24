package mori.Lie.node.tools;

import static mori.Lie.Node.*;
import static mori.Lie.NodeType.*;
import mori.Lie.Node;

/*
 * @brief Tokenに含まれてる文字列を用いているか、検査する。
 */
public class NodeChecker {

	public boolean mExe(Node arg) throws Exception{
		
		if(arg.mNodeType == MULTI_NODE){
			
			int size = arg.mSubNodes.size();
			
			if(size == 1){
				throw new Exception(arg.mToString() + " 15");
			}
			
			for(int cnt = 0; cnt < size; cnt++){
				
				Node node = arg.mGetSubNode(cnt);
				
				if(node.mToken == null || node.mToken.equals("")){
					
					throw new Exception(arg.mToString() + " 20");
				}
			}
			
			return true;
		}else if(arg.mNodeType == POLY_NODE){
				
			if(arg.mToken != null){
				
				throw new Exception(arg.mToString() + " 29");
			}

			int size = arg.mSubNodes.size();
			
			for(int cnt = 0; cnt < size; cnt++){
				
				Node node = arg.mGetSubNode(cnt);
			
				if(mExe(node) == false){
					return false;
				}
			}
			
			return true;
		}else{
			
			return true;
		}
	}
}
