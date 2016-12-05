package mori.Lie.factor;

import static java.lang.System.out;
import static mori.Lie.factor.Holder.mCommonFactorSearcher;
import static mori.Lie.factor.Intersector.INTERSECTOR_LEFT;
import static mori.Lie.factor.Intersector.INTERSECTOR_RIGHT;
import static mori.Lie.node.tools.Holder.mDivider;
import mori.Lie.Node;

public class Factorizer extends mori.Lie.Lie{

	public void mExe(int id)throws Exception{
		
		Node equ = mEquations.mGet(id);
		
		Node leftNode = equ.mGetSubNode(0);
		
		Node rightNode = equ.mGetSubNode(1);
	
		Node leftSideLeftAngle = mCommonFactorSearcher.mExe(leftNode, INTERSECTOR_LEFT);

		if(leftSideLeftAngle != null){
			
			out.printf("leftSideLeftAngle:%s\n", leftSideLeftAngle.toString());
			
			Node dividedNode = mDivider.mExe(leftNode, leftSideLeftAngle, INTERSECTOR_LEFT);
			
			out.printf("dividedNode:%s\n", dividedNode.toString());
			
		}else{
			
			out.println("leftSideLeftAngle is null.");
		}

		Node leftSideRightAngle = mCommonFactorSearcher.mExe(leftNode, INTERSECTOR_RIGHT);

		if(leftSideRightAngle != null){
			
			out.printf("leftSideRightAngle:%s\n", leftSideRightAngle.toString());

			Node dividedNode = mDivider.mExe(leftNode, leftSideRightAngle, INTERSECTOR_RIGHT);
			
			out.printf("dividedNode:%s\n", dividedNode.toString());
			
		}else{
			
			out.println("leftSideRightAngle is null.");
		}

		Node rightSideLeftAngle = mCommonFactorSearcher.mExe(rightNode, INTERSECTOR_LEFT);
		
		if(rightSideLeftAngle != null){

			out.printf("rightSideLeftAngle:%s\n", rightSideLeftAngle.toString());

			Node dividedNode = mDivider.mExe(rightNode, rightSideLeftAngle, INTERSECTOR_LEFT);
			
			out.printf("dividedNode:%s\n", dividedNode.toString());
			
		}else{
			
			out.println("rightSideLeftAngle is null.");
		}

		Node rightSideRightAngle = mCommonFactorSearcher.mExe(rightNode, INTERSECTOR_RIGHT);
		
		if(rightSideRightAngle != null){

			out.printf("rightSideRightAngle:%s\n", rightSideRightAngle.toString());

			Node dividedNode = mDivider.mExe(rightNode, rightSideRightAngle, INTERSECTOR_RIGHT);
			
			out.printf("dividedNode:%s\n", dividedNode.toString());
			
		}else{
			
			out.println("rightSideRightAngle is null.");
		}
	}
}
