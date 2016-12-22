package mori.Lie;

import static java.lang.System.out;
import static mori.Lie.factor.Holder.mFactorizer;
import static mori.Lie.Node.*;
import static mori.Lie.node.tools.Holder.mShrinker;
import mori.Lie.expander.Expander;
import mori.Lie.node.parser.Holder;

public class Equations{
	
	public java.util.Vector mValues = new java.util.Vector<Node>();
	
	private static mori.Lie.node.tools.Factory mFactory = new mori.Lie.node.tools.Factory();
	
	private static mori.Lie.node.tools.Printer mPrinter = new mori.Lie.node.tools.Printer();
	
	private static mori.Lie.node.tools.Together mTogether = new mori.Lie.node.tools.Together();

	private static Expander mExpander = new Expander();
	
	public Equations(){
	}
	
	public boolean mCheck(int id)throws Exception{

		Node equ = (Node)mValues.elementAt(id);
	
		Node leftNode = (Node)equ.mSubNodes.elementAt(0);
		
		Node rightNode = (Node)equ.mSubNodes.elementAt(1);
		
		boolean leftValid = leftNode.mCheck();

		boolean rightValid = rightNode.mCheck();
		
		if(leftValid == false || rightValid == false){
			
			mValues.removeElementAt(id);
			
			return false;
		}
		return true;
	}
	
	public void mCheck()throws Exception{
		boolean valid = false;
		boolean removed = false;
		
		for(int cnt = 0; cnt < mValues.size(); cnt++){
			
			valid = mCheck(cnt);
			
			if(valid == false){
				removed = true;
			}
		}
	}
	
	public void mSet(int id, Node node){
		
		mValues.set(id, node);
	}
	
	public int add(Node equ){
	
		mValues.add(equ);
		
		return (mValues.size() - 1);
	}
	
	public void add(String arg)throws Exception{
		
		String leftStr = arg.substring(0, arg.indexOf("=")).trim();
		
		String rightStr = arg.substring(arg.indexOf("=")+1, arg.length()).trim();
		
		Node leftNode = Holder.mExe(leftStr); // mFactory.mExe(leftStr);

		Node rightNode = Holder.mExe(rightStr); // mFactory.mExe(rightStr);
		
		Node equ = new Node();
		
		equ.mNodeType = EQU_NODE;
		
		equ.mSubNodes = new java.util.Vector<Node>();
		
		equ.mSubNodes.add(leftNode);
		
		equ.mSubNodes.add(rightNode);
		
		mValues.add(equ);
	}
	
	public void mList()throws Exception{
		
		for(int cnt = 0; cnt < mValues.size(); cnt++){
			
			Node equ = (Node)mValues.elementAt(cnt);
		
			Node leftNode = (Node)equ.mSubNodes.elementAt(0);
			
			Node rightNode = (Node)equ.mSubNodes.elementAt(1);
			
			out.printf("%d ", cnt);
			
			mPrinter.mExe(leftNode);
			
			out.print(" = ");
			
			mPrinter.mExe(rightNode);
			
			out.println();
		}
	}
	
	public int size(){
		return mValues.size();
	}
	
	public Node mGet(int id){
		return (Node)mValues.elementAt(id);
	}

	public void mFactor(int id)throws Exception{
		mFactorizer.mExe(id);
	}

	public void mShrink(int id)throws Exception{
		mShrinker.mExe(id);
	}
	
	public void mExpand(int id)throws Exception{
		
		Node equ = mGet(id);
		
		add(mFactory.mExe(equ));
		
		int size = mValues.size();

		boolean expanded = true;
		Node    destNode = new Node();
		
		while(expanded){
			equ = mGet(size - 1);
			
			Node rightNode = mFactory.mExe(equ.mGetSubNode(1));
		
			for(int ect = 0; ect < mValues.size(); ect++){
				
				Node replaceNode = (Node)mValues.elementAt(ect);
				
				expanded = mExpander.mExe(destNode, rightNode, replaceNode);
		
				if(expanded){
					
					equ.mSet(1, destNode);
					
					break;
				}
			}// for ect
		}
		
		out.printf("%d:", size - 1);
		
		mPrinter.mExeln(equ);
	}

	public void mRemove(int id){
		
		mValues.remove(id);
	}
}

