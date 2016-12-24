package mori.Lie.factor;

import static mori.Lie.factor.Holder.mIntersectorMonoMono;
import static mori.Lie.factor.Holder.mIntersectorMonoMulti;
import static mori.Lie.factor.Holder.mIntersectorMultiMulti;
import static mori.Lie.factor.Holder.mIntersectorNumNum;
import static mori.Lie.Node.*;
import static mori.Lie.NodeType.*;
import mori.Lie.Node;

/**
  èWçáêœÇåvéZÇ∑ÇÈÅB
 */
public class Intersector {

	public final static int INTERSECTOR_LEFT = 1;
	
	public final static int INTERSECTOR_RIGHT = 2;
	
	public Node mExe(
			Node oneNode,
			Node anoNode,
			int  aLeftRight
	) throws Exception{

		if(oneNode.mNodeType == NULL_NODE
		|| anoNode.mNodeType == NULL_NODE){
			
			return null;
		}else
		if(oneNode.mNodeType == NUMBER_NODE){
			
			if(anoNode.mNodeType == NUMBER_NODE
			|| anoNode.mNodeType == MONO_NODE
			|| anoNode.mNodeType == MULTI_NODE){
				
				return mIntersectorNumNum.mExe(oneNode, anoNode);
			
			}else{

				throw new Exception("not implemented.");
			}
		}else
		if(oneNode.mNodeType == MONO_NODE){

			if(anoNode.mNodeType == NUMBER_NODE){
				
				return mIntersectorNumNum.mExe(oneNode, anoNode);
			
			}else
			if(anoNode.mNodeType == MONO_NODE){

				return mIntersectorMonoMono.mExe(oneNode, anoNode);

			}else
			if(anoNode.mNodeType == MULTI_NODE){
				
				return mIntersectorMonoMulti.mExe(oneNode, anoNode, aLeftRight);
			}else{

				throw new Exception("not implemented.");
			}
		}else
		if(oneNode.mNodeType == MULTI_NODE){

			if(anoNode.mNodeType == NUMBER_NODE){
				
				return mIntersectorNumNum.mExe(oneNode, anoNode);
			
			}else
			if(anoNode.mNodeType == MONO_NODE){

				return mIntersectorMonoMulti.mExe(anoNode, oneNode, aLeftRight);

			}else
			if(anoNode.mNodeType == MULTI_NODE){
				
				return mIntersectorMultiMulti.mExe(oneNode, anoNode, aLeftRight);
				
			}else{

				throw new Exception("not implemented.");
			}
		}else{
			
			throw new Exception("not implemented.");
		}
	}
}
