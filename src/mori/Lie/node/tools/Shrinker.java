package mori.Lie.node.tools;

import static java.lang.System.out;
import static mori.Lie.Node.*;
import static mori.Lie.node.tools.Holder.mEqualNomialChecker;
import static mori.Lie.node.tools.Holder.mFactory;
import static mori.Lie.node.translator.Holder.mNodeTranslator;
import mori.Lie.Node;

public class Shrinker extends mori.Lie.Lie{

	public boolean mSearch(Node arg)throws Exception{
		boolean shrinked = false;
		
		if(arg.mNodeType == OPE_ADD_NODE
		|| arg.mNodeType == OPE_SUB_NODE
		|| arg.mNodeType == OPE_MULTI_NODE
		|| arg.mNodeType == OPE_DIV_NODE){
			
			for(int id = 0; id < arg.mSubNodes.size(); id++){
			
				Node node = arg.mGetSubNode(id);
				
				shrinked = mSearch(node);
				
				if(shrinked){
					return shrinked;
				}
			}
		}else
		if(arg.mNodeType == PARENTHESIS_NODE){

			Node node = arg.mGetSubNode(0);
			
			shrinked = mSearch(node);
			
			return shrinked;
		}else
		if(arg.mNodeType == POLY_NODE){
			
			for(int id = 0; id < mEquations.size(); id++){
				
				Node equ = mEquations.mGet(id);
				
				Node leftNode = equ.mGetSubNode(0);
				
				Node rightNode = equ.mGetSubNode(1);
				
				boolean isEqual = mEqualNomialChecker.mExe(arg, rightNode);
				
				if(isEqual){
					mFactory.mCopy(arg, leftNode);
					
					shrinked = true;
					
					return shrinked;
				}
			}
		}else{
			// nothing to do.
		}
		
		return shrinked;
	}
	
	public void mExe(
		int id
	)throws Exception{

		Node equ = mEquations.mGet(id);
		
		mEquations.add( mFactory.mExe(equ) );
		
		equ = mEquations.mGet(mEquations.size() - 1);
		
		Node rightNode = equ.mGetSubNode(1);
		
		boolean shrinked = mSearch(rightNode);
		
		if(shrinked == false){
			
			mEquations.mRemove(mEquations.size() - 1);
			
		}else{
			
			equ = mNodeTranslator.mExe(equ);
			
			out.printf("%d:%s\n", 
					mEquations.size() - 1,
					equ.toString());
			
			mEquations.mSet(mEquations.size() - 1, equ);
		}
	}
}
