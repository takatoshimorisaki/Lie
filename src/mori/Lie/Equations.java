package mori.Lie;

import static java.lang.System.out;
import static mori.Lie.Node.*;

import mori.Lie.expander.Expander;

public class Equations{
	
	public java.util.Vector mValues = new java.util.Vector<Node>();
	
	private static mori.Lie.NodeTools.Factory mFactory = new mori.Lie.NodeTools.Factory();
	
	private static mori.Lie.NodeTools.Printer mPrinter = new mori.Lie.NodeTools.Printer();
	
	private static mori.Lie.NodeTools.Together mTogether = new mori.Lie.NodeTools.Together();

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
	public int add(Node equ){
	
		mValues.add(equ);
		
		return (mValues.size() - 1);
	}
	
	public void add(String arg)throws Exception{
		
		String leftStr = arg.substring(0, arg.indexOf("=")).trim();
		
		String rightStr = arg.substring(arg.indexOf("=")+1, arg.length()).trim();
		
		Node leftNode = mFactory.mExe(leftStr);
		
		Node rightNode = mFactory.mExe(rightStr);
		
		Node equ = new Node();
		
		equ.mNodeType = EQU_NODE;
		
		equ.mSubNodes = new java.util.Vector<Node>();
		
		equ.mSubNodes.add(leftNode);
		
		equ.mSubNodes.add(rightNode);
		
		mValues.add(equ);
	}
	
	public void mList(){
		
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
	
	public void mExpand(int id)throws Exception{
		
		Node equ = mGet(id);
		
		add(mFactory.mExe(equ));
		
		int size = mValues.size();
		
		equ = mGet(size - 1);
		
		Node rightNode = mFactory.mExe(equ.mGetSubNode(1));

		boolean expanded = true;
		Node    destNode = new Node();
		
		while(expanded){
			
			for(int ect = 0; ect < mValues.size(); ect++){
				
				Node replaceNode = (Node)mValues.elementAt(ect);
				
				expanded = mExpander.mExe(destNode, rightNode, replaceNode);
		
				if(expanded){
					rightNode = destNode;
					
					destNode = new Node();
					
					break;
				}
			}// for ect
		}// while expanded
		
		mPrinter.mExe(equ);
	}

	public void mRemove(int id){
		
		mValues.remove(id);
	}
}

