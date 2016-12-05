package mori.Lie.factor;

public class Holder {

	public static CommonFactorSearcher mCommonFactorSearcher = new CommonFactorSearcher();
	
	public static Intersector mIntersector = new Intersector();
	
	public static IntersectorMonoMono mIntersectorMonoMono = new IntersectorMonoMono();
	
	public static IntersectorMonoMulti mIntersectorMonoMulti = new IntersectorMonoMulti();
	
	public static IntersectorMultiMulti mIntersectorMultiMulti = new IntersectorMultiMulti();
	
	public static IntersectorNumNum mIntersectorNumNum = new IntersectorNumNum();
	
	public static Factorizer mFactorizer = new Factorizer();
	
}
