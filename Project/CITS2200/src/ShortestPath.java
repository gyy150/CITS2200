import java.util.*;
public class ShortestPath {
	public LinkedList<Integer> p;
	
	public ShortestPath(){
		this.p = new LinkedList<Integer>();
	}
	
	public ShortestPath(int s){
		this.p = new LinkedList<Integer>();
		p.add(s);
	}
	public void printPath(){
		for(int i: p ){
			System.out.printf("----->%5d ", i);
		}
		System.out.println("");
	}
	
}
