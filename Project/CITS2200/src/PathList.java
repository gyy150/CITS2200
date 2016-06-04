import java.util.*;
public class PathList {
	private int PathCount;
	public LinkedList<ShortestPath> PathList;
	
	/**
	 * reverse a path list 
	 * @param PL the path list to be reversed 
	 * @param sourcevertex   the beginning vertex
	 * @return
	 */
	public static LinkedList<ShortestPath> reversePath(LinkedList<ShortestPath> PL, int sourcevertex ){
		
		LinkedList<ShortestPath> pl = new LinkedList<ShortestPath>();
		
		for(ShortestPath p : PL){
			ShortestPath p3 = new ShortestPath();
			
			for(int a : p.p){
				p3.p.addFirst(a);
			}
			p3.p.removeLast();													//get rid of the destination vertex in the list, the end of the before list contain the destinagtion vertex
			p3.p.addFirst(sourcevertex);
			pl.add(p3);
		}
		
		return pl;
	}
	
	public PathList(){
		this.PathList = new LinkedList<ShortestPath>();
		this.PathCount = 0;
	}
	public int getPathCount(){
		return this.PathCount;
	}
	public void increaseCountBy(int n){
		this.PathCount = this.PathCount +n;
	}
	
	public void increaseCount(){
		this.PathCount++;
	}
	
	public void decreaseCount(){
		this.PathCount--;
	}
	
	
	
	/**
	 * used to create path from one vertex to another one when they are directly connected 
	 * @param s the index of source vertex
	 */
	
	public PathList(int s){
		this.PathList = new LinkedList<ShortestPath>();
		this.PathCount = 1;
		ShortestPath p = new ShortestPath(s);
		this.PathList.add(p);
		
	}
	/**
	 * 
	 * @param p a list of path to be added to the current collection of path
	 */
	public void addPath(LinkedList<ShortestPath> p ){
		for(ShortestPath a : p){
			this.PathList.add(a);
		}
	}
	/**
	 * clear all the path stored inside the path list, also reset the path count to be 0
	 */
	public void clearPath(){
		this.PathList.clear();
		this.PathCount = 0;
	}
	

	public void printAllPath(){
		for(ShortestPath p :PathList){
			p.printPath();
		}
	}
	
	public LinkedList<ShortestPath>  combinePath(PathList PL){
		
		LinkedList<ShortestPath> CombinedPathList = new LinkedList<ShortestPath>();
		for(ShortestPath p1: this.PathList){
			for(ShortestPath p2 : PL.PathList){
				ShortestPath p3 = new ShortestPath();
				for(int a : p1.p){
					p3.p.add(a);
				}
				for(int a: p2.p){
					p3.p.add(a);
				}
				CombinedPathList.add(p3);
			}
		}
		return CombinedPathList;
	}
}
