package mori.Lie;

public interface I_Operator {

	public Node mExe(
		Node aOneNode,
		Node aAnoNode
	)throws Exception;

	public void mExe(
		Node aDestNode,
		Node aOneNode,
		Node aAnoNode
	)throws Exception;
}
