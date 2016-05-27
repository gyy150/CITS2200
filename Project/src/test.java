import java.io.File;

public class test {
	public static void main(String[] argv){
		File file = new File(".");
		for(String fileNames : file.list()){
			System.out.println(fileNames);
		}
		InputReader b = new InputReader();
		
		System.out.println(b.FindClosenessCentrality(0));
		System.out.println("Closeness Centrality" );
		
		System.out.println("Degree Centrality" );
		System.out.println(b.FindDegreeCentrality() );
		
		System.out.println("" );
		System.out.println("***************number of vertex*********************************" );
		System.out.println(b.NumberofVertex );
		System.out.println("******************************************************************" );
		
		b.PrintAdjacencyMatrix();
		
		/***
		System.out.println(b.VertexArray[5] );
		System.out.println(b.VertexArray[1]);
		System.out.println(b.VertexArray[2] );
		System.out.println(b.VertexArray[3] );
		System.out.println(b.VertexArray[4] );
		**/
		
	}
	public static void test1(){
		BinarySearchTree a = new BinarySearchTree(5);
		System.out.println(BinarySearchTree.IndexCounter);
		a.addNode(10);
		System.out.println(BinarySearchTree.IndexCounter);
		a.addNode(20);
		System.out.println(BinarySearchTree.IndexCounter);
		a.addNode(15);
		System.out.println(BinarySearchTree.IndexCounter);
		
		System.out.println(a.addNode(20));
		System.out.println(BinarySearchTree.IndexCounter);
	}
	
}
