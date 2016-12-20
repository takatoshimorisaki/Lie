package mori.Lie;

import static java.lang.System.out;
import static mori.Lie.node.tools.Holder.mEqualChecker;
import static mori.Lie.node.tools.Holder.mEqualNomialChecker;
import static mori.Lie.node.tools.Holder.mFactory;
import static mori.Lie.node.tools.Holder.mNodeChecker;
import static mori.Lie.node.tools.Holder.mPrinter;
import static mori.Lie.node.tools.Holder.mTokenChecker;
import mori.Lie.expander.Expander;
import mori.tools.Rational;

public class Node extends mori.Lie.Lie{
	
	public final static int NULL_NODE           = -1;
	
	public final static int NUMBER_NODE         = 0;
	
	public final static int MONOMIAL_NODE       = 1;
	
	public final static int MONO_NODE           = 1;
	
	public final static int MULTINOMIAL_NODE    = 2;
	
	public final static int MULTI_NODE          = 2;
	
	public final static int POLYNOMIAL_NODE     = 3;
	
	public final static int POLY_NODE           = 3;
	
	public final static int BRACKET_NODE        = 4;
	
	public final static int EQU_NODE            = 5;

	public final static int EQUATION_NODE       = 5;
	
	public final static int OPE_ADD_NODE        = 6;
	
	public final static int OPE_SUB_NODE        = 7;
	
	public final static int OPE_MULTI_NODE      = 8;
	
	public final static int OPE_DIV_NODE        = 9;

	public final static int OPE_POWER_NODE      = 10;

	public final static int OPE_PLUS_NODE       = 11;

	public final static int OPE_MINUS_NODE      = 12;
	
	public final static int PARENTHESIS_NODE    = 20;

	public final static int ALPHABET_NODE       = 21;

	public final static int RATIONAL_NODE       = 22;
		
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
	
	public String toNodeType(){
		String ans = null;
		
		try{
			ans = mPrinter.toNodeType(this);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			return ans;
		}
	}
}

