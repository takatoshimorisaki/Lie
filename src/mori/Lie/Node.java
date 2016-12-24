package mori.Lie;

import static java.lang.System.out;
import static mori.Lie.NodeType.*;
import static mori.Lie.node.tools.Holder.mEqualChecker;
import static mori.Lie.node.tools.Holder.mEqualNomialChecker;
import static mori.Lie.node.tools.Holder.mFactory;
import static mori.Lie.node.tools.Holder.mNodeChecker;
import static mori.Lie.node.tools.Holder.mPrinter;
import static mori.Lie.node.tools.Holder.mTokenChecker;
import mori.Lie.expander.Expander;
import mori.tools.Rational;

public class Node extends mori.Lie.Lie{
	
	public final static double DOUBLE_THRESHOLD = 1.0e-07;
	
	public int mNodeType = NULL_NODE;
	
	public double mCoef = 1.0;
	
	public String mToken;
	
	public Rational mPower = new Rational();
	
	public java.util.Vector<Node> mSubNodes;
	
	public Node(){
		mInit();
	}
	
	public void mInit(){
		
		mCoef = 1.0;
		
		mToken = null;
		
		mPower.mInit();
		
		mSubNodes = new java.util.Vector<Node>();
	}
	
	public void add(Node arg){
		mSubNodes.add( mFactory.mExe(arg) );
	}
	
	public void mSet(int id, Node node){
		mSubNodes.set(id, node);
	}
	
	public void mRemove(int id){
		mSubNodes.remove(id);
	}
	
	public Node mGetSubNode(int id){
		return (Node)mSubNodes.elementAt(id);
	}
	
	/*
	 * @brief TokenÇ…ä‹Ç‹ÇÍÇƒÇÈï∂éöóÒÇópÇ¢ÇƒÇ¢ÇÈÇ©ÅAåüç∏Ç∑ÇÈÅB
	 */
	public boolean mCheck()throws Exception{
		boolean ans = false;
		
		ans =  mTokenChecker.mExe(this);
		
		if(ans == false){
			return ans;
		}
		
		return mNodeChecker.mExe(this);
	}

	public boolean mEqualNomials(Node anoNode)throws Exception{
		
		return mEqualNomialChecker.mExe(this, anoNode);
	}
	
	public boolean mEquals(Node anoNode)throws Exception{
		
		return mEqualChecker.mExe(this, anoNode);
	}
	
	public String mToString()throws Exception{
		
		return mPrinter.mToString(this);
	}
	
	public String toNodeType()throws Exception{
		
		return mPrinter.toNodeType(this);
	}
	
}

