package mori.Lie.node.tools;

import static mori.Lie.Node.*;
import mori.Lie.Node;

/*
 * @brief Token‚ÉŠÜ‚Ü‚ê‚Ä‚é•¶š—ñ‚ğ—p‚¢‚Ä‚¢‚é‚©AŒŸ¸‚·‚éB
 */
public class NodeChecker {

	public boolean mExe(Node arg) throws Exception{
		
		if(arg.mNodeType == MULTI_NODE){
			
			int size = arg.mSubNodes.size();
			
			if(size == 1){
				throw new Exception(arg.toString() + " 15");
			}
			
			for(int cnt = 0; cnt < size; cnt++){
				
				Node node = arg.mGetSubNode(cnt);
				
				if(node.mToken == null || node.mToken.equals("")){
					
					throw new Exception(arg.toString() + " 20");
				}
			}
			
			return true;
		}else if(arg.mNodeType == POLY_NODE){
				
			if(arg.mToken != null){
				
				throw new Exception(arg.toString() + " 29");
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
