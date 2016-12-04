package mori.Lie.divider;

import static mori.Lie.divider.Holder.mDividerMonoMono;
import static mori.Lie.divider.Holder.mDividerMonoMulti;
import static mori.Lie.divider.Holder.mDividerMonoNum;
import static mori.Lie.divider.Holder.mDividerNumMono;
import static mori.Lie.divider.Holder.mDividerNumMulti;
import static mori.Lie.divider.Holder.mDividerNumNum;
import static mori.Lie.Node.*;
import mori.Lie.Node;

public class Divider {

	public final static int DIVIDER_LEFT = 1;
	
	public final static int DIVIDER_RIGHT = 2;
	
	public Node mExe(
		Node aOneNode,
		Node aAnoNode,
		int  aLeftRight
	)throws Exception{
		Node ans = new Node();
		
		mExe(ans, aOneNode, aAnoNode, aLeftRight);
		
		return ans;
	}

	public void mExe(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode,
		int  aLeftRight
	)throws Exception{
		
		if(aOneNode.mNodeType == NULL_NODE){
			
			throw new Exception();
			
		}else
		if(aOneNode.mNodeType == NUMBER_NODE){
			
			if(aAnoNode.mNodeType == NUMBER_NODE){
				
				mDividerNumNum.mExe(aDestNode, aOneNode, aAnoNode);
				
			}else
			if(aAnoNode.mNodeType == MONO_NODE){
				
				mDividerNumMono.mExe(aDestNode, aOneNode, aAnoNode);
				
			}else
			if(aAnoNode.mNodeType == MULTI_NODE){
				
				mDividerNumMulti.mExe(aDestNode, aOneNode, aAnoNode);
				
			}else{
			
				throw new Exception();
			}
		}else
		if(aOneNode.mNodeType == MONO_NODE){
			
			if(aAnoNode.mNodeType == NUMBER_NODE){
				
				mDividerMonoNum.mExe(aDestNode, aOneNode, aAnoNode);

			}else
			if(aAnoNode.mNodeType == MONO_NODE){
				
				mDividerMonoMono.mExe(aDestNode, aOneNode, aAnoNode, aLeftRight);

			}else
			if(aAnoNode.mNodeType == MULTI_NODE){
				
				mDividerMonoMulti.mExe(aDestNode, aOneNode, aAnoNode, aLeftRight);
				
			}else{
			
				throw new Exception();
			}
		}else
		if(aOneNode.mNodeType == MULTI_NODE){
			
			if(aAnoNode.mNodeType == NUMBER_NODE){

				mDividerNumMulti.mExe(aDestNode, aAnoNode, aOneNode);

			}else
			if(aAnoNode.mNodeType == MONO_NODE){
				

			}else
			if(aAnoNode.mNodeType == MULTI_NODE){
				
				
			}else{
			
				throw new Exception();
			}
		}else{

			throw new Exception();
		}
	}
}
