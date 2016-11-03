package mori.Lie.node.tools;

import static java.lang.System.out;

import mori.Lie.Node;

public class Factory extends mori.Lie.Node{
	
	public void mCopy(
		Node aDestNode,
		Node aSrcNode
	){
		aDestNode.mNodeType = aSrcNode.mNodeType;
		
		aDestNode.mCoef = aSrcNode.mCoef;
		
		if(aSrcNode.mToken != null){
			
			aDestNode.mToken = new String(aSrcNode.mToken);
		}
		
		aDestNode.mPower = aSrcNode.mPower;

		aDestNode.mSubNodes = null;
		
		if(aSrcNode.mSubNodes != null){
			
			aDestNode.mSubNodes = new java.util.Vector<Node>();
			
			int size = aSrcNode.mSubNodes.size();
					
			for(int id = 0; id < size; id++){
				
				Node node = aSrcNode.mGetSubNode(id);
				
				aDestNode.add(node);
			}
		}
	}
	
	public Node mExe(
		Node aOne,
		Node aAno,
		int aNodeType
	){
		Node node = new Node();
		
		node.mNodeType = aNodeType;
		
		node.mSubNodes = new java.util.Vector<Node>();
		
		node.mSubNodes.add(aOne);
		
		node.mSubNodes.add(aAno);
		
		return node;
	}
	
	public Node mExe(
		Node aSrcNode
	){
		Node destNode = new Node();

		destNode.mNodeType = aSrcNode.mNodeType;
		
		destNode.mCoef = aSrcNode.mCoef;
		
		if(aSrcNode.mToken != null){
			
			destNode.mToken = new String(aSrcNode.mToken);
		}
		
		destNode.mPower = aSrcNode.mPower;
		
		if(aSrcNode.mSubNodes != null){
			
			if(aSrcNode.mSubNodes.size() > 0){
				
				destNode.mSubNodes = new java.util.Vector<Node>();
				
				for(int cnt = 0; cnt < aSrcNode.mSubNodes.size(); cnt++){
					
					Node node = (Node)aSrcNode.mSubNodes.elementAt(cnt);
					
					destNode.mSubNodes.add( this.mExe(node) );
				}
			}
		}
		
		return destNode;
	}
	
	public Node mExe(String arg)throws Exception{
		
		Node dest = new Node();
		
		int addIndex = arg.indexOf(":");
		
		int multiIndex = arg.indexOf("*");
		
		int powerIndex = arg.indexOf("^");
		
		if(addIndex >= 0){
			
			dest.mNodeType = POLY_NODE;
			
			String[] parts = arg.split(":");
		
			dest.mSubNodes = new java.util.Vector<Node>();
			
			for(int cnt = 0; cnt < parts.length; cnt++){
				
				dest.mSubNodes.add( this.mExe(parts[cnt]) );
			}
		}else
		if( multiIndex >= 0){
			
			dest.mNodeType = MULTI_NODE;
			
			String[] parts = arg.split("\\*");

			dest.mSubNodes = new java.util.Vector<Node>();
			
			int startCnt = 0;
			double coef = 1.0;
			
			try{
				coef = Double.parseDouble(parts[0].trim());
				
				startCnt = 1;
				
			}catch(Exception ex){
				// nothing to do.
			}
			
			if((parts.length - startCnt) > 1){
				for(int cnt = startCnt; cnt < parts.length; cnt++){
					
					dest.mSubNodes.add( this.mExe(parts[cnt]) );
				}
				
				dest.mCoef = coef;
			}else{
				dest = mExe(parts[1]);
				
				dest.mCoef = coef * dest.mCoef;
			}
		}else{
			
			dest.mSubNodes = null;
			
			try{
				
				dest.mNodeType = NUMBER_NODE;
				
				dest.mCoef = (new Double(arg)).doubleValue();
				
				dest.mPower = 1;

			}catch(Exception ex){
				
				dest.mNodeType = MONOMIAL_NODE;
				
				String arg1 = null;
				
				if(arg.startsWith("-")){
					
					dest.mCoef = -1.0;
					
					arg1 = arg.substring(1);
					
				}else{
					
					arg1 = arg;
				}
				
				if(powerIndex >= 0){
					
					String[] parts = arg1.split("\\^");
					
					dest.mToken = new String(parts[0]);
					
					dest.mPower = Integer.parseInt(parts[1]);
					
				}else{
				
					dest.mToken = new String(arg1);
					
					dest.mPower = 1;
					
				}
			}
		}
		
		if(dest.mNodeType == NULL_NODE){
			throw new Exception(arg);
		}
		
		return dest;
	}
}

