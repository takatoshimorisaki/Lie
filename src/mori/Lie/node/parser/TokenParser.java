package mori.Lie.node.parser;

import static java.lang.System.out;
import static mori.Lie.Node.*;
import mori.Lie.Node;
import mori.tools.AlphabetChecker;
import mori.tools.NumericChecker;

public class TokenParser {

	private Node mRoot;

	private StringBuffer mStrBuf;
	
	private int mNodeType0;
	
	private int mNodeType1;
	
	private int mParenthesisDepth;
	
	private AlphabetParser mAlphabetParser;
	
	private NodeParser mNodeParser;
	
	public TokenParser(){
		
		mRoot = new Node();
	
		mAlphabetParser = new AlphabetParser();
		
		mNodeParser = new NodeParser();
	}

	public void mExe(String token)throws Exception{
	
		if(mStrBuf == null){
			
			mStrBuf = new StringBuffer();
		}
	
		mNodeType1 = mNodeType0;
		
		if(NumericChecker.mExe(token)){
			
			mStrBuf.append(token);
			
			mNodeType0 = NUMBER_NODE;
			
		}else
		if(AlphabetChecker.mExe(token)){
			
			mStrBuf.append(token);
			
			mNodeType0 = ALPHABET_NODE;
			
		}else
		if(token.equals("+")){

			mAddNumberAlphabetNode();
			
			mStrBuf.append(token);
			
			mNodeType0 = OPE_ADD_NODE;
			
			mAddNode();
			
		}else
		if(token.equals(":")){

			mAddNumberAlphabetNode();
			
			mStrBuf.append(token);
			
			mNodeType0 = OPE_ADD_NODE;
			
			mAddNode();
			
		}else
		if(token.equals("-")){

			mAddNumberAlphabetNode();
			
			mStrBuf.append(token);
			
			mNodeType0 = OPE_SUB_NODE;

			mAddNode();
			
		}else
		if(token.equals("*")){

			mAddNumberAlphabetNode();

			mStrBuf.append(token);
			
			mNodeType0 = OPE_MULTI_NODE;

			mAddNode();
			
		}else
		if(token.equals("/")){

			mAddNumberAlphabetNode();

			mStrBuf.append(token);
			
			mNodeType0 = OPE_DIV_NODE;

			mAddNode();

		}else
		if(token.equals("^")){

			mAddNumberAlphabetNode();

			mStrBuf.append(token);
			
			mNodeType0 = OPE_POWER_NODE;

			mAddNode();
			
		}else
		if(token.equals("(")){

			mAddNumberAlphabetNode();
			
			mStrBuf.append(token);
			
			mNodeType0 = PARENTHESIS_NODE;
			
			mParenthesisDepth++;

		}else
		if(token.equals(")")){

			mStrBuf.append(token);
			
			mNodeType0 = PARENTHESIS_NODE;
			
			mParenthesisDepth--;
			
			mAddNode();
		}	
	}

	private void mAddNumberAlphabetNode()throws Exception{
		
		if(mNodeType1 == NUMBER_NODE
		&& mParenthesisDepth == 0){
			
			Node node = new Node();
			
			node.mNodeType = NUMBER_NODE;
			
			node.mToken = mStrBuf.toString();
			
			mRoot.add(node);
			
			mStrBuf = new StringBuffer();
		}else
		if(mNodeType1 == ALPHABET_NODE
		&& mParenthesisDepth == 0){
			
			Node node = mAlphabetParser.mExe( mStrBuf.toString() );

			mRoot.add(node);
			
			mStrBuf = new StringBuffer();
		}
	}

	private void mAddNode(){

		if(mParenthesisDepth == 0){
			
			Node node = new Node();
			
			node.mNodeType = mNodeType0;
			
			mRoot.add(node);
			
			mStrBuf = null;
		}
	}

	public Node mParse()throws Exception{

		if(mNodeType0 == NUMBER_NODE){

			Node node = new Node();
			
			node.mNodeType = NUMBER_NODE;
			
			node.mToken = mStrBuf.toString();
			
			mRoot.add(node);
			
			mStrBuf = null;
		}else
		if(mNodeType0 == ALPHABET_NODE){
			
			Node node = mAlphabetParser.mExe( mStrBuf.toString() );

			mRoot.add(node);
			
			mStrBuf = null;
		}
		
		out.printf("TokenParser mParse mRoot %s\n", mRoot.toString());
		
		Node ans = mNodeParser.mExe(mRoot);
		
		return ans;
	}
}
